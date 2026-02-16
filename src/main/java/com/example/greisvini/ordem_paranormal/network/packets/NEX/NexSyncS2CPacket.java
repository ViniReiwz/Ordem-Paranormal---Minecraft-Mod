package com.example.greisvini.ordem_paranormal.network.packets.NEX;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

import com.example.greisvini.ordem_paranormal.capabilities.NEX.NEXProvider;

// Mensagem para sincronizar valor de NEX entre server e client
public class NexSyncS2CPacket 
{

    private int final_nex;

    public NexSyncS2CPacket(int final_nex)
    {
        this.final_nex = final_nex;
    }

    
    public NexSyncS2CPacket(FriendlyByteBuf buf)
    {
        this.final_nex = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf)
    {
        buf.writeInt(this.final_nex);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier)
    {
        NetworkEvent.Context context = supplier.get();

        context.enqueueWork(() -> 
        {
            
            if(context.getDirection().getReceptionSide().isClient())
            {
                // Tudo feito aqui está no client !!!
                ServerPlayer player = context.getSender();
                if(player == null){return;}

                // Sincroniza o nex do server com o client
                player.getCapability(NEXProvider.NEX).ifPresent(nex ->
                {
                    nex.setNex(this.final_nex);
                });;
            }

            else {context.setPacketHandled(false);}
        });

        return context.getPacketHandled();
    }

  
}
