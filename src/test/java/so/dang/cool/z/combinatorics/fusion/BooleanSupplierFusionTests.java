package so.dang.cool.z.combinatorics.fusion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static so.dang.cool.z.combinatorics.TestFunctions.*;

import org.junit.jupiter.api.Test;

import so.dang.cool.z.Z;
import so.dang.cool.z.annotation.Evil;

public class BooleanSupplierFusionTests {
    @Test
    void boolSup_to_fn() {
        assertEquals("true", Z.fuse(getBooleanTrue, booleanToString).get());
    }

    @Test
    void boolSup_to_bifn() {
        assertEquals("HI", Z.fuse(getBooleanTrue, maybeToUpper).apply("hi"));
    }

    @Test
    void boolSup_to_toDblFn() {
        assertEquals(1.0, Z.fuse(getBooleanTrue, maybeOneAsDouble).getAsDouble());
    }

    @Test
    void boolSup_to_toDblBifn() {
        assertEquals(3.0, Z.fuse(getBooleanTrue, maybeAddOneToStringAsDouble).applyAsDouble("2.0"));
    }

    @Test
    void boolSup_to_toIntFn() {
        assertEquals(2, Z.fuse(getBooleanTrue, maybeTwoAsInt).getAsInt());
    }

    @Test
    void boolSup_to_toIntBifn() {
        assertEquals(4, Z.fuse(getBooleanTrue, maybeAddTwoToStringAsInt).applyAsInt("2"));
    }

    @Test
    void boolSup_to_toLongFn() {
        assertEquals(3L, Z.fuse(getBooleanTrue, maybeThreeAsLong).getAsLong());
    }

    @Test
    void boolSup_to_toLongBifn() {
        assertEquals(7L, Z.fuse(getBooleanTrue, maybeAddThreeToStringAsLong).applyAsLong("4"));
    }

    @Test
    void boolSup_to_pred() {
        assertFalse(Z.fuse(getBooleanTrue, not).getAsBoolean());
    }

    @Test
    void boolSup_to_bipred() {
        assertTrue(Z.fuse(getBooleanTrue, maybeNotFromString).test("false"));
    }

    @Evil
    @Test
    void boolSup_to_cns() {
        synchronized(consumedBooleanA) {
            consumedBooleanA = false;

            Z.fuse(getBooleanTrue, saveBooleanA).run();

            assertTrue(consumedBooleanA);
        }
    }

    @Evil
    @Test
    void boolSup_to_bicns() {
        synchronized(consumedBooleanB) {
            synchronized(consumedStringG) {
                consumedBooleanB = false;
                consumedStringG = "";

                Z.fuse(getBooleanTrue, saveBooleanBAndStringG).accept("z");

                assertTrue(consumedBooleanB);
                assertEquals("z", consumedStringG);
            }
        }
    }

    @Evil
    @Test
    void boolSup_to_objDblCns() {
        synchronized(consumedBooleanC) {
            synchronized(consumedDoubleC) {
                consumedBooleanC = false;
                consumedDoubleC = 0.0;

                Z.fuse(getBooleanTrue, saveBooleanCDoubleC).accept(5.0);

                assertTrue(consumedBooleanC);
                assertEquals(5.0, consumedDoubleC);
            }
        }
    }

    @Evil
    @Test
    void boolSup_to_objIntCns() {
        synchronized(consumedBooleanD) {
            synchronized(consumedIntC) {
                consumedBooleanD = false;
                consumedIntC = 0;

                Z.fuse(getBooleanTrue, saveBooleanDIntC).accept(6);

                assertTrue(consumedBooleanD);
                assertEquals(6, consumedIntC);
            }
        }
    }

    @Evil
    @Test
    void boolSup_to_objLongFn() {
        synchronized(consumedBooleanE) {
            synchronized(consumedLongC) {
                consumedBooleanE = false;
                consumedLongC = 0L;

                Z.fuse(getBooleanTrue, saveBooleanELongC).accept(7L);

                assertTrue(consumedBooleanE);
                assertEquals(7L, consumedLongC);
            }
        }
    }

    @Test
    void boolSup_to_toUnop() {
        assertTrue(Z.fuse(getBooleanTrue, booleanId).getAsBoolean());
    }

    @Test
    void boolSup_to_toBiop() {
        assertTrue(Z.fuse(getBooleanTrue, maybeNot).applyAsBoolean(false));
    }
}
