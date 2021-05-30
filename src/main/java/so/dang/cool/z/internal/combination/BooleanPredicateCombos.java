package so.dang.cool.z.internal.combination;

import so.dang.cool.z.function.BooleanFunction;
import so.dang.cool.z.function.BooleanPredicate;
import so.dang.cool.z.internal.combination.Combine.WithBooleanFunction;

interface BooleanPredicateCombos {
    BooleanPredicate resolve();

    /* BooleanPredicate -> BooleanFunction<A> */

    public default <A> BooleanFunction<A> fuseBooleanFunction(
        BooleanFunction<A> next
    ) {
        return (boolean b) -> next.apply(resolve().test(b));
    }

    public default <A> BooleanFunction<A> fuse(BooleanFunction<A> next) {
        return fuseBooleanFunction(next);
    }

    public default <A> WithBooleanFunction<A> fusingBooleanFunction(
        BooleanFunction<A> next
    ) {
        return WithBooleanFunction.of(fuseBooleanFunction(next));
    }

    public default <A> WithBooleanFunction<A> fusing(BooleanFunction<A> next) {
        return fusingBooleanFunction(next);
    }
}
