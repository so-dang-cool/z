package so.dang.cool.z;

import java.util.function.*;

import so.dang.cool.z.continuation.FunctionContinuation;
import so.dang.cool.z.continuation.SupplierContinuation;
import so.dang.cool.z.function.Operator;

public class Z {
    public static <A> SupplierContinuation<A> with(A initial) {
        return SupplierContinuation.of(initial);
    }
    
    public static <A,B,C> Function<A, C> fuse(Function<A, B> initial, Function<B, C> next) {
        return next.compose(initial);
    }

    public static <A, B> Predicate<A> fuse(Function<A, B> initial, Predicate<B> next) {
        return (A a) -> next.test(initial.apply(a));
    }

    public static <A, B> Consumer<A> fuse(Function<A, B> initial, Consumer<B> next) {
        return (A a) -> next.accept(initial.apply(a));
    }

    public static <A, B> Supplier<B> fuse(Supplier<A> initial, Function<A, B> next) {
        return () -> next.apply(initial.get());
    }

    public static <A> BooleanSupplier fuse(Supplier<A> initial, Predicate<A> next) {
        return () -> next.test(initial.get());
    }

    public static <A> Operator fuse(Supplier<A> initial, Consumer<A> next) {
        return () -> next.accept(initial.get());
    }

    public static <A, B, C> FunctionContinuation<A, C> fusing(Function<A, B> initial, Function<B, C> next) {
        return FunctionContinuation.of(initial, next);
    }

    public static <A, B> SupplierContinuation<B> fusing(Supplier<A> initial, Function<A, B> next) {
        return SupplierContinuation.of(initial, next);
    }
}
