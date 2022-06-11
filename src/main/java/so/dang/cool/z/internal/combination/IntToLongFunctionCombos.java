package so.dang.cool.z.internal.combination;

import java.util.function.IntFunction;
import java.util.function.IntToLongFunction;
import java.util.function.LongBinaryOperator;
import java.util.function.LongConsumer;
import java.util.function.LongFunction;
import java.util.function.LongPredicate;
import java.util.function.LongToDoubleFunction;
import java.util.function.LongToIntFunction;
import java.util.function.LongUnaryOperator;
import so.dang.cool.z.internal.combination.Combine.WithIntConsumer;
import so.dang.cool.z.internal.combination.Combine.WithIntFunction;
import so.dang.cool.z.internal.combination.Combine.WithIntPredicate;
import so.dang.cool.z.internal.combination.Combine.WithIntToDoubleFunction;
import so.dang.cool.z.internal.combination.Combine.WithIntToLongFunction;
import so.dang.cool.z.internal.combination.Combine.WithIntUnaryOperator;

interface IntToLongFunctionCombos {
    IntToLongFunction resolve();

    /* IntToLongFunction -> LongFunction<A> */

    public default <A> WithIntFunction<A> fuseLongFunction(
        LongFunction<A> next
    ) {
        return WithIntFunction.of(
            (int i) -> next.apply(resolve().applyAsLong(i))
        );
    }

    public default <A> WithIntFunction<A> fuse(LongFunction<A> next) {
        return fuseLongFunction(next);
    }

    /* IntToLongFunction -> LongToDoubleFunction */

    public default WithIntToDoubleFunction fuseLongToDoubleFunction(
        LongToDoubleFunction next
    ) {
        return WithIntToDoubleFunction.of(
            (int i) -> next.applyAsDouble(resolve().applyAsLong(i))
        );
    }

    public default WithIntToDoubleFunction fuse(LongToDoubleFunction next) {
        return fuseLongToDoubleFunction(next);
    }

    /* IntToLongFunction -> LongToIntFunction */

    public default WithIntUnaryOperator fuseLongToIntFunction(
        LongToIntFunction next
    ) {
        return WithIntUnaryOperator.of(
            (int i) -> next.applyAsInt(resolve().applyAsLong(i))
        );
    }

    public default WithIntUnaryOperator fuse(LongToIntFunction next) {
        return fuseLongToIntFunction(next);
    }

    /* IntToLongFunction -> LongPredicate */

    public default WithIntPredicate fuseLongPredicate(LongPredicate next) {
        return WithIntPredicate.of(
            (int i) -> next.test(resolve().applyAsLong(i))
        );
    }

    public default WithIntPredicate fuse(LongPredicate next) {
        return fuseLongPredicate(next);
    }

    /* IntToLongFunction -> LongConsumer */

    public default WithIntConsumer fuseLongConsumer(LongConsumer next) {
        return WithIntConsumer.of(
            (int i) -> next.accept(resolve().applyAsLong(i))
        );
    }

    public default WithIntConsumer fuse(LongConsumer next) {
        return fuseLongConsumer(next);
    }

    /* IntToLongFunction -> LongUnaryOperator */

    public default WithIntToLongFunction fuseLongUnaryOperator(
        LongUnaryOperator next
    ) {
        return WithIntToLongFunction.of(
            (int i) -> next.applyAsLong(resolve().applyAsLong(i))
        );
    }

    public default WithIntToLongFunction fuse(LongUnaryOperator next) {
        return fuseLongUnaryOperator(next);
    }

    /* IntToLongFunction -> LongBinaryOperator */

    public default IntFunction<LongUnaryOperator> fuseLongBinaryOperator(
        LongBinaryOperator next
    ) {
        return (int i) ->
            (long n) -> next.applyAsLong(resolve().applyAsLong(i), n);
    }

    public default IntFunction<LongUnaryOperator> fuse(
        LongBinaryOperator next
    ) {
        return fuseLongBinaryOperator(next);
    }
}
