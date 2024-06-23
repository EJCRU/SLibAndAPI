package org.api.spoofer.slibandapi.mutation;

import org.api.spoofer.slibandapi.ModulePlugins;
import java.lang.reflect.Field;
import java.lang.reflect.Method;


public class Mod {


    public void method(ModulePlugins plugins , Method method) {}

    public void field(ModulePlugins plugins , Field field) {}
}
