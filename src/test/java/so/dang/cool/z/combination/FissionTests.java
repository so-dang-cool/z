package so.dang.cool.z.combination;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static so.dang.cool.z.combination.TestFunctions.*;

import org.junit.jupiter.api.Test;
import so.dang.cool.z.Z;
import so.dang.cool.z.annotation.Evil;

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
        assertEquals(
            "abcd",
            Z.split(concat4).apply("a").apply("b").apply("c").apply("d")
        );
    }

    @Test
    void split5_test() {
        assertEquals(
            "abcde",
            Z
                .split(concat5)
                .apply("a")
                .apply("b")
                .apply("c")
                .apply("d")
                .apply("e")
        );
    }

    @Test
    void split6_test() {
        assertEquals(
            "abcdef",
            Z
                .split(concat6)
                .apply("a")
                .apply("b")
                .apply("c")
                .apply("d")
                .apply("e")
                .apply("f")
        );
    }

    @Test
    void split7_test() {
        assertEquals(
            "abcdefg",
            Z
                .split(concat7)
                .apply("a")
                .apply("b")
                .apply("c")
                .apply("d")
                .apply("e")
                .apply("f")
                .apply("g")
        );
    }

    @Test
    void split8_test() {
        assertEquals(
            "abcdefgh",
            Z
                .split(concat8)
                .apply("a")
                .apply("b")
                .apply("c")
                .apply("d")
                .apply("e")
                .apply("f")
                .apply("g")
                .apply("h")
        );
    }

    @Test
    void split9_test() {
        assertEquals(
            "abcdefghi",
            Z
                .split(concat9)
                .apply("a")
                .apply("b")
                .apply("c")
                .apply("d")
                .apply("e")
                .apply("f")
                .apply("g")
                .apply("h")
                .apply("i")
        );
    }

    @Test
    void split10_test() {
        assertEquals(
            "abcdefghij",
            Z
                .split(concat10)
                .apply("a")
                .apply("b")
                .apply("c")
                .apply("d")
                .apply("e")
                .apply("f")
                .apply("g")
                .apply("h")
                .apply("i")
                .apply("j")
        );
    }

    @Test
    void split11_test() {
        assertEquals(
            "abcdefghijk",
            Z
                .split(concat11)
                .apply("a")
                .apply("b")
                .apply("c")
                .apply("d")
                .apply("e")
                .apply("f")
                .apply("g")
                .apply("h")
                .apply("i")
                .apply("j")
                .apply("k")
        );
    }

    @Test
    void split12_test() {
        assertEquals(
            "abcdefghijkl",
            Z
                .split(concat12)
                .apply("a")
                .apply("b")
                .apply("c")
                .apply("d")
                .apply("e")
                .apply("f")
                .apply("g")
                .apply("h")
                .apply("i")
                .apply("j")
                .apply("k")
                .apply("l")
        );
    }

    @Test
    void split_toDbl_test() {
        assertEquals(
            2.5,
            Z.split(addStringsAsDouble).apply("1.0").applyAsDouble("1.5")
        );
    }

    @Test
    void split_toInt_test() {
        assertEquals(3, Z.split(addStringsAsInt).apply("1").applyAsInt("2"));
    }

    @Test
    void split_toLong_test() {
        assertEquals(3L, Z.split(addStringsAsLong).apply("1").applyAsLong("2"));
    }

    @Test
    void split_bipred_test() {
        assertTrue(Z.split(startsWith).apply("giraffe").test("gir"));
    }

    @Evil
    @Test
    void split_objDblCns_test() {
        synchronized (consumedStringD) {
            synchronized (consumedDoubleB) {
                consumedStringD = "";
                consumedDoubleB = 0.0;

                Z.split(saveStringDDoubleB).apply("ff").accept(11.0);

                assertEquals("ff", consumedStringD);
                assertEquals(11.0, consumedDoubleB);
            }
        }
    }

    @Evil
    @Test
    void split_objIntCns_test() {
        synchronized (consumedStringE) {
            synchronized (consumedIntB) {
                consumedStringE = "";
                consumedIntB = 0;

                Z.split(saveStringEIntB).apply("mother").accept(3);

                assertEquals("mother", consumedStringE);
                assertEquals(3, consumedIntB);
            }
        }
    }

    @Evil
    @Test
    void split_objLongCns_test() {
        synchronized (consumedStringF) {
            synchronized (consumedLongB) {
                consumedStringF = "";
                consumedLongB = 0L;

                Z.split(saveStringFLongB).apply("SaGa").accept(1L);

                assertEquals("SaGa", consumedStringF);
                assertEquals(1L, consumedLongB);
            }
        }
    }
}
