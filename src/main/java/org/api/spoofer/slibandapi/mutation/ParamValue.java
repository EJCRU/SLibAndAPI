package org.api.spoofer.slibandapi.mutation;

import org.api.spoofer.slibandapi.ModulePlugins;

import java.lang.reflect.Parameter;

public interface ParamValue {
    Object eval(ModulePlugins plugin, Parameter parameter);
}
