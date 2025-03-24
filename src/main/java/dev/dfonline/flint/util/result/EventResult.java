package dev.dfonline.flint.util.result;

/**
 * Represents the result of an event that can either proceed or be canceled.
 */
public enum EventResult {

    /**
     * Continue normally, possibly letting other features to cancel it.
     */
    PASS,

    /**
     * Cancel the event, cannot be uncanceled.
     */
    CANCEL

}
