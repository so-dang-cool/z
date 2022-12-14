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
        assertTrue(getBoolean(true).getAsBoolean());
        assertTrue(Z.with(true).getAsBoolean());
        assertTrue(Z.fuse(getBoolean(true)).getAsBoolean());
        assertFalse(getBoolean(false).getAsBoolean());
        assertFalse(Z.with(false).getAsBoolean());
        assertFalse(Z.fuse(getBoolean(false)).getAsBoolean());
    }

    @Test
    void boolSup_to_boolFn() {
        assertEquals(
            "true",
            Z.fuse(getBoolean(true)).fuse(booleanToString).get()
        );
        assertEquals(
            "false",
            Z.fuse(getBoolean(false)).fuse(booleanToString).get()
        );
    }

    @Test
    void boolSup_to_toDblFn() {
        assertEquals(
            1.0,
            Z.fuse(getBoolean(true)).fuse(maybeOneAsDouble).getAsDouble()
        );
    }

    @Test
    void boolSup_to_toIntFn() {
        assertEquals(
            2,
            Z.fuse(getBoolean(true)).fuse(maybeTwoAsInt).getAsInt()
        );
    }

    @Test
    void boolSup_to_toLongFn() {
        assertEquals(
            3L,
            Z.fuse(getBoolean(true)).fuse(maybeThreeAsLong).getAsLong()
        );
    }

    @Test
    void boolSup_to_pred() {
        assertFalse(Z.fuse(getBoolean(true)).fuse(not).getAsBoolean());
    }

    @Evil
    @Test
    void boolSup_to_cns() {
        synchronized (consumedBooleanA) {
            consumedBooleanA = false;

            Z.fuse(getBoolean(true)).fuse(saveBooleanA).run();

            assertTrue(consumedBooleanA);
        }
    }

    @Test
    void boolSup_to_toUnop() {
        assertTrue(Z.fuse(getBoolean(true)).fuse(booleanId).getAsBoolean());
    }
}
