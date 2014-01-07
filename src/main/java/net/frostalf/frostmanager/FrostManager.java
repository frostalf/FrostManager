
package net.frostalf.frostmanager;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import net.frostalf.frostmanager.listeners.FrostManagerListener;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
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
    private FrostManager plugin;
    private PluginManager pm = plugin.getServer().getPluginManager();
    
    
    @Override
    public void onEnable(){
        listener.registerEvents();
    }
    
    @Override
    public void onDisable(){
        
    }
    
    public void cacheData(){
        for(String name : Arrays.asList(pm.getPlugins().toString())){
            this.pluginNames.add(name);
        }
    }
    
}
