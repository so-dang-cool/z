package so.dang.cool.z.internal.combination;

import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleFunction;
import java.util.function.DoublePredicate;
import java.util.function.DoubleToIntFunction;
import java.util.function.DoubleToLongFunction;
import java.util.function.DoubleUnaryOperator;
import so.dang.cool.z.function.BooleanFunction;
import so.dang.cool.z.function.BooleanToDoubleFunction;
import so.dang.cool.z.internal.combination.Combine.WithBooleanConsumer;
import so.dang.cool.z.internal.combination.Combine.WithBooleanFunction;
import so.dang.cool.z.internal.combination.Combine.WithBooleanPredicate;
import so.dang.cool.z.internal.combination.Combine.WithBooleanToDoubleFunction;
import so.dang.cool.z.internal.combination.Combine.WithBooleanToIntFunction;
import so.dang.cool.z.internal.combination.Combine.WithBooleanToLongFunction;

interface BooleanToDoubleFunctionCombos {
    BooleanToDoubleFunction resolve();

    /* BooleanToDoubleFunction -> DoubleFunction<A> */

    public default <A> WithBooleanFunction<A> fuseDoubleFunction(
        DoubleFunction<A> next
    ) {
        return WithBooleanFunction.of(
            (boolean b) -> next.apply(resolve().applyAsDouble(b))
        );
    }

    public default <A> WithBooleanFunction<A> fuse(DoubleFunction<A> next) {
        return fuseDoubleFunction(next);
    }

    /* BooleanToDoubleFunction -> DoubleToIntFunction */

    public default WithBooleanToIntFunction fuseDoubleToIntFunction(
        DoubleToIntFunction next
    ) {
        return WithBooleanToIntFunction.of(
            (boolean b) -> next.applyAsInt(resolve().applyAsDouble(b))
        );
    }

    public default WithBooleanToIntFunction fuse(DoubleToIntFunction next) {
        return fuseDoubleToIntFunction(next);
    }

    /* BooleanToDoubleFunction -> DoubleToLongFunction */

    public default WithBooleanToLongFunction fuseDoubleToLongFunction(
        DoubleToLongFunction next
    ) {
        return WithBooleanToLongFunction.of(
            (boolean b) -> next.applyAsLong(resolve().applyAsDouble(b))
        );
    }

    public default WithBooleanToLongFunction fuse(DoubleToLongFunction next) {
        return fuseDoubleToLongFunction(next);
    }

    /* BooleanToDoubleFunction -> DoublePredicate */

    public default WithBooleanPredicate fuseDoublePredicate(
        DoublePredicate next
    ) {
        return WithBooleanPredicate.of(
            (boolean b) -> next.test(resolve().applyAsDouble(b))
        );
    }

    public default WithBooleanPredicate fuse(DoublePredicate next) {
        return fuseDoublePredicate(next);
    }

    /* BooleanToDoubleFunction -> DoubleConsumer */

    public default WithBooleanConsumer fuseDoubleConsumer(DoubleConsumer next) {
        return WithBooleanConsumer.of(
            (boolean b) -> next.accept(resolve().applyAsDouble(b))
        );
    }

    public default WithBooleanConsumer fuse(DoubleConsumer next) {
        return fuseDoubleConsumer(next);
    }

    /* BooleanToDoubleFunction -> DoubleUnaryOperator */

    public default WithBooleanToDoubleFunction fuseDoubleUnaryOperator(
        DoubleUnaryOperator next
    ) {
        return WithBooleanToDoubleFunction.of(
            (boolean b) -> next.applyAsDouble(resolve().applyAsDouble(b))
        );
    }

    public default WithBooleanToDoubleFunction fuse(DoubleUnaryOperator next) {
        return fuseDoubleUnaryOperator(next);
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

    public default WithBooleanToDoubleFunction fuseDoubleBinaryOperator(
        DoubleBinaryOperator next,
        double secondArg
    ) {
        return WithBooleanToDoubleFunction.of(
            (boolean b) ->
                next.applyAsDouble(resolve().applyAsDouble(b), secondArg)
        );
    }

    public default WithBooleanToDoubleFunction fuse(
        DoubleBinaryOperator next,
        double secondArg
    ) {
        return fuseDoubleBinaryOperator(next, secondArg);
    }
}
