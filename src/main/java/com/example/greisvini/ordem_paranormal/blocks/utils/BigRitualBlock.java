package com.example.greisvini.ordem_paranormal.blocks.utils;


import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class BigRitualBlock extends GroundDisplayBlock
{
    // Cria uma integerpropertie, aogra este bloo tem uma propriedade do tipo inteiro que pode ser manipulada para algum fim
    // No caso, isso indica a posição no grid considerando-o como uma matriz de blocos 3x3. então o [1,1] é o centro
    // ou, ainda a posição do 4 (tratado mais como um vetor do que qualquer coisa)
    public static final IntegerProperty GRID_PARTS = IntegerProperty.create("grid_part", 0, 8);

    // Posição do meio, mais fácil de referenciar do que  por um 4 jogado no código
    public static final int MIDDLE_POS = 4;
    
    public BigRitualBlock()
    {
        super();

        // Registra a propriedade default como sendo 0
        this.registerDefaultState(this.stateDefinition.any().setValue(GRID_PARTS, 0));
    }

    // Adiciona o GRID_PARTS ao blockstate,se não da erro.
    @Override
    protected void createBlockStateDefinition(Builder<Block, BlockState> builder) 
    {
        builder.add(GRID_PARTS);
    }
}
