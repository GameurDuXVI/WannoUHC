package fr.gameurduxvi.uhc.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.pandorauhc.api.PandoraAPI;

public class CommandWhitelist implements CommandExecutor {
	
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player){
            Player p = (Player) sender;
            if (p.isOp()){
                if (args.length == 2){
                    if (args[0].equalsIgnoreCase("add")){
                        PandoraAPI.get().getProxyLink().addPlayerToWhitelist(args[1]);
                    }else if (args[1].equalsIgnoreCase("remove")){
                        PandoraAPI.get().getProxyLink().removePlayerToWhitelist(args[1]);
                    }
                }else if (args.length == 1){
                    if (args[0].equalsIgnoreCase("queueon")){
                        PandoraAPI.get().getProxyLink().setQueueStatus(true);
                    }else if (args[0].equalsIgnoreCase("queueoff")){
                        PandoraAPI.get().getProxyLink().setQueueStatus(false);
                    }else if (args[0].equalsIgnoreCase("off")){
                        PandoraAPI.get().getProxyLink().setWhitelistStatus(false);
                    }else if (args[0].equalsIgnoreCase("on")){
                        PandoraAPI.get().getProxyLink().setWhitelistStatus(true);
                    }
                }
            }
        }
        return false;
    }
}
