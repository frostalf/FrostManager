
package net.frostalf.frostmanager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import net.frostalf.frostmanager.listeners.FrostManagerListener;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author Frostalf
 */
public class FrostManager extends JavaPlugin {

    private FrostManagerListener listener = new FrostManagerListener(this);
    private File dataFile;
    private YamlConfiguration data;
    private ArrayList<String> pluginNames = new ArrayList<String>();
    private HashMap<Integer, PluginInfo> plugins = new HashMap<>();
    
    @Override
    public void onEnable(){
        listener.registerEvents();
    }
    
    @Override
    public void onDisable(){
        
    }    
}
