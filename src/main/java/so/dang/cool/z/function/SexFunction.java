package so.dang.cool.z.function;

import so.dang.cool.z.annotation.Evil;

/**
 * Represents a function that accepts six arguments and produces a result.
 * This is a six-arity specialization of {@code Function}.
 */
@Evil
@FunctionalInterface
public interface SexFunction<A, B, C, D, E, F, G> {
    /**
     * Applies this function to the given values.
     */
    public G apply(A a, B b, C c, D d, E e, F f);
}
