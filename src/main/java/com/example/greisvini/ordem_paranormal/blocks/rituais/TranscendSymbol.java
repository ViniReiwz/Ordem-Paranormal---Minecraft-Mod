package com.example.greisvini.ordem_paranormal.blocks.rituais;

import com.example.greisvini.ordem_paranormal.blocks.utils.BigRitualBlock;
import com.example.greisvini.ordem_paranormal.capabilities.NEX.NEXProvider;
import com.example.greisvini.ordem_paranormal.network.OrdemMessages;
import com.example.greisvini.ordem_paranormal.network.packets.NEX.UpNexC2SPacket;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

/*
 * Vai ter algo aqui ainda, só esperar !
 */
public class TranscendSymbol extends BigRitualBlock
{

    // Método ativado ao clicar com botão direito
    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player,InteractionHand hand, BlockHitResult hit_res) 
    {

        if(level.isClientSide())
        {
            // verifica se o player está agachado
            if(player.isShiftKeyDown())
            {
                player.getCapability(NEXProvider.NEX).ifPresent(nex -> 
                {
                    if(nex.canLvlUp()){ OrdemMessages.sendToServer(new UpNexC2SPacket()); }
                });

                return InteractionResult.SUCCESS;
            }

            return InteractionResult.FAIL;
        }

        return InteractionResult.PASS;
    }
}
