package com.bilastend.Countdown.Listener;

import com.bilastend.Countdown.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class onHit implements Listener {


    @EventHandler(ignoreCancelled = true)
    public void onEntityDamage(EntityDamageEvent event) {
        if(event.getFinalDamage() == 0 || !Main.getInstance().getTimeManager().isRunning()) return;

        if(event.getEntity() instanceof Player){
            int current = Main.getInstance().getTimer().getTime();
            
            Main.getInstance().getTimer().setTime(current-Main.getInstance().getTimeManager().getDamageSeconds());
            event.getEntity().getServer().broadcastMessage(((Player) event.getEntity()).getDisplayName() + " hat " + String.format("%.2f",event.getFinalDamage()) + " Herzen Schaden genommen");
        }
    }
}
