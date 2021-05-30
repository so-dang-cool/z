package so.dang.cool.z.internal.combination;

import java.util.function.LongFunction;
import java.util.function.LongPredicate;
import so.dang.cool.z.function.BooleanFunction;
import so.dang.cool.z.internal.combination.Combine.WithLongFunction;

interface LongPredicateCombos {
    LongPredicate resolve();

    /* LongPredicate -> BooleanFunction<A> */

    public default <A> LongFunction<A> fuseBooleanFunction(
        BooleanFunction<A> next
    ) {
        return (long n) -> next.apply(resolve().test(n));
    }

    public default <A> LongFunction<A> fuse(BooleanFunction<A> next) {
        return fuseBooleanFunction(next);
    }

    public default <A> WithLongFunction<A> fusingBooleanFunction(
        BooleanFunction<A> next
    ) {
        return WithLongFunction.of(fuseBooleanFunction(next));
    }

    public default <A> WithLongFunction<A> fusing(BooleanFunction<A> next) {
        return fusingBooleanFunction(next);
    }
}
