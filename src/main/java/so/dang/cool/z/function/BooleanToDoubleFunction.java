package so.dang.cool.z.function;

/**
 * Represents a function that accepts a boolean-valued argument and produces a
 * double-valued result. This is a boolean-to-double primitive specialization
 * for {@code Function}. 
 */
@FunctionalInterface
public interface BooleanToDoubleFunction {
    /**
     * Applies this function to the given value.
     */
    double applyAsDouble(boolean value);
}
