package so.dang.cool.z.misc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.function.Function;
import java.util.function.Supplier;
import org.junit.jupiter.api.Test;

public class ExplorationTests {

    @Test
    public void uhawwwwwww_example() {
        Function<String, String> addW = s -> s.concat("ｗ");

        Function<String, String> addSevenWs = s ->
            Channel
                .push(s)
                .fuse(addW)
                .fuse(addW)
                .fuse(addW)
                .fuse(addW)
                .fuse(addW)
                .fuse(addW)
                .fuse(addW)
                .get();

        assertEquals("うはｗｗｗｗｗｗｗ", addSevenWs.apply("うは"));
    }
}

interface Channel<A, PREV extends Channel<?, ?>> extends Supplier<A> {
    <B> Channel<B, PREV> fuse(Function<A, B> f);

    static <A> OneThing<A, Nothing> push(A a) {
        return new OneThing<>(a, new Nothing());
    }

    class Nothing implements Channel<Void, Nothing> {

        @Override
        public <A> OneThing<A, Nothing> fuse(Function<Void, A> f) {
            return new OneThing<>(f.apply(null), this);
        }

        @Override
        public Void get() {
            return null;
        }
    }

    class OneThing<A, PREV extends Channel<?, ?>> implements Channel<A, PREV> {

        A a;
        PREV prev;

        OneThing(A a, PREV prev) {
            this.a = a;
            this.prev = prev;
        }

        @Override
        public <B> OneThing<B, PREV> fuse(Function<A, B> f) {
            return new OneThing<>(f.apply(a), prev);
        }

        @Override
        public A get() {
            return a;
        }
    }
}
