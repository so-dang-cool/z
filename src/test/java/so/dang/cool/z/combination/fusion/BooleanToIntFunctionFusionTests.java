package so.dang.cool.z.combination.fusion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static so.dang.cool.z.combination.TestFunctions.*;

import org.junit.jupiter.api.Test;
import so.dang.cool.z.Z;
import so.dang.cool.z.annotation.Evil;

public class BooleanToIntFunctionFusionTests {

    @Test
    void dblToInt() {
        assertEquals(2, maybeTwoAsInt.applyAsInt(true));

        assertEquals(2, Z.fuse(maybeTwoAsInt).applyAsInt(true));
    }

    @Test
    void dblToInt_to_intFn() {
        assertEquals("2", Z.fuse(maybeTwoAsInt).fuse(intToString).apply(true));
    }

    @Test
    void dblToInt_to_intToDbl() {
        assertEquals(
            2.0,
            Z.fuse(maybeTwoAsInt).fuse(intToDouble).applyAsDouble(true)
        );
    }

    @Test
    void dblToInt_to_intToLong() {
        assertEquals(
            2L,
            Z.fuse(maybeTwoAsInt).fuse(intToLong).applyAsLong(true)
        );
    }

    @Test
    void dblToInt_to_intPred() {
        assertTrue(Z.fuse(maybeTwoAsInt).fuse(isInt(2)).test(true));
        assertFalse(Z.fuse(maybeTwoAsInt).fuse(isInt(3)).test(true));
    }

    @Evil
    @Test
    void dblToInt_to_intCns() {
        synchronized (consumedIntA) {
            consumedIntA = 0;

            Z.fuse(maybeTwoAsInt).fuse(saveIntA).accept(true);

            assertEquals(2, consumedIntA);
        }
    }

    @Test
    void dblToInt_to_intUnop() {
        assertEquals(
            4,
            Z.fuse(maybeTwoAsInt).fuse(addTwoToInt).applyAsInt(true)
        );
    }

    @Test
    void dblToInt_to_intBiop() {
        assertEquals(
            5,
            Z.fuse(maybeTwoAsInt).fuse(addInts).apply(true).applyAsInt(3)
        );
    }
}
