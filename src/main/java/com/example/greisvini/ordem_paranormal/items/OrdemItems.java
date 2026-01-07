package com.example.greisvini.ordem_paranormal.items;

import com.example.greisvini.ordem_paranormal.OrdemParanormal;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class OrdemItems 
{
    /*
     * Basicamente precisamos registrar tudo que adicionamos no mod em registradores utilizando o DeferredRegister
     * Como esta classe refere-se aos items do mod, o registrador, denominado ORDEMITEMS será responsável por armazenar
     * os dados de todos os itens do mod.
     * 
     * Ao criar um DeferredRegister, como queremos registrar um item passamos: <Item>. Ainda, o DeferredRegister é um 
     * registrador 'desvinculado', ou seja, sabe-se que deseja registrar um item mas não aonde.
     * 
     * Dessa forma, dizemos que deve-se registrar no registarador geral de items do forge (ForgeRegistries.ITEMS) e
     * passamos o id do mod para o namespace, assim um item qualquer xxxxx será referenciado como:
     * ordem_paranormal:xxxxx
     */
    public static final DeferredRegister<Item> ORDEM_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, OrdemParanormal.MOD_ID);

    /*
     * Adiciona o item 'sangue' para o jogo logicamente.
     * O item é um 'objeto registrativo', ou seja, pode ser registrado. SANGUE  nada mais é uma referencia à este objeto,
     * registrado em ORDEMITEMS (Nossa lista de items do mod), com o nome sangue e instanciada como um item com as propriedades padrão
     * 
     * As características como textura, nom inGame, entre outros são definidos em um .json na pastas resources/item
     * Todos os aspectos referentes à qualquer item devem referenciá-lo pelo nome apssado como parametro no register, no caso 'sangue'
     */
    public static final RegistryObject<Item> sangue_symbol = ORDEM_ITEMS.register("sangue_symbol", () -> new Item(new Item.Properties()));
    
    // Adicionando outro item qualquer
    public static final RegistryObject<Item> energia_symbol = ORDEM_ITEMS.register("energia_symbol", () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus)
    {
        ORDEM_ITEMS.register(eventBus);
    }
}
