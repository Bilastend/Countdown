package com.bilastend.Countdown.Listener;

import com.bilastend.Countdown.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class OnJoin implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onPlayerJoin(PlayerJoinEvent event) {
        Main.getInstance().getBar().addPlayer(event.getPlayer());
    }
}
