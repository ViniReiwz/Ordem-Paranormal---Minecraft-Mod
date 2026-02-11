package com.example.greisvini.ordem_paranormal.client.UI;

import net.minecraft.network.chat.Component;

import com.example.greisvini.ordem_paranormal.client.UI.assets.OrdemButton;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;

public class OrdemMainMenu extends Screen
{
    public OrdemMainMenu()    
    {
        super(Component.literal("Menu principal"));
    }

    @Override
    protected void init() 
    {    
        Button botao = new OrdemButton(this.width, this.height, width, height, Component.literal("Teste"), btn -> {});

        this.addRenderableWidget(botao);
    }

    @Override
    public void render(GuiGraphics gui, int mouseX, int mouseY, float partialTick) 
    {  
        this.renderBackground(gui);
        super.render(gui, mouseX, mouseY, partialTick);
    }
    
    

}
