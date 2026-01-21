package com.example.greisvini.ordem_paranormal.utils;

import com.example.greisvini.ordem_paranormal.OrdemParanormal;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class OrdemTags 
{
    public static class Blocks
    {

        public static TagKey<Block> non_chalk_writeable = createTag("non_chalk_writeable");


        private static TagKey<Block> createTag(String name)
        {
            return BlockTags.create(new ResourceLocation(OrdemParanormal.MOD_ID,name));
        }
    }

    public static class Items 
    {
    
        
    }
}
