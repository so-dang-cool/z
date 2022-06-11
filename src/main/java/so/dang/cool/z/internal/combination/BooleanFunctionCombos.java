package so.dang.cool.z.internal.combination;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
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
import so.dang.cool.z.function.BooleanFunction;
import so.dang.cool.z.internal.combination.Combine.WithBiConsumer;
import so.dang.cool.z.internal.combination.Combine.WithBiFunction;
import so.dang.cool.z.internal.combination.Combine.WithBiPredicate;
import so.dang.cool.z.internal.combination.Combine.WithBooleanConsumer;
import so.dang.cool.z.internal.combination.Combine.WithBooleanFunction;
import so.dang.cool.z.internal.combination.Combine.WithBooleanPredicate;
import so.dang.cool.z.internal.combination.Combine.WithBooleanToDoubleFunction;
import so.dang.cool.z.internal.combination.Combine.WithBooleanToIntFunction;
import so.dang.cool.z.internal.combination.Combine.WithBooleanToLongFunction;
import so.dang.cool.z.internal.combination.Combine.WithToDoubleBiFunction;
import so.dang.cool.z.internal.combination.Combine.WithToIntBiFunction;
import so.dang.cool.z.internal.combination.Combine.WithToLongBiFunction;

interface BooleanFunctionCombos<A> {
    BooleanFunction<A> resolve();

    /* BooleanFunction<A> -> Function<A,B> */

    public default <B> WithBooleanFunction<B> fuseFunction(
        Function<A, B> next
    ) {
        return WithBooleanFunction.of(
            (boolean b) -> next.apply(resolve().apply(b))
        );
    }

    public default <B> WithBooleanFunction<B> fuse(Function<A, B> next) {
        return fuseFunction(next);
    }

    /* BooleanFunction<A> -> BiFunction<A,B,C> */

    @PromotesPrimitive(promoted = Boolean.class)
    public default <B, C> WithBiFunction<Boolean, B, C> fuseBiFunction(
        BiFunction<A, B, C> next
    ) {
        return WithBiFunction.of(
            (Boolean b1) -> (B b2) -> next.apply(resolve().apply(b1), b2)
        );
    }

    public default <B, C> WithBiFunction<Boolean, B, C> fuse(
        BiFunction<A, B, C> next
    ) {
        return fuseBiFunction(next);
    }

    /* BooleanFunction<A> -> ToDoubleFunction<A> */

    public default WithBooleanToDoubleFunction fuseToDoubleFunction(
        ToDoubleFunction<A> next
    ) {
        return WithBooleanToDoubleFunction.of(
            (boolean b) -> next.applyAsDouble(resolve().apply(b))
        );
    }

    public default WithBooleanToDoubleFunction fuse(ToDoubleFunction<A> next) {
        return fuseToDoubleFunction(next);
    }

    /* BooleanFunction<A> -> ToDoubleBiFunction<A> */

    @PromotesPrimitive(promoted = Boolean.class)
    public default <B> WithToDoubleBiFunction<Boolean, B> fuseToDoubleBiFunction(
        ToDoubleBiFunction<A, B> next
    ) {
        return WithToDoubleBiFunction.of(
            (Boolean b1) ->
                (B b2) -> next.applyAsDouble(resolve().apply(b1), b2)
        );
    }

    public default <B> WithToDoubleBiFunction<Boolean, B> fuse(
        ToDoubleBiFunction<A, B> next
    ) {
        return fuseToDoubleBiFunction(next);
    }

    /* BooleanFunction<A> -> ToIntFunction<A> */

    public default WithBooleanToIntFunction fuseToIntFunction(
        ToIntFunction<A> next
    ) {
        return WithBooleanToIntFunction.of(
            (boolean b) -> next.applyAsInt(resolve().apply(b))
        );
    }

    public default WithBooleanToIntFunction fuse(ToIntFunction<A> next) {
        return fuseToIntFunction(next);
    }

    /* BooleanFunction<A> -> ToIntBiFunction<A> */

    @PromotesPrimitive(promoted = Boolean.class)
    public default <B> WithToIntBiFunction<Boolean, B> fuseToIntBiFunction(
        ToIntBiFunction<A, B> next
    ) {
        return WithToIntBiFunction.of(
            (Boolean b1) -> (B b2) -> next.applyAsInt(resolve().apply(b1), b2)
        );
    }

    public default <B> WithToIntBiFunction<Boolean, B> fuse(
        ToIntBiFunction<A, B> next
    ) {
        return fuseToIntBiFunction(next);
    }

    /* BooleanFunction<A> -> ToLongFunction<A> */

    public default WithBooleanToLongFunction fuseToLongFunction(
        ToLongFunction<A> next
    ) {
        return WithBooleanToLongFunction.of(
            (boolean b) -> next.applyAsLong(resolve().apply(b))
        );
    }

    public default WithBooleanToLongFunction fuse(ToLongFunction<A> next) {
        return fuseToLongFunction(next);
    }

    /* BooleanFunction<A> -> ToLongBiFunction<A> */

    @PromotesPrimitive(promoted = Boolean.class)
    public default <B> WithToLongBiFunction<Boolean, B> fuseToLongBiFunction(
        ToLongBiFunction<A, B> next
    ) {
        return WithToLongBiFunction.of(
            (Boolean b1) -> (B b2) -> next.applyAsLong(resolve().apply(b1), b2)
        );
    }

    public default <B> WithToLongBiFunction<Boolean, B> fuse(
        ToLongBiFunction<A, B> next
    ) {
        return fuseToLongBiFunction(next);
    }

    /* BooleanFunction<A> -> Predicate<A> */

    public default WithBooleanPredicate fusePredicate(Predicate<A> next) {
        return WithBooleanPredicate.of(
            (boolean b) -> next.test(resolve().apply(b))
        );
    }

    public default WithBooleanPredicate fuse(Predicate<A> next) {
        return fusePredicate(next);
    }

    /* BooleanFunction<A> -> BiPredicate<A> */

    @PromotesPrimitive(promoted = Boolean.class)
    public default <B> WithBiPredicate<Boolean, B> fuseBiPredicate(
        BiPredicate<A, B> next
    ) {
        return WithBiPredicate.of(
            (Boolean b1) -> (B b2) -> next.test(resolve().apply(b1), b2)
        );
    }

    public default <B> WithBiPredicate<Boolean, B> fuse(
        BiPredicate<A, B> next
    ) {
        return fuseBiPredicate(next);
    }

    /* BooleanFunction<A> -> Consumer<A> */

    public default WithBooleanConsumer fuseConsumer(Consumer<A> next) {
        return WithBooleanConsumer.of(
            (boolean b) -> next.accept(resolve().apply(b))
        );
    }

    public default WithBooleanConsumer fuse(Consumer<A> next) {
        return fuseConsumer(next);
    }

    /* BooleanFunction<A> -> BiConsumer<A> */

    @PromotesPrimitive(promoted = Boolean.class)
    public default <B> WithBiConsumer<Boolean, B> fuseBiConsumer(
        BiConsumer<A, B> next
    ) {
        return WithBiConsumer.of(
            (Boolean b1) -> (B b2) -> next.accept(resolve().apply(b1), b2)
        );
    }

    public default <B> WithBiConsumer<Boolean, B> fuse(BiConsumer<A, B> next) {
        return fuseBiConsumer(next);
    }

    /* BooleanFunction<A> -> ObjDoubleConsumer<A> */

    @PromotesPrimitive(promoted = { Boolean.class, Double.class })
    public default WithBiConsumer<Boolean, Double> fuseObjDoubleConsumer(
        ObjDoubleConsumer<A> next
    ) {
        return WithBiConsumer.of(
            (Boolean b) -> (Double d) -> next.accept(resolve().apply(b), d)
        );
    }

    public default WithBiConsumer<Boolean, Double> fuse(
        ObjDoubleConsumer<A> next
    ) {
        return fuseObjDoubleConsumer(next);
    }

    /* BooleanFunction<A> -> ObjIntConsumer<A> */

    @PromotesPrimitive(promoted = { Boolean.class, Integer.class })
    public default WithBiConsumer<Boolean, Integer> fuseObjIntConsumer(
        ObjIntConsumer<A> next
    ) {
        return WithBiConsumer.of(
            (Boolean b) -> (Integer d) -> next.accept(resolve().apply(b), d)
        );
    }

    public default WithBiConsumer<Boolean, Integer> fuse(
        ObjIntConsumer<A> next
    ) {
        return fuseObjIntConsumer(next);
    }

    /* BooleanFunction<A> -> ObjLongConsumer<A> */

    @PromotesPrimitive(promoted = { Boolean.class, Long.class })
    public default WithBiConsumer<Boolean, Long> fuseObjLongConsumer(
        ObjLongConsumer<A> next
    ) {
        return WithBiConsumer.of(
            (Boolean b) -> (Long d) -> next.accept(resolve().apply(b), d)
        );
    }

    public default WithBiConsumer<Boolean, Long> fuse(ObjLongConsumer<A> next) {
        return fuseObjLongConsumer(next);
    }
}
