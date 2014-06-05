package us.axe2760.coffee;

import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;

import java.util.ArrayList;
import java.util.List;

public class Util {
    public static ItemStack addLoreLine(ItemStack item, String line){
        ItemMeta meta = item.getItemMeta();
        meta.getLore().add(line);
        ItemStack out = item.clone();
        out.setItemMeta(meta);
        return out;
    }

    public static void addSpiceToItem(ItemStack item, Spice spice){
        PotionMeta meta = (PotionMeta)item.getItemMeta();

        meta.addCustomEffect(spice.getPotionEffect(), false);
        List<String> lore = meta.getLore();
        lore.add(spice.getColor() + spice.getName());
        meta.setLore(lore);
        meta.setDisplayName(ChatColor.GOLD + "Coffee");
        item.setItemMeta(meta);
    }
}
