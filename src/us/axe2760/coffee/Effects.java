package us.axe2760.coffee;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.List;
import java.util.UUID;

public class Effects {
    private List<CoffeeEffect> effectsToApply;
    private Plugin plugin;

    public Effects(Plugin plugin, List<CoffeeEffect> effectsToApply){
        this.plugin = plugin;
        this.effectsToApply = effectsToApply;
    }

    public List<CoffeeEffect> getEffectsToApply() {
        return effectsToApply;
    }

    public void setEffectsToApply(List<CoffeeEffect> effectsToApply) {
        this.effectsToApply = effectsToApply;
    }

    public void addEffect(CoffeeEffect effect){
        effectsToApply.add(effect);
    }

    public void start(final Player player){

        for (final CoffeeEffect effect : effectsToApply){
            Bukkit.getScheduler().scheduleSyncDelayedTask(plugin,
            new BukkitRunnable(){
                public void run(){
                    player.addPotionEffect(effect.getEffect());
                }
            }, (long)effect.getWait());
        }
    }


}
