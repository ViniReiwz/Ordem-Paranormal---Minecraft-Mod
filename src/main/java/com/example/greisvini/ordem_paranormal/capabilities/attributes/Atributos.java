package com.example.greisvini.ordem_paranormal.capabilities.attributes;

import java.util.UUID;

import net.minecraft.nbt.CompoundTag;

/*
 * Classe designada para os atributos do sistema de RPG
 */
public class Atributos
{

    public static UUID FOR_UUID = UUID.fromString("10c72df3-c471-46fd-b977-825a941cd717");
    public static UUID AGI_UUID = UUID.fromString("a24f92bb-27f8-4234-8539-9c23e933e39c");
    public static UUID PRE_UUID = UUID.fromString("77713956-8bdb-4d1b-99f0-aeaa87737b71");
    public static UUID INT_UUID = UUID.fromString("6eecf4a4-7f14-4963-9403-94fac9f1c681");
    public static UUID VIG_UUID = UUID.fromString("09805518-6bb4-44a1-b48a-03e750f3b366");

    private int FOR = 1;
    private int AGI = 1;
    private int PRE = 1;
    private int INT = 1;
    private int VIG = 1;
    
    
    public void set(int val, String type)
    {
        switch (type) 
        {
            case "FOR":
            {   
                this.FOR = val;
                break;
            }

            case "AGI":
            {
                this.AGI = val;
                break;
            }

            case "PRE":
            {
                this.PRE = val;
                break;
            }

            case "INT":
            {
                this.INT = val;
                break;
            }

            case "VIG":
            {
                this.VIG = val;
                break;
            }

            default:
                break;
        }
    }

    public int get(String type)
    {
        switch (type) 
        {
            case "FOR":
            {   
                return this.FOR;
                
            }

            case "AGI":
            {
                return this.AGI;
                
            }

            case "PRE":
            {
                return this.PRE;
                
            }

            case "INT":
            {
                return this.INT;
                
            }

            case "VIG":
            {
                return this.VIG;
                
            }

            default:
                return -1;
        }
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
        this.FOR = att.get("FOR");
        this.AGI = att.get("AGI");
        this.PRE = att.get("PRE");
        this.INT = att.get("INT");
        this.VIG = att.get("VIG");
    }
    

}
