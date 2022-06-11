package so.dang.cool.z.internal.combination;

import java.util.function.Function;
import java.util.function.Predicate;
import so.dang.cool.z.function.BooleanConsumer;
import so.dang.cool.z.function.BooleanFunction;
import so.dang.cool.z.function.BooleanPredicate;
import so.dang.cool.z.function.BooleanToDoubleFunction;
import so.dang.cool.z.function.BooleanToIntFunction;
import so.dang.cool.z.function.BooleanToLongFunction;
import so.dang.cool.z.internal.combination.Combine.WithBiConsumer;
import so.dang.cool.z.internal.combination.Combine.WithBiFunction;
import so.dang.cool.z.internal.combination.Combine.WithBiPredicate;
import so.dang.cool.z.internal.combination.Combine.WithToDoubleBiFunction;
import so.dang.cool.z.internal.combination.Combine.WithToIntBiFunction;
import so.dang.cool.z.internal.combination.Combine.WithToLongBiFunction;

interface BiPredicateCombos<A, B> {
    Function<A, Predicate<B>> resolve();

    /* BiPredicate<A, B> -> BooleanFunction<C> */

    public default <C> WithBiFunction<A, B, C> fuseBooleanFunction(
        BooleanFunction<C> next
    ) {
        return WithBiFunction.of(
            (A a) -> (B b) -> next.apply(resolve().apply(a).test(b))
        );
    }

    public default <C> WithBiFunction<A, B, C> fuse(BooleanFunction<C> next) {
        return fuseBooleanFunction(next);
    }

    /* BiPredicate<A, B> -> BooleanToDoubleFunction<C> */

    public default WithToDoubleBiFunction<A, B> fuseBooleanToDoubleFunction(
        BooleanToDoubleFunction next
    ) {
        return WithToDoubleBiFunction.of(
            (A a) -> (B b) -> next.applyAsDouble(resolve().apply(a).test(b))
        );
    }

    public default <C> WithToDoubleBiFunction<A, B> fuse(
        BooleanToDoubleFunction next
    ) {
        return fuseBooleanToDoubleFunction(next);
    }

    /* BiPredicate<A, B> -> BooleanToIntFunction<C> */

    public default <C> WithToIntBiFunction<A, B> fuseBooleanToIntFunction(
        BooleanToIntFunction next
    ) {
        return WithToIntBiFunction.of(
            (A a) -> (B b) -> next.applyAsInt(resolve().apply(a).test(b))
        );
    }

    public default <C> WithToIntBiFunction<A, B> fuse(
        BooleanToIntFunction next
    ) {
        return fuseBooleanToIntFunction(next);
    }

    /* BiPredicate<A, B> -> BooleanToLongFunction<C> */

    public default <C> WithToLongBiFunction<A, B> fuseBooleanToLongFunction(
        BooleanToLongFunction next
    ) {
        return WithToLongBiFunction.of(
            (A a) -> (B b) -> next.applyAsLong(resolve().apply(a).test(b))
        );
    }

    public default <C> WithToLongBiFunction<A, B> fuse(
        BooleanToLongFunction next
    ) {
        return fuseBooleanToLongFunction(next);
    }

    /* BiPredicate<A, B> -> BooleanPredicate<C> */

    public default <C> WithBiPredicate<A, B> fuseBooleanPredicate(
        BooleanPredicate next
    ) {
        return WithBiPredicate.of(
            (A a) -> (B b) -> next.test(resolve().apply(a).test(b))
        );
    }

    public default <C> WithBiPredicate<A, B> fuse(BooleanPredicate next) {
        return fuseBooleanPredicate(next);
    }

    /* BiPredicate<A, B> -> BooleanConsumer<C> */

    public default <C> WithBiConsumer<A, B> fuseBooleanConsumer(
        BooleanConsumer next
    ) {
        return WithBiConsumer.of(
            (A a) -> (B b) -> next.accept(resolve().apply(a).test(b))
        );
    }

    public default <C> WithBiConsumer<A, B> fuse(BooleanConsumer next) {
        return fuseBooleanConsumer(next);
    }
}
