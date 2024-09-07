package me.mishiko_kun_phd50.woolbattleperks1.perks.perksScripts.activePerksScripts;

import me.mishiko_kun_phd50.woolbattleperks1.WoolbattlePerks_1;
import me.mishiko_kun_phd50.woolbattleperks1.perks.perksScripts.PerksScripts;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class Rope extends PerksScripts {

    private static final int woolNeeded = 24;

    private static final short reloadTime = 15;

    public static ItemStack getPerk(boolean isReady){
        return newPerkItem(Material.VINE, Material.FIREWORK_CHARGE, "Rope", isReady);
    }
    public static void onUsed(Player player, int slot){
        if(isEnoughWool(player, woolNeeded)){
            removeWool(player, woolNeeded);

            World world = player.getWorld();
            Location loc = player.getLocation();

            int x = (int)player.getLocation().getX();
            int y = (int)player.getLocation().getY();
            int z = (int)player.getLocation().getZ();

            player.teleport(new Location(player.getWorld(), x+0.5, y, z+0.5).setDirection(loc.getDirection()));

            float yaw = loc.getYaw();

            yaw = (yaw + 360) % 360;

            // Определяем сторону света
            if (yaw >= 315 || yaw < 45) {
                z++;
                //return "Север";
            } else if (yaw >= 45 && yaw < 135) {
                x--;
                //return "Восток";
            } else if (yaw >= 135 && yaw < 225) {
                z--;
                //return "Юг";
            } else { // yaw >= 225 && yaw < 315
                x++;
                //return "Запад";
            }
            final int finalX = x;
            final int finalY = y;
            final int finalZ = z;

            for(int i=1; i<11; i++){
                Block block = world.getBlockAt(x, y-i, z);
                if(block.getType().equals(Material.AIR)){
                    block.setType(Material.WOOL);
                }
            }
            new BukkitRunnable() {
                @Override
                public void run() {
                    for(int i=1; i<11; i++){
                        Block block = world.getBlockAt(finalX, finalY-i, finalZ);
                        if(block.getType().equals(Material.AIR)){
                            block.setType(Material.WOOL);
                        }
                    }
                }
            }.runTaskLater(WoolbattlePerks_1.getInstance(), 600L);
            onCooldown(player, slot, getPerk(false), getPerk(true), reloadTime);
        }
    }
}


