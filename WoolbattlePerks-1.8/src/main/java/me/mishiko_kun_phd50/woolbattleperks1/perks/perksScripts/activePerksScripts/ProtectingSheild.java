package me.mishiko_kun_phd50.woolbattleperks1.perks.perksScripts.activePerksScripts;

import me.mishiko_kun_phd50.woolbattleperks1.WoolbattlePerks_1;
import me.mishiko_kun_phd50.woolbattleperks1.perks.perksScripts.PerksScripts;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;

public class ProtectingSheild extends PerksScripts {

    private static final int woolNeeded = 20;
    private static final short reloadTime = 15;

    public static ItemStack getPerk(boolean isReady){
        return newPerkItem(Material.EMERALD, "Sheild", isReady);
    }

    public static void onUsed(Player player, int slot){
        if(isEnoughWool(player, woolNeeded)){
            removeWool(player, woolNeeded);
            player.setMetadata("ProtectedBySheild", new FixedMetadataValue(WoolbattlePerks_1.getInstance(), true));

            onCooldown(player, slot, getPerk(false), getPerk(true), reloadTime);

            new BukkitRunnable(){

                @Override
                public void run() {
                    player.removeMetadata("ProtectedBySheild", WoolbattlePerks_1.getInstance());
                }
            }.runTaskLater(WoolbattlePerks_1.getInstance(), 100L);
        }
    }
}
