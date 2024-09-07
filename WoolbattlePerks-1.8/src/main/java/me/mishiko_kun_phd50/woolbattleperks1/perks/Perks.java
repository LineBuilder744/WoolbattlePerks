package me.mishiko_kun_phd50.woolbattleperks1.perks;

import me.mishiko_kun_phd50.woolbattleperks1.perks.perksScripts.activePerksScripts.*;
import me.mishiko_kun_phd50.woolbattleperks1.perks.perksScripts.passivePerksScripts.HookArrow;
import me.mishiko_kun_phd50.woolbattleperks1.perks.perksScripts.passivePerksScripts.RocketJump;
import me.mishiko_kun_phd50.woolbattleperks1.perks.perksScripts.passivePerksScripts.TnTArrow;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public enum Perks {

    // Active Perks
    ARROWBOMB("ArrowBomb", ArrowBomb.getPerk(true)),
    BOOSTER("Booster", Booster.getPerk(true)),
    SAFETYPLATFORM("SafetyPlatform", SafetyPlatform.getPerk(true)),
    SAFETYCAPSULE("SafetyCapsule", SafetyCapsule.getPerk(true)),
    SHEILD("Sheild", ProtectingSheild.getPerk(true)),
    SWITCHER("Switcher", Switcher.getPerk(true)),
    THROWABLETNT("ThrowableTnT", ThrowableTnT.getPerk(true)),
    WALLBUILDER("WallBuilder", WallBuilder.getPerk(true)),
    LINEBUILDER("LineBuilder", LineBuilder.getPerk(true)),
    ROPE("Rope", Rope.getPerk(true)),

    // Passive Perks
    ROCKETJUMP("RocketJump", RocketJump.getPerk()),
    EXPLODINGARROW("ExplodingArrow", TnTArrow.getPerk(true)),
    HOOKARROW("HookArrow", HookArrow.getPerk(true));

    private String name;
    private ItemStack perkItem;

    Perks(String name, ItemStack perkItem) {
        this.name = name;
        this.perkItem = perkItem;
    }
    public ItemStack getPerkItem(){
        return perkItem;
    }
    public static Perks getPerkFrom(ItemStack stack){
        ItemMeta meta = stack.getItemMeta();

        if(meta.getLore() == null){
            return null;
        }
        Perks perk = valueOf(meta.getLore().get(0).substring(2));

        return perk;
    }

    public void onUsed(Player player){
        if(name.equalsIgnoreCase("ArrowBomb")){
            ArrowBomb.onUsed(player, player.getInventory().getHeldItemSlot());
        }
        else if(name.equalsIgnoreCase("Booster")){
            Booster.onUsed(player, player.getInventory().getHeldItemSlot());
        }
        else if(name.equalsIgnoreCase("Rope")){
            Rope.onUsed(player, player.getInventory().getHeldItemSlot());
        }
        else if(name.equalsIgnoreCase("SafetyPlatform")){
            SafetyPlatform.onUsed(player, player.getInventory().getHeldItemSlot());
        }
        else if(name.equalsIgnoreCase("SafetyCapsule")){
            SafetyCapsule.onUsed(player, player.getInventory().getHeldItemSlot());
        }
        else if(name.equalsIgnoreCase("Sheild")){
            ProtectingSheild.onUsed(player, player.getInventory().getHeldItemSlot());
        }
        else if(name.equalsIgnoreCase("Switcher")){
            Switcher.onUsed(player, player.getInventory().getHeldItemSlot());
        }
        else if(name.equalsIgnoreCase("ThrowbaleTnT")){
            ThrowableTnT.onUsed(player, player.getInventory().getHeldItemSlot());
        }
        else if(name.equalsIgnoreCase("WallBuilder")){
            WallBuilder.onUsed(player, player.getInventory().getHeldItemSlot());
        }
        else if(name.equalsIgnoreCase("LineBuilder")){
            LineBuilder.onUsed(player, player.getInventory().getHeldItemSlot());
        }
    }
    public void onArrow(Player player, Projectile projectile){
        if(name.equalsIgnoreCase("HookArrow")){
            HookArrow.onUsed(player, projectile);
        }
        else if(name.equalsIgnoreCase("ExplodingArrow")){
            TnTArrow.onUsed(player, projectile);
        }
    }
}
