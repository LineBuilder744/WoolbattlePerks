package me.mishiko_kun_phd50.woolbattleperks1.perks.perksScripts.activePerksScripts;

import me.mishiko_kun_phd50.woolbattleperks1.WoolbattlePerks_1;
import me.mishiko_kun_phd50.woolbattleperks1.perks.perksScripts.PerksScripts;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

public class ThrowableTnT extends PerksScripts {

    static private final int woolNeeded = 17;
    private static final short reloadTime = 10;


    public static ItemStack getPerk(boolean isReady){
        return newPerkItem(Material.TNT, Material.FIREWORK_CHARGE, "ThrowableTnT", isReady);
    }

    public static void onUsed(Player player, int slot){

        if(isEnoughWool(player, woolNeeded)){
            removeWool(player, woolNeeded);
            Snowball snowball = player.launchProjectile(Snowball.class);
            snowball.setMetadata("throwableTnT", new FixedMetadataValue(WoolbattlePerks_1.getInstance(), true));

            onCooldown(player, slot, getPerk(false), getPerk(true), reloadTime);

        }
    }
    static public void spawnThrowableTnT(Projectile projectile){
        TNTPrimed tnt = (TNTPrimed) projectile.getWorld().spawnEntity(projectile.getLocation(), EntityType.PRIMED_TNT);
        tnt.setFuseTicks(5);

        tnt.setMetadata("ThrowableTnT", new FixedMetadataValue(WoolbattlePerks_1.getInstance(), true));
    }
}
