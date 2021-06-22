package so.dang.cool.z.internal.combination;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntToLongFunction;
import java.util.function.IntUnaryOperator;
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

interface ToIntBiFunctionCombos<A, B> {
    Function<A, ToIntFunction<B>> resolve();

    /* ToIntBiFunction<A, B> -> IntFunction<C> */

    public default <C> Function<A, Function<B, C>> fuseIntFunction(
        IntFunction<C> next
    ) {
        return (A a) -> (B b) -> next.apply(resolve().apply(a).applyAsInt(b));
    }

    public default <C> Function<A, Function<B, C>> fuse(IntFunction<C> next) {
        return fuseIntFunction(next);
    }

    public default <C> WithBiFunction<A, B, C> fusingIntFunction(
        IntFunction<C> next
    ) {
        return WithBiFunction.of(fuseIntFunction(next));
    }

    public default <C> WithBiFunction<A, B, C> fusing(IntFunction<C> next) {
        return fusingIntFunction(next);
    }

    /* ToIntBiFunction<A, B> -> IntToDoubleFunction */

    public default Function<A, ToDoubleFunction<B>> fuseIntToDoubleFunction(
        IntToDoubleFunction next
    ) {
        return (A a) ->
            (B b) -> next.applyAsDouble(resolve().apply(a).applyAsInt(b));
    }

    public default Function<A, ToDoubleFunction<B>> fuse(
        IntToDoubleFunction next
    ) {
        return fuseIntToDoubleFunction(next);
    }

    public default WithToDoubleBiFunction<A, B> fusingIntToDoubleFunction(
        IntToDoubleFunction next
    ) {
        return WithToDoubleBiFunction.of(fuseIntToDoubleFunction(next));
    }

    public default WithToDoubleBiFunction<A, B> fusing(
        IntToDoubleFunction next
    ) {
        return fusingIntToDoubleFunction(next);
    }

    /* ToIntBiFunction<A, B> -> IntToLongFunction */

    public default Function<A, ToLongFunction<B>> fuseIntToLongFunction(
        IntToLongFunction next
    ) {
        return (A a) ->
            (B b) -> next.applyAsLong(resolve().apply(a).applyAsInt(b));
    }

    public default Function<A, ToLongFunction<B>> fuse(IntToLongFunction next) {
        return fuseIntToLongFunction(next);
    }

    public default WithToLongBiFunction<A, B> fusingIntToLongFunction(
        IntToLongFunction next
    ) {
        return WithToLongBiFunction.of(fuseIntToLongFunction(next));
    }

    public default WithToLongBiFunction<A, B> fusing(IntToLongFunction next) {
        return fusingIntToLongFunction(next);
    }

    /* ToIntBiFunction<A, B> -> IntPredicate */

    public default Function<A, Predicate<B>> fuseIntPredicate(
        IntPredicate next
    ) {
        return (A a) -> (B b) -> next.test(resolve().apply(a).applyAsInt(b));
    }

    public default Function<A, Predicate<B>> fuse(IntPredicate next) {
        return fuseIntPredicate(next);
    }

    public default WithBiPredicate<A, B> fusingIntPredicate(IntPredicate next) {
        return WithBiPredicate.of(fuseIntPredicate(next));
    }

    public default WithBiPredicate<A, B> fusing(IntPredicate next) {
        return fusingIntPredicate(next);
    }

    /* ToIntBiFunction<A, B> -> IntConsumer */

    public default Function<A, Consumer<B>> fuseIntConsumer(IntConsumer next) {
        return (A a) -> (B b) -> next.accept(resolve().apply(a).applyAsInt(b));
    }

    public default Function<A, Consumer<B>> fuse(IntConsumer next) {
        return fuseIntConsumer(next);
    }

    public default WithBiConsumer<A, B> fusingIntConsumer(IntConsumer next) {
        return WithBiConsumer.of(fuseIntConsumer(next));
    }

    public default WithBiConsumer<A, B> fusing(IntConsumer next) {
        return fusingIntConsumer(next);
    }

    /* ToIntBiFunction<A, B> -> IntUnaryOperator */

    public default Function<A, ToIntFunction<B>> fuseIntUnaryOperator(
        IntUnaryOperator next
    ) {
        return (A a) ->
            (B b) -> next.applyAsInt(resolve().apply(a).applyAsInt(b));
    }

    public default Function<A, ToIntFunction<B>> fuse(IntUnaryOperator next) {
        return fuseIntUnaryOperator(next);
    }

    public default WithToIntBiFunction<A, B> fusingIntUnaryOperator(
        IntUnaryOperator next
    ) {
        return WithToIntBiFunction.of(fuseIntUnaryOperator(next));
    }

    public default WithToIntBiFunction<A, B> fusing(IntUnaryOperator next) {
        return fusingIntUnaryOperator(next);
    }

    /* ToIntBiFunction<A, B> -> IntBinaryOperator */

    public default Function<A, Function<B, IntUnaryOperator>> fuseIntBinaryOperator(
        IntBinaryOperator next
    ) {
        return (A a) ->
            (B b) ->
                (int i) -> next.applyAsInt(resolve().apply(a).applyAsInt(b), i);
    }

    public default Function<A, Function<B, IntUnaryOperator>> fuse(
        IntBinaryOperator next
    ) {
        return fuseIntBinaryOperator(next);
    }

    // TODO: Implement with currying
    // fusingIntBinaryOperator(IntBinaryOperator next) { ... }

    public default Function<A, ToIntFunction<B>> fuseIntBinaryOperator(
        IntBinaryOperator next,
        int secondArg
    ) {
        return (A a) ->
            (B b) ->
                next.applyAsInt(resolve().apply(a).applyAsInt(b), secondArg);
    }

    public default Function<A, ToIntFunction<B>> fuse(
        IntBinaryOperator next,
        int secondArg
    ) {
        return fuseIntBinaryOperator(next, secondArg);
    }

    public default WithToIntBiFunction<A, B> fusingIntBinaryOperator(
        IntBinaryOperator next,
        int secondArg
    ) {
        return WithToIntBiFunction.of(fuseIntBinaryOperator(next, secondArg));
    }

    public default WithToIntBiFunction<A, B> fusing(
        IntBinaryOperator next,
        int secondArg
    ) {
        return fusingIntBinaryOperator(next, secondArg);
    }
}
