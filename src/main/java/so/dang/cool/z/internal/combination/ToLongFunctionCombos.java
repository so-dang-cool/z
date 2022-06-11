package so.dang.cool.z.internal.combination;

import java.util.function.Function;
import java.util.function.LongBinaryOperator;
import java.util.function.LongConsumer;
import java.util.function.LongFunction;
import java.util.function.LongPredicate;
import java.util.function.LongToDoubleFunction;
import java.util.function.LongToIntFunction;
import java.util.function.LongUnaryOperator;
import java.util.function.ToLongFunction;
import so.dang.cool.z.internal.combination.Combine.WithConsumer;
import so.dang.cool.z.internal.combination.Combine.WithFunction;
import so.dang.cool.z.internal.combination.Combine.WithPredicate;
import so.dang.cool.z.internal.combination.Combine.WithToDoubleFunction;
import so.dang.cool.z.internal.combination.Combine.WithToIntFunction;
import so.dang.cool.z.internal.combination.Combine.WithToLongFunction;

interface ToLongFunctionCombos<A> {
    ToLongFunction<A> resolve();

    /* ToLongFunction<A> -> LongFunction<B> */

    public default <B> WithFunction<A, B> fuseLongFunction(
        LongFunction<B> next
    ) {
        return WithFunction.of((A a) -> next.apply(resolve().applyAsLong(a)));
    }

    public default <B> WithFunction<A, B> fuse(LongFunction<B> next) {
        return fuseLongFunction(next);
    }

    /* ToLongFunction<A> -> LongToDoubleFunction */

    public default WithToDoubleFunction<A> fuseLongToDoubleFunction(
        LongToDoubleFunction next
    ) {
        return WithToDoubleFunction.of(
            (A a) -> next.applyAsDouble(resolve().applyAsLong(a))
        );
    }

    public default WithToDoubleFunction<A> fuse(LongToDoubleFunction next) {
        return fuseLongToDoubleFunction(next);
    }

    /* ToLongFunction<A> -> LongToIntFunction */

    public default WithToIntFunction<A> fuseLongToIntFunction(
        LongToIntFunction next
    ) {
        return WithToIntFunction.of(
            (A a) -> next.applyAsInt(resolve().applyAsLong(a))
        );
    }

    public default WithToIntFunction<A> fuse(LongToIntFunction next) {
        return fuseLongToIntFunction(next);
    }

    /* ToLongFunction<A> -> LongPredicate */

    public default WithPredicate<A> fuseLongPredicate(LongPredicate next) {
        return WithPredicate.of((A a) -> next.test(resolve().applyAsLong(a)));
    }

    public default WithPredicate<A> fuse(LongPredicate next) {
        return fuseLongPredicate(next);
    }

    /* ToLongFunction<A> -> LongConsumer */

    public default WithConsumer<A> fuseLongConsumer(LongConsumer next) {
        return WithConsumer.of((A a) -> next.accept(resolve().applyAsLong(a)));
    }

    public default WithConsumer<A> fuse(LongConsumer next) {
        return fuseLongConsumer(next);
    }

    /* ToLongFunction<A> -> LongUnaryOperator */

    public default WithToLongFunction<A> fuseLongUnaryOperator(
        LongUnaryOperator next
    ) {
        return WithToLongFunction.of(
            (A a) -> next.applyAsLong(resolve().applyAsLong(a))
        );
    }

    public default WithToLongFunction<A> fuse(LongUnaryOperator next) {
        return fuseLongUnaryOperator(next);
    }

    /* ToLongFunction<A> -> LongBinaryOperator */

    public default Function<A, LongUnaryOperator> fuseLongBinaryOperator(
        LongBinaryOperator next
    ) {
        return (A a) ->
            (long n) -> next.applyAsLong(resolve().applyAsLong(a), n);
    }

    public default Function<A, LongUnaryOperator> fuse(
        LongBinaryOperator next
    ) {
        return fuseLongBinaryOperator(next);
    }
}
