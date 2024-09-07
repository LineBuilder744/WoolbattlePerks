package me.mishiko_kun_phd50.woolbattleperks1.commands;

import me.mishiko_kun_phd50.woolbattleperks1.perks.perksScripts.activePerksScripts.*;
import me.mishiko_kun_phd50.woolbattleperks1.perks.perksScripts.passivePerksScripts.*;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GetPerks implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player){
            Player player = (Player) commandSender;
            if(player.isOp()){
                Location location = player.getLocation();
                World world = player.getWorld();

                if(strings[0].equalsIgnoreCase("SafetyPlatform")){
                    world.dropItem(location, SafetyPlatform.getPerk(true));
                }
                else if(strings[0].equalsIgnoreCase("SafetyCapsule")){
                    world.dropItem(location, SafetyCapsule.getPerk(true));
                }
                else if(strings[0].equalsIgnoreCase("Booster")){
                    world.dropItem(location, Booster.getPerk(true));
                }
                else if(strings[0].equalsIgnoreCase("Rope")){
                    world.dropItem(location, Rope.getPerk(true));
                }
                else if(strings[0].equalsIgnoreCase("WallBuilder")){
                    world.dropItem(location, WallBuilder.getPerk(true));
                }
                else if(strings[0].equalsIgnoreCase("switcher")){
                    world.dropItem(location, Switcher.getPerk(true));
                }
                else if(strings[0].equalsIgnoreCase("ArrowBomb")){
                    world.dropItem(location, ArrowBomb.getPerk(true));
                }
                else if(strings[0].equalsIgnoreCase("ThrowableTnT")){
                    world.dropItem(location, ThrowableTnT.getPerk(true));
                }
                else if(strings[0].equalsIgnoreCase("Linebuilder")){
                    world.dropItem(location, LineBuilder.getPerk(true));
                }
                else if(strings[0].equalsIgnoreCase("sheild")){
                    world.dropItem(location, ProtectingSheild.getPerk(true));
                }

                ///////// Passive perks

                else if(strings[0].equalsIgnoreCase("TnTArrow")){
                    world.dropItem(location, TnTArrow.getPerk(true));
                }
                else if(strings[0].equalsIgnoreCase("HookArrow")){
                    world.dropItem(location, HookArrow.getPerk(true));
                }
                else if(strings[0].equalsIgnoreCase("RocketJump")){
                    world.dropItem(location, RocketJump.getPerk());
                }
                return true;
            }

        }

        return false;
    }
}
