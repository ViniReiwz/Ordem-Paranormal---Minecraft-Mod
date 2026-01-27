package com.example.greisvini.ordem_paranormal.blocks;


import com.example.greisvini.ordem_paranormal.OrdemParanormal;
import com.example.greisvini.ordem_paranormal.blocks.rituais.TranscendSymbol;
import com.example.greisvini.ordem_paranormal.items.OrdemItems;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class OrdemBlocos 
{
    // Inicializa o registrador de blocos do mod
    public static final DeferredRegister<Block> ORDEM_BLOCOS = DeferredRegister.create(ForgeRegistries.BLOCKS,OrdemParanormal.MOD_ID);

    // Cria e já registra o bloco, copiando as características de madeira de carvalho
    public static final RegistryObject<Block> MADEIRA_ENSANGUENTADA = registerBlock("madeira_ensanguentada", () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD).strength(3f)));

    public static final RegistryObject<Block> TABUA_ENSANGUENTADA = registerBlock("tabua_ensanguentada", () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).strength(2f)));
   
    public static final RegistryObject<TranscendSymbol> TRANSCEND_SYMBOL = registerBlock("transcend_symbol", () -> new TranscendSymbol());

    /*
     * Registra um bloco a partir de determinado supplier e também registra o item relacionado
     */
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block_supplier)
    {
        RegistryObject<T> bloco_a_registrar = ORDEM_BLOCOS.register(name, block_supplier);
        registerBlockItem(name, bloco_a_registrar);
        return bloco_a_registrar;
    }

    /*
     * Este trecho de código torna possível associar um bloco (BlockState) à um item dentro
     * do jogo, pois um bloco não tem nada a ver com um item (pode-se ter um bloco sem 
     * um item). Como queremos um item associado temos que criar esse método
     * 
     * Notemos que este método aceita qualquer classe que seja um bloco (Versatilidade para blocos especiais) do mod
     */
    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name,RegistryObject<T> block)
    {
        // Retorna o item referente ao bloco
        return OrdemItems.ORDEM_ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus)
    {
        ORDEM_BLOCOS.register(eventBus);
    }
}
