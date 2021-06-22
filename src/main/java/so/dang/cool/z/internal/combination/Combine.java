package so.dang.cool.z.internal.combination;

import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleFunction;
import java.util.function.DoublePredicate;
import java.util.function.DoubleSupplier;
import java.util.function.DoubleToIntFunction;
import java.util.function.DoubleToLongFunction;
import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.IntSupplier;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntToLongFunction;
import java.util.function.IntUnaryOperator;
import java.util.function.LongBinaryOperator;
import java.util.function.LongConsumer;
import java.util.function.LongFunction;
import java.util.function.LongPredicate;
import java.util.function.LongSupplier;
import java.util.function.LongToDoubleFunction;
import java.util.function.LongToIntFunction;
import java.util.function.LongUnaryOperator;
import java.util.function.ObjDoubleConsumer;
import java.util.function.ObjIntConsumer;
import java.util.function.ObjLongConsumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToDoubleBiFunction;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntBiFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongBiFunction;
import java.util.function.ToLongFunction;
import java.util.function.UnaryOperator;
import so.dang.cool.z.annotation.Evil;
import so.dang.cool.z.annotation.Experimental;
import so.dang.cool.z.function.BooleanConsumer;
import so.dang.cool.z.function.BooleanFunction;
import so.dang.cool.z.function.BooleanPredicate;
import so.dang.cool.z.function.BooleanToDoubleFunction;
import so.dang.cool.z.function.BooleanToIntFunction;
import so.dang.cool.z.function.BooleanToLongFunction;
import so.dang.cool.z.function.Operator;

/**
 * Deep fusions involving many functions.
 */
@Experimental
public abstract class Combine<A, Fn> {

    private Combine() {}

    /**
     * Retrieve the resulting fusion.
     */
    public abstract Fn resolve();

    public static class WithFunction<A, B>
        extends Combine<B, Function<A, B>>
        implements FunctionCombos<A, B> {

        private final transient Function<A, B> initial;

        private WithFunction(Function<A, B> initial) {
            this.initial = Objects.requireNonNull(initial);
        }

        public static <A, B> WithFunction<A, B> of(Function<A, B> initial) {
            return new WithFunction<>(initial);
        }

        @Override
        public Function<A, B> resolve() {
            return initial;
        }
    }

    public static final class WithBiFunction<A, B, C>
        extends Combine<C, Function<A, Function<B, C>>>
        implements BiFunctionCombos<A, B, C> {

        private final transient BiFunction<A, B, C> initial;

        private WithBiFunction(BiFunction<A, B, C> initial) {
            this.initial = Objects.requireNonNull(initial);
        }

        public static <A, B, C> WithBiFunction<A, B, C> of(
            BiFunction<A, B, C> initial
        ) {
            return new WithBiFunction<>(initial);
        }

        public static <A, B, C> WithBiFunction<A, B, C> of(
            Function<A, Function<B, C>> initial
        ) {
            return new WithBiFunction<>(
                (A a, B b) -> initial.apply(a).apply(b)
            );
        }

        @Override
        public Function<A, Function<B, C>> resolve() {
            return (A a) -> (B b) -> initial.apply(a, b);
        }
    }

    public static final class WithBooleanFunction<A>
        extends Combine<A, BooleanFunction<A>>
        implements BooleanFunctionCombos<A> {

        private final transient BooleanFunction<A> initial;

        private WithBooleanFunction(BooleanFunction<A> initial) {
            this.initial = Objects.requireNonNull(initial);
        }

        public static <A> WithBooleanFunction<A> of(
            BooleanFunction<A> initial
        ) {
            return new WithBooleanFunction<>(initial);
        }

        @Override
        public BooleanFunction<A> resolve() {
            return initial;
        }
    }

    public static final class WithBooleanToDoubleFunction
        extends Combine<Double, BooleanToDoubleFunction>
        implements BooleanToDoubleFunctionCombos {

        private final transient BooleanToDoubleFunction initial;

        private WithBooleanToDoubleFunction(BooleanToDoubleFunction initial) {
            this.initial = Objects.requireNonNull(initial);
        }

        public static WithBooleanToDoubleFunction of(
            BooleanToDoubleFunction initial
        ) {
            return new WithBooleanToDoubleFunction(initial);
        }

        @Override
        public BooleanToDoubleFunction resolve() {
            return initial;
        }
    }

    public static final class WithBooleanToIntFunction
        extends Combine<Integer, BooleanToIntFunction>
        implements BooleanToIntFunctionCombos {

        private final transient BooleanToIntFunction initial;

        private WithBooleanToIntFunction(BooleanToIntFunction initial) {
            this.initial = Objects.requireNonNull(initial);
        }

        public static WithBooleanToIntFunction of(
            BooleanToIntFunction initial
        ) {
            return new WithBooleanToIntFunction(initial);
        }

        @Override
        public BooleanToIntFunction resolve() {
            return initial;
        }
    }

    public static final class WithBooleanToLongFunction
        extends Combine<Long, BooleanToLongFunction>
        implements BooleanToLongFunctionCombos {

        private final transient BooleanToLongFunction initial;

        private WithBooleanToLongFunction(BooleanToLongFunction initial) {
            this.initial = Objects.requireNonNull(initial);
        }

        public static WithBooleanToLongFunction of(
            BooleanToLongFunction initial
        ) {
            return new WithBooleanToLongFunction(initial);
        }

        @Override
        public BooleanToLongFunction resolve() {
            return initial;
        }
    }

    public static final class WithDoubleFunction<A>
        extends Combine<A, DoubleFunction<A>>
        implements DoubleFunctionCombos<A> {

        private final transient DoubleFunction<A> initial;

        private WithDoubleFunction(DoubleFunction<A> initial) {
            this.initial = Objects.requireNonNull(initial);
        }

        public static <A> WithDoubleFunction<A> of(DoubleFunction<A> initial) {
            return new WithDoubleFunction<>(initial);
        }

        @Override
        public DoubleFunction<A> resolve() {
            return initial;
        }
    }

    public static final class WithDoubleToIntFunction
        extends Combine<Integer, DoubleToIntFunction>
        implements DoubleToIntFunctionCombos {

        private final transient DoubleToIntFunction initial;

        private WithDoubleToIntFunction(DoubleToIntFunction initial) {
            this.initial = Objects.requireNonNull(initial);
        }

        public static WithDoubleToIntFunction of(DoubleToIntFunction initial) {
            return new WithDoubleToIntFunction(initial);
        }

        @Override
        public DoubleToIntFunction resolve() {
            return initial;
        }
    }

    public static final class WithDoubleToLongFunction
        extends Combine<Long, DoubleToLongFunction>
        implements DoubleToLongFunctionCombos {

        private final transient DoubleToLongFunction initial;

        private WithDoubleToLongFunction(DoubleToLongFunction initial) {
            this.initial = Objects.requireNonNull(initial);
        }

        public static WithDoubleToLongFunction of(
            DoubleToLongFunction initial
        ) {
            return new WithDoubleToLongFunction(initial);
        }

        @Override
        public DoubleToLongFunction resolve() {
            return initial;
        }
    }

    public static final class WithToDoubleFunction<A>
        extends Combine<Double, ToDoubleFunction<A>>
        implements ToDoubleFunctionCombos<A> {

        private final transient ToDoubleFunction<A> initial;

        private WithToDoubleFunction(ToDoubleFunction<A> initial) {
            this.initial = Objects.requireNonNull(initial);
        }

        public static <A> WithToDoubleFunction<A> of(
            ToDoubleFunction<A> initial
        ) {
            return new WithToDoubleFunction<>(initial);
        }

        @Override
        public ToDoubleFunction<A> resolve() {
            return initial;
        }
    }

    public static final class WithToDoubleBiFunction<A, B>
        extends Combine<Double, Function<A, ToDoubleFunction<B>>>
        implements ToDoubleBiFunctionCombos<A, B> {

        private final transient ToDoubleBiFunction<A, B> initial;

        private WithToDoubleBiFunction(ToDoubleBiFunction<A, B> initial) {
            this.initial = Objects.requireNonNull(initial);
        }

        public static <A, B> WithToDoubleBiFunction<A, B> of(
            ToDoubleBiFunction<A, B> initial
        ) {
            return new WithToDoubleBiFunction<>(initial);
        }

        public static <A, B> WithToDoubleBiFunction<A, B> of(
            Function<A, ToDoubleFunction<B>> initial
        ) {
            return new WithToDoubleBiFunction<>(
                (A a, B b) -> initial.apply(a).applyAsDouble(b)
            );
        }

        @Override
        public Function<A, ToDoubleFunction<B>> resolve() {
            return (A a) -> (B b) -> initial.applyAsDouble(a, b);
        }
    }

    public static final class WithIntFunction<A>
        extends Combine<A, IntFunction<A>>
        implements IntFunctionCombos<A> {

        private final transient IntFunction<A> initial;

        private WithIntFunction(IntFunction<A> initial) {
            this.initial = Objects.requireNonNull(initial);
        }

        public static <A> WithIntFunction<A> of(IntFunction<A> initial) {
            return new WithIntFunction<>(initial);
        }

        @Override
        public IntFunction<A> resolve() {
            return initial;
        }
    }

    public static final class WithIntToDoubleFunction
        extends Combine<Integer, IntToDoubleFunction>
        implements IntToDoubleFunctionCombos {

        private final transient IntToDoubleFunction initial;

        private WithIntToDoubleFunction(IntToDoubleFunction initial) {
            this.initial = Objects.requireNonNull(initial);
        }

        public static WithIntToDoubleFunction of(IntToDoubleFunction initial) {
            return new WithIntToDoubleFunction(initial);
        }

        @Override
        public IntToDoubleFunction resolve() {
            return initial;
        }
    }

    public static final class WithIntToLongFunction
        extends Combine<Long, IntToLongFunction>
        implements IntToLongFunctionCombos {

        private final transient IntToLongFunction initial;

        private WithIntToLongFunction(IntToLongFunction initial) {
            this.initial = Objects.requireNonNull(initial);
        }

        public static WithIntToLongFunction of(IntToLongFunction initial) {
            return new WithIntToLongFunction(initial);
        }

        @Override
        public IntToLongFunction resolve() {
            return initial;
        }
    }

    public static final class WithToIntFunction<A>
        extends Combine<Integer, ToIntFunction<A>>
        implements ToIntFunctionCombos<A> {

        private final transient ToIntFunction<A> initial;

        private WithToIntFunction(ToIntFunction<A> initial) {
            this.initial = Objects.requireNonNull(initial);
        }

        public static <A> WithToIntFunction<A> of(ToIntFunction<A> initial) {
            return new WithToIntFunction<>(initial);
        }

        @Override
        public ToIntFunction<A> resolve() {
            return initial;
        }
    }

    public static final class WithToIntBiFunction<A, B>
        extends Combine<Integer, Function<A, ToIntFunction<B>>>
        implements ToIntBiFunctionCombos<A, B> {

        private final transient ToIntBiFunction<A, B> initial;

        private WithToIntBiFunction(ToIntBiFunction<A, B> initial) {
            this.initial = Objects.requireNonNull(initial);
        }

        public static <A, B> WithToIntBiFunction<A, B> of(
            ToIntBiFunction<A, B> initial
        ) {
            return new WithToIntBiFunction<>(initial);
        }

        public static <A, B> WithToIntBiFunction<A, B> of(
            Function<A, ToIntFunction<B>> initial
        ) {
            return new WithToIntBiFunction<>(
                (A a, B b) -> initial.apply(a).applyAsInt(b)
            );
        }

        @Override
        public Function<A, ToIntFunction<B>> resolve() {
            return (A a) -> (B b) -> initial.applyAsInt(a, b);
        }
    }

    public static final class WithLongFunction<A>
        extends Combine<A, LongFunction<A>>
        implements LongFunctionCombos<A> {

        private final transient LongFunction<A> initial;

        private WithLongFunction(LongFunction<A> initial) {
            this.initial = Objects.requireNonNull(initial);
        }

        public static <A> WithLongFunction<A> of(LongFunction<A> initial) {
            return new WithLongFunction<>(initial);
        }

        @Override
        public LongFunction<A> resolve() {
            return initial;
        }
    }

    public static final class WithLongToDoubleFunction
        extends Combine<Long, LongToDoubleFunction>
        implements LongToDoubleFunctionCombos {

        private final transient LongToDoubleFunction initial;

        private WithLongToDoubleFunction(LongToDoubleFunction initial) {
            this.initial = Objects.requireNonNull(initial);
        }

        public static WithLongToDoubleFunction of(
            LongToDoubleFunction initial
        ) {
            return new WithLongToDoubleFunction(initial);
        }

        @Override
        public LongToDoubleFunction resolve() {
            return initial;
        }
    }

    public static final class WithLongToIntFunction
        extends Combine<Long, LongToIntFunction>
        implements LongToIntFunctionCombos {

        private final transient LongToIntFunction initial;

        private WithLongToIntFunction(LongToIntFunction initial) {
            this.initial = Objects.requireNonNull(initial);
        }

        public static WithLongToIntFunction of(LongToIntFunction initial) {
            return new WithLongToIntFunction(initial);
        }

        @Override
        public LongToIntFunction resolve() {
            return initial;
        }
    }

    public static final class WithToLongFunction<A>
        extends Combine<Long, ToLongFunction<A>>
        implements ToLongFunctionCombos<A> {

        private final transient ToLongFunction<A> initial;

        private WithToLongFunction(ToLongFunction<A> initial) {
            this.initial = Objects.requireNonNull(initial);
        }

        public static <A> WithToLongFunction<A> of(ToLongFunction<A> initial) {
            return new WithToLongFunction<>(initial);
        }

        @Override
        public ToLongFunction<A> resolve() {
            return initial;
        }
    }

    public static final class WithToLongBiFunction<A, B>
        extends Combine<Long, Function<A, ToLongFunction<B>>>
        implements ToLongBiFunctionCombos<A, B> {

        private final transient ToLongBiFunction<A, B> initial;

        private WithToLongBiFunction(ToLongBiFunction<A, B> initial) {
            this.initial = Objects.requireNonNull(initial);
        }

        public static <A, B> WithToLongBiFunction<A, B> of(
            ToLongBiFunction<A, B> initial
        ) {
            return new WithToLongBiFunction<>(initial);
        }

        public static <A, B> WithToLongBiFunction<A, B> of(
            Function<A, ToLongFunction<B>> initial
        ) {
            return new WithToLongBiFunction<>(
                (A a, B b) -> initial.apply(a).applyAsLong(b)
            );
        }

        @Override
        public Function<A, ToLongFunction<B>> resolve() {
            return (A a) -> (B b) -> initial.applyAsLong(a, b);
        }
    }

    @Evil
    public static final class WithConsumer<A>
        extends Combine<Void, Consumer<A>>
        implements ConsumerCombos<A> {

        private final transient Consumer<A> initial;

        private WithConsumer(Consumer<A> initial) {
            this.initial = Objects.requireNonNull(initial);
        }

        public static <A> WithConsumer<A> of(Consumer<A> initial) {
            return new WithConsumer<>(initial);
        }

        @Override
        public Consumer<A> resolve() {
            return initial;
        }
    }

    @Evil
    public static final class WithBiConsumer<A, B>
        extends Combine<Void, Function<A, Consumer<B>>>
        implements BiConsumerCombos<A, B> {

        private final transient BiConsumer<A, B> initial;

        private WithBiConsumer(BiConsumer<A, B> initial) {
            this.initial = Objects.requireNonNull(initial);
        }

        public static <A, B> WithBiConsumer<A, B> of(BiConsumer<A, B> initial) {
            return new WithBiConsumer<>(initial);
        }

        public static <A, B> WithBiConsumer<A, B> of(
            Function<A, Consumer<B>> initial
        ) {
            return new WithBiConsumer<>(
                (A a, B b) -> initial.apply(a).accept(b)
            );
        }

        @Override
        public Function<A, Consumer<B>> resolve() {
            return (A a) -> (B b) -> initial.accept(a, b);
        }
    }

    @Evil
    public static final class WithBooleanConsumer
        extends Combine<Void, BooleanConsumer>
        implements BooleanConsumerCombos {

        private final transient BooleanConsumer initial;

        private WithBooleanConsumer(BooleanConsumer initial) {
            this.initial = Objects.requireNonNull(initial);
        }

        public static WithBooleanConsumer of(BooleanConsumer initial) {
            return new WithBooleanConsumer(initial);
        }

        @Override
        public BooleanConsumer resolve() {
            return initial;
        }
    }

    @Evil
    public static final class WithDoubleConsumer
        extends Combine<Void, DoubleConsumer>
        implements DoubleConsumerCombos {

        private final transient DoubleConsumer initial;

        private WithDoubleConsumer(DoubleConsumer initial) {
            this.initial = Objects.requireNonNull(initial);
        }

        public static WithDoubleConsumer of(DoubleConsumer initial) {
            return new WithDoubleConsumer(initial);
        }

        @Override
        public DoubleConsumer resolve() {
            return initial;
        }
    }

    @Evil
    public static final class WithObjDoubleConsumer<A>
        extends Combine<Void, Function<A, DoubleConsumer>>
        implements ObjDoubleConsumerCombos<A> {

        private final transient ObjDoubleConsumer<A> initial;

        private WithObjDoubleConsumer(ObjDoubleConsumer<A> initial) {
            this.initial = Objects.requireNonNull(initial);
        }

        public static <A> WithObjDoubleConsumer<A> of(
            ObjDoubleConsumer<A> initial
        ) {
            return new WithObjDoubleConsumer<>(initial);
        }

        public static <A> WithObjDoubleConsumer<A> of(
            Function<A, DoubleConsumer> initial
        ) {
            return new WithObjDoubleConsumer<>(
                (A a, double d) -> initial.apply(a).accept(d)
            );
        }

        @Override
        public Function<A, DoubleConsumer> resolve() {
            return (A a) -> (double d) -> initial.accept(a, d);
        }
    }

    @Evil
    public static final class WithIntConsumer
        extends Combine<Void, IntConsumer>
        implements IntConsumerCombos {

        private final transient IntConsumer initial;

        private WithIntConsumer(IntConsumer initial) {
            this.initial = Objects.requireNonNull(initial);
        }

        public static WithIntConsumer of(IntConsumer initial) {
            return new WithIntConsumer(initial);
        }

        @Override
        public IntConsumer resolve() {
            return initial;
        }
    }

    @Evil
    public static final class WithObjIntConsumer<A>
        extends Combine<Void, Function<A, IntConsumer>>
        implements ObjIntConsumerCombos<A> {

        private final transient ObjIntConsumer<A> initial;

        private WithObjIntConsumer(ObjIntConsumer<A> initial) {
            this.initial = Objects.requireNonNull(initial);
        }

        public static <A> WithObjIntConsumer<A> of(ObjIntConsumer<A> initial) {
            return new WithObjIntConsumer<>(initial);
        }

        public static <A> WithObjIntConsumer<A> of(
            Function<A, IntConsumer> initial
        ) {
            return new WithObjIntConsumer<>(
                (A a, int i) -> initial.apply(a).accept(i)
            );
        }

        @Override
        public Function<A, IntConsumer> resolve() {
            return (A a) -> (int i) -> initial.accept(a, i);
        }
    }

    @Evil
    public static final class WithLongConsumer
        extends Combine<Void, LongConsumer>
        implements LongConsumerCombos {

        private final transient LongConsumer initial;

        private WithLongConsumer(LongConsumer initial) {
            this.initial = Objects.requireNonNull(initial);
        }

        public static WithLongConsumer of(LongConsumer initial) {
            return new WithLongConsumer(initial);
        }

        @Override
        public LongConsumer resolve() {
            return initial;
        }
    }

    @Evil
    public static final class WithObjLongConsumer<A>
        extends Combine<Void, Function<A, LongConsumer>>
        implements ObjLongConsumerCombos<A> {

        private final transient ObjLongConsumer<A> initial;

        private WithObjLongConsumer(ObjLongConsumer<A> initial) {
            this.initial = Objects.requireNonNull(initial);
        }

        public static <A> WithObjLongConsumer<A> of(
            ObjLongConsumer<A> initial
        ) {
            return new WithObjLongConsumer<>(initial);
        }

        public static <A> WithObjLongConsumer<A> of(
            Function<A, LongConsumer> initial
        ) {
            return new WithObjLongConsumer<>(
                (A a, long n) -> initial.apply(a).accept(n)
            );
        }

        @Override
        public Function<A, LongConsumer> resolve() {
            return (A a) -> (long n) -> initial.accept(a, n);
        }
    }

    public static final class WithPredicate<A>
        extends Combine<Boolean, Predicate<A>>
        implements PredicateCombos<A> {

        private final transient Predicate<A> initial;

        private WithPredicate(Predicate<A> initial) {
            this.initial = Objects.requireNonNull(initial);
        }

        public static <A> WithPredicate<A> of(Predicate<A> initial) {
            return new WithPredicate<>(initial);
        }

        @Override
        public Predicate<A> resolve() {
            return initial;
        }
    }

    public static final class WithBiPredicate<A, B>
        extends Combine<Boolean, Function<A, Predicate<B>>>
        implements BiPredicateCombos<A, B> {

        private final transient BiPredicate<A, B> initial;

        private WithBiPredicate(BiPredicate<A, B> initial) {
            this.initial = Objects.requireNonNull(initial);
        }

        public static <A, B> WithBiPredicate<A, B> of(
            BiPredicate<A, B> initial
        ) {
            return new WithBiPredicate<>(initial);
        }

        public static <A, B> WithBiPredicate<A, B> of(
            Function<A, Predicate<B>> initial
        ) {
            return new WithBiPredicate<>(
                (A a, B b) -> initial.apply(a).test(b)
            );
        }

        @Override
        public Function<A, Predicate<B>> resolve() {
            return (A a) -> (B b) -> initial.test(a, b);
        }
    }

    public static final class WithBooleanPredicate
        extends Combine<Boolean, BooleanPredicate>
        implements BooleanPredicateCombos {

        private final transient BooleanPredicate initial;

        private WithBooleanPredicate(BooleanPredicate initial) {
            this.initial = Objects.requireNonNull(initial);
        }

        public static WithBooleanPredicate of(BooleanPredicate initial) {
            return new WithBooleanPredicate(initial);
        }

        @Override
        public BooleanPredicate resolve() {
            return initial;
        }
    }

    public static final class WithDoublePredicate
        extends Combine<Double, DoublePredicate>
        implements DoublePredicateCombos {

        private final transient DoublePredicate initial;

        private WithDoublePredicate(DoublePredicate initial) {
            this.initial = Objects.requireNonNull(initial);
        }

        public static WithDoublePredicate of(DoublePredicate initial) {
            return new WithDoublePredicate(initial);
        }

        @Override
        public DoublePredicate resolve() {
            return initial;
        }
    }

    public static final class WithIntPredicate
        extends Combine<Integer, IntPredicate>
        implements IntPredicateCombos {

        private final transient IntPredicate initial;

        private WithIntPredicate(IntPredicate initial) {
            this.initial = Objects.requireNonNull(initial);
        }

        public static WithIntPredicate of(IntPredicate initial) {
            return new WithIntPredicate(initial);
        }

        @Override
        public IntPredicate resolve() {
            return initial;
        }
    }

    public static final class WithLongPredicate
        extends Combine<Long, LongPredicate>
        implements LongPredicateCombos {

        private final transient LongPredicate initial;

        private WithLongPredicate(LongPredicate initial) {
            this.initial = Objects.requireNonNull(initial);
        }

        public static WithLongPredicate of(LongPredicate initial) {
            return new WithLongPredicate(initial);
        }

        @Override
        public LongPredicate resolve() {
            return initial;
        }
    }

    public static final class WithSupplier<A>
        extends Combine<A, Supplier<A>>
        implements SupplierCombos<A> {

        private final transient Supplier<A> initial;

        private WithSupplier(Supplier<A> initial) {
            this.initial = Objects.requireNonNull(initial);
        }

        public static <A> WithSupplier<A> of(Supplier<A> initial) {
            return new WithSupplier<>(initial);
        }

        @Override
        public Supplier<A> resolve() {
            return initial;
        }
    }

    public static final class WithBooleanSupplier
        extends Combine<Boolean, BooleanSupplier>
        implements BooleanSupplierCombos {

        private final transient BooleanSupplier initial;

        private WithBooleanSupplier(BooleanSupplier initial) {
            this.initial = Objects.requireNonNull(initial);
        }

        public static <A> WithBooleanSupplier of(BooleanSupplier initial) {
            return new WithBooleanSupplier(initial);
        }

        @Override
        public BooleanSupplier resolve() {
            return initial;
        }
    }

    public static final class WithDoubleSupplier
        extends Combine<Double, DoubleSupplier>
        implements DoubleSupplierCombos {

        private final transient DoubleSupplier initial;

        private WithDoubleSupplier(DoubleSupplier initial) {
            this.initial = Objects.requireNonNull(initial);
        }

        public static <A> WithDoubleSupplier of(DoubleSupplier initial) {
            return new WithDoubleSupplier(initial);
        }

        @Override
        public DoubleSupplier resolve() {
            return initial;
        }
    }

    public static final class WithIntSupplier
        extends Combine<Integer, IntSupplier>
        implements IntSupplierCombos {

        private final transient IntSupplier initial;

        private WithIntSupplier(IntSupplier initial) {
            this.initial = Objects.requireNonNull(initial);
        }

        public static <A> WithIntSupplier of(IntSupplier initial) {
            return new WithIntSupplier(initial);
        }

        @Override
        public IntSupplier resolve() {
            return initial;
        }
    }

    public static final class WithLongSupplier
        extends Combine<Long, LongSupplier>
        implements LongSupplierCombos {

        private final transient LongSupplier initial;

        private WithLongSupplier(LongSupplier initial) {
            this.initial = Objects.requireNonNull(initial);
        }

        public static <A> WithLongSupplier of(LongSupplier initial) {
            return new WithLongSupplier(initial);
        }

        @Override
        public LongSupplier resolve() {
            return initial;
        }
    }

    public static final class WithOperator
        extends Combine<Void, Operator>
        implements OperatorCombos {

        private final transient Operator initial;

        private WithOperator(Operator initial) {
            this.initial = Objects.requireNonNull(initial);
        }

        public static WithOperator of(Operator initial) {
            return new WithOperator(initial);
        }

        @Override
        public Operator resolve() {
            return initial;
        }
    }

    public static final class WithUnaryOperator<A>
        extends Combine<A, UnaryOperator<A>> {

        private final transient UnaryOperator<A> initial;

        private WithUnaryOperator(UnaryOperator<A> initial) {
            this.initial = Objects.requireNonNull(initial);
        }

        public static <A> WithUnaryOperator<A> of(UnaryOperator<A> initial) {
            return new WithUnaryOperator<>(initial);
        }

        @Override
        public UnaryOperator<A> resolve() {
            return initial;
        }

        /* UnaryOperator -> Function<A, B> */

        public <B> Function<A, B> fuseFunction(Function<A, B> next) {
            return (A a) -> next.apply(resolve().apply(a));
        }

        public <B> Function<A, B> fuse(Function<A, B> next) {
            return fuseFunction(next);
        }

        public <B> WithFunction<A, B> fusingFunction(Function<A, B> next) {
            return WithFunction.of(fuse(next));
        }

        public <B> WithFunction<A, B> fusing(Function<A, B> next) {
            return fusingFunction(next);
        }

        /* UnaryOperator -> BiFunction<A, B, C> */

        public <B, C> Function<A, Function<B, C>> fuseBiFunction(
            BiFunction<A, B, C> next
        ) {
            return (A a) -> (B c) -> next.apply(resolve().apply(a), c);
        }

        public <B, C> Function<A, Function<B, C>> fuse(
            BiFunction<A, B, C> next
        ) {
            return fuseBiFunction(next);
        }

        public <B, C> WithBiFunction<A, B, C> fusingBiFunction(
            BiFunction<A, B, C> next
        ) {
            return WithBiFunction.of(fuse(next));
        }

        public <B, C> WithBiFunction<A, B, C> fusing(BiFunction<A, B, C> next) {
            return fusingBiFunction(next);
        }
    }

    public static final class WithBinaryOperator<A>
        extends Combine<A, Function<A, UnaryOperator<A>>> {

        private final transient BinaryOperator<A> initial;

        private WithBinaryOperator(BinaryOperator<A> initial) {
            this.initial = Objects.requireNonNull(initial);
        }

        public static <A> WithBinaryOperator<A> of(BinaryOperator<A> initial) {
            return new WithBinaryOperator<>(initial);
        }

        @Override
        public Function<A, UnaryOperator<A>> resolve() {
            return (A a1) -> (A a2) -> initial.apply(a1, a2);
        }

        /* BinaryOperator -> Function<A, B> */

        public <B> Function<A, Function<A, B>> fuseFunction(
            Function<A, B> next
        ) {
            return (A a) -> (A b) -> next.apply(initial.apply(a, b));
        }

        public <B> Function<A, Function<A, B>> fuse(Function<A, B> next) {
            return fuseFunction(next);
        }

        public <B> WithBiFunction<A, A, B> fusingFunction(Function<A, B> next) {
            return WithBiFunction.of(fuse(next));
        }

        public <B> WithBiFunction<A, A, B> fusing(Function<A, B> next) {
            return fusingFunction(next);
        }
    }

    public static final class WithDoubleUnaryOperator
        extends Combine<Double, DoubleUnaryOperator>
        implements DoubleUnaryOperatorCombos {

        private final transient DoubleUnaryOperator initial;

        private WithDoubleUnaryOperator(DoubleUnaryOperator initial) {
            this.initial = Objects.requireNonNull(initial);
        }

        public static <A> WithDoubleUnaryOperator of(
            DoubleUnaryOperator initial
        ) {
            return new WithDoubleUnaryOperator(initial);
        }

        @Override
        public DoubleUnaryOperator resolve() {
            return initial;
        }
    }

    public static final class WithDoubleBinaryOperator
        extends Combine<Double, DoubleFunction<DoubleUnaryOperator>>
        implements DoubleBinaryOperatorCombos {

        private final transient DoubleBinaryOperator initial;

        private WithDoubleBinaryOperator(DoubleBinaryOperator initial) {
            this.initial = Objects.requireNonNull(initial);
        }

        public static WithDoubleBinaryOperator of(
            DoubleBinaryOperator initial
        ) {
            return new WithDoubleBinaryOperator(initial);
        }

        public static WithDoubleBinaryOperator of(
            DoubleFunction<DoubleUnaryOperator> initial
        ) {
            return new WithDoubleBinaryOperator(
                (double d1, double d2) -> initial.apply(d1).applyAsDouble(d2)
            );
        }

        @Override
        public DoubleFunction<DoubleUnaryOperator> resolve() {
            return (double d1) -> (double d2) -> initial.applyAsDouble(d1, d2);
        }
    }

    public static final class WithIntUnaryOperator
        extends Combine<Integer, IntUnaryOperator>
        implements IntUnaryOperatorCombos {

        private final transient IntUnaryOperator initial;

        private WithIntUnaryOperator(IntUnaryOperator initial) {
            this.initial = Objects.requireNonNull(initial);
        }

        public static <A> WithIntUnaryOperator of(IntUnaryOperator initial) {
            return new WithIntUnaryOperator(initial);
        }

        @Override
        public IntUnaryOperator resolve() {
            return initial;
        }
    }

    public static final class WithIntBinaryOperator
        extends Combine<Integer, IntFunction<IntUnaryOperator>>
        implements IntBinaryOperatorCombos {

        private final transient IntBinaryOperator initial;

        private WithIntBinaryOperator(IntBinaryOperator initial) {
            this.initial = Objects.requireNonNull(initial);
        }

        public static <A> WithIntBinaryOperator of(IntBinaryOperator initial) {
            return new WithIntBinaryOperator(initial);
        }

        public static WithIntBinaryOperator of(
            IntFunction<IntUnaryOperator> initial
        ) {
            return new WithIntBinaryOperator(
                (int i1, int i2) -> initial.apply(i1).applyAsInt(i2)
            );
        }

        @Override
        public IntFunction<IntUnaryOperator> resolve() {
            return (int i1) -> (int i2) -> initial.applyAsInt(i1, i2);
        }
    }

    public static final class WithLongUnaryOperator
        extends Combine<Long, LongUnaryOperator>
        implements LongUnaryOperatorCombos {

        private final transient LongUnaryOperator initial;

        private WithLongUnaryOperator(LongUnaryOperator initial) {
            this.initial = Objects.requireNonNull(initial);
        }

        public static <A> WithLongUnaryOperator of(LongUnaryOperator initial) {
            return new WithLongUnaryOperator(initial);
        }

        @Override
        public LongUnaryOperator resolve() {
            return initial;
        }
    }

    public static final class WithLongBinaryOperator
        extends Combine<Long, LongFunction<LongUnaryOperator>>
        implements LongBinaryOperatorCombos {

        private final transient LongBinaryOperator initial;

        private WithLongBinaryOperator(LongBinaryOperator initial) {
            this.initial = Objects.requireNonNull(initial);
        }

        public static <A> WithLongBinaryOperator of(
            LongBinaryOperator initial
        ) {
            return new WithLongBinaryOperator(initial);
        }

        public static <A> WithLongBinaryOperator of(
            LongFunction<LongUnaryOperator> initial
        ) {
            return new WithLongBinaryOperator(
                (long n1, long n2) -> initial.apply(n1).applyAsLong(n2)
            );
        }

        @Override
        public LongFunction<LongUnaryOperator> resolve() {
            return (long n1) -> (long n2) -> initial.applyAsLong(n1, n2);
        }
    }
}
