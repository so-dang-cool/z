package so.dang.cool.z.function;

import so.dang.cool.z.annotation.Evil;

/**
 * Represents a function that accepts four arguments and produces a result.
 * This is a four-arity specialization of {@code Function}.
 */
@Evil
@FunctionalInterface
public interface QuadFunction<A, B, C, D, E> {
    /**
     * Applies this function to the given values.
     */
    public E apply(A a, B b, C c, D d);
}
