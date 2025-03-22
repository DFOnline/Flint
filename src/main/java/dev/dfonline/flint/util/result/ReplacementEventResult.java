package dev.dfonline.flint.util.result;

public final class ReplacementEventResult<T> {

    private static final ReplacementEventResult<?> PASS = new ReplacementEventResult<>(Type.PASS);
    private static final ReplacementEventResult<?> CANCEL = new ReplacementEventResult<>(Type.CANCEL);

    private final T newMessage;
    private final Type type;

    private ReplacementEventResult(Type type) {
        this.newMessage = null;
        this.type = type;
    }

    private ReplacementEventResult(Type type, T message) {
        this.newMessage = message;
        this.type = Type.REPLACE;
    }

    public Type getType() {
        return this.type;
    }

    public T getValue() {
        return this.newMessage;
    }

    @SuppressWarnings("unchecked")
    public static <T> ReplacementEventResult<T> pass() {
        return (ReplacementEventResult<T>) PASS;
    }

    @SuppressWarnings("unchecked")
    public static <T> ReplacementEventResult<T> cancel() {
        return (ReplacementEventResult<T>) CANCEL;
    }

    public static <T> ReplacementEventResult<T> replace(T newMessage) {
        return new ReplacementEventResult<>(Type.REPLACE, newMessage);
    }

    public enum Type {
        PASS,
        CANCEL,
        REPLACE
    }

}
