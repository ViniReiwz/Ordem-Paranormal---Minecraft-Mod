package com.example.greisvini.ordem_paranormal.client.UI;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import com.example.greisvini.ordem_paranormal.OrdemParanormal;
import com.example.greisvini.ordem_paranormal.utils.OrdemKeyBindings;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;

public class OrdemMainMenu extends Screen
{

    public static ResourceLocation MENU_BACKGROUND = new ResourceLocation(OrdemParanormal.MOD_ID, "textures/ui/menu_background.png");

    public static ResourceLocation ATRIBUTOS_PNG = new ResourceLocation(OrdemParanormal.MOD_ID, "textures/ui/atributos.png");

    private static int  bg_scale = 100;

    private int bg_width = 256 + bg_scale;
    private int bg_height = 128 + bg_scale/2 ;

    public OrdemMainMenu()    
    {   
        super(Component.literal("Menu principal"));
    }

    @Override
    protected void init() 
    {    
        // Button botao = new OrdemButton(this.width, this.height, width, height, Component.literal("Teste"), btn -> {});

        // this.addRenderableWidget(botao);
    }

    @Override
    public void render(GuiGraphics gui, int mouseX, int mouseY, float partialTick) 
    {  
        
        // Origem do background para ficar centralizado na tela
        int bg_x_origin = (this.width - this.bg_width)/2;
        int bg_y_origin = (this.height - this.bg_height)/2; 

        // Coloca o plano de fundo do menu
        gui.blit(
            MENU_BACKGROUND,                // Textura
            bg_x_origin, bg_y_origin,       // Posição de origem (0,0 == canto supeiror esquerdo)
            0,0,        // Posição de início na imagem (0,0 == sup esquerdo)
            this.bg_width,this.bg_height,   // Tamanho a ser exibido
            this.bg_width,this.bg_height    // Tamanho da imagem
            );

        int atrib_size = this.bg_width/3;

        // Coloca o grid pentagonal de atributos
        gui.blit(
            ATRIBUTOS_PNG,                  // Textura
            bg_x_origin + this.bg_width/20, bg_y_origin + this.bg_height/6,       // Posição de origem (0,0 == canto supeiror esquerdo)
            0,0,        // Posição de início na imagem (0,0 == sup esquerdo)
            atrib_size,atrib_size,          // Tamanho da imagem
            atrib_size, atrib_size          // Tamanho a ser exibido
            );
        
        // Posição hoizontal do modelo do player, com a origem sendo no centro do modelo
        int playerX = bg_x_origin + this.bg_width/20 + atrib_size/2;

        // Posição vertical do modelo do player, com a origme nos pés do modelo
        int playerY = bg_y_origin + this.bg_height/6 + (9*atrib_size/12);

        // Altura em pixel do modelo
        int scale = 25;

        InventoryScreen.renderEntityInInventoryFollowsMouse(
            gui,                                // Interface
            playerX, playerY, scale,            // Posição em X, Y e altura do modelo
            playerX - mouseX,                   // Acompanha o mouse realtivamente à posição horizontal do modelo
            playerY - scale - mouseY,           // Acompanha o mouse relativamente à posição vertical do modelo
            Minecraft.getInstance().player);    // Player do Client

        super.render(gui, mouseX, mouseY, partialTick);
    }

    // Sobreescreve o método para fechar o menu na tecla 'M' (mesma que abre)
    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers)
    {
        if(OrdemKeyBindings.OPEN_MENU_KEY.matches(keyCode, scanCode))
        {
            this.onClose();
            return true;
        }

        // Chama o super, logo fecha com 'ESC' também
        return super.keyPressed(keyCode, scanCode, modifiers);
    }
}
