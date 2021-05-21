package so.dang.cool.z.function;

import so.dang.cool.z.annotation.Evil;

/**
 * Represents a function that accepts ten arguments and produces a result. This
 * is a ten-arity specialization of {@code Function}.
 */
@Evil
@FunctionalInterface
public interface DecFunction<A, B, C, D, E, F, G, H, I, J, K> {
    /**
     * Applies this function to the given values.
     */
    public K apply(A a, B b, C c, D d, E e, F f, G g, H h, I i, J j);
}
