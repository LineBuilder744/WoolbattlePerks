package me.mishiko_kun_phd50.woolbattleperks1.perks.perksScripts.activePerksScripts;

import me.mishiko_kun_phd50.woolbattleperks1.WoolbattlePerks_1;
import me.mishiko_kun_phd50.woolbattleperks1.perks.perksScripts.PerksScripts;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;

public class SafetyPlatform extends PerksScripts {
    static private final int woolNeeded = 22;

    private static final short reloadTime = 15;

    public static ItemStack getPerk(boolean isReady){
        return newPerkItem(Material.BLAZE_ROD, "SafetyPlatform", isReady);
    }
    static public void onUsed(Player player, int slot) {
        new BukkitRunnable() {
            @Override
            public void run() {
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
                                Block block = world.getBlockAt(x1+x, y1-1, z1+z);
                                block.setType(Material.WOOL);
                                block.setMetadata("perkplacedblock", new FixedMetadataValue(WoolbattlePerks_1.getInstance(), true));
                            }
                        }
                    }
                    onCooldown(player, slot, getPerk(false), getPerk(true), reloadTime);

                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            for (int x = -2; x <= 2; x++) {
                                for (int z = -3; z <= 1; z++) {
                                    Block block = world.getBlockAt(x1+x, y1-1, z1+z);
                                    if(block.getType().equals(Material.WOOL) && block.hasMetadata("perkplacedblock")){
                                        //Block block = world.getBlockAt(x1+x, y1-1, z1+z);
                                        block.setType(Material.AIR);

                                    }
                                }
                            }
                        }
                    }.runTaskLater(WoolbattlePerks_1.getInstance(), 600L);

                }
            }
        }.runTask(WoolbattlePerks_1.getInstance());


    }

}
