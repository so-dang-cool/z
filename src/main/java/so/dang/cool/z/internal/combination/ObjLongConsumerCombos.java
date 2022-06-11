package so.dang.cool.z.internal.combination;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;
import java.util.function.Function;
import java.util.function.IntSupplier;
import java.util.function.LongConsumer;
import java.util.function.LongFunction;
import java.util.function.LongPredicate;
import java.util.function.LongSupplier;
import java.util.function.LongToDoubleFunction;
import java.util.function.LongToIntFunction;
import java.util.function.LongUnaryOperator;
import java.util.function.Supplier;
import so.dang.cool.z.function.Operator;
import so.dang.cool.z.internal.combination.Combine.WithObjLongConsumer;

interface ObjLongConsumerCombos<A> {
    Function<A, LongConsumer> resolve();

    /* ObjLongConsumer<A> -> Supplier<B> */

    public default <B> Function<A, LongFunction<B>> absorbSupplier(
        Supplier<B> next
    ) {
        return (A a) ->
            (long n) -> {
                resolve().apply(a).accept(n);
                return next.get();
            };
    }

    public default <B> Function<A, LongFunction<B>> absorb(Supplier<B> next) {
        return absorbSupplier(next);
    }

    /* ObjLongConsumer<A> -> BooleanSupplier */

    public default Function<A, LongPredicate> absorbBooleanSupplier(
        BooleanSupplier next
    ) {
        return (A a) ->
            (long n) -> {
                resolve().apply(a).accept(n);
                return next.getAsBoolean();
            };
    }

    public default Function<A, LongPredicate> absorb(BooleanSupplier next) {
        return absorbBooleanSupplier(next);
    }

    /* ObjLongConsumer<A> -> IntSupplier */

    public default Function<A, LongToIntFunction> absorbIntSupplier(
        IntSupplier next
    ) {
        return (A a) ->
            (long n) -> {
                resolve().apply(a).accept(n);
                return next.getAsInt();
            };
    }

    public default Function<A, LongToIntFunction> absorb(IntSupplier next) {
        return absorbIntSupplier(next);
    }

    /* ObjLongConsumer<A> -> DoubleSupplier */

    public default Function<A, LongToDoubleFunction> absorbDoubleSupplier(
        DoubleSupplier next
    ) {
        return (A a) ->
            (long n) -> {
                resolve().apply(a).accept(n);
                return next.getAsDouble();
            };
    }

    public default Function<A, LongToDoubleFunction> absorb(
        DoubleSupplier next
    ) {
        return absorbDoubleSupplier(next);
    }

    /* ObjLongConsumer<A> -> LongSupplier */

    public default Function<A, LongUnaryOperator> absorbLongSupplier(
        LongSupplier next
    ) {
        return (A a) ->
            (long n) -> {
                resolve().apply(a).accept(n);
                return next.getAsLong();
            };
    }

    public default Function<A, LongUnaryOperator> absorb(LongSupplier next) {
        return absorbLongSupplier(next);
    }

    /* ObjLongConsumer<A> -> Operator */

    public default WithObjLongConsumer<A> absorbOperator(Operator next) {
        return WithObjLongConsumer.of(
            (A a) ->
                (long n) -> {
                    resolve().apply(a).accept(n);
                    next.run();
                }
        );
    }

    public default WithObjLongConsumer<A> absorb(Operator next) {
        return absorbOperator(next);
    }
}
