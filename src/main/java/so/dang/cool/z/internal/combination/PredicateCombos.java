package so.dang.cool.z.internal.combination;

import java.util.function.Predicate;
import so.dang.cool.z.function.BooleanConsumer;
import so.dang.cool.z.function.BooleanFunction;
import so.dang.cool.z.function.BooleanPredicate;
import so.dang.cool.z.function.BooleanToDoubleFunction;
import so.dang.cool.z.function.BooleanToIntFunction;
import so.dang.cool.z.function.BooleanToLongFunction;
import so.dang.cool.z.internal.combination.Combine.WithConsumer;
import so.dang.cool.z.internal.combination.Combine.WithFunction;
import so.dang.cool.z.internal.combination.Combine.WithPredicate;
import so.dang.cool.z.internal.combination.Combine.WithToDoubleFunction;
import so.dang.cool.z.internal.combination.Combine.WithToIntFunction;
import so.dang.cool.z.internal.combination.Combine.WithToLongFunction;

interface PredicateCombos<A> {
    Predicate<A> resolve();

    /* Predicate<A> -> BooleanFunction<B> */

    public default <B> WithFunction<A, B> fuseBooleanFunction(
        BooleanFunction<B> next
    ) {
        return WithFunction.of((A a) -> next.apply(resolve().test(a)));
    }

    public default <B> WithFunction<A, B> fuse(BooleanFunction<B> next) {
        return fuseBooleanFunction(next);
    }

    /* Predicate<A> -> BooleanToDoubleFunction */

    public default WithToDoubleFunction<A> fuseBooleanToDoubleFunction(
        BooleanToDoubleFunction next
    ) {
        return WithToDoubleFunction.of(
            (A a) -> next.applyAsDouble(resolve().test(a))
        );
    }

    public default WithToDoubleFunction<A> fuse(BooleanToDoubleFunction next) {
        return fuseBooleanToDoubleFunction(next);
    }

    /* Predicate<A> -> BooleanToIntFunction */

    public default WithToIntFunction<A> fuseBooleanToIntFunction(
        BooleanToIntFunction next
    ) {
        return WithToIntFunction.of(
            (A a) -> next.applyAsInt(resolve().test(a))
        );
    }

    public default WithToIntFunction<A> fuse(BooleanToIntFunction next) {
        return fuseBooleanToIntFunction(next);
    }

    /* Predicate<A> -> BooleanToLongFunction */

    public default WithToLongFunction<A> fuseBooleanToLongFunction(
        BooleanToLongFunction next
    ) {
        return WithToLongFunction.of(
            (A a) -> next.applyAsLong(resolve().test(a))
        );
    }

    public default WithToLongFunction<A> fuse(BooleanToLongFunction next) {
        return fuseBooleanToLongFunction(next);
    }

    /* Predicate<A> -> BooleanPredicate */

    public default WithPredicate<A> fuseBooleanPredicate(
        BooleanPredicate next
    ) {
        return WithPredicate.of((A a) -> next.test(resolve().test(a)));
    }

    public default WithPredicate<A> fuse(BooleanPredicate next) {
        return fuseBooleanPredicate(next);
    }

    /* Predicate<A> -> BooleanConsumer */

    public default WithConsumer<A> fuseBooleanConsumer(BooleanConsumer next) {
        return WithConsumer.of((A a) -> next.accept(resolve().test(a)));
    }

    public default WithConsumer<A> fuse(BooleanConsumer next) {
        return fuseBooleanConsumer(next);
    }
}
