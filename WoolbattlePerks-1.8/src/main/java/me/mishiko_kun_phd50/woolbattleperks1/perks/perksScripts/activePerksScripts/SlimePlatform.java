package me.mishiko_kun_phd50.woolbattleperks1.perks.perksScripts.activePerksScripts;
import me.mishiko_kun_phd50.woolbattleperks1.perks.perksScripts.PerksScripts;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SlimePlatform extends PerksScripts {
    static private final int woolNeeded = 22;

    private static final short reloadTime = 15;

    public static ItemStack getPerk(boolean isReady){
        return newPerkItem(Material.SLIME_BALL, "SlimePlatform", isReady);
    }
    static public void onUsed(Player player, int slot) {
        if(isEnoughWool(player, woolNeeded)){
            removeWool(player, woolNeeded);
            World world = player.getWorld();

            Location loc = player.getLocation();
            int x1 = (int)loc.getX();
            int y1 = (int)loc.getY();
            int z1 = (int)loc.getZ();

            player.teleport(new Location(player.getWorld(), x1+0.5, y1, z1-0.5).setDirection(loc.getDirection()));
            for (int x = -2; x <= 2; x++) {
                for (int z = -3; z <= 1; z++) {
                    if(world.getBlockAt(x1+x, y1-1, z1+z).getType().equals(Material.AIR)){
                        world.getBlockAt(x1+x, y1-1, z1+z).setType(Material.WOOL);
                    }
                }
            }
            //ItemStack safetyPlatform = getPerk(Material.BLAZE_ROD, "Safety Platform", true(player);
            onCooldown(player, slot, getPerk(false), getPerk(true), reloadTime);
        }

    }
}
