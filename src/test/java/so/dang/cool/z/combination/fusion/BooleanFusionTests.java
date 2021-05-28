package so.dang.cool.z.combination.fusion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static so.dang.cool.z.combination.TestFunctions.*;

import org.junit.jupiter.api.Test;
import so.dang.cool.z.Z;
import so.dang.cool.z.annotation.Evil;

public class BooleanFusionTests {

    @Test
    void boolean_deep() {
        assertTrue(Z.with(true).resolve().getAsBoolean());
    }

    @Test
    void boolean_to_bifn() {
        assertEquals("HI", Z.fuse(true, maybeToUpper).apply("hi"));
    }

    @Test
    void boolean_to_boolFn() {
        assertEquals("true", Z.fuse(true, booleanToString).get());
    }

    @Test
    void boolean_to_boolFn_deep() {
        assertEquals("true", Z.with(true).fuse(booleanToString).get());
    }

    @Test
    void boolean_to_boolFn_deeper() {
        assertEquals(
            "true",
            Z.with(true).fusing(booleanToString).resolve().get()
        );
    }

    @Test
    void boolean_to_boolToDblFn() {
        assertEquals(1.0, Z.fuse(true, maybeOneAsDouble).getAsDouble());
    }

    @Test
    void boolean_to_toDblBifn() {
        assertEquals(
            3.0,
            Z.fuse(true, maybeAddOneToStringAsDouble).applyAsDouble("2.0")
        );
    }

    @Test
    void boolean_to_boolToIntFn() {
        assertEquals(2, Z.fuse(true, maybeTwoAsInt).getAsInt());
    }

    @Test
    void boolean_to_toIntBifn() {
        assertEquals(4, Z.fuse(true, maybeAddTwoToStringAsInt).applyAsInt("2"));
    }

    @Test
    void boolean_to_boolToLongFn() {
        assertEquals(3L, Z.fuse(true, maybeThreeAsLong).getAsLong());
    }

    @Test
    void boolean_to_toLongBifn() {
        assertEquals(
            7L,
            Z.fuse(true, maybeAddThreeToStringAsLong).applyAsLong("4")
        );
    }

    @Test
    void boolean_to_pred() {
        assertFalse(Z.fuse(true, not).getAsBoolean());
    }

    @Test
    void boolean_to_bipred() {
        assertTrue(Z.fuse(true, maybeNotFromString).test("false"));
    }

    @Evil
    @Test
    void boolean_to_cns() {
        synchronized (consumedBooleanA) {
            consumedBooleanA = false;

            Z.fuse(true, saveBooleanA).run();

            assertTrue(consumedBooleanA);
        }
    }

    @Evil
    @Test
    void boolean_to_bicns() {
        synchronized (consumedBooleanB) {
            synchronized (consumedStringG) {
                consumedBooleanB = false;
                consumedStringG = "";

                Z.fuse(true, saveBooleanBAndStringG).accept("z");

                assertTrue(consumedBooleanB);
                assertEquals("z", consumedStringG);
            }
        }
    }

    @Test
    void boolean_to_toUnop() {
        assertTrue(Z.fuse(true, booleanId).getAsBoolean());
    }
}
