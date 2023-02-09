package so.dang.cool.z.misc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;
import java.time.Instant;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import org.junit.jupiter.api.Test;

public class ExplorationTests {

    @Test
    public void decoratedSuppliers() {
        Supplier<Integer> threeSup = () -> {
            try {
                Thread.sleep(Duration.ofSeconds(3).toMillis());
            } catch (InterruptedException _e) {
                // Whatever man.
            }
            return 3;
        };

        Supplier<Integer> timedThreeSup = Suppliers.timed(
            threeSup,
            dur ->
                System.out.printf("Getting three took: %sms\n", dur.toMillis())
        );

        System.out.println("Getting 3...");
        int three = timedThreeSup.get();
        System.out.println("Done");

        assertEquals(3, three);
    }

    static class Suppliers {

        public static <T> Supplier<T> timed(
            Supplier<T> sup,
            Consumer<Duration> elapsed
        ) {
            return () -> {
                Instant before = Instant.now();
                T result = sup.get();
                Instant after = Instant.now();

                elapsed.accept(Duration.between(before, after));

                return result;
            };
        }
    }

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
