package so.dang.cool.z.continuation;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleSupplier;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.function.IntSupplier;
import java.util.function.LongConsumer;
import java.util.function.LongSupplier;
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

import so.dang.cool.z.Z;
import so.dang.cool.z.function.Operator;

public class SupplierContinuation <A> {
    private final Supplier<A> supplier;
    
    private SupplierContinuation(Supplier<A> supplier) {
        this.supplier = supplier;
    }
    
    public static <A> SupplierContinuation<A> of(A initial) {
        return new SupplierContinuation<>(() -> initial);
    }

    public static <A> SupplierContinuation<A> of(Supplier<A> initial) {
        return new SupplierContinuation<>(initial);
    }

    public <B> Supplier<B> fuse(Function<A, B> next) {
        return Z.fuse(supplier, next);
    }

    public <B, C> Function<B, C> fuse(BiFunction<A, B, C> next) {
        return Z.fuse(supplier, next);
    }

    public DoubleSupplier fuse(ToDoubleFunction<A> next) {
        return Z.fuse(supplier, next);
    }

    public <B> ToDoubleFunction<B> fuse(ToDoubleBiFunction<A, B> next) {
        return Z.fuse(supplier, next);
    }

    public IntSupplier fuse(ToIntFunction<A> next) {
        return Z.fuse(supplier, next);
    }

    public <B> ToIntFunction<B> fuse(ToIntBiFunction<A, B> next) {
        return Z.fuse(supplier, next);
    }

    public LongSupplier fuse(ToLongFunction<A> next) {
        return Z.fuse(supplier, next);
    }

    public <B> ToLongFunction<B> fuse(ToLongBiFunction<A, B> next) {
        return Z.fuse(supplier, next);
    }

    public BooleanSupplier fuse(Predicate<A> next) {
        return Z.fuse(supplier, next);
    }

    public <B> Predicate<B> fuse(BiPredicate<A, B> next) {
        return Z.fuse(supplier, next);
    }

    public Operator fuse(Consumer<A> next) {
        return Z.fuse(supplier, next);
    }

    public <B> Consumer<B> fuse(BiConsumer<A, B> next) {
        return Z.fuse(supplier, next);
    }

    public DoubleConsumer fuse(ObjDoubleConsumer<A> next) {
        return Z.fuse(supplier, next);
    }

    public IntConsumer fuse(ObjIntConsumer<A> next) {
        return Z.fuse(supplier, next);
    }

    public LongConsumer fuse(ObjLongConsumer<A> next) {
        return Z.fuse(supplier, next);
    }

    public <B> SupplierContinuation<B> fusing(Function<A, B> next) {
        return fusingFn(next);
    }

    public <B> SupplierContinuation<B> fusingFn(Function<A, B> next) {
        return Z.fusing(supplier, next);
    }

    public <B, C> FunctionContinuation<B, C> fusing(BiFunction<A, B, C> next) {
        return fusingBiFn(next);
    }

    public <B, C> FunctionContinuation<B, C> fusingBiFn(BiFunction<A, B, C> next) {
        return Z.fusing(supplier, next);
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
