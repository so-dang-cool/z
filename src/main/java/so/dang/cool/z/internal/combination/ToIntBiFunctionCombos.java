package so.dang.cool.z.internal.combination;

import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntToLongFunction;
import java.util.function.IntUnaryOperator;
import java.util.function.ToIntFunction;
import so.dang.cool.z.internal.combination.Combine.WithBiConsumer;
import so.dang.cool.z.internal.combination.Combine.WithBiFunction;
import so.dang.cool.z.internal.combination.Combine.WithBiPredicate;
import so.dang.cool.z.internal.combination.Combine.WithToDoubleBiFunction;
import so.dang.cool.z.internal.combination.Combine.WithToIntBiFunction;
import so.dang.cool.z.internal.combination.Combine.WithToLongBiFunction;

interface ToIntBiFunctionCombos<A, B> {
    Function<A, ToIntFunction<B>> resolve();

    /* ToIntBiFunction<A, B> -> IntFunction<C> */

    public default <C> WithBiFunction<A, B, C> fuseIntFunction(
        IntFunction<C> next
    ) {
        return WithBiFunction.of(
            (A a) -> (B b) -> next.apply(resolve().apply(a).applyAsInt(b))
        );
    }

    public default <C> WithBiFunction<A, B, C> fuse(IntFunction<C> next) {
        return fuseIntFunction(next);
    }

    /* ToIntBiFunction<A, B> -> IntToDoubleFunction */

    public default WithToDoubleBiFunction<A, B> fuseIntToDoubleFunction(
        IntToDoubleFunction next
    ) {
        return WithToDoubleBiFunction.of(
            (A a) ->
                (B b) -> next.applyAsDouble(resolve().apply(a).applyAsInt(b))
        );
    }

    public default WithToDoubleBiFunction<A, B> fuse(IntToDoubleFunction next) {
        return fuseIntToDoubleFunction(next);
    }

    /* ToIntBiFunction<A, B> -> IntToLongFunction */

    public default WithToLongBiFunction<A, B> fuseIntToLongFunction(
        IntToLongFunction next
    ) {
        return WithToLongBiFunction.of(
            (A a) -> (B b) -> next.applyAsLong(resolve().apply(a).applyAsInt(b))
        );
    }

    public default WithToLongBiFunction<A, B> fuse(IntToLongFunction next) {
        return fuseIntToLongFunction(next);
    }

    /* ToIntBiFunction<A, B> -> IntPredicate */

    public default WithBiPredicate<A, B> fuseIntPredicate(IntPredicate next) {
        return WithBiPredicate.of(
            (A a) -> (B b) -> next.test(resolve().apply(a).applyAsInt(b))
        );
    }

    public default WithBiPredicate<A, B> fuse(IntPredicate next) {
        return fuseIntPredicate(next);
    }

    /* ToIntBiFunction<A, B> -> IntConsumer */

    public default WithBiConsumer<A, B> fuseIntConsumer(IntConsumer next) {
        return WithBiConsumer.of(
            (A a) -> (B b) -> next.accept(resolve().apply(a).applyAsInt(b))
        );
    }

    public default WithBiConsumer<A, B> fuse(IntConsumer next) {
        return fuseIntConsumer(next);
    }

    /* ToIntBiFunction<A, B> -> IntUnaryOperator */

    public default WithToIntBiFunction<A, B> fuseIntUnaryOperator(
        IntUnaryOperator next
    ) {
        return WithToIntBiFunction.of(
            (A a) -> (B b) -> next.applyAsInt(resolve().apply(a).applyAsInt(b))
        );
    }

    public default WithToIntBiFunction<A, B> fuse(IntUnaryOperator next) {
        return fuseIntUnaryOperator(next);
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
}
