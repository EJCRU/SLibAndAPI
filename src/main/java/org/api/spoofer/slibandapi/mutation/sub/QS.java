package org.api.spoofer.slibandapi.mutation.sub;

import org.api.spoofer.slibandapi.ModulePlugins;
import org.api.spoofer.slibandapi.SLibAndAPI;
import org.api.spoofer.slibandapi.mutation.Mod;

import java.lang.reflect.Field;

public class QS extends Mod {

    @Override
    public void field(ModulePlugins plugins, Field field) {
        Qulifair qulifair = field.getAnnotation(Qulifair.class);
        if (qulifair != null) {
            try {
                field.set(plugins.getIns(), qulifair.def() ? ModulePlugins.getBean(SLibAndAPI.getPlugin(), qulifair.value()) : plugins.getBean(qulifair.value()));
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Qulifair is null", e);
            }
        }
    }
}
