package so.dang.cool.z.internal.combination;

import java.util.function.BooleanSupplier;
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
import so.dang.cool.z.annotation.Evil;
import so.dang.cool.z.function.Operator;
import so.dang.cool.z.internal.combination.Combine.WithDoubleConsumer;
import so.dang.cool.z.internal.combination.Combine.WithDoubleFunction;
import so.dang.cool.z.internal.combination.Combine.WithDoublePredicate;
import so.dang.cool.z.internal.combination.Combine.WithDoubleToIntFunction;
import so.dang.cool.z.internal.combination.Combine.WithDoubleToLongFunction;
import so.dang.cool.z.internal.combination.Combine.WithDoubleUnaryOperator;

interface DoubleConsumerCombos {
    DoubleConsumer resolve();

    /* DoubleConsumer -> Supplier<A> */

    @Evil
    public default <A> DoubleFunction<A> absorbSupplier(Supplier<A> next) {
        return (double d) -> {
            resolve().accept(d);
            return next.get();
        };
    }

    @Evil
    public default <A> DoubleFunction<A> absorb(Supplier<A> next) {
        return absorbSupplier(next);
    }

    @Evil
    public default <A> WithDoubleFunction<A> absorbingSupplier(
        Supplier<A> next
    ) {
        return WithDoubleFunction.of(absorbSupplier(next));
    }

    @Evil
    public default <A> WithDoubleFunction<A> absorbing(Supplier<A> next) {
        return absorbingSupplier(next);
    }

    /* DoubleConsumer -> BooleanSupplier<A> */

    @Evil
    public default DoublePredicate absorbBooleanSupplier(BooleanSupplier next) {
        return (double d) -> {
            resolve().accept(d);
            return next.getAsBoolean();
        };
    }

    @Evil
    public default DoublePredicate absorb(BooleanSupplier next) {
        return absorbBooleanSupplier(next);
    }

    @Evil
    public default WithDoublePredicate absorbingBooleanSupplier(
        BooleanSupplier next
    ) {
        return WithDoublePredicate.of(absorbBooleanSupplier(next));
    }

    @Evil
    public default WithDoublePredicate absorbing(BooleanSupplier next) {
        return absorbingBooleanSupplier(next);
    }

    /* DoubleConsumer -> DoubleSupplier<B> */

    @Evil
    public default DoubleUnaryOperator absorbDoubleSupplier(
        DoubleSupplier next
    ) {
        return (double d) -> {
            resolve().accept(d);
            return next.getAsDouble();
        };
    }

    @Evil
    public default DoubleUnaryOperator absorb(DoubleSupplier next) {
        return absorbDoubleSupplier(next);
    }

    @Evil
    public default WithDoubleUnaryOperator absorbingDoubleSupplier(
        DoubleSupplier next
    ) {
        return WithDoubleUnaryOperator.of(absorbDoubleSupplier(next));
    }

    @Evil
    public default WithDoubleUnaryOperator absorbing(DoubleSupplier next) {
        return absorbingDoubleSupplier(next);
    }

    /* DoubleConsumer -> IntSupplier<B> */

    @Evil
    public default DoubleToIntFunction absorbIntSupplier(IntSupplier next) {
        return (double d) -> {
            resolve().accept(d);
            return next.getAsInt();
        };
    }

    @Evil
    public default DoubleToIntFunction absorb(IntSupplier next) {
        return absorbIntSupplier(next);
    }

    @Evil
    public default WithDoubleToIntFunction absorbingIntSupplier(
        IntSupplier next
    ) {
        return WithDoubleToIntFunction.of(absorbIntSupplier(next));
    }

    @Evil
    public default WithDoubleToIntFunction absorbing(IntSupplier next) {
        return absorbingIntSupplier(next);
    }

    /* DoubleConsumer -> LongSupplier<B> */

    @Evil
    public default DoubleToLongFunction absorbLongSupplier(LongSupplier next) {
        return (double d) -> {
            resolve().accept(d);
            return next.getAsLong();
        };
    }

    @Evil
    public default DoubleToLongFunction absorb(LongSupplier next) {
        return absorbLongSupplier(next);
    }

    @Evil
    public default WithDoubleToLongFunction absorbingLongSupplier(
        LongSupplier next
    ) {
        return WithDoubleToLongFunction.of(absorbLongSupplier(next));
    }

    @Evil
    public default WithDoubleToLongFunction absorbing(LongSupplier next) {
        return absorbingLongSupplier(next);
    }

    /* DoubleConsumer -> Operator<B> */

    @Evil
    public default DoubleConsumer absorbOperator(Operator next) {
        return (double d) -> {
            resolve().accept(d);
            next.run();
        };
    }

    @Evil
    public default DoubleConsumer absorb(Operator next) {
        return absorbOperator(next);
    }

    @Evil
    public default WithDoubleConsumer absorbingOperator(Operator next) {
        return WithDoubleConsumer.of(absorbOperator(next));
    }

    @Evil
    public default WithDoubleConsumer absorbing(Operator next) {
        return absorbingOperator(next);
    }
}
