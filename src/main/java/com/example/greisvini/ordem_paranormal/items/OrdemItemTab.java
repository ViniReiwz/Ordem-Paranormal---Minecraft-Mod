package com.example.greisvini.ordem_paranormal.items;

import com.example.greisvini.ordem_paranormal.OrdemParanormal;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class OrdemItemTab
{
    // Cria o registrador para a tabela do modo criativo
    public static final DeferredRegister<CreativeModeTab> ORDEM_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB,  OrdemParanormal.MOD_ID);

    // Registra a tabela no registrador acima
    /*
     * Chama o builder da tabela 'CreativeModeTab.builder()',
     * .icon define o ícone da tabela a ser exibido, no caso, o ícone do item de sangue (Precisa passar o objeto, não só o png)
     * Além disso, ItemStack é o objeto do item, não o conceito (O item ingame propriamente dito), por isso usamos ele aqui
     * .title define o nome exibido. COlocamos o Component.translatable pra ter versatilidade com várias línguas
     * .displayItems dita os itens a serem exibidos na aba
     * pParameters são alguns parâmetros relacionados ao display de itens, não entendi ao certo ainda
     * o pOutput é justamente a saída de itens, ou seja a exibição deles com o .accept
     */
    public static final RegistryObject<CreativeModeTab> ordem_tab = ORDEM_TAB.register("ordem_tab", () -> CreativeModeTab.builder()
    .icon(() -> new ItemStack(OrdemItems.sangue_symbol.get()))
    .title(Component.translatable("creative_tab.ordem_tab"))
    .displayItems((pParameters,pOutput) -> {
        pOutput.accept(OrdemItems.sangue_symbol.get());
        pOutput.accept(OrdemItems.energia_symbol.get());
    })
    .build());

    public static void register(IEventBus eventBus)
    {
         ORDEM_TAB.register(eventBus);
    }
}
