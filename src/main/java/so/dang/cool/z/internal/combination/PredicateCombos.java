package so.dang.cool.z.internal.combination;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;
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

    public default <B> Function<A, B> fuseBooleanFunction(
        BooleanFunction<B> next
    ) {
        return (A a) -> next.apply(resolve().test(a));
    }

    public default <B> Function<A, B> fuse(BooleanFunction<B> next) {
        return fuseBooleanFunction(next);
    }

    public default <B> WithFunction<A, B> fusingBooleanFunction(
        BooleanFunction<B> next
    ) {
        return WithFunction.of(fuseBooleanFunction(next));
    }

    public default <B> WithFunction<A, B> fusing(BooleanFunction<B> next) {
        return fusingBooleanFunction(next);
    }

    /* Predicate<A> -> BooleanToDoubleFunction */

    public default ToDoubleFunction<A> fuseBooleanToDoubleFunction(
        BooleanToDoubleFunction next
    ) {
        return (A a) -> next.applyAsDouble(resolve().test(a));
    }

    public default ToDoubleFunction<A> fuse(BooleanToDoubleFunction next) {
        return fuseBooleanToDoubleFunction(next);
    }

    public default WithToDoubleFunction<A> fusingBooleanToDoubleFunction(
        BooleanToDoubleFunction next
    ) {
        return WithToDoubleFunction.of(fuseBooleanToDoubleFunction(next));
    }

    public default WithToDoubleFunction<A> fusing(
        BooleanToDoubleFunction next
    ) {
        return fusingBooleanToDoubleFunction(next);
    }

    /* Predicate<A> -> BooleanToIntFunction */

    public default ToIntFunction<A> fuseBooleanToIntFunction(
        BooleanToIntFunction next
    ) {
        return (A a) -> next.applyAsInt(resolve().test(a));
    }

    public default ToIntFunction<A> fuse(BooleanToIntFunction next) {
        return fuseBooleanToIntFunction(next);
    }

    public default WithToIntFunction<A> fusingBooleanToIntFunction(
        BooleanToIntFunction next
    ) {
        return WithToIntFunction.of(fuseBooleanToIntFunction(next));
    }

    public default WithToIntFunction<A> fusing(BooleanToIntFunction next) {
        return fusingBooleanToIntFunction(next);
    }

    /* Predicate<A> -> BooleanToLongFunction */

    public default ToLongFunction<A> fuseBooleanToLongFunction(
        BooleanToLongFunction next
    ) {
        return (A a) -> next.applyAsLong(resolve().test(a));
    }

    public default ToLongFunction<A> fuse(BooleanToLongFunction next) {
        return fuseBooleanToLongFunction(next);
    }

    public default WithToLongFunction<A> fusingBooleanToLongFunction(
        BooleanToLongFunction next
    ) {
        return WithToLongFunction.of(fuseBooleanToLongFunction(next));
    }

    public default WithToLongFunction<A> fusing(BooleanToLongFunction next) {
        return fusingBooleanToLongFunction(next);
    }

    /* Predicate<A> -> BooleanPredicate */

    public default Predicate<A> fuseBooleanPredicate(BooleanPredicate next) {
        return (A a) -> next.test(resolve().test(a));
    }

    public default Predicate<A> fuse(BooleanPredicate next) {
        return fuseBooleanPredicate(next);
    }

    public default WithPredicate<A> fusingBooleanPredicate(
        BooleanPredicate next
    ) {
        return WithPredicate.of(fuseBooleanPredicate(next));
    }

    public default WithPredicate<A> fusing(BooleanPredicate next) {
        return fusingBooleanPredicate(next);
    }

    /* Predicate<A> -> BooleanConsumer */

    public default Consumer<A> fuseBooleanConsumer(BooleanConsumer next) {
        return (A a) -> next.accept(resolve().test(a));
    }

    public default Consumer<A> fuse(BooleanConsumer next) {
        return fuseBooleanConsumer(next);
    }

    public default WithConsumer<A> fusingBooleanConsumer(BooleanConsumer next) {
        return WithConsumer.of(fuseBooleanConsumer(next));
    }

    public default WithConsumer<A> fusing(BooleanConsumer next) {
        return fusingBooleanConsumer(next);
    }
}
