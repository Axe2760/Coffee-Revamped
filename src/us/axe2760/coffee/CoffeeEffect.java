package us.axe2760.coffee;

import org.bukkit.potion.PotionEffect;

public class CoffeeEffect{
    public PotionEffect effect;
    public int wait;
    public String player; //TODO: outdated for 1.8

    public CoffeeEffect(PotionEffect effect, int wait) {
        this.effect = effect;
        this.wait = wait;
    }

    public PotionEffect getEffect() {
        return effect;
    }

    public void setEffect(PotionEffect effect) {
        this.effect = effect;
    }

    public int getWait() {
        return wait;
    }

    public void setWait(int wait) {
        this.wait = wait;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }
}