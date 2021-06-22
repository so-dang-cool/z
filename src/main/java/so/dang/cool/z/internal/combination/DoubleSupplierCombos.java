package so.dang.cool.z.internal.combination;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleFunction;
import java.util.function.DoublePredicate;
import java.util.function.DoubleSupplier;
import java.util.function.DoubleToIntFunction;
import java.util.function.DoubleToLongFunction;
import java.util.function.DoubleUnaryOperator;
import java.util.function.IntSupplier;
import java.util.function.LongSupplier;
import java.util.function.Supplier;
import so.dang.cool.z.function.Operator;
import so.dang.cool.z.internal.combination.Combine.WithBooleanSupplier;
import so.dang.cool.z.internal.combination.Combine.WithDoubleSupplier;
import so.dang.cool.z.internal.combination.Combine.WithDoubleUnaryOperator;
import so.dang.cool.z.internal.combination.Combine.WithIntSupplier;
import so.dang.cool.z.internal.combination.Combine.WithLongSupplier;
import so.dang.cool.z.internal.combination.Combine.WithOperator;
import so.dang.cool.z.internal.combination.Combine.WithSupplier;

interface DoubleSupplierCombos {
    public DoubleSupplier resolve();

    /* DoubleSupplier -> DoubleFunction<A> */

    public default <A> Supplier<A> fuseDoubleFunction(DoubleFunction<A> next) {
        return () -> next.apply(resolve().getAsDouble());
    }

    public default <A> Supplier<A> fuse(DoubleFunction<A> next) {
        return fuseDoubleFunction(next);
    }

    public default <A> WithSupplier<A> fusingDoubleFunction(
        DoubleFunction<A> next
    ) {
        return WithSupplier.of(fuse(next));
    }

    public default <A> WithSupplier<A> fusing(DoubleFunction<A> next) {
        return fusingDoubleFunction(next);
    }

    /* DoubleSupplier -> DoubleToIntFunction */

    public default IntSupplier fuseDoubleToIntFunction(
        DoubleToIntFunction next
    ) {
        return () -> next.applyAsInt(resolve().getAsDouble());
    }

    public default IntSupplier fuse(DoubleToIntFunction next) {
        return fuseDoubleToIntFunction(next);
    }

    public default WithIntSupplier fusingDoubleToIntFunction(
        DoubleToIntFunction next
    ) {
        return WithIntSupplier.of(fuse(next));
    }

    public default WithIntSupplier fusing(DoubleToIntFunction next) {
        return fusingDoubleToIntFunction(next);
    }

    /* DoubleSupplier -> DoubleToLongFunction */

    public default LongSupplier fuseDoubleToLongFunction(
        DoubleToLongFunction next
    ) {
        return () -> next.applyAsLong(resolve().getAsDouble());
    }

    public default LongSupplier fuse(DoubleToLongFunction next) {
        return fuseDoubleToLongFunction(next);
    }

    public default WithLongSupplier fusingDoubleToLongFunction(
        DoubleToLongFunction next
    ) {
        return WithLongSupplier.of(fuse(next));
    }

    public default WithLongSupplier fusing(DoubleToLongFunction next) {
        return fusingDoubleToLongFunction(next);
    }

    /* DoubleSupplier -> DoublePredicate */

    public default BooleanSupplier fuseDoublePredicate(DoublePredicate next) {
        return () -> next.test(resolve().getAsDouble());
    }

    public default BooleanSupplier fuse(DoublePredicate next) {
        return fuseDoublePredicate(next);
    }

    public default WithBooleanSupplier fusingDoublePredicate(
        DoublePredicate next
    ) {
        return WithBooleanSupplier.of(fuse(next));
    }

    public default WithBooleanSupplier fusing(DoublePredicate next) {
        return fusingDoublePredicate(next);
    }

    /* DoubleSupplier -> DoubleConsumer */

    public default Operator fuseDoubleConsumer(DoubleConsumer next) {
        return () -> next.accept(resolve().getAsDouble());
    }

    public default Operator fuse(DoubleConsumer next) {
        return fuseDoubleConsumer(next);
    }

    public default WithOperator fusingDoubleConsumer(DoubleConsumer next) {
        return WithOperator.of(fuse(next));
    }

    public default WithOperator fusing(DoubleConsumer next) {
        return fusingDoubleConsumer(next);
    }

    /* DoubleSupplier -> DoubleUnaryOperator */

    public default DoubleSupplier fuseDoubleUnaryOperator(
        DoubleUnaryOperator next
    ) {
        return () -> next.applyAsDouble(resolve().getAsDouble());
    }

    public default DoubleSupplier fuse(DoubleUnaryOperator next) {
        return fuseDoubleUnaryOperator(next);
    }

    public default WithDoubleSupplier fusingDoubleUnaryOperator(
        DoubleUnaryOperator next
    ) {
        return WithDoubleSupplier.of(fuse(next));
    }

    public default WithDoubleSupplier fusing(DoubleUnaryOperator next) {
        return fusingDoubleUnaryOperator(next);
    }

    /* DoubleSupplier -> DoubleBinaryOperator */

    public default DoubleUnaryOperator fuseDoubleBinaryOperator(
        DoubleBinaryOperator next
    ) {
        return (double d) -> next.applyAsDouble(resolve().getAsDouble(), d);
    }

    public default DoubleUnaryOperator fuse(DoubleBinaryOperator next) {
        return fuseDoubleBinaryOperator(next);
    }

    public default WithDoubleUnaryOperator fusingDoubleBinaryOperator(
        DoubleBinaryOperator next
    ) {
        return WithDoubleUnaryOperator.of(fuse(next));
    }

    public default WithDoubleUnaryOperator fusing(DoubleBinaryOperator next) {
        return fusingDoubleBinaryOperator(next);
    }

    public default DoubleSupplier fuseDoubleBinaryOperator(
        DoubleBinaryOperator next,
        double secondArg
    ) {
        return () -> next.applyAsDouble(resolve().getAsDouble(), secondArg);
    }

    public default DoubleSupplier fuse(
        DoubleBinaryOperator next,
        double secondArg
    ) {
        return fuseDoubleBinaryOperator(next, secondArg);
    }

    public default WithDoubleSupplier fusingDoubleBinaryOperator(
        DoubleBinaryOperator next,
        double secondArg
    ) {
        return WithDoubleSupplier.of(fuse(next, secondArg));
    }

    public default WithDoubleSupplier fusing(
        DoubleBinaryOperator next,
        double secondArg
    ) {
        return fusingDoubleBinaryOperator(next, secondArg);
    }
}
