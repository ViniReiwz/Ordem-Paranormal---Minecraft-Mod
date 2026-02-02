package com.example.greisvini.ordem_paranormal;

import com.example.greisvini.ordem_paranormal.blocks.OrdemBlocos;
import com.example.greisvini.ordem_paranormal.items.OrdemItemTab;
import com.example.greisvini.ordem_paranormal.items.OrdemItems;
import com.example.greisvini.ordem_paranormal.network.OrdemMessages;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("ordem_paranormal")
public class OrdemParanormal 
{
    public static final String MOD_ID = "ordem_paranormal";
    public OrdemParanormal()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Inicializa a tabela do modo criativo
        OrdemItemTab.register(modEventBus);

        // Inicializa os items do mod
        OrdemItems.register(modEventBus);

        // Inicializa os blocos do mod
        OrdemBlocos.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        OrdemMessages.register();
    }
}
