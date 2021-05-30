package so.dang.cool.z.internal.combination;

import java.util.function.DoubleFunction;
import java.util.function.LongFunction;
import java.util.function.LongToDoubleFunction;
import so.dang.cool.z.internal.combination.Combine.WithLongFunction;

interface LongToDoubleFunctionCombos {
    LongToDoubleFunction resolve();

    /* LongToDoubleFunction -> DoubleFunction<A> */

    public default <A> LongFunction<A> fuseFunction(DoubleFunction<A> next) {
        return (long n) -> next.apply(resolve().applyAsDouble(n));
    }

    public default <A> LongFunction<A> fuse(DoubleFunction<A> next) {
        return fuseFunction(next);
    }

    public default <A> WithLongFunction<A> fusingFunction(
        DoubleFunction<A> next
    ) {
        return WithLongFunction.of(fuseFunction(next));
    }

    public default <A> WithLongFunction<A> fusing(DoubleFunction<A> next) {
        return fusingFunction(next);
    }
}
