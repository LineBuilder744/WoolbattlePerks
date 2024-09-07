package me.mishiko_kun_phd50.woolbattleperks1.perks.perksScripts.passivePerksScripts;

import me.mishiko_kun_phd50.woolbattleperks1.perks.perksScripts.PassivePerksScripts;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.inventory.ItemStack;

public class TnTArrow extends PassivePerksScripts {
    private static final int woolNeeded = 17;
    private static final int arrowsToReady = 10;

    public static ItemStack getPerk(boolean isReady){
        return newPassivePerkItem(Material.TNT, "ExplodingArrow", isReady);
    }
    public static void onUsed(Player player, Projectile projectile){
        onArrow(player, projectile, getPerk(true), getPerk(false), "ExplodingArrow", arrowsToReady, woolNeeded);
    }
}
