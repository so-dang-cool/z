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
import so.dang.cool.z.internal.combination.Combine.WithConsumer;
import so.dang.cool.z.internal.combination.Combine.WithFunction;
import so.dang.cool.z.internal.combination.Combine.WithPredicate;
import so.dang.cool.z.internal.combination.Combine.WithToDoubleFunction;
import so.dang.cool.z.internal.combination.Combine.WithToIntFunction;
import so.dang.cool.z.internal.combination.Combine.WithToLongFunction;

interface ToDoubleFunctionCombos<A> {
    ToDoubleFunction<A> resolve();

    /* ToDoubleFunction<A> -> DoubleFunction<B> */

    public default <B> Function<A, B> fuseDoubleFunction(
        DoubleFunction<B> next
    ) {
        return (A a) -> next.apply(resolve().applyAsDouble(a));
    }

    public default <B> Function<A, B> fuse(DoubleFunction<B> next) {
        return fuseDoubleFunction(next);
    }

    public default <B> WithFunction<A, B> fusingDoubleFunction(
        DoubleFunction<B> next
    ) {
        return WithFunction.of(fuseDoubleFunction(next));
    }

    public default <B> WithFunction<A, B> fusing(DoubleFunction<B> next) {
        return fusingDoubleFunction(next);
    }

    /* ToDoubleFunction -> DoubleToIntFunction */

    public default ToIntFunction<A> fuseDoubleToIntFunction(
        DoubleToIntFunction next
    ) {
        return (A a) -> next.applyAsInt(resolve().applyAsDouble(a));
    }

    public default ToIntFunction<A> fuse(DoubleToIntFunction next) {
        return fuseDoubleToIntFunction(next);
    }

    public default WithToIntFunction<A> fusingDoubleToIntFunction(
        DoubleToIntFunction next
    ) {
        return WithToIntFunction.of(fuseDoubleToIntFunction(next));
    }

    public default WithToIntFunction<A> fusing(DoubleToIntFunction next) {
        return fusingDoubleToIntFunction(next);
    }

    /* ToDoubleFunction -> DoubleToLongFunction */

    public default ToLongFunction<A> fuseDoubleToLongFunction(
        DoubleToLongFunction next
    ) {
        return (A a) -> next.applyAsLong(resolve().applyAsDouble(a));
    }

    public default ToLongFunction<A> fuse(DoubleToLongFunction next) {
        return fuseDoubleToLongFunction(next);
    }

    public default WithToLongFunction<A> fusingDoubleToLongFunction(
        DoubleToLongFunction next
    ) {
        return WithToLongFunction.of(fuseDoubleToLongFunction(next));
    }

    public default WithToLongFunction<A> fusing(DoubleToLongFunction next) {
        return fusingDoubleToLongFunction(next);
    }

    /* ToDoubleFunction -> DoublePredicate */

    public default Predicate<A> fuseDoublePredicate(DoublePredicate next) {
        return (A a) -> next.test(resolve().applyAsDouble(a));
    }

    public default Predicate<A> fuse(DoublePredicate next) {
        return fuseDoublePredicate(next);
    }

    public default WithPredicate<A> fusingDoublePredicate(
        DoublePredicate next
    ) {
        return WithPredicate.of(fuseDoublePredicate(next));
    }

    public default WithPredicate<A> fusing(DoublePredicate next) {
        return fusingDoublePredicate(next);
    }

    /* ToDoubleFunction -> DoubleConsumer */

    public default Consumer<A> fuseDoubleConsumer(DoubleConsumer next) {
        return (A a) -> next.accept(resolve().applyAsDouble(a));
    }

    public default Consumer<A> fuse(DoubleConsumer next) {
        return fuseDoubleConsumer(next);
    }

    public default WithConsumer<A> fusingDoubleConsumer(DoubleConsumer next) {
        return WithConsumer.of(fuseDoubleConsumer(next));
    }

    public default WithConsumer<A> fusing(DoubleConsumer next) {
        return fusingDoubleConsumer(next);
    }

    /* ToDoubleFunction -> DoubleUnaryOperator */

    public default ToDoubleFunction<A> fuseDoubleUnaryOperator(
        DoubleUnaryOperator next
    ) {
        return (A a) -> next.applyAsDouble(resolve().applyAsDouble(a));
    }

    public default ToDoubleFunction<A> fuse(DoubleUnaryOperator next) {
        return fuseDoubleUnaryOperator(next);
    }

    public default WithToDoubleFunction<A> fusingDoubleUnaryOperator(
        DoubleUnaryOperator next
    ) {
        return WithToDoubleFunction.of(fuseDoubleUnaryOperator(next));
    }

    public default WithToDoubleFunction<A> fusing(DoubleUnaryOperator next) {
        return fusingDoubleUnaryOperator(next);
    }

    /* ToDoubleFunction -> DoubleBinaryOperator */

    public default Function<A, DoubleUnaryOperator> fuseDoubleBinaryOperator(
        DoubleBinaryOperator next
    ) {
        return (A a) ->
            (double d) -> next.applyAsDouble(resolve().applyAsDouble(a), d);
    }

    public default Function<A, DoubleUnaryOperator> fuse(
        DoubleBinaryOperator next
    ) {
        return fuseDoubleBinaryOperator(next);
    }

    // TODO: Implement with currying
    // fusingDoubleBinaryOperator(DoubleBinaryOperator next) { ... }

    public default ToDoubleFunction<A> fuseDoubleBinaryOperator(
        DoubleBinaryOperator next,
        double secondArg
    ) {
        return (A a) ->
            next.applyAsDouble(resolve().applyAsDouble(a), secondArg);
    }

    public default ToDoubleFunction<A> fuse(
        DoubleBinaryOperator next,
        double secondArg
    ) {
        return fuseDoubleBinaryOperator(next, secondArg);
    }

    public default WithToDoubleFunction<A> fusingDoubleBinaryOperator(
        DoubleBinaryOperator next,
        double secondArg
    ) {
        return WithToDoubleFunction.of(
            fuseDoubleBinaryOperator(next, secondArg)
        );
    }

    public default WithToDoubleFunction<A> fusing(
        DoubleBinaryOperator next,
        double secondArg
    ) {
        return fusingDoubleBinaryOperator(next, secondArg);
    }
}
