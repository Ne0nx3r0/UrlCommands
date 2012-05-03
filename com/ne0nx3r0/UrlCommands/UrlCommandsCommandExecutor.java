package com.ne0nx3r0.UrlCommands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
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
            if(args[0].equalsIgnoreCase("time")){
                plugin.um.callUrl("time");
            }else if(args.length == 2 && args[0].equalsIgnoreCase("md5")){
                plugin.um.callUrl("md5", args[1]);
            }
        }
          
        return true;
        
    }//End onCommand
}
