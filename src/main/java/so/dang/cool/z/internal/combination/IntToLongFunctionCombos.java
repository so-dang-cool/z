package so.dang.cool.z.internal.combination;

import java.util.function.IntFunction;
import java.util.function.IntToLongFunction;
import java.util.function.LongFunction;
import so.dang.cool.z.internal.combination.Combine.WithIntFunction;

interface IntToLongFunctionCombos {
    IntToLongFunction resolve();

    /* IntToLongFunction -> LongFunction<A> */

    public default <A> IntFunction<A> fuseFunction(LongFunction<A> next) {
        return (int i) -> next.apply(resolve().applyAsLong(i));
    }

    public default <A> IntFunction<A> fuse(LongFunction<A> next) {
        return fuseFunction(next);
    }

    public default <A> WithIntFunction<A> fusingFunction(LongFunction<A> next) {
        return WithIntFunction.of(fuseFunction(next));
    }

    public default <A> WithIntFunction<A> fusing(LongFunction<A> next) {
        return fusingFunction(next);
    }
}
