package so.dang.cool.z.combination.fusion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static so.dang.cool.z.combination.TestFunctions.*;

import org.junit.jupiter.api.Test;
import so.dang.cool.z.Z;
import so.dang.cool.z.annotation.Evil;

public class IntFunctionFusionTests {

    @Test
    void intFn() {
        assertEquals("1", intToString.apply(1));
    }

    @Test
    void intFn_deep() {
        assertEquals("1", Z.fuse(intToString).apply(1));
    }

    @Test
    void intFn_to_fn() {
        assertEquals(
            "1!",
            Z.fuse(intToString).fuse(addExclamationMark).apply(1)
        );
    }

    @Test
    void intFn_to_bifn() {
        assertEquals(
            "1.0",
            Z.fuse(intToString).fuse(concat).apply(1).apply(".0")
        );
    }

    @Test
    void intFn_to_toDblFn() {
        assertEquals(
            1,
            Z.fuse(intToString).fuse(stringToDouble).applyAsDouble(1)
        );
    }

    @Test
    void intFn_to_toDblBifn() {
        assertEquals(
            3,
            Z
                .fuse(intToString)
                .fuse(addStringsAsDouble)
                .apply(1)
                .applyAsDouble("2")
        );
    }

    @Test
    void intFn_to_toIntFn() {
        assertEquals(1, Z.fuse(intToString).fuse(stringToInt).applyAsInt(1));
    }

    @Test
    void intFn_to_toIntBifn() {
        assertEquals(
            3,
            Z.fuse(intToString).fuse(addStringsAsInt).apply(1).applyAsInt("2")
        );
    }

    @Test
    void intFn_to_toLong() {
        assertEquals(1L, Z.fuse(intToString).fuse(stringToLong).applyAsLong(1));
    }

    @Test
    void intFn_to_toLongBifn() {
        assertEquals(
            3L,
            Z.fuse(intToString).fuse(addStringsAsLong).apply(1).applyAsLong("2")
        );
    }

    @Test
    void intFn_to_pred() {
        assertFalse(Z.fuse(intToString).fuse(isEmpty).test(1));
    }

    @Test
    void intFn_to_bipred() {
        assertTrue(Z.fuse(intToString).fuse(startsWith).apply(1).test("1"));
    }

    @Evil
    @Test
    void intFn_to_cns() {
        synchronized (consumedStringA) {
            consumedStringA = "";

            Z.fuse(intToString).fuse(saveStringA).accept(2);

            assertEquals("2", consumedStringA);
        }
    }

    @Evil
    @Test
    void intFn_to_bicns() {
        synchronized (consumedStringB) {
            synchronized (consumedStringC) {
                consumedStringB = "";
                consumedStringC = "";

                Z
                    .fuse(intToString)
                    .fuse(saveStringsBandC)
                    .apply(3)
                    .accept("two and a half");

                assertEquals("3", consumedStringB);
                assertEquals("two and a half", consumedStringC);
            }
        }
    }

    @Evil
    @Test
    void intFn_to_objDblCns() {
        synchronized (consumedStringD) {
            synchronized (consumedDoubleB) {
                consumedStringD = "";
                consumedDoubleB = 0.0;

                Z
                    .fuse(intToString)
                    .fuse(saveStringDDoubleB)
                    .apply(4)
                    .accept(5.0);

                assertEquals("4", consumedStringD);
                assertEquals(5.0, consumedDoubleB);
            }
        }
    }

    @Evil
    @Test
    void intFn_to_objIntCns() {
        synchronized (consumedStringE) {
            synchronized (consumedIntB) {
                consumedStringE = "";
                consumedIntB = 0;

                Z.fuse(intToString).fuse(saveStringEIntB).apply(8).accept(8);

                assertEquals("8", consumedStringE);
                assertEquals(8, consumedIntB);
            }
        }
    }

    @Evil
    @Test
    void intFn_to_objLongCns() {
        synchronized (consumedStringF) {
            synchronized (consumedLongB) {
                consumedStringF = "";
                consumedLongB = 0L;

                Z.fuse(intToString).fuse(saveStringFLongB).apply(6).accept(6L);

                assertEquals("6", consumedStringF);
                assertEquals(6L, consumedLongB);
            }
        }
    }

    @Test
    void intFn_to_unop() {
        assertEquals(
            "10?",
            Z.fuse(intToString).fuse(addQuestionMark).apply(10)
        );
    }

    @Test
    void intFn_to_biop() {
        assertEquals(
            "same-ish",
            Z.fuse(intToString).fuse(relation).apply(11).apply("11")
        );
    }
}
