package org.api.spoofer.slibandapi.utils;

public class Assert {
    public static void assertTrue(boolean condition, String message) {
        if(!condition) throw new AssertionError(message);
    }
    public static void assertFalse(boolean condition, String message) {
        if(condition) throw new AssertionError(message);
    }
    public static void assertNull(Object object, String message) {
        if(object == null) throw new AssertionError(message);
    }
    public static void assertEquals(Object expected, Object actual, String message) {
        if(!expected.equals(actual)) throw new AssertionError(message);
    }
}
