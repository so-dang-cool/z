package so.dang.cool.z.internal.combination;

import java.util.function.LongConsumer;
import java.util.function.LongFunction;
import java.util.function.LongPredicate;
import java.util.function.LongToDoubleFunction;
import java.util.function.LongToIntFunction;
import java.util.function.LongUnaryOperator;
import so.dang.cool.z.function.BooleanConsumer;
import so.dang.cool.z.function.BooleanFunction;
import so.dang.cool.z.function.BooleanPredicate;
import so.dang.cool.z.function.BooleanToDoubleFunction;
import so.dang.cool.z.function.BooleanToIntFunction;
import so.dang.cool.z.function.BooleanToLongFunction;
import so.dang.cool.z.internal.combination.Combine.WithLongConsumer;
import so.dang.cool.z.internal.combination.Combine.WithLongFunction;
import so.dang.cool.z.internal.combination.Combine.WithLongPredicate;
import so.dang.cool.z.internal.combination.Combine.WithLongToDoubleFunction;
import so.dang.cool.z.internal.combination.Combine.WithLongToIntFunction;
import so.dang.cool.z.internal.combination.Combine.WithLongUnaryOperator;

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

    /* LongPredicate -> BooleanToDoubleFunction */

    public default LongToDoubleFunction fuseBooleanToDoubleFunction(
        BooleanToDoubleFunction next
    ) {
        return (long n) -> next.applyAsDouble(resolve().test(n));
    }

    public default LongToDoubleFunction fuse(BooleanToDoubleFunction next) {
        return fuseBooleanToDoubleFunction(next);
    }

    public default WithLongToDoubleFunction fusingBooleanToDoubleFunction(
        BooleanToDoubleFunction next
    ) {
        return WithLongToDoubleFunction.of(fuseBooleanToDoubleFunction(next));
    }

    public default WithLongToDoubleFunction fusing(
        BooleanToDoubleFunction next
    ) {
        return fusingBooleanToDoubleFunction(next);
    }

    /* LongPredicate -> BooleanToIntFunction */

    public default LongToIntFunction fuseBooleanToIntFunction(
        BooleanToIntFunction next
    ) {
        return (long n) -> next.applyAsInt(resolve().test(n));
    }

    public default LongToIntFunction fuse(BooleanToIntFunction next) {
        return fuseBooleanToIntFunction(next);
    }

    public default WithLongToIntFunction fusingBooleanToIntFunction(
        BooleanToIntFunction next
    ) {
        return WithLongToIntFunction.of(fuseBooleanToIntFunction(next));
    }

    public default WithLongToIntFunction fusing(BooleanToIntFunction next) {
        return fusingBooleanToIntFunction(next);
    }

    /* LongPredicate -> BooleanToLongFunction */

    public default LongUnaryOperator fuseBooleanToLongFunction(
        BooleanToLongFunction next
    ) {
        return (long n) -> next.applyAsLong(resolve().test(n));
    }

    public default LongUnaryOperator fuse(BooleanToLongFunction next) {
        return fuseBooleanToLongFunction(next);
    }

    public default WithLongUnaryOperator fusingBooleanToLongFunction(
        BooleanToLongFunction next
    ) {
        return WithLongUnaryOperator.of(fuseBooleanToLongFunction(next));
    }

    public default WithLongUnaryOperator fusing(BooleanToLongFunction next) {
        return fusingBooleanToLongFunction(next);
    }

    /* LongPredicate -> BooleanPredicate */

    public default LongPredicate fuseBooleanPredicate(BooleanPredicate next) {
        return (long n) -> next.test(resolve().test(n));
    }

    public default LongPredicate fuse(BooleanPredicate next) {
        return fuseBooleanPredicate(next);
    }

    public default WithLongPredicate fusingBooleanPredicate(
        BooleanPredicate next
    ) {
        return WithLongPredicate.of(fuseBooleanPredicate(next));
    }

    public default WithLongPredicate fusing(BooleanPredicate next) {
        return fusingBooleanPredicate(next);
    }

    /* LongPredicate -> BooleanConsumer */

    public default LongConsumer fuseBooleanConsumer(BooleanConsumer next) {
        return (long n) -> next.accept(resolve().test(n));
    }

    public default LongConsumer fuse(BooleanConsumer next) {
        return fuseBooleanConsumer(next);
    }

    public default WithLongConsumer fusingBooleanConsumer(
        BooleanConsumer next
    ) {
        return WithLongConsumer.of(fuseBooleanConsumer(next));
    }

    public default WithLongConsumer fusing(BooleanConsumer next) {
        return fusingBooleanConsumer(next);
    }
}
