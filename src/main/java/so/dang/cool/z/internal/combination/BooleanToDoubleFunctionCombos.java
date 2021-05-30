package so.dang.cool.z.internal.combination;

import java.util.function.DoubleFunction;
import so.dang.cool.z.function.BooleanFunction;
import so.dang.cool.z.function.BooleanToDoubleFunction;
import so.dang.cool.z.internal.combination.Combine.WithBooleanFunction;

interface BooleanToDoubleFunctionCombos {
    BooleanToDoubleFunction resolve();

    /* BooleanToDoubleFunction<A> -> DoubleFunction<B> */

    public default <A> BooleanFunction<A> fuseDoubleFunction(
        DoubleFunction<A> next
    ) {
        return (boolean b) -> next.apply(resolve().applyAsDouble(b));
    }

    public default <A> BooleanFunction<A> fuse(DoubleFunction<A> next) {
        return fuseDoubleFunction(next);
    }

    public default <A> WithBooleanFunction<A> fusingDoubleFunction(
        DoubleFunction<A> next
    ) {
        return WithBooleanFunction.of(fuseDoubleFunction(next));
    }

    public default <A> WithBooleanFunction<A> fusing(DoubleFunction<A> next) {
        return fusingDoubleFunction(next);
    }
}
