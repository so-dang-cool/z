package so.dang.cool.z.internal.combination;

import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.DoubleSupplier;
import java.util.function.Function;
import java.util.function.IntSupplier;
import java.util.function.LongSupplier;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;
import so.dang.cool.z.annotation.Evil;
import so.dang.cool.z.function.Operator;
import so.dang.cool.z.internal.combination.Combine.WithBiFunction;
import so.dang.cool.z.internal.combination.Combine.WithConsumer;
import so.dang.cool.z.internal.combination.Combine.WithFunction;
import so.dang.cool.z.internal.combination.Combine.WithPredicate;
import so.dang.cool.z.internal.combination.Combine.WithToDoubleFunction;
import so.dang.cool.z.internal.combination.Combine.WithToIntFunction;
import so.dang.cool.z.internal.combination.Combine.WithToLongFunction;

interface ConsumerCombos<A> {
    Consumer<A> resolve();

    /* Consumer<A> -> Function<B, C> */

    @Evil
    public default <B, C> Function<A, Function<B, C>> absorbFunction(
        Function<B, C> next
    ) {
        return (A a) ->
            (B b) -> {
                resolve().accept(a);
                return next.apply(b);
            };
    }

    @Evil
    public default <B, C> Function<A, Function<B, C>> absorb(
        Function<B, C> next
    ) {
        return absorbFunction(next);
    }

    @Evil
    public default <B, C> WithBiFunction<A, B, C> absorbingFunction(
        Function<B, C> next
    ) {
        return WithBiFunction.of(absorbFunction(next));
    }

    @Evil
    public default <B, C> WithBiFunction<A, B, C> absorbing(
        Function<B, C> next
    ) {
        return absorbingFunction(next);
    }

    /* Consumer<A> -> Supplier<B> */

    @Evil
    public default <B> Function<A, B> absorbSupplier(Supplier<B> next) {
        return (A a) -> {
            resolve().accept(a);
            return next.get();
        };
    }

    @Evil
    public default <B> Function<A, B> absorb(Supplier<B> next) {
        return absorbSupplier(next);
    }

    @Evil
    public default <B> WithFunction<A, B> absorbingSupplier(Supplier<B> next) {
        return WithFunction.of(absorbSupplier(next));
    }

    @Evil
    public default <B> WithFunction<A, B> absorbing(Supplier<B> next) {
        return absorbingSupplier(next);
    }

    /* Consumer<A> -> BooleanSupplier<B> */

    @Evil
    public default Predicate<A> absorbBooleanSupplier(BooleanSupplier next) {
        return (A a) -> {
            resolve().accept(a);
            return next.getAsBoolean();
        };
    }

    @Evil
    public default Predicate<A> absorb(BooleanSupplier next) {
        return absorbBooleanSupplier(next);
    }

    @Evil
    public default WithPredicate<A> absorbingBooleanSupplier(
        BooleanSupplier next
    ) {
        return WithPredicate.of(absorbBooleanSupplier(next));
    }

    @Evil
    public default WithPredicate<A> absorbing(BooleanSupplier next) {
        return absorbingBooleanSupplier(next);
    }

    /* Consumer<A> -> DoubleSupplier<B> */

    @Evil
    public default ToDoubleFunction<A> absorbDoubleSupplier(
        DoubleSupplier next
    ) {
        return (A a) -> {
            resolve().accept(a);
            return next.getAsDouble();
        };
    }

    @Evil
    public default ToDoubleFunction<A> absorb(DoubleSupplier next) {
        return absorbDoubleSupplier(next);
    }

    @Evil
    public default WithToDoubleFunction<A> absorbingDoubleSupplier(
        DoubleSupplier next
    ) {
        return WithToDoubleFunction.of(absorbDoubleSupplier(next));
    }

    @Evil
    public default WithToDoubleFunction<A> absorbing(DoubleSupplier next) {
        return absorbingDoubleSupplier(next);
    }

    /* Consumer<A> -> IntSupplier<B> */

    @Evil
    public default ToIntFunction<A> absorbIntSupplier(IntSupplier next) {
        return (A a) -> {
            resolve().accept(a);
            return next.getAsInt();
        };
    }

    @Evil
    public default ToIntFunction<A> absorb(IntSupplier next) {
        return absorbIntSupplier(next);
    }

    @Evil
    public default WithToIntFunction<A> absorbingIntSupplier(IntSupplier next) {
        return WithToIntFunction.of(absorbIntSupplier(next));
    }

    @Evil
    public default WithToIntFunction<A> absorbing(IntSupplier next) {
        return absorbingIntSupplier(next);
    }

    /* Consumer<A> -> LongSupplier<B> */

    @Evil
    public default ToLongFunction<A> absorbLongSupplier(LongSupplier next) {
        return (A a) -> {
            resolve().accept(a);
            return next.getAsLong();
        };
    }

    @Evil
    public default ToLongFunction<A> absorb(LongSupplier next) {
        return absorbLongSupplier(next);
    }

    @Evil
    public default WithToLongFunction<A> absorbingLongSupplier(
        LongSupplier next
    ) {
        return WithToLongFunction.of(absorbLongSupplier(next));
    }

    @Evil
    public default WithToLongFunction<A> absorbing(LongSupplier next) {
        return absorbingLongSupplier(next);
    }

    /* Consumer<A> -> Operator<B> */

    @Evil
    public default Consumer<A> absorbOperator(Operator next) {
        return (A a) -> {
            resolve().accept(a);
            next.run();
        };
    }

    @Evil
    public default Consumer<A> absorb(Operator next) {
        return absorbOperator(next);
    }

    @Evil
    public default WithConsumer<A> absorbingOperator(Operator next) {
        return WithConsumer.of(absorbOperator(next));
    }

    @Evil
    public default WithConsumer<A> absorbing(Operator next) {
        return absorbingOperator(next);
    }
}
