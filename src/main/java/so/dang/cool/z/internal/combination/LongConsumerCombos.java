package so.dang.cool.z.internal.combination;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;
import java.util.function.IntSupplier;
import java.util.function.LongConsumer;
import java.util.function.LongSupplier;
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
    public default <A> WithLongFunction<A> absorbSupplier(Supplier<A> next) {
        return WithLongFunction.of(
            (long n) -> {
                resolve().accept(n);
                return next.get();
            }
        );
    }

    @Evil
    public default <A> WithLongFunction<A> absorb(Supplier<A> next) {
        return absorbSupplier(next);
    }

    /* LongConsumer -> BooleanSupplier<A> */

    @Evil
    public default WithLongPredicate absorbBooleanSupplier(
        BooleanSupplier next
    ) {
        return WithLongPredicate.of(
            (long n) -> {
                resolve().accept(n);
                return next.getAsBoolean();
            }
        );
    }

    @Evil
    public default WithLongPredicate absorb(BooleanSupplier next) {
        return absorbBooleanSupplier(next);
    }

    /* LongConsumer -> DoubleSupplier<B> */

    @Evil
    public default WithLongToDoubleFunction absorbDoubleSupplier(
        DoubleSupplier next
    ) {
        return WithLongToDoubleFunction.of(
            (long n) -> {
                resolve().accept(n);
                return next.getAsDouble();
            }
        );
    }

    @Evil
    public default WithLongToDoubleFunction absorb(DoubleSupplier next) {
        return absorbDoubleSupplier(next);
    }

    /* LongConsumer -> IntSupplier<B> */

    @Evil
    public default WithLongToIntFunction absorbIntSupplier(IntSupplier next) {
        return WithLongToIntFunction.of(
            (long n) -> {
                resolve().accept(n);
                return next.getAsInt();
            }
        );
    }

    @Evil
    public default WithLongToIntFunction absorb(IntSupplier next) {
        return absorbIntSupplier(next);
    }

    /* LongConsumer -> LongSupplier<B> */

    @Evil
    public default WithLongUnaryOperator absorbLongSupplier(LongSupplier next) {
        return WithLongUnaryOperator.of(
            (long n) -> {
                resolve().accept(n);
                return next.getAsLong();
            }
        );
    }

    @Evil
    public default WithLongUnaryOperator absorb(LongSupplier next) {
        return absorbLongSupplier(next);
    }

    /* LongConsumer -> Operator<B> */

    @Evil
    public default WithLongConsumer absorbOperator(Operator next) {
        return WithLongConsumer.of(
            (long n) -> {
                resolve().accept(n);
                next.run();
            }
        );
    }

    @Evil
    public default WithLongConsumer absorb(Operator next) {
        return absorbOperator(next);
    }
}
