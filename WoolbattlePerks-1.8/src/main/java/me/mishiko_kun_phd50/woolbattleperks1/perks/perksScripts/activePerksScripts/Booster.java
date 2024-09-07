package me.mishiko_kun_phd50.woolbattleperks1.perks.perksScripts.activePerksScripts;
import me.mishiko_kun_phd50.woolbattleperks1.perks.perksScripts.PerksScripts;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class Booster extends PerksScripts {

    private static final int woolNeeded = 24;

    private static final short reloadTime = 15;

    public static ItemStack getPerk(boolean isReady){
        return newPerkItem(Material.TRIPWIRE_HOOK, Material.FIREWORK_CHARGE, "Booster", isReady);
    }

    public static void onUsed(Player player, int slot) {
        if(isEnoughWool(player, woolNeeded)){
            removeWool(player, woolNeeded);
            Vector velocity = player.getVelocity();
            velocity.multiply(120).setY(1.4).setX(player.getLocation().getDirection().getX()*2.5).setZ(player.getLocation().getDirection().getZ()*2.5);
            //Vector velocity = player.getLocation().getDirection().getX();// Устанавливаем скорость по оси Y
            player.setVelocity(velocity);

            onCooldown(player, slot, getPerk(false), getPerk(true), reloadTime);
        }
    }
}
