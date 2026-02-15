package com.example.greisvini.ordem_paranormal.items.ritualisticos;

import com.example.greisvini.ordem_paranormal.blocks.OrdemBlocos;
import com.example.greisvini.ordem_paranormal.blocks.utils.BigRitualBlock;
import com.example.greisvini.ordem_paranormal.utils.OrdemTags;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LevelEvent;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.core.Direction;

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
            BlockPos middle_pos = uoContext.getClickedPos();
            Player player = uoContext.getPlayer();  

            if(curr_level.getBlockState(middle_pos).is(OrdemTags.Blocks.NON_CHALK_WRITEABLE))
            {
                player.sendSystemMessage(Component.translatable("cannot.write.symbol"));
                return InteractionResult.FAIL;
            }

            // verifica se o ambiente é valido para escrever o símbolo
            else if(isValidTranscendPlace(curr_level, middle_pos))
            {
                // Danifica o item e informa o lado do cliente
                player.getItemInHand(InteractionHand.MAIN_HAND).hurtAndBreak(1, player, p -> p.broadcastBreakEvent(InteractionHand.MAIN_HAND));

                drawTranscendSymbol(curr_level,uoContext.getClickedPos(), player.getDirection());
                return InteractionResult.CONSUME;
            }

            // Mensagem placeholder para erro
            else
            {
                player.sendSystemMessage(Component.translatable("cannot.write.symbol"));
                return InteractionResult.FAIL;
            }
        }

        return InteractionResult.sidedSuccess(curr_level.isClientSide());
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

        BlockState base = curr_level.getBlockState(middle_pos);

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
                if(!curr_state.is(base.getBlock()) || !above_state.isAir())
                {is_valid_for_draw = false; break;}
            }

            // Continua percorrendo o loop apenas se for verdadeiro
            if(!is_valid_for_draw){break;}
        }
    
        return is_valid_for_draw;   
    }

    /*
     * Desenha o símbolo de transcender num grid 3x3
     * Level curr_level => Instância do mundo
     * BlockPos middle_pos => Posição do centro do grid
     * Direction player_dir => Direção em que o player está olhando
     * 
     * return: void
     */
    private static void drawTranscendSymbol(Level curr_level, BlockPos middle_pos, Direction player_dir)
    {
        // Desenha o ritual de transcender no chão
        
        // Bloco base onde o ritual esta sendo desenhado
        BlockState base = curr_level.getBlockState(middle_pos);

        // Percorre todas as colunas do grid 3x3
        for(int i = 0; i < 3; i ++)
        {
            BlockPos curr_pos = new BlockPos(new Vec3i(0, 0, 0));

            // Seleciona a coluna do grid
            switch (i) 
            {
                // Coluna esquerda, realtiva à direção em que o player olha
                case 0:
                {
                    curr_pos = middle_pos.relative(player_dir.getClockWise().getOpposite());
                    break;
                }
                
                // Coluna do centro
                case 1:
                {
                    curr_pos = middle_pos;
                    break;
                }
                
                // Coluna direita, relativa à direção em que o player olha
                case 2:
                {
                    curr_pos = middle_pos.relative(player_dir.getClockWise());
                    break;
                }
                
                default:
                    break;
            }

            // Pega o blockstate
            BlockState transcend_symbol = OrdemBlocos.TRANSCEND_SYMBOL.get().defaultBlockState();

            // Seta 'FACING_DIR' para a mesma direção em que o player olha
            transcend_symbol = transcend_symbol.setValue(BigRitualBlock.FACING_DIR, player_dir);

            BlockPos up = curr_pos.relative(player_dir);
            BlockPos down = curr_pos.relative(player_dir.getOpposite());

            // Dá um trigger no evento de partículas para efeito de 'desenhar/modificar' o bloco base
            curr_level.levelEvent(LevelEvent.PARTICLES_DESTROY_BLOCK,up,Block.getId(base));
            curr_level.levelEvent(LevelEvent.PARTICLES_DESTROY_BLOCK,curr_pos,Block.getId(base));
            curr_level.levelEvent(LevelEvent.PARTICLES_DESTROY_BLOCK,down,Block.getId(base));

            // Coloca o bloco do ritual efetivamente acima da posição correta, na orientação correta
            curr_level.setBlock(up.above(), transcend_symbol.setValue(BigRitualBlock.GRID_PARTS, i), 3);
            curr_level.setBlock(curr_pos.above(), transcend_symbol.setValue(BigRitualBlock.GRID_PARTS, 3 + i), 3);
            curr_level.setBlock(down.above(), transcend_symbol.setValue(BigRitualBlock.GRID_PARTS, 6 + i), 3);

            
            
        }
    }
    
}
