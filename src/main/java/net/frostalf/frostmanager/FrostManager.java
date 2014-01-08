
package net.frostalf.frostmanager;

import java.util.HashMap;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author Frostalf
 */
public class FrostManager extends JavaPlugin {

    public HashMap<String, PluginInfo> plugins = new HashMap<>();
    private HashMap<Plugin, String> pluginNames = new HashMap<>();
    public PluginManager pm = this.getServer().getPluginManager();
    
    @Override
    public void onEnable(){
        //listener.registerEvents();
        this.getCommand("fmdisable").setExecutor(new Commands(this));
        this.getCommand("fmversion").setExecutor(new Commands(this));
        this.getCommand("fminfo").setExecutor(new Commands(this));
        this.getCommand("fmenable").setExecutor(new Commands(this));
        this.cacheData();
    }
    
    @Override
    public void onDisable(){
        
    }
    
    public void addPlugin(String plugin, PluginInfo plugininfo){
        this.plugins.put(plugin, plugininfo);
    }
    
    public void cacheData(){
        for(Plugin plugin : pm.getPlugins()){
            if(plugin.getName().equals(this.getName())){
                continue;
            }
            PluginInfo pluginInfo = new PluginInfo(plugin, plugin.getName().toLowerCase(), plugin.getDescription().getVersion(), plugin.getDescription().getDescription(), plugin.isEnabled());
            this.addPlugin(plugin.getName().toLowerCase(), pluginInfo);
        }
    }
    
    public void recacheData(){
        this.plugins.clear();
        this.cacheData();
    }
    
    public PluginInfo getPlugin(String plugin){
        return this.plugins.get(plugin.toLowerCase());
    }
    
    public void disablePlugin(String plugin){
        pm.disablePlugin(this.getPlugin(plugin.toLowerCase()).getPlugin());
    }
    
    public void enablePlugin(String plugin){
        pm.enablePlugin(this.getPlugin(plugin).getPlugin());
    }
}
