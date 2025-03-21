package dev.dfonline.flint.feature.trait;

import java.util.Optional;

public interface UserMessageListeningFeature extends FeatureTrait {
    class Result {
        public enum Type {
            PASS,
            CANCEL,
            REPLACE;
        }

        private final Optional<String> newMessage;
        private final Type type;

        public Type getType() {
            return type;
        }
        private Result(Type type) {
            newMessage = Optional.empty();
            this.type = type;
        }
        private Result(Type type, String message) {
            newMessage = Optional.of(message);
            this.type = Type.REPLACE;
        }
        public Optional<String> getMessage() {
            return newMessage;
        }

        public static Result pass() {
            return new Result(Type.PASS);
        }
        public static Result cancel() {
            return new Result(Type.CANCEL);
        }
        public static Result replace(String newMessage) {
            return new Result(Type.REPLACE, newMessage);
        }
    }

    Result sendMessage(String message);
}
