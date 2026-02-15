package com.example.greisvini.ordem_paranormal.client.UI.assets;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;

// Classe de botão customizado 
public class OrdemButton extends Button
{
    // Construtor customizado para não tilizar o builder
    public OrdemButton(int x, int y, int width, int height, Component message, OnPress onpress)
    {
        super(x,y,width,height,message,onpress,DEFAULT_NARRATION);
    }

    // renderiza o botão a cada tick
    @Override
    public void renderWidget(GuiGraphics gui, int mouseX, int mouseY, float partialTick) 
    {        
        gui.fill(mouseX, mouseY, mouseX + 100, mouseY + 100, 0xFF000000);
        gui.drawCenteredString(Minecraft.getInstance().font, this.getMessage(), mouseX, mouseY, 0xFFFFFFFF);
    }



}
