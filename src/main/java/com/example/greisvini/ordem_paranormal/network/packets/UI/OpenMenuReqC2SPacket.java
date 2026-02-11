package com.example.greisvini.ordem_paranormal.network.packets.UI;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import net.minecraft.server.level.ServerPlayer;

import java.util.function.Supplier;
import com.example.greisvini.ordem_paranormal.network.OrdemMessages;

// Mensagem para abrir menu principal
public class OpenMenuReqC2SPacket 
{
    public OpenMenuReqC2SPacket()
    {
    }

    public OpenMenuReqC2SPacket(FriendlyByteBuf buf)
    {
    }

    public void toBytes(FriendlyByteBuf buf)
    {
    }

    // Exeuta a operação no servidor
    public boolean handle(Supplier<NetworkEvent.Context> supplier)
    {
        NetworkEvent.Context context = supplier.get();
        ServerPlayer player = context.getSender();
        if(player == null){return false;}
        context.enqueueWork(() -> 
        {
            OrdemMessages.sendToPlayer(new OpenOrdemMenuS2CPacket(), player);
        });

        // Confirma que o packet foi tratado
        context.setPacketHandled(true);
        return true;
    }
}
