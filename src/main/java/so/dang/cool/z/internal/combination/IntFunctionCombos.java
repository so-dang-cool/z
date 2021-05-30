package so.dang.cool.z.internal.combination;

import java.util.function.Function;
import java.util.function.IntFunction;
import so.dang.cool.z.internal.combination.Combine.WithIntFunction;

interface IntFunctionCombos<A> {
    IntFunction<A> resolve();

    /* IntFunction<A> -> Function<A,B> */

    public default <B> IntFunction<B> fuseFunction(Function<A, B> next) {
        return (int i) -> next.apply(resolve().apply(i));
    }

    public default <B> IntFunction<B> fuse(Function<A, B> next) {
        return fuseFunction(next);
    }

    public default <B> WithIntFunction<B> fusingFunction(Function<A, B> next) {
        return WithIntFunction.of(fuseFunction(next));
    }

    public default <B> WithIntFunction<B> fusing(Function<A, B> next) {
        return fusingFunction(next);
    }
}
