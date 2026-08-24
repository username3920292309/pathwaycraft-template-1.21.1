package com.reader.pathwaycraft.attachments;

import com.reader.pathwaycraft.PathwayCraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;

import java.util.UUID;

public record Characteristic(
        int sequence,
        String path,
        float digestion,
        boolean active,
        UUID owner,
        UUID target,
        UUID origin
) {

    public CompoundTag toNbt(){
        CompoundTag tag = new CompoundTag();
        tag.putInt("sequence", sequence());
        tag.putString("pathway", path());
        tag.putFloat("digestion", digestion());
        tag.putBoolean("active", active());
        tag.putUUID("owner", owner());
        tag.putUUID("target", target());
        tag.putUUID("origin", origin());
        return tag;
    }

    public static Characteristic fromNBT(Tag tag){
        if (tag instanceof CompoundTag compoundTag){
            return new Characteristic(
                    compoundTag.getInt("sequence"),
                    compoundTag.getString("pathway"),
                    compoundTag.getFloat("digestion"),
                    compoundTag.getBoolean("active"),
                    compoundTag.getUUID("owner"),
                    compoundTag.getUUID("target"),
                    compoundTag.getUUID("origin")
            );
        } else {
            throw new RuntimeException("Bad tag");
        }
    }

}
