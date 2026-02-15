package com.example.greisvini.ordem_paranormal.network.packets.Atributos;

import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import net.minecraft.client.player.LocalPlayer;

import java.util.function.Supplier;

import com.example.greisvini.ordem_paranormal.capabilities.attributes.Atributos;
import com.example.greisvini.ordem_paranormal.capabilities.attributes.AtributosProvider;

// Mensagem para sincronziar os valores de TODOS os atributos entre server e client
public class AtribSyncAllS2CPacket 
{
    private int forca;      // FOR
    private int agilidade;  // AGI
    private int presenca;   // PRE
    private int intelecto;  // INT
    private int vigor;      // VIG

    public AtribSyncAllS2CPacket(Atributos attr)
    {
        this.forca = attr.get("FOR");
        this.agilidade = attr.get("AGI");
        this.presenca = attr.get("PRE");
        this.intelecto = attr.get("INT");
        this.vigor = attr.get("VIG");
    }

    // Lê do buffer, quando chamado pelo server
    public AtribSyncAllS2CPacket(FriendlyByteBuf buf)
    {
        this.forca = buf.readInt();
        this.agilidade = buf.readInt();
        this.presenca = buf.readInt();
        this.intelecto = buf.readInt();
        this.vigor = buf.readInt();
    }

    // Escreve no buffer, quando enviado pelo client
    public void toBytes(FriendlyByteBuf buf)
    {
        buf.writeInt(this.forca);
        buf.writeInt(this.agilidade);
        buf.writeInt(this.presenca);
        buf.writeInt(this.intelecto);
        buf.writeInt(this.vigor);
    }

    // Executa a operação no cliente
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
                    // Sincroniza todos os atributos no cliente
                    attr.set(this.forca, "FOR");
                    attr.set(this.agilidade, "AGI");
                    attr.set(this.presenca, "PRE");
                    attr.set(this.intelecto, "INT");
                    attr.set(this.vigor, "VIG");
                });

                // Confirma que lidou com o pacote
                context.setPacketHandled(true);
            }
            else { context.setPacketHandled(false); }
            
        });
        
        return context.getPacketHandled();
    }
}
