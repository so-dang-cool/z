package so.dang.cool.z.combination;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static so.dang.cool.z.combination.TestFunctions.*;

import org.junit.jupiter.api.Test;

import so.dang.cool.z.Z;

public class FissionTests {
    @Test
    void split2_test() {
        assertEquals("ab", Z.split(concat2).apply("a").apply("b"));
    }

    @Test
    void split3_test() {
        assertEquals("abc", Z.split(concat3).apply("a").apply("b").apply("c"));
    }

    @Test
    void split4_test() {
        assertEquals("abcd", Z.split(concat4).apply("a").apply("b").apply("c").apply("d"));
    }

    @Test
    void split5_test() {
        assertEquals("abcde", Z.split(concat5).apply("a").apply("b").apply("c").apply("d").apply("e"));
    }

    @Test
    void split6_test() {
        assertEquals("abcdef", Z.split(concat6).apply("a").apply("b").apply("c").apply("d").apply("e").apply("f"));
    }

    @Test
    void split7_test() {
        assertEquals("abcdefg", Z.split(concat7).apply("a").apply("b").apply("c").apply("d").apply("e").apply("f").apply("g"));
    }

    @Test
    void split8_test() {
        assertEquals("abcdefgh", Z.split(concat8).apply("a").apply("b").apply("c").apply("d").apply("e").apply("f").apply("g").apply("h"));
    }

    @Test
    void split9_test() {
        assertEquals("abcdefghi", Z.split(concat9).apply("a").apply("b").apply("c").apply("d").apply("e").apply("f").apply("g").apply("h").apply("i"));
    }

    @Test
    void split10_test() {
        assertEquals("abcdefghij", Z.split(concat10).apply("a").apply("b").apply("c").apply("d").apply("e").apply("f").apply("g").apply("h").apply("i").apply("j"));
    }

    @Test
    void split11_test() {
        assertEquals("abcdefghijk", Z.split(concat11).apply("a").apply("b").apply("c").apply("d").apply("e").apply("f").apply("g").apply("h").apply("i").apply("j").apply("k"));
    }

    @Test
    void split12_test() {
        assertEquals("abcdefghijkl", Z.split(concat12).apply("a").apply("b").apply("c").apply("d").apply("e").apply("f").apply("g").apply("h").apply("i").apply("j").apply("k").apply("l"));
    }
}
