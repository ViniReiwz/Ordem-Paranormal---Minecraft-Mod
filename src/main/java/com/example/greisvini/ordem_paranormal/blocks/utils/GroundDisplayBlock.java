package com.example.greisvini.ordem_paranormal.blocks.utils;



import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LevelEvent;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

/*
 * Classe de blocos auxiliares que servirão como display no chão (posteriormente em paredes também)
 */
public class GroundDisplayBlock extends Block
{
    
    // Blocos sem colisão que só existem para exibir textura
    public GroundDisplayBlock() 
    {
        super(Properties.of()
        .noCollission()
        .noOcclusion()
        .strength(0.0f)
        );
    }

    @Override 
    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext cCcontext) 
    {
        
        return Shapes.box(0.0, 0.0, 0.0, 1.0, (double)1.0/160.0, 1.0);
    }

    // Verifica se algum vizinho mudou, daí remove o bloco acima
    @Override
    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block block,BlockPos fromPos, boolean isMoving) 
    {
        if(level.getBlockState(pos.below()).isAir())
        {
            level.removeBlock(pos,  false);
            level.levelEvent(LevelEvent.PARTICLES_DESTROY_BLOCK, pos, Block.getId(state));

        }
    }

    

        
}
