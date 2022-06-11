package so.dang.cool.z.internal.combination;

import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleFunction;
import java.util.function.DoublePredicate;
import java.util.function.DoubleToIntFunction;
import java.util.function.DoubleToLongFunction;
import java.util.function.DoubleUnaryOperator;
import so.dang.cool.z.internal.combination.Combine.WithDoubleBinaryOperator;

interface DoubleBinaryOperatorCombos {
    DoubleFunction<DoubleUnaryOperator> resolve();

    /* DoubleBinaryOperator -> DoubleFunction<A> */

    public default <A> DoubleFunction<DoubleFunction<A>> fuseDoubleFunction(
        DoubleFunction<A> next
    ) {
        return (double d1) ->
            (double d2) -> next.apply(resolve().apply(d1).applyAsDouble(d2));
    }

    public default <A> DoubleFunction<DoubleFunction<A>> fuse(
        DoubleFunction<A> next
    ) {
        return fuseDoubleFunction(next);
    }

    /* DoubleBinaryOperator -> DoubleToIntFunction */

    public default DoubleFunction<DoubleToIntFunction> fuseDoubleToIntFunction(
        DoubleToIntFunction next
    ) {
        return (double d1) ->
            (double d2) ->
                next.applyAsInt(resolve().apply(d1).applyAsDouble(d2));
    }

    public default DoubleFunction<DoubleToIntFunction> fuse(
        DoubleToIntFunction next
    ) {
        return fuseDoubleToIntFunction(next);
    }

    /* DoubleBinaryOperator -> DoubleToLongFunction */

    public default DoubleFunction<DoubleToLongFunction> fuseDoubleToLongFunction(
        DoubleToLongFunction next
    ) {
        return (double d1) ->
            (double d2) ->
                next.applyAsLong(resolve().apply(d1).applyAsDouble(d2));
    }

    public default DoubleFunction<DoubleToLongFunction> fuse(
        DoubleToLongFunction next
    ) {
        return fuseDoubleToLongFunction(next);
    }

    /* DoubleBinaryOperator -> DoublePredicate */

    public default DoubleFunction<DoublePredicate> fuseDoublePredicate(
        DoublePredicate next
    ) {
        return (double d1) ->
            (double d2) -> next.test(resolve().apply(d1).applyAsDouble(d2));
    }

    public default DoubleFunction<DoublePredicate> fuse(DoublePredicate next) {
        return fuseDoublePredicate(next);
    }

    /* DoubleBinaryOperator -> DoubleConsumer */

    public default DoubleFunction<DoubleConsumer> fuseDoubleConsumer(
        DoubleConsumer next
    ) {
        return (double d1) ->
            (double d2) -> next.accept(resolve().apply(d1).applyAsDouble(d2));
    }

    public default DoubleFunction<DoubleConsumer> fuse(DoubleConsumer next) {
        return fuseDoubleConsumer(next);
    }

    /* DoubleBinaryOperator -> DoubleUnaryOperator */

    public default WithDoubleBinaryOperator fuseDoubleUnaryOperator(
        DoubleUnaryOperator next
    ) {
        return WithDoubleBinaryOperator.of(
            (double d1) ->
                (double d2) ->
                    next.applyAsDouble(resolve().apply(d1).applyAsDouble(d2))
        );
    }

    public default WithDoubleBinaryOperator fuse(DoubleUnaryOperator next) {
        return fuseDoubleUnaryOperator(next);
    }

    /* DoubleBinaryOperator -> DoubleBinaryOperator */

    public default DoubleFunction<DoubleFunction<DoubleUnaryOperator>> fuseDoubleBinaryOperator(
        DoubleBinaryOperator next
    ) {
        return (double d1) ->
            (double d2) ->
                (double d3) ->
                    next.applyAsDouble(
                        resolve().apply(d1).applyAsDouble(d2),
                        d3
                    );
    }

    public default DoubleFunction<DoubleFunction<DoubleUnaryOperator>> fuse(
        DoubleBinaryOperator next
    ) {
        return fuseDoubleBinaryOperator(next);
    }
}
