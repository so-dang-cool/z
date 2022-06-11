package so.dang.cool.z.internal.combination;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.IntSupplier;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntToLongFunction;
import java.util.function.IntUnaryOperator;
import java.util.function.LongSupplier;
import java.util.function.Supplier;
import so.dang.cool.z.function.Operator;
import so.dang.cool.z.internal.combination.Combine.WithObjIntConsumer;

interface ObjIntConsumerCombos<A> {
    Function<A, IntConsumer> resolve();

    /* ObjIntConsumer<A> -> Supplier<B> */

    public default <B> Function<A, IntFunction<B>> absorbSupplier(
        Supplier<B> next
    ) {
        return (A a) ->
            (int i) -> {
                resolve().apply(a).accept(i);
                return next.get();
            };
    }

    public default <B> Function<A, IntFunction<B>> absorb(Supplier<B> next) {
        return absorbSupplier(next);
    }

    /* ObjIntConsumer<A> -> BooleanSupplier */

    public default Function<A, IntPredicate> absorbBooleanSupplier(
        BooleanSupplier next
    ) {
        return (A a) ->
            (int i) -> {
                resolve().apply(a).accept(i);
                return next.getAsBoolean();
            };
    }

    public default Function<A, IntPredicate> absorb(BooleanSupplier next) {
        return absorbBooleanSupplier(next);
    }

    /* ObjIntConsumer<A> -> DoubleSupplier */

    public default Function<A, IntToDoubleFunction> absorbDoubleSupplier(
        DoubleSupplier next
    ) {
        return (A a) ->
            (int i) -> {
                resolve().apply(a).accept(i);
                return next.getAsDouble();
            };
    }

    public default Function<A, IntToDoubleFunction> absorb(
        DoubleSupplier next
    ) {
        return absorbDoubleSupplier(next);
    }

    /* ObjIntConsumer<A> -> IntSupplier */

    public default Function<A, IntUnaryOperator> absorbIntSupplier(
        IntSupplier next
    ) {
        return (A a) ->
            (int i) -> {
                resolve().apply(a).accept(i);
                return next.getAsInt();
            };
    }

    public default Function<A, IntUnaryOperator> absorb(IntSupplier next) {
        return absorbIntSupplier(next);
    }

    /* ObjIntConsumer<A> -> LongSupplier */

    public default Function<A, IntToLongFunction> absorbLongSupplier(
        LongSupplier next
    ) {
        return (A a) ->
            (int i) -> {
                resolve().apply(a).accept(i);
                return next.getAsLong();
            };
    }

    public default Function<A, IntToLongFunction> absorb(LongSupplier next) {
        return absorbLongSupplier(next);
    }

    /* ObjIntConsumer<A> -> Operator */

    public default WithObjIntConsumer<A> absorbOperator(Operator next) {
        return WithObjIntConsumer.of(
            (A a) ->
                (int i) -> {
                    resolve().apply(a).accept(i);
                    next.run();
                }
        );
    }

    public default WithObjIntConsumer<A> absorb(Operator next) {
        return absorbOperator(next);
    }
}
