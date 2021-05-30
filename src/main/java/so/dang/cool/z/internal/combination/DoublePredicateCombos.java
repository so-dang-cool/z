package so.dang.cool.z.internal.combination;

import java.util.function.DoubleFunction;
import java.util.function.DoublePredicate;
import so.dang.cool.z.function.BooleanFunction;
import so.dang.cool.z.internal.combination.Combine.WithDoubleFunction;

interface DoublePredicateCombos {
    DoublePredicate resolve();

    /* DoublePredicate -> BooleanFunction<A> */

    public default <A> DoubleFunction<A> fuseBooleanFunction(
        BooleanFunction<A> next
    ) {
        return (double d) -> next.apply(resolve().test(d));
    }

    public default <A> DoubleFunction<A> fuse(BooleanFunction<A> next) {
        return fuseBooleanFunction(next);
    }

    public default <A> WithDoubleFunction<A> fusingBooleanFunction(
        BooleanFunction<A> next
    ) {
        return WithDoubleFunction.of(fuseBooleanFunction(next));
    }

    public default <A> WithDoubleFunction<A> fusing(BooleanFunction<A> next) {
        return fusingBooleanFunction(next);
    }
}
