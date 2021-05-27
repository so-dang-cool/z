package so.dang.cool.z.combination.fusion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static so.dang.cool.z.combination.TestFunctions.*;

import org.junit.jupiter.api.Test;
import so.dang.cool.z.Z;
import so.dang.cool.z.annotation.Evil;

public class IntPredicateFusionTests {

    @Test
    void intPred() {
        assertTrue(isIntTwo.test(2));
    }

    @Test
    void intPred_deep() {
        assertTrue(Z.with(isIntTwo).resolve().test(2));
    }

    @Test
    void intPred_to_fn() {
        assertEquals("true", Z.fuse(isIntTwo, booleanToString).apply(2));
    }

    @Test
    void intPred_to_fn_deep() {
        assertEquals("true", Z.with(isIntTwo).fuse(booleanToString).apply(2));
    }

    @Test
    void intPred_to_fn_deeper() {
        assertEquals(
            "true",
            Z.with(isIntTwo).fusing(booleanToString).resolve().apply(2)
        );
    }

    @Test
    void intPred_to_bifn() {
        assertEquals("HI", Z.fuse(isIntTwo, maybeToUpper).apply(2).apply("hi"));
    }

    @Test
    void intPred_to_toDblFn() {
        assertEquals(1.0, Z.fuse(isIntTwo, maybeOneAsDouble).applyAsDouble(2));
    }

    @Test
    void intPred_to_toDblBifn() {
        assertEquals(
            2.0,
            Z
                .fuse(isIntTwo, maybeAddOneToStringAsDouble)
                .apply(2)
                .applyAsDouble("1.0")
        );
    }

    @Test
    void intPred_to_toIntFn() {
        assertEquals(2, Z.fuse(isIntTwo, maybeTwoAsInt).applyAsInt(2));
    }

    @Test
    void intPred_to_toIntBifn() {
        assertEquals(
            3,
            Z.fuse(isIntTwo, maybeAddTwoToStringAsInt).apply(2).applyAsInt("1")
        );
    }

    @Test
    void intPred_to_toLongFn() {
        assertEquals(3L, Z.fuse(isIntTwo, maybeThreeAsLong).applyAsLong(2));
    }

    @Test
    void intPred_to_toLongBifn() {
        assertEquals(
            4L,
            Z
                .fuse(isIntTwo, maybeAddThreeToStringAsLong)
                .apply(2)
                .applyAsLong("1")
        );
    }

    @Test
    void intPred_to_pred() {
        assertFalse(Z.fuse(isIntTwo, not).test(2));
    }

    @Test
    void intPred_to_bipred() {
        assertTrue(Z.fuse(isIntTwo, maybeNotFromString).apply(2).test("false"));
    }

    @Evil
    @Test
    void intPred_to_cns() {
        synchronized (consumedBooleanA) {
            consumedBooleanA = false;

            Z.fuse(isIntTwo, saveBooleanA).accept(2);

            assertTrue(consumedBooleanA);
        }
    }

    @Evil
    @Test
    void intPred_to_bicns() {
        synchronized (consumedBooleanB) {
            synchronized (consumedStringG) {
                consumedBooleanB = false;
                consumedStringG = "";

                Z
                    .fuse(isIntTwo, saveBooleanBAndStringG)
                    .apply(2)
                    .accept("yolo");

                assertTrue(consumedBooleanB);
                assertEquals("yolo", consumedStringG);
            }
        }
    }

    @Evil
    @Test
    void intPred_to_objDblCns() {
        synchronized (consumedBooleanC) {
            synchronized (consumedDoubleC) {
                consumedBooleanC = false;
                consumedDoubleC = 0.0;

                Z.fuse(isIntTwo, saveBooleanCDoubleC).apply(2).accept(5.0);

                assertTrue(consumedBooleanC);
                assertEquals(5.0, consumedDoubleC);
            }
        }
    }

    @Evil
    @Test
    void intPred_to_objIntCns() {
        synchronized (consumedBooleanD) {
            synchronized (consumedIntC) {
                consumedBooleanD = false;
                consumedIntC = 0;

                Z.fuse(isIntTwo, saveBooleanDIntC).apply(2).accept(6);

                assertTrue(consumedBooleanD);
                assertEquals(6, consumedIntC);
            }
        }
    }

    @Evil
    @Test
    void intPred_to_objLongFn() {
        synchronized (consumedBooleanE) {
            synchronized (consumedLongC) {
                consumedBooleanE = false;
                consumedLongC = 0L;

                Z.fuse(isIntTwo, saveBooleanELongC).apply(2).accept(7L);

                assertTrue(consumedBooleanE);
                assertEquals(7L, consumedLongC);
            }
        }
    }

    @Test
    void intPred_to_toUnop() {
        assertTrue(Z.fuse(isIntTwo, booleanId).test(2));
    }

    @Test
    void intPred_to_toBiop() {
        assertTrue(Z.fuse(isIntTwo, maybeNot).apply(2).test(false));
    }
}
