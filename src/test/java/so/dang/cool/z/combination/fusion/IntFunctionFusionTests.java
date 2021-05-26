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
    void intFn_to_fn() {
        assertEquals("1!", Z.fuse(intToString, addExclamationMark).apply(1));
    }

    @Test
    void intFn_to_fn_deep() {
        assertEquals("1!", Z.with(intToString).fuse(addExclamationMark).apply(1));
    }

    @Test
    void intFn_to_fn_deeper() {
        assertEquals("1!", Z.with(intToString).fusing(addExclamationMark).resolve().apply(1));
    }

    @Test
    void intFn_to_bifn() {
        assertEquals("1.0", Z.fuse(intToString, concat).apply(1).apply(".0"));
    }

    @Test
    void intFn_to_toDblFn() {
        assertEquals(1.0, Z.fuse(intToString, stringToDouble).applyAsDouble(1));
    }

    @Test
    void intFn_to_toDblBin() {
        assertEquals(3.0, Z.fuse(intToString, addStringsAsDouble).apply(1).applyAsDouble("2.0"));
    }

    @Test
    void intFn_to_toInt() {
        assertEquals(1, Z.fuse(intToString, stringToInt).applyAsInt(1));
    }

    @Test
    void intFn_to_toIntBifn() {
        assertEquals(3, Z.fuse(intToString, addStringsAsInt).apply(1).applyAsInt("2"));
    }

    @Test
    void intFn_to_toLongFn() {
        assertEquals(1L, Z.fuse(intToString, stringToLong).applyAsLong(1));
    }

    @Test
    void intFn_to_toLongBifn() {
        assertEquals(3L, Z.fuse(intToString, addStringsAsLong).apply(1).applyAsLong("2"));
    }

    @Test
    void intFn_to_pred() {
        assertFalse(Z.fuse(intToString, isEmpty).test(1));
    }

    @Test
    void intFn_to_bipred() {
        assertTrue(Z.fuse(intToString, startsWith).apply(11).test("1"));
    }

    @Evil
    @Test
    void intFn_to_cns() {
        synchronized(consumedStringA) {
            consumedStringA = "";

            Z.fuse(intToString, saveStringA).accept(123);

            assertEquals("123", consumedStringA);
        }
    }

    @Evil
    @Test
    void intFn_to_bicns() {
        synchronized(consumedStringB) {
            synchronized(consumedStringC) {
                consumedStringB = "";
                consumedStringC = "";

                Z.fuse(intToString, saveStringsBandC).apply(34).accept("thirty four");

                assertEquals("34", consumedStringB);
                assertEquals("thirty four", consumedStringC);
            }
        }
    }

    @Evil
    @Test
    void intFn_to_objDblCns() {
        synchronized(consumedStringD) {
            synchronized(consumedDoubleB) {
                consumedStringD = "";
                consumedDoubleB = 0.0;

                Z.fuse(intToString, saveStringDDoubleB).apply(45).accept(4.5);

                assertEquals("45", consumedStringD);
                assertEquals(4.5, consumedDoubleB);
            }
        }
    }

    @Evil
    @Test
    void intFn_to_objIntCns() {
        synchronized(consumedStringE) {
            synchronized(consumedIntB) {
                consumedStringE = "";
                consumedIntB = 0;

                Z.fuse(intToString, saveStringEIntB).apply(56).accept(67);

                assertEquals("56", consumedStringE);
                assertEquals(67, consumedIntB);
            }
        }
    }

    @Evil
    @Test
    void intFn_to_objLongCns() {
        synchronized(consumedStringF) {
            synchronized(consumedLongB) {
                consumedStringF = "";
                consumedLongB = 0L;

                Z.fuse(intToString, saveStringFLongB).apply(78).accept(89L);

                assertEquals("78", consumedStringF);
                assertEquals(89L, consumedLongB);
            }
        }
    }

    @Test
    void intFn_to_unop() {
        assertEquals("10?", Z.fuse(intToString, addQuestionMark).apply(10));
    }

    @Test
    void intFn_to_biop() {
        assertEquals("same-ish", Z.fuse(intToString, relation).apply(234).apply("234"));
    }
}
