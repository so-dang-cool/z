package so.dang.cool.z.continuation;

import java.util.function.Function;
import java.util.function.Predicate;

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

    public Predicate<A> fuse(Predicate<B> next) {
        return Z.fuse(function, next);
    }

    public <C> FunctionContinuation<A, C> fusing(Function<B, C> next) {
        return Z.fusing(function, next);
    }
}