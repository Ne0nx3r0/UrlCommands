package com.ne0nx3r0.UrlCommands;

import com.ne0nx3r0.UrlManager.events.UrlResponseEvent;
import java.util.Map;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
 
public class UrlCommandsUrlListener implements Listener{
    UrlCommands plugin;
    
    public UrlCommandsUrlListener(UrlCommands p){
        this.plugin = p;
    }
    
    @EventHandler
    public void OnUrlResponse(UrlResponseEvent e){
        if(plugin.urlCommandsList.containsKey(e.getUrlCallName())){
            UrlCommand uc = plugin.urlCommandsList.get(e.getUrlCallName());
            
            if(uc.showPlayerResult()){
                plugin.msg(e.getSender(), e.getPlainTextResult());
            }
            
            if(uc.hasPlayerCommand() || uc.hasConsoleCommand()){
                Map<String,String> json = e.getJSONResult();
                
                if(uc.hasPlayerCommand()){
                    String sPlayerCommand = uc.getPlayerCommand();
                    
                    for(String sKey : json.keySet()){
                        sPlayerCommand = sPlayerCommand.replace("%"+sKey+"%",json.get(sKey));
                    }
                    
                    Bukkit.dispatchCommand(
                            Bukkit.getConsoleSender(),
                            sPlayerCommand
                    );
                }

                if(uc.hasConsoleCommand()){
                    String sConsoleCommand = uc.getConsoleCommand();

                    for(String sKey : json.keySet()){
                        sConsoleCommand = sConsoleCommand.replace("%"+sKey+"%",json.get(sKey));
                    }
                    
                    Bukkit.dispatchCommand(
                            Bukkit.getConsoleSender(),
                            sConsoleCommand
                    );
                } 
            }
        }    
    }
}