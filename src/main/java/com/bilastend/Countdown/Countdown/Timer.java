package com.bilastend.Countdown.Countdown;

import com.bilastend.Countdown.Main;
import org.bukkit.scheduler.BukkitRunnable;

import java.time.LocalTime;

public class Timer {
    private int time;

    public Timer(){
      time = Main.getInstance().getTimeManager().getStartSeconds();
      run();
    }

    public boolean isRunning() {
        return Main.getInstance().getTimeManager().isRunning();
    }

    public int getTime() {
        return time;
    }

    public String getTimeFormat(){
        LocalTime time = LocalTime.ofSecondOfDay(getTime());
        return time.toString();
    }

    public void setTime(int time) {
        if(time < 0) time = 0;
        this.time = time;
    }

    private void run(){
        new BukkitRunnable() {

            @Override
            public void run() {
                if(!isRunning()){
                    return;
                }
                if(getTime() > -1)setTime(getTime()-1);
            }
        }.runTaskTimer(Main.getInstance(),0,20);
    }

}
