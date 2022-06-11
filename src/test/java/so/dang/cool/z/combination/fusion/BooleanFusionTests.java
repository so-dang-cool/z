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
        assertTrue(Z.with(true).getAsBoolean());
    }

    @Test
    void boolean_to_boolFn() {
        assertEquals("true", Z.with(true).fuse(booleanToString).get());
    }

    @Test
    void boolean_to_boolToDblFn() {
        assertEquals(1.0, Z.with(true).fuse(maybeOneAsDouble).getAsDouble());
    }

    @Test
    void boolean_to_boolToIntFn() {
        assertEquals(2, Z.with(true).fuse(maybeTwoAsInt).getAsInt());
    }

    @Test
    void boolean_to_boolToLongFn() {
        assertEquals(3L, Z.with(true).fuse(maybeThreeAsLong).getAsLong());
    }

    @Test
    void boolean_to_pred() {
        assertFalse(Z.with(true).fuse(not).getAsBoolean());
    }

    @Evil
    @Test
    void boolean_to_cns() {
        synchronized (consumedBooleanA) {
            consumedBooleanA = false;

            Z.with(true).fuse(saveBooleanA).run();

            assertTrue(consumedBooleanA);
        }
    }

    @Test
    void boolean_to_toUnop() {
        assertTrue(Z.with(true).fuse(booleanId).getAsBoolean());
    }
}
