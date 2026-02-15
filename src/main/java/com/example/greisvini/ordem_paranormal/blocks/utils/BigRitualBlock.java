package com.example.greisvini.ordem_paranormal.blocks.utils;


import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class BigRitualBlock extends GroundDisplayBlock
{
    // Cria uma integerpropertie, aogra este bloo tem uma propriedade do tipo inteiro que pode ser manipulada para algum fim
    // No caso, isso indica a posição no grid considerando-o como uma matriz de blocos 3x3. então o [1,1] é o centro
    // ou, ainda a posição do 4 (tratado mais como um vetor do que qualquer coisa)
    public static final IntegerProperty GRID_PARTS = IntegerProperty.create("grid_part", 0, 8);

    // Porpriedade de direção, para seguir a orientação do player
    public static final DirectionProperty FACING_DIR = BlockStateProperties.HORIZONTAL_FACING;


    // Posição do meio, mais fácil de referenciar do que  por um 4 jogado no código
    public static final int MIDDLE_POS = 4;
    
    public BigRitualBlock()
    {
        super();

        // Registra a posição no grid com 0 inicialmente, bem como coloca os blocos encarando a direção norte
        this.registerDefaultState(this.stateDefinition.any()
        .setValue(GRID_PARTS, 0)
        .setValue(FACING_DIR, Direction.NORTH));
        
    }

    // Adiciona as propriedads ao blockstate
    @Override
    protected void createBlockStateDefinition(Builder<Block, BlockState> builder) 
    {
        builder.add(GRID_PARTS);
        builder.add(FACING_DIR);
    }
}
