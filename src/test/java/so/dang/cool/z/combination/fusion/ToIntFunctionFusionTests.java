package so.dang.cool.z.combination.fusion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static so.dang.cool.z.combination.TestFunctions.*;

import org.junit.jupiter.api.Test;

import so.dang.cool.z.Z;
import so.dang.cool.z.annotation.Evil;

public class ToIntFunctionFusionTests {
    @Test
    void toIntFn_to_intFn() {
        assertEquals("1", Z.fuse(stringToInt, intToString).apply("1"));
    }

    @Test
    void toIntFn_to_intFn_deep() {
        assertEquals("1", Z.with(stringToInt).fuse(intToString).apply("1"));
    }

    @Test
    void toIntFn_to_intFn_deeper() {
        assertEquals("1", Z.with(stringToInt).fusing(intToString).resolve().apply("1"));
    }

    @Test
    void toIntFn_to_intToDbl() {
        assertEquals(1.0, Z.fuse(stringToInt, intToDouble).applyAsDouble("1"));
    }

    @Test
    void toIntFn_to_intToLong() {
        assertEquals(1L, Z.fuse(stringToInt, intToLong).applyAsLong("1"));
    }

    @Test
    void toIntFn_to_intPred() {
        assertTrue(Z.fuse(stringToInt, isIntTwo).test("2"));
    }

    @Evil
    @Test
    void toIntFn_to_intCns() {
        synchronized(consumedIntA) {
            consumedIntA = 0;

            Z.fuse(stringToInt, saveIntA).accept("5");

            assertEquals(5, consumedIntA);
        }
    }

    @Test
    void toIntFn_to_intUnop() {
        assertEquals(8, Z.fuse(stringToInt, addTwoToInt).applyAsInt("6"));
    }

    @Test
    void toIntFn_to_intBiop() {
        assertEquals(3, Z.fuse(stringToInt, addInts).apply("1").applyAsInt(2));
    }    
}
