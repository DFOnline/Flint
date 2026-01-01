package dev.dfonline.flint.util;

import java.util.function.Function;

public final class ObjectUtil {

    private ObjectUtil() {
    }

    /**
     * Safely converts an object to a string using the provided mapping function.
     * If the given value is {@code null}, the method returns the string {@code "null"}.
     *
     * @param <T>    the type of the object
     * @param value  the object to convert to a string
     * @param mapper a function that converts the object to a string
     * @return the string representation of the object, or {@code "null"} if the object is {@code null}
     */
    public static <T> String toString(T value, Function<T, String> mapper) {
        return toString(value, mapper, "null");
    }

    /**
     * Safely converts an object to a string using the provided mapping function.
     * If the given value is {@code null}, the method returns the specified default value.
     *
     * @param <T>          the type of the object
     * @param value        the object to convert to a string
     * @param mapper       a function that converts the object to a string
     * @param defaultValue the string to return if {@code value} is {@code null}
     * @return the string representation of the object, or {@code defaultValue} if the object is {@code null}
     */
    public static <T> String toString(T value, Function<T, String> mapper, String defaultValue) {
        if (value == null) {
            return defaultValue;
        }
        return mapper.apply(value);
    }

}
