
package net.frostalf.frostmanager;

import org.bukkit.plugin.Plugin;

/**
 *
 * @author Frostalf
 */
public class PluginInfo {

    private final String name;
    private final String version;
    private final String description;
    private final boolean enabled;
    private final Plugin plugin;

    public PluginInfo(Plugin plugin, String name, String version, String description, boolean enabled){
        this.plugin = plugin;
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
        return this.plugin;
    }
}
