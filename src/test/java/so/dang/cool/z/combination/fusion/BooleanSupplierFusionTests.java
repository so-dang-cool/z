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
        assertTrue(Z.with(getBooleanTrue).getAsBoolean());
    }

    @Test
    void boolSup_to_boolFn() {
        assertEquals("true", Z.fuse(getBooleanTrue, booleanToString).get());
        assertEquals(
            "true",
            Z.with(getBooleanTrue).fuse(booleanToString).get()
        );
        assertEquals(
            "true",
            Z.with(getBooleanTrue).fusing(booleanToString).get()
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
            Z.with(getBooleanTrue).fuse(maybeOneAsDouble).getAsDouble()
        );
        assertEquals(
            1.0,
            Z.with(getBooleanTrue).fusing(maybeOneAsDouble).getAsDouble()
        );
    }

    @Test
    void boolSup_to_toIntFn() {
        assertEquals(2, Z.fuse(getBooleanTrue, maybeTwoAsInt).getAsInt());
        assertEquals(2, Z.with(getBooleanTrue).fuse(maybeTwoAsInt).getAsInt());
        assertEquals(
            2,
            Z.with(getBooleanTrue).fusing(maybeTwoAsInt).getAsInt()
        );
    }

    @Test
    void boolSup_to_toLongFn() {
        assertEquals(3L, Z.fuse(getBooleanTrue, maybeThreeAsLong).getAsLong());
        assertEquals(
            3L,
            Z.with(getBooleanTrue).fuse(maybeThreeAsLong).getAsLong()
        );
        assertEquals(
            3L,
            Z.with(getBooleanTrue).fusing(maybeThreeAsLong).getAsLong()
        );
    }

    @Test
    void boolSup_to_pred() {
        assertFalse(Z.fuse(getBooleanTrue, not).getAsBoolean());
        assertFalse(Z.with(getBooleanTrue).fuse(not).getAsBoolean());
        assertFalse(Z.with(getBooleanTrue).fusing(not).getAsBoolean());
    }

    @Evil
    @Test
    void boolSup_to_cns() {
        synchronized (consumedBooleanA) {
            consumedBooleanA = false;

            Z.fuse(getBooleanTrue, saveBooleanA).run();
            Z.with(getBooleanTrue).fuse(saveBooleanA).run();
            Z.with(getBooleanTrue).fusing(saveBooleanA).run();

            assertTrue(consumedBooleanA);
        }
    }

    @Test
    void boolSup_to_toUnop() {
        assertTrue(Z.fuse(getBooleanTrue, booleanId).getAsBoolean());
        assertTrue(Z.with(getBooleanTrue).fuse(booleanId).getAsBoolean());
        assertTrue(Z.with(getBooleanTrue).fusing(booleanId).getAsBoolean());
    }
}
