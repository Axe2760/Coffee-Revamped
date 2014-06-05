package us.axe2760.coffee;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.FurnaceSmeltEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class CoffeeListener implements Listener {
    public static IconMenu spiceMenu;
    private Plugin plugin;

    public int getInventorySize(int max) {
        if (max <= 0) return 9;
        int quotient = (int)Math.ceil(max / 9.0);
        return quotient > 5 ? 54: quotient * 9;
    }

    public CoffeeListener(Plugin plugin){
        this.plugin = plugin;
        spiceMenu = new IconMenu("Add a spice!", getInventorySize(SpiceFactory.spices.size()), new IconMenu.OptionClickEventHandler() {
            @Override
            public void onOptionClick(IconMenu.OptionClickEvent event) {
                Spice spice = SpiceFactory.spices.get(event.getPosition());

                Util.addSpiceToItem(event.getPlayer().getItemInHand(), spice);
                event.setWillClose(true);
            }
        }, plugin);
        for (int i = 0; i < SpiceFactory.spices.size(); i++){
            Spice spice = SpiceFactory.spices.get(i);
            spiceMenu.setOption(i,new ItemStack(Material.GLASS_BOTTLE),spice.getName(), spice.getDescription());
        }
    }
    @EventHandler
    public void onSmelt(FurnaceSmeltEvent event){
        if (event.getSource().getType() != Material.POTION || event.getResult().getType() != Material.POTION) return;
        if (!event.getSource().hasItemMeta()) return;
        ItemMeta meta = event.getSource().getItemMeta();
        if (!meta.getDisplayName().equalsIgnoreCase(ChatColor.GOLD + "Coffee")) return;

        //we know it's coffee now
        ItemStack output = event.getSource().clone();
        meta.setDisplayName(ChatColor.LIGHT_PURPLE + "Right click to add a spice!");
        output.setItemMeta(meta);
        event.setResult(output);
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent event){
        if (event.getPlayer().getItemInHand() == null) return;
        if (event.getPlayer().getItemInHand().getType() != Material.POTION) return;
        if (!event.getPlayer().getItemInHand().hasItemMeta()) return;
        if (!event.getPlayer().getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.LIGHT_PURPLE + "Right click to add a spice!")) return;
        spiceMenu.open(event.getPlayer());
        event.setCancelled(true);
    }

    @EventHandler
    public void consumePotion(PlayerItemConsumeEvent event){
        if (!event.getItem().hasItemMeta()) return;
        if (!event.getItem().getItemMeta().hasDisplayName()) return;
        if (!event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GOLD + "Coffee")) return;

        CoffeeBase coffee = CoffeeBase.fromItemStack(event.getItem());
        Player player = event.getPlayer();
        player.addPotionEffect(Coffee.defaultPotionEffect);
        for (Spice spice : coffee.spices){
            player.addPotionEffect(spice.getPotionEffect());
            spice.getNegativeEffect().start(player);
        }

        //event.getPlayer().getInventory().removeItem(event.getItem()); //The testing server was weird
        //event.getPlayer().getInventory().addItem(new ItemStack(Material.GLASS_BOTTLE));
        //if (event.getPlayer().getGameMode() == GameMode.CREATIVE) event.setCancelled(true);
    }

}
