package com.example.greisvini.ordem_paranormal.events;

import com.example.greisvini.ordem_paranormal.OrdemParanormal;
import com.example.greisvini.ordem_paranormal.capabilities.attributes.AtributosProvider;
import com.example.greisvini.ordem_paranormal.network.OrdemMessages;
import com.example.greisvini.ordem_paranormal.network.packets.ChangeAtribC2SPacket;
import com.example.greisvini.ordem_paranormal.utils.OrdemKeyBindings;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
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
                // Envia a mensagem para aumentar um atributo (Placeholder para testar o sistema)
                OrdemMessages.sendToServer(new ChangeAtribC2SPacket(1,"FOR"));
            }
        }

        // Quando o player está quebrando algo, chama ete evento
        @SubscribeEvent
        public static void onBreakBlock(PlayerEvent.BreakSpeed event)
        {
            Player player = event.getEntity();
            
            // Incrementa a velocidade de quebra de blocos em 10% por ponto de força
            player.getCapability(AtributosProvider.ATRIBUTOS).ifPresent(attr -> 
            {   
                float for_bonus = 1.0f + (attr.get("FOR") * 0.1f);
                event.setNewSpeed(event.getOriginalSpeed() * for_bonus);
            });
        }
    }
    
    // Registra a keybind
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
