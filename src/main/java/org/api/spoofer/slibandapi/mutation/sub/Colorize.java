package org.api.spoofer.slibandapi.mutation.sub;

import org.api.spoofer.slibandapi.ModulePlugins;
import org.api.spoofer.slibandapi.mutation.Mod;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

public class Colorize extends Mod {


    public Colorize(ModulePlugins plugins) {
        super(plugins);
    }

    @SuppressWarnings("unchecked")
    public static Object colorize(Object input) {
        if (input instanceof String) {
            return ChatColor.translateAlternateColorCodes('&', (String) input);
        }

        return ((List<String>) input).stream().map(Colorize::colorize).collect(Collectors.toList());
    }

    @Override
    public void field(Field field) {
        if (field.isAnnotationPresent(Color.class)) {
            try {
                field.set(plugins.getIns(), colorize(field.get(plugins.getIns())));
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
