package org.api.spoofer.slibandapi.mutation.param;

import org.api.spoofer.slibandapi.ModulePlugins;
import org.api.spoofer.slibandapi.mutation.ParamValue;
import org.api.spoofer.slibandapi.mutation.sub.Config;
import org.api.spoofer.slibandapi.mutation.sub.ConfigScan;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Parameter;

public class ConfigParam implements ParamValue {
    @Override
    public Object eval(ModulePlugins clazz, @NotNull Parameter parameter) {
        Config ann = parameter.getAnnotation(Config.class);

        if (ann != null) {
            return ConfigScan.eval(clazz, ann);
        }

        return null;
    }
}
