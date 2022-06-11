package so.dang.cool.z.internal.combination;

import so.dang.cool.z.function.BooleanConsumer;
import so.dang.cool.z.function.BooleanFunction;
import so.dang.cool.z.function.BooleanPredicate;
import so.dang.cool.z.function.BooleanToDoubleFunction;
import so.dang.cool.z.function.BooleanToIntFunction;
import so.dang.cool.z.function.BooleanToLongFunction;
import so.dang.cool.z.internal.combination.Combine.WithBooleanConsumer;
import so.dang.cool.z.internal.combination.Combine.WithBooleanFunction;
import so.dang.cool.z.internal.combination.Combine.WithBooleanPredicate;
import so.dang.cool.z.internal.combination.Combine.WithBooleanToDoubleFunction;
import so.dang.cool.z.internal.combination.Combine.WithBooleanToIntFunction;
import so.dang.cool.z.internal.combination.Combine.WithBooleanToLongFunction;

interface BooleanPredicateCombos {
    BooleanPredicate resolve();

    /* BooleanPredicate -> BooleanFunction<A> */

    public default <A> WithBooleanFunction<A> fuseBooleanFunction(
        BooleanFunction<A> next
    ) {
        return WithBooleanFunction.of(
            (boolean b) -> next.apply(resolve().test(b))
        );
    }

    public default <A> WithBooleanFunction<A> fuse(BooleanFunction<A> next) {
        return fuseBooleanFunction(next);
    }

    /* BooleanPredicate -> BooleanToDoubleFunction */

    public default WithBooleanToDoubleFunction fuseBooleanToDoubleFunction(
        BooleanToDoubleFunction next
    ) {
        return WithBooleanToDoubleFunction.of(
            (boolean b) -> next.applyAsDouble(resolve().test(b))
        );
    }

    public default WithBooleanToDoubleFunction fuse(
        BooleanToDoubleFunction next
    ) {
        return fuseBooleanToDoubleFunction(next);
    }

    /* BooleanPredicate -> BooleanToIntFunction */

    public default WithBooleanToIntFunction fuseBooleanToIntFunction(
        BooleanToIntFunction next
    ) {
        return WithBooleanToIntFunction.of(
            (boolean b) -> next.applyAsInt(resolve().test(b))
        );
    }

    public default WithBooleanToIntFunction fuse(BooleanToIntFunction next) {
        return fuseBooleanToIntFunction(next);
    }

    /* BooleanPredicate -> BooleanToLongFunction */

    public default WithBooleanToLongFunction fuseBooleanToLongFunction(
        BooleanToLongFunction next
    ) {
        return WithBooleanToLongFunction.of(
            (boolean b) -> next.applyAsLong(resolve().test(b))
        );
    }

    public default WithBooleanToLongFunction fuse(BooleanToLongFunction next) {
        return fuseBooleanToLongFunction(next);
    }

    /* BooleanPredicate -> BooleanPredicate */

    public default WithBooleanPredicate fuseBooleanPredicate(
        BooleanPredicate next
    ) {
        return WithBooleanPredicate.of(
            (boolean b) -> next.test(resolve().test(b))
        );
    }

    public default WithBooleanPredicate fuse(BooleanPredicate next) {
        return fuseBooleanPredicate(next);
    }

    /* BooleanPredicate -> BooleanConsumer */

    public default WithBooleanConsumer fuseBooleanConsumer(
        BooleanConsumer next
    ) {
        return WithBooleanConsumer.of(
            (boolean b) -> next.accept(resolve().test(b))
        );
    }

    public default WithBooleanConsumer fuse(BooleanConsumer next) {
        return fuseBooleanConsumer(next);
    }
}
