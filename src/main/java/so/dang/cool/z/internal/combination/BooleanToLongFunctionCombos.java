package so.dang.cool.z.internal.combination;

import java.util.function.LongFunction;
import so.dang.cool.z.function.BooleanFunction;
import so.dang.cool.z.function.BooleanToLongFunction;
import so.dang.cool.z.internal.combination.Combine.WithBooleanFunction;

interface BooleanToLongFunctionCombos {
    BooleanToLongFunction resolve();

    /* BooleanToLongFunction<A> -> LongFunction<B> */

    public default <A> BooleanFunction<A> fuseLongFunction(
        LongFunction<A> next
    ) {
        return (boolean b) -> next.apply(resolve().applyAsLong(b));
    }

    public default <A> BooleanFunction<A> fuse(LongFunction<A> next) {
        return fuseLongFunction(next);
    }

    public default <A> WithBooleanFunction<A> fusingLongFunction(
        LongFunction<A> next
    ) {
        return WithBooleanFunction.of(fuseLongFunction(next));
    }

    public default <A> WithBooleanFunction<A> fusing(LongFunction<A> next) {
        return fusingLongFunction(next);
    }
}
