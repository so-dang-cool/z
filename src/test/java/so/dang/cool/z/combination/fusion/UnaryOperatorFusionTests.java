package so.dang.cool.z.combination.fusion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static so.dang.cool.z.combination.TestFunctions.*;

import org.junit.jupiter.api.Test;
import so.dang.cool.z.Z;
import so.dang.cool.z.annotation.Evil;

public class UnaryOperatorFusionTests {

    @Test
    void unop() {
        assertEquals("hello?", addQuestionMark.apply("hello"));
    }

    @Test
    void unop_deep() {
        assertEquals(
            "is it me you're looking for?",
            Z
                .with(addQuestionMark)
                .resolve()
                .apply("is it me you're looking for")
        );
    }

    @Test
    void unop_to_fn() {
        assertEquals("hello?", Z.fuse(addQuestionMark, toLower).apply("HeLlO"));
    }

    @Test
    void unop_to_fn_deep() {
        assertEquals(
            "hello?",
            Z.with(addQuestionMark).fuse(toLower).apply("HeLlO")
        );
    }

    @Test
    void unop_to_fn_deeper() {
        assertEquals(
            "hello?",
            Z.with(addQuestionMark).fusing(toLower).resolve().apply("HeLlO")
        );
    }

    @Test
    void unop_to_bifn() {
        assertEquals(
            "hello?!",
            Z.fuse(addQuestionMark, concat).apply("hello").apply("!")
        );
    }

    @Test
    void unop_to_bifn_deep() {
        assertEquals(
            "hello?!",
            Z.with(addQuestionMark).fuse(concat).apply("hello").apply("!")
        );
    }

    @Test
    void unop_to_bifn_deeper() {
        assertEquals(
            "hello?!",
            Z
                .with(addQuestionMark)
                .fusing(concat)
                .resolve()
                .apply("hello")
                .apply("!")
        );
    }

    @Test
    void unop_to_toDblFn() {
        assertEquals(
            1.0,
            Z.fuse(addTrailingZero, stringToDouble).applyAsDouble("1.")
        );
    }

    @Test
    void unop_to_toDblBifn() {
        assertEquals(
            3.0,
            Z
                .fuse(addTrailingZero, addStringsAsDouble)
                .apply("1.")
                .applyAsDouble("2.0")
        );
    }

    @Test
    void unop_to_toIntFn() {
        assertEquals(10, Z.fuse(addTrailingZero, stringToInt).applyAsInt("1"));
    }

    @Test
    void unop_to_toIntBifn() {
        assertEquals(
            12,
            Z.fuse(addTrailingZero, addStringsAsInt).apply("1").applyAsInt("2")
        );
    }

    @Test
    void unop_to_toLongFn() {
        assertEquals(
            10L,
            Z.fuse(addTrailingZero, stringToLong).applyAsLong("1")
        );
    }

    @Test
    void unop_to_toLongBifn() {
        assertEquals(
            12L,
            Z
                .fuse(addTrailingZero, addStringsAsLong)
                .apply("1")
                .applyAsLong("2")
        );
    }

    @Test
    void unop_to_pred() {
        assertFalse(Z.fuse(addQuestionMark, isEmpty).test(""));
    }

    @Test
    void unop_to_bipred() {
        assertTrue(
            Z.fuse(addQuestionMark, startsWith).apply("hello").test("hell")
        );
    }

    @Evil
    @Test
    void unop_to_cns() {
        synchronized (consumedStringA) {
            consumedStringA = "";

            Z.fuse(addQuestionMark, saveStringA).accept("hello");

            assertEquals("hello?", consumedStringA);
        }
    }

    @Evil
    @Test
    void unop_to_bicns() {
        synchronized (consumedStringB) {
            synchronized (consumedStringC) {
                consumedStringB = "";
                consumedStringC = "";

                Z
                    .fuse(addQuestionMark, saveStringsBandC)
                    .apply("greetings")
                    .accept("earthlings");

                assertEquals("greetings?", consumedStringB);
                assertEquals("earthlings", consumedStringC);
            }
        }
    }

    @Evil
    @Test
    void unop_to_objDblCns() {
        synchronized (consumedStringD) {
            synchronized (consumedDoubleB) {
                consumedStringD = "";
                consumedDoubleB = 0.0;

                Z
                    .fuse(addQuestionMark, saveStringDDoubleB)
                    .apply("five")
                    .accept(5.0);

                assertEquals("five?", consumedStringD);
                assertEquals(5.0, consumedDoubleB);
            }
        }
    }

    @Evil
    @Test
    void unop_to_objIntCns() {
        synchronized (consumedStringE) {
            synchronized (consumedIntB) {
                consumedStringE = "";
                consumedIntB = 0;

                Z.fuse(addQuestionMark, saveStringEIntB).apply("six").accept(6);

                assertEquals("six?", consumedStringE);
                assertEquals(6, consumedIntB);
            }
        }
    }

    @Evil
    @Test
    void unop_to_objLongFn() {
        synchronized (consumedStringF) {
            synchronized (consumedLongB) {
                consumedStringF = "";
                consumedLongB = 0L;

                Z
                    .fuse(addQuestionMark, saveStringFLongB)
                    .apply("seven")
                    .accept(7L);

                assertEquals("seven?", consumedStringF);
                assertEquals(7L, consumedLongB);
            }
        }
    }

    @Test
    void unop_to_unop() {
        assertEquals(
            "goodbye??",
            Z.fuse(addQuestionMark, addQuestionMark).apply("goodbye")
        );
    }

    @Test
    void unop_to_biop() {
        assertEquals(
            "same-ish",
            Z.fuse(addQuestionMark, relation).apply("yo man").apply("YO MAN?")
        );
    }
}
