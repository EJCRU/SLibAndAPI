package org.api.spoofer.slibandapi.mutation.sub;

import org.api.spoofer.slibandapi.ModulePlugins;
import org.api.spoofer.slibandapi.SLibAndAPI;
import org.api.spoofer.slibandapi.mutation.Mod;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class BeanS extends Mod {


    public BeanS(ModulePlugins plugins) {
        super(plugins);
    }

    @Override
    public void field(Field field) {
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
    public void method(Method method) {
        Bean bean = method.getAnnotation(Bean.class);

        if(bean != null) {
            try {
                if(bean.value().isEmpty()) {
                    method.invoke(plugins.getIns(), SLibAndAPI.parameter(plugins, method.getParameters()));
                }else plugins.setBean(bean.value() , method.invoke(plugins.getIns(), SLibAndAPI.parameter(plugins , method.getParameters())));
            } catch (Exception e) {
                throw new RuntimeException("invoke method is panic " , e);
            }
        }
    }
}
