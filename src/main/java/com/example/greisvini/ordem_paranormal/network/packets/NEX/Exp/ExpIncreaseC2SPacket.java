package com.example.greisvini.ordem_paranormal.network.packets.NEX.Exp;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

import com.example.greisvini.ordem_paranormal.capabilities.NEX.NEXProvider;

// Mensagem para mudar valor de atributo in-game
public class ExpIncreaseC2SPacket 
{

    private int increase_val;

    public ExpIncreaseC2SPacket(int val)
    {
        this.increase_val = val;
    }

    
    public ExpIncreaseC2SPacket(FriendlyByteBuf buf)
    {
        this.increase_val = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf)
    {
        buf.writeInt(this.increase_val);
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

                // Incrementa o xp atual
                player.getCapability(NEXProvider.NEX).ifPresent(nex ->
                {
                    nex.increaseXp(this.increase_val);
                });;
            }

            else {context.setPacketHandled(false);}
        });

        return context.getPacketHandled();
    }

  
}
