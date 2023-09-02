package com.github.hellzdarkarkon.src.forgemodtut;

import com.mojang.logging.LogUtils;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(ForgeModTut.MODID)
public class ForgeModTut {

    public static final String MODID = "tutorialmod";
    public static final Logger LOGGER = LogUtils.getLogger();

    public ForgeModTut() {
        IEventBus modEB = FMLJavaModLoadingContext.get().getModEventBus();

        modEB.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);
        modEB.addListener(this::addCreative);

    }

    private void commonSetup(final FMLCommonSetupEvent ev) {


    }

    private void addCreative(BuildCreativeModeTabContentsEvent ev) {

    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent ev) {

    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent ev) {

        }
    }

}