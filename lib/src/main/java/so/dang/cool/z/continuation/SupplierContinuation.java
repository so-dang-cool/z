package so.dang.cool.z.continuation;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import so.dang.cool.z.Z;

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

    public static <A, B> SupplierContinuation<B> of(Supplier<A> initial, Function<A, B> next) {
        return new SupplierContinuation<>(Z.fuse(initial, next));
    }

    public <B> Supplier<B> fuse(Function<A, B> next) {
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
        return FunctionContinuation.of((B b) -> next.apply(supplier.get(), b));
    }
}