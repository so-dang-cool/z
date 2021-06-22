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

    // TODO: Implement with currying
    // fusingDoubleFunction(DoubleFunction<A> next) { ... }

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

    // TODO: Implement with currying
    // fusingDoubleToIntFunction(DoubleToIntFunction next) { ... }

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

    // TODO: Implement with currying
    // fusingDoubleToLongFunction(DoubleToLongFunction next) { ... }

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

    // TODO: Implement with currying
    // fusingDoublePredicate(DoublePredicate next) { ... }

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

    // TODO: Implement with currying
    // fusingDoubleConsumer(DoubleConsumer next) { ... }

    /* DoubleBinaryOperator -> DoubleUnaryOperator */

    public default DoubleFunction<DoubleUnaryOperator> fuseDoubleUnaryOperator(
        DoubleUnaryOperator next
    ) {
        return (double d1) ->
            (double d2) ->
                next.applyAsDouble(resolve().apply(d1).applyAsDouble(d2));
    }

    public default DoubleFunction<DoubleUnaryOperator> fuse(
        DoubleUnaryOperator next
    ) {
        return fuseDoubleUnaryOperator(next);
    }

    public default WithDoubleBinaryOperator fusingDoubleUnaryOperator(
        DoubleUnaryOperator next
    ) {
        return WithDoubleBinaryOperator.of(fuseDoubleUnaryOperator(next));
    }

    public default WithDoubleBinaryOperator fusing(DoubleUnaryOperator next) {
        return fusingDoubleUnaryOperator(next);
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

    // TODO: Implement with currying
    // fusingDoubleBinaryOperator(DoubleBinaryOperator next) { ... }

    public default DoubleFunction<DoubleUnaryOperator> fuseDoubleBinaryOperator(
        DoubleBinaryOperator next,
        double secondArg
    ) {
        return (double d1) ->
            (double d2) ->
                next.applyAsDouble(
                    resolve().apply(d1).applyAsDouble(d2),
                    secondArg
                );
    }

    public default DoubleFunction<DoubleUnaryOperator> fuse(
        DoubleBinaryOperator next,
        double secondArg
    ) {
        return fuseDoubleBinaryOperator(next, secondArg);
    }

    public default WithDoubleBinaryOperator fusingDoubleBinaryOperator(
        DoubleBinaryOperator next,
        double secondArg
    ) {
        return WithDoubleBinaryOperator.of(
            fuseDoubleBinaryOperator(next, secondArg)
        );
    }

    public default WithDoubleBinaryOperator fusing(
        DoubleBinaryOperator next,
        double secondArg
    ) {
        return fusingDoubleBinaryOperator(next, secondArg);
    }
}
