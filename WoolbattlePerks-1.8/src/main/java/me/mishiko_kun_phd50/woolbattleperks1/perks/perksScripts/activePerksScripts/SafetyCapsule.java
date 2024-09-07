package me.mishiko_kun_phd50.woolbattleperks1.perks.perksScripts.activePerksScripts;

import me.mishiko_kun_phd50.woolbattleperks1.perks.perksScripts.PerksScripts;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SafetyCapsule extends PerksScripts {
    private static final int woolNeeded = 20;

    private static final short reloadTime = 15;
    public static ItemStack getPerk(boolean isReady){
        return newPerkItem(Material.STAINED_GLASS, (short)14, Material.FIREWORK_CHARGE, "SafetyCapsule", isReady);
    }

    static public void onUsed(Player player, int slot) {
        if(isEnoughWool(player, woolNeeded)){
            removeWool(player, woolNeeded);
            World world = player.getWorld();

            Location loc = player.getLocation();
            int x = (int)loc.getX();
            int y = (int)loc.getY();
            int z = (int)loc.getZ();



            player.teleport(new Location(player.getWorld(), x+0.5, y, z+0.5).setDirection(loc.getDirection()));
            // Устанавливаем платформу 6x6 блоков под игроком
            if( world.getBlockAt(x, y-1, z).getType().equals(Material.AIR)) {
                world.getBlockAt(x, y-1, z).setType(Material.WOOL);
            }
            if( world.getBlockAt(x, y+2, z).getType().equals(Material.AIR)) {
                world.getBlockAt(x, y+2, z).setType(Material.WOOL);
            }

            if( world.getBlockAt(x+1, y, z).getType().equals(Material.AIR)){
                world.getBlockAt(x+1, y, z).setType(Material.WOOL);
            }
            if( world.getBlockAt(x+1, y+1, z).getType().equals(Material.AIR)){
                world.getBlockAt(x+1, y+1, z).setType(Material.WOOL);
            }

            if( world.getBlockAt(x-1, y, z).getType().equals(Material.AIR)){
                world.getBlockAt(x-1, y, z).setType(Material.WOOL);
            }
            if( world.getBlockAt(x-1, y+1, z).getType().equals(Material.AIR)){
                world.getBlockAt(x-1, y+1, z).setType(Material.WOOL);
            }

            if( world.getBlockAt(x, y, z+1).getType().equals(Material.AIR)){
                world.getBlockAt(x, y, z+1).setType(Material.WOOL);
            }
            if( world.getBlockAt(x, y+1, z+1).getType().equals(Material.AIR)){
                world.getBlockAt(x, y+1, z+1).setType(Material.WOOL);
            }

            if( world.getBlockAt(x, y, z-1).getType().equals(Material.AIR)){
                world.getBlockAt(x, y, z-1).setType(Material.WOOL);
            }
            if( world.getBlockAt(x, y+1, z-1).getType().equals(Material.AIR)){
                world.getBlockAt(x, y+1, z-1).setType(Material.WOOL);
            }
            //ItemStack safetyPlatform = getPerk(Material.BLAZE_ROD, "Safety Platform", true(player);
            onCooldown(player, slot, getPerk(false), getPerk(true), reloadTime);
        }

    }
}
