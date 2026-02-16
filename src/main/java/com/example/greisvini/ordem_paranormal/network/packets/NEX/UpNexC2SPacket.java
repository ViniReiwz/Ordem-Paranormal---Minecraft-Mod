package com.example.greisvini.ordem_paranormal.network.packets.NEX;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

import com.example.greisvini.ordem_paranormal.capabilities.NEX.NEXProvider;
import com.example.greisvini.ordem_paranormal.network.OrdemMessages;

// Mensagem para aumentar no NEX do player
public class UpNexC2SPacket 
{
    public UpNexC2SPacket()
    {
    }

    
    public UpNexC2SPacket(FriendlyByteBuf buf)
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
            
            if(context.getDirection().getReceptionSide().isServer())
            {
                // Tudo feito aqui está no servidor !!!
                ServerPlayer player = context.getSender();
                if(player == null){return;}

                // Incrementa o valor do nex
                player.getCapability(NEXProvider.NEX).ifPresent(nex ->
                {
                    nex.upNex();
                    OrdemMessages.sendToPlayer(new NexSyncS2CPacket(nex.getNex()), player);
                });;
            }

            else {context.setPacketHandled(false);}
        });

        return context.getPacketHandled();
    }

  
}
