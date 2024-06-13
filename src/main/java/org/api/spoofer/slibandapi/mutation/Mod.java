package org.api.spoofer.slibandapi.mutation;

import lombok.AllArgsConstructor;
import org.api.spoofer.slibandapi.ModulePlugins;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;


@AllArgsConstructor
public class Mod {

    protected final ModulePlugins plugins;

    public void method(Method method) {
    }

    public void field(Field field) {
    }

    public Object parameter(Parameter parameter) {
        return null;
    }

    public Object parameter(Plugin clazz, Parameter parameter) {
        return null;
    }
}
