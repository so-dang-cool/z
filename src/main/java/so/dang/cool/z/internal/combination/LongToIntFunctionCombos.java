package so.dang.cool.z.internal.combination;

import java.util.function.IntFunction;
import java.util.function.LongFunction;
import java.util.function.LongToIntFunction;
import so.dang.cool.z.internal.combination.Combine.WithLongFunction;

interface LongToIntFunctionCombos {
    LongToIntFunction resolve();

    /* LongToIntFunction -> IntFunction<A> */

    public default <A> LongFunction<A> fuseFunction(IntFunction<A> next) {
        return (long n) -> next.apply(resolve().applyAsInt(n));
    }

    public default <A> LongFunction<A> fuse(IntFunction<A> next) {
        return fuseFunction(next);
    }

    public default <A> WithLongFunction<A> fusingFunction(IntFunction<A> next) {
        return WithLongFunction.of(fuseFunction(next));
    }

    public default <A> WithLongFunction<A> fusing(IntFunction<A> next) {
        return fusingFunction(next);
    }
}
