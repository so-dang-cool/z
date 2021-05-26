package so.dang.cool.z.combination.fusion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static so.dang.cool.z.combination.TestFunctions.*;

import org.junit.jupiter.api.Test;

import so.dang.cool.z.Z;
import so.dang.cool.z.annotation.Evil;

public class DoublePredicateFusionTests {
    @Test
    void dblPred_to_bifn() {
        assertEquals("HI", Z.fuse(isDoubleOne, maybeToUpper).apply(1.0).apply("hi"));
    }

    @Test
    void dblPred_to_boolFn() {
        assertEquals("true", Z.fuse(isDoubleOne, booleanToString).apply(1.0));
    }

    @Test
    void dblPred_to_boolFn_deep() {
        assertEquals("true", Z.with(isDoubleOne).fuse(booleanToString).apply(1.0));
    }

    @Test
    void dblPred_to_boolFn_deeper() {
        assertEquals("true", Z.with(isDoubleOne).fusing(booleanToString).resolve().apply(1.0));
    }

    @Test
    void dblPred_to_toDblFn() {
        assertEquals(1.0, Z.fuse(isDoubleOne, maybeOneAsDouble).applyAsDouble(1.0));
    }

    @Test
    void dblPred_to_toDblBifn() {
        assertEquals(2.0, Z.fuse(isDoubleOne, maybeAddOneToStringAsDouble).apply(1.0).applyAsDouble("1.0"));
    }

    @Test
    void dblPred_to_toIntFn() {
        assertEquals(2, Z.fuse(isDoubleOne, maybeTwoAsInt).applyAsInt(1.0));
    }

    @Test
    void dblPred_to_toIntBifn() {
        assertEquals(3, Z.fuse(isDoubleOne, maybeAddTwoToStringAsInt).apply(1.0).applyAsInt("1"));
    }

    @Test
    void dblPred_to_toLongFn() {
        assertEquals(3L, Z.fuse(isDoubleOne, maybeThreeAsLong).applyAsLong(1.0));
    }

    @Test
    void dblPred_to_toLongBifn() {
        assertEquals(4L, Z.fuse(isDoubleOne, maybeAddThreeToStringAsLong).apply(1.0).applyAsLong("1"));
    }

    @Test
    void dblPred_to_pred() {
        assertFalse(Z.fuse(isDoubleOne, not).test(1.0));
    }

    @Test
    void dblPred_to_bipred() {
        assertTrue(Z.fuse(isDoubleOne, maybeNotFromString).apply(1.0).test("false"));
    }

    @Evil
    @Test
    void dblPred_to_cns() {
        synchronized(consumedBooleanA) {
            consumedBooleanA = false;

            Z.fuse(isDoubleOne, saveBooleanA).accept(1.0);

            assertTrue(consumedBooleanA);
        }
    }

    @Evil
    @Test
    void dblPred_to_bicns() {
        synchronized(consumedBooleanB) {
            synchronized(consumedStringG) {
                consumedBooleanB = false;
                consumedStringG = "";

                Z.fuse(isDoubleOne, saveBooleanBAndStringG).apply(1.0).accept("yolo");

                assertTrue(consumedBooleanB);
                assertEquals("yolo", consumedStringG);
            }
        }
    }

    @Evil
    @Test
    void dblPred_to_objDblCns() {
        synchronized(consumedBooleanC) {
            synchronized(consumedDoubleC) {
                consumedBooleanC = false;
                consumedDoubleC = 0.0;

                Z.fuse(isDoubleOne, saveBooleanCDoubleC).apply(1.0).accept(5.0);

                assertTrue(consumedBooleanC);
                assertEquals(5.0, consumedDoubleC);
            }
        }
    }

    @Evil
    @Test
    void dblPred_to_objIntCns() {
        synchronized(consumedBooleanD) {
            synchronized(consumedIntC) {
                consumedBooleanD = false;
                consumedIntC = 0;

                Z.fuse(isDoubleOne, saveBooleanDIntC).apply(1.0).accept(6);

                assertTrue(consumedBooleanD);
                assertEquals(6, consumedIntC);
            }
        }
    }

    @Evil
    @Test
    void dblPred_to_objLongFn() {
        synchronized(consumedBooleanE) {
            synchronized(consumedLongC) {
                consumedBooleanE = false;
                consumedLongC = 0L;

                Z.fuse(isDoubleOne, saveBooleanELongC).apply(1.0).accept(7L);

                assertTrue(consumedBooleanE);
                assertEquals(7L, consumedLongC);
            }
        }
    }

    @Test
    void dblPred_to_toUnop() {
        assertTrue(Z.fuse(isDoubleOne, booleanId).test(1.0));
    }

    @Test
    void dblPred_to_toBiop() {
        assertTrue(Z.fuse(isDoubleOne, maybeNot).apply(1.0).test(false));
    }
}
