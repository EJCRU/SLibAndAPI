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

import java.lang.reflect.*;

public class RegisterSLib {

    private static final ParamValue[] paramValues = new ParamValue[]{
            new ConfigParam(),
            new QParam(),
            new CommandParam()
    };

    private static final Mod[] mods = new Mod[]{
            new ConfigScan(),
            new QS(),
            new Colorize(),
            new BeanS(),
            new hStart()
    };

    public static void reg(final Plugin plugin, final Class<?> clazz) {

        try {
            final Settings settings = clazz.getAnnotation(Settings.class);

            FileConfiguration cfg = plugin.getConfig();

            if (settings != null && settings.cfg() != null) {
                cfg = BuilderCfg.get(plugin, settings.cfg());
            }

            final ModulePlugins modulePlugins = new ModulePlugins(plugin, clazz.getConstructors()[0].newInstance(parameter(new ModulePlugins(plugin , null , cfg) , clazz.getConstructors()[0].getParameters())), cfg);

            for (Field f : clazz.getDeclaredFields()) {
                f.setAccessible(true);
                for (Mod mod : mods) {
                    mod.field(modulePlugins, f);
                }
            }

            for (Method m : clazz.getDeclaredMethods()) {
                m.setAccessible(true);
                for (Mod mod : mods) {
                    mod.method(modulePlugins, m);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("create instance is panic ", e);
        }
    }

    public static Object[] parameter(final ModulePlugins plugin, final Parameter[] parameters) {
        final Object[] ob = new Object[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            Object object = null;
            for (ParamValue mod : paramValues) {
                object = object == null ? mod.eval(plugin, parameters[i]) : object;
            }
            if (object != null) {
                ob[i] = parameters[i].isAnnotationPresent(Color.class) ? Colorize.colorize(object) : object;
            }
        }
        return ob;
    }
}
