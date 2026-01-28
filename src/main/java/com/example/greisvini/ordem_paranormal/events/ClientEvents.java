package com.example.greisvini.ordem_paranormal.events;

import com.example.greisvini.ordem_paranormal.OrdemParanormal;
import com.example.greisvini.ordem_paranormal.utils.OrdemKeyBindings;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/*
 * Classe dedicada aos eventos no lado do cliente, registrar e apertar teclas, etc.
 */
public class ClientEvents 
{
    @Mod.EventBusSubscriber(modid = OrdemParanormal.MOD_ID,value = Dist.CLIENT)
    public static class ClientForgeEvents
    {
        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key input_event)
        {
            if(OrdemKeyBindings.CAST_KEY.consumeClick())
            {
                Minecraft.getInstance().player.sendSystemMessage(Component.literal("placeholder de conjuração !!!"));
            }
        }
    }
    
    @Mod.EventBusSubscriber(modid = OrdemParanormal.MOD_ID,value = Dist.CLIENT,bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientOrdemBusEvent
    {
        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent reg_event)
        {
            reg_event.register(OrdemKeyBindings.CAST_KEY);
        }
    }

}
