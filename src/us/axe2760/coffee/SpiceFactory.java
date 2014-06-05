package us.axe2760.coffee;

import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class SpiceFactory {
    public static List<Spice> spices;
    private static Plugin instance;

    public static void setInstance(Plugin plugin){
        instance = plugin;
        spices = new ArrayList<Spice>();
    }

    public static void loadSpices(FileConfiguration config){
        ConfigurationSection sps = config.getConfigurationSection("spices");
        for (String string : sps.getKeys(false)){
            spices.add(getSpice(sps.getConfigurationSection(string)));
        }
    }

    public static Spice getSpice(ConfigurationSection spiceConfig){
        String name = spiceConfig.getName();
        String potType = spiceConfig.getString("potionEffectType");
        int potStrength = spiceConfig.getInt("potionStrength");
        int potDuration = spiceConfig.getInt("potionDuration");
        PotionEffectType type = PotionEffectType.getByName(potType);
        PotionEffect effect = new PotionEffect(type,potDuration,potStrength);

        String negPotType = spiceConfig.getString("negativePotionEffectType");
        int negPotStrength = spiceConfig.getInt("negativePotionStrength");
        int negPotDuration = spiceConfig.getInt("negativePotionDuration");

        PotionEffectType negType = PotionEffectType.getByName(negPotType);
        PotionEffect negativeEffect = new PotionEffect(negType,negPotDuration, negPotStrength);
        Effects effects = new Effects(instance, new ArrayList<CoffeeEffect>());
        effects.addEffect(new CoffeeEffect(negativeEffect, potDuration));
        Spice spice = new Spice(name,effect, effects);
        spice.setColor(ChatColor.getByChar(spiceConfig.getString("colorNumber")));

        spice.setDescription(spiceConfig.getString("description"));
        return spice;
    }

    public static List<Spice> getSpices(List<String> lore){
        if (lore == null) return null;
        List<Spice> output = new ArrayList<Spice>();
        lore.remove(0); //Remove the "Spices: " line.
        for (String line : lore){
            for (Spice spice : spices){
                if (spice.getName().equalsIgnoreCase(ChatColor.stripColor(line))){
                    output.add(spice);
                }
            }
        }
        return output;
    }
}
