package com.example.greisvini.ordem_paranormal.network;

import com.example.greisvini.ordem_paranormal.OrdemParanormal;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class OrdemMessages 
{
    
    public static SimpleChannel INSTANCE;
    private static int packet_id = 0;

    private static int id()
    {
        return packet_id++;
    }

    public static void register()
    {
        SimpleChannel net = NetworkRegistry.ChannelBuilder
        .named(new ResourceLocation(OrdemParanormal.MOD_ID, "messages"))
        .networkProtocolVersion(() -> "1.0")
        .clientAcceptedVersions(s -> true)
        .serverAcceptedVersions(s -> true)
        .simpleChannel();

        INSTANCE = net;
    }


    public static <MSG> void sendToServer(MSG message)
    {
        INSTANCE.sendToServer(message);
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player)
    {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player),message);
    }
}
