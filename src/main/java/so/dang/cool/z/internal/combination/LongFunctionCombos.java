package so.dang.cool.z.internal.combination;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.function.LongConsumer;
import java.util.function.LongFunction;
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
import so.dang.cool.z.internal.combination.Combine.WithLongConsumer;
import so.dang.cool.z.internal.combination.Combine.WithLongFunction;
import so.dang.cool.z.internal.combination.Combine.WithLongPredicate;
import so.dang.cool.z.internal.combination.Combine.WithLongToDoubleFunction;
import so.dang.cool.z.internal.combination.Combine.WithLongToIntFunction;
import so.dang.cool.z.internal.combination.Combine.WithLongUnaryOperator;

interface LongFunctionCombos<A> {
    LongFunction<A> resolve();

    /* LongFunction<A> -> Function<A,B> */

    public default <B> WithLongFunction<B> fuseFunction(Function<A, B> next) {
        return WithLongFunction.of((long n) -> next.apply(resolve().apply(n)));
    }

    public default <B> WithLongFunction<B> fuse(Function<A, B> next) {
        return fuseFunction(next);
    }

    /* LongFunction<A> -> BiFunction<A,B,C> */

    public default <B, C> LongFunction<Function<B, C>> fuseBiFunction(
        BiFunction<A, B, C> next
    ) {
        return (long n) -> (B b) -> next.apply(resolve().apply(n), b);
    }

    public default <B, C> LongFunction<Function<B, C>> fuse(
        BiFunction<A, B, C> next
    ) {
        return fuseBiFunction(next);
    }

    /* LongFunction<A> -> ToDoubleFunction<A> */

    public default WithLongToDoubleFunction fuseToDoubleFunction(
        ToDoubleFunction<A> next
    ) {
        return WithLongToDoubleFunction.of(
            (long n) -> next.applyAsDouble(resolve().apply(n))
        );
    }

    public default WithLongToDoubleFunction fuse(ToDoubleFunction<A> next) {
        return fuseToDoubleFunction(next);
    }

    /* LongFunction<A> -> ToDoubleBiFunction<A> */

    public default <B> LongFunction<ToDoubleFunction<B>> fuseToDoubleBiFunction(
        ToDoubleBiFunction<A, B> next
    ) {
        return (long n) -> (B b) -> next.applyAsDouble(resolve().apply(n), b);
    }

    public default <B> LongFunction<ToDoubleFunction<B>> fuse(
        ToDoubleBiFunction<A, B> next
    ) {
        return fuseToDoubleBiFunction(next);
    }

    /* LongFunction<A> -> ToIntFunction<A> */

    public default WithLongToIntFunction fuseToIntFunction(
        ToIntFunction<A> next
    ) {
        return WithLongToIntFunction.of(
            (long n) -> next.applyAsInt(resolve().apply(n))
        );
    }

    public default WithLongToIntFunction fuse(ToIntFunction<A> next) {
        return fuseToIntFunction(next);
    }

    /* LongFunction<A> -> ToIntBiFunction<A> */

    public default <B> LongFunction<ToIntFunction<B>> fuseToIntBiFunction(
        ToIntBiFunction<A, B> next
    ) {
        return (long n) -> (B b) -> next.applyAsInt(resolve().apply(n), b);
    }

    public default <B> LongFunction<ToIntFunction<B>> fuse(
        ToIntBiFunction<A, B> next
    ) {
        return fuseToIntBiFunction(next);
    }

    /* LongFunction<A> -> ToLongFunction<A> */

    public default WithLongUnaryOperator fuseToLongFunction(
        ToLongFunction<A> next
    ) {
        return WithLongUnaryOperator.of(
            (long n) -> next.applyAsLong(resolve().apply(n))
        );
    }

    public default WithLongUnaryOperator fuse(ToLongFunction<A> next) {
        return fuseToLongFunction(next);
    }

    /* LongFunction<A> -> ToLongBiFunction<A> */

    public default <B> LongFunction<ToLongFunction<B>> fuseToLongBiFunction(
        ToLongBiFunction<A, B> next
    ) {
        return (long n) -> (B b) -> next.applyAsLong(resolve().apply(n), b);
    }

    public default <B> LongFunction<ToLongFunction<B>> fuse(
        ToLongBiFunction<A, B> next
    ) {
        return fuseToLongBiFunction(next);
    }

    /* LongFunction<A> -> Predicate<A> */

    public default WithLongPredicate fusePredicate(Predicate<A> next) {
        return WithLongPredicate.of((long n) -> next.test(resolve().apply(n)));
    }

    public default WithLongPredicate fuse(Predicate<A> next) {
        return fusePredicate(next);
    }

    /* LongFunction<A> -> BiPredicate<A> */

    public default <B> LongFunction<Predicate<B>> fuseBiPredicate(
        BiPredicate<A, B> next
    ) {
        return (long n) -> (B b) -> next.test(resolve().apply(n), b);
    }

    public default <B> LongFunction<Predicate<B>> fuse(BiPredicate<A, B> next) {
        return fuseBiPredicate(next);
    }

    /* LongFunction<A> -> Consumer<A> */

    public default WithLongConsumer fuseConsumer(Consumer<A> next) {
        return WithLongConsumer.of((long n) -> next.accept(resolve().apply(n)));
    }

    public default WithLongConsumer fuse(Consumer<A> next) {
        return fuseConsumer(next);
    }

    /* LongFunction<A> -> BiConsumer<A> */

    public default <B> LongFunction<Consumer<B>> fuseBiConsumer(
        BiConsumer<A, B> next
    ) {
        return (long n) -> (B b) -> next.accept(resolve().apply(n), b);
    }

    public default <B> LongFunction<Consumer<B>> fuse(BiConsumer<A, B> next) {
        return fuseBiConsumer(next);
    }

    /* LongFunction<A> -> ObjDoubleConsumer<A> */

    public default LongFunction<DoubleConsumer> fuseObjDoubleConsumer(
        ObjDoubleConsumer<A> next
    ) {
        return (long n) -> (double d) -> next.accept(resolve().apply(n), d);
    }

    public default LongFunction<DoubleConsumer> fuse(
        ObjDoubleConsumer<A> next
    ) {
        return fuseObjDoubleConsumer(next);
    }

    /* LongFunction<A> -> ObjIntConsumer<A> */

    public default LongFunction<IntConsumer> fuseObjIntConsumer(
        ObjIntConsumer<A> next
    ) {
        return (long n) -> (int d) -> next.accept(resolve().apply(n), d);
    }

    public default LongFunction<IntConsumer> fuse(ObjIntConsumer<A> next) {
        return fuseObjIntConsumer(next);
    }

    /* LongFunction<A> -> ObjLongConsumer<A> */

    public default LongFunction<LongConsumer> fuseObjLongConsumer(
        ObjLongConsumer<A> next
    ) {
        return (long n) -> (long d) -> next.accept(resolve().apply(n), d);
    }

    public default LongFunction<LongConsumer> fuse(ObjLongConsumer<A> next) {
        return fuseObjLongConsumer(next);
    }
}
