package so.dang.cool.z.continuation;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import so.dang.cool.z.Z;

public abstract class Continue<A, Fn, Prev> {
    private Continue() {}

    public abstract Fn resolve();

    /* Function [TODO: Incomplete] */

    public static final class WithFn<A, B> extends Continue<B, Function<A, B>, Void> {
        private final Function<A, B> initial;
        private WithFn(Function<A, B> initial) {
            this.initial = initial;
        }

        public static <A, B> WithFn<A, B> of(Function<A, B> initial) {
            return new WithFn<>(initial);
        }

        @Override
        public Function<A, B> resolve() {
            return initial;
        }

        /* Function -> Function */

        public <C> Function<A, C> fuse(Function<B, C> next) {
            return Z.fuse(initial, next);
        }

        public <C> FnFusingFn<A, B, C, Void, WithFn<A, B>> fusing(Function<B, C> next) {
            return FnFusingFn.of(this, next);
        }

        /* Function -> BiFunction */

        public <C, D> Function<A, Function<C, D>> fuse(BiFunction<B, C, D> next) {
            return Z.fuse(initial, next);
        }

        // public <C, D> FnFusingBifn<A, B, C, Void, WithFn<A, B>> fusing(BiFunction<B, C, D> next) {
        //     return FnFusingBifn.of(this, next);
        // }
    }

    public static final class FnFusingFn<A, B, C, Prev, Curr extends Continue<B, Function<A, B>, Prev>> extends Continue<C, Function<A, C>, Curr> {
        private final Curr continuation;
        private final Function<B, C> current;

        private FnFusingFn(Curr continuation, Function<B, C> current) {
            this.continuation = continuation;
            this.current = current;
        }

        public static <A, B, C, Prev, Curr extends Continue<B, Function<A, B>, Prev>> FnFusingFn<A, B, C, Prev, Curr> of(Curr continuation, Function<B, C> current) {
            return new FnFusingFn<>(continuation, current);
        }

        @Override
        public Function<A, C> resolve() {
            return Z.fuse(continuation.resolve(), current);
        }

        /* Function -> Function -> Function */

        public <D> Function<A, D> fuse(Function<C, D> next) {
            return Z.fuse(resolve(), next);
        }

        public <D> FnFusingFn<A, C, D, Curr, FnFusingFn<A, B, C, Prev, Curr>> fusing(Function<C, D> next) {
            return FnFusingFn.of(this, next);
        }
    }

    public static final class FnFusingBifn<A, B, C, D, Curr extends Continue<B, Function<A, B>, Prev>, Prev> extends Continue<C, Function<A, Function<C, D>>, Curr> {
        private final Curr continuation;
        private final BiFunction<B, C, D> current;

        private FnFusingBifn(Curr continuation, BiFunction<B, C, D> current) {
            this.continuation = continuation;
            this.current = current;
        }

        public static <A, B, C, D, Curr extends Continue<B, Function<A, B>, Prev>, Prev> FnFusingBifn<A, B, C, D, Curr, Prev> of(Curr continuation, BiFunction<B, C, D> current) {
            return new FnFusingBifn<>(continuation, current);
        }

        @Override
        public Function<A, Function<C, D>> resolve() {
            return (A a) -> (C c) -> current.apply(continuation.resolve().apply(a), c);
        }

        /* Function -> BiFunction -> Function */

        // public <D> Function<A, D> fuse(Function<C, D> next) {
        //     return Z.fuse(resolve(), next);
        // }

        // public <D> FnFusingFn<A, C, D, Continue<B, Function<A, B>, Prev>> fusing(Function<C, D> next) {
        //     return FnFusingFn.of(this, next);
        // }
    }

    /* BiFunction [TODO: Incomplete] */

    public static final class WithBifn<A, B, C> extends Continue<C, Function<A, Function<B, C>>, Void> {
        private final BiFunction<A, B, C> initial;

        private WithBifn(BiFunction<A, B, C> initial) {
            this.initial = initial;
        }

        public static <A, B, C> WithBifn<A, B, C> of(BiFunction<A, B, C> initial) {
            return new WithBifn<>(initial);
        }

        @Override
        public Function<A, Function<B, C>> resolve() {
            return Z.split(initial);
        }

        /* BiFunction -> Function */

        public <D> Function<A, Function<B, D>> fuse(Function<C, D> next) {
            return Z.fuse(initial, next);
        }

        public <D> BifnFusingFn<A, B, C, D, Void> fusing(Function<C, D> next) {
            return BifnFusingFn.of(this, next);
        }
    }

    public static final class BifnFusingFn<A, B, C, D, Prev> extends Continue<D, Function<A, Function<B, D>>, Continue<C, Function<A, Function<B, C>>, Prev>> {
        private final Continue<C, Function<A, Function<B, C>>, Prev> continuation;
        private final Function<C, D> current;

        private BifnFusingFn(Continue<C, Function<A, Function<B, C>>, Prev> continuation, Function<C, D> current) {
            this.continuation = continuation;
            this.current = current;
        }

        public static <A, B, C, D, Prev> BifnFusingFn<A, B, C, D, Prev> of(Continue<C, Function<A, Function<B, C>>, Prev> continuation, Function<C, D> current) {
            return new BifnFusingFn<>(continuation, current);
        }

        @Override
        public Function<A, Function<B, D>> resolve() {
            return (A a) -> (B b) -> current.apply(continuation.resolve().apply(a).apply(b));
        }

        /* BiFunction -> Function -> Function */

        public <E> Function<A, Function<B, E>> fuse(Function<D, E> next) {
            return (A a) -> (B b) -> next.apply(resolve().apply(a).apply(b));
        }

        public <E> BifnFusingFn<A, B, D, E, Continue<C, Function<A, Function<B, C>>, Prev>> fusing(Function<D, E> next) {
            return BifnFusingFn.of(this, next);
        }
    }

    /* DoubleFunction [TODO] */
    /* DoubleToIntFunction [TODO] */
    /* DoubleToLongFunction [TODO] */
    /* ToDoubleFunction [TODO] */
    /* ToDoubleBiFunction [TODO] */
    /* IntFunction [TODO] */
    /* IntToDoubleFunction [TODO] */
    /* IntToLongFunction [TODO] */
    /* ToIntFunction [TODO] */
    /* ToIntBiFunction [TODO] */
    /* LongFunction [TODO] */
    /* LongToDoubleFunction [TODO] */
    /* LongToIntFunction [TODO] */
    /* ToLongFunction [TODO] */
    /* ToLongBiFunction [TODO] */
    /* Predicate [TODO] */
    /* BiPredicate [TODO] */
    /* DoublePredicate [TODO] */
    /* IntPredicate [TODO] */
    /* LongPredicate [TODO] */
    /* Consumer [TODO] */
    /* BiConsumer [TODO] */
    /* DoubleConsumer [TODO] */
    /* ObjDoubleConsumer [TODO] */
    /* IntConsumer [TODO] */
    /* ObjIntConsumer [TODO] */
    /* LongConsumer [TODO] */
    /* ObjLongConsumer [TODO] */
    /* Supplier [TODO] */
    /* BooleanSupplier [TODO] */
    /* DoubleSupplier [TODO] */
    /* IntSupplier [TODO] */
    /* LongSupplier [TODO] */
    /* Operator (Z only) [TODO] */
    /* UnaryOperator [TODO] */
    /* BinaryOperator [TODO] */
    /* DoubleUnaryOperator [TODO] */
    /* DoubleBinaryOperator [TODO] */
    /* IntUnaryOperator [TODO] */
    /* IntBinaryOperator [TODO] */
    /* LongUnaryOperator [TODO] */
    /* LongBinaryOperator [TODO] */

    public static final class WithSup<A> extends Continue<A, Supplier<A>, Void> {
        private final Supplier<A> initial;
        private WithSup(Supplier<A> initial) {
            this.initial = initial;
        }

        public static <A> WithSup<A> of(Supplier<A> initial) {
            return new WithSup<>(initial);
        }

        @Override
        public Supplier<A> resolve() {
            return initial;
        }

        public <B> Supplier<B> fuse(Function<A, B> next) {
            return Z.fuse(initial, next);
        }

        public <B> SupFusingFn<A, B, Void> fusing(Function<A, B> next) {
            return SupFusingFn.of(this, next);
        }
    }

    public static class SupFusingFn<A, B, Prev> extends Continue<B, Supplier<B>, Continue<A, Supplier<A>, Prev>> {
        private final Continue<A, Supplier<A>, Prev> continuation;
        private final Function<A, B> current;
        
        private SupFusingFn(Continue<A, Supplier<A>, Prev> continuation, Function<A, B> current) {
            this.current = current;
            this.continuation = continuation;
        }

        public static <A, B, Prev> SupFusingFn<A, B, Prev> of(Continue<A, Supplier<A>, Prev> continuation, Function<A, B> current) {
            return new SupFusingFn<>(continuation, current);
        }

        /* Supplier<A> -> Function<A, B> */

        @Override
        public Supplier<B> resolve() {
            return Z.fuse(continuation.resolve(), current);
        }

        /* Supplier<A> -> Function<A, B> -> Function <B, C> */

        public <C> Supplier<C> fuse(Function<B, C> next) {
            return Z.fuse(resolve(), next);
        }

        public <C> SupFusingFn<B, C, Continue<A, Supplier<A>, Prev>> fusing(Function<B, C> next) {
            return SupFusingFn.of(this, next);
        }

        /* Supplier<A> -> Function<A, B> -> BiFunction<B, C, D> */

        public <C, D> Function<C, D> fuse(BiFunction<B, C, D> next) {
            return Z.fuse(resolve(), next);
        }

        // public <C, D, Curr extends Continue<A, Supplier<A>, Prev>> FnFusingBifn<A, B, C, D, String, Prev> fusing(BiFunction<B, C, D> next) {
        //     var intermediate = WithFn.of(Z.fuse(resolve(), next));
        //     return intermediate.fusing(next);
        // }
    }
}
