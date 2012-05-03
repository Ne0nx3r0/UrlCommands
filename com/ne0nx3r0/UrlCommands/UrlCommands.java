package com.ne0nx3r0.UrlCommands;

import com.ne0nx3r0.UrlManager.UrlManager;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;
import java.util.logging.Level;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class UrlCommands extends JavaPlugin{
    UrlManager um;
    
    @Override
    public void onEnable(){
        
    //Setup urlCalls
        um = (UrlManager) getServer().getPluginManager().getPlugin("UrlManager");
        
        loadUrlCalls();

    //Register uc command
        getCommand("uc").setExecutor(new UrlCommandsCommandExecutor(this));

    //Register listener
        getServer().getPluginManager().registerEvents(new UrlCommandsUrlListener(this), this);        
    }
    
//Load URLs

    public void loadUrlCalls(){        
        File configFile = new File(getDataFolder(), "urls.yml");   
        
        if(!configFile.exists()){
            configFile.getParentFile().mkdirs();
            copy(getResource("urls.yml"), configFile);
        }

        FileConfiguration yml = YamlConfiguration.loadConfiguration(configFile);

        Set<String> urlsList = yml.getKeys(false);
        
        if(urlsList.isEmpty()){
            log("No urls found in "+configFile.getName());
        }
        
        for(String sUrlCommand : urlsList){
            List<String> tempParams = new ArrayList<String>();
            Map<String,String> tempData = new HashMap<String,String>();
            
            String sUrlAddress = yml.getString(sUrlCommand+".url");
            
            String sType = yml.getString(sUrlCommand+".type");
            if(sType == null){
                sType = "GET";
            }else{
                sType = sType.toUpperCase();
            }

            String sParams = yml.getString(sUrlCommand+".params");
            
            if(sParams != null){
                tempParams.addAll(Arrays.asList(sParams.split(",")));
            }

            um.addUrlCall(
                sUrlCommand,
                sUrlAddress,
                sType,
                tempParams.toArray(new String[tempParams.size()])
            );
            
            ConfigurationSection csTempData = yml.getConfigurationSection(sUrlCommand+".data");
            
            if(csTempData != null){
                for(String sKey : csTempData.getKeys(false)){
                    um.addUrlCallData(sUrlCommand, sKey, csTempData.getString(sKey));
                }
            }
        }
    }
    
    private void copy(InputStream in, File file) {
        try {
            OutputStream out = new FileOutputStream(file);
            byte[] buf = new byte[1024];
            int len;
            while((len=in.read(buf))>0){
                out.write(buf,0,len);
            }
            out.close();
            in.close();
        } catch (Exception e) {
        }
    }
    
//Generic wrappers for console messages
    protected void log(Level level,String sMessage){
        if(!sMessage.equals(""))
            getLogger().log(level,sMessage);
    }
    protected void log(String sMessage){
        log(Level.INFO,sMessage);
    }
    protected void error(String sMessage){
        log(Level.WARNING,sMessage);
    }
    
    protected void msg(Player p,String message){
        if(p != null){
            p.chat("[UC] "+message);
        }else{
            log(message);
        }
    }
}
