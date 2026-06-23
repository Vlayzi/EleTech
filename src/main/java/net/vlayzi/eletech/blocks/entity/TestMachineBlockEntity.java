package net.vlayzi.eletech.blocks.entity;

import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.IEnergyStorage;
import net.vlayzi.eletech.util.CustomEnergyStorage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TestMachineBlockEntity extends BlockEntity {

    // 1. Создаем наше хранилище (например, 50 000 RF емкость, 1000 RF/тик передача)
    private final CustomEnergyStorage ENERGY_STORAGE = new CustomEnergyStorage(50000, 1000) {
        @Override
        public void onEnergyChanged() {
            setChanged(); // Говорим игре сохранить чанк! (Решает баг №2)
        }
    };

    // 2. Обертка для взаимодействия с проводами других модов
    private LazyOptional<IEnergyStorage> lazyEnergyHandler = LazyOptional.empty();

    public TestMachineBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    // 3. Выставляем энергию наружу (Решает баг №4)
    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ENERGY) {
            return lazyEnergyHandler.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyEnergyHandler = LazyOptional.of(() -> ENERGY_STORAGE);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyEnergyHandler.invalidate(); // Удаляем связь, если блок сломали
    }

    // 4. Сохранение энергии при выходе (Решает баг №1)
    @Override
    protected void saveAdditional(CompoundTag tag) {
        tag.put("energy", ENERGY_STORAGE.serializeNBT());
        super.saveAdditional(tag);
    }

    // 5. Загрузка энергии при входе
    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        ENERGY_STORAGE.deserializeNBT(tag.get("energy"));
    }

    // Отправляет данные блоку при его загрузке на клиенте (когда игрок подходит к нему)
    @Override
    public CompoundTag getUpdateTag() {
        return saveWithoutMetadata();
    }

    // Отправляет пакет обновлений, когда мы вызываем setChanged()
    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }
}