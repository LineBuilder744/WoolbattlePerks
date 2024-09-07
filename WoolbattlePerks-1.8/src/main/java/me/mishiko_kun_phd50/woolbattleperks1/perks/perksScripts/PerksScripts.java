package me.mishiko_kun_phd50.woolbattleperks1.perks.perksScripts;

import me.mishiko_kun_phd50.woolbattleperks1.WoolbattlePerks_1;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PerksScripts {
    public void onUsed(){}

    protected static ItemStack newPerkItem(Material material, String name, boolean isReady){
        ItemStack perkItem = new ItemStack(material, 1);
        ItemMeta meta = perkItem.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + name);
        if(isReady){
            meta.addEnchant(Enchantment.DURABILITY, 1, true);
        }
        if(isReady){
            List<String> lore = new ArrayList<>();
            lore.add("§k"+name.toUpperCase());

            meta.setLore(lore);
        }

        perkItem.setItemMeta(meta);

        return perkItem;
    }

    protected static ItemStack newPerkItem(Material readyMaterial, Material reloadingMaterial, String name, boolean isReady){
        ItemStack perkItem;

        if(isReady){
            perkItem = new ItemStack(readyMaterial, 1);
        }
        else{
            perkItem = new ItemStack(reloadingMaterial, 1);
        }
        ItemMeta meta = perkItem.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + name);
        if(isReady){
            meta.addEnchant(Enchantment.DURABILITY, 1, true);
        }

        if(isReady){
            List<String> lore = new ArrayList<>();
            lore.add("§k"+name.toUpperCase());

            meta.setLore(lore);
        }

        perkItem.setItemMeta(meta);

        return perkItem;
    }
    protected static ItemStack newPerkItem(Material readyMaterial, Short id, Material reloadingMaterial, String name, boolean isReady){
        ItemStack perkItem;

        if(isReady){
            perkItem = new ItemStack(readyMaterial, 1, id);
        }
        else{
            perkItem = new ItemStack(reloadingMaterial, 1);
        }
        ItemMeta meta = perkItem.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + name);
        if(isReady){
            meta.addEnchant(Enchantment.DURABILITY, 1, true);
        }
        if(isReady){
            List<String> lore = new ArrayList<>();
            lore.add("§k"+name.toUpperCase());

            meta.setLore(lore);
        }
        perkItem.setItemMeta(meta);

        return perkItem;
    }
    protected static ItemStack newPassivePerkItem(Material material, String name, boolean isReady){
        ItemStack perkItem;

        perkItem = new ItemStack(material, 1);

        ItemMeta meta = perkItem.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + name);
        if(isReady){
            meta.addEnchant(Enchantment.DURABILITY, 1, true);
        }

        List<String> lore = new ArrayList<>();
        lore.add("§k"+name.toUpperCase());

        meta.setLore(lore);

        perkItem.setItemMeta(meta);

        return perkItem;
    }
    protected static void placeBlock(Block block, Player player) {
        BlockPlaceEvent event = new BlockPlaceEvent(block, block.getState(), block.getRelative(BlockFace.DOWN), new ItemStack(block.getType()), player, true);

        // Вызываем событие установки блока
        Bukkit.getPluginManager().callEvent(event);
    }
    public static void removeItemOrSetReady(Player player, ItemStack item, ItemStack itemReady) {

        new BukkitRunnable() {
            @Override
            public void run() {
                int count = itemCounter(player, item);
                // Перебираем все предметы в инвентаре игрока
                for (int i=0; i < player.getInventory().getContents().length; i++) {
                    ItemStack stack = player.getInventory().getContents()[i];
                    if (stack != null && stack.isSimilar(item)) {
                        // Убираем одну штуку предмета
                        stack.setAmount(stack.getAmount() - 1);
                        count--;

                        if (count == 1) {
                            player.getInventory().setItem(i, itemReady);
                        }
                        break;
                    }
                }
            }
        }.runTaskAsynchronously(WoolbattlePerks_1.getInstance());

    }
    protected static void removeWool(Player player, int woolNeeded) {
        player.getInventory().removeItem(new ItemStack(Material.WOOL, woolNeeded));
    }

    public static boolean isEnoughWool(Player player, int woolNeeded) {
        return player.getInventory().contains(Material.WOOL, woolNeeded);
    }

    private static int itemCounter(Player player, ItemStack item){
        int count = 0;

        for (ItemStack stack : player.getInventory().getContents()) {
            if (stack != null && stack.isSimilar(item)) {
                count += stack.getAmount();
            }
        }

        return count;
    }
    protected static void onCooldown(Player player, int slot, ItemStack perkItem, ItemStack perkItemReady, short cooldownTime){
        perkItem.setAmount(cooldownTime);
        player.getInventory().setItem(slot, perkItem);

        perkItem.setAmount(1);

        new BukkitRunnable() {
            short count = cooldownTime;
            @Override
            public void run() {
                removeItemOrSetReady(player, perkItem, perkItemReady);
                count--;
                if(count<=0){
                    cancel();
                }
            }
        }.runTaskTimerAsynchronously(WoolbattlePerks_1.getInstance(), 0L, 20L); // Запускаем каждую секунду (20 тиков)
    }
}
