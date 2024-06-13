package org.api.spoofer.slibandapi.mutation.param;

import org.api.spoofer.slibandapi.ModulePlugins;
import org.api.spoofer.slibandapi.mutation.ParamValue;
import org.api.spoofer.slibandapi.mutation.sub.Qulifair;
import java.lang.reflect.Parameter;

public class QParam implements ParamValue {
    @Override
    public Object eval(ModulePlugins plugin, Parameter parameter) {
        Qulifair qulifair = parameter.getAnnotation(Qulifair.class);

        if (qulifair != null) {
            return plugin.getBean().get(qulifair.value());
        }
        return null;
    }
}
