package net.vlayzi.eletech.util;

import net.minecraftforge.energy.EnergyStorage;

public abstract class CustomEnergyStorage extends EnergyStorage {

    public CustomEnergyStorage(int capacity, int maxTransfer) {
        super(capacity, maxTransfer, maxTransfer, 0);
    }

    // Переопределяем метод получения энергии
    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        int received = super.receiveEnergy(maxReceive, simulate);
        if (received > 0 && !simulate) {
            onEnergyChanged(); // Уведомляем блок, если энергия реально пришла
        }
        return received;
    }

    // Переопределяем метод отдачи энергии
    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        int extracted = super.extractEnergy(maxExtract, simulate);
        if (extracted > 0 && !simulate) {
            onEnergyChanged(); // Уведомляем блок, если энергия реально ушла
        }
        return extracted;
    }

    public void setEnergy(int energy) {
        // Ограничиваем энергию: не меньше 0 и не больше максимальной вместимости
        this.energy = Math.max(0, Math.min(energy, getMaxEnergyStored()));
        onEnergyChanged();
    }

    // Этот метод мы заставим работать внутри самого блока
    public abstract void onEnergyChanged();
}