package org.api.spoofer.slibandapi;


import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

@Getter
public class ModulePlugins {

    private static final Table<Plugin , String , Object> hbean = HashBasedTable.create();


    private final Plugin plugin;
    private final Object ins;
    private final FileConfiguration configuration;

    public ModulePlugins(Plugin plugin, Object ins, FileConfiguration configuration) {
        this.plugin = plugin;
        this.ins = ins;
        this.configuration = configuration;
    }

    public static Object getBean(Plugin plugin , String name) {
        return hbean.get(plugin, name);
    }
    public static void setBean(Plugin plugin , String name, Object value) {
        hbean.put(plugin, name, value);
    }

    public Object getBean(String name) {
        return hbean.get(plugin , name);
    }

    public void setBean(String name, Object bean) {
        hbean.put(plugin , name, bean);
    }
}
