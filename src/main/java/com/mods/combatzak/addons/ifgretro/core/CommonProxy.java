package com.mods.combatzak.addons.ifgretro.core;

import cofh.core.render.IModelRegister;
import net.minecraft.network.play.server.SPacketChunkData;
import net.minecraft.server.management.PlayerChunkMap;
import net.minecraft.server.management.PlayerChunkMapEntry;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.NibbleArray;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;
import net.minecraftforge.common.ForgeChunkManager;

import java.util.Arrays;

/**
 * Created by CombatZAK on 7/3/2020.
 */
public class CommonProxy {

    public void preInit() {
    }

    public void init() {
    }

    public void addModelRegister(IModelRegister model) {

    }

    public void relightChunk(Chunk chunk) {

        if (chunk != null) {
            chunk.generateSkylightMap();
            ExtendedBlockStorage[] storage = chunk.getBlockStorageArray();
            for (int i = storage.length; i-- > 0; )
                if (storage[i] != null) {
                    //{ spigot compat: force data array to exist
                    NibbleArray a = storage[i].getSkyLight();
                    if (a != null) {
                        a.set(0, 0, 0, 0);
                        a.set(0, 0, 0, 15);
                        //}
                        Arrays.fill(a.getData(), (byte) 0);
                    }
                }
            chunk.resetRelightChecks();
            chunk.setModified(true);
            World world = chunk.getWorld();
            if (world instanceof WorldServer) {
                PlayerChunkMap chunkMap = ((WorldServer) world).getPlayerChunkMap();

                PlayerChunkMapEntry entry = chunkMap.getEntry(chunk.x, chunk.z);

                if (entry != null)
                    entry.sendPacket(new SPacketChunkData(chunk, -1));
            }
        }
    }
}
