package com.example.greisvini.ordem_paranormal.events;

import com.example.greisvini.ordem_paranormal.OrdemParanormal;
import com.example.greisvini.ordem_paranormal.capabilities.attributes.Atributos;
import com.example.greisvini.ordem_paranormal.capabilities.attributes.AtributosProvider;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = OrdemParanormal.MOD_ID)
public class OrdemEvents 
{

    // Liga os atributos à um jogador
    @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event)
    {
        if(event.getObject() instanceof Player)
        {
            if(!event.getObject().getCapability(AtributosProvider.ATRIBUTOS).isPresent())
            {
                event.addCapability(new ResourceLocation(OrdemParanormal.MOD_ID,"atributos"), new AtributosProvider());
            }
        }
    }

    // Salva os dados quando o player morre
    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event)
    {
        if(event.isWasDeath())
        {
            event.getOriginal().getCapability(AtributosProvider.ATRIBUTOS).ifPresent(old -> {
                event.getEntity().getCapability(AtributosProvider.ATRIBUTOS).ifPresent(news -> {
                    news.copyFrom(old);
                });
            });
        }
    }


    // Registra a capability no jogo
    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event)
    {
        event.register(Atributos.class);
    }
}
