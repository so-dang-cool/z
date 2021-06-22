package so.dang.cool.z.internal.combination;

import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleFunction;
import java.util.function.DoublePredicate;
import java.util.function.DoubleToIntFunction;
import java.util.function.DoubleToLongFunction;
import java.util.function.DoubleUnaryOperator;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntToLongFunction;
import java.util.function.IntUnaryOperator;
import so.dang.cool.z.internal.combination.Combine.WithIntConsumer;
import so.dang.cool.z.internal.combination.Combine.WithIntFunction;
import so.dang.cool.z.internal.combination.Combine.WithIntPredicate;
import so.dang.cool.z.internal.combination.Combine.WithIntToDoubleFunction;
import so.dang.cool.z.internal.combination.Combine.WithIntToLongFunction;
import so.dang.cool.z.internal.combination.Combine.WithIntUnaryOperator;

interface IntToDoubleFunctionCombos {
    IntToDoubleFunction resolve();

    /* IntToDoubleFunction -> DoubleFunction<A> */

    public default <A> IntFunction<A> fuseFunction(DoubleFunction<A> next) {
        return (int i) -> next.apply(resolve().applyAsDouble(i));
    }

    public default <A> IntFunction<A> fuse(DoubleFunction<A> next) {
        return fuseFunction(next);
    }

    public default <A> WithIntFunction<A> fusingFunction(
        DoubleFunction<A> next
    ) {
        return WithIntFunction.of(fuseFunction(next));
    }

    public default <A> WithIntFunction<A> fusing(DoubleFunction<A> next) {
        return fusingFunction(next);
    }

    /* IntToDoubleFunction -> DoubleToIntFunction */

    public default IntUnaryOperator fuseDoubleToIntFunction(
        DoubleToIntFunction next
    ) {
        return (int i) -> next.applyAsInt(resolve().applyAsDouble(i));
    }

    public default IntUnaryOperator fuse(DoubleToIntFunction next) {
        return fuseDoubleToIntFunction(next);
    }

    public default WithIntUnaryOperator fusingDoubleToIntFunction(
        DoubleToIntFunction next
    ) {
        return WithIntUnaryOperator.of(fuseDoubleToIntFunction(next));
    }

    public default WithIntUnaryOperator fusing(DoubleToIntFunction next) {
        return fusingDoubleToIntFunction(next);
    }

    /* IntToDoubleFunction -> DoubleToLongFunction */

    public default IntToLongFunction fuseDoubleToLongFunction(
        DoubleToLongFunction next
    ) {
        return (int i) -> next.applyAsLong(resolve().applyAsDouble(i));
    }

    public default IntToLongFunction fuse(DoubleToLongFunction next) {
        return fuseDoubleToLongFunction(next);
    }

    public default WithIntToLongFunction fusingDoubleToLongFunction(
        DoubleToLongFunction next
    ) {
        return WithIntToLongFunction.of(fuseDoubleToLongFunction(next));
    }

    public default WithIntToLongFunction fusing(DoubleToLongFunction next) {
        return fusingDoubleToLongFunction(next);
    }

    /* IntToDoubleFunction -> DoublePredicate */

    public default IntPredicate fuseDoublePredicate(DoublePredicate next) {
        return (int i) -> next.test(resolve().applyAsDouble(i));
    }

    public default IntPredicate fuse(DoublePredicate next) {
        return fuseDoublePredicate(next);
    }

    public default WithIntPredicate fusingDoublePredicate(
        DoublePredicate next
    ) {
        return WithIntPredicate.of(fuseDoublePredicate(next));
    }

    public default WithIntPredicate fusing(DoublePredicate next) {
        return fusingDoublePredicate(next);
    }

    /* IntToDoubleFunction -> DoubleConsumer */

    public default IntConsumer fuseDoubleConsumer(DoubleConsumer next) {
        return (int i) -> next.accept(resolve().applyAsDouble(i));
    }

    public default IntConsumer fuse(DoubleConsumer next) {
        return fuseDoubleConsumer(next);
    }

    public default WithIntConsumer fusingDoubleConsumer(DoubleConsumer next) {
        return WithIntConsumer.of(fuseDoubleConsumer(next));
    }

    public default WithIntConsumer fusing(DoubleConsumer next) {
        return fusingDoubleConsumer(next);
    }

    /* IntToDoubleFunction -> DoubleUnaryOperator */

    public default IntToDoubleFunction fuseDoubleUnaryOperator(
        DoubleUnaryOperator next
    ) {
        return (int i) -> next.applyAsDouble(resolve().applyAsDouble(i));
    }

    public default IntToDoubleFunction fuse(DoubleUnaryOperator next) {
        return fuseDoubleUnaryOperator(next);
    }

    public default WithIntToDoubleFunction fusingDoubleUnaryOperator(
        DoubleUnaryOperator next
    ) {
        return WithIntToDoubleFunction.of(fuseDoubleUnaryOperator(next));
    }

    public default WithIntToDoubleFunction fusing(DoubleUnaryOperator next) {
        return fusingDoubleUnaryOperator(next);
    }

    /* IntToDoubleFunction -> DoubleBinaryOperator */

    public default IntFunction<DoubleUnaryOperator> fuseDoubleBinaryOperator(
        DoubleBinaryOperator next
    ) {
        return (int i) ->
            (double d) -> next.applyAsDouble(resolve().applyAsDouble(i), d);
    }

    public default IntFunction<DoubleUnaryOperator> fuse(
        DoubleBinaryOperator next
    ) {
        return fuseDoubleBinaryOperator(next);
    }

    // TODO: Implement with currying
    // fusingDoubleBinaryOperator(DoubleBinaryOperator next) { ... }

    public default IntToDoubleFunction fuseDoubleBinaryOperator(
        DoubleBinaryOperator next,
        double secondArg
    ) {
        return (int i) ->
            next.applyAsDouble(resolve().applyAsDouble(i), secondArg);
    }

    public default IntToDoubleFunction fuse(
        DoubleBinaryOperator next,
        double secondArg
    ) {
        return fuseDoubleBinaryOperator(next, secondArg);
    }

    public default WithIntToDoubleFunction fusingDoubleBinaryOperator(
        DoubleBinaryOperator next,
        double secondArg
    ) {
        return WithIntToDoubleFunction.of(
            fuseDoubleBinaryOperator(next, secondArg)
        );
    }

    public default WithIntToDoubleFunction fusing(
        DoubleBinaryOperator next,
        double secondArg
    ) {
        return fusingDoubleBinaryOperator(next, secondArg);
    }
}
