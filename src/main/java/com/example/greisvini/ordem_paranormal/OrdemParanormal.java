package com.example.greisvini.ordem_paranormal;

import com.example.greisvini.ordem_paranormal.items.OrdemItems;

import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
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

        OrdemItems.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {

    }

    // Adiciona o item à uma tabela de items no modo criativo (Tabelas padrão do mine)
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
        // Adiciona o SANGUE na tabela de ingredientes
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS)
        {
            event.accept(OrdemItems.SANGUE);
        }
    }
}
