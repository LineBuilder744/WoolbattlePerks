package me.mishiko_kun_phd50.woolbattleperks1;

import me.mishiko_kun_phd50.woolbattleperks1.commands.GetPerks;
import me.mishiko_kun_phd50.woolbattleperks1.perks.perksEvents.*;
import org.bukkit.Bukkit;

import org.bukkit.plugin.java.JavaPlugin;


public final class WoolbattlePerks_1 extends JavaPlugin {

    private static WoolbattlePerks_1 instance;

    @Override
    public void onEnable() {
        instance = this;
        getCommand("getperk").setExecutor(new GetPerks());

        Bukkit.getPluginManager().registerEvents(new Events(), this);
        Bukkit.getPluginManager().registerEvents(new DoubleJump(), this);
        Bukkit.getPluginManager().registerEvents(new ActivePerksEvents(), this);
        Bukkit.getPluginManager().registerEvents(new PassivePerksEvents(), this);
        Bukkit.getPluginManager().registerEvents(new ProjectilesEvents(), this);


    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    public static WoolbattlePerks_1 getInstance(){
        return instance;
    }
}
