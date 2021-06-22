package so.dang.cool.z.internal.combination;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.IntSupplier;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntToLongFunction;
import java.util.function.IntUnaryOperator;
import java.util.function.LongSupplier;
import java.util.function.Supplier;
import so.dang.cool.z.annotation.Evil;
import so.dang.cool.z.function.Operator;
import so.dang.cool.z.internal.combination.Combine.WithIntConsumer;
import so.dang.cool.z.internal.combination.Combine.WithIntFunction;
import so.dang.cool.z.internal.combination.Combine.WithIntPredicate;
import so.dang.cool.z.internal.combination.Combine.WithIntToDoubleFunction;
import so.dang.cool.z.internal.combination.Combine.WithIntToLongFunction;
import so.dang.cool.z.internal.combination.Combine.WithIntUnaryOperator;

interface IntConsumerCombos {
    IntConsumer resolve();

    /* IntConsumer -> Supplier<A> */

    @Evil
    public default <A> IntFunction<A> absorbSupplier(Supplier<A> next) {
        return (int i) -> {
            resolve().accept(i);
            return next.get();
        };
    }

    @Evil
    public default <A> IntFunction<A> absorb(Supplier<A> next) {
        return absorbSupplier(next);
    }

    @Evil
    public default <A> WithIntFunction<A> absorbingSupplier(Supplier<A> next) {
        return WithIntFunction.of(absorbSupplier(next));
    }

    @Evil
    public default <A> WithIntFunction<A> absorbing(Supplier<A> next) {
        return absorbingSupplier(next);
    }

    /* IntConsumer -> BooleanSupplier<A> */

    @Evil
    public default IntPredicate absorbBooleanSupplier(BooleanSupplier next) {
        return (int i) -> {
            resolve().accept(i);
            return next.getAsBoolean();
        };
    }

    @Evil
    public default IntPredicate absorb(BooleanSupplier next) {
        return absorbBooleanSupplier(next);
    }

    @Evil
    public default WithIntPredicate absorbingBooleanSupplier(
        BooleanSupplier next
    ) {
        return WithIntPredicate.of(absorbBooleanSupplier(next));
    }

    @Evil
    public default WithIntPredicate absorbing(BooleanSupplier next) {
        return absorbingBooleanSupplier(next);
    }

    /* IntConsumer -> DoubleSupplier<B> */

    @Evil
    public default IntToDoubleFunction absorbDoubleSupplier(
        DoubleSupplier next
    ) {
        return (int i) -> {
            resolve().accept(i);
            return next.getAsDouble();
        };
    }

    @Evil
    public default IntToDoubleFunction absorb(DoubleSupplier next) {
        return absorbDoubleSupplier(next);
    }

    @Evil
    public default WithIntToDoubleFunction absorbingDoubleSupplier(
        DoubleSupplier next
    ) {
        return WithIntToDoubleFunction.of(absorbDoubleSupplier(next));
    }

    @Evil
    public default WithIntToDoubleFunction absorbing(DoubleSupplier next) {
        return absorbingDoubleSupplier(next);
    }

    /* IntConsumer -> IntSupplier<B> */

    @Evil
    public default IntUnaryOperator absorbIntSupplier(IntSupplier next) {
        return (int i) -> {
            resolve().accept(i);
            return next.getAsInt();
        };
    }

    @Evil
    public default IntUnaryOperator absorb(IntSupplier next) {
        return absorbIntSupplier(next);
    }

    @Evil
    public default WithIntUnaryOperator absorbingIntSupplier(IntSupplier next) {
        return WithIntUnaryOperator.of(absorbIntSupplier(next));
    }

    @Evil
    public default WithIntUnaryOperator absorbing(IntSupplier next) {
        return absorbingIntSupplier(next);
    }

    /* IntConsumer -> LongSupplier<B> */

    @Evil
    public default IntToLongFunction absorbLongSupplier(LongSupplier next) {
        return (int i) -> {
            resolve().accept(i);
            return next.getAsLong();
        };
    }

    @Evil
    public default IntToLongFunction absorb(LongSupplier next) {
        return absorbLongSupplier(next);
    }

    @Evil
    public default WithIntToLongFunction absorbingLongSupplier(
        LongSupplier next
    ) {
        return WithIntToLongFunction.of(absorbLongSupplier(next));
    }

    @Evil
    public default WithIntToLongFunction absorbing(LongSupplier next) {
        return absorbingLongSupplier(next);
    }

    /* IntConsumer -> Operator<B> */

    @Evil
    public default IntConsumer absorbOperator(Operator next) {
        return (int i) -> {
            resolve().accept(i);
            next.run();
        };
    }

    @Evil
    public default IntConsumer absorb(Operator next) {
        return absorbOperator(next);
    }

    @Evil
    public default WithIntConsumer absorbingOperator(Operator next) {
        return WithIntConsumer.of(absorbOperator(next));
    }

    @Evil
    public default WithIntConsumer absorbing(Operator next) {
        return absorbingOperator(next);
    }
}
