package dev.dfonline.flint.feature.trait;

public class ReplaceEventResult <T> {
    public enum Type {
        PASS,
        CANCEL,
        REPLACE;
    }

    private final T newMessage;
    private final Type type;


    public Type getType() {
        return type;
    }
    private ReplaceEventResult(Type type) {
        newMessage = null;
        this.type = type;
    }
    private ReplaceEventResult(Type type, T message) {
        newMessage = message;
        this.type = Type.REPLACE;
    }
    public T getValue() {
        return newMessage;
    }

    public static <T> ReplaceEventResult<T> pass() {
        return new ReplaceEventResult<>(Type.PASS);
    }
    public static <T> ReplaceEventResult<T> cancel() {
        return new ReplaceEventResult<>(Type.CANCEL);
    }
    public static <T> ReplaceEventResult<T> replace(T newMessage) {
        return new ReplaceEventResult<>(Type.REPLACE, newMessage);
    }
}
