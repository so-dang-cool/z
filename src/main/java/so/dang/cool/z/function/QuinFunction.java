package so.dang.cool.z.function;

import so.dang.cool.z.annotation.Evil;

/**
 * Represents a function that accepts five arguments and produces a result.
 * This is a five-arity specialization of {@code Function}.
 */
@Evil
@FunctionalInterface
public interface QuinFunction<A, B, C, D, E, F> {
    /**
     * Applies this function to the given values.
     */
    public F apply(A a, B b, C c, D d, E e);
}
