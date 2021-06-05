package com.bilastend.Countdown.Listener;

import com.bilastend.Countdown.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;


public class EntityKill implements Listener {

    public Main getPlugin(){
        return Main.getInstance();
    }

    @EventHandler(ignoreCancelled = true)
    public void onEntityDeath(EntityDeathEvent event) {
        if(!getPlugin().getTimeManager().isRunning())return;

        if(event.getEntity() instanceof EnderDragon){
            String s;
            if(Bukkit.getOnlinePlayers().size() > 1) s = "Ihr habt"; else s = "Du hast";
            getPlugin().getServer().broadcastMessage(String.format("Der Enderdrache ist besiegt! %s es geschafft!", s));
            getPlugin().getTimeManager().reset();
        }

        if(event.getEntity().getKiller() instanceof Player){
            int current = getPlugin().getTimer().getTime();

            if(event.getEntity() instanceof Monster){
                int hostileMobKill = getPlugin().getTimeManager().getHostileMobSeconds();
                getPlugin().getTimer().setTime(current+hostileMobKill);
            }else {
                int passiveMobKill = getPlugin().getTimeManager().getPassiveMobSeconds();
                getPlugin().getTimer().setTime(current+passiveMobKill);
            }
        }
    }
}
