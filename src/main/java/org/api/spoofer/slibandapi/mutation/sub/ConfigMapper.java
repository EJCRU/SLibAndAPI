package org.api.spoofer.slibandapi.mutation.sub;

import lombok.AllArgsConstructor;

import java.util.Map;

@AllArgsConstructor
public class ConfigMapper {

    private final Map<String, Object> values;


    public Object get(final String key) {
        return values.get(key);
    }

    public void set(final String key, final Object value) {
        values.put(key, value);
    }
}
