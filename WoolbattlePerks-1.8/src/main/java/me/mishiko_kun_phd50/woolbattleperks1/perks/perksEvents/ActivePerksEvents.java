package me.mishiko_kun_phd50.woolbattleperks1.perks.perksEvents;

import me.mishiko_kun_phd50.woolbattleperks1.perks.Perks;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;

import org.bukkit.event.player.PlayerInteractEvent;


public class ActivePerksEvents implements Listener {
    @EventHandler
    public void onPerk(PlayerInteractEvent event){
        Player player = event.getPlayer();

        if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK){
            if(player.getItemInHand().getItemMeta().getLore() != null) {

                Perks perk = Perks.getPerkFrom(player.getItemInHand());
                perk.onUsed(player);
            }
        }
    }

}
