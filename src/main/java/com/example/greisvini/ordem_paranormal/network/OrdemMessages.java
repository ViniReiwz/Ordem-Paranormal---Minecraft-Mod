package com.example.greisvini.ordem_paranormal.network;

import com.example.greisvini.ordem_paranormal.OrdemParanormal;
import com.example.greisvini.ordem_paranormal.network.packets.Atributos.AtribSyncS2CPacket;
import com.example.greisvini.ordem_paranormal.network.packets.Atributos.ChangeAtribC2SPacket;
import com.example.greisvini.ordem_paranormal.network.packets.NEX.NexSyncS2CPacket;
import com.example.greisvini.ordem_paranormal.network.packets.NEX.UpNexC2SPacket;
import com.example.greisvini.ordem_paranormal.network.packets.NEX.Exp.ExpIncreaseC2SPacket;
import com.example.greisvini.ordem_paranormal.network.packets.Atributos.AtribSyncAllS2CPacket;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;
import net.minecraft.server.level.ServerPlayer;

public class OrdemMessages 
{
    private static SimpleChannel INSTANCE;

    private static int package_id = 0;
    
    private static int id()
    {
        return package_id++;
    }

    public static void register()
    {
        SimpleChannel net = NetworkRegistry.ChannelBuilder
        .named(new ResourceLocation(OrdemParanormal.MOD_ID,"messages"))
        .networkProtocolVersion(() -> "1.0")
        .clientAcceptedVersions(s -> true)
        .serverAcceptedVersions(s -> true)
        .simpleChannel();

        INSTANCE = net;

        // Mensagem para mudar valor de atributo
        net.messageBuilder(ChangeAtribC2SPacket.class, id(),NetworkDirection.PLAY_TO_SERVER)
        .decoder(ChangeAtribC2SPacket::new)
        .encoder(ChangeAtribC2SPacket::toBytes)
        .consumerMainThread(ChangeAtribC2SPacket::handle)
        .add();

        // Sincroniza UM atributo entre server e client
        net.messageBuilder(AtribSyncS2CPacket.class, id(),NetworkDirection.PLAY_TO_CLIENT)
        .decoder(AtribSyncS2CPacket::new)
        .encoder(AtribSyncS2CPacket::toBytes)
        .consumerMainThread(AtribSyncS2CPacket::handle)
        .add();

        // Sincroniza TODOS os atributos entre server e client
        net.messageBuilder(AtribSyncAllS2CPacket.class, id(),NetworkDirection.PLAY_TO_CLIENT)
        .decoder(AtribSyncAllS2CPacket::new)
        .encoder(AtribSyncAllS2CPacket::toBytes)
        .consumerMainThread(AtribSyncAllS2CPacket::handle)
        .add();

        // Sincroniza TODOS os atributos entre server e client
        net.messageBuilder(UpNexC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
        .decoder(UpNexC2SPacket::new)
        .encoder(UpNexC2SPacket::toBytes)
        .consumerMainThread(UpNexC2SPacket::handle)
        .add();

        // Sincroniza o NEX entre server e client
        net.messageBuilder(NexSyncS2CPacket.class, id(),NetworkDirection.PLAY_TO_CLIENT)
        .decoder(NexSyncS2CPacket::new)
        .encoder(NexSyncS2CPacket::toBytes)
        .consumerMainThread(NexSyncS2CPacket::handle)
        .add();

        // Incrementa na experiência do player
        net.messageBuilder(ExpIncreaseC2SPacket.class, id(),NetworkDirection.PLAY_TO_SERVER)
        .decoder(ExpIncreaseC2SPacket::new)
        .encoder(ExpIncreaseC2SPacket::toBytes)
        .consumerMainThread(ExpIncreaseC2SPacket::handle)
        .add();
    }

    public static <MSG> void sendToServer(MSG msg)
    {
        INSTANCE.sendToServer(msg);
    }

    public static <MSG> void sendToPlayer(MSG msg, ServerPlayer srv_player)
    {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> srv_player), msg);
    }
}
