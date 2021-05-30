package so.dang.cool.z.internal.combination;

import java.util.function.Function;
import java.util.function.LongFunction;
import java.util.function.ToLongFunction;
import so.dang.cool.z.internal.combination.Combine.WithFunction;

interface ToLongFunctionCombos<A> {
    ToLongFunction<A> resolve();

    /* ToLongFunction<A> -> LongFunction<B> */

    public default <B> Function<A, B> fuseLongFunction(LongFunction<B> next) {
        return (A a) -> next.apply(resolve().applyAsLong(a));
    }

    public default <B> Function<A, B> fuse(LongFunction<B> next) {
        return fuseLongFunction(next);
    }

    public default <B> WithFunction<A, B> fusingLongFunction(
        LongFunction<B> next
    ) {
        return WithFunction.of(fuseLongFunction(next));
    }

    public default <B> WithFunction<A, B> fusing(LongFunction<B> next) {
        return fusingLongFunction(next);
    }
}
