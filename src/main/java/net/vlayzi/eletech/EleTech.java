package net.vlayzi.eletech;

import net.vlayzi.eletech.init.ModBlockEntities;
import net.vlayzi.eletech.init.ModBlocks;
import net.vlayzi.eletech.init.ModCreativeModeTabs;
import net.vlayzi.eletech.init.ModItems;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("eletech") // Мы написали ID текстом, чтобы убрать ошибку
public class EleTech {
    public static final String MODID = "eletech";

    public EleTech() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Блоки должны регистрироваться первыми!
        ModBlocks.register(modEventBus);
        ModItems.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        ModCreativeModeTabs.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }
}