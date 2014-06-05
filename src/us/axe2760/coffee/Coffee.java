package us.axe2760.coffee;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.material.MaterialData;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Coffee extends JavaPlugin {
    public static PotionEffect defaultPotionEffect;

    public void onEnable(){
        saveDefaultConfig();
        saveConfig();

        if (getConfig().getBoolean("send-enable-message")) getLogger().info( "Loading Coffee....");
        SpiceFactory.setInstance(this);
        SpiceFactory.loadSpices(this.getConfig());
        loadDefaultPotionEffect(this.getConfig().getConfigurationSection("coffeeBasePotionEffect"));

        FurnaceRecipe recipe = new FurnaceRecipe(new ItemStack(Material.POTION), Material.POTION);
        Bukkit.addRecipe(recipe);

        ItemStack result = new CoffeeBase().getItem();
        ShapelessRecipe coffeeRecipe = new ShapelessRecipe(result);

        coffeeRecipe.addIngredient(1, new MaterialData(351, (byte) 3));
        coffeeRecipe.addIngredient(1, Material.getMaterial(373));
        coffeeRecipe.addIngredient(1, Material.getMaterial(353));
        Bukkit.addRecipe(coffeeRecipe);

        Bukkit.getPluginManager().registerEvents(new CoffeeListener(this), this);
    }

    public void loadDefaultPotionEffect(ConfigurationSection cs){
        String potType = cs.getString("potionEffectType");
        int potStrength = cs.getInt("potionStrength");
        int potDuration = cs.getInt("potionDuration");
        PotionEffectType type = PotionEffectType.getByName(potType);

        defaultPotionEffect = new PotionEffect(type, potDuration, potStrength);
    }
}
