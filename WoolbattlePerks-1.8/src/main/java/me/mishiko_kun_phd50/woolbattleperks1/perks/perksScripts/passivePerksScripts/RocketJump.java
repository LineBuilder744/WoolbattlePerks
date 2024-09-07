package me.mishiko_kun_phd50.woolbattleperks1.perks.perksScripts.passivePerksScripts;

import me.mishiko_kun_phd50.woolbattleperks1.perks.perksScripts.PassivePerksScripts;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class RocketJump extends PassivePerksScripts {

    public static ItemStack getPerk(){
        return newPassivePerkItem(Material.RABBIT_FOOT, "RocketJump", true);
    }
}
