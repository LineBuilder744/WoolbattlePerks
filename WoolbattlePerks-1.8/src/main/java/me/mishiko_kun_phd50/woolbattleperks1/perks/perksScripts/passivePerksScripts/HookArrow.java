package me.mishiko_kun_phd50.woolbattleperks1.perks.perksScripts.passivePerksScripts;

import me.mishiko_kun_phd50.woolbattleperks1.perks.perksScripts.PassivePerksScripts;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.inventory.ItemStack;

public class HookArrow extends PassivePerksScripts {
    private static final int woolNeeded = 17;
    private static final int arrowsToReady = 5;

    public static ItemStack getPerk(boolean isReady){
        return newPassivePerkItem(Material.REDSTONE_TORCH_ON, "HookArrow", isReady);
    }
    public static void onUsed(Player player, Projectile projectile){
        onArrow(player, projectile, getPerk(true), getPerk(false), "HookArrow", arrowsToReady, woolNeeded);
    }
}
