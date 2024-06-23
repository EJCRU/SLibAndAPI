package org.api.spoofer.slibandapi.cfg;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import lombok.AllArgsConstructor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;

@AllArgsConstructor
public class BuilderCfg {

    private static final Table<Plugin , String , YamlConfiguration> patcher = HashBasedTable.create();


    public static void create(Plugin patch , final String name) {
        File customConfigFile = new File(patch.getDataFolder(), name);
        if (!customConfigFile.exists()) {
            customConfigFile.getParentFile().mkdirs();
            patch.saveResource(name, false);
        }

        YamlConfiguration customConfig = new YamlConfiguration();

        try {
            customConfig.load(customConfigFile);
        } catch (Exception  e) {
            throw new RuntimeException("config create panic!" , e);
        }

        patcher.put(patch , name , customConfig);
    }

    public static YamlConfiguration get(Plugin plugin , final String name) {
        return patcher.get(plugin , name);
    }
}
