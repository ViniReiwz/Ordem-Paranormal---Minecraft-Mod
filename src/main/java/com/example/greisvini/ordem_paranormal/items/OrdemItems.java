package com.example.greisvini.ordem_paranormal.items;

import com.example.greisvini.ordem_paranormal.OrdemParanormal;
import com.example.greisvini.ordem_paranormal.items.comida.SopaCaoticaItem;
import com.example.greisvini.ordem_paranormal.items.ritualisticos.GizItem;

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
     * Adicionando um item para o jogo logicamente.
     * O item é um 'objeto registrativo', ou seja, pode ser registrado. o nome em letras maiúsculas (XXXX) nada mais é do que
     * uma referencia à este objeto,
     * registrado em ORDEMITEMS (Nossa lista de items do mod), com o nome passado no  primeiro parâmetro e instanciada como um item com as propriedades definidas no supplier (Item.Properties)
     * 
     * As características como textura, nom inGame, entre outros são definidos em um .json na pastas resources/item
     * Todos os aspectos referentes à qualquer item devem referenciá-lo pelo nome dado como ao register parâmetro ('xxxx').
     */
    


    public static final RegistryObject<GizItem> GIZ = ORDEM_ITEMS.register("giz", () -> new GizItem(new Item.Properties().durability(1)));

    // Item de comida têm de passar foodProperties
    public static final RegistryObject<SopaCaoticaItem> SOPA_CAOTICA = ORDEM_ITEMS.register("sopa_caotica", () -> new SopaCaoticaItem(new Item.Properties().food(OrdemFoods.SOPA_CAOTICA)));

    public static void register(IEventBus eventBus)
    {
        ORDEM_ITEMS.register(eventBus);
    }
}
