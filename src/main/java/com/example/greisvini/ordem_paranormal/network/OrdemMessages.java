package com.example.greisvini.ordem_paranormal.network;

import com.example.greisvini.ordem_paranormal.OrdemParanormal;
import com.example.greisvini.ordem_paranormal.network.packets.AtribSyncS2CPacket;
import com.example.greisvini.ordem_paranormal.network.packets.ChangeAtribC2SPacket;

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

        // Sincroniza os atributos entre server e client
        net.messageBuilder(AtribSyncS2CPacket.class, id(),NetworkDirection.PLAY_TO_CLIENT)
        .decoder(AtribSyncS2CPacket::new)
        .encoder(AtribSyncS2CPacket::toBytes)
        .consumerMainThread(AtribSyncS2CPacket::handle)
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
