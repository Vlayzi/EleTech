package net.vlayzi.eletech.items;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import vazkii.patchouli.api.PatchouliAPI;

public class ExplorerDiary extends Item {
    public ExplorerDiary(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (!level.isClientSide && player instanceof ServerPlayer serverPlayer) {
            // "eletech" - это ваш MODID, "engineer_diary" - имя папки в assets
            ResourceLocation bookId = new ResourceLocation("eletech", "engineer_diary");
            PatchouliAPI.get().openBookGUI(serverPlayer, bookId);
        }

        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
    }
}