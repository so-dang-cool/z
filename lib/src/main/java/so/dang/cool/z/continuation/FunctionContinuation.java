package so.dang.cool.z.continuation;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
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

import so.dang.cool.z.Z;

public class FunctionContinuation <A, B> {
    private final Function<A, B> function;

    private FunctionContinuation(Function <A, B> function) {
        this.function = function;
    }

    public static <A, B> FunctionContinuation<A, B> of(Function<A, B> function) {
        return new FunctionContinuation<>(function);
    }

    public static <A, B, C> FunctionContinuation<A, C> of(Function<A, B> initial, Function<B, C> next) {
        return new FunctionContinuation<>(Z.fuse(initial, next));
    }

    public <C> Function<A, C> fuse(Function<B, C> next) {
        return Z.fuse(function, next);
    }

    public <C, D> Function<A, Function<C, D>> fuse(BiFunction<B, C, D> next) {
        return Z.fuse(function, next);
    }

    public ToDoubleFunction<A> fuse(ToDoubleFunction<B> next) {
        return Z.fuse(function, next);
    }

    public <C> Function<A, ToDoubleFunction<C>> fuse(ToDoubleBiFunction<B, C> next) {
        return Z.fuse(function, next);
    }

    public ToIntFunction<A> fuse(ToIntFunction<B> next) {
        return Z.fuse(function, next);
    }

    public <C> Function<A, ToIntFunction<C>> fuse(ToIntBiFunction<B, C> next) {
        return Z.fuse(function, next);
    }

    public ToLongFunction<A> fuse(ToLongFunction<B> next) {
        return Z.fuse(function, next);
    }

    public <C> Function<A, ToLongFunction<C>> fuse(ToLongBiFunction<B, C> next) {
        return Z.fuse(function, next);
    }

    public Predicate<A> fuse(Predicate<B> next) {
        return Z.fuse(function, next);
    }

    public <C> Function<A, Predicate<C>> fuse(BiPredicate<B, C> next) {
        return Z.fuse(function, next);
    }

    public Consumer<A> fuse(Consumer<B> next) {
        return Z.fuse(function, next);
    }

    public <C> Function<A, Consumer<C>> fuse(BiConsumer<B, C> next) {
        return Z.fuse(function, next);
    }

    public Function<A, DoubleConsumer> fuse(ObjDoubleConsumer<B> next) {
        return Z.fuse(function, next);
    }

    public Function<A, IntConsumer> fuse(ObjIntConsumer<B> next) {
        return Z.fuse(function, next);
    }

    public Function<A, LongConsumer> fuse(ObjLongConsumer<B> next) {
        return Z.fuse(function, next);
    }

    public <C> FunctionContinuation<A, C> fusing(Function<B, C> next) {
        return Z.fusing(function, next);
    }

    /* BiFunctionContinuationContinuation [TODO] */
    /* DoubleFunctionContinuation [TODO] */
    /* DoubleToIntFunctionContinuation [TODO] */
    /* DoubleToLongFunctionContinuation [TODO] */
    /* ToDoubleFunctionContinuation [TODO] */
    /* ToDoubleBiFunctionContinuation [TODO] */
    /* IntFunctionContinuation [TODO] */
    /* IntToDoubleFunctionContinuation [TODO] */
    /* IntToLongFunctionContinuation [TODO] */
    /* ToIntFunctionContinuation [TODO] */
    /* ToIntBiFunctionContinuation [TODO] */
    /* LongFunctionContinuation [TODO] */
    /* LongToDoubleFunctionContinuation [TODO] */
    /* LongToIntFunctionContinuation [TODO] */
    /* ToLongFunctionContinuation [TODO] */
    /* ToLongBiFunctionContinuation [TODO] */
    /* PredicateContinuation [TODO] */
    /* BiPredicateContinuation [TODO] */
    /* DoublePredicateContinuation [TODO] */
    /* IntPredicateContinuation [TODO] */
    /* LongPredicateContinuation [TODO] */
    /* ConsumerContinuation [TODO] */
    /* BiConsumerContinuation [TODO] */
    /* DoubleConsumerContinuation [TODO] */
    /* ObjDoubleConsumerContinuation [TODO] */
    /* IntConsumerContinuation [TODO] */
    /* ObjIntConsumerContinuation [TODO] */
    /* LongConsumerContinuation [TODO] */
    /* ObjLongConsumerContinuation [TODO] */
    /* SupplierContinuation [TODO] */
    /* BooleanSupplierContinuation [TODO] */
    /* DoubleSupplierContinuation [TODO] */
    /* IntSupplierContinuation [TODO] */
    /* LongSupplierContinuation [TODO] */
    /* OperatorContinuation [TODO] */
    /* UnaryOperatorContinuation [TODO] */
    /* BinaryOperatorContinuation [TODO] */
    /* DoubleUnaryOperatorContinuation [TODO] */
    /* DoubleBinaryOperatorContinuation [TODO] */
    /* IntUnaryOperatorContinuation [TODO] */
    /* IntBinaryOperatorContinuation [TODO] */
    /* LongUnaryOperatorContinuation [TODO] */
    /* LongBinaryOperatorContinuation [TODO] */
}