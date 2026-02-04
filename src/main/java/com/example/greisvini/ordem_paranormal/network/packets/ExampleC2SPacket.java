package com.example.greisvini.ordem_paranormal.network.packets;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
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

        });

        return true;
    }
}
