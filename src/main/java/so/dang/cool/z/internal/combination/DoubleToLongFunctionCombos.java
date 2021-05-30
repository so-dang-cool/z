package so.dang.cool.z.internal.combination;

import java.util.function.DoubleFunction;
import java.util.function.DoubleToLongFunction;
import java.util.function.LongFunction;
import so.dang.cool.z.internal.combination.Combine.WithDoubleFunction;

interface DoubleToLongFunctionCombos {
    DoubleToLongFunction resolve();

    /* DoubleToLongFunction -> LongFunction<A> */

    public default <A> DoubleFunction<A> fuseFunction(LongFunction<A> next) {
        return (double d) -> next.apply(resolve().applyAsLong(d));
    }

    public default <A> DoubleFunction<A> fuse(LongFunction<A> next) {
        return fuseFunction(next);
    }

    public default <A> WithDoubleFunction<A> fusingFunction(
        LongFunction<A> next
    ) {
        return WithDoubleFunction.of(fuseFunction(next));
    }

    public default <A> WithDoubleFunction<A> fusing(LongFunction<A> next) {
        return fusingFunction(next);
    }
}
