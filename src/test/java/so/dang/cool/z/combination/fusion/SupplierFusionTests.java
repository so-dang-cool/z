package so.dang.cool.z.combination.fusion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static so.dang.cool.z.combination.TestFunctions.*;

import org.junit.jupiter.api.Test;

import so.dang.cool.z.Z;
import so.dang.cool.z.annotation.Evil;

public class SupplierFusionTests {
    @Test
    void sup_to_fn() {
        assertEquals(suppliedString.toLowerCase(), Z.fuse(getString, toLower).get());
    }

    @Test
    void sup_to_bifn() {
        assertEquals("Z!", Z.fuse(getString, concat).apply("!"));
    }

    @Test
    void sup_to_toDblFn() {
        assertEquals(1.0, Z.fuse(() -> "1.0", stringToDouble).getAsDouble());
    }

    @Test
    void sup_to_toDblBifn() {
        assertEquals(2.0, Z.fuse(() -> "1.0", addStringsAsDouble).applyAsDouble("1.0"));
    }

    @Test
    void sup_to_toIntFn() {
        assertEquals(1, Z.fuse(() -> "1", stringToInt).getAsInt());
    }

    @Test
    void sup_to_toIntBifn() {
        assertEquals(2, Z.fuse(() -> "1", addStringsAsInt).applyAsInt("1"));
    }

    @Test
    void sup_to_toLongFn() {
        assertEquals(1L, Z.fuse(() -> "1", stringToLong).getAsLong());
    }

    @Test
    void sup_to_toLongBifn() {
        assertEquals(2L, Z.fuse(() -> "1", addStringsAsLong).applyAsLong("1"));
    }

    @Test
    void sup_to_pred() {
        assertFalse(Z.fuse(getString, isEmpty).getAsBoolean());
    }

    @Test
    void sup_to_bipred() {
        assertTrue(Z.fuse(getString, startsWith).test("Z"));
    }

    @Evil
    @Test
    void sup_to_cns() {
        synchronized(consumedStringA) {
            consumedStringA = "";

            Z.fuse(getString, saveStringA).run();

            assertEquals(suppliedString, consumedStringA);
        }
    }

    @Evil
    @Test
    void sup_to_bicns() {
        synchronized(consumedStringB) {
            synchronized(consumedStringC) {
                consumedStringB = "";
                consumedStringC = "";

                Z.fuse(getString, saveStringsBandC).accept("z");

                assertEquals(suppliedString, consumedStringB);
                assertEquals("z", consumedStringC);
            }
        }
    }

    @Evil
    @Test
    void sup_to_objDblCns() {
        synchronized(consumedStringD) {
            synchronized(consumedDoubleB) {
                consumedStringD = "";
                consumedDoubleB = 0.0;

                Z.fuse(getString, saveStringDDoubleB).accept(5.0);

                assertEquals(suppliedString, consumedStringD);
                assertEquals(5.0, consumedDoubleB);
            }
        }
    }

    @Evil
    @Test
    void sup_to_objIntCns() {
        synchronized(consumedStringE) {
            synchronized(consumedIntB) {
                consumedStringE = "";
                consumedIntB = 0;

                Z.fuse(getString, saveStringEIntB).accept(6);

                assertEquals(suppliedString, consumedStringE);
                assertEquals(6, consumedIntB);
            }
        }
    }

    @Evil
    @Test
    void sup_to_objLongFn() {
        synchronized(consumedStringF) {
            synchronized(consumedLongB) {
                consumedStringF = "";
                consumedLongB = 0L;

                Z.fuse(getString, saveStringFLongB).accept(7L);

                assertEquals(suppliedString, consumedStringF);
                assertEquals(7L, consumedLongB);
            }
        }
    }

    @Test
    void sup_to_toUnop() {
        assertEquals("Z?", Z.fuse(getString, addQuestionMark).get());
    }

    @Test
    void sup_to_toBiop() {
        assertEquals("same-ish", Z.fuse(getString, relation).apply("z"));
    }
}
