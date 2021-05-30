package so.dang.cool.z.internal.combination;

import java.util.function.DoubleFunction;
import java.util.function.DoubleToIntFunction;
import java.util.function.IntFunction;
import so.dang.cool.z.internal.combination.Combine.WithDoubleFunction;

interface DoubleToIntFunctionCombos {
    DoubleToIntFunction resolve();

    /* DoubleToIntFunction -> IntFunction<A> */

    public default <A> DoubleFunction<A> fuseFunction(IntFunction<A> next) {
        return (double d) -> next.apply(resolve().applyAsInt(d));
    }

    public default <A> DoubleFunction<A> fuse(IntFunction<A> next) {
        return fuseFunction(next);
    }

    public default <A> WithDoubleFunction<A> fusingFunction(
        IntFunction<A> next
    ) {
        return WithDoubleFunction.of(fuseFunction(next));
    }

    public default <A> WithDoubleFunction<A> fusing(IntFunction<A> next) {
        return fusingFunction(next);
    }
}
