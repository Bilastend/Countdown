package com.bilastend.Countdown.Countdown;

import com.bilastend.Countdown.Main;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Bar {

    private BossBar bar;

    public Main getPlugin(){
        return Main.getInstance();
    }

    public void addPlayer(Player player){
        bar.addPlayer(player);
    }

    public BossBar getBar(){
        return bar;
    }

    public void createBar(){
        bar = Bukkit.createBossBar("Challenge pausiert...", BarColor.PINK, BarStyle.SOLID);
        bar.setVisible(true);
        cast();
    }

    public double getProgress(){
        double p = getPlugin().getTimer().getTime()/(float)getPlugin().getTimeManager().getStartSeconds();
        if(p > 1){
            return 1.0;
        }else {
            return p;
        }
    }

    public boolean isRunning(){
        return Main.getInstance().getTimeManager().isRunning();
    }

    public void cast(){
        new BukkitRunnable() {
            @Override
            public void run() {
                if(!isRunning())return;
                double p = getProgress();
                if(p > -1){
                    bar.setProgress(p);
                    bar.setTitle("Verbleibende Zeit: " + getPlugin().getTimer().getTimeFormat());
                }
            }
        }.runTaskTimer(getPlugin(),0,20);
    }


}
