package me.mishiko_kun_phd50.woolbattleperks1.perks.perksEvents;

import me.mishiko_kun_phd50.woolbattleperks1.perks.perksScripts.activePerksScripts.ThrowableTnT;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class ProjectilesEvents implements Listener {

    private final Random random = new Random();

    @EventHandler
    public void onTNTExplode(EntityExplodeEvent event) {
        if(event.getEntity().hasMetadata("ThrowableTnT") || event.getEntity().hasMetadata("ExplodingArrow")){
            // Проверяем, что взорванное существо - это TNT

            Location explosionLocation = event.getLocation();
            Collection<Entity> affectedEntities = event.getLocation().getWorld().getNearbyEntities(explosionLocation, 5, 5, 5); // Радиус 5 блоков

            for (Entity entity : affectedEntities) {
                if (entity instanceof Player) {
                    Player player = (Player) entity;

                    // Проверяем, что игрок на мирной сложности

                    double launchPower = 2.0; // Можете изменить значение для регулировки силы подбрасывания
                    player.setVelocity(explosionLocation.toVector().subtract(player.getLocation().toVector()).normalize().multiply(launchPower).multiply(-1).setY(1.5));

                }
            }

            List<Block> blocksToRemove = new ArrayList<>();

            for (org.bukkit.block.Block block : event.blockList()) {
                // Если блок не шерсть, добавляем его в список блоков для удаления
                if (block.getType() != Material.WOOL) {
                    blocksToRemove.add(block);
                }
            }

            event.blockList().removeAll(blocksToRemove);// Устанавливаем новый список блоков для разрушения
        }
    }
    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {
        Projectile projectile = event.getEntity();

        if (projectile instanceof Snowball) {

            if(projectile.hasMetadata("throwableTnT")){
                ThrowableTnT.spawnThrowableTnT(projectile);
                //tnt.setTicksLived(10);
            }
            if(projectile.hasMetadata("arrowbomb")){
                Player shooter = (Player) projectile.getShooter();
                // Получаем местоположение попадания
                Vector hitLocation = projectile.getLocation().toVector();

                // Создаем 20 стрел, разлетающихся в форме шара
                for (int i = 0; i < 40; i++) {
                    // Генерируем случайные координаты для направления
                    double theta = random.nextDouble() * Math.PI; // Угол от 0 до π
                    double phi = random.nextDouble() * 2 * Math.PI; // Угол от 0 до 2π

                    // Преобразуем сферические координаты в декартовы
                    double x = Math.sin(theta) * Math.cos(phi);
                    double y = Math.cos(theta);
                    double z = Math.sin(theta) * Math.sin(phi);

                    Vector direction = new Vector(x, y, z).normalize(); // Нормализуем вектор

                    // Спавним стрелу в месте попадания
                    Arrow arrow = projectile.getWorld().spawn(hitLocation.toLocation(projectile.getWorld()), Arrow.class);
                    arrow.setVelocity(direction.multiply(1)); // Устанавливаем скорость стрелы
                    arrow.setShooter(shooter);
                    arrow.setKnockbackStrength(2);
                }
            }
        }
        else if(projectile instanceof Arrow){
            if(projectile.hasMetadata("ExplodingArrow")){
                ThrowableTnT.spawnThrowableTnT(projectile);
            }
            if(projectile.hasMetadata("WoolRemovingArrow")){
                int x = projectile.getLocation().getBlockX();
                int y = projectile.getLocation().getBlockY();
                int z = projectile.getLocation().getBlockZ();

                for(int ValueX=-1; ValueX < 2; ValueX++){
                    for(int ValueY=-1; ValueY < 2; ValueY++){
                        for(int ValueZ=-1; ValueZ < 2; ValueZ++){
                            projectile.getWorld().getBlockAt(x+ValueX, y+ValueY, z+ValueZ).setType(Material.AIR);
                        }
                    }
                }

            }

            projectile.remove();
        }
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {

        // Switcher logic
        if (event.getEntity() instanceof Player && event.getDamager() instanceof Projectile) {
            Player player = (Player) event.getEntity();
            Projectile damager = (Projectile) event.getDamager();

            if(player.hasMetadata("ProtectedBySheild") || damager.getShooter() == player){
                event.setCancelled(true);
            }

            else if (damager instanceof Snowball) {
                Snowball snowball = (Snowball) damager;

                if(snowball.getShooter() instanceof Player){
                    if(snowball.hasMetadata("switcher")){
                        Player shooter = (Player) snowball.getShooter();
                        event.setCancelled(true);

                        Location playerLoc = player.getLocation();
                        Location shooterLoc = shooter.getLocation();

                        shooter.teleport(playerLoc);
                        player.teleport(shooterLoc);
                    }
                }
            }
            else if (event.getDamager() instanceof Arrow) {
                Arrow arrow = (Arrow) damager;
                // hookarrow logic
                if(damager.hasMetadata("HookArrow")){

                    if(arrow.getShooter() instanceof Player){
                        Player shooter = (Player) arrow.getShooter();

                        player.teleport(shooter.getLocation());
                    }
                }
            }
        }
    }
}
