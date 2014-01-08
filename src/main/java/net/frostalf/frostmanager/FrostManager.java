
package net.frostalf.frostmanager;

import java.io.File;
import java.util.HashMap;
import java.util.logging.Level;
import org.bukkit.plugin.InvalidDescriptionException;
import org.bukkit.plugin.InvalidPluginException;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.UnknownDependencyException;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author Frostalf
 */
public class FrostManager extends JavaPlugin {

    private final HashMap<String, PluginInfo> plugins = new HashMap<>();
    private final HashMap<String, FileInfo> pluginFiles = new HashMap<>();
    private final PluginManager pm = this.getServer().getPluginManager();
    private final Commands command = new Commands(this);
    
    @Override
    public void onEnable(){
        this.getCommand("fmdisable").setExecutor(command);
        this.getCommand("fmenable").setExecutor(command);
        this.getCommand("fmload").setExecutor(command);
        this.getCommand("fmversion").setExecutor(command);
        this.getCommand("fminfo").setExecutor(command);
        this.getCommand("fmreload").setExecutor(command);
        this.cacheFileList();
        this.cacheData();
    }
    
    @Override
    public void onDisable(){
        
    }
    
    public void addPlugin(String plugin, PluginInfo plugininfo){
        this.plugins.put(plugin, plugininfo);
    }
    public void addPluginFile(String plugin, FileInfo fileinfo){
        this.pluginFiles.put(plugin, fileinfo);
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
    
    public void cacheFileList(){
        for(File pluginFile : getDataFolder().listFiles()){
            if(pluginFile.getName().equals(this.getName())){
                continue;
            }
            if(pluginFile.isFile()){
                continue;
            }
            FileInfo FileInfo = new FileInfo(pluginFile, pluginFile.getName());
            this.addPluginFile(pluginFile.getName().toLowerCase(), FileInfo);
        }
    }
    
    public void recacheFileList(){
        this.pluginFiles.clear();
        this.cacheFileList();
    }
    
    public FileInfo getPluginFile(String plugin){
        return this.pluginFiles.get(plugin.toLowerCase());
    }
    
    public PluginInfo getPlugin(String plugin){
        return this.plugins.get(plugin.toLowerCase());
    }
    
    public void disablePlugin(String plugin){
        if(!this.getPlugin(plugin).isEnabled()){
            return;
        }
        pm.disablePlugin(this.getPlugin(plugin).getPlugin());
        recacheData();
    }
    
    public void enablePlugin(String plugin){
        if(this.getPlugin(plugin).isEnabled()){
            return;
        }
        pm.enablePlugin(this.getPlugin(plugin).getPlugin());
        recacheData();
    }
    
    public void loadPlugin(String plugin){
        File f = new File(getDataFolder().getParentFile() + File.separator + this.getPluginFile(plugin).getFile());
        try {
            if(this.getPlugin(plugin).isEnabled()){
                return;
            }
            pm.loadPlugin(f);
            recacheData();
        } catch (InvalidPluginException ex) {
            getLogger().log(Level.SEVERE, "Could not load plugin! Plugin file name is Invalid");
        } catch (InvalidDescriptionException ex){
            getLogger().log(Level.SEVERE, "Plugin has invalid plugin.yml!");
        } catch (UnknownDependencyException ex){
            getLogger().log(Level.SEVERE, "Plugin has dependencies that are not present on server!");
        }
    }
}
