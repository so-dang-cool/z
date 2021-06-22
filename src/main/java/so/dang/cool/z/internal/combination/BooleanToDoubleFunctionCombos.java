package so.dang.cool.z.internal.combination;

import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleFunction;
import java.util.function.DoublePredicate;
import java.util.function.DoubleToIntFunction;
import java.util.function.DoubleToLongFunction;
import java.util.function.DoubleUnaryOperator;
import so.dang.cool.z.function.BooleanConsumer;
import so.dang.cool.z.function.BooleanFunction;
import so.dang.cool.z.function.BooleanPredicate;
import so.dang.cool.z.function.BooleanToDoubleFunction;
import so.dang.cool.z.function.BooleanToIntFunction;
import so.dang.cool.z.function.BooleanToLongFunction;
import so.dang.cool.z.internal.combination.Combine.WithBooleanConsumer;
import so.dang.cool.z.internal.combination.Combine.WithBooleanFunction;
import so.dang.cool.z.internal.combination.Combine.WithBooleanPredicate;
import so.dang.cool.z.internal.combination.Combine.WithBooleanToDoubleFunction;
import so.dang.cool.z.internal.combination.Combine.WithBooleanToIntFunction;
import so.dang.cool.z.internal.combination.Combine.WithBooleanToLongFunction;

interface BooleanToDoubleFunctionCombos {
    BooleanToDoubleFunction resolve();

    /* BooleanToDoubleFunction -> DoubleFunction<A> */

    public default <A> BooleanFunction<A> fuseDoubleFunction(
        DoubleFunction<A> next
    ) {
        return (boolean b) -> next.apply(resolve().applyAsDouble(b));
    }

    public default <A> BooleanFunction<A> fuse(DoubleFunction<A> next) {
        return fuseDoubleFunction(next);
    }

    public default <A> WithBooleanFunction<A> fusingDoubleFunction(
        DoubleFunction<A> next
    ) {
        return WithBooleanFunction.of(fuseDoubleFunction(next));
    }

    public default <A> WithBooleanFunction<A> fusing(DoubleFunction<A> next) {
        return fusingDoubleFunction(next);
    }

    /* BooleanToDoubleFunction -> DoubleToIntFunction */

    public default BooleanToIntFunction fuseDoubleToIntFunction(
        DoubleToIntFunction next
    ) {
        return (boolean b) -> next.applyAsInt(resolve().applyAsDouble(b));
    }

    public default BooleanToIntFunction fuse(DoubleToIntFunction next) {
        return fuseDoubleToIntFunction(next);
    }

    public default WithBooleanToIntFunction fusingDoubleToIntFunction(
        DoubleToIntFunction next
    ) {
        return WithBooleanToIntFunction.of(fuseDoubleToIntFunction(next));
    }

    public default WithBooleanToIntFunction fusing(DoubleToIntFunction next) {
        return fusingDoubleToIntFunction(next);
    }

    /* BooleanToDoubleFunction -> DoubleToLongFunction */

    public default BooleanToLongFunction fuseDoubleToLongFunction(
        DoubleToLongFunction next
    ) {
        return (boolean b) -> next.applyAsLong(resolve().applyAsDouble(b));
    }

    public default BooleanToLongFunction fuse(DoubleToLongFunction next) {
        return fuseDoubleToLongFunction(next);
    }

    public default WithBooleanToLongFunction fusingDoubleToLongFunction(
        DoubleToLongFunction next
    ) {
        return WithBooleanToLongFunction.of(fuseDoubleToLongFunction(next));
    }

    public default WithBooleanToLongFunction fusing(DoubleToLongFunction next) {
        return fusingDoubleToLongFunction(next);
    }

    /* BooleanToDoubleFunction -> DoublePredicate */

    public default BooleanPredicate fuseDoublePredicate(DoublePredicate next) {
        return (boolean b) -> next.test(resolve().applyAsDouble(b));
    }

    public default BooleanPredicate fuse(DoublePredicate next) {
        return fuseDoublePredicate(next);
    }

    public default WithBooleanPredicate fusingDoublePredicate(
        DoublePredicate next
    ) {
        return WithBooleanPredicate.of(fuseDoublePredicate(next));
    }

    public default WithBooleanPredicate fusing(DoublePredicate next) {
        return fusingDoublePredicate(next);
    }

    /* BooleanToDoubleFunction -> DoubleConsumer */

    public default BooleanConsumer fuseDoubleConsumer(DoubleConsumer next) {
        return (boolean b) -> next.accept(resolve().applyAsDouble(b));
    }

    public default BooleanConsumer fuse(DoubleConsumer next) {
        return fuseDoubleConsumer(next);
    }

    public default WithBooleanConsumer fusingDoubleConsumer(
        DoubleConsumer next
    ) {
        return WithBooleanConsumer.of(fuseDoubleConsumer(next));
    }

    public default WithBooleanConsumer fusing(DoubleConsumer next) {
        return fusingDoubleConsumer(next);
    }

    /* BooleanToDoubleFunction -> DoubleUnaryOperator */

    public default BooleanToDoubleFunction fuseDoubleUnaryOperator(
        DoubleUnaryOperator next
    ) {
        return (boolean b) -> next.applyAsDouble(resolve().applyAsDouble(b));
    }

    public default BooleanToDoubleFunction fuse(DoubleUnaryOperator next) {
        return fuseDoubleUnaryOperator(next);
    }

    public default WithBooleanToDoubleFunction fusingDoubleUnaryOperator(
        DoubleUnaryOperator next
    ) {
        return WithBooleanToDoubleFunction.of(fuseDoubleUnaryOperator(next));
    }

    public default WithBooleanToDoubleFunction fusing(
        DoubleUnaryOperator next
    ) {
        return fusingDoubleUnaryOperator(next);
    }

    /* BooleanToDoubleFunction -> DoubleBinaryOperator */

    public default BooleanFunction<DoubleUnaryOperator> fuseDoubleBinaryOperator(
        DoubleBinaryOperator next
    ) {
        return (boolean b) ->
            (double d) -> next.applyAsDouble(resolve().applyAsDouble(b), d);
    }

    public default BooleanFunction<DoubleUnaryOperator> fuse(
        DoubleBinaryOperator next
    ) {
        return fuseDoubleBinaryOperator(next);
    }

    // TODO: Implement with currying
    // fusingDoubleBinaryOperator(DoubleBinaryOperator next) { ... }

    public default BooleanToDoubleFunction fuseDoubleBinaryOperator(
        DoubleBinaryOperator next,
        double secondArg
    ) {
        return (boolean b) ->
            next.applyAsDouble(resolve().applyAsDouble(b), secondArg);
    }

    public default BooleanToDoubleFunction fuse(
        DoubleBinaryOperator next,
        double secondArg
    ) {
        return fuseDoubleBinaryOperator(next, secondArg);
    }

    public default WithBooleanToDoubleFunction fusingDoubleBinaryOperator(
        DoubleBinaryOperator next,
        double secondArg
    ) {
        return WithBooleanToDoubleFunction.of(
            fuseDoubleBinaryOperator(next, secondArg)
        );
    }

    public default WithBooleanToDoubleFunction fusing(
        DoubleBinaryOperator next,
        double secondArg
    ) {
        return fusingDoubleBinaryOperator(next, secondArg);
    }
}
