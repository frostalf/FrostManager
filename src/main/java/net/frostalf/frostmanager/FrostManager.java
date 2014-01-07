
package net.frostalf.frostmanager;

import java.util.ArrayList;
import java.util.HashMap;
import net.frostalf.frostmanager.listeners.FrostManagerListener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author Frostalf
 */
public class FrostManager extends JavaPlugin {

    private FrostManagerListener listener = new FrostManagerListener(this);
    public HashMap<String, PluginInfo> plugins = new HashMap<>();
    public PluginManager pm = this.getServer().getPluginManager();
    
    
    @Override
    public void onEnable(){
        //listener.registerEvents();
        this.getCommand("fmdisable").setExecutor(new Commands(this));
        this.getCommand("fmversion").setExecutor(new Commands(this));
        this.getCommand("fminfo").setExecutor(new Commands(this));
        this.getCommand("fmenable").setExecutor(new Commands(this));
        cacheData();
    }
    
    @Override
    public void onDisable(){
        
    }
    
    public void addPlugin(String plugin, PluginInfo plugininfo){
        this.plugins.put(plugin, plugininfo);
    }
    
    public void cacheData(){
        Plugin[] pluginList = pm.getPlugins();
        for(Plugin plugin : pluginList){
            if(plugin == this){
                continue;
            }
            String name = plugin.getName();
            PluginInfo pluginInfo = new PluginInfo(name, getVersion(name), getDescription(name), isEnabled(name));
            this.addPlugin(name, pluginInfo);
        }
    }
    
    public void recacheData(){
        this.plugins.clear();
        this.cacheData();
    }
    
    public PluginInfo getPlugin(String plugin){
        return this.plugins.get(plugin);
    }
    
    public PluginInfo getPlugin(Plugin plugin){
        return this.plugins.get(plugin.getName());
    }
    
    public String getVersion(String name){
        return pm.getPlugin(name).getDescription().getVersion();
    }
    
    public String getDescription(String name){
        return pm.getPlugin(name).getDescription().getDescription();
    }
    
    public boolean isEnabled(String name){
        return pm.getPlugin(name).isEnabled();
    }
}
