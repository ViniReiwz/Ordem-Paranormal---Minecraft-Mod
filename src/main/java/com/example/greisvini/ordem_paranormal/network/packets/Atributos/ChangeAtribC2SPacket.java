package com.example.greisvini.ordem_paranormal.network.packets.Atributos;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

import com.example.greisvini.ordem_paranormal.capabilities.attributes.Atributos;
import com.example.greisvini.ordem_paranormal.capabilities.attributes.AtributosProvider;
import com.example.greisvini.ordem_paranormal.network.OrdemMessages;

// Mensagem para mudar valor de atributo in-game
public class ChangeAtribC2SPacket 
{
    // Valor (entre 0 e um)
    private int value;
    private String type;


    public ChangeAtribC2SPacket(int val, String type)
    {
        this.value = val;
        this.type = type;
    }

    // Lê do buffer, quando chamado pelo server
    public ChangeAtribC2SPacket(FriendlyByteBuf buf)
    {
        this.value = buf.readInt();
        this.type = buf.readUtf();
    }

    // Escreve no buffer, quando enviado pelo client
    public void toBytes(FriendlyByteBuf buf)
    {
        buf.writeInt(this.value);
        buf.writeUtf(this.type);
    }

    // Exeuta a operação no servidor
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

                // Incremente em 'value' o atributo do tipo 'type'
                player.getCapability(AtributosProvider.ATRIBUTOS).ifPresent(attr ->
                {
                    int res = attr.get(type) + this.value;
                    if(res >= 0)
                    {
                        attr.set(res,this.type);
                    }
                    else
                    {
                        attr.set(0,type);
                        res = 0;
                    }

                    applyModifiers(player, attr);

                    OrdemMessages.sendToPlayer(new AtribSyncS2CPacket(res, type), player);

                    // Confirma que o packet foi tratado
                    context.setPacketHandled(true);
                });;
            }

            else {context.setPacketHandled(false);}
        });

        return context.getPacketHandled();
    }

    // Aplica todas as modificações permanentes relacionadas à todos os atributos
    public static void applyModifiers(ServerPlayer player, Atributos attr)
    {
        // Aumenta a velocidade de movimento
        if(player.getAttribute(Attributes.MOVEMENT_SPEED) != null)
        {
            double bonus = attr.get("AGI") * 0.1; // 10% de aumento de velocidade por ponto de agilidade

            player.getAttribute(Attributes.MOVEMENT_SPEED).removePermanentModifier(Atributos.AGI_UUID);

            player.getAttribute(Attributes.MOVEMENT_SPEED).addPermanentModifier(new AttributeModifier(Atributos.AGI_UUID,"mov_speed_bonus", bonus, AttributeModifier.Operation.MULTIPLY_BASE));
        }

        // Aumenta a velocidade de ataque
        if(player.getAttribute(Attributes.ATTACK_SPEED) != null)
        {
            double bonus = attr.get("AGI") * 0.05; // 5% de aumento de velocidade por ponto de agilidade

            player.getAttribute(Attributes.ATTACK_SPEED).removePermanentModifier(Atributos.AGI_UUID);

            player.getAttribute(Attributes.ATTACK_SPEED).addPermanentModifier(new AttributeModifier(Atributos.AGI_UUID,"attack_speed_bonus", bonus, AttributeModifier.Operation.MULTIPLY_BASE));
        }

        // Aumenta o dano base
        if(player.getAttribute(Attributes.ATTACK_DAMAGE) != null)
        {
            double bonus = attr.get("FOR") * 0.1; // 10% de aumento de dano por FOR

            player.getAttribute(Attributes.ATTACK_DAMAGE).removePermanentModifier(Atributos.FOR_UUID);

            player.getAttribute(Attributes.ATTACK_DAMAGE).addPermanentModifier(new AttributeModifier(Atributos.FOR_UUID,"attack_damagebonus", bonus, AttributeModifier.Operation.MULTIPLY_BASE));
        }
    }
}
