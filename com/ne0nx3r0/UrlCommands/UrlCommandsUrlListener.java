package com.ne0nx3r0.UrlCommands;

import com.ne0nx3r0.UrlManager.events.UrlResponseEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
 
public class UrlCommandsUrlListener implements Listener{
    UrlCommands plugin;
    
    public UrlCommandsUrlListener(UrlCommands p){
        this.plugin = p;
    }
    
    @EventHandler
    public void OnUrlResponse(UrlResponseEvent e) {
        if(plugin.urlCommandList.containsKey(e.getUrlCallName())){
            plugin.msg(e.getSender(), e.getPlainTextResult());
        }
        
    }
}