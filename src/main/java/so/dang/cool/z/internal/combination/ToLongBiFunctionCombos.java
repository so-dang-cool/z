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
import so.dang.cool.z.internal.combination.Combine.WithBiConsumer;
import so.dang.cool.z.internal.combination.Combine.WithBiFunction;
import so.dang.cool.z.internal.combination.Combine.WithBiPredicate;
import so.dang.cool.z.internal.combination.Combine.WithToDoubleBiFunction;
import so.dang.cool.z.internal.combination.Combine.WithToIntBiFunction;
import so.dang.cool.z.internal.combination.Combine.WithToLongBiFunction;

interface ToLongBiFunctionCombos<A, B> {
    Function<A, ToLongFunction<B>> resolve();

    /* ToLongBiFunction<A, B> -> LongFunction<C> */

    public default <C> WithBiFunction<A, B, C> fuseLongFunction(
        LongFunction<C> next
    ) {
        return WithBiFunction.of(
            (A a) -> (B b) -> next.apply(resolve().apply(a).applyAsLong(b))
        );
    }

    public default <C> WithBiFunction<A, B, C> fuse(LongFunction<C> next) {
        return fuseLongFunction(next);
    }

    /* ToLongBiFunction<A, B> -> LongToDoubleFunction */

    public default WithToDoubleBiFunction<A, B> fuseLongToDoubleFunction(
        LongToDoubleFunction next
    ) {
        return WithToDoubleBiFunction.of(
            (A a) ->
                (B b) -> next.applyAsDouble(resolve().apply(a).applyAsLong(b))
        );
    }

    public default WithToDoubleBiFunction<A, B> fuse(
        LongToDoubleFunction next
    ) {
        return fuseLongToDoubleFunction(next);
    }

    /* ToLongBiFunction<A, B> -> LongToIntFunction */

    public default WithToIntBiFunction<A, B> fuseLongToIntFunction(
        LongToIntFunction next
    ) {
        return WithToIntBiFunction.of(
            (A a) -> (B b) -> next.applyAsInt(resolve().apply(a).applyAsLong(b))
        );
    }

    public default WithToIntBiFunction<A, B> fuse(LongToIntFunction next) {
        return fuseLongToIntFunction(next);
    }

    /* ToLongBiFunction<A, B> -> LongPredicate */

    public default WithBiPredicate<A, B> fuseLongPredicate(LongPredicate next) {
        return WithBiPredicate.of(
            (A a) -> (B b) -> next.test(resolve().apply(a).applyAsLong(b))
        );
    }

    public default WithBiPredicate<A, B> fuse(LongPredicate next) {
        return fuseLongPredicate(next);
    }

    /* ToLongBiFunction<A, B> -> LongConsumer */

    public default WithBiConsumer<A, B> fuseLongConsumer(LongConsumer next) {
        return WithBiConsumer.of(
            (A a) -> (B b) -> next.accept(resolve().apply(a).applyAsLong(b))
        );
    }

    public default WithBiConsumer<A, B> fuse(LongConsumer next) {
        return fuseLongConsumer(next);
    }

    /* ToLongBiFunction<A, B> -> LongUnaryOperator */

    public default WithToLongBiFunction<A, B> fuseLongUnaryOperator(
        LongUnaryOperator next
    ) {
        return WithToLongBiFunction.of(
            (A a) ->
                (B b) -> next.applyAsLong(resolve().apply(a).applyAsLong(b))
        );
    }

    public default WithToLongBiFunction<A, B> fuse(LongUnaryOperator next) {
        return fuseLongUnaryOperator(next);
    }

    /* ToLongBiFunction<A, B> -> LongBinaryOperator */

    public default Function<A, Function<B, LongUnaryOperator>> fuseLongBinaryOperator(
        LongBinaryOperator next
    ) {
        return (A a) ->
            (B b) ->
                (long n) ->
                    next.applyAsLong(resolve().apply(a).applyAsLong(b), n);
    }

    public default Function<A, Function<B, LongUnaryOperator>> fuse(
        LongBinaryOperator next
    ) {
        return fuseLongBinaryOperator(next);
    }
}
