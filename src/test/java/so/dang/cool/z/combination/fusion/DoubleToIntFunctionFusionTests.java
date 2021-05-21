package so.dang.cool.z.combination.fusion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static so.dang.cool.z.combination.TestFunctions.*;

import org.junit.jupiter.api.Test;

import so.dang.cool.z.Z;
import so.dang.cool.z.annotation.Evil;

public class DoubleToIntFunctionFusionTests {
    @Test
    void dblToInt_to_intFn() {
        assertEquals("1", Z.fuse(doubleToInt, intToString).apply(1.1));
    }

    @Test
    void dblToInt_to_intToDbl() {
        assertEquals(1.0, Z.fuse(doubleToInt, intToDouble).applyAsDouble(1.2));
    }

    @Test
    void dblToInt_to_intToLong() {
        assertEquals(1L, Z.fuse(doubleToInt, intToLong).applyAsLong(1.3));
    }

    @Test
    void dblToInt_to_intPred() {
        assertTrue(Z.fuse(doubleToInt, isIntTwo).test(2.1));
    }

    @Evil
    @Test
    void dblToInt_to_intCns() {
        synchronized(consumedIntA) {
            consumedIntA = 0;

            Z.fuse(doubleToInt, saveIntA).accept(2.2);

            assertEquals(2, consumedIntA);
        }
    }

    @Test
    void dblToInt_to_intUnop() {
        assertEquals(4, Z.fuse(doubleToInt, addTwoToInt).applyAsInt(2.3));
    }

    @Test
    void dblToInt_to_intBiop() {
        assertEquals(5, Z.fuse(doubleToInt, addInts).apply(2.4).applyAsInt(3));
    }    
}
