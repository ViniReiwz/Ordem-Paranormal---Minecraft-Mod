package com.example.greisvini.ordem_paranormal.capabilities.attributes;

import net.minecraft.nbt.CompoundTag;

/*
 * Classe designada para os atributos do sistema de RPG
 */
public class Atributos
{
    private int FOR = 1;
    private int AGI = 1;
    private int PRE = 1;
    private int INT = 1;
    private int VIG = 1;
    
    
    public int getFOR() 
    {
        return this.FOR;
    }
    public void setFOR(int forca) 
    {
        this.FOR = forca;
    }

    public int getAGI() 
    {
        return this.AGI;
    }
    public void setAGI(int agilidade) 
    {
        this.AGI = agilidade;
    }

    public int getPRE() 
    {
        return this.PRE;
    }
    public void setPRE(int presenca) 
    {
        this.PRE = presenca;
    }

    public int getINT() 
    {
        return this.INT;
    }
    public void setINT(int intelecto) 
    {
        this.INT = intelecto;
    }

    public int getVIG() 
    {
        return this.VIG;
    }
    public void setVIG(int vigor) 
    {
        this.VIG = vigor;
    }

    // Salva os dados
    public void saveNBTdata(CompoundTag nbt)
    {   
        nbt.putInt("FOR", this.FOR);
        nbt.putInt("AGI", this.AGI);
        nbt.putInt("PRE",this.PRE);
        nbt.putInt("INT", this.INT);
        nbt.putInt("VIG", this.VIG);
    }

    // Carrega os dados salvos anteriormente
    public void loadNBTdata(CompoundTag nbt)
    {
        this.FOR = nbt.getInt("FOR");
        this.AGI = nbt.getInt("AGI");
        this.PRE = nbt.getInt("PRE");
        this.INT = nbt.getInt("INT");
        this.VIG = nbt.getInt("VIG");
    }

    public void copyFrom(Atributos att)
    {
        this.FOR = att.getFOR();
        this.AGI = att.getAGI();
        this.PRE = att.getPRE();
        this.INT = att.getINT();
        this.VIG = att.getVIG();
    }
    

}
