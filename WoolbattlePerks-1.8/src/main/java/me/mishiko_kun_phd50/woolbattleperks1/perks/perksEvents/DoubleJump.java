package me.mishiko_kun_phd50.woolbattleperks1.perks.perksEvents;

import me.mishiko_kun_phd50.woolbattleperks1.WoolbattlePerks_1;
import me.mishiko_kun_phd50.woolbattleperks1.perks.Perks;

import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class DoubleJump implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        player.setAllowFlight(true);
    }

    @EventHandler
    public void onTeleport(PlayerTeleportEvent event){
        if(event.getCause() == PlayerTeleportEvent.TeleportCause.COMMAND || event.getCause() == PlayerTeleportEvent.TeleportCause.PLUGIN){
            event.getPlayer().setAllowFlight(true);
        }
    }
    @EventHandler
    public void onDoubleJump(PlayerToggleFlightEvent event) {
        Player player = event.getPlayer();

        double multyplier = 1.2;

        if(player.getInventory().contains(Perks.ROCKETJUMP.getPerkItem())){
            multyplier = 1.6;
        }
        if (player.getGameMode() == GameMode.SURVIVAL) {
            event.setCancelled(true);

            player.playSound(player.getLocation(), Sound.BLAZE_HIT, 1, 1);
            throwPlayerUp(player, multyplier);
            player.setAllowFlight(false);
            player.setFoodLevel(10);

            new BukkitRunnable() {
                @Override
                public void run() {
                    player.setAllowFlight(true);
                }
            }.runTaskLater(WoolbattlePerks_1.getInstance(), 80L);

        }

    }
    private static void throwPlayerUp(Player player, double power) {
        Vector velocity = player.getVelocity();
        velocity.setY(power); // Устанавливаем скорость по оси Y
        player.setVelocity(velocity); // Устанавливаем новую скорость игроку
    }
}
