package so.dang.cool.z.function;

/**
 * Represents a function that accepts a boolean-valued argument and produces a
 * int-valued result. This is a boolean-to-int primitive specialization for
 * {@code Function}.
 */
@FunctionalInterface
public interface BooleanToIntFunction {
    /**
     * Applies this function to the given value.
     */
    int applyAsInt(boolean value);
}
