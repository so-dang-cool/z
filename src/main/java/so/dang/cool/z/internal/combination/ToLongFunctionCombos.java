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
import so.dang.cool.z.internal.combination.Combine.WithConsumer;
import so.dang.cool.z.internal.combination.Combine.WithFunction;
import so.dang.cool.z.internal.combination.Combine.WithPredicate;
import so.dang.cool.z.internal.combination.Combine.WithToDoubleFunction;
import so.dang.cool.z.internal.combination.Combine.WithToIntFunction;
import so.dang.cool.z.internal.combination.Combine.WithToLongFunction;

interface ToLongFunctionCombos<A> {
    ToLongFunction<A> resolve();

    /* ToLongFunction<A> -> LongFunction<B> */

    public default <B> Function<A, B> fuseLongFunction(LongFunction<B> next) {
        return (A a) -> next.apply(resolve().applyAsLong(a));
    }

    public default <B> Function<A, B> fuse(LongFunction<B> next) {
        return fuseLongFunction(next);
    }

    public default <B> WithFunction<A, B> fusingLongFunction(
        LongFunction<B> next
    ) {
        return WithFunction.of(fuseLongFunction(next));
    }

    public default <B> WithFunction<A, B> fusing(LongFunction<B> next) {
        return fusingLongFunction(next);
    }

    /* ToLongFunction<A> -> LongToDoubleFunction */

    public default ToDoubleFunction<A> fuseLongToDoubleFunction(
        LongToDoubleFunction next
    ) {
        return (A a) -> next.applyAsDouble(resolve().applyAsLong(a));
    }

    public default ToDoubleFunction<A> fuse(LongToDoubleFunction next) {
        return fuseLongToDoubleFunction(next);
    }

    public default WithToDoubleFunction<A> fusingLongToDoubleFunction(
        LongToDoubleFunction next
    ) {
        return WithToDoubleFunction.of(fuseLongToDoubleFunction(next));
    }

    public default WithToDoubleFunction<A> fusing(LongToDoubleFunction next) {
        return fusingLongToDoubleFunction(next);
    }

    /* ToLongFunction<A> -> LongToIntFunction */

    public default ToIntFunction<A> fuseLongToIntFunction(
        LongToIntFunction next
    ) {
        return (A a) -> next.applyAsInt(resolve().applyAsLong(a));
    }

    public default ToIntFunction<A> fuse(LongToIntFunction next) {
        return fuseLongToIntFunction(next);
    }

    public default WithToIntFunction<A> fusingLongToIntFunction(
        LongToIntFunction next
    ) {
        return WithToIntFunction.of(fuseLongToIntFunction(next));
    }

    public default WithToIntFunction<A> fusing(LongToIntFunction next) {
        return fusingLongToIntFunction(next);
    }

    /* ToLongFunction<A> -> LongPredicate */

    public default Predicate<A> fuseLongPredicate(LongPredicate next) {
        return (A a) -> next.test(resolve().applyAsLong(a));
    }

    public default Predicate<A> fuse(LongPredicate next) {
        return fuseLongPredicate(next);
    }

    public default WithPredicate<A> fusingLongPredicate(LongPredicate next) {
        return WithPredicate.of(fuseLongPredicate(next));
    }

    public default WithPredicate<A> fusing(LongPredicate next) {
        return fusingLongPredicate(next);
    }

    /* ToLongFunction<A> -> LongConsumer */

    public default Consumer<A> fuseLongConsumer(LongConsumer next) {
        return (A a) -> next.accept(resolve().applyAsLong(a));
    }

    public default Consumer<A> fuse(LongConsumer next) {
        return fuseLongConsumer(next);
    }

    public default WithConsumer<A> fusingLongConsumer(LongConsumer next) {
        return WithConsumer.of(fuseLongConsumer(next));
    }

    public default WithConsumer<A> fusing(LongConsumer next) {
        return fusingLongConsumer(next);
    }

    /* ToLongFunction<A> -> LongUnaryOperator */

    public default ToLongFunction<A> fuseLongUnaryOperator(
        LongUnaryOperator next
    ) {
        return (A a) -> next.applyAsLong(resolve().applyAsLong(a));
    }

    public default ToLongFunction<A> fuse(LongUnaryOperator next) {
        return fuseLongUnaryOperator(next);
    }

    public default WithToLongFunction<A> fusingLongUnaryOperator(
        LongUnaryOperator next
    ) {
        return WithToLongFunction.of(fuseLongUnaryOperator(next));
    }

    public default WithToLongFunction<A> fusing(LongUnaryOperator next) {
        return fusingLongUnaryOperator(next);
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

    // TODO: Implement with currying
    // fusingLongBinaryOperator(LongBinaryOperator next) { ... }

    public default ToLongFunction<A> fuseLongBinaryOperator(
        LongBinaryOperator next,
        long secondArg
    ) {
        return (A a) -> next.applyAsLong(resolve().applyAsLong(a), secondArg);
    }

    public default ToLongFunction<A> fuse(
        LongBinaryOperator next,
        long secondArg
    ) {
        return fuseLongBinaryOperator(next, secondArg);
    }

    public default WithToLongFunction<A> fusingLongBinaryOperator(
        LongBinaryOperator next,
        long secondArg
    ) {
        return WithToLongFunction.of(fuseLongBinaryOperator(next, secondArg));
    }

    public default WithToLongFunction<A> fusing(
        LongBinaryOperator next,
        long secondArg
    ) {
        return fusingLongBinaryOperator(next, secondArg);
    }
}
