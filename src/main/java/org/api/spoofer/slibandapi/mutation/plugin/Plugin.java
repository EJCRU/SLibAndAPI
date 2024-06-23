package org.api.spoofer.slibandapi.mutation.plugin;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Plugin {
    String description() default "";
    String name() default "";
}
