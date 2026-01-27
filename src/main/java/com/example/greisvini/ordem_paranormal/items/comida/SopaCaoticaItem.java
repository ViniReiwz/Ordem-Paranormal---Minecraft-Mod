package com.example.greisvini.ordem_paranormal.items.comida;

import java.util.Random;



import net.minecraft.world.entity.player.Player;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;


public class SopaCaoticaItem extends Item
{

    public SopaCaoticaItem(Properties properties) 
    {
        super(properties);
    }
    
    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) 
    {
        if(!level.isClientSide() && (entity instanceof Player player))
        {
            Random rand = new Random();

            // 'Rola' um dado de 20 lados
            int roll = rand.nextInt(1, 21);
            
            if(roll == 20)
            {
                // adiciona o efeito de regeneração ao player
                // tempo em ticks 200 = 10 segundos
                // Nível do efeito igual valor int+ 1, logo o nível será II
                player.addEffect(new MobEffectInstance(MobEffects.REGENERATION,100,1));
                this.feed(player,20, 1f );
            }

            else if (roll == 1)
            {
                player.addEffect(new MobEffectInstance(MobEffects.POISON,100, 1));
                this.feed(player, -1, 0f);
            }

            else
            {
                this.feed(player, roll, (float)roll/20f);
            }

        }

        return super.finishUsingItem(stack, level, entity);
    }

    private void feed(Player player, int nutriton, float saturation)
    {
        if(nutriton >= 0)
        {
            player.getFoodData().eat(nutriton, saturation);
        }
        else
        {   
            // Dfeixa o jogador com fome total (elimina toda a saturação e comida)
            player.getFoodData().setFoodLevel(0);;
        }
    }
}
