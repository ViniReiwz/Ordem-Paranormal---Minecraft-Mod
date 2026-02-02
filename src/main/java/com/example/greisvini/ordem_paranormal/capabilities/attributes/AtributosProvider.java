package com.example.greisvini.ordem_paranormal.capabilities.attributes;

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

public class AtributosProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> 
{
    // Utilizado para referenciar a capability no código todo
    public static Capability<Atributos> ATRIBUTOS = CapabilityManager.get(new CapabilityToken<Atributos>() { });

    private Atributos atributos = null;
    private final LazyOptional<Atributos> optional = LazyOptional.of(this::createAtributos);

    // Padrão singleton, utiliza apenas uma instância da calsse Atributos
    private Atributos createAtributos()
    {
        if(this.atributos == null)
        {
            this.atributos = new Atributos();
        }

        return this.atributos;
    }


    // Retorna a capability
    @Override
    public <T> @NotNull LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) 
    {
        if(cap == ATRIBUTOS)
        {
            return optional.cast();
        }
        
        return LazyOptional.empty();
    }

    // Carrega os dados
    @Override
    public void deserializeNBT(CompoundTag nbt) 
    {
        createAtributos().loadNBTdata(nbt);
    }

    // Salva os dados
    @Override
    public CompoundTag serializeNBT() 
    {
        CompoundTag nbt = new CompoundTag();
        createAtributos().saveNBTdata(nbt);
        return nbt;
    }
    
}
