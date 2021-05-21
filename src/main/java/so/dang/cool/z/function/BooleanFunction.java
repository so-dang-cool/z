package so.dang.cool.z.function;

/**
 * Represents a function that accepts a boolean-valued argument and produces a
 * result. This is a boolean-consuming primitive specialization for
 * {@code Function}.
 */
@FunctionalInterface
public interface BooleanFunction<A> {
    /**
     * Applies this function to the given value.
     */
    A apply(boolean value);
}
