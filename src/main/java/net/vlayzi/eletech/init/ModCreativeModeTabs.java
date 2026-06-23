package net.vlayzi.eletech.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.vlayzi.eletech.EleTech;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, EleTech.MODID);

    public static final RegistryObject<CreativeModeTab> ELETECH_TAB = CREATIVE_MODE_TABS.register("eletech_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.STEEL_INGOT.get()))
                    .title(Component.translatable("creativetab.eletech_tab"))
                    .displayItems((parameters, output) -> {
                        // Добавляем наши предметы в список вкладки
                        output.accept(ModItems.STEEL_INGOT.get());
                        output.accept(ModItems.OBSIDIAN_INGOT.get());
                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }

    public static final RegistryObject<CreativeModeTab> ELETECH_EQUIPMENT_TAB = CREATIVE_MODE_TABS.register("eletech_equipment_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.STEEL_CHESTPLATE.get())) // Иконка вкладки
                    .title(Component.translatable("creativetab.eletech_equipment"))
                    .displayItems((parameters, output) -> {
                        // Сюда добавляем всё боевое
                        output.accept(ModItems.STEEL_HELMET.get());
                        output.accept(ModItems.STEEL_CHESTPLATE.get());
                        output.accept(ModItems.STEEL_LEGGINGS.get());
                        output.accept(ModItems.STEEL_BOOTS.get());
                    })
                    .build());

    public static final RegistryObject<CreativeModeTab> ELETECH_TOOLS_TAB = CREATIVE_MODE_TABS.register("eletech_tools",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.EXPLORER_DIARY.get())) // Иконка вкладки — твоя книга
                    .title(Component.translatable("creativetab.eletech_tools")) // Название из файла локализации
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.EXPLORER_DIARY.get()); // Добавляем книгу во вкладку
                    })
                    .build());
}