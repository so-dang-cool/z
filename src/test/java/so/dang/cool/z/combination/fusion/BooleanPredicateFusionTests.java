package so.dang.cool.z.combination.fusion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static so.dang.cool.z.combination.TestFunctions.*;

import org.junit.jupiter.api.Test;
import so.dang.cool.z.Z;
import so.dang.cool.z.annotation.Evil;

public class BooleanPredicateFusionTests {

    @Test
    void boolPred() {
        assertTrue(booleanId.test(true));
    }

    @Test
    void boolPred_deep() {
        assertTrue(Z.with(booleanId).resolve().test(true));
    }

    @Test
    void boolPred_to_bifn() {
        assertEquals(
            "HI",
            Z.fuse(booleanId, maybeToUpper).apply(true).apply("hi")
        );
    }

    @Test
    void boolPred_to_boolFn() {
        assertEquals("true", Z.fuse(booleanId, booleanToString).apply(true));
    }

    @Test
    void boolPred_to_boolFn_deep() {
        assertEquals(
            "true",
            Z.with(booleanId).fuse(booleanToString).apply(true)
        );
    }

    @Test
    void boolPred_to_boolFn_deeper() {
        assertEquals(
            "true",
            Z.with(booleanId).fusing(booleanToString).resolve().apply(true)
        );
    }

    @Test
    void boolPred_to_toDblFn() {
        assertEquals(
            1.0,
            Z.fuse(booleanId, maybeOneAsDouble).applyAsDouble(true)
        );
    }

    @Test
    void boolPred_to_toDblBifn() {
        assertEquals(
            2.0,
            Z
                .fuse(booleanId, maybeAddOneToStringAsDouble)
                .apply(true)
                .applyAsDouble("1.0")
        );
    }

    @Test
    void boolPred_to_toIntFn() {
        assertEquals(2, Z.fuse(booleanId, maybeTwoAsInt).applyAsInt(true));
    }

    @Test
    void boolPred_to_toIntBifn() {
        assertEquals(
            3,
            Z
                .fuse(booleanId, maybeAddTwoToStringAsInt)
                .apply(true)
                .applyAsInt("1")
        );
    }

    @Test
    void boolPred_to_boolToLongFn() {
        assertEquals(3L, Z.fuse(booleanId, maybeThreeAsLong).applyAsLong(true));
    }

    @Test
    void boolPred_to_boolToLongBifn() {
        assertEquals(
            4L,
            Z
                .fuse(booleanId, maybeAddThreeToStringAsLong)
                .apply(true)
                .applyAsLong("1")
        );
    }

    @Test
    void boolPred_to_bipred() {
        assertTrue(
            Z.fuse(booleanId, maybeNotFromString).apply(true).test("false")
        );
    }

    @Test
    void boolPred_to_boolPred() {
        assertFalse(Z.fuse(booleanId, not).test(true));
    }

    @Evil
    @Test
    void boolPred_to_cns() {
        synchronized (consumedBooleanA) {
            consumedBooleanA = false;

            Z.fuse(booleanId, saveBooleanA).accept(true);

            assertTrue(consumedBooleanA);
        }
    }

    @Evil
    @Test
    void boolPred_to_bicns() {
        synchronized (consumedBooleanB) {
            synchronized (consumedStringG) {
                consumedBooleanB = false;
                consumedStringG = "";

                Z
                    .fuse(booleanId, saveBooleanBAndStringG)
                    .apply(true)
                    .accept("yolo");

                assertTrue(consumedBooleanB);
                assertEquals("yolo", consumedStringG);
            }
        }
    }
}
