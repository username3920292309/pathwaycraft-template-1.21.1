package com.pathwaycraft.reader.attachments;

import java.util.UUID;

public record Characteristic(
        int sequence,
        String path,
        float digestion,
        boolean active,
        UUID owner,
        UUID target
) {
}
