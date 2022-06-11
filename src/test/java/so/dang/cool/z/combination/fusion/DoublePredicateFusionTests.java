package so.dang.cool.z.combination.fusion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static so.dang.cool.z.combination.TestFunctions.*;

import org.junit.jupiter.api.Test;
import so.dang.cool.z.Z;
import so.dang.cool.z.annotation.Evil;

public class DoublePredicateFusionTests {

    @Test
    void dblPred() {
        assertTrue(isDoubleOne.test(1.0));

        assertTrue(Z.fuse(isDoubleOne).test(1.0));
    }

    @Test
    void dblPred_to_boolFn() {
        assertEquals(
            "true",
            Z.fuse(isDoubleOne).fuse(booleanToString).apply(1.0)
        );
    }

    @Test
    void dblPred_to_boolToDblFn() {
        assertEquals(
            1.0,
            Z.fuse(isDoubleOne).fuse(maybeOneAsDouble).applyAsDouble(1.0)
        );
    }

    @Test
    void dblPred_to_toIntFn() {
        assertEquals(
            2,
            Z.fuse(isDoubleOne).fuse(maybeTwoAsInt).applyAsInt(1.0)
        );
    }

    @Test
    void dblPred_to_toLongFn() {
        assertEquals(
            3L,
            Z.fuse(isDoubleOne).fuse(maybeThreeAsLong).applyAsLong(1.0)
        );
    }

    @Test
    void dblPred_to_boolPred() {
        assertFalse(Z.fuse(isDoubleOne).fuse(not).test(1.0));
    }

    @Evil
    @Test
    void dblPred_to_cns() {
        synchronized (consumedBooleanA) {
            consumedBooleanA = false;

            Z.fuse(isDoubleOne).fuse(saveBooleanA).accept(1.0);

            assertTrue(consumedBooleanA);
        }
    }
}
