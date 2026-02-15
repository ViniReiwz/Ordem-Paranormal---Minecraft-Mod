package com.example.greisvini.ordem_paranormal.network.packets.Atributos;

import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraftforge.network.NetworkEvent;
import net.minecraft.client.player.LocalPlayer;

import java.util.function.Supplier;

import com.example.greisvini.ordem_paranormal.capabilities.attributes.AtributosProvider;

// Mensagem para sincronziar os valores de UM atributo entre server e client
public class AtribSyncS2CPacket 
{
    // Valor (entre 0 e um)
    private final int final_val;
    private final String final_type;

 
    public AtribSyncS2CPacket(int final_val, String final_type)
    {
        this.final_val = final_val;
        this.final_type = final_type;
    }

    // Lê do buffer, quando chamado pelo server
    public AtribSyncS2CPacket(FriendlyByteBuf buf)
    {
        this.final_val = buf.readInt();
        this.final_type = buf.readUtf();
    }

    // Escreve no buffer, quando enviado pelo client
    public void toBytes(FriendlyByteBuf buf)
    {
        buf.writeInt(this.final_val);
        buf.writeUtf(this.final_type);
    }

    // Exeuta a operação no cliente
    public boolean handle(Supplier<NetworkEvent.Context> supplier)
    {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> 
        {
            if(context.getDirection().getReceptionSide().isClient())
            {
                // Tudo aqui está no cliente !!
                LocalPlayer p = Minecraft.getInstance().player;
                p.getCapability(AtributosProvider.ATRIBUTOS).ifPresent(attr ->
                {
                    attr.set(final_val, final_type);
                });

                p.sendSystemMessage(Component.literal(final_type + ": " + final_val));

                // Confirma que lidou com o pacote
                context.setPacketHandled(true);
            }
            else { context.setPacketHandled(false); }
            
        });
        
        return context.getPacketHandled();
    }
}
