package so.dang.cool.z.internal.combination;

import java.util.function.LongBinaryOperator;
import java.util.function.LongConsumer;
import java.util.function.LongFunction;
import java.util.function.LongPredicate;
import java.util.function.LongToDoubleFunction;
import java.util.function.LongToIntFunction;
import java.util.function.LongUnaryOperator;
import so.dang.cool.z.internal.combination.Combine.WithLongBinaryOperator;
import so.dang.cool.z.internal.combination.Combine.WithLongConsumer;
import so.dang.cool.z.internal.combination.Combine.WithLongFunction;
import so.dang.cool.z.internal.combination.Combine.WithLongPredicate;
import so.dang.cool.z.internal.combination.Combine.WithLongToDoubleFunction;
import so.dang.cool.z.internal.combination.Combine.WithLongToIntFunction;
import so.dang.cool.z.internal.combination.Combine.WithLongUnaryOperator;

interface LongUnaryOperatorCombos {
    LongUnaryOperator resolve();

    /* LongUnaryOperator -> LongFunction<A> */

    public default <A> WithLongFunction<A> fuseLongFunction(
        LongFunction<A> next
    ) {
        return WithLongFunction.of(
            (long n) -> next.apply(resolve().applyAsLong(n))
        );
    }

    public default <A> WithLongFunction<A> fuse(LongFunction<A> next) {
        return fuseLongFunction(next);
    }

    /* LongUnaryOperator -> LongToIntFunction */

    public default WithLongToIntFunction fuseLongToIntFunction(
        LongToIntFunction next
    ) {
        return WithLongToIntFunction.of(
            (long n) -> next.applyAsInt(resolve().applyAsLong(n))
        );
    }

    public default WithLongToIntFunction fuse(LongToIntFunction next) {
        return fuseLongToIntFunction(next);
    }

    /* LongUnaryOperator -> LongToDoubleFunction */

    public default WithLongToDoubleFunction fuseLongToDoubleFunction(
        LongToDoubleFunction next
    ) {
        return WithLongToDoubleFunction.of(
            (long n) -> next.applyAsDouble(resolve().applyAsLong(n))
        );
    }

    public default WithLongToDoubleFunction fuse(LongToDoubleFunction next) {
        return fuseLongToDoubleFunction(next);
    }

    /* LongUnaryOperator -> LongPredicate */

    public default WithLongPredicate fuseLongPredicate(LongPredicate next) {
        return WithLongPredicate.of(
            (long n) -> next.test(resolve().applyAsLong(n))
        );
    }

    public default WithLongPredicate fuse(LongPredicate next) {
        return fuseLongPredicate(next);
    }

    /* LongUnaryOperator -> LongConsumer */

    public default WithLongConsumer fuseLongConsumer(LongConsumer next) {
        return WithLongConsumer.of(
            (long n) -> next.accept(resolve().applyAsLong(n))
        );
    }

    public default WithLongConsumer fuse(LongConsumer next) {
        return fuseLongConsumer(next);
    }

    /* LongUnaryOperator -> LongUnaryOperator */

    public default WithLongUnaryOperator fuseLongUnaryOperator(
        LongUnaryOperator next
    ) {
        return WithLongUnaryOperator.of(
            (long n) -> next.applyAsLong(resolve().applyAsLong(n))
        );
    }

    public default WithLongUnaryOperator fuse(LongUnaryOperator next) {
        return fuseLongUnaryOperator(next);
    }

    /* LongUnaryOperator -> LongBinaryOperator */

    public default WithLongBinaryOperator fuseLongBinaryOperator(
        LongBinaryOperator next
    ) {
        return WithLongBinaryOperator.of(
            (long d1) ->
                (long d2) -> next.applyAsLong(resolve().applyAsLong(d1), d2)
        );
    }

    public default WithLongBinaryOperator fuse(LongBinaryOperator next) {
        return fuseLongBinaryOperator(next);
    }
}
