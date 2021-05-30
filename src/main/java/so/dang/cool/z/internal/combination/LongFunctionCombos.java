package so.dang.cool.z.internal.combination;

import java.util.function.Function;
import java.util.function.LongFunction;
import so.dang.cool.z.internal.combination.Combine.WithLongFunction;

interface LongFunctionCombos<A> {
    LongFunction<A> resolve();

    /* LongFunction<A> -> Function<A,B> */

    public default <B> LongFunction<B> fuseFunction(Function<A, B> next) {
        return (long n) -> next.apply(resolve().apply(n));
    }

    public default <B> LongFunction<B> fuse(Function<A, B> next) {
        return fuseFunction(next);
    }

    public default <B> WithLongFunction<B> fusingFunction(Function<A, B> next) {
        return WithLongFunction.of(fuseFunction(next));
    }

    public default <B> WithLongFunction<B> fusing(Function<A, B> next) {
        return fusingFunction(next);
    }
}
