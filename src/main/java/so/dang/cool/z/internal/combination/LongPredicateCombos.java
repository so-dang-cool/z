package so.dang.cool.z.internal.combination;

import java.util.function.LongPredicate;
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

    public default <A> WithLongFunction<A> fuseBooleanFunction(
        BooleanFunction<A> next
    ) {
        return WithLongFunction.of((long n) -> next.apply(resolve().test(n)));
    }

    public default <A> WithLongFunction<A> fuse(BooleanFunction<A> next) {
        return fuseBooleanFunction(next);
    }

    /* LongPredicate -> BooleanToDoubleFunction */

    public default WithLongToDoubleFunction fuseBooleanToDoubleFunction(
        BooleanToDoubleFunction next
    ) {
        return WithLongToDoubleFunction.of(
            (long n) -> next.applyAsDouble(resolve().test(n))
        );
    }

    public default WithLongToDoubleFunction fuse(BooleanToDoubleFunction next) {
        return fuseBooleanToDoubleFunction(next);
    }

    /* LongPredicate -> BooleanToIntFunction */

    public default WithLongToIntFunction fuseBooleanToIntFunction(
        BooleanToIntFunction next
    ) {
        return WithLongToIntFunction.of(
            (long n) -> next.applyAsInt(resolve().test(n))
        );
    }

    public default WithLongToIntFunction fuse(BooleanToIntFunction next) {
        return fuseBooleanToIntFunction(next);
    }

    /* LongPredicate -> BooleanToLongFunction */

    public default WithLongUnaryOperator fuseBooleanToLongFunction(
        BooleanToLongFunction next
    ) {
        return WithLongUnaryOperator.of(
            (long n) -> next.applyAsLong(resolve().test(n))
        );
    }

    public default WithLongUnaryOperator fuse(BooleanToLongFunction next) {
        return fuseBooleanToLongFunction(next);
    }

    /* LongPredicate -> BooleanPredicate */

    public default WithLongPredicate fuseBooleanPredicate(
        BooleanPredicate next
    ) {
        return WithLongPredicate.of((long n) -> next.test(resolve().test(n)));
    }

    public default WithLongPredicate fuse(BooleanPredicate next) {
        return fuseBooleanPredicate(next);
    }

    /* LongPredicate -> BooleanConsumer */

    public default WithLongConsumer fuseBooleanConsumer(BooleanConsumer next) {
        return WithLongConsumer.of((long n) -> next.accept(resolve().test(n)));
    }

    public default WithLongConsumer fuse(BooleanConsumer next) {
        return fuseBooleanConsumer(next);
    }
}
