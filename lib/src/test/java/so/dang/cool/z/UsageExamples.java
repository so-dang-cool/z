package so.dang.cool.z;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

public class UsageExamples {
    @Test
    public void simple_regex_example() {
        final String urlRegex = "https?://localhost(:\\d+)?(/\\S*)?";
        Pattern pattern = Pattern.compile(urlRegex);

        Predicate<CharSequence> predicate = Z.fuse(pattern::matcher, Matcher::matches);

        assertFalse(predicate.test("invalid"));
        assertTrue(predicate.test("https://localhost:443"));
    }

    @Test
    public void complex_regex_example() {
        Predicate<CharSequence> predicate = Z.with("https?://localhost(:\\d+)?(/\\S*)?")
            .fusingFn(Pattern::compile)
            .fusing(Pattern::matcher)
            .fuse(Matcher::matches);

        assertFalse(predicate.test("invalid"));
        assertTrue(predicate.test("https://localhost:443"));
    }

    @Test
    public void uhawwwwwww_example() {
        Function<String, String> addW = (String s) -> s.concat("ｗ");

        var addSevenWs = Z.with(addW)
            .fusing(addW)
            .fusing(addW)
            .fusing(addW)
            .fusing(addW)
            .fusing(addW)
            .fuse(addW);

        assertEquals("うはｗｗｗｗｗｗｗ", addSevenWs.apply("うは"));
    }

    @Test
    public void splitting_int_to_string() {
        // This doesn't work...
        // Bifunction<Integer, Integer, String> iToS = Integer::toString;

        // Integer::toString has a few implementations, this doesn't have enough context for the compiler
        // var iToS = Z.split(Integer::toString);

        // ... but this works!
        Function<Integer, Function<Integer, String>> iToS =
            Z.split(Integer::toString);

        String res = IntStream.range(0, 15)
            .mapToObj(i ->
                IntStream.range(1, 16)
                    .mapToObj(Integer::valueOf)
                    .map(iToS.apply(i))
                    .collect(Collectors.joining(", "))
            )
            .collect(Collectors.joining("\n"));

        assertEquals(
            "0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0\n"
            + "1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1\n"
            + "2, 10, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2\n"
            + "3, 11, 10, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3\n"
            + "4, 100, 11, 10, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4\n"
            + "5, 101, 12, 11, 10, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5\n"
            + "6, 110, 20, 12, 11, 10, 6, 6, 6, 6, 6, 6, 6, 6, 6\n"
            + "7, 111, 21, 13, 12, 11, 10, 7, 7, 7, 7, 7, 7, 7, 7\n"
            + "8, 1000, 22, 20, 13, 12, 11, 10, 8, 8, 8, 8, 8, 8, 8\n"
            + "9, 1001, 100, 21, 14, 13, 12, 11, 10, 9, 9, 9, 9, 9, 9\n"
            + "10, 1010, 101, 22, 20, 14, 13, 12, 11, 10, a, a, a, a, a\n"
            + "11, 1011, 102, 23, 21, 15, 14, 13, 12, 11, 10, b, b, b, b\n"
            + "12, 1100, 110, 30, 22, 20, 15, 14, 13, 12, 11, 10, c, c, c\n"
            + "13, 1101, 111, 31, 23, 21, 16, 15, 14, 13, 12, 11, 10, d, d\n"
            + "14, 1110, 112, 32, 24, 22, 20, 16, 15, 14, 13, 12, 11, 10, e",
            res
        );
    }

    @Test
    public void splitting_int_to_string_TOO_MUCH_Z_FUSE() {
        // The level of fusion in this test case is probably not a good idea.

        var iToS = Z.split(
            (BiFunction<Integer, Integer, String>) Integer::toString
        );

        var integerStream = Z.fuse(
            IntStream::range,
            (BiFunction<IntStream, IntFunction<Integer>, Stream<Integer>>) IntStream::mapToObj
        );

        var stringing = Z.fuse(
            (Integer start, Integer end) ->
                integerStream
                    .apply(start)
                    .apply(end)
                    .apply(Integer::valueOf),
            Z.fuse(
                (BiFunction<Stream<Integer>, Function<Integer, String>, Stream<String>>)
                    Stream::map,
                (Stream<String> stream, String delim) -> stream.collect(Collectors.joining(delim))
            )
        );

        // A class can be used to convey intent to complex fusions.
        // This is briefly included to fuel the imagination. For real world
        // applications, I'd suggest something like Immutables, a staged
        // builder, and add a static pure function from Stringer -> String.
        class Stringer {
            int start;
            int end;
            Function<Integer, String> xform;
            String delim;

            Stringer() {}
            Stringer range(int start, int end) {
                this.start = start;
                this.end = end;
                return this;
            }
            Stringer xform(Function<Integer, String> xform) {
                this.xform = xform;
                return this;
            }
            Stringer delim(String delim) {
                this.delim = delim;
                return this;
            }
            String go() {
                return stringing
                    .apply(start)
                    .apply(end)
                    .apply(xform)
                    .apply(delim);
            }
        };

        String res = new Stringer()
            .range(0, 15)
            .xform(i ->
                new Stringer()
                    .range(1, 16)
                    .xform(iToS.apply(i))
                    .delim(", ")
                    .go()
            )
            .delim("\n")
            .go();

        assertEquals(
            "0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0\n"
            + "1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1\n"
            + "2, 10, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2\n"
            + "3, 11, 10, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3\n"
            + "4, 100, 11, 10, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4\n"
            + "5, 101, 12, 11, 10, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5\n"
            + "6, 110, 20, 12, 11, 10, 6, 6, 6, 6, 6, 6, 6, 6, 6\n"
            + "7, 111, 21, 13, 12, 11, 10, 7, 7, 7, 7, 7, 7, 7, 7\n"
            + "8, 1000, 22, 20, 13, 12, 11, 10, 8, 8, 8, 8, 8, 8, 8\n"
            + "9, 1001, 100, 21, 14, 13, 12, 11, 10, 9, 9, 9, 9, 9, 9\n"
            + "10, 1010, 101, 22, 20, 14, 13, 12, 11, 10, a, a, a, a, a\n"
            + "11, 1011, 102, 23, 21, 15, 14, 13, 12, 11, 10, b, b, b, b\n"
            + "12, 1100, 110, 30, 22, 20, 15, 14, 13, 12, 11, 10, c, c, c\n"
            + "13, 1101, 111, 31, 23, 21, 16, 15, 14, 13, 12, 11, 10, d, d\n"
            + "14, 1110, 112, 32, 24, 22, 20, 16, 15, 14, 13, 12, 11, 10, e",
            res
        );
    }

    @Test
    public void string_builder_example() {
        // TODO: Figure out how to make this work

        // BiFunction<String, String, String> append = (String base, String suffix) -> base.concat(suffix);

        // var fourPartStringBuilder = Z.with(String.class)
        //     .fusing(append)
        //     .fusing(append)
        //     .fusing(append)
        //     .fuse(append);

        // assertEquals("hello world!", fourPartStringBuilder.accept("hello").apply(" ").apply("world").apply("!"));
    }
}
