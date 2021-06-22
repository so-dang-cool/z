package so.dang.cool.z.internal.combination;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleFunction;
import java.util.function.DoublePredicate;
import java.util.function.DoubleSupplier;
import java.util.function.DoubleToIntFunction;
import java.util.function.DoubleToLongFunction;
import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;
import java.util.function.IntSupplier;
import java.util.function.LongSupplier;
import java.util.function.Supplier;
import so.dang.cool.z.function.Operator;
import so.dang.cool.z.internal.combination.Combine.WithObjDoubleConsumer;

interface ObjDoubleConsumerCombos<A> {
    Function<A, DoubleConsumer> resolve();

    /* ObjDoubleConsumer<A> -> Supplier<B> */

    public default <B> Function<A, DoubleFunction<B>> absorbSupplier(
        Supplier<B> next
    ) {
        return (A a) ->
            (double d) -> {
                resolve().apply(a).accept(d);
                return next.get();
            };
    }

    public default <B> Function<A, DoubleFunction<B>> absorb(Supplier<B> next) {
        return absorbSupplier(next);
    }

    // TODO: Implement with currying
    // absorbingSupplier(Supplier<B> next) { ... }

    /* ObjDoubleConsumer<A> -> BooleanSupplier */

    public default Function<A, DoublePredicate> absorbBooleanSupplier(
        BooleanSupplier next
    ) {
        return (A a) ->
            (double d) -> {
                resolve().apply(a).accept(d);
                return next.getAsBoolean();
            };
    }

    public default Function<A, DoublePredicate> absorb(BooleanSupplier next) {
        return absorbBooleanSupplier(next);
    }

    // TODO: Implement with currying
    // absorbingBooleanSupplier(BooleanSupplier next) { ... }

    /* ObjDoubleConsumer<A> -> IntSupplier */

    public default Function<A, DoubleToIntFunction> absorbIntSupplier(
        IntSupplier next
    ) {
        return (A a) ->
            (double d) -> {
                resolve().apply(a).accept(d);
                return next.getAsInt();
            };
    }

    public default Function<A, DoubleToIntFunction> absorb(IntSupplier next) {
        return absorbIntSupplier(next);
    }

    // TODO: Implement with currying
    // absorbingIntSupplier(IntSupplier<B> next) { ... }

    /* ObjDoubleConsumer<A> -> DoubleSupplier */

    public default Function<A, DoubleUnaryOperator> absorbDoubleSupplier(
        DoubleSupplier next
    ) {
        return (A a) ->
            (double d) -> {
                resolve().apply(a).accept(d);
                return next.getAsDouble();
            };
    }

    public default Function<A, DoubleUnaryOperator> absorb(
        DoubleSupplier next
    ) {
        return absorbDoubleSupplier(next);
    }

    // TODO: Implement with currying
    // absorbingDoubleSupplier(DoubleSupplier<B> next) { ... }

    /* ObjDoubleConsumer<A> -> LongSupplier */

    public default Function<A, DoubleToLongFunction> absorbLongSupplier(
        LongSupplier next
    ) {
        return (A a) ->
            (double d) -> {
                resolve().apply(a).accept(d);
                return next.getAsLong();
            };
    }

    public default Function<A, DoubleToLongFunction> absorb(LongSupplier next) {
        return absorbLongSupplier(next);
    }

    // TODO: Implement with currying
    // absorbingLongSupplier(LongSupplier<B> next) { ... }

    /* ObjDoubleConsumer<A> -> Operator */

    public default Function<A, DoubleConsumer> absorbOperator(Operator next) {
        return (A a) ->
            (double d) -> {
                resolve().apply(a).accept(d);
                next.run();
            };
    }

    public default Function<A, DoubleConsumer> absorb(Operator next) {
        return absorbOperator(next);
    }

    public default WithObjDoubleConsumer<A> absorbingOperator(Operator next) {
        return WithObjDoubleConsumer.of(absorbOperator(next));
    }

    public default WithObjDoubleConsumer<A> absorbing(Operator next) {
        return absorbingOperator(next);
    }
}
