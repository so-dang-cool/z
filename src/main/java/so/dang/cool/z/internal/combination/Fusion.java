package so.dang.cool.z.internal.combination;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.DoubleFunction;
import java.util.function.DoubleToIntFunction;
import java.util.function.DoubleToLongFunction;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntToLongFunction;
import java.util.function.LongFunction;
import java.util.function.LongToDoubleFunction;
import java.util.function.LongToIntFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToDoubleBiFunction;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntBiFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongBiFunction;
import java.util.function.ToLongFunction;
import java.util.function.UnaryOperator;

import so.dang.cool.z.Z;
import so.dang.cool.z.annotation.Experimental;
import so.dang.cool.z.function.BooleanFunction;
import so.dang.cool.z.function.BooleanToDoubleFunction;
import so.dang.cool.z.function.BooleanToIntFunction;
import so.dang.cool.z.function.BooleanToLongFunction;

/**
 * Deep fusions involving many functions.
 */
@Experimental
public abstract class Fusion<A, Fn> {
    private Fusion() {}

    /**
     * Retrieve the resulting fusion.
     */
    public abstract Fn resolve();

    /* Function -> ... [TODO: Incomplete] */

    public static final class WithFunction<A, B> extends Fusion<B, Function<A, B>> {
        private final Function<A, B> initial;

        private WithFunction(Function<A, B> initial) {
            this.initial = initial;
        }

        public static <A, B> WithFunction<A, B> of(Function<A, B> initial) {
            return new WithFunction<>(initial);
        }

        @Override
        public Function<A, B> resolve() {
            return initial;
        }

        /* Function<A, B> -> Function<B, C> */

        public <C> Function<A, C> fuseFunction(Function<B, C> next) {
            return Z.fuse(initial, next);
        }

        public <C> Function<A, C> fuse(Function<B, C> next) {
            return fuseFunction(next);
        }

        public <C> WithFunction<A, C> fusingFunction(Function<B, C> next) {
            return WithFunction.of(Z.fuse(initial, next));
        }

        public <C> WithFunction<A, C> fusing(Function<B, C> next) {
            return fusingFunction(next);
        }

        /* Function<A, B> -> BiFunction<B, C, D> */

        public <C, D> Function<A, Function<C, D>> fuseBiFunction(BiFunction<B, C, D> next) {
            return Z.fuse(initial, next);
        }

        public <C, D> Function<A, Function<C, D>> fuse(BiFunction<B, C, D> next) {
            return fuseBiFunction(next);
        }

        public <C, D> WithBiFunction<A, C, D> fusingBiFunction(BiFunction<B, C, D> next) {
            // TODO: Need a currying combinator?
            return WithBiFunction.of(Z.assimilate2(Z.fuse(initial, next)));
        }

        public <C, D> WithBiFunction<A, C, D> fusing(BiFunction<B, C, D> next) {
            return fusingBiFunction(next);
        }

        /* Function<A, B> -> DoubleFunction<B> */

        public ToDoubleFunction<A> fuseToDoubleFunction(ToDoubleFunction<B> next) {
            return Z.fuse(initial, next);
        }

        public ToDoubleFunction<A> fuse(ToDoubleFunction<B> next) {
            return fuseToDoubleFunction(next);
        }

        public WithToDoubleFunction<A> fusingToDoubleFunction(ToDoubleFunction<B> next) {
            return WithToDoubleFunction.of(Z.fuse(initial, next));
        }

        public WithToDoubleFunction<A> fusing(ToDoubleFunction<B> next) {
            return fusingToDoubleFunction(next);
        }

        /* Function<A, B> -> ToDoubleBiFunction<B> [TODO] */

        /* Function<A, B> -> ToIntFunction<B> */

        public ToIntFunction<A> fuseToIntFunction(ToIntFunction<B> next) {
            return Z.fuse(initial, next);
        }

        public ToIntFunction<A> fuse(ToIntFunction<B> next) {
            return fuseToIntFunction(next);
        }

        public WithToIntFunction<A> fusingToIntFunction(ToIntFunction<B> next) {
            return WithToIntFunction.of(Z.fuse(initial, next));
        }

        public WithToIntFunction<A> fusing(ToIntFunction<B> next) {
            return fusingToIntFunction(next);
        }

        /* Function<A, B> -> ToIntBiFunction<B> [TODO] */

        /* Function<A, B> -> ToLongFunction<B> */

        public ToLongFunction<A> fuseToLongFunction(ToLongFunction<B> next) {
            return Z.fuse(initial, next);
        }

        public ToLongFunction<A> fuse(ToLongFunction<B> next) {
            return fuseToLongFunction(next);
        }

        public WithToLongFunction<A> fusingToLongFunction(ToLongFunction<B> next) {
            return WithToLongFunction.of(Z.fuse(initial, next));
        }

        public WithToLongFunction<A> fusing(ToLongFunction<B> next) {
            return fusingToLongFunction(next);
        }

        /* Function<A, B> -> ToLongBiFunction<B> [TODO] */

        /* Function<A, B> -> Predicate<B> */

        public Predicate<A> fusePredicate(Predicate<B> next) {
            return Z.fuse(resolve(), next);
        }

        public Predicate<A> fuse(Predicate<B> next) {
            return fusePredicate(next);
        }

        public WithPredicate<A> fusingPredicate(Predicate<B> next) {
            return WithPredicate.of(Z.fuse(initial, next));
        }

        public WithPredicate<A> fusing(Predicate<B> next) {
            return fusingPredicate(next);
        }

        /* Function<A, B> -> BiPredicate<B, C> [TODO] */

        /* Function<A, B> -> Consumer<B> */

        public Consumer<A> fuseConsumer(Consumer<B> next) {
            return Z.fuse(initial, next);
        }

        public Consumer<A> fuse(Consumer<B> next) {
            return fuseConsumer(next);
        }

        public WithConsumer<A> fusingConsumer(Consumer<B> next) {
            return WithConsumer.of(Z.fuse(initial, next));
        }

        public WithConsumer<A> fusing(Consumer<B> next) {
            return fusingConsumer(next);
        }

        /* Function<A, B> -> BiConsumer<B, C> [TODO] */

        /* Function<A, B> -> ObjDoubleConsumer<B, C> [TODO] */

        /* Function<A, B> -> ObjIntConsumer<B, C> [TODO] */

        /* Function<A, B> -> ObjLongConsumer<B, C> [TODO] */

        /* Function<A, B> -> UnaryOperator<B> */

        public Function<A, B> fuseUnaryOperator(UnaryOperator<B> next) {
            return Z.fuse(initial, next);
        }

        public Function<A, B> fuse(UnaryOperator<B> next) {
            return fuseUnaryOperator(next);
        }

        public WithFunction<A, B> fusingUnaryOperator(UnaryOperator<B> next) {
            return WithFunction.of(Z.fuse(initial, next));
        }

        public WithFunction<A, B> fusing(UnaryOperator<B> next) {
            return fusingUnaryOperator(next);
        }

        /* Function<A, B> -> BinaryOperator<B> */

        public Function<A, UnaryOperator<B>> fuseBinaryOperator(BinaryOperator<B> next) {
            return Z.fuse(initial, next);
        }

        public Function<A, UnaryOperator<B>> fuse(BinaryOperator<B> next) {
            return fuseBinaryOperator(next);
        }

        public WithBiFunction<A, B, B> fusingBinaryOperator(BinaryOperator<B> next) {
            // TODO: simplify with currying
            return WithBiFunction.of((A a, B b) -> next.apply(initial.apply(a), b));
        }

        public WithBiFunction<A, B, B> fusing(BinaryOperator<B> next) {
            return fusingBinaryOperator(next);
        }

        /* Function<A, B> -> [Multi]Function<B...> [TODO...?] */
    }

    /*
        BiFunction<A, B, C> -> BiFunction<C, D, E>
        = Function<A, Function<B, Function<D, E>>>

        Function<A, B> -> BiFunction<B, C, D>
        = Function<A, Function<C, D>>
    */

    public static final class WithBiFunction<A, B, C> extends Fusion<C, Function<A, Function<B, C>>> {
        private final BiFunction<A, B, C> initial;

        private WithBiFunction(BiFunction<A, B, C> initial) {
            this.initial = initial;
        }

        public static <A, B, C> WithBiFunction<A, B, C> of(BiFunction<A, B, C> initial) {
            return new WithBiFunction<>(initial);
        }

        public static <A, B, C> WithBiFunction<A, B, C> of(Function<A, Function<B, C>> initial) {
            return new WithBiFunction<>((A a, B b) -> initial.apply(a).apply(b));
        }

        @Override
        public Function<A, Function<B, C>> resolve() {
            return Z.split(initial);
        }

        /* BiFunction<A, B, C> -> Function<C, D> */

        public <D> Function<A, Function<B, D>> fuseFunction(Function<C, D> next) {
            return Z.fuse(initial, next);
        }

        public <D> Function<A, Function<B, D>> fuse(Function<C, D> next) {
            return fuseFunction(next);
        }

        public <D> WithBiFunction<A, B, D> fusingFunction(Function<C, D> next) {
            return WithBiFunction.of(fuseFunction(next));
        }

        public <D> WithBiFunction<A, B, D> fusing(Function<C, D> next) {
            return fusingFunction(next);
        }
    }

    public static final class WithBooleanFunction<A> extends Fusion<A, BooleanFunction<A>> {
        private final BooleanFunction<A> initial;

        private WithBooleanFunction(BooleanFunction<A> initial) {
            this.initial = initial;
        }

        public static <A> WithBooleanFunction<A> of(BooleanFunction<A> initial) {
            return new WithBooleanFunction<>(initial);
        }

        @Override
        public BooleanFunction<A> resolve() {
            return initial;
        }

        /* BooleanFunction<A> -> Function<A,B> */

        public <B> BooleanFunction<B> fuseFunction(Function<A, B> next) {
            return Z.fuse(initial, next);
        }

        public <B> BooleanFunction<B> fuse(Function<A, B> next) {
            return fuseFunction(next);
        }

        public <B> WithBooleanFunction<B> fusingFunction(Function<A, B> next) {
            return WithBooleanFunction.of(fuseFunction(next));
        }

        public <B> WithBooleanFunction<B> fusing(Function<A, B> next) {
            return fusingFunction(next);
        }
    }

    public static final class WithBooleanToDoubleFunction extends Fusion<Double, BooleanToDoubleFunction> {
        private final BooleanToDoubleFunction initial;

        private WithBooleanToDoubleFunction(BooleanToDoubleFunction initial) {
            this.initial = initial;
        }

        public static WithBooleanToDoubleFunction of(BooleanToDoubleFunction initial) {
            return new WithBooleanToDoubleFunction(initial);
        }

        @Override
        public BooleanToDoubleFunction resolve() {
            return initial;
        }

        /* BooleanToDoubleFunction<A> -> DoubleFunction<B> */

        public <A> BooleanFunction<A> fuseDoubleFunction(DoubleFunction<A> next) {
            return Z.fuse(initial, next);
        }

        public <A> BooleanFunction<A> fuse(DoubleFunction<A> next) {
            return fuseDoubleFunction(next);
        }

        public <A> WithBooleanFunction<A> fusingDoubleFunction(DoubleFunction<A> next) {
            return WithBooleanFunction.of(fuseDoubleFunction(next));
        }

        public <A> WithBooleanFunction<A> fusing(DoubleFunction<A> next) {
            return fusingDoubleFunction(next);
        }
    }

    public static final class WithBooleanToIntFunction extends Fusion<Integer, BooleanToIntFunction> {
        private final BooleanToIntFunction initial;

        private WithBooleanToIntFunction(BooleanToIntFunction initial) {
            this.initial = initial;
        }

        public static WithBooleanToIntFunction of(BooleanToIntFunction initial) {
            return new WithBooleanToIntFunction(initial);
        }

        @Override
        public BooleanToIntFunction resolve() {
            return initial;
        }

        /* BooleanToIntFunction<A> -> IntFunction<B> */

        public <A> BooleanFunction<A> fuseIntFunction(IntFunction<A> next) {
            return Z.fuse(initial, next);
        }

        public <A> BooleanFunction<A> fuse(IntFunction<A> next) {
            return fuseIntFunction(next);
        }

        public <A> WithBooleanFunction<A> fusingIntFunction(IntFunction<A> next) {
            return WithBooleanFunction.of(fuseIntFunction(next));
        }

        public <A> WithBooleanFunction<A> fusing(IntFunction<A> next) {
            return fusingIntFunction(next);
        }
    }

    public static final class WithBooleanToLongFunction extends Fusion<Long, BooleanToLongFunction> {
        private final BooleanToLongFunction initial;

        private WithBooleanToLongFunction(BooleanToLongFunction initial) {
            this.initial = initial;
        }

        public static WithBooleanToLongFunction of(BooleanToLongFunction initial) {
            return new WithBooleanToLongFunction(initial);
        }

        @Override
        public BooleanToLongFunction resolve() {
            return initial;
        }

        /* BooleanToLongFunction<A> -> LongFunction<B> */

        public <A> BooleanFunction<A> fuseLongFunction(LongFunction<A> next) {
            return Z.fuse(initial, next);
        }

        public <A> BooleanFunction<A> fuse(LongFunction<A> next) {
            return fuseLongFunction(next);
        }

        public <A> WithBooleanFunction<A> fusingLongFunction(LongFunction<A> next) {
            return WithBooleanFunction.of(fuseLongFunction(next));
        }

        public <A> WithBooleanFunction<A> fusing(LongFunction<A> next) {
            return fusingLongFunction(next);
        }
    }

    public static final class WithDoubleFunction<A> extends Fusion<A, DoubleFunction<A>> {
        private final DoubleFunction<A> initial;

        private WithDoubleFunction(DoubleFunction<A> initial) {
            this.initial = initial;
        }

        public static <A> WithDoubleFunction<A> of(DoubleFunction<A> initial) {
            return new WithDoubleFunction<>(initial);
        }

        @Override
        public DoubleFunction<A> resolve() {
            return initial;
        }

        /* DoubleFunction<A> -> Function<A,B> */

        public <B> DoubleFunction<B> fuseFunction(Function<A, B> next) {
            return Z.fuse(initial, next);
        }

        public <B> DoubleFunction<B> fuse(Function<A, B> next) {
            return fuseFunction(next);
        }

        public <B> WithDoubleFunction<B> fusingFunction(Function<A, B> next) {
            return WithDoubleFunction.of(fuseFunction(next));
        }

        public <B> WithDoubleFunction<B> fusing(Function<A, B> next) {
            return fusingFunction(next);
        }
    }

    public static final class WithDoubleToIntFunction extends Fusion<Integer, DoubleToIntFunction> {
        private final DoubleToIntFunction initial;

        private WithDoubleToIntFunction(DoubleToIntFunction initial) {
            this.initial = initial;
        }

        public static WithDoubleToIntFunction of(DoubleToIntFunction initial) {
            return new WithDoubleToIntFunction(initial);
        }

        @Override
        public DoubleToIntFunction resolve() {
            return initial;
        }

        /* DoubleToIntFunction -> IntFunction<A> */

        public <A> DoubleFunction<A> fuseFunction(IntFunction<A> next) {
            return Z.fuse(initial, next);
        }

        public <A> DoubleFunction<A> fuse(IntFunction<A> next) {
            return fuseFunction(next);
        }

        public <A> WithDoubleFunction<A> fusingFunction(IntFunction<A> next) {
            return WithDoubleFunction.of(fuseFunction(next));
        }

        public <A> WithDoubleFunction<A> fusing(IntFunction<A> next) {
            return fusingFunction(next);
        }
    }

    public static final class WithDoubleToLongFunction extends Fusion<Long, DoubleToLongFunction> {
        private final DoubleToLongFunction initial;

        private WithDoubleToLongFunction(DoubleToLongFunction initial) {
            this.initial = initial;
        }

        public static WithDoubleToLongFunction of(DoubleToLongFunction initial) {
            return new WithDoubleToLongFunction(initial);
        }

        @Override
        public DoubleToLongFunction resolve() {
            return initial;
        }

        /* DoubleToLongFunction -> LongFunction<A> */

        public <A> DoubleFunction<A> fuseFunction(LongFunction<A> next) {
            return Z.fuse(initial, next);
        }

        public <A> DoubleFunction<A> fuse(LongFunction<A> next) {
            return fuseFunction(next);
        }

        public <A> WithDoubleFunction<A> fusingFunction(LongFunction<A> next) {
            return WithDoubleFunction.of(fuseFunction(next));
        }

        public <A> WithDoubleFunction<A> fusing(LongFunction<A> next) {
            return fusingFunction(next);
        }
    }

    public static final class WithToDoubleFunction<A> extends Fusion<Double, ToDoubleFunction<A>> {
        private final ToDoubleFunction<A> initial;

        private WithToDoubleFunction(ToDoubleFunction<A> initial) {
            this.initial = initial;
        }

        public static <A> WithToDoubleFunction<A> of(ToDoubleFunction<A> initial) {
            return new WithToDoubleFunction<>(initial);
        }

        @Override
        public ToDoubleFunction<A> resolve() {
            return initial;
        }

        /* ToDoubleFunction<A> -> DoubleFunction<B> */

        public <B> Function<A, B> fuseDoubleFunction(DoubleFunction<B> next) {
            return Z.fuse(initial, next);
        }

        public <B> Function<A, B> fuse(DoubleFunction<B> next) {
            return fuseDoubleFunction(next);
        }

        public <B> WithFunction<A, B> fusingDoubleFunction(DoubleFunction<B> next) {
            return WithFunction.of(fuseDoubleFunction(next));
        }

        public <B> WithFunction<A, B> fusing(DoubleFunction<B> next) {
            return fusingDoubleFunction(next);
        }
    }

    public static final class WithToDoubleBiFunction<A, B> extends Fusion<Double, ToDoubleBiFunction<A, B>> {
        private final ToDoubleBiFunction<A, B> initial;

        private WithToDoubleBiFunction(ToDoubleBiFunction<A, B> initial) {
            this.initial = initial;
        }

        public static <A, B> WithToDoubleBiFunction<A, B> of(ToDoubleBiFunction<A, B> initial) {
            return new WithToDoubleBiFunction<>(initial);
        }

        @Override
        public ToDoubleBiFunction<A, B> resolve() {
            return initial;
        }

        /* ToDoubleBiFunction<A, B> -> DoubleFunction<C> */

        public <C> Function<A, Function<B, C>> fuseDoubleFunction(DoubleFunction<C> next) {
            return Z.fuse(initial, next);
        }

        public <C> Function<A, Function<B, C>> fuse(DoubleFunction<C> next) {
            return fuseDoubleFunction(next);
        }

        public <C> WithBiFunction<A, B, C> fusingDoubleFunction(DoubleFunction<C> next) {
            return WithBiFunction.of(fuseDoubleFunction(next));
        }

        public <C> WithBiFunction<A, B, C> fusing(DoubleFunction<C> next) {
            return fusingDoubleFunction(next);
        }
    }

    public static final class WithIntFunction<A> extends Fusion<A, IntFunction<A>> {
        private final IntFunction<A> initial;

        private WithIntFunction(IntFunction<A> initial) {
            this.initial = initial;
        }

        public static <A> WithIntFunction<A> of(IntFunction<A> initial) {
            return new WithIntFunction<>(initial);
        }

        @Override
        public IntFunction<A> resolve() {
            return initial;
        }

        /* IntFunction<A> -> Function<A,B> */

        public <B> IntFunction<B> fuseFunction(Function<A, B> next) {
            return Z.fuse(initial, next);
        }

        public <B> IntFunction<B> fuse(Function<A, B> next) {
            return fuseFunction(next);
        }

        public <B> WithIntFunction<B> fusingFunction(Function<A, B> next) {
            return WithIntFunction.of(fuseFunction(next));
        }

        public <B> WithIntFunction<B> fusing(Function<A, B> next) {
            return fusingFunction(next);
        }
    }

    public static final class WithIntToDoubleFunction extends Fusion<Integer, IntToDoubleFunction> {
        private final IntToDoubleFunction initial;

        private WithIntToDoubleFunction(IntToDoubleFunction initial) {
            this.initial = initial;
        }

        public static WithIntToDoubleFunction of(IntToDoubleFunction initial) {
            return new WithIntToDoubleFunction(initial);
        }

        @Override
        public IntToDoubleFunction resolve() {
            return initial;
        }

        /* IntToDoubleFunction -> DoubleFunction<A> */

        public <A> IntFunction<A> fuseFunction(DoubleFunction<A> next) {
            return Z.fuse(initial, next);
        }

        public <A> IntFunction<A> fuse(DoubleFunction<A> next) {
            return fuseFunction(next);
        }

        public <A> WithIntFunction<A> fusingFunction(DoubleFunction<A> next) {
            return WithIntFunction.of(fuseFunction(next));
        }

        public <A> WithIntFunction<A> fusing(DoubleFunction<A> next) {
            return fusingFunction(next);
        }
    }

    public static final class WithIntToLongFunction extends Fusion<Long, IntToLongFunction> {
        private final IntToLongFunction initial;

        private WithIntToLongFunction(IntToLongFunction initial) {
            this.initial = initial;
        }

        public static WithIntToLongFunction of(IntToLongFunction initial) {
            return new WithIntToLongFunction(initial);
        }

        @Override
        public IntToLongFunction resolve() {
            return initial;
        }

        /* IntToLongFunction -> LongFunction<A> */

        public <A> IntFunction<A> fuseFunction(LongFunction<A> next) {
            return Z.fuse(initial, next);
        }

        public <A> IntFunction<A> fuse(LongFunction<A> next) {
            return fuseFunction(next);
        }

        public <A> WithIntFunction<A> fusingFunction(LongFunction<A> next) {
            return WithIntFunction.of(fuseFunction(next));
        }

        public <A> WithIntFunction<A> fusing(LongFunction<A> next) {
            return fusingFunction(next);
        }
    }

    public static final class WithToIntFunction<A> extends Fusion<Integer, ToIntFunction<A>> {
        private final ToIntFunction<A> initial;

        private WithToIntFunction(ToIntFunction<A> initial) {
            this.initial = initial;
        }

        public static <A> WithToIntFunction<A> of(ToIntFunction<A> initial) {
            return new WithToIntFunction<>(initial);
        }

        @Override
        public ToIntFunction<A> resolve() {
            return initial;
        }

        /* ToIntFunction<A> -> IntFunction<B> */

        public <B> Function<A, B> fuseIntFunction(IntFunction<B> next) {
            return Z.fuse(initial, next);
        }

        public <B> Function<A, B> fuse(IntFunction<B> next) {
            return fuseIntFunction(next);
        }

        public <B> WithFunction<A, B> fusingIntFunction(IntFunction<B> next) {
            return WithFunction.of(fuseIntFunction(next));
        }

        public <B> WithFunction<A, B> fusing(IntFunction<B> next) {
            return fusingIntFunction(next);
        }
    }

    public static final class WithToIntBiFunction<A, B> extends Fusion<Integer, ToIntBiFunction<A, B>> {
        private final ToIntBiFunction<A, B> initial;

        private WithToIntBiFunction(ToIntBiFunction<A, B> initial) {
            this.initial = initial;
        }

        public static <A, B> WithToIntBiFunction<A, B> of(ToIntBiFunction<A, B> initial) {
            return new WithToIntBiFunction<>(initial);
        }

        @Override
        public ToIntBiFunction<A, B> resolve() {
            return initial;
        }

        /* ToIntBiFunction<A, B> -> IntFunction<C> */

        public <C> Function<A, Function<B, C>> fuseIntFunction(IntFunction<C> next) {
            return Z.fuse(initial, next);
        }

        public <C> Function<A, Function<B, C>> fuse(IntFunction<C> next) {
            return fuseIntFunction(next);
        }

        public <C> WithBiFunction<A, B, C> fusingIntFunction(IntFunction<C> next) {
            return WithBiFunction.of(fuseIntFunction(next));
        }

        public <C> WithBiFunction<A, B, C> fusing(IntFunction<C> next) {
            return fusingIntFunction(next);
        }
    }

    public static final class WithLongFunction<A> extends Fusion<A, LongFunction<A>> {
        private final LongFunction<A> initial;

        private WithLongFunction(LongFunction<A> initial) {
            this.initial = initial;
        }

        public static <A> WithLongFunction<A> of(LongFunction<A> initial) {
            return new WithLongFunction<>(initial);
        }

        @Override
        public LongFunction<A> resolve() {
            return initial;
        }

        /* LongFunction<A> -> Function<A,B> */

        public <B> LongFunction<B> fuseFunction(Function<A, B> next) {
            return Z.fuse(initial, next);
        }

        public <B> LongFunction<B> fuse(Function<A, B> next) {
            return fuseFunction(next);
        }

        public <B> WithLongFunction<B> fusingFunction(Function<A, B> next) {
            return WithLongFunction.of(fuseFunction(next));
        }

        public <B> WithLongFunction<B> fusing(Function<A, B> next) {
            return fusingFunction(next);
        }
    }

    public static final class WithLongToDoubleFunction extends Fusion<Long, LongToDoubleFunction> {
        private final LongToDoubleFunction initial;

        private WithLongToDoubleFunction(LongToDoubleFunction initial) {
            this.initial = initial;
        }

        public static WithLongToDoubleFunction of(LongToDoubleFunction initial) {
            return new WithLongToDoubleFunction(initial);
        }

        @Override
        public LongToDoubleFunction resolve() {
            return initial;
        }

        /* LongToDoubleFunction -> DoubleFunction<A> */

        public <A> LongFunction<A> fuseFunction(DoubleFunction<A> next) {
            return Z.fuse(initial, next);
        }

        public <A> LongFunction<A> fuse(DoubleFunction<A> next) {
            return fuseFunction(next);
        }

        public <A> WithLongFunction<A> fusingFunction(DoubleFunction<A> next) {
            return WithLongFunction.of(fuseFunction(next));
        }

        public <A> WithLongFunction<A> fusing(DoubleFunction<A> next) {
            return fusingFunction(next);
        }
    }

    public static final class WithLongToIntFunction extends Fusion<Long, LongToIntFunction> {
        private final LongToIntFunction initial;

        private WithLongToIntFunction(LongToIntFunction initial) {
            this.initial = initial;
        }

        public static WithLongToIntFunction of(LongToIntFunction initial) {
            return new WithLongToIntFunction(initial);
        }

        @Override
        public LongToIntFunction resolve() {
            return initial;
        }

        /* LongToIntFunction -> IntFunction<A> */

        public <A> LongFunction<A> fuseFunction(IntFunction<A> next) {
            return Z.fuse(initial, next);
        }

        public <A> LongFunction<A> fuse(IntFunction<A> next) {
            return fuseFunction(next);
        }

        public <A> WithLongFunction<A> fusingFunction(IntFunction<A> next) {
            return WithLongFunction.of(fuseFunction(next));
        }

        public <A> WithLongFunction<A> fusing(IntFunction<A> next) {
            return fusingFunction(next);
        }
    }

    public static final class WithToLongFunction<A> extends Fusion<Long, ToLongFunction<A>> {
        private final ToLongFunction<A> initial;

        private WithToLongFunction(ToLongFunction<A> initial) {
            this.initial = initial;
        }

        public static <A> WithToLongFunction<A> of(ToLongFunction<A> initial) {
            return new WithToLongFunction<>(initial);
        }

        @Override
        public ToLongFunction<A> resolve() {
            return initial;
        }

        /* ToLongFunction<A> -> LongFunction<B> */

        public <B> Function<A, B> fuseLongFunction(LongFunction<B> next) {
            return Z.fuse(initial, next);
        }

        public <B> Function<A, B> fuse(LongFunction<B> next) {
            return fuseLongFunction(next);
        }

        public <B> WithFunction<A, B> fusingLongFunction(LongFunction<B> next) {
            return WithFunction.of(fuseLongFunction(next));
        }

        public <B> WithFunction<A, B> fusing(LongFunction<B> next) {
            return fusingLongFunction(next);
        }
    }

    public static final class WithToLongBiFunction<A, B> extends Fusion<Long, ToLongBiFunction<A, B>> {
        private final ToLongBiFunction<A, B> initial;

        private WithToLongBiFunction(ToLongBiFunction<A, B> initial) {
            this.initial = initial;
        }

        public static <A, B> WithToLongBiFunction<A, B> of(ToLongBiFunction<A, B> initial) {
            return new WithToLongBiFunction<>(initial);
        }

        @Override
        public ToLongBiFunction<A, B> resolve() {
            return initial;
        }

        /* ToLongBiFunction<A, B> -> LongFunction<C> */

        public <C> Function<A, Function<B, C>> fuseLongFunction(LongFunction<C> next) {
            return Z.fuse(initial, next);
        }

        public <C> Function<A, Function<B, C>> fuse(LongFunction<C> next) {
            return fuseLongFunction(next);
        }

        public <C> WithBiFunction<A, B, C> fusingLongFunction(LongFunction<C> next) {
            return WithBiFunction.of(fuseLongFunction(next));
        }

        public <C> WithBiFunction<A, B, C> fusing(LongFunction<C> next) {
            return fusingLongFunction(next);
        }
    }

    public static final class WithConsumer<A> extends Fusion<Void, Consumer<A>> {
        private final Consumer<A> initial;

        private WithConsumer(Consumer<A> initial) {
            this.initial = initial;
        }

        public static <A> WithConsumer<A> of(Consumer<A> initial) {
            return new WithConsumer<>(initial);
        }

        @Override
        public Consumer<A> resolve() {
            return initial;
        }
    }

    public static final class WithBiConsumer<A, B> extends Fusion<Void, BiConsumer<A, B>> {
        private final BiConsumer<A, B> initial;

        private WithBiConsumer(BiConsumer<A, B> initial) {
            this.initial = initial;
        }

        public static <A, B> WithBiConsumer<A, B> of(BiConsumer<A, B> initial) {
            return new WithBiConsumer<>(initial);
        }

        @Override
        public BiConsumer<A, B> resolve() {
            return initial;
        }
    }

    public static final class WithPredicate<A> extends Fusion<Boolean, Predicate<A>> {
        private final Predicate<A> initial;

        private WithPredicate(Predicate<A> initial) {
            this.initial = initial;
        }

        public static <A> WithPredicate<A> of(Predicate<A> initial) {
            return new WithPredicate<>(initial);
        }

        @Override
        public Predicate<A> resolve() {
            return initial;
        }

        /* Predicate<A> -> BooleanFunction<B> */

        public <B> Function<A, B> fuseFunction(BooleanFunction<B> next) {
            return Z.fuse(initial, next);
        }

        public <B> Function<A, B> fuse(BooleanFunction<B> next) {
            return fuseFunction(next);
        }

        public <B> WithFunction<A, B> fusingFunction(BooleanFunction<B> next) {
            return WithFunction.of(fuseFunction(next));
        }

        public <B> WithFunction<A, B> fusing(BooleanFunction<B> next) {
            return fusingFunction(next);
        }
    }

    public static final class WithBiPredicate<A, B> extends Fusion<Boolean, BiPredicate<A, B>> {
        private final BiPredicate<A, B> initial;

        private WithBiPredicate(BiPredicate<A, B> initial) {
            this.initial = initial;
        }

        public static <A, B> WithBiPredicate<A, B> of(BiPredicate<A, B> initial) {
            return new WithBiPredicate<>(initial);
        }

        @Override
        public BiPredicate<A, B> resolve() {
            return initial;
        }

        /* BiPredicate<A, B> -> BooleanFunction<C> */

        public <C> Function<A, Function<B, C>> fuseBooleanFunction(BooleanFunction<C> next) {
            return Z.fuse(initial, next);
        }

        public <C> Function<A, Function<B, C>> fuse(BooleanFunction<C> next) {
            return fuseBooleanFunction(next);
        }

        public <C> WithBiFunction<A, B, C> fusingBooleanFunction(BooleanFunction<C> next) {
            return WithBiFunction.of(fuseBooleanFunction(next));
        }

        public <C> WithBiFunction<A, B, C> fusing(BooleanFunction<C> next) {
            return fusingBooleanFunction(next);
        }
    }

    public static final class WithSupplier<A> extends Fusion<A, Supplier<A>> {
        private final Supplier<A> initial;

        private WithSupplier(Supplier<A> initial) {
            this.initial = initial;
        }

        public static <A> WithSupplier<A> of(Supplier<A> initial) {
            return new WithSupplier<>(initial);
        }

        @Override
        public Supplier<A> resolve() {
            return initial;
        }

        /* Supplier<A> -> Function<A, B> */

        public <B> Supplier<B> fuseFunction(Function<A, B> next) {
            return Z.fuse(resolve(), next);
        }

        public <B> Supplier<B> fuse(Function<A, B> next) {
            return fuseFunction(next);
        }

        public <B> WithSupplier<B> fusingFunction(Function<A, B> next) {
            return WithSupplier.of(fuse(next));
        }

        public <B> WithSupplier<B> fusing(Function<A, B> next) {
            return fusingFunction(next);
        }

        /* Supplier<A> -> BiFunction<A, B, C> */

        public <B, C> Function<B, C> fuseBiFunction(BiFunction<A, B, C> next) {
            return Z.fuse(resolve(), next);
        }

        public <B, C> Function<B, C> fuse(BiFunction<A, B, C> next) {
            return fuseBiFunction(next);
        }

        public <B, C> WithFunction<B, C> fusingBiFunction(BiFunction<A, B, C> next) {
            return WithFunction.of(fuseBiFunction(next));
        }

        public <B, C> WithFunction<B, C> fusing(BiFunction<A, B, C> next) {
            return fusingBiFunction(next);
        }
    }
}
