package com.example.greisvini.ordem_paranormal.network.packets.UI;

import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

import com.example.greisvini.ordem_paranormal.client.UI.OrdemMainMenu;

// Mensagem para abrir menu principal
public class OpenOrdemMenuS2CPacket 
{
    public OpenOrdemMenuS2CPacket()
    {
    }

    public OpenOrdemMenuS2CPacket(FriendlyByteBuf buf)
    {
    }

    public void toBytes(FriendlyByteBuf buf)
    {
    }

    // Exeuta a operação no servidor
    public boolean handle(Supplier<NetworkEvent.Context> supplier)
    {
        NetworkEvent.Context context = supplier.get();

        context.enqueueWork(() -> 
        {
            // Tudo feito aqui está no servidor !!!
            Minecraft.getInstance().setScreen(new OrdemMainMenu());
        });

        // Confirma que o packet foi tratado
        context.setPacketHandled(true);
        return true;
    }
}
