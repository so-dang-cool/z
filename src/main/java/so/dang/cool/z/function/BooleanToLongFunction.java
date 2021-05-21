package so.dang.cool.z.function;

/**
 * Represents a function that accepts a boolean-valued argument and produces a
 * long-valued result. This is a boolean-to-long primitive specialization for
 * {@code Function}.
 */
@FunctionalInterface
public interface BooleanToLongFunction {
    /**
     * Applies this function to the given value.
     */
    long applyAsLong(boolean value);
}
