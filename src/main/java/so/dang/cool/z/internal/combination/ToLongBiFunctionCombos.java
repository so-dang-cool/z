package so.dang.cool.z.internal.combination;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.LongBinaryOperator;
import java.util.function.LongConsumer;
import java.util.function.LongFunction;
import java.util.function.LongPredicate;
import java.util.function.LongToDoubleFunction;
import java.util.function.LongToIntFunction;
import java.util.function.LongUnaryOperator;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
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

    public default <C> Function<A, Function<B, C>> fuseLongFunction(
        LongFunction<C> next
    ) {
        return (A a) -> (B b) -> next.apply(resolve().apply(a).applyAsLong(b));
    }

    public default <C> Function<A, Function<B, C>> fuse(LongFunction<C> next) {
        return fuseLongFunction(next);
    }

    public default <C> WithBiFunction<A, B, C> fusingLongFunction(
        LongFunction<C> next
    ) {
        return WithBiFunction.of(fuseLongFunction(next));
    }

    public default <C> WithBiFunction<A, B, C> fusing(LongFunction<C> next) {
        return fusingLongFunction(next);
    }

    /* ToLongBiFunction<A, B> -> LongToDoubleFunction */

    public default Function<A, ToDoubleFunction<B>> fuseLongToDoubleFunction(
        LongToDoubleFunction next
    ) {
        return (A a) ->
            (B b) -> next.applyAsDouble(resolve().apply(a).applyAsLong(b));
    }

    public default Function<A, ToDoubleFunction<B>> fuse(
        LongToDoubleFunction next
    ) {
        return fuseLongToDoubleFunction(next);
    }

    public default WithToDoubleBiFunction<A, B> fusingLongToDoubleFunction(
        LongToDoubleFunction next
    ) {
        return WithToDoubleBiFunction.of(fuseLongToDoubleFunction(next));
    }

    public default WithToDoubleBiFunction<A, B> fusing(
        LongToDoubleFunction next
    ) {
        return fusingLongToDoubleFunction(next);
    }

    /* ToLongBiFunction<A, B> -> LongToIntFunction */

    public default Function<A, ToIntFunction<B>> fuseLongToIntFunction(
        LongToIntFunction next
    ) {
        return (A a) ->
            (B b) -> next.applyAsInt(resolve().apply(a).applyAsLong(b));
    }

    public default Function<A, ToIntFunction<B>> fuse(LongToIntFunction next) {
        return fuseLongToIntFunction(next);
    }

    public default WithToIntBiFunction<A, B> fusingLongToIntFunction(
        LongToIntFunction next
    ) {
        return WithToIntBiFunction.of(fuseLongToIntFunction(next));
    }

    public default WithToIntBiFunction<A, B> fusing(LongToIntFunction next) {
        return fusingLongToIntFunction(next);
    }

    /* ToLongBiFunction<A, B> -> LongPredicate */

    public default Function<A, Predicate<B>> fuseLongPredicate(
        LongPredicate next
    ) {
        return (A a) -> (B b) -> next.test(resolve().apply(a).applyAsLong(b));
    }

    public default Function<A, Predicate<B>> fuse(LongPredicate next) {
        return fuseLongPredicate(next);
    }

    public default WithBiPredicate<A, B> fusingLongPredicate(
        LongPredicate next
    ) {
        return WithBiPredicate.of(fuseLongPredicate(next));
    }

    public default WithBiPredicate<A, B> fusing(LongPredicate next) {
        return fusingLongPredicate(next);
    }

    /* ToLongBiFunction<A, B> -> LongConsumer */

    public default Function<A, Consumer<B>> fuseLongConsumer(
        LongConsumer next
    ) {
        return (A a) -> (B b) -> next.accept(resolve().apply(a).applyAsLong(b));
    }

    public default Function<A, Consumer<B>> fuse(LongConsumer next) {
        return fuseLongConsumer(next);
    }

    public default WithBiConsumer<A, B> fusingLongConsumer(LongConsumer next) {
        return WithBiConsumer.of(fuseLongConsumer(next));
    }

    public default WithBiConsumer<A, B> fusing(LongConsumer next) {
        return fusingLongConsumer(next);
    }

    /* ToLongBiFunction<A, B> -> LongUnaryOperator */

    public default Function<A, ToLongFunction<B>> fuseLongUnaryOperator(
        LongUnaryOperator next
    ) {
        return (A a) ->
            (B b) -> next.applyAsLong(resolve().apply(a).applyAsLong(b));
    }

    public default Function<A, ToLongFunction<B>> fuse(LongUnaryOperator next) {
        return fuseLongUnaryOperator(next);
    }

    public default WithToLongBiFunction<A, B> fusingLongUnaryOperator(
        LongUnaryOperator next
    ) {
        return WithToLongBiFunction.of(fuseLongUnaryOperator(next));
    }

    public default WithToLongBiFunction<A, B> fusing(LongUnaryOperator next) {
        return fusingLongUnaryOperator(next);
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

    // TODO: Implement with currying
    // fusingLongBinaryOperator(LongBinaryOperator next) { ... }

    public default Function<A, ToLongFunction<B>> fuseLongBinaryOperator(
        LongBinaryOperator next,
        long secondArg
    ) {
        return (A a) ->
            (B b) ->
                next.applyAsLong(resolve().apply(a).applyAsLong(b), secondArg);
    }

    public default Function<A, ToLongFunction<B>> fuse(
        LongBinaryOperator next,
        long secondArg
    ) {
        return fuseLongBinaryOperator(next, secondArg);
    }

    public default WithToLongBiFunction<A, B> fusingLongBinaryOperator(
        LongBinaryOperator next,
        long secondArg
    ) {
        return WithToLongBiFunction.of(fuseLongBinaryOperator(next, secondArg));
    }

    public default WithToLongBiFunction<A, B> fusing(
        LongBinaryOperator next,
        long secondArg
    ) {
        return fusingLongBinaryOperator(next, secondArg);
    }
}
