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
import java.util.function.LongPredicate;
import java.util.function.LongToDoubleFunction;
import java.util.function.LongToIntFunction;
import java.util.function.LongUnaryOperator;
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

    public default <B> LongFunction<B> fuseFunction(Function<A, B> next) {
        return (long n) -> next.apply(resolve().apply(n));
    }

    public default <B> LongFunction<B> fuse(Function<A, B> next) {
        return fuseFunction(next);
    }

    public default <B> WithLongFunction<B> fusingFunction(Function<A, B> next) {
        return WithLongFunction.of(fuseFunction(next));
    }

    public default <B> WithLongFunction<B> fusing(Function<A, B> next) {
        return fusingFunction(next);
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

    // TODO: Implement with currying
    // fusingBiFunction(bifn)

    public default <B, C> LongFunction<C> fuseBiFunction(
        BiFunction<A, B, C> next,
        B secondArg
    ) {
        return (long n) -> next.apply(resolve().apply(n), secondArg);
    }

    public default <B, C> LongFunction<C> fuse(
        BiFunction<A, B, C> next,
        B secondArg
    ) {
        return fuseBiFunction(next, secondArg);
    }

    public default <B, C> WithLongFunction<C> fusingBiFunction(
        BiFunction<A, B, C> next,
        B secondArg
    ) {
        return WithLongFunction.of(fuseBiFunction(next, secondArg));
    }

    public default <B, C> WithLongFunction<C> fusing(
        BiFunction<A, B, C> next,
        B secondArg
    ) {
        return fusingBiFunction(next, secondArg);
    }

    /* LongFunction<A> -> ToDoubleFunction<A> */

    public default LongToDoubleFunction fuseToDoubleFunction(
        ToDoubleFunction<A> next
    ) {
        return (long n) -> next.applyAsDouble(resolve().apply(n));
    }

    public default LongToDoubleFunction fuse(ToDoubleFunction<A> next) {
        return fuseToDoubleFunction(next);
    }

    public default WithLongToDoubleFunction fusingToDoubleFunction(
        ToDoubleFunction<A> next
    ) {
        return WithLongToDoubleFunction.of(fuseToDoubleFunction(next));
    }

    public default WithLongToDoubleFunction fusing(ToDoubleFunction<A> next) {
        return fusingToDoubleFunction(next);
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

    // TODO: Implement with currying
    // fusingToDoubleBiFunction(ToDoubleBiFunction<A, B> next)

    public default <B> LongToDoubleFunction fuseToDoubleBiFunction(
        ToDoubleBiFunction<A, B> next,
        B secondArg
    ) {
        return (long n) -> next.applyAsDouble(resolve().apply(n), secondArg);
    }

    public default <B> LongToDoubleFunction fuse(
        ToDoubleBiFunction<A, B> next,
        B secondArg
    ) {
        return fuseToDoubleBiFunction(next, secondArg);
    }

    public default <B> WithLongToDoubleFunction fusingToDoubleBiFunction(
        ToDoubleBiFunction<A, B> next,
        B secondArg
    ) {
        return WithLongToDoubleFunction.of(
            fuseToDoubleBiFunction(next, secondArg)
        );
    }

    public default <B> WithLongToDoubleFunction fusing(
        ToDoubleBiFunction<A, B> next,
        B secondArg
    ) {
        return fusingToDoubleBiFunction(next, secondArg);
    }

    /* LongFunction<A> -> ToIntFunction<A> */

    public default LongToIntFunction fuseToIntFunction(ToIntFunction<A> next) {
        return (long n) -> next.applyAsInt(resolve().apply(n));
    }

    public default LongToIntFunction fuse(ToIntFunction<A> next) {
        return fuseToIntFunction(next);
    }

    public default WithLongToIntFunction fusingToIntFunction(
        ToIntFunction<A> next
    ) {
        return WithLongToIntFunction.of(fuseToIntFunction(next));
    }

    public default WithLongToIntFunction fusing(ToIntFunction<A> next) {
        return fusingToIntFunction(next);
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

    // TODO: Implement with currying
    // fusingToIntBiFunction(ToIntBiFunction<A, B> next)

    public default <B> LongToIntFunction fuseToIntBiFunction(
        ToIntBiFunction<A, B> next,
        B secondArg
    ) {
        return (long n) -> next.applyAsInt(resolve().apply(n), secondArg);
    }

    public default <B> LongToIntFunction fuse(
        ToIntBiFunction<A, B> next,
        B secondArg
    ) {
        return fuseToIntBiFunction(next, secondArg);
    }

    public default <B> WithLongToIntFunction fusingToIntBiFunction(
        ToIntBiFunction<A, B> next,
        B secondArg
    ) {
        return WithLongToIntFunction.of(fuseToIntBiFunction(next, secondArg));
    }

    public default <B> WithLongToIntFunction fusing(
        ToIntBiFunction<A, B> next,
        B secondArg
    ) {
        return fusingToIntBiFunction(next, secondArg);
    }

    /* LongFunction<A> -> ToLongFunction<A> */

    public default LongUnaryOperator fuseToLongFunction(
        ToLongFunction<A> next
    ) {
        return (long n) -> next.applyAsLong(resolve().apply(n));
    }

    public default LongUnaryOperator fuse(ToLongFunction<A> next) {
        return fuseToLongFunction(next);
    }

    public default WithLongUnaryOperator fusingToLongFunction(
        ToLongFunction<A> next
    ) {
        return WithLongUnaryOperator.of(fuseToLongFunction(next));
    }

    public default WithLongUnaryOperator fusing(ToLongFunction<A> next) {
        return fusingToLongFunction(next);
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

    // TODO: Implement with currying
    // fusingToLongBiFunction(ToLongBiFunction<A, B> next)

    public default <B> LongUnaryOperator fuseToLongBiFunction(
        ToLongBiFunction<A, B> next,
        B secondArg
    ) {
        return (long n) -> next.applyAsLong(resolve().apply(n), secondArg);
    }

    public default <B> LongUnaryOperator fuse(
        ToLongBiFunction<A, B> next,
        B secondArg
    ) {
        return fuseToLongBiFunction(next, secondArg);
    }

    public default <B> WithLongUnaryOperator fusingToLongBiFunction(
        ToLongBiFunction<A, B> next,
        B secondArg
    ) {
        return WithLongUnaryOperator.of(fuseToLongBiFunction(next, secondArg));
    }

    public default <B> WithLongUnaryOperator fusing(
        ToLongBiFunction<A, B> next,
        B secondArg
    ) {
        return fusingToLongBiFunction(next, secondArg);
    }

    /* LongFunction<A> -> Predicate<A> */

    public default LongPredicate fusePredicate(Predicate<A> next) {
        return (long n) -> next.test(resolve().apply(n));
    }

    public default LongPredicate fuse(Predicate<A> next) {
        return fusePredicate(next);
    }

    public default WithLongPredicate fusingPredicate(Predicate<A> next) {
        return WithLongPredicate.of(fusePredicate(next));
    }

    public default WithLongPredicate fusing(Predicate<A> next) {
        return fusingPredicate(next);
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

    // TODO: Implement with currying
    // fusingBiPredicate(BiPredicate<A, B> next)

    public default <B> LongPredicate fuseBiPredicate(
        BiPredicate<A, B> next,
        B secondArg
    ) {
        return (long n) -> next.test(resolve().apply(n), secondArg);
    }

    public default <B> LongPredicate fuse(BiPredicate<A, B> next, B secondArg) {
        return fuseBiPredicate(next, secondArg);
    }

    public default <B> WithLongPredicate fusingBiPredicate(
        BiPredicate<A, B> next,
        B secondArg
    ) {
        return WithLongPredicate.of(fuseBiPredicate(next, secondArg));
    }

    public default <B> WithLongPredicate fusing(
        BiPredicate<A, B> next,
        B secondArg
    ) {
        return fusingBiPredicate(next, secondArg);
    }

    /* LongFunction<A> -> Consumer<A> */

    public default LongConsumer fuseConsumer(Consumer<A> next) {
        return (long n) -> next.accept(resolve().apply(n));
    }

    public default LongConsumer fuse(Consumer<A> next) {
        return fuseConsumer(next);
    }

    public default WithLongConsumer fusingConsumer(Consumer<A> next) {
        return WithLongConsumer.of(fuseConsumer(next));
    }

    public default WithLongConsumer fusing(Consumer<A> next) {
        return fusingConsumer(next);
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

    // TODO: Implement with currying
    // fusingBiConsumer(BiConsumer<A, B> next)

    public default <B> LongConsumer fuseBiConsumer(
        BiConsumer<A, B> next,
        B secondArg
    ) {
        return (long n) -> next.accept(resolve().apply(n), secondArg);
    }

    public default <B> LongConsumer fuse(BiConsumer<A, B> next, B secondArg) {
        return fuseBiConsumer(next, secondArg);
    }

    public default <B> WithLongConsumer fusingBiConsumer(
        BiConsumer<A, B> next,
        B secondArg
    ) {
        return WithLongConsumer.of(fuseBiConsumer(next, secondArg));
    }

    public default <B> WithLongConsumer fusing(
        BiConsumer<A, B> next,
        B secondArg
    ) {
        return fusingBiConsumer(next, secondArg);
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

    // TODO: Implement with currying
    // public default LongFunction<DoubleConsumer> fusingObjDoubleConsumer(ObjDoubleConsumer<A> next) {...}

    public default LongConsumer fuseObjDoubleConsumer(
        ObjDoubleConsumer<A> next,
        double secondArg
    ) {
        return (long n) -> next.accept(resolve().apply(n), secondArg);
    }

    public default LongConsumer fuse(
        ObjDoubleConsumer<A> next,
        double secondArg
    ) {
        return fuseObjDoubleConsumer(next, secondArg);
    }

    public default WithLongConsumer fusingObjDoubleConsumer(
        ObjDoubleConsumer<A> next,
        double secondArg
    ) {
        return WithLongConsumer.of(fuseObjDoubleConsumer(next, secondArg));
    }

    public default WithLongConsumer fusing(
        ObjDoubleConsumer<A> next,
        double secondArg
    ) {
        return fusingObjDoubleConsumer(next, secondArg);
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

    // TODO: Implement with currying
    // public default LongFunction<IntConsumer> fusingObjIntConsumer(ObjIntConsumer<A> next) {...}

    public default LongConsumer fuseObjIntConsumer(
        ObjIntConsumer<A> next,
        int secondArg
    ) {
        return (long n) -> next.accept(resolve().apply(n), secondArg);
    }

    public default LongConsumer fuse(ObjIntConsumer<A> next, int secondArg) {
        return fuseObjIntConsumer(next, secondArg);
    }

    public default WithLongConsumer fusingObjIntConsumer(
        ObjIntConsumer<A> next,
        int secondArg
    ) {
        return WithLongConsumer.of(fuseObjIntConsumer(next, secondArg));
    }

    public default WithLongConsumer fusing(
        ObjIntConsumer<A> next,
        int secondArg
    ) {
        return fusingObjIntConsumer(next, secondArg);
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

    // TODO: Implement with currying
    // public default LongFunction<LongConsumer> fusingObjLongConsumer(ObjLongConsumer<A> next) {...}

    public default LongConsumer fuseObjLongConsumer(
        ObjLongConsumer<A> next,
        long secondArg
    ) {
        return (long n) -> next.accept(resolve().apply(n), secondArg);
    }

    public default LongConsumer fuse(ObjLongConsumer<A> next, long secondArg) {
        return fuseObjLongConsumer(next, secondArg);
    }

    public default WithLongConsumer fusingObjLongConsumer(
        ObjLongConsumer<A> next,
        long secondArg
    ) {
        return WithLongConsumer.of(fuseObjLongConsumer(next, secondArg));
    }

    public default WithLongConsumer fusing(
        ObjLongConsumer<A> next,
        long secondArg
    ) {
        return fusingObjLongConsumer(next, secondArg);
    }
}
