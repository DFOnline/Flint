package dev.dfonline.flint.util.result;

/**
 * Represents the result of an event that can either proceed normally, be canceled,
 * or have its value replaced with a new one.
 *
 * @param <T> The type of value that can be replaced
 */
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

    /**
     * Gets the type of this result.
     *
     * @return The result type
     */
    public Type getType() {
        return this.type;
    }

    /**
     * Gets the replacement value if this result is of type REPLACE.
     *
     * @return The replacement value, or null if not a REPLACE result
     */
    public T getValue() {
        return this.newMessage;
    }

    /**
     * Creates a result indicating the event should proceed normally.
     *
     * @param <T> The type parameter for the result
     * @return A result with PASS type
     */
    @SuppressWarnings("unchecked")
    public static <T> ReplacementEventResult<T> pass() {
        return (ReplacementEventResult<T>) PASS;
    }

    /**
     * Creates a result indicating the event should be canceled.
     *
     * @param <T> The type parameter for the result
     * @return A result with CANCEL type
     */
    @SuppressWarnings("unchecked")
    public static <T> ReplacementEventResult<T> cancel() {
        return (ReplacementEventResult<T>) CANCEL;
    }

    /**
     * Creates a result indicating the event should be handled with a replacement value.
     *
     * @param <T>        The type parameter for the result
     * @param newMessage The replacement value to use
     * @return A result with REPLACE type and the given replacement value
     */
    public static <T> ReplacementEventResult<T> replace(T newMessage) {
        return new ReplacementEventResult<>(Type.REPLACE, newMessage);
    }

    /**
     * Possible types for a replacement event result.
     */
    public enum Type {
        /**
         * Continue normally, possibly letting other features to cancel it.
         */
        PASS,

        /**
         * Cancel the event, cannot be uncanceled.
         */
        CANCEL,

        /**
         * Replace the event value with a new one.
         */
        REPLACE
    }

}
