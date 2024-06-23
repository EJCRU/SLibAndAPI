package org.api.spoofer.slibandapi;

import lombok.Getter;
import org.api.spoofer.slibandapi.cfg.BuilderCfg;
import org.api.spoofer.slibandapi.mutation.plugin.Plugin;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public final class SLibAndAPI extends JavaPlugin {
    private @Getter static org.bukkit.plugin.Plugin plugin;

    @Override
    public void onEnable() {
        plugin = this;
        BuilderCfg.create(this , "SLibAndAPI.yml");
        List<String> warn = new ArrayList<>();
        ModulePlugins.setBean(this , "WARN" , warn);
        final File[] file = new File(getDataFolder() , "classes").listFiles();
        if(file != null) {
            for (File f : file) {
                if (!f.isDirectory() && f.getName().endsWith(".jar")) {
                    try (URLClassLoader classLoader = new URLClassLoader(new URL[]{f.toURI().toURL()} , getClassLoader()); JarFile jarFile = new JarFile(f)) {
                        Enumeration<JarEntry> entries = jarFile.entries();
                        while (entries.hasMoreElements()) {
                            JarEntry entry = entries.nextElement();
                            if (entry.getName().endsWith(".class")) {
                                String className = entry.getName().replace("/", ".").replace(".class", "");
                                Class<?> clazz = classLoader.loadClass(className);
                                if(clazz != null && clazz.isAnnotationPresent(Plugin.class)) {
                                    RegisterSLib.reg(this , clazz);
                                }
                            }
                        }
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        for(String info : warn) {
            getLogger().warning(info);
        }
    }

    @Override
    public void onDisable() {
        Bukkit.getScheduler().cancelTasks(this);
    }
}
