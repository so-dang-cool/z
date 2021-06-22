package so.dang.cool.z.internal.combination;

import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleFunction;
import java.util.function.DoublePredicate;
import java.util.function.DoubleToIntFunction;
import java.util.function.DoubleToLongFunction;
import java.util.function.DoubleUnaryOperator;
import so.dang.cool.z.internal.combination.Combine.WithDoubleBinaryOperator;
import so.dang.cool.z.internal.combination.Combine.WithDoubleConsumer;
import so.dang.cool.z.internal.combination.Combine.WithDoubleFunction;
import so.dang.cool.z.internal.combination.Combine.WithDoublePredicate;
import so.dang.cool.z.internal.combination.Combine.WithDoubleToIntFunction;
import so.dang.cool.z.internal.combination.Combine.WithDoubleToLongFunction;
import so.dang.cool.z.internal.combination.Combine.WithDoubleUnaryOperator;

interface DoubleUnaryOperatorCombos {
    DoubleUnaryOperator resolve();

    /* DoubleUnaryOperator -> DoubleFunction<A> */

    public default <A> DoubleFunction<A> fuseDoubleFunction(
        DoubleFunction<A> next
    ) {
        return (double d) -> next.apply(resolve().applyAsDouble(d));
    }

    public default <A> DoubleFunction<A> fuse(DoubleFunction<A> next) {
        return fuseDoubleFunction(next);
    }

    public default <A> WithDoubleFunction<A> fusingDoubleFunction(
        DoubleFunction<A> next
    ) {
        return WithDoubleFunction.of(fuseDoubleFunction(next));
    }

    public default <A> WithDoubleFunction<A> fusing(DoubleFunction<A> next) {
        return fusingDoubleFunction(next);
    }

    /* DoubleUnaryOperator -> DoubleToIntFunction */

    public default DoubleToIntFunction fuseDoubleToIntFunction(
        DoubleToIntFunction next
    ) {
        return (double d) -> next.applyAsInt(resolve().applyAsDouble(d));
    }

    public default DoubleToIntFunction fuse(DoubleToIntFunction next) {
        return fuseDoubleToIntFunction(next);
    }

    public default WithDoubleToIntFunction fusingDoubleToIntFunction(
        DoubleToIntFunction next
    ) {
        return WithDoubleToIntFunction.of(fuseDoubleToIntFunction(next));
    }

    public default WithDoubleToIntFunction fusing(DoubleToIntFunction next) {
        return fusingDoubleToIntFunction(next);
    }

    /* DoubleUnaryOperator -> DoubleToLongFunction */

    public default DoubleToLongFunction fuseDoubleToLongFunction(
        DoubleToLongFunction next
    ) {
        return (double d) -> next.applyAsLong(resolve().applyAsDouble(d));
    }

    public default DoubleToLongFunction fuse(DoubleToLongFunction next) {
        return fuseDoubleToLongFunction(next);
    }

    public default WithDoubleToLongFunction fusingDoubleToLongFunction(
        DoubleToLongFunction next
    ) {
        return WithDoubleToLongFunction.of(fuseDoubleToLongFunction(next));
    }

    public default WithDoubleToLongFunction fusing(DoubleToLongFunction next) {
        return fusingDoubleToLongFunction(next);
    }

    /* DoubleUnaryOperator -> DoublePredicate */

    public default DoublePredicate fuseDoublePredicate(DoublePredicate next) {
        return (double d) -> next.test(resolve().applyAsDouble(d));
    }

    public default DoublePredicate fuse(DoublePredicate next) {
        return fuseDoublePredicate(next);
    }

    public default WithDoublePredicate fusingDoublePredicate(
        DoublePredicate next
    ) {
        return WithDoublePredicate.of(fuseDoublePredicate(next));
    }

    public default WithDoublePredicate fusing(DoublePredicate next) {
        return fusingDoublePredicate(next);
    }

    /* DoubleUnaryOperator -> DoubleConsumer */

    public default DoubleConsumer fuseDoubleConsumer(DoubleConsumer next) {
        return (double d) -> next.accept(resolve().applyAsDouble(d));
    }

    public default DoubleConsumer fuse(DoubleConsumer next) {
        return fuseDoubleConsumer(next);
    }

    public default WithDoubleConsumer fusingDoubleConsumer(
        DoubleConsumer next
    ) {
        return WithDoubleConsumer.of(fuseDoubleConsumer(next));
    }

    public default WithDoubleConsumer fusing(DoubleConsumer next) {
        return fusingDoubleConsumer(next);
    }

    /* DoubleUnaryOperator -> DoubleUnaryOperator */

    public default DoubleUnaryOperator fuseDoubleUnaryOperator(
        DoubleUnaryOperator next
    ) {
        return (double d) -> next.applyAsDouble(resolve().applyAsDouble(d));
    }

    public default DoubleUnaryOperator fuse(DoubleUnaryOperator next) {
        return fuseDoubleUnaryOperator(next);
    }

    public default WithDoubleUnaryOperator fusingDoubleUnaryOperator(
        DoubleUnaryOperator next
    ) {
        return WithDoubleUnaryOperator.of(fuseDoubleUnaryOperator(next));
    }

    public default WithDoubleUnaryOperator fusing(DoubleUnaryOperator next) {
        return fusingDoubleUnaryOperator(next);
    }

    /* DoubleUnaryOperator -> DoubleBinaryOperator */

    public default DoubleFunction<DoubleUnaryOperator> fuseDoubleBinaryOperator(
        DoubleBinaryOperator next
    ) {
        return (double d1) ->
            (double d2) -> next.applyAsDouble(resolve().applyAsDouble(d1), d2);
    }

    public default DoubleFunction<DoubleUnaryOperator> fuse(
        DoubleBinaryOperator next
    ) {
        return fuseDoubleBinaryOperator(next);
    }

    public default WithDoubleBinaryOperator fusingDoubleBinaryOperator(
        DoubleBinaryOperator next
    ) {
        return WithDoubleBinaryOperator.of(fuseDoubleBinaryOperator(next));
    }

    public default WithDoubleBinaryOperator fusing(DoubleBinaryOperator next) {
        return fusingDoubleBinaryOperator(next);
    }

    public default DoubleUnaryOperator fuseDoubleBinaryOperator(
        DoubleBinaryOperator next,
        double secondArg
    ) {
        return (double d) ->
            next.applyAsDouble(resolve().applyAsDouble(d), secondArg);
    }

    public default DoubleUnaryOperator fuse(
        DoubleBinaryOperator next,
        double secondArg
    ) {
        return fuseDoubleBinaryOperator(next, secondArg);
    }

    public default WithDoubleUnaryOperator fusingDoubleBinaryOperator(
        DoubleBinaryOperator next,
        double secondArg
    ) {
        return WithDoubleUnaryOperator.of(
            fuseDoubleBinaryOperator(next, secondArg)
        );
    }

    public default WithDoubleUnaryOperator fusing(
        DoubleBinaryOperator next,
        double secondArg
    ) {
        return fusingDoubleBinaryOperator(next, secondArg);
    }
}
