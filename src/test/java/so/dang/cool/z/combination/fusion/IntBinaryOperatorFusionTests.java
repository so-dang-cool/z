package so.dang.cool.z.combination.fusion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static so.dang.cool.z.combination.TestFunctions.*;

import org.junit.jupiter.api.Test;
import so.dang.cool.z.Z;
import so.dang.cool.z.annotation.Evil;

public class IntBinaryOperatorFusionTests {

    @Test
    void intBiop() {
        assertEquals(3, addInts.applyAsInt(1, 2));
        assertEquals(3, Z.fuse(addInts).apply(1).applyAsInt(2));
    }

    @Test
    void intBiop_to_intFn() {
        assertEquals("3", Z.fuse(addInts).fuse(intToString).apply(1).apply(2));
    }

    @Test
    void intBiop_to_intToDbl() {
        assertEquals(
            3.0,
            Z.fuse(addInts).fuse(intToDouble).apply(1).applyAsDouble(2)
        );
    }

    @Test
    void intBiop_to_dblToLong() {
        assertEquals(
            9L,
            Z.fuse(addInts).fuse(intToLong).apply(4).applyAsLong(5)
        );
    }

    @Test
    void intBiop_to_intPred() {
        assertTrue(Z.fuse(addInts).fuse(isIntTwo).apply(-1).test(3));
    }

    @Evil
    @Test
    void intBiop_to_intCns() {
        synchronized (consumedIntA) {
            consumedIntA = 0;

            Z.fuse(addInts).fuse(saveIntA).apply(2).accept(3);

            assertEquals(5, consumedIntA);
        }
    }

    @Test
    void intBiop_to_intUnop() {
        assertEquals(
            6,
            Z.fuse(addInts).fuse(addTwoToInt).apply(1).applyAsInt(3)
        );
    }

    @Test
    void intBiop_to_intBiop() {
        assertEquals(
            6,
            Z.fuse(addInts).fuse(addInts).apply(1).apply(2).applyAsInt(3)
        );
    }
}
