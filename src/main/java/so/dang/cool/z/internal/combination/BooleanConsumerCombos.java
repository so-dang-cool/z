package so.dang.cool.z.internal.combination;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;
import java.util.function.IntSupplier;
import java.util.function.LongSupplier;
import java.util.function.Supplier;
import so.dang.cool.z.annotation.Evil;
import so.dang.cool.z.function.BooleanConsumer;
import so.dang.cool.z.function.BooleanFunction;
import so.dang.cool.z.function.BooleanPredicate;
import so.dang.cool.z.function.BooleanToDoubleFunction;
import so.dang.cool.z.function.BooleanToIntFunction;
import so.dang.cool.z.function.BooleanToLongFunction;
import so.dang.cool.z.function.Operator;
import so.dang.cool.z.internal.combination.Combine.WithBooleanConsumer;
import so.dang.cool.z.internal.combination.Combine.WithBooleanFunction;
import so.dang.cool.z.internal.combination.Combine.WithBooleanPredicate;
import so.dang.cool.z.internal.combination.Combine.WithBooleanToDoubleFunction;
import so.dang.cool.z.internal.combination.Combine.WithBooleanToIntFunction;
import so.dang.cool.z.internal.combination.Combine.WithBooleanToLongFunction;

interface BooleanConsumerCombos {
    BooleanConsumer resolve();

    /* BooleanConsumer -> Supplier<A> */

    @Evil
    public default <A> BooleanFunction<A> absorbSupplier(Supplier<A> next) {
        return (boolean b) -> {
            resolve().accept(b);
            return next.get();
        };
    }

    @Evil
    public default <A> BooleanFunction<A> absorb(Supplier<A> next) {
        return absorbSupplier(next);
    }

    @Evil
    public default <A> WithBooleanFunction<A> absorbingSupplier(
        Supplier<A> next
    ) {
        return WithBooleanFunction.of(absorbSupplier(next));
    }

    @Evil
    public default <A> WithBooleanFunction<A> absorbing(Supplier<A> next) {
        return absorbingSupplier(next);
    }

    /* BooleanConsumer -> BooleanSupplier<A> */

    @Evil
    public default BooleanPredicate absorbBooleanSupplier(
        BooleanSupplier next
    ) {
        return (boolean b) -> {
            resolve().accept(b);
            return next.getAsBoolean();
        };
    }

    @Evil
    public default BooleanPredicate absorb(BooleanSupplier next) {
        return absorbBooleanSupplier(next);
    }

    @Evil
    public default WithBooleanPredicate absorbingBooleanSupplier(
        BooleanSupplier next
    ) {
        return WithBooleanPredicate.of(absorbBooleanSupplier(next));
    }

    @Evil
    public default WithBooleanPredicate absorbing(BooleanSupplier next) {
        return absorbingBooleanSupplier(next);
    }

    /* BooleanConsumer -> DoubleSupplier<B> */

    @Evil
    public default BooleanToDoubleFunction absorbDoubleSupplier(
        DoubleSupplier next
    ) {
        return (boolean b) -> {
            resolve().accept(b);
            return next.getAsDouble();
        };
    }

    @Evil
    public default BooleanToDoubleFunction absorb(DoubleSupplier next) {
        return absorbDoubleSupplier(next);
    }

    @Evil
    public default WithBooleanToDoubleFunction absorbingDoubleSupplier(
        DoubleSupplier next
    ) {
        return WithBooleanToDoubleFunction.of(absorbDoubleSupplier(next));
    }

    @Evil
    public default WithBooleanToDoubleFunction absorbing(DoubleSupplier next) {
        return absorbingDoubleSupplier(next);
    }

    /* BooleanConsumer -> IntSupplier<B> */

    @Evil
    public default BooleanToIntFunction absorbIntSupplier(IntSupplier next) {
        return (boolean b) -> {
            resolve().accept(b);
            return next.getAsInt();
        };
    }

    @Evil
    public default BooleanToIntFunction absorb(IntSupplier next) {
        return absorbIntSupplier(next);
    }

    @Evil
    public default WithBooleanToIntFunction absorbingIntSupplier(
        IntSupplier next
    ) {
        return WithBooleanToIntFunction.of(absorbIntSupplier(next));
    }

    @Evil
    public default WithBooleanToIntFunction absorbing(IntSupplier next) {
        return absorbingIntSupplier(next);
    }

    /* BooleanConsumer -> LongSupplier<B> */

    @Evil
    public default BooleanToLongFunction absorbLongSupplier(LongSupplier next) {
        return (boolean b) -> {
            resolve().accept(b);
            return next.getAsLong();
        };
    }

    @Evil
    public default BooleanToLongFunction absorb(LongSupplier next) {
        return absorbLongSupplier(next);
    }

    @Evil
    public default WithBooleanToLongFunction absorbingLongSupplier(
        LongSupplier next
    ) {
        return WithBooleanToLongFunction.of(absorbLongSupplier(next));
    }

    @Evil
    public default WithBooleanToLongFunction absorbing(LongSupplier next) {
        return absorbingLongSupplier(next);
    }

    /* BooleanConsumer -> Operator<B> */

    @Evil
    public default BooleanConsumer absorbOperator(Operator next) {
        return (boolean b) -> {
            resolve().accept(b);
            next.run();
        };
    }

    @Evil
    public default BooleanConsumer absorb(Operator next) {
        return absorbOperator(next);
    }

    @Evil
    public default WithBooleanConsumer absorbingOperator(Operator next) {
        return WithBooleanConsumer.of(absorbOperator(next));
    }

    @Evil
    public default WithBooleanConsumer absorbing(Operator next) {
        return absorbingOperator(next);
    }
}
