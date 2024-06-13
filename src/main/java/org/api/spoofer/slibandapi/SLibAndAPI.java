package org.api.spoofer.slibandapi;

import org.api.spoofer.slibandapi.cfg.BuilderCfg;
import org.api.spoofer.slibandapi.mutation.Mod;
import org.api.spoofer.slibandapi.mutation.ParamValue;
import org.api.spoofer.slibandapi.mutation.Settings;
import org.api.spoofer.slibandapi.mutation.param.CommandParam;
import org.api.spoofer.slibandapi.mutation.param.ConfigParam;
import org.api.spoofer.slibandapi.mutation.param.QParam;
import org.api.spoofer.slibandapi.mutation.sub.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public final class SLibAndAPI extends JavaPlugin {



    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {

    }

    private static final ParamValue[] paramValues = new ParamValue[]{
            new ConfigParam(),
            new QParam(),
            new CommandParam()
    };

    public static void reg(Plugin plugin, Class<?> clazz) {

        ModulePlugins modulePlugins;

        try {

            Settings settings = clazz.getAnnotation(Settings.class);

            Object object = clazz.getConstructors()[0].newInstance(parameter(new ModulePlugins(plugin , null , null) , clazz.getConstructors()[0].getParameters()));



            FileConfiguration cfg = plugin.getConfig();


            if(settings != null && settings.cfg() != null) {
                cfg = BuilderCfg.get(plugin , settings.cfg());
            }

            if(clazz.getSuperclass().isAssignableFrom(Settings.class)) {
                try {
                    cfg = BuilderCfg.get(plugin , (String) clazz.getMethod("getCfg").invoke(object));
                } catch (NoSuchMethodException e) {
                    throw new RuntimeException("No such setting '" + clazz.getSimpleName() + "'.");
                }
            }

            modulePlugins = new ModulePlugins(plugin,  object , cfg);
        } catch (InstantiationException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException("create instance is panic ");
        }

        Mod[] mods = new Mod[]{
                new ConfigScan(modulePlugins),
                new QS(modulePlugins),
                new Colorize(modulePlugins),
                new BeanS(modulePlugins)
        };

        for (Field f : clazz.getDeclaredFields()) {
            f.setAccessible(true);
            for (Mod mod : mods) {
                mod.field(f);
            }
        }

        for (Method m : clazz.getDeclaredMethods()) {
            m.setAccessible(true);
            for (Mod mod : mods) {
                mod.method(m);
            }
        }
    }

    public static Object[] parameter(ModulePlugins plugin, Parameter[] parameters) {
        Object[] ob = new Object[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            Object object = null;
            for (ParamValue mod : paramValues) {
                if(object == null) {
                    object = mod.eval(plugin, parameters[i]);
                }
            }
            if(object != null) {
                if (parameters[i].isAnnotationPresent(Color.class)) {
                    object = Colorize.colorize(object);
                }
                ob[i] = object;
            }
        }
        return ob;
    }
}
