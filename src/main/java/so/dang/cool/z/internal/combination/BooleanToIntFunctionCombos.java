package so.dang.cool.z.internal.combination;

import java.util.function.IntFunction;
import so.dang.cool.z.function.BooleanFunction;
import so.dang.cool.z.function.BooleanToIntFunction;
import so.dang.cool.z.internal.combination.Combine.WithBooleanFunction;

interface BooleanToIntFunctionCombos {
    BooleanToIntFunction resolve();

    /* BooleanToIntFunction<A> -> IntFunction<B> */

    public default <A> BooleanFunction<A> fuseIntFunction(IntFunction<A> next) {
        return (boolean b) -> next.apply(resolve().applyAsInt(b));
    }

    public default <A> BooleanFunction<A> fuse(IntFunction<A> next) {
        return fuseIntFunction(next);
    }

    public default <A> WithBooleanFunction<A> fusingIntFunction(
        IntFunction<A> next
    ) {
        return WithBooleanFunction.of(fuseIntFunction(next));
    }

    public default <A> WithBooleanFunction<A> fusing(IntFunction<A> next) {
        return fusingIntFunction(next);
    }
}
