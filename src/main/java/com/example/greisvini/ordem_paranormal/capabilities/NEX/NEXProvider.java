package com.example.greisvini.ordem_paranormal.capabilities.NEX;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;

public class NEXProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> 
{
    // Utilizado para referenciar a capability no código todo
    public static Capability<NEX> NEX = CapabilityManager.get(new CapabilityToken<NEX>() { });

    private NEX nex = null;
    private final LazyOptional<NEX> optional = LazyOptional.of(this::createNEX);

    // Padrão singleton, utiliza apenas uma instância da classe NEX
    private NEX createNEX()
    {
        if(this.nex == null)
        {
            this.nex = new NEX();
        }

        return this.nex;
    }


    // Retorna a capability
    @Override
    public <T> @NotNull LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) 
    {
        if(cap == NEX)
        {
            return optional.cast();
        }
        
        return LazyOptional.empty();
    }

    // Carrega os dados
    @Override
    public void deserializeNBT(CompoundTag nbt) 
    {
        createNEX().loadNBTdata(nbt);
    }

    // Salva os dados
    @Override
    public CompoundTag serializeNBT() 
    {
        CompoundTag nbt = new CompoundTag();
        createNEX().saveNBTdata(nbt);
        return nbt;
    }
    
}
