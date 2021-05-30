package so.dang.cool.z.internal.combination;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import so.dang.cool.z.annotation.Evil;
import so.dang.cool.z.internal.combination.Combine.WithBiFunction;
import so.dang.cool.z.internal.combination.Combine.WithFunction;

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
}
