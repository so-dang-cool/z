package so.dang.cool.z.continuation;

import java.util.function.Function;

public class MultiFunctionContinuation<A, B, Fn> {
    private final Function<Function<A, B>, Fn> continuation;

    private MultiFunctionContinuation(Function<Function<A, B>, Fn> continuation) {
        this.continuation = continuation;
    }

    public static <A, B, Fn> MultiFunctionContinuation<A, B, Fn> of(Function<Function<A, B>, Fn> continuation) {
        return new MultiFunctionContinuation<>(continuation);
    }

    /*
        ================
        ┏━╸╻ ╻┏━┓╻┏━┓┏┓╻
        ┣╸ ┃ ┃┗━┓┃┃ ┃┃┗┫
        ╹  ┗━┛┗━┛╹┗━┛╹ ╹
        ================
     */

    public Fn fuse(Function<A, B> next) {
        return continuation.apply(next);
    }

    /*
        ==================================
        ╻ ╻╻ ╻┏━┓┏━╸┏━┓   ┏━╸╻ ╻┏━┓╻┏━┓┏┓╻
        ┣━┫┗┳┛┣━┛┣╸ ┣┳┛   ┣╸ ┃ ┃┗━┓┃┃ ┃┃┗┫
        ╹ ╹ ╹ ╹  ┗━╸╹┗╸   ╹  ┗━┛┗━┛╹┗━┛╹ ╹
        ==================================
     */

     public <C> MultiFunctionContinuation<B, C, Function<A, Fn>> fusing(Function<A, B> next) {
        Function<Function<B, C>, Function<A, Fn>> multiFn =
            (Function<B, C> next2) -> (A a) ->
                next2.apply(continuation.apply(next));
        return MultiFunctionContinuation.of(multiFn);
    }
}

/*

a -> b -> c

Fn<A, Fn<B, C>>

FromFn<C, FromFn<B, A>>

*/

interface FromFn<B, A> {
    public B apply(A a);
}
