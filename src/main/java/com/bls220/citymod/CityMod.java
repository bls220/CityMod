package com.bls220.citymod;

import com.bls220.citymod.block.ModBlocks;
import com.bls220.citymod.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = CityMod.MOD_ID, version = CityMod.VERSION, name = CityMod.NAME)
public class CityMod
{
    public static final String MOD_ID = "citymod";
    public static final String VERSION = "1.0";
    public static final String NAME = "City Mod";

    public static final Logger log = LogManager.getLogger(CityMod.MOD_ID);

    @Mod.Instance(MOD_ID)
    public static CityMod instance;

    @SidedProxy(serverSide = "com.bls220.citymod.proxy.CommonProxy", clientSide = "com.bls220.citymod.proxy.ClientProxy")
    public static CommonProxy proxy;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        ModBlocks.init();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
    }
}
