package so.dang.cool.z.internal.combination;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleFunction;
import java.util.function.DoublePredicate;
import java.util.function.DoubleToIntFunction;
import java.util.function.DoubleToLongFunction;
import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;
import java.util.function.IntConsumer;
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
import so.dang.cool.z.internal.combination.Combine.WithDoubleConsumer;
import so.dang.cool.z.internal.combination.Combine.WithDoubleFunction;
import so.dang.cool.z.internal.combination.Combine.WithDoublePredicate;
import so.dang.cool.z.internal.combination.Combine.WithDoubleToIntFunction;
import so.dang.cool.z.internal.combination.Combine.WithDoubleToLongFunction;
import so.dang.cool.z.internal.combination.Combine.WithDoubleUnaryOperator;

interface DoubleFunctionCombos<A> {
    DoubleFunction<A> resolve();

    /* DoubleFunction<A> -> Function<A,B> */

    public default <B> DoubleFunction<B> fuseFunction(Function<A, B> next) {
        return (double b) -> next.apply(resolve().apply(b));
    }

    public default <B> DoubleFunction<B> fuse(Function<A, B> next) {
        return fuseFunction(next);
    }

    public default <B> WithDoubleFunction<B> fusingFunction(
        Function<A, B> next
    ) {
        return WithDoubleFunction.of(fuseFunction(next));
    }

    public default <B> WithDoubleFunction<B> fusing(Function<A, B> next) {
        return fusingFunction(next);
    }

    /* DoubleFunction<A> -> BiFunction<A,B,C> */

    public default <B, C> DoubleFunction<Function<B, C>> fuseBiFunction(
        BiFunction<A, B, C> next
    ) {
        return (double b1) -> (B b2) -> next.apply(resolve().apply(b1), b2);
    }

    public default <B, C> DoubleFunction<Function<B, C>> fuse(
        BiFunction<A, B, C> next
    ) {
        return fuseBiFunction(next);
    }

    // TODO: Implement with currying
    // fusingBiFunction(bifn)

    public default <B, C> DoubleFunction<C> fuseBiFunction(
        BiFunction<A, B, C> next,
        B secondArg
    ) {
        return (double b) -> next.apply(resolve().apply(b), secondArg);
    }

    public default <B, C> DoubleFunction<C> fuse(
        BiFunction<A, B, C> next,
        B secondArg
    ) {
        return fuseBiFunction(next, secondArg);
    }

    public default <B, C> WithDoubleFunction<C> fusingBiFunction(
        BiFunction<A, B, C> next,
        B secondArg
    ) {
        return WithDoubleFunction.of(fuseBiFunction(next, secondArg));
    }

    public default <B, C> WithDoubleFunction<C> fusing(
        BiFunction<A, B, C> next,
        B secondArg
    ) {
        return fusingBiFunction(next, secondArg);
    }

    /* DoubleFunction<A> -> ToDoubleFunction<A> */

    public default DoubleUnaryOperator fuseToDoubleFunction(
        ToDoubleFunction<A> next
    ) {
        return (double b) -> next.applyAsDouble(resolve().apply(b));
    }

    public default DoubleUnaryOperator fuse(ToDoubleFunction<A> next) {
        return fuseToDoubleFunction(next);
    }

    public default WithDoubleUnaryOperator fusingToDoubleFunction(
        ToDoubleFunction<A> next
    ) {
        return WithDoubleUnaryOperator.of(fuseToDoubleFunction(next));
    }

    public default WithDoubleUnaryOperator fusing(ToDoubleFunction<A> next) {
        return fusingToDoubleFunction(next);
    }

    /* DoubleFunction<A> -> ToDoubleBiFunction<A> */

    public default <B> DoubleFunction<ToDoubleFunction<B>> fuseToDoubleBiFunction(
        ToDoubleBiFunction<A, B> next
    ) {
        return (double b1) ->
            (B b2) -> next.applyAsDouble(resolve().apply(b1), b2);
    }

    public default <B> DoubleFunction<ToDoubleFunction<B>> fuse(
        ToDoubleBiFunction<A, B> next
    ) {
        return fuseToDoubleBiFunction(next);
    }

    // TODO: Implement with currying
    // fusingToDoubleBiFunction(ToDoubleBiFunction<A, B> next)

    public default <B> DoubleUnaryOperator fuseToDoubleBiFunction(
        ToDoubleBiFunction<A, B> next,
        B secondArg
    ) {
        return (double b1) ->
            next.applyAsDouble(resolve().apply(b1), secondArg);
    }

    public default <B> DoubleUnaryOperator fuse(
        ToDoubleBiFunction<A, B> next,
        B secondArg
    ) {
        return fuseToDoubleBiFunction(next, secondArg);
    }

    public default <B> WithDoubleUnaryOperator fusingToDoubleBiFunction(
        ToDoubleBiFunction<A, B> next,
        B secondArg
    ) {
        return WithDoubleUnaryOperator.of(
            fuseToDoubleBiFunction(next, secondArg)
        );
    }

    public default <B> WithDoubleUnaryOperator fusing(
        ToDoubleBiFunction<A, B> next,
        B secondArg
    ) {
        return fusingToDoubleBiFunction(next, secondArg);
    }

    /* DoubleFunction<A> -> ToIntFunction<A> */

    public default DoubleToIntFunction fuseToIntFunction(
        ToIntFunction<A> next
    ) {
        return (double b) -> next.applyAsInt(resolve().apply(b));
    }

    public default DoubleToIntFunction fuse(ToIntFunction<A> next) {
        return fuseToIntFunction(next);
    }

    public default WithDoubleToIntFunction fusingToIntFunction(
        ToIntFunction<A> next
    ) {
        return WithDoubleToIntFunction.of(fuseToIntFunction(next));
    }

    public default WithDoubleToIntFunction fusing(ToIntFunction<A> next) {
        return fusingToIntFunction(next);
    }

    /* DoubleFunction<A> -> ToIntBiFunction<A> */

    public default <B> DoubleFunction<ToIntFunction<B>> fuseToIntBiFunction(
        ToIntBiFunction<A, B> next
    ) {
        return (double b1) ->
            (B b2) -> next.applyAsInt(resolve().apply(b1), b2);
    }

    public default <B> DoubleFunction<ToIntFunction<B>> fuse(
        ToIntBiFunction<A, B> next
    ) {
        return fuseToIntBiFunction(next);
    }

    // TODO: Implement with currying
    // fusingToIntBiFunction(ToIntBiFunction<A, B> next)

    public default <B> DoubleToIntFunction fuseToIntBiFunction(
        ToIntBiFunction<A, B> next,
        B secondArg
    ) {
        return (double b1) -> next.applyAsInt(resolve().apply(b1), secondArg);
    }

    public default <B> DoubleToIntFunction fuse(
        ToIntBiFunction<A, B> next,
        B secondArg
    ) {
        return fuseToIntBiFunction(next, secondArg);
    }

    public default <B> WithDoubleToIntFunction fusingToIntBiFunction(
        ToIntBiFunction<A, B> next,
        B secondArg
    ) {
        return WithDoubleToIntFunction.of(fuseToIntBiFunction(next, secondArg));
    }

    public default <B> WithDoubleToIntFunction fusing(
        ToIntBiFunction<A, B> next,
        B secondArg
    ) {
        return fusingToIntBiFunction(next, secondArg);
    }

    /* DoubleFunction<A> -> ToLongFunction<A> */

    public default DoubleToLongFunction fuseToLongFunction(
        ToLongFunction<A> next
    ) {
        return (double b) -> next.applyAsLong(resolve().apply(b));
    }

    public default DoubleToLongFunction fuse(ToLongFunction<A> next) {
        return fuseToLongFunction(next);
    }

    public default WithDoubleToLongFunction fusingToLongFunction(
        ToLongFunction<A> next
    ) {
        return WithDoubleToLongFunction.of(fuseToLongFunction(next));
    }

    public default WithDoubleToLongFunction fusing(ToLongFunction<A> next) {
        return fusingToLongFunction(next);
    }

    /* DoubleFunction<A> -> ToLongBiFunction<A> */

    public default <B> DoubleFunction<ToLongFunction<B>> fuseToLongBiFunction(
        ToLongBiFunction<A, B> next
    ) {
        return (double b1) ->
            (B b2) -> next.applyAsLong(resolve().apply(b1), b2);
    }

    public default <B> DoubleFunction<ToLongFunction<B>> fuse(
        ToLongBiFunction<A, B> next
    ) {
        return fuseToLongBiFunction(next);
    }

    // TODO: Implement with currying
    // fusingToLongBiFunction(ToLongBiFunction<A, B> next)

    public default <B> DoubleToLongFunction fuseToLongBiFunction(
        ToLongBiFunction<A, B> next,
        B secondArg
    ) {
        return (double b1) -> next.applyAsLong(resolve().apply(b1), secondArg);
    }

    public default <B> DoubleToLongFunction fuse(
        ToLongBiFunction<A, B> next,
        B secondArg
    ) {
        return fuseToLongBiFunction(next, secondArg);
    }

    public default <B> WithDoubleToLongFunction fusingToLongBiFunction(
        ToLongBiFunction<A, B> next,
        B secondArg
    ) {
        return WithDoubleToLongFunction.of(
            fuseToLongBiFunction(next, secondArg)
        );
    }

    public default <B> WithDoubleToLongFunction fusing(
        ToLongBiFunction<A, B> next,
        B secondArg
    ) {
        return fusingToLongBiFunction(next, secondArg);
    }

    /* DoubleFunction<A> -> Predicate<A> */

    public default DoublePredicate fusePredicate(Predicate<A> next) {
        return (double b) -> next.test(resolve().apply(b));
    }

    public default DoublePredicate fuse(Predicate<A> next) {
        return fusePredicate(next);
    }

    public default WithDoublePredicate fusingPredicate(Predicate<A> next) {
        return WithDoublePredicate.of(fusePredicate(next));
    }

    public default WithDoublePredicate fusing(Predicate<A> next) {
        return fusingPredicate(next);
    }

    /* DoubleFunction<A> -> BiPredicate<A> */

    public default <B> DoubleFunction<Predicate<B>> fuseBiPredicate(
        BiPredicate<A, B> next
    ) {
        return (double b1) -> (B b2) -> next.test(resolve().apply(b1), b2);
    }

    public default <B> DoubleFunction<Predicate<B>> fuse(
        BiPredicate<A, B> next
    ) {
        return fuseBiPredicate(next);
    }

    // TODO: Implement with currying
    // fusingBiPredicate(BiPredicate<A, B> next)

    public default <B> DoublePredicate fuseBiPredicate(
        BiPredicate<A, B> next,
        B secondArg
    ) {
        return (double b1) -> next.test(resolve().apply(b1), secondArg);
    }

    public default <B> DoublePredicate fuse(
        BiPredicate<A, B> next,
        B secondArg
    ) {
        return fuseBiPredicate(next, secondArg);
    }

    public default <B> WithDoublePredicate fusingBiPredicate(
        BiPredicate<A, B> next,
        B secondArg
    ) {
        return WithDoublePredicate.of(fuseBiPredicate(next, secondArg));
    }

    public default <B> WithDoublePredicate fusing(
        BiPredicate<A, B> next,
        B secondArg
    ) {
        return fusingBiPredicate(next, secondArg);
    }

    /* DoubleFunction<A> -> Consumer<A> */

    public default DoubleConsumer fuseConsumer(Consumer<A> next) {
        return (double b) -> next.accept(resolve().apply(b));
    }

    public default DoubleConsumer fuse(Consumer<A> next) {
        return fuseConsumer(next);
    }

    public default WithDoubleConsumer fusingConsumer(Consumer<A> next) {
        return WithDoubleConsumer.of(fuseConsumer(next));
    }

    public default WithDoubleConsumer fusing(Consumer<A> next) {
        return fusingConsumer(next);
    }

    /* DoubleFunction<A> -> BiConsumer<A> */

    public default <B> DoubleFunction<Consumer<B>> fuseBiConsumer(
        BiConsumer<A, B> next
    ) {
        return (double b1) -> (B b2) -> next.accept(resolve().apply(b1), b2);
    }

    public default <B> DoubleFunction<Consumer<B>> fuse(BiConsumer<A, B> next) {
        return fuseBiConsumer(next);
    }

    // TODO: Implement with currying
    // fusingBiConsumer(BiConsumer<A, B> next)

    public default <B> DoubleConsumer fuseBiConsumer(
        BiConsumer<A, B> next,
        B secondArg
    ) {
        return (double b1) -> next.accept(resolve().apply(b1), secondArg);
    }

    public default <B> DoubleConsumer fuse(BiConsumer<A, B> next, B secondArg) {
        return fuseBiConsumer(next, secondArg);
    }

    public default <B> WithDoubleConsumer fusingBiConsumer(
        BiConsumer<A, B> next,
        B secondArg
    ) {
        return WithDoubleConsumer.of(fuseBiConsumer(next, secondArg));
    }

    public default <B> WithDoubleConsumer fusing(
        BiConsumer<A, B> next,
        B secondArg
    ) {
        return fusingBiConsumer(next, secondArg);
    }

    /* DoubleFunction<A> -> ObjDoubleConsumer<A> */

    public default DoubleFunction<DoubleConsumer> fuseObjDoubleConsumer(
        ObjDoubleConsumer<A> next
    ) {
        return (double b) -> (double d) -> next.accept(resolve().apply(b), d);
    }

    public default DoubleFunction<DoubleConsumer> fuse(
        ObjDoubleConsumer<A> next
    ) {
        return fuseObjDoubleConsumer(next);
    }

    // TODO: Implement with currying
    // public default DoubleFunction<DoubleConsumer> fusingObjDoubleConsumer(ObjDoubleConsumer<A> next) {...}

    public default DoubleConsumer fuseObjDoubleConsumer(
        ObjDoubleConsumer<A> next,
        double secondArg
    ) {
        return (double b) -> next.accept(resolve().apply(b), secondArg);
    }

    public default DoubleConsumer fuse(
        ObjDoubleConsumer<A> next,
        double secondArg
    ) {
        return fuseObjDoubleConsumer(next, secondArg);
    }

    public default WithDoubleConsumer fusingObjDoubleConsumer(
        ObjDoubleConsumer<A> next,
        double secondArg
    ) {
        return WithDoubleConsumer.of(fuseObjDoubleConsumer(next, secondArg));
    }

    public default WithDoubleConsumer fusing(
        ObjDoubleConsumer<A> next,
        double secondArg
    ) {
        return fusingObjDoubleConsumer(next, secondArg);
    }

    /* DoubleFunction<A> -> ObjIntConsumer<A> */

    public default DoubleFunction<IntConsumer> fuseObjIntConsumer(
        ObjIntConsumer<A> next
    ) {
        return (double b) -> (int d) -> next.accept(resolve().apply(b), d);
    }

    public default DoubleFunction<IntConsumer> fuse(ObjIntConsumer<A> next) {
        return fuseObjIntConsumer(next);
    }

    // TODO: Implement with currying
    // public default DoubleFunction<IntConsumer> fusingObjIntConsumer(ObjIntConsumer<A> next) {...}

    public default DoubleConsumer fuseObjIntConsumer(
        ObjIntConsumer<A> next,
        int secondArg
    ) {
        return (double b) -> next.accept(resolve().apply(b), secondArg);
    }

    public default DoubleConsumer fuse(ObjIntConsumer<A> next, int secondArg) {
        return fuseObjIntConsumer(next, secondArg);
    }

    public default WithDoubleConsumer fusingObjIntConsumer(
        ObjIntConsumer<A> next,
        int secondArg
    ) {
        return WithDoubleConsumer.of(fuseObjIntConsumer(next, secondArg));
    }

    public default WithDoubleConsumer fusing(
        ObjIntConsumer<A> next,
        int secondArg
    ) {
        return fusingObjIntConsumer(next, secondArg);
    }

    /* DoubleFunction<A> -> ObjLongConsumer<A> */

    public default DoubleFunction<LongConsumer> fuseObjLongConsumer(
        ObjLongConsumer<A> next
    ) {
        return (double b) -> (long d) -> next.accept(resolve().apply(b), d);
    }

    public default DoubleFunction<LongConsumer> fuse(ObjLongConsumer<A> next) {
        return fuseObjLongConsumer(next);
    }

    // TODO: Implement with currying
    // public default DoubleFunction<LongConsumer> fusingObjLongConsumer(ObjLongConsumer<A> next) {...}

    public default DoubleConsumer fuseObjLongConsumer(
        ObjLongConsumer<A> next,
        long secondArg
    ) {
        return (double b) -> next.accept(resolve().apply(b), secondArg);
    }

    public default DoubleConsumer fuse(
        ObjLongConsumer<A> next,
        long secondArg
    ) {
        return fuseObjLongConsumer(next, secondArg);
    }

    public default WithDoubleConsumer fusingObjLongConsumer(
        ObjLongConsumer<A> next,
        long secondArg
    ) {
        return WithDoubleConsumer.of(fuseObjLongConsumer(next, secondArg));
    }

    public default WithDoubleConsumer fusing(
        ObjLongConsumer<A> next,
        long secondArg
    ) {
        return fusingObjLongConsumer(next, secondArg);
    }
}
