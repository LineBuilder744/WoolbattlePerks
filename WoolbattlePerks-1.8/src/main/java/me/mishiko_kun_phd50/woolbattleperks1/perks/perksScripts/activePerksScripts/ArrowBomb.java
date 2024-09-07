package me.mishiko_kun_phd50.woolbattleperks1.perks.perksScripts.activePerksScripts;

import me.mishiko_kun_phd50.woolbattleperks1.WoolbattlePerks_1;
import me.mishiko_kun_phd50.woolbattleperks1.perks.perksScripts.PerksScripts;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

public class ArrowBomb extends PerksScripts{
    private static final short reloadTime = 6;
    private static final int woolNeeded = 10;
    //private final int slot;
    // private final player;


    public static ItemStack getPerk(boolean isReady){
        return newPerkItem(Material.FIREWORK_CHARGE, Material.FIREWORK_CHARGE, "ArrowBomb", isReady);
    }

    public static void onUsed(Player player, int slot){
        if(isEnoughWool(player, woolNeeded)){
            removeWool(player, woolNeeded);

            Snowball snowball = player.launchProjectile(Snowball.class);
            snowball.setMetadata("arrowbomb", new FixedMetadataValue(WoolbattlePerks_1.getInstance(), true));

            onCooldown(player, slot, getPerk(false), getPerk(true), reloadTime);
        }
    }
}
