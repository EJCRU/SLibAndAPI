package org.api.spoofer.slibandapi.mutation.sub;

import org.api.spoofer.slibandapi.ModulePlugins;
import org.api.spoofer.slibandapi.mutation.Mod;

import java.lang.reflect.Field;

public class QS extends Mod {
    public QS(ModulePlugins plugins) {
        super(plugins);
    }

    @Override
    public void field(Field field) {

        Qulifair qulifair = field.getAnnotation(Qulifair.class);

        if (qulifair != null) {
            try {
                field.set(plugins.getIns(), plugins.getBean().get(qulifair.value()));
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Qulifair is null " , e);
            }
        }
    }
}
