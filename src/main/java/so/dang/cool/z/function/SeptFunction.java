package so.dang.cool.z.function;

import so.dang.cool.z.annotation.Evil;

/**
 * Represents a function that accepts seven arguments and produces a result.
 * This is a seven-arity specialization of {@code Function}.
 */
@Evil
@FunctionalInterface
public interface SeptFunction<A, B, C, D, E, F, G, H> {
    /**
     * Applies this function to the given values.
     */
    public H apply(A a, B b, C c, D d, E e, F f, G g);
}
