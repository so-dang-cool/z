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
    void boolean_to_fn() {
        assertEquals("true", Z.fuse(true, booleanToString).get());
    }

    @Test
    void boolean_to_bifn() {
        assertEquals("HI", Z.fuse(true, maybeToUpper).apply("hi"));
    }

    @Test
    void boolean_to_toDblFn() {
        assertEquals(1.0, Z.fuse(true, maybeOneAsDouble).getAsDouble());
    }

    @Test
    void boolean_to_toDblBifn() {
        assertEquals(3.0, Z.fuse(true, maybeAddOneToStringAsDouble).applyAsDouble("2.0"));
    }

    @Test
    void boolean_to_toIntFn() {
        assertEquals(2, Z.fuse(true, maybeTwoAsInt).getAsInt());
    }

    @Test
    void boolean_to_toIntBifn() {
        assertEquals(4, Z.fuse(true, maybeAddTwoToStringAsInt).applyAsInt("2"));
    }

    @Test
    void boolean_to_toLongFn() {
        assertEquals(3L, Z.fuse(true, maybeThreeAsLong).getAsLong());
    }

    @Test
    void boolean_to_toLongBifn() {
        assertEquals(7L, Z.fuse(true, maybeAddThreeToStringAsLong).applyAsLong("4"));
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
        synchronized(consumedBooleanA) {
            consumedBooleanA = false;

            Z.fuse(true, saveBooleanA).run();

            assertTrue(consumedBooleanA);
        }
    }

    @Evil
    @Test
    void boolean_to_bicns() {
        synchronized(consumedBooleanB) {
            synchronized(consumedStringG) {
                consumedBooleanB = false;
                consumedStringG = "";

                Z.fuse(true, saveBooleanBAndStringG).accept("z");

                assertTrue(consumedBooleanB);
                assertEquals("z", consumedStringG);
            }
        }
    }

    @Evil
    @Test
    void boolean_to_objDblCns() {
        synchronized(consumedBooleanC) {
            synchronized(consumedDoubleC) {
                consumedBooleanC = false;
                consumedDoubleC = 0.0;

                Z.fuse(true, saveBooleanCDoubleC).accept(5.0);

                assertTrue(consumedBooleanC);
                assertEquals(5.0, consumedDoubleC);
            }
        }
    }

    @Evil
    @Test
    void boolean_to_objIntCns() {
        synchronized(consumedBooleanD) {
            synchronized(consumedIntC) {
                consumedBooleanD = false;
                consumedIntC = 0;

                Z.fuse(true, saveBooleanDIntC).accept(6);

                assertTrue(consumedBooleanD);
                assertEquals(6, consumedIntC);
            }
        }
    }

    @Evil
    @Test
    void boolean_to_objLongFn() {
        synchronized(consumedBooleanE) {
            synchronized(consumedLongC) {
                consumedBooleanE = false;
                consumedLongC = 0L;

                Z.fuse(true, saveBooleanELongC).accept(7L);

                assertTrue(consumedBooleanE);
                assertEquals(7L, consumedLongC);
            }
        }
    }

    @Test
    void boolean_to_toUnop() {
        assertTrue(Z.fuse(true, booleanId).getAsBoolean());
    }

    @Test
    void boolean_to_toBiop() {
        assertTrue(Z.fuse(true, maybeNot).test(false));
    }
}
