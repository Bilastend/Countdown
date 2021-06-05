package com.bilastend.Countdown.Manager;

import com.bilastend.Countdown.Main;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Manager {

    private boolean running;
    private int startSeconds;
    private int hostileMobSeconds;
    private int passiveMobSeconds;
    private int damageSeconds;

    public int getDamageSeconds() {
        return damageSeconds;
    }

    public void setDamageSeconds(int damageSeconds) {
        this.damageSeconds = damageSeconds;
    }

    public Manager(){
        startSeconds = 900;
        hostileMobSeconds = 30;
        passiveMobSeconds = 15;
        damageSeconds = 60;
        running = false;
        checkTimer();
    }

    public Main getPlugin(){
        return Main.getInstance();
    }

    public int getStartSeconds() {
        return startSeconds;
    }

    public void setStartSeconds(int startSeconds) {
        this.startSeconds = startSeconds;
    }

    public int getHostileMobSeconds() {
        return hostileMobSeconds;
    }

    public void setHostileMobSeconds(int hostileMobSeconds) {
        this.hostileMobSeconds = hostileMobSeconds;
    }

    public int getPassiveMobSeconds() {
        return passiveMobSeconds;
    }

    public void setPassiveMobSeconds(int passiveMobSeconds) {
        this.passiveMobSeconds = passiveMobSeconds;
    }

    public void checkTimer(){
        new BukkitRunnable(){

            @Override
            public void run() {
                if(!running)return;
                if(getPlugin().getTimer().getTime() == 0){
                    killAll();
                    getPlugin().getServer().broadcastMessage("§4Die Zeit ist abgelaufen");
                }
            }
        }.runTaskTimer(Main.getInstance(),0,20);
    }


    public void killAll(){
            for (Player player: Bukkit.getOnlinePlayers()) {
                player.setHealth(0);
                getPlugin().getBar().getBar().setTitle("Zeit abgelaufen");
            }
            reset();
    }

    public void setSpectator(){
        for (Player player: Bukkit.getOnlinePlayers()
             ) {
            player.setGameMode(GameMode.SPECTATOR);
        }
    }

    public void stop(){
        running = false;
        getPlugin().getBar().getBar().setTitle("Challenge pausiert...");

    }

    public void start(){
        running = true;
    }

    public boolean isRunning() {
        return running;
    }

    public void reset(){
        stop();
        getPlugin().getTimer().setTime(getStartSeconds());
        getPlugin().getBar().getBar().setTitle("Challenge pausiert...");
    }


}
