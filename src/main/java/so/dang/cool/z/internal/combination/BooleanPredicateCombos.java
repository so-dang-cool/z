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

    /* BooleanPredicate -> BooleanToDoubleFunction */

    public default BooleanToDoubleFunction fuseBooleanToDoubleFunction(
        BooleanToDoubleFunction next
    ) {
        return (boolean b) -> next.applyAsDouble(resolve().test(b));
    }

    public default BooleanToDoubleFunction fuse(BooleanToDoubleFunction next) {
        return fuseBooleanToDoubleFunction(next);
    }

    public default WithBooleanToDoubleFunction fusingBooleanToDoubleFunction(
        BooleanToDoubleFunction next
    ) {
        return WithBooleanToDoubleFunction.of(
            fuseBooleanToDoubleFunction(next)
        );
    }

    public default WithBooleanToDoubleFunction fusing(
        BooleanToDoubleFunction next
    ) {
        return fusingBooleanToDoubleFunction(next);
    }

    /* BooleanPredicate -> BooleanToIntFunction */

    public default BooleanToIntFunction fuseBooleanToIntFunction(
        BooleanToIntFunction next
    ) {
        return (boolean b) -> next.applyAsInt(resolve().test(b));
    }

    public default BooleanToIntFunction fuse(BooleanToIntFunction next) {
        return fuseBooleanToIntFunction(next);
    }

    public default WithBooleanToIntFunction fusingBooleanToIntFunction(
        BooleanToIntFunction next
    ) {
        return WithBooleanToIntFunction.of(fuseBooleanToIntFunction(next));
    }

    public default WithBooleanToIntFunction fusing(BooleanToIntFunction next) {
        return fusingBooleanToIntFunction(next);
    }

    /* BooleanPredicate -> BooleanToLongFunction */

    public default BooleanToLongFunction fuseBooleanToLongFunction(
        BooleanToLongFunction next
    ) {
        return (boolean b) -> next.applyAsLong(resolve().test(b));
    }

    public default BooleanToLongFunction fuse(BooleanToLongFunction next) {
        return fuseBooleanToLongFunction(next);
    }

    public default WithBooleanToLongFunction fusingBooleanToLongFunction(
        BooleanToLongFunction next
    ) {
        return WithBooleanToLongFunction.of(fuseBooleanToLongFunction(next));
    }

    public default WithBooleanToLongFunction fusing(
        BooleanToLongFunction next
    ) {
        return fusingBooleanToLongFunction(next);
    }

    /* BooleanPredicate -> BooleanPredicate */

    public default BooleanPredicate fuseBooleanPredicate(
        BooleanPredicate next
    ) {
        return (boolean b) -> next.test(resolve().test(b));
    }

    public default BooleanPredicate fuse(BooleanPredicate next) {
        return fuseBooleanPredicate(next);
    }

    public default WithBooleanPredicate fusingBooleanPredicate(
        BooleanPredicate next
    ) {
        return WithBooleanPredicate.of(fuseBooleanPredicate(next));
    }

    public default WithBooleanPredicate fusing(BooleanPredicate next) {
        return fusingBooleanPredicate(next);
    }

    /* BooleanPredicate -> BooleanConsumer */

    public default BooleanConsumer fuseBooleanConsumer(BooleanConsumer next) {
        return (boolean b) -> next.accept(resolve().test(b));
    }

    public default BooleanConsumer fuse(BooleanConsumer next) {
        return fuseBooleanConsumer(next);
    }

    public default WithBooleanConsumer fusingBooleanConsumer(
        BooleanConsumer next
    ) {
        return WithBooleanConsumer.of(fuseBooleanConsumer(next));
    }

    public default WithBooleanConsumer fusing(BooleanConsumer next) {
        return fusingBooleanConsumer(next);
    }
}
