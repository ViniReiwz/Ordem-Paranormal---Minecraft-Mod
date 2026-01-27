package com.example.greisvini.ordem_paranormal.items;

import net.minecraft.world.food.FoodProperties;

public class OrdemFoods 
{
    /*
     * Cria a foodproperties para a sopa caótica
     * FoodProperties atuam como uma 'tabela de dados' que guardam valores como anutritção, saturação,
     * se é para comer rápidp, etc.
     * 
     * Nesse caso como queremos um item que varia de acordo com valores aleatórios, a foodproperties pode ser genérica, pois os
     * valores serão alterados na classe SopaCaoticaItem quando o item terminar de ser usado
     * 
     * nutriçaõ vai de 0 à 20 e o modificador de saturação indica quantas vezes em relação à nutrição este vai ficar saturado
     * (meio que no total da 20 de nutrição + 20 de saturação)
     */
    public static FoodProperties SOPA_CAOTICA = new FoodProperties.Builder().nutrition(0).saturationMod(0.0f).alwaysEat().build();
}
