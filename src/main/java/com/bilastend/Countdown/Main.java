package com.bilastend.Countdown;

import com.bilastend.Countdown.Commands.Plugin;
import com.bilastend.Countdown.Countdown.Bar;
import com.bilastend.Countdown.Countdown.Timer;
import com.bilastend.Countdown.Manager.Manager;
import com.bilastend.Countdown.Listener.EntityKill;
import com.bilastend.Countdown.Listener.OnJoin;
import com.bilastend.Countdown.Listener.onHit;
import com.bilastend.Countdown.Listener.onPlayerDeath;
import com.bilastend.Countdown.utils.Config;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private static Main instance;

    private Config config;
    private Timer timer;
    private Bar bar;
    private Manager timeManager;


    @Override
    public void onLoad(){
       instance = this;
    }

    @Override
    public void onEnable() {

        PluginManager manager = Bukkit.getPluginManager();
        manager.registerEvents(new OnJoin(),this);
        manager.registerEvents(new EntityKill(),this);
        manager.registerEvents(new onHit(),this);
        manager.registerEvents(new onPlayerDeath(),this);

        getCommand("ch").setExecutor(new Plugin());

        config = new Config();
        timeManager = new Manager();
        readValues();
        timer = new Timer();
        bar = new Bar();
        bar.createBar();
    }

    @Override
    public void onDisable() {
        saveValues();
    }

    public static Main getInstance() {
        return instance;
    }

    public Timer getTimer() {
        return timer;
    }

    public Bar getBar() {
        return bar;
    }

    public Manager getTimeManager() {
        return timeManager;
    }

    public void readValues(){
        if(config.getProperties().get("startSeconds") != null) timeManager.setStartSeconds(Integer.parseInt(config.getProperties().getProperty("startSeconds")));
        if(config.getProperties().get("hostileMobSeconds") != null) timeManager.setHostileMobSeconds(Integer.parseInt(config.getProperties().getProperty("hostileMobSeconds")));
        if(config.getProperties().get("passiveMobSeconds") != null) timeManager.setPassiveMobSeconds(Integer.parseInt(config.getProperties().getProperty("passiveMobSeconds")));
        if(config.getProperties().get("damageSeconds") != null) timeManager.setDamageSeconds(Integer.parseInt(config.getProperties().getProperty("damageSeconds")));
    }

    public void saveValues(){
        config.getProperties().setProperty("startSeconds", String.valueOf(timeManager.getStartSeconds()));
        config.getProperties().setProperty("hostileMobSeconds", String.valueOf(timeManager.getHostileMobSeconds()));
        config.getProperties().setProperty("passiveMobSeconds", String.valueOf(timeManager.getPassiveMobSeconds()));
        config.getProperties().setProperty("damageSeconds", String.valueOf(timeManager.getDamageSeconds()));
        config.save();
    }


}
