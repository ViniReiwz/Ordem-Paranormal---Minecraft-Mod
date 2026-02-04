package com.example.greisvini.ordem_paranormal.network.packets;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

import com.example.greisvini.ordem_paranormal.capabilities.attributes.AtributosProvider;

public class ExampleC2SPacket 
{
    public ExampleC2SPacket()
    {

    }

    public ExampleC2SPacket(FriendlyByteBuf buf)
    {

    }

    public void toBytes(FriendlyByteBuf buf)
    {

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier)
    {
        NetworkEvent.Context context = supplier.get();

        context.enqueueWork(() -> 
        {
            // Tudo feito aqui está no servidor !!!
            ServerPlayer player = context.getSender();

            player.getCapability(AtributosProvider.ATRIBUTOS).ifPresent(attr ->
            {
                attr.setFOR(attr.getFOR() + 1);
                player.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(attr.getFOR());
                player.sendSystemMessage(Component.literal("DANO BASE: " + player.getAttributeBaseValue(Attributes.ATTACK_DAMAGE)));

                player.sendSystemMessage(Component.literal("FOR: " + attr.getFOR()));
            
            });;

        });

        return true;
    }
}
