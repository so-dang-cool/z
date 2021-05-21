package so.dang.cool.z.function;

import so.dang.cool.z.annotation.Evil;

/**
 * Represents a function that accepts eight arguments and produces a result.
 * This is an eight-arity specialization of {@code Function}.
 */
@Evil
@FunctionalInterface
public interface OctFunction<A, B, C, D, E, F, G, H, I> {
    /**
     * Applies this function to the given values.
     */
    public I apply(A a, B b, C c, D d, E e, F f, G g, H h);
}
