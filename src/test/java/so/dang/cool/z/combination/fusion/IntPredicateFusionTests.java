package so.dang.cool.z.combination.fusion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static so.dang.cool.z.combination.TestFunctions.*;

import java.util.function.IntPredicate;
import org.junit.jupiter.api.Test;
import so.dang.cool.z.Z;
import so.dang.cool.z.annotation.Evil;

public class IntPredicateFusionTests {

    private static final IntPredicate isIntTwo = isInt(2);

    @Test
    void intPred() {
        assertTrue(isIntTwo.test(2));

        assertTrue(Z.fuse(isIntTwo).test(2));
    }

    @Test
    void intPred_to_fn() {
        assertEquals("true", Z.fuse(isIntTwo).fuse(booleanToString).apply(2));
    }

    @Test
    void intPred_to_toDblFn() {
        assertEquals(
            1.0,
            Z.fuse(isIntTwo).fuse(maybeOneAsDouble).applyAsDouble(2)
        );
    }

    @Test
    void intPred_to_toIntFn() {
        assertEquals(2, Z.fuse(isIntTwo).fuse(maybeTwoAsInt).applyAsInt(2));
    }

    @Test
    void intPred_to_toLongFn() {
        assertEquals(
            3L,
            Z.fuse(isIntTwo).fuse(maybeThreeAsLong).applyAsLong(2)
        );
    }

    @Test
    void intPred_to_boolPred() {
        assertFalse(Z.fuse(isIntTwo).fuse(not).test(2));
        assertTrue(Z.fuse(isIntTwo).fuse(not).test(9999));
    }

    @Evil
    @Test
    void intPred_to_cns() {
        synchronized (consumedBooleanA) {
            consumedBooleanA = false;

            Z.fuse(isIntTwo).fuse(saveBooleanA).accept(2);

            assertTrue(consumedBooleanA);
        }
    }
}
