package so.dang.cool.z.combination.fusion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static so.dang.cool.z.combination.TestFunctions.*;

import org.junit.jupiter.api.Test;
import so.dang.cool.z.Z;
import so.dang.cool.z.annotation.Evil;

public class LongPredicateFusionTests {

    @Test
    void longPred() {
        assertTrue(isLongThree.test(3L));
    }

    @Test
    void longPred_deep() {
        assertTrue(Z.with(isLongThree).resolve().test(3L));
    }

    @Test
    void longPred_to_bifn() {
        assertEquals(
            "HI",
            Z.fuse(isLongThree, maybeToUpper).apply(3L).apply("hi")
        );
    }

    @Test
    void longPred_to_boolFn() {
        assertEquals("true", Z.fuse(isLongThree, booleanToString).apply(3L));
    }

    @Test
    void longPred_to_boolFn_deep() {
        assertEquals(
            "true",
            Z.with(isLongThree).fuse(booleanToString).apply(3L)
        );
    }

    @Test
    void longPred_to_boolFn_deeper() {
        assertEquals(
            "true",
            Z.with(isLongThree).fusing(booleanToString).resolve().apply(3L)
        );
    }

    @Test
    void longPred_to_toDblFn() {
        assertEquals(
            1.0,
            Z.fuse(isLongThree, maybeOneAsDouble).applyAsDouble(3L)
        );
    }

    @Test
    void longPred_to_toDblBifn() {
        assertEquals(
            2.0,
            Z
                .fuse(isLongThree, maybeAddOneToStringAsDouble)
                .apply(3L)
                .applyAsDouble("1.0")
        );
    }

    @Test
    void longPred_to_toIntFn() {
        assertEquals(2, Z.fuse(isLongThree, maybeTwoAsInt).applyAsInt(3L));
    }

    @Test
    void longPred_to_toIntBifn() {
        assertEquals(
            3,
            Z
                .fuse(isLongThree, maybeAddTwoToStringAsInt)
                .apply(3L)
                .applyAsInt("1")
        );
    }

    @Test
    void longPred_to_toLongFn() {
        assertEquals(3L, Z.fuse(isLongThree, maybeThreeAsLong).applyAsLong(3L));
    }

    @Test
    void longPred_to_toLongBifn() {
        assertEquals(
            4L,
            Z
                .fuse(isLongThree, maybeAddThreeToStringAsLong)
                .apply(3L)
                .applyAsLong("1")
        );
    }

    @Test
    void longPred_to_pred() {
        assertFalse(Z.fuse(isLongThree, not).test(3L));
    }

    @Test
    void longPred_to_bipred() {
        assertTrue(
            Z.fuse(isLongThree, maybeNotFromString).apply(3L).test("false")
        );
    }

    @Evil
    @Test
    void longPred_to_cns() {
        synchronized (consumedBooleanA) {
            consumedBooleanA = false;

            Z.fuse(isLongThree, saveBooleanA).accept(3L);

            assertTrue(consumedBooleanA);
        }
    }

    @Evil
    @Test
    void longPred_to_bicns() {
        synchronized (consumedBooleanB) {
            synchronized (consumedStringG) {
                consumedBooleanB = false;
                consumedStringG = "";

                Z
                    .fuse(isLongThree, saveBooleanBAndStringG)
                    .apply(3L)
                    .accept("yolo");

                assertTrue(consumedBooleanB);
                assertEquals("yolo", consumedStringG);
            }
        }
    }

    @Evil
    @Test
    void longPred_to_objDblCns() {
        synchronized (consumedBooleanC) {
            synchronized (consumedDoubleC) {
                consumedBooleanC = false;
                consumedDoubleC = 0.0;

                Z.fuse(isLongThree, saveBooleanCDoubleC).apply(3L).accept(5.0);

                assertTrue(consumedBooleanC);
                assertEquals(5.0, consumedDoubleC);
            }
        }
    }

    @Evil
    @Test
    void longPred_to_objIntCns() {
        synchronized (consumedBooleanD) {
            synchronized (consumedIntC) {
                consumedBooleanD = false;
                consumedIntC = 0;

                Z.fuse(isLongThree, saveBooleanDIntC).apply(3L).accept(6);

                assertTrue(consumedBooleanD);
                assertEquals(6, consumedIntC);
            }
        }
    }

    @Evil
    @Test
    void longPred_to_objLongFn() {
        synchronized (consumedBooleanE) {
            synchronized (consumedLongC) {
                consumedBooleanE = false;
                consumedLongC = 0L;

                Z.fuse(isLongThree, saveBooleanELongC).apply(3L).accept(7L);

                assertTrue(consumedBooleanE);
                assertEquals(7L, consumedLongC);
            }
        }
    }

    @Test
    void longPred_to_toUnop() {
        assertTrue(Z.fuse(isLongThree, booleanId).test(3L));
    }

    @Test
    void longPred_to_toBiop() {
        assertTrue(Z.fuse(isLongThree, maybeNot).apply(3L).test(false));
    }
}
