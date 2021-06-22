package so.dang.cool.z.internal.combination;

import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleFunction;
import java.util.function.DoublePredicate;
import java.util.function.DoubleToIntFunction;
import java.util.function.DoubleToLongFunction;
import java.util.function.DoubleUnaryOperator;
import java.util.function.LongConsumer;
import java.util.function.LongFunction;
import java.util.function.LongPredicate;
import java.util.function.LongToDoubleFunction;
import java.util.function.LongToIntFunction;
import java.util.function.LongUnaryOperator;
import so.dang.cool.z.internal.combination.Combine.WithLongConsumer;
import so.dang.cool.z.internal.combination.Combine.WithLongFunction;
import so.dang.cool.z.internal.combination.Combine.WithLongPredicate;
import so.dang.cool.z.internal.combination.Combine.WithLongToDoubleFunction;
import so.dang.cool.z.internal.combination.Combine.WithLongToIntFunction;
import so.dang.cool.z.internal.combination.Combine.WithLongUnaryOperator;

interface LongToDoubleFunctionCombos {
    LongToDoubleFunction resolve();

    /* LongToDoubleFunction -> DoubleFunction<A> */

    public default <A> LongFunction<A> fuseFunction(DoubleFunction<A> next) {
        return (long n) -> next.apply(resolve().applyAsDouble(n));
    }

    public default <A> LongFunction<A> fuse(DoubleFunction<A> next) {
        return fuseFunction(next);
    }

    public default <A> WithLongFunction<A> fusingFunction(
        DoubleFunction<A> next
    ) {
        return WithLongFunction.of(fuseFunction(next));
    }

    public default <A> WithLongFunction<A> fusing(DoubleFunction<A> next) {
        return fusingFunction(next);
    }

    /* LongToDoubleFunction -> DoubleToLongFunction */

    public default LongUnaryOperator fuseDoubleToLongFunction(
        DoubleToLongFunction next
    ) {
        return (long n) -> next.applyAsLong(resolve().applyAsDouble(n));
    }

    public default LongUnaryOperator fuse(DoubleToLongFunction next) {
        return fuseDoubleToLongFunction(next);
    }

    public default WithLongUnaryOperator fusingDoubleToLongFunction(
        DoubleToLongFunction next
    ) {
        return WithLongUnaryOperator.of(fuseDoubleToLongFunction(next));
    }

    public default WithLongUnaryOperator fusing(DoubleToLongFunction next) {
        return fusingDoubleToLongFunction(next);
    }

    /* LongToDoubleFunction -> DoubleToIntFunction */

    public default LongToIntFunction fuseDoubleToIntFunction(
        DoubleToIntFunction next
    ) {
        return (long n) -> next.applyAsInt(resolve().applyAsDouble(n));
    }

    public default LongToIntFunction fuse(DoubleToIntFunction next) {
        return fuseDoubleToIntFunction(next);
    }

    public default WithLongToIntFunction fusingDoubleToIntFunction(
        DoubleToIntFunction next
    ) {
        return WithLongToIntFunction.of(fuseDoubleToIntFunction(next));
    }

    public default WithLongToIntFunction fusing(DoubleToIntFunction next) {
        return fusingDoubleToIntFunction(next);
    }

    /* LongToDoubleFunction -> DoublePredicate */

    public default LongPredicate fuseDoublePredicate(DoublePredicate next) {
        return (long n) -> next.test(resolve().applyAsDouble(n));
    }

    public default LongPredicate fuse(DoublePredicate next) {
        return fuseDoublePredicate(next);
    }

    public default WithLongPredicate fusingDoublePredicate(
        DoublePredicate next
    ) {
        return WithLongPredicate.of(fuseDoublePredicate(next));
    }

    public default WithLongPredicate fusing(DoublePredicate next) {
        return fusingDoublePredicate(next);
    }

    /* LongToDoubleFunction -> DoubleConsumer */

    public default LongConsumer fuseDoubleConsumer(DoubleConsumer next) {
        return (long n) -> next.accept(resolve().applyAsDouble(n));
    }

    public default LongConsumer fuse(DoubleConsumer next) {
        return fuseDoubleConsumer(next);
    }

    public default WithLongConsumer fusingDoubleConsumer(DoubleConsumer next) {
        return WithLongConsumer.of(fuseDoubleConsumer(next));
    }

    public default WithLongConsumer fusing(DoubleConsumer next) {
        return fusingDoubleConsumer(next);
    }

    /* LongToDoubleFunction -> DoubleUnaryOperator */

    public default LongToDoubleFunction fuseDoubleUnaryOperator(
        DoubleUnaryOperator next
    ) {
        return (long n) -> next.applyAsDouble(resolve().applyAsDouble(n));
    }

    public default LongToDoubleFunction fuse(DoubleUnaryOperator next) {
        return fuseDoubleUnaryOperator(next);
    }

    public default WithLongToDoubleFunction fusingDoubleUnaryOperator(
        DoubleUnaryOperator next
    ) {
        return WithLongToDoubleFunction.of(fuseDoubleUnaryOperator(next));
    }

    public default WithLongToDoubleFunction fusing(DoubleUnaryOperator next) {
        return fusingDoubleUnaryOperator(next);
    }

    /* LongToDoubleFunction -> DoubleBinaryOperator */

    public default LongFunction<DoubleUnaryOperator> fuseDoubleBinaryOperator(
        DoubleBinaryOperator next
    ) {
        return (long n) ->
            (double d) -> next.applyAsDouble(resolve().applyAsDouble(n), d);
    }

    public default LongFunction<DoubleUnaryOperator> fuse(
        DoubleBinaryOperator next
    ) {
        return fuseDoubleBinaryOperator(next);
    }

    // TODO: Implement with currying
    // fusingDoubleBinaryOperator(DoubleBinaryOperator next) { ... }

    public default LongToDoubleFunction fuseDoubleBinaryOperator(
        DoubleBinaryOperator next,
        double secondArg
    ) {
        return (long n) ->
            next.applyAsDouble(resolve().applyAsDouble(n), secondArg);
    }

    public default LongToDoubleFunction fuse(
        DoubleBinaryOperator next,
        double secondArg
    ) {
        return fuseDoubleBinaryOperator(next, secondArg);
    }

    public default WithLongToDoubleFunction fusingDoubleBinaryOperator(
        DoubleBinaryOperator next,
        double secondArg
    ) {
        return WithLongToDoubleFunction.of(
            fuseDoubleBinaryOperator(next, secondArg)
        );
    }

    public default WithLongToDoubleFunction fusing(
        DoubleBinaryOperator next,
        double secondArg
    ) {
        return fusingDoubleBinaryOperator(next, secondArg);
    }
}
