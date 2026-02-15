package com.example.greisvini.ordem_paranormal.utils;

import com.mojang.blaze3d.platform.InputConstants;

import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

/*
 * Classe feita para guardar todos os keybindings do mod
 */
public class OrdemKeyBindings 
{

    // Categoria dos keybinds, ou seja, nome do mod que aparece ao clicar em 'controles' na configuração
    // Têm de estrar present no lang.json, pois é translatable
    public static final String KEY_CATEGORY_ORDEM = "key.category.ordem_paranormal";

    // Castar rituais no 'R' --------------

    // Translatable da função da tecla (nome que aparece ao lado do binding)
    public static final String KEY_CAST_RITUALS = "key.ordem_paranormal.cast_rituals";

    public static final String KEY_OPEN_MENU = "key.ordem_paranormal.open_menu";
    
    /*
        Mapeia a tecla,passando:
        (nome/função (String),  Contexto em que é pressionada , Tipo do input (mouse ou teclado), Código da tecla(int),
        Categoria pertencente (String))
    */ 
    public static final KeyMapping CAST_KEY = new KeyMapping(KEY_CAST_RITUALS, KeyConflictContext.IN_GAME,InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_C, KEY_CATEGORY_ORDEM);
    // ------------------------------------

    // Tecla para abrir menu 'M'
    public static final KeyMapping OPEN_MENU_KEY = new KeyMapping(KEY_OPEN_MENU, KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_M, KEY_CATEGORY_ORDEM);
}
