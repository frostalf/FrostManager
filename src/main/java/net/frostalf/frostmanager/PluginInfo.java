
package net.frostalf.frostmanager;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

/**
 *
 * @author Frostalf
 */
public class PluginInfo {
    
    private FrostManager plugin;
    public PluginInfo(FrostManager plugin){
        this.plugin = plugin;
    }
    
    public String name;
    public String version;
    public String description;
    public boolean enabled;
    
    public PluginInfo(String name, String version, String description, boolean enabled){
        this.name = name;
        this.version = version;
        this.description = description;
        this.enabled = enabled;
    }
    
    public String getName(){
        return this.name;
    }
    
    public String getVersion(){
        return this.version;
    }
    
    public String getDescription(){
        return this.description;
    }
    
    public boolean isEnabled(){
        return this.enabled;
    }
    
    public Plugin getPlugin(){
        PluginManager pm = plugin.getServer().getPluginManager();
        return pm.getPlugin(this.name);
    }

}
