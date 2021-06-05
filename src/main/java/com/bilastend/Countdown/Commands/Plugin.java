package com.bilastend.Countdown.Commands;

import com.bilastend.Countdown.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Plugin implements CommandExecutor {

    public Main getPlugin(){
        return Main.getInstance();
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if(args.length == 0) {
            commandSender.sendMessage(getHelp());
            return true;
        }

        switch (args[0]){
            case "start" -> {
                getPlugin().getTimeManager().start();
                getPlugin().getServer().broadcastMessage("§aChallenge gestartet");
                return true;
            }
            case "stop" -> {
                getPlugin().getTimeManager().stop();
                getPlugin().getServer().broadcastMessage("§cChallenge gestoppt");
                getPlugin().getBar().getBar().setTitle("Challenge pausiert...");
                return true;
            }
            case "set" -> {
                try {
                    getPlugin().getTimer().setTime(Integer.parseInt(args[1]));
                    getPlugin().getServer().broadcastMessage(String.format("§aChallenge Timer auf %d Sekunden gesetzt",Integer.parseInt(args[1])));
                    return true;
                }catch (NumberFormatException | ArrayIndexOutOfBoundsException e){
                    commandSender.sendMessage(getHelp());
                    return true;
                }
            }
            case "reset" -> {
                getPlugin().getTimeManager().stop();
                getPlugin().getBar().getBar().setTitle("Challenge pausiert...");
                getPlugin().getServer().broadcastMessage("§cChallenge resetted");
                getPlugin().getTimer().setTime(getPlugin().getTimeManager().getStartSeconds());
                return true;
            }

            case "change" -> {
                try{
                    switch (args[1]){
                        case "hostile" -> {
                            getPlugin().getTimeManager().setHostileMobSeconds(Integer.parseInt(args[2]));
                            getPlugin().getServer().broadcastMessage(String.format("§aZusätzliche Sekunden für das töten von aggressiven Mobs auf %d Sekunden gesetzt",Integer.parseInt(args[2])));
                            return true;
                        }
                        case "passive" -> {
                            getPlugin().getTimeManager().setPassiveMobSeconds(Integer.parseInt(args[2]));
                            getPlugin().getServer().broadcastMessage(String.format("§aZusätzliche Sekunden für das töten von passiven Mobs auf %d Sekunden gesetzt",Integer.parseInt(args[2])));
                            return true;
                        }
                        case "start" -> {
                            getPlugin().getTimeManager().setStartSeconds(Integer.parseInt(args[2]));
                            getPlugin().getServer().broadcastMessage(String.format("§aZeit zum Start der Challenge auf %d Sekunden gesetzt",Integer.parseInt(args[2])));
                            return true;
                        }
                        case "damage" -> {
                            getPlugin().getTimeManager().setDamageSeconds(Integer.parseInt(args[2]));
                            getPlugin().getServer().broadcastMessage(String.format("§aZeit Verlust bei Schaden auf %d Sekunden gesetzt",Integer.parseInt(args[2])));
                            return true;
                        }
                    }
                }catch (NumberFormatException | ArrayIndexOutOfBoundsException e){
                    commandSender.sendMessage(getHelp());
                }
            }
            default -> commandSender.sendMessage(getHelp());
        }

        return false;
    }

    public String getHelp(){
        return "§c/§ach start - §cStarte die Challenge\n/§ach stop - §cStoppe die Challenge\n/§ach set §9[num] - §cVerbleibende Zeit auf §9[num] setzen\n§c/§ach reset - §cResette die  Challenge\n/§ach change §9[hostile/passive/start/damage] §b[num] - §cZusätzliche Zeit beim töten eines aggressiven Mobs oder einem passiven/neutralen Mob einstellen, Timer zu beginn der Challenge einstellen or Zeitabzug für Schaden einstellen";
    }
}
