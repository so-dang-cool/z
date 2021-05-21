package so.dang.cool.z.function;

import so.dang.cool.z.annotation.Evil;

/**
 * Represents a function that accepts three arguments and produces a result.
 * This is a three-arity specialization of {@code Function}.
 */
@Evil
@FunctionalInterface
public interface TriFunction<A, B, C, D> {
    /**
     * Applies this function to the given values.
     */
    public D apply(A a, B b, C c);
}
