package com.reader.pathwaycraft.attachments;

import com.reader.pathwaycraft.PathwayCraft;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.neoforged.neoforge.common.util.INBTSerializable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BeyonderAttachment implements INBTSerializable<ListTag> {

    private final List<Characteristic> charList = new ArrayList<>();

    //add char
    public void addChar(Characteristic c){
        charList.add(c);
    }

    //remove char
    public void remove(Characteristic c) {
        charList.remove(c);
    }

    // Full iteration
    public List<Characteristic> getAll() {
        return charList;
    }

    // Filter by path
    public List<Characteristic> getByPath(String path) {
        return charList.stream()
                .filter(c -> c.path().equals(path))
                .toList();
    }

    // Filter by activity and sequence
    public List<Characteristic> getActiveBySequence(int sequence) {
        return charList.stream()
                .filter(Characteristic::active)
                .filter(c -> c.sequence() == sequence)
                .toList();
    }

    // Filter by owner UUID
    public List<Characteristic> getByOwner(UUID owner) {
        return charList.stream()
                .filter(c -> owner.equals(c.owner()))
                .toList();
    }



    @Override
    public ListTag serializeNBT(HolderLookup.Provider provider) {
        ListTag listTag = new ListTag();
        for (Characteristic c : charList) {
            listTag.add(c.toNbt());
        }
        return listTag;
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider provider, ListTag listTag) {
        for (Tag tag : listTag){
            addChar(Characteristic.fromNBT(tag));
        }


    }
}
