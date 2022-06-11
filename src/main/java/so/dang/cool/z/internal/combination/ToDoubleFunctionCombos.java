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
import so.dang.cool.z.internal.combination.Combine.WithConsumer;
import so.dang.cool.z.internal.combination.Combine.WithFunction;
import so.dang.cool.z.internal.combination.Combine.WithPredicate;
import so.dang.cool.z.internal.combination.Combine.WithToDoubleFunction;
import so.dang.cool.z.internal.combination.Combine.WithToIntFunction;
import so.dang.cool.z.internal.combination.Combine.WithToLongFunction;

interface ToDoubleFunctionCombos<A> {
    ToDoubleFunction<A> resolve();

    /* ToDoubleFunction<A> -> DoubleFunction<B> */

    public default <B> WithFunction<A, B> fuseDoubleFunction(
        DoubleFunction<B> next
    ) {
        return WithFunction.of((A a) -> next.apply(resolve().applyAsDouble(a)));
    }

    public default <B> WithFunction<A, B> fuse(DoubleFunction<B> next) {
        return fuseDoubleFunction(next);
    }

    /* ToDoubleFunction -> DoubleToIntFunction */

    public default WithToIntFunction<A> fuseDoubleToIntFunction(
        DoubleToIntFunction next
    ) {
        return WithToIntFunction.of(
            (A a) -> next.applyAsInt(resolve().applyAsDouble(a))
        );
    }

    public default WithToIntFunction<A> fuse(DoubleToIntFunction next) {
        return fuseDoubleToIntFunction(next);
    }

    /* ToDoubleFunction -> DoubleToLongFunction */

    public default WithToLongFunction<A> fuseDoubleToLongFunction(
        DoubleToLongFunction next
    ) {
        return WithToLongFunction.of(
            (A a) -> next.applyAsLong(resolve().applyAsDouble(a))
        );
    }

    public default WithToLongFunction<A> fuse(DoubleToLongFunction next) {
        return fuseDoubleToLongFunction(next);
    }

    /* ToDoubleFunction -> DoublePredicate */

    public default WithPredicate<A> fuseDoublePredicate(DoublePredicate next) {
        return WithPredicate.of((A a) -> next.test(resolve().applyAsDouble(a)));
    }

    public default WithPredicate<A> fuse(DoublePredicate next) {
        return fuseDoublePredicate(next);
    }

    /* ToDoubleFunction -> DoubleConsumer */

    public default WithConsumer<A> fuseDoubleConsumer(DoubleConsumer next) {
        return WithConsumer.of(
            (A a) -> next.accept(resolve().applyAsDouble(a))
        );
    }

    public default WithConsumer<A> fuse(DoubleConsumer next) {
        return fuseDoubleConsumer(next);
    }

    /* ToDoubleFunction -> DoubleUnaryOperator */

    public default WithToDoubleFunction<A> fuseDoubleUnaryOperator(
        DoubleUnaryOperator next
    ) {
        return WithToDoubleFunction.of(
            (A a) -> next.applyAsDouble(resolve().applyAsDouble(a))
        );
    }

    public default WithToDoubleFunction<A> fuse(DoubleUnaryOperator next) {
        return fuseDoubleUnaryOperator(next);
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
}
