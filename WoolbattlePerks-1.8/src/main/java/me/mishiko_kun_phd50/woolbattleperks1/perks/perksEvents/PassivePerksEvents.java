package me.mishiko_kun_phd50.woolbattleperks1.perks.perksEvents;

import me.mishiko_kun_phd50.woolbattleperks1.perks.Perks;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.ItemStack;

public class PassivePerksEvents implements Listener {
    @EventHandler
    public void onBow(EntityShootBowEvent event){

        if(event.getEntity() instanceof Player && event.getProjectile() instanceof Projectile){
            Player player = (Player)event.getEntity();
            for (ItemStack stack : player.getInventory().getContents()) {
                if(stack != null && stack.getItemMeta().getLore() != null){

                    Perks passivePerk = Perks.getPerkFrom(stack);
                    assert passivePerk != null;
                    passivePerk.onArrow(player, (Projectile) event.getProjectile());

                }
            }
        }
    }
}
