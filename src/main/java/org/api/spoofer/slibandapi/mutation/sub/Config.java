package org.api.spoofer.slibandapi.mutation.sub;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Config {
    String value();
    boolean ignore() default false;
    String[] section() default {};
}
