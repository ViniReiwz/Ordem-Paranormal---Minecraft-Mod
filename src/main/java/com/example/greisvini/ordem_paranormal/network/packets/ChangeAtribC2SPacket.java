package com.example.greisvini.ordem_paranormal.network.packets;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

import com.example.greisvini.ordem_paranormal.capabilities.attributes.AtributosProvider;

// Mensagem para mudar valor de atributo in-game
public class ChangeAtribC2SPacket 
{
    // Valor (entre 0 e um)
    private int value;
    private String type;


    public ChangeAtribC2SPacket(int val, String type)
    {
        // Normaliza o valor entre 1 e -1, ou 0 (impossível ser, mas vale)
        if(val != 0){val = val/Math.abs(val);}

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

    // Exeuta a operação
    public boolean handle(Supplier<NetworkEvent.Context> supplier)
    {
        NetworkEvent.Context context = supplier.get();

        context.enqueueWork(() -> 
        {
            // Tudo feito aqui está no servidor !!!
            ServerPlayer player = context.getSender();
            if(player == null){return;}

            // Incremente em value o atributo passado em type

            player.getCapability(AtributosProvider.ATRIBUTOS).ifPresent(attr ->
            {

                int final_value = 0;
                switch (this.type) 
                {
                    case "FOR":
                    {   
                        int res = attr.getFOR() + this.value;

                        if(res >= 0){attr.setFOR(res); final_value = res;}
                        else{attr.setFOR(0);}

                        player.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(attr.getFOR());

                        break;
                    }

                    case "AGI":
                    {
                        int res = attr.getAGI() + this.value;

                        if(res >= 0){attr.setAGI(res); final_value = res;}
                        else{attr.setAGI(0);}

                        break;
                    }

                    case "PRE":
                    {
                        int res = attr.getPRE() + this.value;

                        if(res >= 0){attr.setPRE(res); final_value = res;}
                        else{attr.setPRE(0);}

                        break;
                    }

                    case "INT":
                    {
                        int res = attr.getINT() + this.value;

                        if(res >= 0){attr.setINT(res); final_value = res;}
                        else{attr.setINT(0);}

                        break;
                    }

                    case "VIG":
                    {
                        int res = attr.getVIG() + this.value;

                        if(res >= 0){attr.setVIG(res); final_value = res;}
                        else{attr.setVIG(0);}

                        break;
                    }

                    default:
                        break;
                }

                player.sendSystemMessage(Component.literal(this.type + ": " + final_value));

            });;

        });

        return true;
    }
}
