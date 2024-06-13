package org.api.spoofer.slibandapi;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import java.util.HashMap;
import java.util.Map;

@Getter
@AllArgsConstructor
public class ModulePlugins {
    private final Plugin plugin;
    private final Map<String, Object> bean = new HashMap<>();
    private final Object ins;
    private final FileConfiguration configuration;

    public void setBean(String name, Object bean) {
        this.bean.put(name, bean);
    }
}
