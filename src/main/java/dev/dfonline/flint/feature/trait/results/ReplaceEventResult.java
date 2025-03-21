package dev.dfonline.flint.feature.trait.results;

public final class ReplaceEventResult<T> {

    private final T newMessage;
    private final Type type;

    private ReplaceEventResult(Type type) {
        this.newMessage = null;
        this.type = type;
    }

    private ReplaceEventResult(Type type, T message) {
        this.newMessage = message;
        this.type = Type.REPLACE;
    }

    public Type getType() {
        return this.type;
    }

    public T getValue() {
        return this.newMessage;
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

    public enum Type {
        PASS,
        CANCEL,
        REPLACE
    }

}
