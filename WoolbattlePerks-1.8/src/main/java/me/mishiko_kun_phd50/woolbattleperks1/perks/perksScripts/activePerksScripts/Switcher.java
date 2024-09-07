package me.mishiko_kun_phd50.woolbattleperks1.perks.perksScripts.activePerksScripts;

import me.mishiko_kun_phd50.woolbattleperks1.WoolbattlePerks_1;
import me.mishiko_kun_phd50.woolbattleperks1.perks.perksScripts.PerksScripts;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

public class Switcher extends PerksScripts {
    private static final short reloadTime = 6;
    private static final int woolNeeded = 10;

    public static ItemStack getPerk(boolean isReady){
        return newPerkItem(Material.SNOW_BALL, Material.FIREWORK_CHARGE, "Switcher", isReady);
    }

    public static void onUsed(Player player, int slot){
        if(isEnoughWool(player, woolNeeded)){
            removeWool(player, woolNeeded);
            Snowball snowball = player.launchProjectile(Snowball.class);
            snowball.setMetadata("switcher", new FixedMetadataValue(WoolbattlePerks_1.getInstance(), true));

            onCooldown(player, slot, getPerk(false), getPerk(true), reloadTime);

        }
    }



}
