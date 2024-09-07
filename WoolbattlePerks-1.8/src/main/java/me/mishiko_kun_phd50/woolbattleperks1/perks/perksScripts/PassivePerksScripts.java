package me.mishiko_kun_phd50.woolbattleperks1.perks.perksScripts;

import me.mishiko_kun_phd50.woolbattleperks1.WoolbattlePerks_1;
import me.mishiko_kun_phd50.woolbattleperks1.perks.perksScripts.PerksScripts;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

public class PassivePerksScripts extends PerksScripts {
    protected static void onArrow(Player player, Projectile projectile, ItemStack perkInstanceReady, ItemStack perkInstanceNotReady, String arrowModifier, int arrowsToReady, int woolNeeded){

        if(player.getInventory().contains(perkInstanceReady)){

            perkInstanceNotReady.setAmount(arrowsToReady);
            player.getInventory().setItem(checkSlot(player, perkInstanceReady), perkInstanceNotReady);

            //player.getInventory().remove(perkInstanceReady);

            if(isEnoughWool(player, woolNeeded)){
                projectile.setMetadata(arrowModifier, new FixedMetadataValue(WoolbattlePerks_1.getInstance(), true));
                removeWool(player, woolNeeded);


            }
        }
        else{
            removeItemOrSetReady(player, perkInstanceNotReady, perkInstanceReady);
        }

    }
    static private int checkSlot(Player player, ItemStack item){
        int slot = 0;
        // Проверяем все слоты инвентаря
        for (int i = 0; i < player.getInventory().getSize(); i++) {
            ItemStack itemInSlot = player.getInventory().getItem(i);
            if (itemInSlot != null && itemInSlot.isSimilar(item)) {
                slot = i;
                break;
            }
        }
        return slot;
    }
}
