package so.dang.cool.z.internal.combination;

import java.util.function.Function;
import java.util.function.LongFunction;
import java.util.function.ToLongFunction;
import so.dang.cool.z.internal.combination.Combine.WithBiFunction;

interface ToLongBiFunctionCombos<A, B> {
    Function<A, ToLongFunction<B>> resolve();

    /* ToLongBiFunction<A, B> -> LongFunction<C> */

    public default <C> Function<A, Function<B, C>> fuseLongFunction(
        LongFunction<C> next
    ) {
        return (A a) -> (B b) -> next.apply(resolve().apply(a).applyAsLong(b));
    }

    public default <C> Function<A, Function<B, C>> fuse(LongFunction<C> next) {
        return fuseLongFunction(next);
    }

    public default <C> WithBiFunction<A, B, C> fusingLongFunction(
        LongFunction<C> next
    ) {
        return WithBiFunction.of(fuseLongFunction(next));
    }

    public default <C> WithBiFunction<A, B, C> fusing(LongFunction<C> next) {
        return fusingLongFunction(next);
    }
}
