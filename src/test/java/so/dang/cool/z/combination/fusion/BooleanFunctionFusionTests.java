package so.dang.cool.z.combination.fusion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static so.dang.cool.z.combination.TestFunctions.*;

import org.junit.jupiter.api.Test;
import so.dang.cool.z.Z;
import so.dang.cool.z.annotation.Evil;

public class BooleanFunctionFusionTests {

    @Test
    void boolFn() {
        assertEquals("true", booleanToString.apply(true));
    }

    @Test
    void boolFn_deep() {
        assertEquals("true", Z.fuse(booleanToString).apply(true));
    }

    @Test
    void boolFn_to_fn() {
        assertEquals(
            "true!",
            Z.fuse(booleanToString).fuse(addExclamationMark).apply(true)
        );
    }

    @Test
    void boolFn_to_bifn() {
        assertEquals(
            "true...",
            Z.fuse(booleanToString).fuse(concat).apply(true).apply("...")
        );
    }

    @Test
    void boolFn_to_toDblFn() {
        assertEquals(
            1.0,
            Z.fuse(maybeOneAsString).fuse(stringToDouble).applyAsDouble(true)
        );
    }

    @Test
    void boolFn_to_toDblBin() {
        assertEquals(
            3.0,
            Z
                .fuse(maybeOneAsString)
                .fuse(addStringsAsDouble)
                .apply(true)
                .applyAsDouble("2.0")
        );
    }

    @Test
    void boolFn_to_toInt() {
        assertEquals(
            1,
            Z.fuse(maybeOneAsString).fuse(stringToInt).applyAsInt(true)
        );
    }

    @Test
    void boolFn_to_toIntBifn() {
        assertEquals(
            3,
            Z
                .fuse(maybeOneAsString)
                .fuse(addStringsAsInt)
                .apply(true)
                .applyAsInt("2")
        );
    }

    @Test
    void boolFn_to_toLongFn() {
        assertEquals(
            1L,
            Z.fuse(maybeOneAsString).fuse(stringToLong).applyAsLong(true)
        );
    }

    @Test
    void boolFn_to_toLongBifn() {
        assertEquals(
            3L,
            Z
                .fuse(maybeOneAsString)
                .fuse(addStringsAsLong)
                .apply(true)
                .applyAsLong("2")
        );
    }

    @Test
    void boolFn_to_pred() {
        assertFalse(Z.fuse(booleanToString).fuse(isEmpty).test(true));
    }

    @Test
    void boolFn_to_bipred() {
        assertTrue(
            Z.fuse(booleanToString).fuse(startsWith).apply(true).test("t")
        );
    }

    @Evil
    @Test
    void boolFn_to_cns() {
        synchronized (consumedStringA) {
            consumedStringA = "";

            Z.fuse(booleanToString).fuse(saveStringA).accept(true);

            assertEquals("true", consumedStringA);
        }
    }

    @Evil
    @Test
    void boolFn_to_bicns() {
        synchronized (consumedStringB) {
            synchronized (consumedStringC) {
                consumedStringB = "";
                consumedStringC = "";

                Z
                    .fuse(booleanToString)
                    .fuse(saveStringsBandC)
                    .apply(true)
                    .accept("true and a half");

                assertEquals("true", consumedStringB);
                assertEquals("true and a half", consumedStringC);
            }
        }
    }

    @Evil
    @Test
    void boolFn_to_objDblCns() {
        synchronized (consumedStringD) {
            synchronized (consumedDoubleB) {
                consumedStringD = "";
                consumedDoubleB = 0.0;

                Z
                    .fuse(booleanToString)
                    .fuse(saveStringDDoubleB)
                    .apply(true)
                    .accept(4.5);

                assertEquals("true", consumedStringD);
                assertEquals(4.5, consumedDoubleB);
            }
        }
    }

    @Evil
    @Test
    void boolFn_to_objIntCns() {
        synchronized (consumedStringE) {
            synchronized (consumedIntB) {
                consumedStringE = "";
                consumedIntB = 0;

                Z
                    .fuse(booleanToString)
                    .fuse(saveStringEIntB)
                    .apply(true)
                    .accept(6);

                assertEquals("true", consumedStringE);
                assertEquals(6, consumedIntB);
            }
        }
    }

    @Evil
    @Test
    void boolFn_to_objLongCns() {
        synchronized (consumedStringF) {
            synchronized (consumedLongB) {
                consumedStringF = "";
                consumedLongB = 0L;

                Z
                    .fuse(booleanToString)
                    .fuse(saveStringFLongB)
                    .apply(true)
                    .accept(8L);

                assertEquals("true", consumedStringF);
                assertEquals(8L, consumedLongB);
            }
        }
    }

    @Test
    void boolFn_to_unop() {
        assertEquals(
            "true?",
            Z.fuse(booleanToString).fuse(addQuestionMark).apply(true)
        );
    }

    @Test
    void boolFn_to_biop() {
        assertEquals(
            "same-ish",
            Z.fuse(booleanToString).fuse(relation).apply(true).apply("TRUE")
        );
    }
}
