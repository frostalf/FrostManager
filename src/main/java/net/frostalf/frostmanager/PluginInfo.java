
package net.frostalf.frostmanager;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

/**
 *
 * @author Frostalf
 */
public class PluginInfo {
    
    private FrostManager plugin;
    
    private String name;
    private String version;
    private String description;
    private PluginManager pm = plugin.getServer().getPluginManager();
    private boolean enabled;
    
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
        return pm.getPlugin(this.name);
    }

}
