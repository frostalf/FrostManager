
package net.frostalf.frostmanager;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import net.frostalf.frostmanager.listeners.FrostManagerListener;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
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
    private ArrayList<String> pluginNames = new ArrayList<>();
    private HashMap<String, PluginInfo> plugins = new HashMap<>();
    private FrostManager plugin;
    private PluginManager pm = plugin.getServer().getPluginManager();
    
    
    @Override
    public void onEnable(){
        listener.registerEvents();
        this.getCommand("disable").setExecutor(new Commands(this));
        this.getCommand("version").setExecutor(new Commands(this));
        this.getCommand("info").setExecutor(new Commands(this));
        this.getCommand("enable").setExecutor(new Commands(this));
    }
    
    @Override
    public void onDisable(){
        
    }
    
    public void cacheData(){
        for(String currentplugins : Arrays.asList(pm.getPlugins().toString())){
            if(currentplugins != null){
                for(String Names : Arrays.asList(pm.getPlugin(currentplugins).getName())){
                    PluginInfo pluginfo = new PluginInfo(Names, pm.getPlugin(currentplugins).getDescription().getVersion(), pm.getPlugin(currentplugins).getDescription().getDescription(), pm.getPlugin(currentplugins).isEnabled());
                    plugins.put(currentplugins, pluginfo);
                }
            }
        }
    }
    
    public void reCacheData(){
        this.pluginNames.clear();
        this.cacheData();
    }
    
    public PluginInfo getPlugin(String plugin){
        return this.plugins.get(plugin);
    }
}
