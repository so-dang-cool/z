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
    }

    @Test
    void intBiop_deep() {
        assertEquals(3, Z.with(addInts).resolve().apply(1).applyAsInt(2));
    }

    @Test
    void intBiop_to_intFn() {
        assertEquals("3", Z.fuse(addInts, intToString).apply(1).apply(2));
    }

    @Test
    void intBiop_to_intFn_deep() {
        assertEquals("3", Z.with(addInts).fuse(intToString).apply(1).apply(2));
    }

    @Test
    void intBiop_to_intToDbl() {
        assertEquals(3.0, Z.fuse(addInts, intToDouble).apply(1).applyAsDouble(2));
    }

    @Test
    void intBiop_to_dblToLong() {
        assertEquals(9L, Z.fuse(addInts, intToLong).apply(4).applyAsLong(5));
    }

    @Test
    void intBiop_to_intPred() {
        assertTrue(Z.fuse(addInts, isIntTwo).apply(-1).test(3));
    }

    @Evil
    @Test
    void intBiop_to_intCns() {
        synchronized(consumedIntA) {
            consumedIntA = 0;

            Z.fuse(addInts, saveIntA).apply(2).accept(3);

            assertEquals(5, consumedIntA);
        }
    }
    
    @Test
    void intBiop_to_intUnop() {
        assertEquals(6, Z.fuse(addInts, addTwoToInt).apply(1).applyAsInt(3));
    }

    @Test
    void intBiop_to_intBiop() {
        assertEquals(6, Z.fuse(addInts, addInts).apply(1).apply(2).applyAsInt(3));
    }
}
