
package net.frostalf.frostmanager;

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
    
    public PluginInfo(String name){
        this.name = pm.getPlugin(name).getName();
        this.version = pm.getPlugin(name).getDescription().getVersion();
        this.description = pm.getPlugin(name).getDescription().getDescription();
        this.enabled = pm.getPlugin(name).isEnabled();
    }
    
    public String getName(){
        return this.name;
    }
    
    public String getVersion(String name){
        return this.version;
    }
    
    public String getDescription(){
        return this.description;
    }
    
    public boolean isEnabled(String name){
        return this.enabled;
    }

}
