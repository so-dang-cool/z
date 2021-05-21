package so.dang.cool.z.function;

import so.dang.cool.z.annotation.Evil;

/**
 * Represents a function that accepts nine arguments and produces a result.
 * This is a nine-arity specialization of {@code Function}.
 */
@Evil
@FunctionalInterface
public interface NonFunction<A, B, C, D, E, F, G, H, I, J> {
    /**
     * Applies this function to the given values.
     */
    public J apply(A a, B b, C c, D d, E e, F f, G g, H h, I i);
}
