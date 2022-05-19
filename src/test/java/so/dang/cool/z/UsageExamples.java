package so.dang.cool.z;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import so.dang.cool.z.annotation.Evil;
import so.dang.cool.z.function.Operator;

public class UsageExamples {

    @Test
    public void example_fusion() {
        var internedTrim = Z.fuse(String::trim, String::intern);

        assertEquals("hello", internedTrim.apply(" hello "));

        // Protip: Interned strings can use == directly.
        assertTrue("hello" == internedTrim.apply(" hello "));
    }

    @Test
    public void example_fission() {
        var concat = Z.split(String::concat);

        assertEquals("hotpot", concat.apply("hot").apply("pot"));

        // Protip: "Curried" functions can be partially applied.
        var goodSomething = concat.apply("pre");

        assertEquals("prefix", goodSomething.apply("fix"));
        assertEquals("presume", goodSomething.apply("sume"));

        // Protip: Z also has a "flip" function to change order.
        var fixedSomething = Z.flip(concat).apply("fix");

        assertEquals("prefix", fixedSomething.apply("pre"));
        assertEquals("suffix", fixedSomething.apply("suf"));
    }

    @Test
    public void example_assimilation() {
        var checkoutMessage = Z.assimilate2(
            (String item) ->
                (String name) -> String.format("Enjoy your %s, %s!", item, name)
        );

        assertEquals(
            "Enjoy your bike, Alice!",
            checkoutMessage.apply("bike", "Alice")
        );
    }

    @Test
    public void example_absorption() {
        var heroes = new ArrayList<>(List.of("joker"));

        var emptiedHeroes = Z.absorb(heroes::clear, () -> heroes);

        assertEquals(List.of(), emptiedHeroes.get());

        heroes.add("twoface");
        emptiedHeroes.get().add("batman");
        assertEquals(List.of("batman"), heroes);
    }

    @Test
    public void ascii_sum_plain_java() {
        var asciiSum =
            ((Function<IntStream, Integer>) IntStream::sum).compose(
                    String::chars
                );

        assertEquals(294, asciiSum.apply("abc"));
        assertEquals(775, asciiSum.apply("burrito"));
        assertEquals(1196, asciiSum.apply("albuquerque"));
    }

    @Test
    public void ascii_sum_plain_java_lambda() {
        ToIntFunction<String> asciiSum = s -> s.chars().sum();

        assertEquals(294, asciiSum.applyAsInt("abc"));
        assertEquals(775, asciiSum.applyAsInt("burrito"));
        assertEquals(1196, asciiSum.applyAsInt("albuquerque"));
    }

    @Test
    public void ascii_sum_z() {
        // Z handles composition a little more succinctly.
        var asciiSum = Z.fuse(String::chars, IntStream::sum);

        assertEquals(294, asciiSum.applyAsInt("abc"));
        assertEquals(775, asciiSum.applyAsInt("burrito"));
        assertEquals(1196, asciiSum.applyAsInt("albuquerque"));
    }

    @Test
    public void is_toledo_plain_java() {
        // Capturing as a functional interface is necessary to expose composition methods.
        Function<String, String> toLowerCase = String::toLowerCase;
        Function<String, Boolean> isLowerCaseToledo = s -> s.equals("toledo");

        // Composition doesn't support composing with predicates, only with other functions.
        // (So you'll get an "apply" method instead of "test")
        // A lambda can get you there, though.
        Function<String, Boolean> isToledoFn = isLowerCaseToledo.compose(
            toLowerCase
        );
        Predicate<String> isToledo = s -> isToledoFn.apply(s);

        assertAll(
            () -> isToledo.test("Toledo"),
            () -> isToledo.test("toledo"),
            () -> isToledo.test("TOLEDO"),
            () -> isToledo.test("ToLeDo"),
            () -> isToledo.test("tOlEdO")
        );

        assertAll(
            () -> isToledo.negate().test("Saskatchewan"),
            () -> isToledo.negate().test("Tokyo"),
            () -> isToledo.negate().test("Vienna")
        );
    }

    @Test
    public void is_toledo_z() {
        // Types are still required to eliminate ambiguity.
        Function<String, String> toLowerCase = String::toLowerCase;
        Predicate<String> isLowerToledo = s -> s.equals("toledo");

        Predicate<String> isToledo = Z.fuse(toLowerCase, isLowerToledo);

        assertAll(
            () -> isToledo.test("Toledo"),
            () -> isToledo.test("toledo"),
            () -> isToledo.test("TOLEDO"),
            () -> isToledo.test("ToLeDo"),
            () -> isToledo.test("tOlEdO")
        );

        assertAll(
            () -> isToledo.negate().test("Saskatchewan"),
            () -> isToledo.negate().test("Tokyo"),
            () -> isToledo.negate().test("Vienna")
        );
    }

    @Test
    public void regex_plain_java() {
        Predicate<String> isLocalHost = (String s) ->
            Pattern
                .compile("https?://localhost(:\\d+)?(/\\S*)?")
                .matcher(s)
                .matches();

        assertFalse(isLocalHost.test("invalid"));
        assertTrue(isLocalHost.test("https://localhost:443"));
    }

    @Test
    public void simple_regex_example() {
        final String urlRegex = "https?://localhost(:\\d+)?(/\\S*)?";
        Pattern pattern = Pattern.compile(urlRegex);

        Predicate<CharSequence> isLocalHost = Z.fuse(
            pattern::matcher,
            Matcher::matches
        );

        assertFalse(isLocalHost.test("invalid"));
        assertTrue(isLocalHost.test("https://localhost:443"));
    }

    @Test
    public void less_simple_regex_example() {
        final String urlRegex = "https?://localhost(:\\d+)?(/\\S*)?";

        Function<String, Pattern> patternOf = Pattern::compile;
        Function<String, Function<CharSequence, Matcher>> matcherOf = Z.fuse(
            patternOf,
            Pattern::matcher
        );
        Predicate<CharSequence> isLocalHost = Z.fuse(
            matcherOf.apply(urlRegex),
            Matcher::matches
        );

        assertFalse(isLocalHost.test("invalid"));
        assertTrue(isLocalHost.test("https://localhost:443"));
    }

    @Test
    public void complex_regex_example() {
        var isLocalHost = Z
            .with("https?://localhost(:\\d+)?(/\\S*)?")
            .fuseFunction(Pattern::compile)
            .fuse(Pattern::matcher)
            .fuse(Matcher::matches);

        assertFalse(isLocalHost.test("invalid"));
        assertTrue(isLocalHost.test("https://localhost:443"));
    }

    @Test
    public void uhawwwwwww_example() {
        Function<String, String> addW = (String s) -> s.concat("ｗ");

        var addSevenWs = Z
            .with(addW)
            .fusing(addW)
            .fusing(addW)
            .fusing(addW)
            .fusing(addW)
            .fusing(addW)
            .fuse(addW);

        assertEquals("うはｗｗｗｗｗｗｗ", addSevenWs.apply("うは"));
    }

    @Test
    public void uhawwwwwww_example_reducing() {
        Function<String, String> addW = (String s) -> s.concat("ｗ");

        Function<String, String> addSevenWs = IntStream
            .range(0, 7)
            .mapToObj(ignored -> addW)
            .reduce(Z::fuse)
            .get();

        assertEquals("うはｗｗｗｗｗｗｗ", addSevenWs.apply("うは"));
    }

    @Test
    public void splitting_int_to_string() {
        // This doesn't work...
        // Bifunction<Integer, Integer, String> iToS = Integer::toString;

        // Integer::toString has a few implementations, this doesn't have enough context for the compiler
        // var iToS = Z.split(Integer::toString);

        // ... but this works!
        Function<Integer, Function<Integer, String>> iToS = Z.split2(
            Integer::toString
        );

        String res = IntStream
            .range(0, 15)
            .mapToObj(
                i ->
                    IntStream
                        .range(1, 16)
                        .mapToObj(Integer::valueOf)
                        .map(iToS.apply(i))
                        .collect(Collectors.joining(", "))
            )
            .collect(Collectors.joining("\n"));

        assertEquals(
            "0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0\n" +
            "1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1\n" +
            "2, 10, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2\n" +
            "3, 11, 10, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3\n" +
            "4, 100, 11, 10, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4\n" +
            "5, 101, 12, 11, 10, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5\n" +
            "6, 110, 20, 12, 11, 10, 6, 6, 6, 6, 6, 6, 6, 6, 6\n" +
            "7, 111, 21, 13, 12, 11, 10, 7, 7, 7, 7, 7, 7, 7, 7\n" +
            "8, 1000, 22, 20, 13, 12, 11, 10, 8, 8, 8, 8, 8, 8, 8\n" +
            "9, 1001, 100, 21, 14, 13, 12, 11, 10, 9, 9, 9, 9, 9, 9\n" +
            "10, 1010, 101, 22, 20, 14, 13, 12, 11, 10, a, a, a, a, a\n" +
            "11, 1011, 102, 23, 21, 15, 14, 13, 12, 11, 10, b, b, b, b\n" +
            "12, 1100, 110, 30, 22, 20, 15, 14, 13, 12, 11, 10, c, c, c\n" +
            "13, 1101, 111, 31, 23, 21, 16, 15, 14, 13, 12, 11, 10, d, d\n" +
            "14, 1110, 112, 32, 24, 22, 20, 16, 15, 14, 13, 12, 11, 10, e",
            res
        );
    }

    @Evil
    @Test
    public void splitting_int_to_string_TOO_MUCH_Z_FUSE() {
        // The level of fusion in this test case is evil.
        // Complexity control is attempted with an object.

        var iToS = Z.split(
            (BiFunction<Integer, Integer, String>) Integer::toString
        );

        var integerStream = Z.fuse(
            IntStream::range,
            (BiFunction<IntStream, IntFunction<Integer>, Stream<Integer>>) IntStream::mapToObj
        );

        var stringing = Z.fuse(
            (Integer start, Integer end) ->
                integerStream.apply(start).apply(end).apply(Integer::valueOf),
            Z.fuse(
                (BiFunction<Stream<Integer>, Function<Integer, String>, Stream<String>>) Stream::map,
                (Stream<String> stream, String delim) ->
                    stream.collect(Collectors.joining(delim))
            )
        );

        // A class can be used to convey intent to complex fusions.
        // This is briefly included to fuel the imagination. For real world
        // applications, I'd suggest something like Immutables, a staged
        // builder, and add a static pure function from Stringer -> String.
        class Stringer {

            transient int start;
            transient int end;
            transient Function<Integer, String> xform;
            transient String delim;

            Stringer() {}

            Stringer range(int start, int end) {
                this.start = start;
                this.end = end;
                return this;
            }

            Stringer transform(Function<Integer, String> xform) {
                this.xform = xform;
                return this;
            }

            Stringer delimiter(String delim) {
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
        }

        String res = new Stringer()
            .range(0, 15)
            .transform(
                i ->
                    new Stringer()
                        .range(1, 16)
                        .transform(iToS.apply(i))
                        .delimiter(", ")
                        .go()
            )
            .delimiter("\n")
            .go();

        assertEquals(
            "0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0\n" +
            "1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1\n" +
            "2, 10, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2\n" +
            "3, 11, 10, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3\n" +
            "4, 100, 11, 10, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4\n" +
            "5, 101, 12, 11, 10, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5\n" +
            "6, 110, 20, 12, 11, 10, 6, 6, 6, 6, 6, 6, 6, 6, 6\n" +
            "7, 111, 21, 13, 12, 11, 10, 7, 7, 7, 7, 7, 7, 7, 7\n" +
            "8, 1000, 22, 20, 13, 12, 11, 10, 8, 8, 8, 8, 8, 8, 8\n" +
            "9, 1001, 100, 21, 14, 13, 12, 11, 10, 9, 9, 9, 9, 9, 9\n" +
            "10, 1010, 101, 22, 20, 14, 13, 12, 11, 10, a, a, a, a, a\n" +
            "11, 1011, 102, 23, 21, 15, 14, 13, 12, 11, 10, b, b, b, b\n" +
            "12, 1100, 110, 30, 22, 20, 15, 14, 13, 12, 11, 10, c, c, c\n" +
            "13, 1101, 111, 31, 23, 21, 16, 15, 14, 13, 12, 11, 10, d, d\n" +
            "14, 1110, 112, 32, 24, 22, 20, 16, 15, 14, 13, 12, 11, 10, e",
            res
        );
    }

    @Evil
    @Test
    public void phrase_builder_HIDDEN_SIDE_EFFECTS() {
        List<String> regrets = new ArrayList<>();

        Supplier<String> getPhrase = () ->
            regrets.stream().collect(Collectors.joining(" "));

        Operator no = () -> regrets.add("no");
        Operator nono = Z.absorb(no, no);
        Consumer<String> regret = regrets::add;
        Consumer<String> starter = Z.absorb(regret, nono);

        // End result looks like an innocent String -> String function...
        Function<String, String> regretCreator = Z.absorb(starter, getPhrase);

        String result1 = regretCreator.apply("Oh,");
        String result2 = regretCreator.apply("... Oh... no no");

        assertEquals("Oh, no no", result1);
        assertEquals("Oh, no no ... Oh... no no no no", result2);
    }
}
