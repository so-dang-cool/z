package so.dang.cool.z.combination.fusion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static so.dang.cool.z.combination.TestFunctions.*;

import org.junit.jupiter.api.Test;

import so.dang.cool.z.Z;
import so.dang.cool.z.annotation.Evil;

public class IntUnaryOperatorFusionTests {
    @Test
    void intUnop_to_intFn() {
        assertEquals("3", Z.fuse(addTwoToInt, intToString).apply(1));
    }

    @Test
    void intUnop_to_intFn_deep() {
        assertEquals("3", Z.with(addTwoToInt).fuse(intToString).apply(1));
    }

    @Test
    void intUnop_to_intFn_deeper() {
        assertEquals("3", Z.with(addTwoToInt).fusing(intToString).resolve().apply(1));
    }

    @Test
    void intUnop_to_intToDbl() {
        assertEquals(3.0, Z.fuse(addTwoToInt, intToDouble).applyAsDouble(1));
    }

    @Test
    void intUnop_to_intToLong() {
        assertEquals(3L, Z.fuse(addTwoToInt, intToLong).applyAsLong(1));
    }

    @Test
    void intUnop_to_intPred() {
        assertTrue(Z.fuse(addTwoToInt, isIntTwo).test(0));
    }

    @Evil
    @Test
    void intUnop_to_intCns() {
        synchronized(consumedIntA) {
            consumedIntA = 0;

            Z.fuse(addTwoToInt, saveIntA).accept(3);

            assertEquals(5, consumedIntA);
        }
    }

    @Test
    void intUnop_to_intUnop() {
        assertEquals(8, Z.fuse(addTwoToInt, addTwoToInt).applyAsInt(4));
    }

    @Test
    void intUnop_to_intBiop() {
        assertEquals(3, Z.fuse(addTwoToInt, addInts).apply(0).applyAsInt(1));
    }
}
