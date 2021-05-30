package so.dang.cool.z.internal.combination;

import java.util.function.DoubleFunction;
import java.util.function.IntFunction;
import java.util.function.IntToDoubleFunction;
import so.dang.cool.z.internal.combination.Combine.WithIntFunction;

interface IntToDoubleFunctionCombos {
    IntToDoubleFunction resolve();

    /* IntToDoubleFunction -> DoubleFunction<A> */

    public default <A> IntFunction<A> fuseFunction(DoubleFunction<A> next) {
        return (int i) -> next.apply(resolve().applyAsDouble(i));
    }

    public default <A> IntFunction<A> fuse(DoubleFunction<A> next) {
        return fuseFunction(next);
    }

    public default <A> WithIntFunction<A> fusingFunction(
        DoubleFunction<A> next
    ) {
        return WithIntFunction.of(fuseFunction(next));
    }

    public default <A> WithIntFunction<A> fusing(DoubleFunction<A> next) {
        return fusingFunction(next);
    }
}
