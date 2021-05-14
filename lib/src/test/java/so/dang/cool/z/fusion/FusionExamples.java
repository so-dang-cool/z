package so.dang.cool.z.fusion;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

import so.dang.cool.z.Z;

public class FusionExamples {
    @Test
    public void ascii_sum_plain_java() {
        // Capturing as a functional interface is necessary to expose composition methods.
        Function<String, IntStream> chars = String::chars;
        Function<IntStream, Integer> sum = IntStream::sum;

        // Composition supports only Functions. E.g. it's not possible to optimize here as
        // a composition over ToIntFunction<String>. Lambda indirection could fake it, but
        // autoboxing/unboxing would still occur.
        Function<String, Integer> asciiSum = sum.compose(chars);
        
        assertEquals(294, asciiSum.apply("abc"));
        assertEquals(775, asciiSum.apply("burrito"));
        assertEquals(1196, asciiSum.apply("albuquerque"));
    }

    @Test
    public void ascii_sum_z() {
        // Z handles composition a little more succinctly.
        ToIntFunction<String> asciiSum = Z.fuse(String::chars, IntStream::sum);
        
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
        Function<String, Boolean> isToledoFn = isLowerCaseToledo.compose(toLowerCase);
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
}
