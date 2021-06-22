package so.dang.cool.z.internal.combination;

import java.util.function.Consumer;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleFunction;
import java.util.function.DoublePredicate;
import java.util.function.DoubleToIntFunction;
import java.util.function.DoubleToLongFunction;
import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;
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

interface ToDoubleBiFunctionCombos<A, B> {
    Function<A, ToDoubleFunction<B>> resolve();

    /* ToDoubleBiFunction<A, B> -> DoubleFunction<C> */

    public default <C> Function<A, Function<B, C>> fuseDoubleFunction(
        DoubleFunction<C> next
    ) {
        return (A a) ->
            (B b) -> next.apply(resolve().apply(a).applyAsDouble(b));
    }

    public default <C> Function<A, Function<B, C>> fuse(
        DoubleFunction<C> next
    ) {
        return fuseDoubleFunction(next);
    }

    public default <C> WithBiFunction<A, B, C> fusingDoubleFunction(
        DoubleFunction<C> next
    ) {
        return WithBiFunction.of(fuseDoubleFunction(next));
    }

    public default <C> WithBiFunction<A, B, C> fusing(DoubleFunction<C> next) {
        return fusingDoubleFunction(next);
    }

    /* ToDoubleBiFunction<A, B> -> DoubleToIntFunction */

    public default Function<A, ToIntFunction<B>> fuseDoubleToIntFunction(
        DoubleToIntFunction next
    ) {
        return (A a) ->
            (B b) -> next.applyAsInt(resolve().apply(a).applyAsDouble(b));
    }

    public default Function<A, ToIntFunction<B>> fuse(
        DoubleToIntFunction next
    ) {
        return fuseDoubleToIntFunction(next);
    }

    public default WithToIntBiFunction<A, B> fusingDoubleToIntFunction(
        DoubleToIntFunction next
    ) {
        return WithToIntBiFunction.of(fuseDoubleToIntFunction(next));
    }

    public default WithToIntBiFunction<A, B> fusing(DoubleToIntFunction next) {
        return fusingDoubleToIntFunction(next);
    }

    /* ToDoubleBiFunction<A, B> -> DoubleToLongFunction */

    public default Function<A, ToLongFunction<B>> fuseDoubleToLongFunction(
        DoubleToLongFunction next
    ) {
        return (A a) ->
            (B b) -> next.applyAsLong(resolve().apply(a).applyAsDouble(b));
    }

    public default Function<A, ToLongFunction<B>> fuse(
        DoubleToLongFunction next
    ) {
        return fuseDoubleToLongFunction(next);
    }

    public default WithToLongBiFunction<A, B> fusingDoubleToLongFunction(
        DoubleToLongFunction next
    ) {
        return WithToLongBiFunction.of(fuseDoubleToLongFunction(next));
    }

    public default WithToLongBiFunction<A, B> fusing(
        DoubleToLongFunction next
    ) {
        return fusingDoubleToLongFunction(next);
    }

    /* ToDoubleBiFunction<A, B> -> DoublePredicate */

    public default Function<A, Predicate<B>> fuseDoublePredicate(
        DoublePredicate next
    ) {
        return (A a) -> (B b) -> next.test(resolve().apply(a).applyAsDouble(b));
    }

    public default Function<A, Predicate<B>> fuse(DoublePredicate next) {
        return fuseDoublePredicate(next);
    }

    public default WithBiPredicate<A, B> fusingDoublePredicate(
        DoublePredicate next
    ) {
        return WithBiPredicate.of(fuseDoublePredicate(next));
    }

    public default WithBiPredicate<A, B> fusing(DoublePredicate next) {
        return fusingDoublePredicate(next);
    }

    /* ToDoubleBiFunction<A, B> -> DoubleConsumer */

    public default Function<A, Consumer<B>> fuseDoubleConsumer(
        DoubleConsumer next
    ) {
        return (A a) ->
            (B b) -> next.accept(resolve().apply(a).applyAsDouble(b));
    }

    public default Function<A, Consumer<B>> fuse(DoubleConsumer next) {
        return fuseDoubleConsumer(next);
    }

    public default WithBiConsumer<A, B> fusingDoubleConsumer(
        DoubleConsumer next
    ) {
        return WithBiConsumer.of(fuseDoubleConsumer(next));
    }

    public default WithBiConsumer<A, B> fusing(DoubleConsumer next) {
        return fusingDoubleConsumer(next);
    }

    /* ToDoubleBiFunction<A, B> -> DoubleUnaryOperator */

    public default Function<A, ToDoubleFunction<B>> fuseDoubleUnaryOperator(
        DoubleUnaryOperator next
    ) {
        return (A a) ->
            (B b) -> next.applyAsDouble(resolve().apply(a).applyAsDouble(b));
    }

    public default Function<A, ToDoubleFunction<B>> fuse(
        DoubleUnaryOperator next
    ) {
        return fuseDoubleUnaryOperator(next);
    }

    public default WithToDoubleBiFunction<A, B> fusingDoubleUnaryOperator(
        DoubleUnaryOperator next
    ) {
        return WithToDoubleBiFunction.of(fuseDoubleUnaryOperator(next));
    }

    public default WithToDoubleBiFunction<A, B> fusing(
        DoubleUnaryOperator next
    ) {
        return fusingDoubleUnaryOperator(next);
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

    // TODO: Implement with currying
    // fusingDoubleBinaryOperator(DoubleBinaryOperator next) { ... }

    public default Function<A, ToDoubleFunction<B>> fuseDoubleBinaryOperator(
        DoubleBinaryOperator next,
        double secondArg
    ) {
        return (A a) ->
            (B b) ->
                next.applyAsDouble(
                    resolve().apply(a).applyAsDouble(b),
                    secondArg
                );
    }

    public default Function<A, ToDoubleFunction<B>> fuse(
        DoubleBinaryOperator next,
        double secondArg
    ) {
        return fuseDoubleBinaryOperator(next, secondArg);
    }

    public default WithToDoubleBiFunction<A, B> fusingDoubleBinaryOperator(
        DoubleBinaryOperator next,
        double secondArg
    ) {
        return WithToDoubleBiFunction.of(
            fuseDoubleBinaryOperator(next, secondArg)
        );
    }

    public default WithToDoubleBiFunction<A, B> fusing(
        DoubleBinaryOperator next,
        double secondArg
    ) {
        return fusingDoubleBinaryOperator(next, secondArg);
    }
}
