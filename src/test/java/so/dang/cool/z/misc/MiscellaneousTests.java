package so.dang.cool.z.misc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Locale;
import java.util.function.BiFunction;
import java.util.function.Function;
import org.junit.jupiter.api.Test;
import so.dang.cool.z.Z;

public class MiscellaneousTests {

    @Test
    void id_test() {
        assertEquals("1", Z.id(String.class).apply("1"));
    }

    @Test
    void flip_bifn() {
        BiFunction<String, Locale, String> toUpperRaw = String::toUpperCase;
        assertEquals("HELLO", toUpperRaw.apply("hello", Locale.ENGLISH));

        BiFunction<Locale, String, String> toUpperFlipped = Z.flip(toUpperRaw);
        assertEquals("HELLO", toUpperFlipped.apply(Locale.ENGLISH, "hello"));
    }

    @Test
    void flip_curried2() {
        BiFunction<String, Locale, String> toUpperRaw = String::toUpperCase;
        Function<String, Function<Locale, String>> toUpperCurried = Z.split(
            toUpperRaw
        );
        assertEquals(
            "HELLO",
            toUpperCurried.apply("hello").apply(Locale.ENGLISH)
        );

        Function<Locale, Function<String, String>> toUpperFlipped = Z.flip(
            toUpperCurried
        );
        assertEquals(
            "HELLO",
            toUpperFlipped.apply(Locale.ENGLISH).apply("hello")
        );
    }
}
