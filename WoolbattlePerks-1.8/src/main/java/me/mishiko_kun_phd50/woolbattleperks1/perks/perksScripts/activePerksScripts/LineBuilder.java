package me.mishiko_kun_phd50.woolbattleperks1.perks.perksScripts.activePerksScripts;

import me.mishiko_kun_phd50.woolbattleperks1.WoolbattlePerks_1;
import me.mishiko_kun_phd50.woolbattleperks1.perks.perksScripts.PerksScripts;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class LineBuilder extends PerksScripts {
    private static final int woolNeeded = 1;
    private static final short reloadTime = 10;
    private static final short maxLineLength = 10;
    private static final Map<UUID, Object[]> playerHoldTime = new HashMap<>();

    public static ItemStack getPerk(boolean isReady){
        return newPerkItem(Material.STICK, "LineBuilder", isReady);
    }

    static public void onUsed(Player player, int slot) {
        new BukkitRunnable() {
            @Override
            public void run() {
                if(isEnoughWool(player, woolNeeded)){
                    removeWool(player, woolNeeded);

                    long currentTime = System.currentTimeMillis();

                    if(playerHoldTime.containsKey(player.getUniqueId())){
                        if(currentTime - (long) playerHoldTime.get(player.getUniqueId())[0] < 1000){

                            if((int) playerHoldTime.get(player.getUniqueId())[2]>=maxLineLength){
                                playerHoldTime.remove(player.getUniqueId());
                                onCooldown(player, slot, getPerk(false), getPerk(true), reloadTime);
                            }
                            else{
                                playerHoldTime.replace(player.getUniqueId(), continueLine(player, currentTime));
                            }
                        }
                        // если прошло менее 10 секунд после использования, то начинается новая линия со старым счетчиком блоков
                        else if(currentTime - (long) playerHoldTime.get(player.getUniqueId())[0] < reloadTime*1000){
                            if((int) playerHoldTime.get(player.getUniqueId())[2]>=maxLineLength){
                                playerHoldTime.remove(player.getUniqueId());
                                onCooldown(player, slot, getPerk(false), getPerk(true), reloadTime);
                            }
                            else{
                                player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 1, 4), true);
                                playerHoldTime.replace(player.getUniqueId(), startNewLine(player, currentTime));
                            }
                        }
                        //Если время после первого использования больше 10 секунд то создается новая линия
                        else{
                            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 1, 4), true);
                            playerHoldTime.replace(player.getUniqueId(), startNewestLine(player, currentTime));
                        }
                    }
                    // Если игрок не использовал перк ранее создается новый элемент в playerHoldTime
                    else{
                        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 1, 4), true);
                        playerHoldTime.put(player.getUniqueId(), startNewestLine(player, currentTime));
                    }
                }
                else{
                    player.playSound(player.getLocation(), Sound.VILLAGER_NO, 1, 1);
                }
            }
        }.runTask(WoolbattlePerks_1.getInstance());

    }

    private static Object[] startNewestLine(Player player, long currentTime){
        Location blockLocation = newBlockLocation(player.getLocation());
        placeNewBlock(blockLocation, player);

        Object[] usageInfo = new Object[3];

        usageInfo[0] = currentTime;
        usageInfo[1] = newBlockLocation(blockLocation);
        usageInfo[2] = (int) 1;

        return usageInfo;
    }

    private static Object[] startNewLine(Player player, long currentTime){
        Location blockLocation = newBlockLocation(player.getLocation());
        placeNewBlock(blockLocation, player);

        Object[] usageInfo = new Object[3];

        usageInfo[0] = currentTime;
        usageInfo[1] = newBlockLocation(blockLocation);
        usageInfo[2] = (int) playerHoldTime.get(player.getUniqueId())[2]+1;

        return usageInfo;
    }

    private static Object[] continueLine(Player player, long currentTime){
        Location blockLocation = (Location) playerHoldTime.get(player.getUniqueId())[1];
        placeNewBlock(blockLocation, player);

        Object[] usageInfo = new Object[3];
        usageInfo[0] = currentTime;
        usageInfo[1] = newBlockLocation(blockLocation);
        usageInfo[2] = (int) playerHoldTime.get(player.getUniqueId())[2]+1;

        return usageInfo;
    }
    private static void removeBlockLater(Location blockLocation){
        new BukkitRunnable() {
            @Override
            public void run() {
                Block block = blockLocation.getBlock();
                if(block.getType()==Material.WOOL && block.hasMetadata("perkplacedblock")){
                    block.setType(Material.AIR);
                }
            }
        }.runTaskLater(WoolbattlePerks_1.getInstance(), 40L);
    }
    private static void placeNewBlock(Location blockLocation, Player player){
        new BukkitRunnable(){

            @Override
            public void run() {
                if(blockLocation.getBlock().getType()==Material.AIR){
                    blockLocation.getBlock().setType(Material.WOOL);

                    Block block = blockLocation.getBlock();
                    placeBlock(block, player);
                    block.setMetadata("perkplacedblock", new FixedMetadataValue(WoolbattlePerks_1.getInstance(), true));

                }
            }
        }.runTask(WoolbattlePerks_1.getInstance());
    }
    private static Location newBlockLocation(Location location){

        float yaw = location.getYaw();
        yaw = (yaw + 360) % 360;

        // Определяем сторону света
        if (yaw >= 315 || yaw < 45) {
            //Bukkit.broadcastMessage("z+1");
            location.setZ(location.getZ()+1);

        } else if (yaw >= 45 && yaw < 135) {
            //Bukkit.broadcastMessage("x-1");
            location.setX(location.getX()-1);

        } else if (yaw >= 135 && yaw < 225) {
            //Bukkit.broadcastMessage("z-1");
            location.setZ(location.getZ()-1);

        } else {
            //Bukkit.broadcastMessage("x+1");
            location.setX(location.getX()+1);
        }

        return location;
    }
}
