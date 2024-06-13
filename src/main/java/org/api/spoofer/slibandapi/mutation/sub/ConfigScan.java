package org.api.spoofer.slibandapi.mutation.sub;

import org.api.spoofer.slibandapi.ModulePlugins;
import org.api.spoofer.slibandapi.mutation.Mod;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.Field;
import java.util.*;

public class ConfigScan extends Mod {


    public ConfigScan(ModulePlugins plugins) {
        super(plugins);
    }

    public static Object eval(ModulePlugins clazz, Config ann) {
        if (ann.value().isEmpty()) throw new RuntimeException("Config value is empty");

        if (ann.section().length == 0) return clazz.getConfiguration().get(ann.value());


        final Set<String> sec = Objects.requireNonNull(clazz.getConfiguration().getConfigurationSection(ann.value()), "ConfigurationSection is emty or null").getKeys(false);

        final Set<ConfigMapper> mappers = new HashSet<>();

        for (String le : sec) {
            Map<String, Object> map = new HashMap<>();
            for (String v : ann.section()) {

                final Object obj = clazz.getConfiguration().get(ann.value() + "." + le + "." + v);

                if(ann.ignore() || obj != null) {
                    map.put(v, obj);
                }else {
                    throw new RuntimeException("Config section '" + le + "' does not contain '" + v + "'");
                }
            }
            mappers.add(new ConfigMapper(map));
        }

        return mappers;
    }

    @Override
    public void field(Field field) {
        if (field.isAnnotationPresent(Config.class)) {
            try {
                field.set(plugins.getIns(), eval(plugins, field.getAnnotation(Config.class)));
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Field is null or is not access", e);
            }
        }
    }
}
