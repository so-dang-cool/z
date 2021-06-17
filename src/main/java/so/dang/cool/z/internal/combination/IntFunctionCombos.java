package so.dang.cool.z.internal.combination;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntToLongFunction;
import java.util.function.IntUnaryOperator;
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

    public default <B> IntFunction<B> fuseFunction(Function<A, B> next) {
        return (int i) -> next.apply(resolve().apply(i));
    }

    public default <B> IntFunction<B> fuse(Function<A, B> next) {
        return fuseFunction(next);
    }

    public default <B> WithIntFunction<B> fusingFunction(Function<A, B> next) {
        return WithIntFunction.of(fuseFunction(next));
    }

    public default <B> WithIntFunction<B> fusing(Function<A, B> next) {
        return fusingFunction(next);
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

    // TODO: Implement with currying
    // fusingBiFunction(bifn)

    public default <B, C> IntFunction<C> fuseBiFunction(
        BiFunction<A, B, C> next,
        B secondArg
    ) {
        return (int i) -> next.apply(resolve().apply(i), secondArg);
    }

    public default <B, C> IntFunction<C> fuse(
        BiFunction<A, B, C> next,
        B secondArg
    ) {
        return fuseBiFunction(next, secondArg);
    }

    public default <B, C> WithIntFunction<C> fusingBiFunction(
        BiFunction<A, B, C> next,
        B secondArg
    ) {
        return WithIntFunction.of(fuseBiFunction(next, secondArg));
    }

    public default <B, C> WithIntFunction<C> fusing(
        BiFunction<A, B, C> next,
        B secondArg
    ) {
        return fusingBiFunction(next, secondArg);
    }

    /* IntFunction<A> -> ToIntFunction<A> */

    public default IntUnaryOperator fuseToIntFunction(ToIntFunction<A> next) {
        return (int i) -> next.applyAsInt(resolve().apply(i));
    }

    public default IntUnaryOperator fuse(ToIntFunction<A> next) {
        return fuseToIntFunction(next);
    }

    public default WithIntUnaryOperator fusingToIntFunction(
        ToIntFunction<A> next
    ) {
        return WithIntUnaryOperator.of(fuseToIntFunction(next));
    }

    public default WithIntUnaryOperator fusing(ToIntFunction<A> next) {
        return fusingToIntFunction(next);
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

    // TODO: Implement with currying
    // fusingToIntBiFunction(ToIntBiFunction<A, B> next)

    public default <B> IntUnaryOperator fuseToIntBiFunction(
        ToIntBiFunction<A, B> next,
        B secondArg
    ) {
        return (int i) -> next.applyAsInt(resolve().apply(i), secondArg);
    }

    public default <B> IntUnaryOperator fuse(
        ToIntBiFunction<A, B> next,
        B secondArg
    ) {
        return fuseToIntBiFunction(next, secondArg);
    }

    public default <B> WithIntUnaryOperator fusingToIntBiFunction(
        ToIntBiFunction<A, B> next,
        B secondArg
    ) {
        return WithIntUnaryOperator.of(fuseToIntBiFunction(next, secondArg));
    }

    public default <B> WithIntUnaryOperator fusing(
        ToIntBiFunction<A, B> next,
        B secondArg
    ) {
        return fusingToIntBiFunction(next, secondArg);
    }

    /* IntFunction<A> -> ToDoubleFunction<A> */

    public default IntToDoubleFunction fuseToDoubleFunction(
        ToDoubleFunction<A> next
    ) {
        return (int i) -> next.applyAsDouble(resolve().apply(i));
    }

    public default IntToDoubleFunction fuse(ToDoubleFunction<A> next) {
        return fuseToDoubleFunction(next);
    }

    public default WithIntToDoubleFunction fusingToDoubleFunction(
        ToDoubleFunction<A> next
    ) {
        return WithIntToDoubleFunction.of(fuseToDoubleFunction(next));
    }

    public default WithIntToDoubleFunction fusing(ToDoubleFunction<A> next) {
        return fusingToDoubleFunction(next);
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

    // TODO: Implement with currying
    // fusingToDoubleBiFunction(ToDoubleBiFunction<A, B> next)

    public default <B> IntToDoubleFunction fuseToDoubleBiFunction(
        ToDoubleBiFunction<A, B> next,
        B secondArg
    ) {
        return (int i) -> next.applyAsDouble(resolve().apply(i), secondArg);
    }

    public default <B> IntToDoubleFunction fuse(
        ToDoubleBiFunction<A, B> next,
        B secondArg
    ) {
        return fuseToDoubleBiFunction(next, secondArg);
    }

    public default <B> WithIntToDoubleFunction fusingToDoubleBiFunction(
        ToDoubleBiFunction<A, B> next,
        B secondArg
    ) {
        return WithIntToDoubleFunction.of(
            fuseToDoubleBiFunction(next, secondArg)
        );
    }

    public default <B> WithIntToDoubleFunction fusing(
        ToDoubleBiFunction<A, B> next,
        B secondArg
    ) {
        return fusingToDoubleBiFunction(next, secondArg);
    }

    /* IntFunction<A> -> ToLongFunction<A> */

    public default IntToLongFunction fuseToLongFunction(
        ToLongFunction<A> next
    ) {
        return (int i) -> next.applyAsLong(resolve().apply(i));
    }

    public default IntToLongFunction fuse(ToLongFunction<A> next) {
        return fuseToLongFunction(next);
    }

    public default WithIntToLongFunction fusingToLongFunction(
        ToLongFunction<A> next
    ) {
        return WithIntToLongFunction.of(fuseToLongFunction(next));
    }

    public default WithIntToLongFunction fusing(ToLongFunction<A> next) {
        return fusingToLongFunction(next);
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

    // TODO: Implement with currying
    // fusingToLongBiFunction(ToLongBiFunction<A, B> next)

    public default <B> IntToLongFunction fuseToLongBiFunction(
        ToLongBiFunction<A, B> next,
        B secondArg
    ) {
        return (int i) -> next.applyAsLong(resolve().apply(i), secondArg);
    }

    public default <B> IntToLongFunction fuse(
        ToLongBiFunction<A, B> next,
        B secondArg
    ) {
        return fuseToLongBiFunction(next, secondArg);
    }

    public default <B> WithIntToLongFunction fusingToLongBiFunction(
        ToLongBiFunction<A, B> next,
        B secondArg
    ) {
        return WithIntToLongFunction.of(fuseToLongBiFunction(next, secondArg));
    }

    public default <B> WithIntToLongFunction fusing(
        ToLongBiFunction<A, B> next,
        B secondArg
    ) {
        return fusingToLongBiFunction(next, secondArg);
    }

    /* IntFunction<A> -> Predicate<A> */

    public default IntPredicate fusePredicate(Predicate<A> next) {
        return (int i) -> next.test(resolve().apply(i));
    }

    public default IntPredicate fuse(Predicate<A> next) {
        return fusePredicate(next);
    }

    public default WithIntPredicate fusingPredicate(Predicate<A> next) {
        return WithIntPredicate.of(fusePredicate(next));
    }

    public default WithIntPredicate fusing(Predicate<A> next) {
        return fusingPredicate(next);
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

    // TODO: Implement with currying
    // fusingBiPredicate(BiPredicate<A, B> next)

    public default <B> IntPredicate fuseBiPredicate(
        BiPredicate<A, B> next,
        B secondArg
    ) {
        return (int i) -> next.test(resolve().apply(i), secondArg);
    }

    public default <B> IntPredicate fuse(BiPredicate<A, B> next, B secondArg) {
        return fuseBiPredicate(next, secondArg);
    }

    public default <B> WithIntPredicate fusingBiPredicate(
        BiPredicate<A, B> next,
        B secondArg
    ) {
        return WithIntPredicate.of(fuseBiPredicate(next, secondArg));
    }

    public default <B> WithIntPredicate fusing(
        BiPredicate<A, B> next,
        B secondArg
    ) {
        return fusingBiPredicate(next, secondArg);
    }

    /* IntFunction<A> -> Consumer<A> */

    public default IntConsumer fuseConsumer(Consumer<A> next) {
        return (int i) -> next.accept(resolve().apply(i));
    }

    public default IntConsumer fuse(Consumer<A> next) {
        return fuseConsumer(next);
    }

    public default WithIntConsumer fusingConsumer(Consumer<A> next) {
        return WithIntConsumer.of(fuseConsumer(next));
    }

    public default WithIntConsumer fusing(Consumer<A> next) {
        return fusingConsumer(next);
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

    // TODO: Implement with currying
    // fusingBiConsumer(BiConsumer<A, B> next)

    public default <B> IntConsumer fuseBiConsumer(
        BiConsumer<A, B> next,
        B secondArg
    ) {
        return (int i) -> next.accept(resolve().apply(i), secondArg);
    }

    public default <B> IntConsumer fuse(BiConsumer<A, B> next, B secondArg) {
        return fuseBiConsumer(next, secondArg);
    }

    public default <B> WithIntConsumer fusingBiConsumer(
        BiConsumer<A, B> next,
        B secondArg
    ) {
        return WithIntConsumer.of(fuseBiConsumer(next, secondArg));
    }

    public default <B> WithIntConsumer fusing(
        BiConsumer<A, B> next,
        B secondArg
    ) {
        return fusingBiConsumer(next, secondArg);
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

    // TODO: Implement with currying
    // public default IntFunction<DoubleConsumer> fusingObjDoubleConsumer(ObjDoubleConsumer<A> next) {...}

    public default IntConsumer fuseObjDoubleConsumer(
        ObjDoubleConsumer<A> next,
        double secondArg
    ) {
        return (int i) -> next.accept(resolve().apply(i), secondArg);
    }

    public default IntConsumer fuse(
        ObjDoubleConsumer<A> next,
        double secondArg
    ) {
        return fuseObjDoubleConsumer(next, secondArg);
    }

    public default WithIntConsumer fusingObjDoubleConsumer(
        ObjDoubleConsumer<A> next,
        double secondArg
    ) {
        return WithIntConsumer.of(fuseObjDoubleConsumer(next, secondArg));
    }

    public default WithIntConsumer fusing(
        ObjDoubleConsumer<A> next,
        double secondArg
    ) {
        return fusingObjDoubleConsumer(next, secondArg);
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

    // TODO: Implement with currying
    // public default IntFunction<IntConsumer> fusingObjIntConsumer(ObjIntConsumer<A> next) {...}

    public default IntConsumer fuseObjIntConsumer(
        ObjIntConsumer<A> next,
        int secondArg
    ) {
        return (int b) -> next.accept(resolve().apply(b), secondArg);
    }

    public default IntConsumer fuse(ObjIntConsumer<A> next, int secondArg) {
        return fuseObjIntConsumer(next, secondArg);
    }

    public default WithIntConsumer fusingObjIntConsumer(
        ObjIntConsumer<A> next,
        int secondArg
    ) {
        return WithIntConsumer.of(fuseObjIntConsumer(next, secondArg));
    }

    public default WithIntConsumer fusing(
        ObjIntConsumer<A> next,
        int secondArg
    ) {
        return fusingObjIntConsumer(next, secondArg);
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

    // TODO: Implement with currying
    // public default IntFunction<LongConsumer> fusingObjLongConsumer(ObjLongConsumer<A> next) {...}

    public default IntConsumer fuseObjLongConsumer(
        ObjLongConsumer<A> next,
        long secondArg
    ) {
        return (int b) -> next.accept(resolve().apply(b), secondArg);
    }

    public default IntConsumer fuse(ObjLongConsumer<A> next, long secondArg) {
        return fuseObjLongConsumer(next, secondArg);
    }

    public default WithIntConsumer fusingObjLongConsumer(
        ObjLongConsumer<A> next,
        long secondArg
    ) {
        return WithIntConsumer.of(fuseObjLongConsumer(next, secondArg));
    }

    public default WithIntConsumer fusing(
        ObjLongConsumer<A> next,
        long secondArg
    ) {
        return fusingObjLongConsumer(next, secondArg);
    }
}
