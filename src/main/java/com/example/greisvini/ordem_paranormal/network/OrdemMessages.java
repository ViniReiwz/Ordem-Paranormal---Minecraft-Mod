package com.example.greisvini.ordem_paranormal.network;

import com.example.greisvini.ordem_paranormal.OrdemParanormal;

import net.minecraft.resources.ResourceLocation;
<<<<<<< Updated upstream
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
=======
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
>>>>>>> Stashed changes
    }

    public static void register()
    {
        SimpleChannel net = NetworkRegistry.ChannelBuilder
<<<<<<< Updated upstream
        .named(new ResourceLocation(OrdemParanormal.MOD_ID, "messages"))
=======
        .named(new ResourceLocation(OrdemParanormal.MOD_ID,"messages"))
>>>>>>> Stashed changes
        .networkProtocolVersion(() -> "1.0")
        .clientAcceptedVersions(s -> true)
        .serverAcceptedVersions(s -> true)
        .simpleChannel();

        INSTANCE = net;
    }

<<<<<<< Updated upstream

    public static <MSG> void sendToServer(MSG message)
    {
        INSTANCE.sendToServer(message);
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player)
    {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player),message);
=======
    public static <MSG> void sendToServer(MSG msg)
    {
        INSTANCE.sendToServer(msg);
    }

    public static <MSG> void sendToPlayer(MSG msg, ServerPlayer srv_player)
    {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> srv_player), msg);
>>>>>>> Stashed changes
    }
}
