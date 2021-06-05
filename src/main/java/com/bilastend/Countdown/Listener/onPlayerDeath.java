package com.bilastend.Countdown.Listener;

import com.bilastend.Countdown.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class onPlayerDeath implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onEntityDeath(EntityDeathEvent event) {
        if(!getPlugin().getTimeManager().isRunning())return;

        if(event.getEntity() instanceof Player){
            getPlugin().getTimeManager().reset();
            getPlugin().getTimeManager().setSpectator();
        }
    }

    public Main getPlugin(){
        return Main.getInstance();
    }
}
