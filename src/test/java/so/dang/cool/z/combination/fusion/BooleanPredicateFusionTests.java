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

        assertTrue(Z.fuse(booleanId).test(true));
    }

    @Test
    void boolPred_to_boolFn() {
        assertEquals(
            "true",
            Z.fuse(booleanId).fuse(booleanToString).apply(true)
        );
    }

    @Test
    void boolPred_to_toDblFn() {
        assertEquals(
            1.0,
            Z.fuse(booleanId).fuse(maybeOneAsDouble).applyAsDouble(true)
        );
    }

    @Test
    void boolPred_to_toIntFn() {
        assertEquals(2, Z.fuse(booleanId).fuse(maybeTwoAsInt).applyAsInt(true));
    }

    @Test
    void boolPred_to_boolToLongFn() {
        assertEquals(
            3L,
            Z.fuse(booleanId).fuse(maybeThreeAsLong).applyAsLong(true)
        );
    }

    @Test
    void boolPred_to_boolPred() {
        assertTrue(Z.fuse(booleanId).fuse(not).test(false));
        assertFalse(Z.fuse(booleanId).fuse(not).test(true));
    }

    @Evil
    @Test
    void boolPred_to_boolCns() {
        synchronized (consumedBooleanA) {
            consumedBooleanA = false;

            Z.fuse(booleanId).fuse(saveBooleanA).accept(true);

            assertTrue(consumedBooleanA);
        }

        synchronized (consumedBooleanA) {
            consumedBooleanA = true;

            Z.fuse(booleanId).fuse(saveBooleanA).accept(false);

            assertFalse(consumedBooleanA);
        }
    }
}
