package so.dang.cool.z.combinatorics.fusion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static so.dang.cool.z.combinatorics.TestFunctions.*;

import org.junit.jupiter.api.Test;

import so.dang.cool.z.Z;
import so.dang.cool.z.annotation.Evil;

public class PredicateFusionTests {
    @Test
    void pred_to_fn() {
        assertEquals("true", Z.fuse(isEmpty, booleanToString).apply(""));
    }

    @Test
    void pred_to_bifn() {
        assertEquals("HI", Z.fuse(isEmpty, maybeToUpper).apply("").apply("hi"));
    }

    @Test
    void pred_to_toDblFn() {
        assertEquals(1.0, Z.fuse(isEmpty, maybeOneAsDouble).applyAsDouble(""));
    }

    @Test
    void pred_to_toDblBifn() {
        assertEquals(2.0, Z.fuse(isEmpty, maybeAddOneToStringAsDouble).apply("").applyAsDouble("1.0"));
    }

    @Test
    void pred_to_toIntFn() {
        assertEquals(2, Z.fuse(isEmpty, maybeTwoAsInt).applyAsInt(""));
    }

    @Test
    void pred_to_toIntBifn() {
        assertEquals(3, Z.fuse(isEmpty, maybeAddTwoToStringAsInt).apply("").applyAsInt("1"));
    }

    @Test
    void pred_to_toLongFn() {
        assertEquals(3L, Z.fuse(isEmpty, maybeThreeAsLong).applyAsLong(""));
    }

    @Test
    void pred_to_toLongBifn() {
        assertEquals(4L, Z.fuse(isEmpty, maybeAddThreeToStringAsLong).apply("").applyAsLong("1"));
    }

    @Test
    void pred_to_pred() {
        assertFalse(Z.fuse(isEmpty, not).test(""));
    }

    @Test
    void pred_to_bipred() {
        assertTrue(Z.fuse(isEmpty, maybeNotFromString).apply("").test("false"));
    }

    @Evil
    @Test
    void pred_to_cns() {
        synchronized(consumedBooleanA) {
            consumedBooleanA = false;

            Z.fuse(isEmpty, saveBooleanA).accept("");

            assertTrue(consumedBooleanA);
        }
    }

    @Evil
    @Test
    void pred_to_bicns() {
        synchronized(consumedBooleanB) {
            synchronized(consumedStringG) {
                consumedBooleanB = false;
                consumedStringG = "";

                Z.fuse(isEmpty, saveBooleanBAndStringG).apply("").accept("yolo");

                assertTrue(consumedBooleanB);
                assertEquals("yolo", consumedStringG);
            }
        }
    }

    @Evil
    @Test
    void pred_to_objDblCns() {
        synchronized(consumedBooleanC) {
            synchronized(consumedDoubleC) {
                consumedBooleanC = false;
                consumedDoubleC = 0.0;

                Z.fuse(isEmpty, saveBooleanCDoubleC).apply("").accept(5.0);

                assertTrue(consumedBooleanC);
                assertEquals(5.0, consumedDoubleC);
            }
        }
    }

    @Evil
    @Test
    void pred_to_objIntCns() {
        synchronized(consumedBooleanD) {
            synchronized(consumedIntC) {
                consumedBooleanD = false;
                consumedIntC = 0;

                Z.fuse(isEmpty, saveBooleanDIntC).apply("").accept(6);

                assertTrue(consumedBooleanD);
                assertEquals(6, consumedIntC);
            }
        }
    }

    @Evil
    @Test
    void pred_to_objLongFn() {
        synchronized(consumedBooleanE) {
            synchronized(consumedLongC) {
                consumedBooleanE = false;
                consumedLongC = 0L;

                Z.fuse(isEmpty, saveBooleanELongC).apply("").accept(7L);

                assertTrue(consumedBooleanE);
                assertEquals(7L, consumedLongC);
            }
        }
    }

    @Test
    void pred_to_toUnop() {
        assertTrue(Z.fuse(isEmpty, booleanId).test(""));
    }

    @Test
    void pred_to_toBiop() {
        assertTrue(Z.fuse(isEmpty, maybeNot).apply("").test(false));
    }    
}
