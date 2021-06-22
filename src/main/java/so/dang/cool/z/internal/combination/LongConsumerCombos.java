package so.dang.cool.z.internal.combination;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;
import java.util.function.IntSupplier;
import java.util.function.LongConsumer;
import java.util.function.LongFunction;
import java.util.function.LongPredicate;
import java.util.function.LongSupplier;
import java.util.function.LongToDoubleFunction;
import java.util.function.LongToIntFunction;
import java.util.function.LongUnaryOperator;
import java.util.function.Supplier;
import so.dang.cool.z.annotation.Evil;
import so.dang.cool.z.function.Operator;
import so.dang.cool.z.internal.combination.Combine.WithLongConsumer;
import so.dang.cool.z.internal.combination.Combine.WithLongFunction;
import so.dang.cool.z.internal.combination.Combine.WithLongPredicate;
import so.dang.cool.z.internal.combination.Combine.WithLongToDoubleFunction;
import so.dang.cool.z.internal.combination.Combine.WithLongToIntFunction;
import so.dang.cool.z.internal.combination.Combine.WithLongUnaryOperator;

interface LongConsumerCombos {
    LongConsumer resolve();

    /* LongConsumer -> Supplier<A> */

    @Evil
    public default <A> LongFunction<A> absorbSupplier(Supplier<A> next) {
        return (long n) -> {
            resolve().accept(n);
            return next.get();
        };
    }

    @Evil
    public default <A> LongFunction<A> absorb(Supplier<A> next) {
        return absorbSupplier(next);
    }

    @Evil
    public default <A> WithLongFunction<A> absorbingSupplier(Supplier<A> next) {
        return WithLongFunction.of(absorbSupplier(next));
    }

    @Evil
    public default <A> WithLongFunction<A> absorbing(Supplier<A> next) {
        return absorbingSupplier(next);
    }

    /* LongConsumer -> BooleanSupplier<A> */

    @Evil
    public default LongPredicate absorbBooleanSupplier(BooleanSupplier next) {
        return (long n) -> {
            resolve().accept(n);
            return next.getAsBoolean();
        };
    }

    @Evil
    public default LongPredicate absorb(BooleanSupplier next) {
        return absorbBooleanSupplier(next);
    }

    @Evil
    public default WithLongPredicate absorbingBooleanSupplier(
        BooleanSupplier next
    ) {
        return WithLongPredicate.of(absorbBooleanSupplier(next));
    }

    @Evil
    public default WithLongPredicate absorbing(BooleanSupplier next) {
        return absorbingBooleanSupplier(next);
    }

    /* LongConsumer -> DoubleSupplier<B> */

    @Evil
    public default LongToDoubleFunction absorbDoubleSupplier(
        DoubleSupplier next
    ) {
        return (long n) -> {
            resolve().accept(n);
            return next.getAsDouble();
        };
    }

    @Evil
    public default LongToDoubleFunction absorb(DoubleSupplier next) {
        return absorbDoubleSupplier(next);
    }

    @Evil
    public default WithLongToDoubleFunction absorbingDoubleSupplier(
        DoubleSupplier next
    ) {
        return WithLongToDoubleFunction.of(absorbDoubleSupplier(next));
    }

    @Evil
    public default WithLongToDoubleFunction absorbing(DoubleSupplier next) {
        return absorbingDoubleSupplier(next);
    }

    /* LongConsumer -> IntSupplier<B> */

    @Evil
    public default LongToIntFunction absorbIntSupplier(IntSupplier next) {
        return (long n) -> {
            resolve().accept(n);
            return next.getAsInt();
        };
    }

    @Evil
    public default LongToIntFunction absorb(IntSupplier next) {
        return absorbIntSupplier(next);
    }

    @Evil
    public default WithLongToIntFunction absorbingIntSupplier(
        IntSupplier next
    ) {
        return WithLongToIntFunction.of(absorbIntSupplier(next));
    }

    @Evil
    public default WithLongToIntFunction absorbing(IntSupplier next) {
        return absorbingIntSupplier(next);
    }

    /* LongConsumer -> LongSupplier<B> */

    @Evil
    public default LongUnaryOperator absorbLongSupplier(LongSupplier next) {
        return (long n) -> {
            resolve().accept(n);
            return next.getAsLong();
        };
    }

    @Evil
    public default LongUnaryOperator absorb(LongSupplier next) {
        return absorbLongSupplier(next);
    }

    @Evil
    public default WithLongUnaryOperator absorbingLongSupplier(
        LongSupplier next
    ) {
        return WithLongUnaryOperator.of(absorbLongSupplier(next));
    }

    @Evil
    public default WithLongUnaryOperator absorbing(LongSupplier next) {
        return absorbingLongSupplier(next);
    }

    /* LongConsumer -> Operator<B> */

    @Evil
    public default LongConsumer absorbOperator(Operator next) {
        return (long n) -> {
            resolve().accept(n);
            next.run();
        };
    }

    @Evil
    public default LongConsumer absorb(Operator next) {
        return absorbOperator(next);
    }

    @Evil
    public default WithLongConsumer absorbingOperator(Operator next) {
        return WithLongConsumer.of(absorbOperator(next));
    }

    @Evil
    public default WithLongConsumer absorbing(Operator next) {
        return absorbingOperator(next);
    }
}
