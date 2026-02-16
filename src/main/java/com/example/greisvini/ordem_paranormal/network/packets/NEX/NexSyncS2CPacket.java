package com.example.greisvini.ordem_paranormal.network.packets.NEX;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

import com.example.greisvini.ordem_paranormal.capabilities.NEX.NEXProvider;

// Mensagem para mudar valor de atributo in-game
public class NexSyncS2CPacket 
{
    public NexSyncS2CPacket()
    {
    }

    
    public NexSyncS2CPacket(FriendlyByteBuf buf)
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
            
            if(context.getDirection().getReceptionSide().isClient())
            {
                // Tudo feito aqui está no servidor !!!
                ServerPlayer player = context.getSender();
                if(player == null){return;}

                // Incremente em 'value' o atributo do tipo 'type'
                player.getCapability(NEXProvider.NEX).ifPresent(nex ->
                {
                    nex.upNex();
                });;
            }

            else {context.setPacketHandled(false);}
        });

        return context.getPacketHandled();
    }

  
}
