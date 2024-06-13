package org.api.spoofer.slibandapi.mutation;



import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Settings {
    String cfg() default "";
}
