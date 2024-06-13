package org.api.spoofer.slibandapi.mutation.param;

import org.api.spoofer.slibandapi.ModulePlugins;
import org.api.spoofer.slibandapi.mutation.ParamValue;
import org.api.spoofer.slibandapi.mutation.sub.Command;
import org.api.spoofer.slibandapi.mutation.sub.CommandBase;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.SimplePluginManager;

import java.lang.reflect.Field;
import java.lang.reflect.Parameter;

public class CommandParam implements ParamValue {

    public static CommandMap commandMap = null;


    @Override
    public Object eval(ModulePlugins plugin, Parameter parameter) {
        Command command = parameter.getAnnotation(Command.class);
        if (command != null) {
            if (commandMap == null && plugin.getPlugin().getServer().getPluginManager() instanceof SimplePluginManager) {
                SimplePluginManager manager = (SimplePluginManager) plugin.getPlugin().getServer().getPluginManager();
                try {
                    Field field = SimplePluginManager.class.getDeclaredField("commandMap");
                    field.setAccessible(true);
                    commandMap = (CommandMap) field.get(manager);
                } catch (IllegalArgumentException | NoSuchFieldException | IllegalAccessException |
                         SecurityException e) {
                    throw new RuntimeException("CommandMap error ", e);
                }
            }
            return new CommandBase(command.value());
        }
        return null;
    }
}
