package so.dang.cool.z.internal.combination;

import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleFunction;
import java.util.function.DoublePredicate;
import java.util.function.DoubleToIntFunction;
import java.util.function.DoubleToLongFunction;
import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import so.dang.cool.z.internal.combination.Combine.WithBiConsumer;
import so.dang.cool.z.internal.combination.Combine.WithBiFunction;
import so.dang.cool.z.internal.combination.Combine.WithBiPredicate;
import so.dang.cool.z.internal.combination.Combine.WithToDoubleBiFunction;
import so.dang.cool.z.internal.combination.Combine.WithToIntBiFunction;
import so.dang.cool.z.internal.combination.Combine.WithToLongBiFunction;

interface ToDoubleBiFunctionCombos<A, B> {
    Function<A, ToDoubleFunction<B>> resolve();

    /* ToDoubleBiFunction<A, B> -> DoubleFunction<C> */

    public default <C> WithBiFunction<A, B, C> fuseDoubleFunction(
        DoubleFunction<C> next
    ) {
        return WithBiFunction.of(
            (A a) -> (B b) -> next.apply(resolve().apply(a).applyAsDouble(b))
        );
    }

    public default <C> WithBiFunction<A, B, C> fuse(DoubleFunction<C> next) {
        return fuseDoubleFunction(next);
    }

    /* ToDoubleBiFunction<A, B> -> DoubleToIntFunction */

    public default WithToIntBiFunction<A, B> fuseDoubleToIntFunction(
        DoubleToIntFunction next
    ) {
        return WithToIntBiFunction.of(
            (A a) ->
                (B b) -> next.applyAsInt(resolve().apply(a).applyAsDouble(b))
        );
    }

    public default WithToIntBiFunction<A, B> fuse(DoubleToIntFunction next) {
        return fuseDoubleToIntFunction(next);
    }

    /* ToDoubleBiFunction<A, B> -> DoubleToLongFunction */

    public default WithToLongBiFunction<A, B> fuseDoubleToLongFunction(
        DoubleToLongFunction next
    ) {
        return WithToLongBiFunction.of(
            (A a) ->
                (B b) -> next.applyAsLong(resolve().apply(a).applyAsDouble(b))
        );
    }

    public default WithToLongBiFunction<A, B> fuse(DoubleToLongFunction next) {
        return fuseDoubleToLongFunction(next);
    }

    /* ToDoubleBiFunction<A, B> -> DoublePredicate */

    public default WithBiPredicate<A, B> fuseDoublePredicate(
        DoublePredicate next
    ) {
        return WithBiPredicate.of(
            (A a) -> (B b) -> next.test(resolve().apply(a).applyAsDouble(b))
        );
    }

    public default WithBiPredicate<A, B> fuse(DoublePredicate next) {
        return fuseDoublePredicate(next);
    }

    /* ToDoubleBiFunction<A, B> -> DoubleConsumer */

    public default WithBiConsumer<A, B> fuseDoubleConsumer(
        DoubleConsumer next
    ) {
        return WithBiConsumer.of(
            (A a) -> (B b) -> next.accept(resolve().apply(a).applyAsDouble(b))
        );
    }

    public default WithBiConsumer<A, B> fuse(DoubleConsumer next) {
        return fuseDoubleConsumer(next);
    }

    /* ToDoubleBiFunction<A, B> -> DoubleUnaryOperator */

    public default WithToDoubleBiFunction<A, B> fuseDoubleUnaryOperator(
        DoubleUnaryOperator next
    ) {
        return WithToDoubleBiFunction.of(
            (A a) ->
                (B b) -> next.applyAsDouble(resolve().apply(a).applyAsDouble(b))
        );
    }

    public default WithToDoubleBiFunction<A, B> fuse(DoubleUnaryOperator next) {
        return fuseDoubleUnaryOperator(next);
    }

    /* ToDoubleBiFunction<A, B> -> DoubleBinaryOperator */

    public default Function<A, Function<B, DoubleUnaryOperator>> fuseDoubleBinaryOperator(
        DoubleBinaryOperator next
    ) {
        return (A a) ->
            (B b) ->
                (double d) ->
                    next.applyAsDouble(resolve().apply(a).applyAsDouble(b), d);
    }

    public default Function<A, Function<B, DoubleUnaryOperator>> fuse(
        DoubleBinaryOperator next
    ) {
        return fuseDoubleBinaryOperator(next);
    }
}
