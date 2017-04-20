package com.bls220.citymod.utils;

import com.bls220.citymod.CityMod;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * Class to handle the import/export and placement of structures (buildings)
 */
public class Structure {

    private Template template;

    public Structure(String structureName){
        this.loadStructure(structureName);
    }

    private void loadStructure(String name){
        InputStream inputStream = MinecraftServer.class.getResourceAsStream(String.format("/assets/%s/schematics/%s.nbt", CityMod.MOD_ID, name));

        final NBTTagCompound nbttagcompound;
        try {
            nbttagcompound = CompressedStreamTools.readCompressed(inputStream);
        }
        catch (final IOException e) {
            CityMod.log.warn(String.format("Failed to load template %s", name), e);
            return;
        }
        finally {
            IOUtils.closeQuietly(inputStream);
        }

        template = new Template();
        template.read(nbttagcompound);
    }

    public void placeStructure(World worldIn, BlockPos pos) {
        // TODO: allow partial placing, so we can "animate" the build
        StructureBoundingBox bb = new StructureBoundingBox(pos, pos.add(template.getSize()));
        template.addBlocksToWorld(worldIn, pos, new PlacementSettings().setBoundingBox(bb));
    }
}
