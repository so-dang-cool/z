package so.dang.cool.z.combination.fusion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static so.dang.cool.z.combination.TestFunctions.*;

import org.junit.jupiter.api.Test;

import so.dang.cool.z.Z;
import so.dang.cool.z.annotation.Evil;

public class BiPredicateFusionTests {
    @Test
    void bipred_to_bifn() {
        assertEquals("YO", Z.fuse(startsWith, maybeToUpper).apply("yolo").apply("yo").apply("yo"));
    }

    @Test
    void bipred_to_boolFn() {
        assertEquals("true", Z.fuse(startsWith, booleanToString).apply("yolo").apply("yo"));
    }

    @Test
    void bipred_to_boolFn_deep() {
        assertEquals("true", Z.with(startsWith).fuse(booleanToString).apply("yolo").apply("yo"));
    }

    @Test
    void bipred_to_boolFn_deeper() {
        assertEquals("true", Z.with(startsWith).fusing(booleanToString).resolve().apply("yolo").apply("yo"));
    }

    @Test
    void bipred_to_toDblFn() {
        assertEquals(1.0, Z.fuse(startsWith, maybeOneAsDouble).apply("yolo").applyAsDouble("yo"));
    }

    @Test
    void bipred_to_toDblBifn() {
        assertEquals(3.0, Z.fuse(startsWith, maybeAddOneToStringAsDouble).apply("yolo").apply("yo").applyAsDouble("2.0"));
    }

    @Test
    void bipred_to_toIntFn() {
        assertEquals(2, Z.fuse(startsWith, maybeTwoAsInt).apply("yolo").applyAsInt("yo"));
    }

    @Test
    void bipred_to_toIntBifn() {
        assertEquals(9, Z.fuse(startsWith, maybeAddTwoToStringAsInt).apply("yolo").apply("yo").applyAsInt("7"));
    }

    @Test
    void bipred_to_toLongFn() {
        assertEquals(3L, Z.fuse(startsWith, maybeThreeAsLong).apply("yolo").applyAsLong("yo"));
    }

    @Test
    void bipred_to_toLongBifn() {
        assertEquals(9L, Z.fuse(startsWith, maybeAddThreeToStringAsLong).apply("yolo").apply("yo").applyAsLong("6"));
    }

    @Test
    void bipred_to_pred() {
        assertFalse(Z.fuse(startsWith, not).apply("yolo").test("yo"));
    }

    @Test
    void bipred_to_bipred() {
        assertTrue(Z.fuse(startsWith, maybeNotFromString).apply("yolo").apply("yo").test("false"));
    }

    @Evil
    @Test
    void bipred_to_cns() {
        synchronized(consumedBooleanA) {
            consumedBooleanA = false;

            Z.fuse(startsWith, saveBooleanA).apply("yolo").accept("yo");

            assertTrue(consumedBooleanA);
        }
    }

    @Evil
    @Test
    void bipred_to_bicns() {
        synchronized(consumedBooleanB) {
            synchronized(consumedStringG) {
                consumedBooleanB = false;
                consumedStringG = "";

                Z.fuse(startsWith, saveBooleanBAndStringG).apply("yolo").apply("yo").accept("mother");

                assertTrue(consumedBooleanB);
                assertEquals("mother", consumedStringG);
            }
        }
    }

    @Evil
    @Test
    void bipred_to_objDblCns() {
        synchronized(consumedBooleanC) {
            synchronized(consumedDoubleC) {
                consumedBooleanC = false;
                consumedDoubleC = 0.0;

                Z.fuse(startsWith, saveBooleanCDoubleC).apply("yolo").apply("yo").accept(0.5);

                assertTrue(consumedBooleanC);
                assertEquals(0.5, consumedDoubleC);
            }
        }
    }

    @Evil
    @Test
    void bipred_to_objIntCns() {
        synchronized(consumedBooleanD) {
            synchronized(consumedIntC) {
                consumedBooleanD = false;
                consumedIntC = 0;

                Z.fuse(startsWith, saveBooleanDIntC).apply("yolo").apply("yo").accept(111);

                assertTrue(consumedBooleanD);
                assertEquals(111, consumedIntC);
            }
        }
    }

    @Evil
    @Test
    void bipred_to_objLongFn() {
        synchronized(consumedBooleanE) {
            synchronized(consumedLongC) {
                consumedBooleanE = false;
                consumedLongC = 0L;

                Z.fuse(startsWith, saveBooleanELongC).apply("yolo").apply("yo").accept(22L);

                assertTrue(consumedBooleanE);
                assertEquals(22L, consumedLongC);
            }
        }
    }

    @Test
    void bipred_to_unop() {
        assertTrue(Z.fuse(startsWith, booleanId).apply("yolo").test("yo"));
    }

    @Test
    void bipred_to_biop() {
        assertTrue(Z.fuse(startsWith, maybeNot).apply("yolo").apply("yo").test(false));
    }
}
