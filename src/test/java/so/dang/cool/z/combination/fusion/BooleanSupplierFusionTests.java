package so.dang.cool.z.combination.fusion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static so.dang.cool.z.combination.TestFunctions.*;

import org.junit.jupiter.api.Test;
import so.dang.cool.z.Z;
import so.dang.cool.z.annotation.Evil;

public class BooleanSupplierFusionTests {

    @Test
    void boolSup() {
        assertTrue(getBooleanTrue.getAsBoolean());
        assertTrue(Z.fuse(getBooleanTrue).getAsBoolean());
    }

    @Test
    void boolSup_to_boolFn() {
        assertEquals("true", Z.fuse(getBooleanTrue, booleanToString).get());
        assertEquals(
            "true",
            Z.fuse(getBooleanTrue).fuse(booleanToString).get()
        );
    }

    @Test
    void boolSup_to_toDblFn() {
        assertEquals(
            1.0,
            Z.fuse(getBooleanTrue, maybeOneAsDouble).getAsDouble()
        );
        assertEquals(
            1.0,
            Z.fuse(getBooleanTrue).fuse(maybeOneAsDouble).getAsDouble()
        );
    }

    @Test
    void boolSup_to_toIntFn() {
        assertEquals(2, Z.fuse(getBooleanTrue, maybeTwoAsInt).getAsInt());
        assertEquals(2, Z.fuse(getBooleanTrue).fuse(maybeTwoAsInt).getAsInt());
    }

    @Test
    void boolSup_to_toLongFn() {
        assertEquals(3L, Z.fuse(getBooleanTrue, maybeThreeAsLong).getAsLong());
        assertEquals(
            3L,
            Z.fuse(getBooleanTrue).fuse(maybeThreeAsLong).getAsLong()
        );
    }

    @Test
    void boolSup_to_pred() {
        assertFalse(Z.fuse(getBooleanTrue, not).getAsBoolean());
        assertFalse(Z.fuse(getBooleanTrue).fuse(not).getAsBoolean());
    }

    @Evil
    @Test
    void boolSup_to_cns() {
        synchronized (consumedBooleanA) {
            consumedBooleanA = false;

            Z.fuse(getBooleanTrue, saveBooleanA).run();
            Z.fuse(getBooleanTrue).fuse(saveBooleanA).run();

            assertTrue(consumedBooleanA);
        }
    }

    @Test
    void boolSup_to_toUnop() {
        assertTrue(Z.fuse(getBooleanTrue, booleanId).getAsBoolean());
        assertTrue(Z.fuse(getBooleanTrue).fuse(booleanId).getAsBoolean());
    }
}
