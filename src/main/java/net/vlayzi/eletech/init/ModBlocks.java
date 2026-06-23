package net.vlayzi.eletech.init;

import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.vlayzi.eletech.EleTech;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, EleTech.MODID);

    // Сюда мы скоро добавим наш первый механизм

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}