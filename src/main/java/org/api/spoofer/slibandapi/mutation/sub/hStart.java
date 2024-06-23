package org.api.spoofer.slibandapi.mutation.sub;

import org.api.spoofer.slibandapi.ModulePlugins;
import org.api.spoofer.slibandapi.RegisterSLib;
import org.api.spoofer.slibandapi.mutation.Mod;

import java.lang.reflect.Method;

public class hStart extends Mod  {

    @Override
    public void method( ModulePlugins plugins,Method method) {
        if(method.isAnnotationPresent(Start.class)) {
            try {
                method.invoke(plugins.getIns(), RegisterSLib.parameter(plugins, method.getParameters()));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
