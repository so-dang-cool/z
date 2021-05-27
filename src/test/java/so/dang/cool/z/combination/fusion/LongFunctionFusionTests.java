package so.dang.cool.z.combination.fusion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static so.dang.cool.z.combination.TestFunctions.*;

import org.junit.jupiter.api.Test;

import so.dang.cool.z.Z;
import so.dang.cool.z.annotation.Evil;

public class LongFunctionFusionTests {
    @Test
    void longFn() {
        assertEquals("3", longToString.apply(3L));
    }

    @Test
    void longFn_deep() {
        assertEquals("3", Z.with(longToString).resolve().apply(3L));
    }

    @Test
    void longFn_to_fn() {
        assertEquals("1!", Z.fuse(longToString, addExclamationMark).apply(1L));
    }

    @Test
    void longFn_to_fn_deep() {
        assertEquals("1!", Z.with(longToString).fuse(addExclamationMark).apply(1L));
    }

    @Test
    void longFn_to_fn_deeper() {
        assertEquals("1!", Z.with(longToString).fusing(addExclamationMark).resolve().apply(1L));
    }

    @Test
    void longFn_to_bifn() {
        assertEquals("1.0", Z.fuse(longToString, concat).apply(1L).apply(".0"));
    }

    @Test
    void longFn_to_toDblFn() {
        assertEquals(1.0, Z.fuse(longToString, stringToDouble).applyAsDouble(1L));
    }

    @Test
    void longFn_to_toDblBin() {
        assertEquals(3.0, Z.fuse(longToString, addStringsAsDouble).apply(1L).applyAsDouble("2.0"));
    }

    @Test
    void longFn_to_toInt() {
        assertEquals(1, Z.fuse(longToString, stringToInt).applyAsInt(1L));
    }

    @Test
    void longFn_to_toIntBifn() {
        assertEquals(3, Z.fuse(longToString, addStringsAsInt).apply(1L).applyAsInt("2"));
    }

    @Test
    void longFn_to_toLongFn() {
        assertEquals(1L, Z.fuse(longToString, stringToLong).applyAsLong(1L));
    }

    @Test
    void longFn_to_toLongBifn() {
        assertEquals(3L, Z.fuse(longToString, addStringsAsLong).apply(1L).applyAsLong("2"));
    }

    @Test
    void longFn_to_pred() {
        assertFalse(Z.fuse(longToString, isEmpty).test(1L));
    }

    @Test
    void longFn_to_bipred() {
        assertTrue(Z.fuse(longToString, startsWith).apply(11L).test("1"));
    }

    @Evil
    @Test
    void longFn_to_cns() {
        synchronized(consumedStringA) {
            consumedStringA = "";

            Z.fuse(longToString, saveStringA).accept(345L);

            assertEquals("345", consumedStringA);
        }
    }

    @Evil
    @Test
    void longFn_to_bicns() {
        synchronized(consumedStringB) {
            synchronized(consumedStringC) {
                consumedStringB = "";
                consumedStringC = "";

                Z.fuse(longToString, saveStringsBandC).apply(123L).accept("one twenty three");

                assertEquals("123", consumedStringB);
                assertEquals("one twenty three", consumedStringC);
            }
        }
    }

    @Evil
    @Test
    void longFn_to_objDblCns() {
        synchronized(consumedStringD) {
            synchronized(consumedDoubleB) {
                consumedStringD = "";
                consumedDoubleB = 0.0;

                Z.fuse(longToString, saveStringDDoubleB).apply(234L).accept(234.0);

                assertEquals("234", consumedStringD);
                assertEquals(234.0, consumedDoubleB);
            }
        }
    }

    @Evil
    @Test
    void longFn_to_objIntCns() {
        synchronized(consumedStringE) {
            synchronized(consumedIntB) {
                consumedStringE = "";
                consumedIntB = 0;

                Z.fuse(longToString, saveStringEIntB).apply(345L).accept(456);

                assertEquals("345", consumedStringE);
                assertEquals(456, consumedIntB);
            }
        }
    }

    @Evil
    @Test
    void longFn_to_objLongCns() {
        synchronized(consumedStringF) {
            synchronized(consumedLongB) {
                consumedStringF = "";
                consumedLongB = 0L;

                Z.fuse(longToString, saveStringFLongB).apply(567L).accept(678L);

                assertEquals("567", consumedStringF);
                assertEquals(678L, consumedLongB);
            }
        }
    }

    @Test
    void longFn_to_unop() {
        assertEquals("100?", Z.fuse(longToString, addQuestionMark).apply(100L));
    }

    @Test
    void longFn_to_biop() {
        assertEquals("same-ish", Z.fuse(longToString, relation).apply(789L).apply("789"));
    }
}
