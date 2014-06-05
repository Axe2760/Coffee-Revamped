package us.axe2760.coffee;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;

import java.util.ArrayList;
import java.util.List;

public class CoffeeBase {
    public List<Spice> spices;

    //public boolean hasSugar; not yet implemented

    public CoffeeBase(){
        spices = new ArrayList<Spice>();
    }

    public ItemStack getItem(){
        ItemStack out = new ItemStack(Material.POTION, 1);
        PotionMeta meta = (PotionMeta) out.getItemMeta();
        List<String> lore = new ArrayList<String>();
        
        meta.addCustomEffect(Coffee.defaultPotionEffect, false);
        lore.add("Brew potion in a furnace to add spices!");

        if (spices.size() > 0){
            lore.add(ChatColor.LIGHT_PURPLE + "Spices: ");
        }
        for (Spice spice : spices){
            meta.addCustomEffect(spice.getPotionEffect(), false);
            lore.add(spice.getColor() + spice.getName());
        }


        meta.setDisplayName(ChatColor.GOLD + "Coffee");
        meta.setLore(lore);
        out.setItemMeta(meta);

        return out;
    }

    public void addSpice(Spice spice){
        spices.add(spice);
    }

    public static CoffeeBase fromItemStack(ItemStack coffee){
        PotionMeta coffeeMeta = (PotionMeta)coffee.getItemMeta();
        if (!coffeeMeta.getDisplayName().equalsIgnoreCase(ChatColor.GOLD + "Coffee")) return null;

        List<Spice> spices = SpiceFactory.getSpices(coffeeMeta.getLore());
        CoffeeBase base = new CoffeeBase();
        if (spices == null) return base;
        for (Spice spice : spices) base.addSpice(spice);
        return base;
    }



}
