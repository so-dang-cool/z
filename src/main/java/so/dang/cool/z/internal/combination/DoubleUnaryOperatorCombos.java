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

    public default <A> WithDoubleFunction<A> fuseDoubleFunction(
        DoubleFunction<A> next
    ) {
        return WithDoubleFunction.of(
            (double d) -> next.apply(resolve().applyAsDouble(d))
        );
    }

    public default <A> WithDoubleFunction<A> fuse(DoubleFunction<A> next) {
        return fuseDoubleFunction(next);
    }

    /* DoubleUnaryOperator -> DoubleToIntFunction */

    public default WithDoubleToIntFunction fuseDoubleToIntFunction(
        DoubleToIntFunction next
    ) {
        return WithDoubleToIntFunction.of(
            (double d) -> next.applyAsInt(resolve().applyAsDouble(d))
        );
    }

    public default WithDoubleToIntFunction fuse(DoubleToIntFunction next) {
        return fuseDoubleToIntFunction(next);
    }

    /* DoubleUnaryOperator -> DoubleToLongFunction */

    public default WithDoubleToLongFunction fuseDoubleToLongFunction(
        DoubleToLongFunction next
    ) {
        return WithDoubleToLongFunction.of(
            (double d) -> next.applyAsLong(resolve().applyAsDouble(d))
        );
    }

    public default WithDoubleToLongFunction fuse(DoubleToLongFunction next) {
        return fuseDoubleToLongFunction(next);
    }

    /* DoubleUnaryOperator -> DoublePredicate */

    public default WithDoublePredicate fuseDoublePredicate(
        DoublePredicate next
    ) {
        return WithDoublePredicate.of(
            (double d) -> next.test(resolve().applyAsDouble(d))
        );
    }

    public default WithDoublePredicate fuse(DoublePredicate next) {
        return fuseDoublePredicate(next);
    }

    /* DoubleUnaryOperator -> DoubleConsumer */

    public default WithDoubleConsumer fuseDoubleConsumer(DoubleConsumer next) {
        return WithDoubleConsumer.of(
            (double d) -> next.accept(resolve().applyAsDouble(d))
        );
    }

    public default WithDoubleConsumer fuse(DoubleConsumer next) {
        return fuseDoubleConsumer(next);
    }

    /* DoubleUnaryOperator -> DoubleUnaryOperator */

    public default WithDoubleUnaryOperator fuseDoubleUnaryOperator(
        DoubleUnaryOperator next
    ) {
        return WithDoubleUnaryOperator.of(
            (double d) -> next.applyAsDouble(resolve().applyAsDouble(d))
        );
    }

    public default WithDoubleUnaryOperator fuse(DoubleUnaryOperator next) {
        return fuseDoubleUnaryOperator(next);
    }

    /* DoubleUnaryOperator -> DoubleBinaryOperator */

    public default WithDoubleBinaryOperator fuseDoubleBinaryOperator(
        DoubleBinaryOperator next
    ) {
        return WithDoubleBinaryOperator.of(
            (double d1) ->
                (double d2) ->
                    next.applyAsDouble(resolve().applyAsDouble(d1), d2)
        );
    }

    public default WithDoubleBinaryOperator fuse(DoubleBinaryOperator next) {
        return fuseDoubleBinaryOperator(next);
    }
}
