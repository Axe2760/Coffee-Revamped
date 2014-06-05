package us.axe2760.coffee;

import org.bukkit.ChatColor;
import org.bukkit.potion.PotionEffect;

public class Spice {
    private String name;
    private PotionEffect potionEffect;
    private Effects negativeEffect;
    private ChatColor color;
    private String description;

    public Spice(String name, PotionEffect potionEffect, Effects negativeEffect) {
        this.name = name;
        this.potionEffect = potionEffect;
        this.negativeEffect = negativeEffect;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PotionEffect getPotionEffect() {
        return potionEffect;
    }

    public void setPotionEffect(PotionEffect potionEffect) {
        this.potionEffect = potionEffect;
    }

    public Effects getNegativeEffect() {
        return negativeEffect;
    }

    public void setNegativeEffect(Effects negativeEffect) {
        this.negativeEffect = negativeEffect;
    }

    public ChatColor getColor() {
        return color;
    }

    public void setColor(ChatColor color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
