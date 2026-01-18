package com.example.greisvini.ordem_paranormal.items.custom;


import com.example.greisvini.ordem_paranormal.blocks.OrdemBlocos;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;

/*
 * Item do giz branco, utilizado para desenhar o símbolo de transcendência no chão
 * o efeito ativa apenas quando o player usa o item em um bloco de madeira ensanguentada
 * rodeado por 7 blocos do mesmo, formando um grid 3x3 e também não pode haver blocos acima de qualquer um destes
 */
public class GizItem extends Item
{
    public GizItem(Properties pProperties)
    {
        super(pProperties);
    }


    // Método sobrescrito que indica o efeito a realizar quando utilizado
    @Override
    public InteractionResult useOn(UseOnContext uoContext) 
    {
        // Pega o level atual ('instância' do mundo, pelo que entendi)
        Level curr_level = uoContext.getLevel();

        // Atua apenas no lado do servidor
        if(!curr_level.isClientSide())
        {
            Player player = uoContext.getPlayer();
            // verifica se o ambiente é valido para escrever o símbolo
            if(isValidTranscendPlace(curr_level, uoContext.getClickedPos()))
            {   
                // Mensagem placeholder para sucesso
                player.sendSystemMessage(Component.literal("Local apto à transcendência :) !!"));

                // Danifica o item e iforma o lado do cliente
                player.getItemInHand(InteractionHand.MAIN_HAND).hurtAndBreak(1, player, p -> p.broadcastBreakEvent(InteractionHand.MAIN_HAND));
            }

            // Mensagem placeholder para erro
            else{player.sendSystemMessage(Component.literal("Local inapto à transcendência :( !!"));}
        }

        return InteractionResult.SUCCESS;
    }

    /*
     * Verifica se o local clicado pelo usuário é apto a receber o ritual de transcender
     * Level curr_level => Level atual do context
     * BlockPos middle_pos => Posição em que o usuário clicou
     * 
     * return boolean => true se está valido, false caso o contrário
     */
    private static boolean isValidTranscendPlace(Level curr_level ,BlockPos middle_pos)
    {
        boolean is_valid_for_draw = true;

        // Percorre o grid 3x3 a partir da posição do bloco central
        for(int i = -1; i < 2; i++)
        {
            for(int j = -1; j < 2; j++)
            {   
                // Percorre todo o grid 3x3 com base na posição central
                BlockPos curr_pos = new BlockPos(new Vec3i(middle_pos.getX() + j, middle_pos.getY(), middle_pos.getZ() + i));

                // Bloco atual
                BlockState curr_state = curr_level.getBlockState(curr_pos);

                // Pega o bloco imediatamente acima do bloco atual
                BlockState above_state = curr_level.getBlockState(curr_pos.above());


                // Transforma em falso caso não seja madeira ou esteja obstrupido acima
                if(!curr_state.is(OrdemBlocos.madeira_ensaguentada.get()) || !above_state.isAir())
                {is_valid_for_draw = false; break;}
            }

            // Continua percorrendo o loop apenas se for verdadeiro
            if(!is_valid_for_draw){break;}
        }

        return is_valid_for_draw;   
    }
    
}
