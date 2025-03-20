package dev.dfonline.flint.util;

import dev.dfonline.flint.Flint;
import org.slf4j.LoggerFactory;

/**
 * A simple SLF4J logger wrapper to make debugging easier by providing
 * a simple way to attach the class name to the logger.
 *
 * <p>Example usage:</p>
 *
 * <pre>{@code
 * public class MyClass {
 *    private static final Logger logger = Logger.of(MyClass.class);
 *
 *    public void doSomething() {
 *       logger.info("Doing something..."); // (Flint | MyClass) Doing something...
 *    }
 * }
 * }</pre>
 */
public final class Logger {

    private final org.slf4j.Logger logger;

    private Logger(Class<?> clazz) {
        String suffix = " | " + clazz.getSimpleName();
        if (clazz.getSimpleName().equals(Flint.MOD_NAME)) {
            suffix = "";
        }
        this.logger = LoggerFactory.getLogger(Flint.MOD_NAME + suffix);
    }

    public static Logger of(Class<?> clazz) {
        return new Logger(clazz);
    }

    public void info(String message, Object... args) {
        logger.info(message, args);
    }

    public void warn(String message, Object... args) {
        logger.warn(message, args);
    }

    public void error(String message, Object... args) {
        logger.error(message, args);
    }

    public void error(String message, Throwable throwable) {
        logger.error(message, throwable);
    }

}
