package com.ne0nx3r0.UrlCommands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class UrlCommandsCommandExecutor implements CommandExecutor {
    private UrlCommands plugin;
    
    public UrlCommandsCommandExecutor(UrlCommands plugin){
        this.plugin = plugin;
    }
    
    @Override
    public boolean onCommand(CommandSender cs, Command cmd, String alias, String[] args) {
        Player player = null;
        
        if ((cs instanceof Player)){
            player = (Player) cs;
        }        

        if(args.length == 0){   
            plugin.msg(player, "Usage: /uc <urlCommand> <param1> <param2> etc...");
        }else{
            if(player == null
            || player.isOp()
            || player.hasPermission("UrlCommands."+args[0])
            || player.hasPermission("UrlCommands.*")){
                if(plugin.urlCommandList.containsKey(args[0])
                && plugin.urlCommandList.get(args[0]).length < args.length){
                    String sUrlCommand = args[0];

                    List<String> lArgs = new ArrayList<String>(Arrays.asList(args));
                    lArgs.remove(0);
                    args = lArgs.toArray(args);

                    plugin.um.callUrl(sUrlCommand,args);
                }else{
                    String sArgs = "";
                    for(String sArg : args){
                        sArgs += " "+sArg;
                    }

                    plugin.msg(player, "Usage: /uc "+sArgs.substring(1));
                }
            }else{
                plugin.msg(player,"You do not have permission to use this command.");
            }
        }
          
        return true;
        
    }//End onCommand
}
