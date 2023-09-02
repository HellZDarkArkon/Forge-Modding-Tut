package com.github.hellzdarkarkon.src.tutorialmod;

import com.github.hellzdarkarkon.src.tutorialmod.block.ModBlocks;
import com.github.hellzdarkarkon.src.tutorialmod.item.ModCreativeModeTabs;
import com.github.hellzdarkarkon.src.tutorialmod.item.ModItems;
import com.mojang.logging.LogUtils;
import net.minecraft.world.item.CreativeModeTabs;
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

@Mod(TutorialMod.MODID)
public class TutorialMod {

    public static final String MODID = "tutorialmod";
    public static final Logger LOGGER = LogUtils.getLogger();

    public TutorialMod() {
        IEventBus modEB = FMLJavaModLoadingContext.get().getModEventBus();

        ModCreativeModeTabs.register(modEB);
        ModItems.register(modEB);
        ModBlocks.register(modEB);

        modEB.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);
        modEB.addListener(this::addCreative);

    }

    private void commonSetup(final FMLCommonSetupEvent ev) {


    }

    private void addCreative(BuildCreativeModeTabContentsEvent ev) {
        if(ev.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            ev.accept(ModItems.SAPPHIRE);
            ev.accept(ModItems.RAW_SAPPHIRE);
        }
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