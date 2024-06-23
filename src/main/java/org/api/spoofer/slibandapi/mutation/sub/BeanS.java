package org.api.spoofer.slibandapi.mutation.sub;

import org.api.spoofer.slibandapi.ModulePlugins;
import org.api.spoofer.slibandapi.RegisterSLib;
import org.api.spoofer.slibandapi.mutation.Mod;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class BeanS extends Mod {


    @Override
    public void field(ModulePlugins plugins,Field field) {
        Bean bean = field.getAnnotation(Bean.class);

        if(bean != null) {
            try {
                plugins.setBean(bean.value() , field.get(plugins.getIns()));
            } catch (IllegalAccessException e) {
                throw new RuntimeException("get field is panic " , e);
            }
        }
    }

    @Override
    public void method(ModulePlugins plugins, Method method) {
        Bean bean = method.getAnnotation(Bean.class);
        if (bean != null) {
            try {
                Object[] params = RegisterSLib.parameter(plugins, method.getParameters());
                Object result = method.invoke(plugins.getIns(), params);
                if (!bean.value().isEmpty()) {
                    plugins.setBean(bean.value(), result);
                }
            } catch (Exception e) {
                throw new RuntimeException("invoke method is panic", e);
            }
        }
    }
}
