package org.api.spoofer.slibandapi.mutation;

import org.api.spoofer.slibandapi.ModulePlugins;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.Parameter;

public interface ParamValue {
    Object eval(ModulePlugins plugin, Parameter parameter);
}
