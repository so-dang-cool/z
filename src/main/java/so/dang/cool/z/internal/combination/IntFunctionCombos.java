package so.dang.cool.z.internal.combination;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.LongConsumer;
import java.util.function.ObjDoubleConsumer;
import java.util.function.ObjIntConsumer;
import java.util.function.ObjLongConsumer;
import java.util.function.Predicate;
import java.util.function.ToDoubleBiFunction;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntBiFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongBiFunction;
import java.util.function.ToLongFunction;
import so.dang.cool.z.internal.combination.Combine.WithIntConsumer;
import so.dang.cool.z.internal.combination.Combine.WithIntFunction;
import so.dang.cool.z.internal.combination.Combine.WithIntPredicate;
import so.dang.cool.z.internal.combination.Combine.WithIntToDoubleFunction;
import so.dang.cool.z.internal.combination.Combine.WithIntToLongFunction;
import so.dang.cool.z.internal.combination.Combine.WithIntUnaryOperator;

interface IntFunctionCombos<A> {
    IntFunction<A> resolve();

    /* IntFunction<A> -> Function<A,B> */

    public default <B> WithIntFunction<B> fuseFunction(Function<A, B> next) {
        return WithIntFunction.of((int i) -> next.apply(resolve().apply(i)));
    }

    public default <B> WithIntFunction<B> fuse(Function<A, B> next) {
        return fuseFunction(next);
    }

    /* IntFunction<A> -> BiFunction<A,B,C> */

    public default <B, C> IntFunction<Function<B, C>> fuseBiFunction(
        BiFunction<A, B, C> next
    ) {
        return (int i) -> (B b) -> next.apply(resolve().apply(i), b);
    }

    public default <B, C> IntFunction<Function<B, C>> fuse(
        BiFunction<A, B, C> next
    ) {
        return fuseBiFunction(next);
    }

    /* IntFunction<A> -> ToIntFunction<A> */

    public default WithIntUnaryOperator fuseToIntFunction(
        ToIntFunction<A> next
    ) {
        return WithIntUnaryOperator.of(
            (int i) -> next.applyAsInt(resolve().apply(i))
        );
    }

    public default WithIntUnaryOperator fuse(ToIntFunction<A> next) {
        return fuseToIntFunction(next);
    }

    /* IntFunction<A> -> ToIntBiFunction<A> */

    public default <B> IntFunction<ToIntFunction<B>> fuseToIntBiFunction(
        ToIntBiFunction<A, B> next
    ) {
        return (int i) -> (B b) -> next.applyAsInt(resolve().apply(i), b);
    }

    public default <B> IntFunction<ToIntFunction<B>> fuse(
        ToIntBiFunction<A, B> next
    ) {
        return fuseToIntBiFunction(next);
    }

    /* IntFunction<A> -> ToDoubleFunction<A> */

    public default WithIntToDoubleFunction fuseToDoubleFunction(
        ToDoubleFunction<A> next
    ) {
        return WithIntToDoubleFunction.of(
            (int i) -> next.applyAsDouble(resolve().apply(i))
        );
    }

    public default WithIntToDoubleFunction fuse(ToDoubleFunction<A> next) {
        return fuseToDoubleFunction(next);
    }

    /* IntFunction<A> -> ToDoubleBiFunction<A> */

    public default <B> IntFunction<ToDoubleFunction<B>> fuseToDoubleBiFunction(
        ToDoubleBiFunction<A, B> next
    ) {
        return (int i) -> (B b) -> next.applyAsDouble(resolve().apply(i), b);
    }

    public default <B> IntFunction<ToDoubleFunction<B>> fuse(
        ToDoubleBiFunction<A, B> next
    ) {
        return fuseToDoubleBiFunction(next);
    }

    /* IntFunction<A> -> ToLongFunction<A> */

    public default WithIntToLongFunction fuseToLongFunction(
        ToLongFunction<A> next
    ) {
        return WithIntToLongFunction.of(
            (int i) -> next.applyAsLong(resolve().apply(i))
        );
    }

    public default WithIntToLongFunction fuse(ToLongFunction<A> next) {
        return fuseToLongFunction(next);
    }

    /* IntFunction<A> -> ToLongBiFunction<A> */

    public default <B> IntFunction<ToLongFunction<B>> fuseToLongBiFunction(
        ToLongBiFunction<A, B> next
    ) {
        return (int i) -> (B b) -> next.applyAsLong(resolve().apply(i), b);
    }

    public default <B> IntFunction<ToLongFunction<B>> fuse(
        ToLongBiFunction<A, B> next
    ) {
        return fuseToLongBiFunction(next);
    }

    /* IntFunction<A> -> Predicate<A> */

    public default WithIntPredicate fusePredicate(Predicate<A> next) {
        return WithIntPredicate.of((int i) -> next.test(resolve().apply(i)));
    }

    public default WithIntPredicate fuse(Predicate<A> next) {
        return fusePredicate(next);
    }

    /* IntFunction<A> -> BiPredicate<A> */

    public default <B> IntFunction<Predicate<B>> fuseBiPredicate(
        BiPredicate<A, B> next
    ) {
        return (int i) -> (B b) -> next.test(resolve().apply(i), b);
    }

    public default <B> IntFunction<Predicate<B>> fuse(BiPredicate<A, B> next) {
        return fuseBiPredicate(next);
    }

    /* IntFunction<A> -> Consumer<A> */

    public default WithIntConsumer fuseConsumer(Consumer<A> next) {
        return WithIntConsumer.of((int i) -> next.accept(resolve().apply(i)));
    }

    public default WithIntConsumer fuse(Consumer<A> next) {
        return fuseConsumer(next);
    }

    /* IntFunction<A> -> BiConsumer<A> */

    public default <B> IntFunction<Consumer<B>> fuseBiConsumer(
        BiConsumer<A, B> next
    ) {
        return (int i) -> (B b) -> next.accept(resolve().apply(i), b);
    }

    public default <B> IntFunction<Consumer<B>> fuse(BiConsumer<A, B> next) {
        return fuseBiConsumer(next);
    }

    /* IntFunction<A> -> ObjDoubleConsumer<A> */

    public default IntFunction<DoubleConsumer> fuseObjDoubleConsumer(
        ObjDoubleConsumer<A> next
    ) {
        return (int i) -> (double d) -> next.accept(resolve().apply(i), d);
    }

    public default IntFunction<DoubleConsumer> fuse(ObjDoubleConsumer<A> next) {
        return fuseObjDoubleConsumer(next);
    }

    /* IntFunction<A> -> ObjIntConsumer<A> */

    public default IntFunction<IntConsumer> fuseObjIntConsumer(
        ObjIntConsumer<A> next
    ) {
        return (int b) -> (int d) -> next.accept(resolve().apply(b), d);
    }

    public default IntFunction<IntConsumer> fuse(ObjIntConsumer<A> next) {
        return fuseObjIntConsumer(next);
    }

    /* IntFunction<A> -> ObjLongConsumer<A> */

    public default IntFunction<LongConsumer> fuseObjLongConsumer(
        ObjLongConsumer<A> next
    ) {
        return (int b) -> (long d) -> next.accept(resolve().apply(b), d);
    }

    public default IntFunction<LongConsumer> fuse(ObjLongConsumer<A> next) {
        return fuseObjLongConsumer(next);
    }
}
