package me.mishiko_kun_phd50.woolbattleperks1.perks.perksEvents;

import me.mishiko_kun_phd50.woolbattleperks1.perks.perksScripts.activePerksScripts.*;
import me.mishiko_kun_phd50.woolbattleperks1.perks.perksScripts.passivePerksScripts.*;
import org.bukkit.*;
import org.bukkit.entity.*;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.*;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;

import org.bukkit.inventory.ItemStack;


public class Events implements Listener {
    private final ItemStack[] perksList = {
            ArrowBomb.getPerk(true),
            Booster.getPerk(true),
            Rope.getPerk(true),
            SafetyPlatform.getPerk(true),
            SafetyCapsule.getPerk(true),
            Switcher.getPerk(true),
            ThrowableTnT.getPerk(true),
            WallBuilder.getPerk(true),
            // passive perks
            HookArrow.getPerk(true),
            HookArrow.getPerk(false),
            TnTArrow.getPerk(true),
            TnTArrow.getPerk(false)
    };
    private final ItemStack[] perksListOnCooldown = {
            ArrowBomb.getPerk(false),
            Booster.getPerk(false),
            Rope.getPerk(false),
            SafetyPlatform.getPerk(false),
            SafetyCapsule.getPerk(false),
            Switcher.getPerk(false),
            ThrowableTnT.getPerk(false),
            WallBuilder.getPerk(false),
            // passive perks
            HookArrow.getPerk(false),
            TnTArrow.getPerk(false)
    };





    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {

    }
    @EventHandler
    public void onDamage(EntityDamageEvent event){
        if(event.getEntity() instanceof Player){
            if(event.getCause() == EntityDamageEvent.DamageCause.FALL){
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (event.getBlock().getType() == Material.WOOL) {
            event.getPlayer().getInventory().addItem(new ItemStack(Material.WOOL, 2));

        }
    }
    @EventHandler
    public void onDropItems(PlayerDropItemEvent event){
        if(event.getItemDrop().getItemStack().getType().equals(Material.WOOL)){
            event.setCancelled(true);
        }
        else{
            for(ItemStack item : perksListOnCooldown){
                if(event.getItemDrop().getItemStack().isSimilar(item)){
                    event.setCancelled(true);
                }
            }
        }
    }
    @EventHandler
    public void onWoolDrop(ItemSpawnEvent event){
        if(event.getEntity() != null){
            Item item = event.getEntity();

            if(item.getItemStack().getType().equals(Material.WOOL)){
                event.setCancelled(true);
            }
        }
    }
    @EventHandler
    public void onPlayerPlaceBlocks(BlockPlaceEvent event){
        Player player = event.getPlayer();

        for(ItemStack item : perksList){
            if(player.getItemInHand() == item){
                event.setCancelled(true);
                break;
            }
        }
    }
}