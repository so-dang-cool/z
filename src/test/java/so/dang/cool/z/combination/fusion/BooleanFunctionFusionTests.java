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
    void boolFn_to_fn() {
        assertEquals("true!", Z.fuse(booleanToString, addExclamationMark).apply(true));
    }

    @Test
    void boolFn_to_bifn() {
        assertEquals("true...", Z.fuse(booleanToString, concat).apply(true).apply("..."));
    }

    @Test
    void boolFn_to_toDblFn() {
        assertEquals(1.0, Z.fuse(maybeOneAsString, stringToDouble).applyAsDouble(true));
    }

    @Test
    void boolFn_to_toDblBin() {
        assertEquals(3.0, Z.fuse(maybeOneAsString, addStringsAsDouble).apply(true).applyAsDouble("2.0"));
    }

    @Test
    void boolFn_to_toInt() {
        assertEquals(1, Z.fuse(maybeOneAsString, stringToInt).applyAsInt(true));
    }

    @Test
    void boolFn_to_toIntBifn() {
        assertEquals(3, Z.fuse(maybeOneAsString, addStringsAsInt).apply(true).applyAsInt("2"));
    }

    @Test
    void boolFn_to_toLongFn() {
        assertEquals(1L, Z.fuse(maybeOneAsString, stringToLong).applyAsLong(true));
    }

    @Test
    void boolFn_to_toLongBifn() {
        assertEquals(3L, Z.fuse(maybeOneAsString, addStringsAsLong).apply(true).applyAsLong("2"));
    }

    @Test
    void boolFn_to_pred() {
        assertFalse(Z.fuse(booleanToString, isEmpty).applyAsBoolean(true));
    }

    @Test
    void boolFn_to_bipred() {
        assertTrue(Z.fuse(booleanToString, startsWith).apply(true).test("t"));
    }

    @Evil
    @Test
    void boolFn_to_cns() {
        synchronized(consumedStringA) {
            consumedStringA = "";

            Z.fuse(booleanToString, saveStringA).accept(true);

            assertEquals("true", consumedStringA);
        }
    }

    @Evil
    @Test
    void boolFn_to_bicns() {
        synchronized(consumedStringB) {
            synchronized(consumedStringC) {
                consumedStringB = "";
                consumedStringC = "";

                Z.fuse(booleanToString, saveStringsBandC).apply(true).accept("true and a half");

                assertEquals("true", consumedStringB);
                assertEquals("true and a half", consumedStringC);
            }
        }
    }

    @Evil
    @Test
    void boolFn_to_objDblCns() {
        synchronized(consumedStringD) {
            synchronized(consumedDoubleB) {
                consumedStringD = "";
                consumedDoubleB = 0.0;

                Z.fuse(booleanToString, saveStringDDoubleB).apply(true).accept(4.5);

                assertEquals("true", consumedStringD);
                assertEquals(4.5, consumedDoubleB);
            }
        }
    }

    @Evil
    @Test
    void boolFn_to_objIntCns() {
        synchronized(consumedStringE) {
            synchronized(consumedIntB) {
                consumedStringE = "";
                consumedIntB = 0;

                Z.fuse(booleanToString, saveStringEIntB).apply(true).accept(6);

                assertEquals("true", consumedStringE);
                assertEquals(6, consumedIntB);
            }
        }
    }

    @Evil
    @Test
    void boolFn_to_objLongCns() {
        synchronized(consumedStringF) {
            synchronized(consumedLongB) {
                consumedStringF = "";
                consumedLongB = 0L;

                Z.fuse(booleanToString, saveStringFLongB).apply(true).accept(8);

                assertEquals("true", consumedStringF);
                assertEquals(8, consumedLongB);
            }
        }
    }

    @Test
    void boolFn_to_unop() {
        assertEquals("true?", Z.fuse(booleanToString, addQuestionMark).apply(true));
    }

    @Test
    void boolFn_to_biop() {
        assertEquals("same-ish", Z.fuse(booleanToString, relation).apply(true).apply("TRUE"));
    }
}
