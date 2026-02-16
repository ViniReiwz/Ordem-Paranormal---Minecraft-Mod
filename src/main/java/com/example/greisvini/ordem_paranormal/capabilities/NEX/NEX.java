package com.example.greisvini.ordem_paranormal.capabilities.NEX;

import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class NEX 
{
    private int nex = 0;
    private int ordem_xp = 0;
    public static int MAX = 99;


    public void upNex()
    {
        // Incrementa o nex de 5 em 5 (%)
        this.nex += 5;

        // Maximo == 99%
        if(this.nex > MAX){this.nex = MAX;}
    }

    public void increaseXp(int val)
    {
        this.ordem_xp += val;

        if(this.ordem_xp >= this.getXpNeeded())
        {
            Player p = Minecraft.getInstance().player;
            p.sendSystemMessage(Component.translatable("up.nex.call"));
        }

    }

    public void setNex(int nex)
    {
        this.nex = nex;
    }

    public int getNex()
    {
        return this.nex;
    }

    public int getXp()
    {
        return this.ordem_xp;
    }

    public int getXpNeeded()
    {
        return ((this.nex/5) + 1) * 10;
    }

      // Salva os dados
    public void saveNBTdata(CompoundTag nbt)
    {   
       nbt.putInt("NEX", this.nex);
       nbt.putInt("ordem_xp",this.ordem_xp);
    }

    // Carrega os dados salvos anteriormente
    public void loadNBTdata(CompoundTag nbt)
    {
        this.nex = nbt.getInt("NEX");
        this.ordem_xp = nbt.getInt("ordem_xp");
    }

    public void copyFrom(NEX nex)
    {
        this.nex = nex.getNex();
        this.ordem_xp = nex.getXp();
    }
}
