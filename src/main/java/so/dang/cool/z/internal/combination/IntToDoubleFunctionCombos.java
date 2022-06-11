package so.dang.cool.z.internal.combination;

import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleFunction;
import java.util.function.DoublePredicate;
import java.util.function.DoubleToIntFunction;
import java.util.function.DoubleToLongFunction;
import java.util.function.DoubleUnaryOperator;
import java.util.function.IntFunction;
import java.util.function.IntToDoubleFunction;
import so.dang.cool.z.internal.combination.Combine.WithIntConsumer;
import so.dang.cool.z.internal.combination.Combine.WithIntFunction;
import so.dang.cool.z.internal.combination.Combine.WithIntPredicate;
import so.dang.cool.z.internal.combination.Combine.WithIntToDoubleFunction;
import so.dang.cool.z.internal.combination.Combine.WithIntToLongFunction;
import so.dang.cool.z.internal.combination.Combine.WithIntUnaryOperator;

interface IntToDoubleFunctionCombos {
    IntToDoubleFunction resolve();

    /* IntToDoubleFunction -> DoubleFunction<A> */

    public default <A> WithIntFunction<A> fuseDoubleFunction(
        DoubleFunction<A> next
    ) {
        return WithIntFunction.of(
            (int i) -> next.apply(resolve().applyAsDouble(i))
        );
    }

    public default <A> WithIntFunction<A> fuse(DoubleFunction<A> next) {
        return fuseDoubleFunction(next);
    }

    /* IntToDoubleFunction -> DoubleToIntFunction */

    public default WithIntUnaryOperator fuseDoubleToIntFunction(
        DoubleToIntFunction next
    ) {
        return WithIntUnaryOperator.of(
            (int i) -> next.applyAsInt(resolve().applyAsDouble(i))
        );
    }

    public default WithIntUnaryOperator fuse(DoubleToIntFunction next) {
        return fuseDoubleToIntFunction(next);
    }

    /* IntToDoubleFunction -> DoubleToLongFunction */

    public default WithIntToLongFunction fuseDoubleToLongFunction(
        DoubleToLongFunction next
    ) {
        return WithIntToLongFunction.of(
            (int i) -> next.applyAsLong(resolve().applyAsDouble(i))
        );
    }

    public default WithIntToLongFunction fuse(DoubleToLongFunction next) {
        return fuseDoubleToLongFunction(next);
    }

    /* IntToDoubleFunction -> DoublePredicate */

    public default WithIntPredicate fuseDoublePredicate(DoublePredicate next) {
        return WithIntPredicate.of(
            (int i) -> next.test(resolve().applyAsDouble(i))
        );
    }

    public default WithIntPredicate fuse(DoublePredicate next) {
        return fuseDoublePredicate(next);
    }

    /* IntToDoubleFunction -> DoubleConsumer */

    public default WithIntConsumer fuseDoubleConsumer(DoubleConsumer next) {
        return WithIntConsumer.of(
            (int i) -> next.accept(resolve().applyAsDouble(i))
        );
    }

    public default WithIntConsumer fuse(DoubleConsumer next) {
        return fuseDoubleConsumer(next);
    }

    /* IntToDoubleFunction -> DoubleUnaryOperator */

    public default WithIntToDoubleFunction fuseDoubleUnaryOperator(
        DoubleUnaryOperator next
    ) {
        return WithIntToDoubleFunction.of(
            (int i) -> next.applyAsDouble(resolve().applyAsDouble(i))
        );
    }

    public default WithIntToDoubleFunction fuse(DoubleUnaryOperator next) {
        return fuseDoubleUnaryOperator(next);
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
}
