package so.dang.cool.z.function;

import so.dang.cool.z.annotation.Evil;

/**
 * Represents a function that accepts twelve arguments and produces a result.
 * This is a twelve-arity specialization of {@code Function}.
 */
@Evil
@FunctionalInterface
public interface DodecFunction<A, B, C, D, E, F, G, H, I, J, K, L, M> {
    /**
     * Applies this function to the given values.
     */
    public M apply(A a, B b, C c, D d, E e, F f, G g, H h, I i, J j, K k, L l);
}
