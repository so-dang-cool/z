package so.dang.cool.z.function;

import so.dang.cool.z.annotation.Evil;

/**
 * Represents a function that accepts eleven arguments and produces a result.
 * This is an eleven-arity specialization of {@code Function}.
 */
@Evil
@FunctionalInterface
public interface UndecFunction<A, B, C, D, E, F, G, H, I, J, K, L> {
    /**
     * Applies this function to the given values.
     */
    public L apply(A a, B b, C c, D d, E e, F f, G g, H h, I i, J j, K k);
}
