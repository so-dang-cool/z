package so.dang.cool.z.combination.fusion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static so.dang.cool.z.combination.TestFunctions.*;

import org.junit.jupiter.api.Test;
import so.dang.cool.z.Z;
import so.dang.cool.z.annotation.Evil;

public class DoubleFunctionFusionTests {

    @Test
    void dblFn() {
        assertEquals("1.0", doubleToString.apply(1.0));
    }

    @Test
    void dblFn_deep() {
        assertEquals(
            "1.0!",
            Z.fuse(doubleToString).fuse(addExclamationMark).apply(1.0)
        );
    }

    @Test
    void dblFn_to_bifn() {
        assertEquals(
            "1.0.0",
            Z.fuse(doubleToString).fuse(concat).apply(1.0).apply(".0")
        );
    }

    @Test
    void dblFn_to_toDblFn() {
        assertEquals(
            1.0,
            Z.fuse(doubleToString).fuse(stringToDouble).applyAsDouble(1.0)
        );
    }

    @Test
    void dblFn_to_toDblBifn() {
        assertEquals(
            3.0,
            Z
                .fuse(doubleToString)
                .fuse(addStringsAsDouble)
                .apply(1.0)
                .applyAsDouble("2.0")
        );
    }

    @Test
    void dblFn_to_toInt() {
        assertEquals(
            1,
            Z.fuse(doubleFloorToString).fuse(stringToInt).applyAsInt(1.0)
        );
    }

    @Test
    void dblFn_to_toIntBifn() {
        assertEquals(
            3,
            Z
                .fuse(doubleFloorToString)
                .fuse(addStringsAsInt)
                .apply(1.0)
                .applyAsInt("2")
        );
    }

    @Test
    void dblFn_to_toLongFn() {
        assertEquals(
            1L,
            Z.fuse(doubleFloorToString).fuse(stringToLong).applyAsLong(1.0)
        );
    }

    @Test
    void dblFn_to_toLongBifn() {
        assertEquals(
            3L,
            Z
                .fuse(doubleFloorToString)
                .fuse(addStringsAsLong)
                .apply(1.0)
                .applyAsLong("2")
        );
    }

    @Test
    void dblFn_to_pred() {
        assertFalse(Z.fuse(doubleToString).fuse(isEmpty).test(1.0));
        assertTrue(Z.fuse(doubleToString).fuse(isNotEmpty).test(1.0));
        assertTrue(Z.fuse(doubleToString).fuse("1.0"::equals).test(1.0));
        assertFalse(Z.fuse(doubleToString).fuse("9999.0"::equals).test(1.0));
    }

    @Test
    void dblFn_to_bipred() {
        assertTrue(
            Z.fuse(doubleToString).fuse(doesStartWith).apply(1.0).test("1")
        );
        assertFalse(
            Z.fuse(doubleToString).fuse(doesStartWith).apply(9999.0).test("1")
        );
    }

    @Evil
    @Test
    void dblFn_to_cns() {
        synchronized (consumedStringA) {
            consumedStringA = "";

            Z.fuse(doubleToString).fuse(saveStringA).accept(1.5);

            assertEquals("1.5", consumedStringA);
        }
    }

    @Evil
    @Test
    void dblFn_to_bicns() {
        synchronized (consumedStringB) {
            synchronized (consumedStringC) {
                consumedStringB = "";
                consumedStringC = "";

                Z
                    .fuse(doubleToString)
                    .fuse(saveStringsBandC)
                    .apply(2.5)
                    .accept("two and a half");

                assertEquals("2.5", consumedStringB);
                assertEquals("two and a half", consumedStringC);
            }
        }
    }

    @Evil
    @Test
    void dblFn_to_objDblCns() {
        synchronized (consumedStringD) {
            synchronized (consumedDoubleB) {
                consumedStringD = "";
                consumedDoubleB = 0.0;

                Z
                    .fuse(doubleToString)
                    .fuse(saveStringDDoubleB)
                    .apply(3.5)
                    .accept(4.5);

                assertEquals("3.5", consumedStringD);
                assertEquals(4.5, consumedDoubleB);
            }
        }
    }

    @Evil
    @Test
    void dblFn_to_objIntCns() {
        synchronized (consumedStringE) {
            synchronized (consumedIntB) {
                consumedStringE = "";
                consumedIntB = 0;

                Z
                    .fuse(doubleToString)
                    .fuse(saveStringEIntB)
                    .apply(5.5)
                    .accept(6);

                assertEquals("5.5", consumedStringE);
                assertEquals(6, consumedIntB);
            }
        }
    }

    @Evil
    @Test
    void dblFn_to_objLongCns() {
        synchronized (consumedStringF) {
            synchronized (consumedLongB) {
                consumedStringF = "";
                consumedLongB = 0L;

                Z
                    .fuse(doubleToString)
                    .fuse(saveStringFLongB)
                    .apply(7.5)
                    .accept(8);

                assertEquals("7.5", consumedStringF);
                assertEquals(8, consumedLongB);
            }
        }
    }

    @Test
    void dblFn_to_unop() {
        assertEquals(
            "9.5?",
            Z.fuse(doubleToString).fuse(addQuestionMark).apply(9.5)
        );
    }

    @Test
    void dblFn_to_biop() {
        assertEquals(
            "same-ish",
            Z.fuse(doubleToString).fuse(relation).apply(10.5).apply("10.5")
        );
    }
}
