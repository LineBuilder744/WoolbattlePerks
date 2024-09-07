package me.mishiko_kun_phd50.woolbattleperks1.perks.perksScripts.activePerksScripts;



import me.mishiko_kun_phd50.woolbattleperks1.perks.perksScripts.PerksScripts;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class WallBuilder extends PerksScripts {
    private static final int woolNeeded = 18;

    private static final short reloadTime = 3;

    public static ItemStack getPerk(boolean isReady){
        return newPerkItem(Material.STAINED_GLASS_PANE, (short)14, Material.FIREWORK_CHARGE, "WallBuilder", isReady);
    }
    public static void onUsed(Player player, int slot){
        if(isEnoughWool(player, woolNeeded)){
            removeWool(player, woolNeeded);

            World world = player.getWorld();
            Location loc = player.getLocation();

            int x = (int)player.getLocation().getX();
            int y = (int)player.getLocation().getY();
            int z = (int)player.getLocation().getZ();

            float yaw = loc.getYaw();

            yaw = (yaw + 360) % 360;

            // Определяем сторону света
            if (yaw >= 315 || yaw < 45) {
                z+=3;

                for (int x1 = -2; x1 < 3; x1++) {
                    for (int y1 = 0; y1 < 4; y1++) {
                        Block block = world.getBlockAt(x+x1, y+y1, z);
                        if(block.getType().equals(Material.AIR)){
                            block.setType(Material.WOOL);

                            placeBlock(block, player);
                        }
                    }
                }


            } else if (yaw >= 45 && yaw < 135) {
                x-=3;

                for (int z1 = -2; z1 < 3; z1++) {
                    for (int y1 = 0; y1 < 4; y1++) {
                        Block block = world.getBlockAt(x, y+y1, z+z1);
                        if(block.getType().equals(Material.AIR)){
                            block.setType(Material.WOOL);

                            placeBlock(block, player);
                        }
                    }
                }

            } else if (yaw >= 135 && yaw < 225) {
                z-=3;
                for (int x1 = -2; x1 < 3; x1++) {
                    for (int y1 = 0; y1 < 4; y1++) {
                        Block block = world.getBlockAt(x+x1, y+y1, z);
                        if(block.getType().equals(Material.AIR)){
                            block.setType(Material.WOOL);

                            placeBlock(block, player);
                        }
                    }
                }

            } else {
                x+=3;
                for (int z1 = -2; z1 < 3; z1++) {
                    for (int y1 = 0; y1 < 4; y1++) {
                        Block block = world.getBlockAt(x, y+y1, z+z1);
                        if(block.getType().equals(Material.AIR)){
                            block.setType(Material.WOOL);

                            placeBlock(block, player);
                        }
                    }
                }

            }


            onCooldown(player, slot, getPerk(false), getPerk(true), reloadTime);
        }
    }
}
