package so.dang.cool.z.combination.fusion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static so.dang.cool.z.combination.TestFunctions.*;

import org.junit.jupiter.api.Test;

import so.dang.cool.z.Z;
import so.dang.cool.z.annotation.Evil;

public class BooleanToIntFunctionFusionTests {
    @Test
    void dblToInt() {
        assertEquals(2, maybeTwoAsInt.applyAsInt(true));
    }

    @Test
    void dblToInt_deep() {
        assertEquals(2, Z.with(maybeTwoAsInt).resolve().applyAsInt(true));
    }

    @Test
    void dblToInt_to_intFn() {
        assertEquals("2", Z.fuse(maybeTwoAsInt, intToString).apply(true));
    }

    @Test
    void dblToInt_to_intFn_deep() {
        assertEquals("2", Z.with(maybeTwoAsInt).fuse(intToString).apply(true));
    }

    @Test
    void dblToInt_to_intFn_deeper() {
        assertEquals("2", Z.with(maybeTwoAsInt).fusing(intToString).resolve().apply(true));
    }

    @Test
    void dblToInt_to_intToDbl() {
        assertEquals(2.0, Z.fuse(maybeTwoAsInt, intToDouble).applyAsDouble(true));
    }

    @Test
    void dblToInt_to_intToLong() {
        assertEquals(2L, Z.fuse(maybeTwoAsInt, intToLong).applyAsLong(true));
    }

    @Test
    void dblToInt_to_intPred() {
        assertTrue(Z.fuse(maybeTwoAsInt, isIntTwo).test(true));
    }

    @Evil
    @Test
    void dblToInt_to_intCns() {
        synchronized(consumedIntA) {
            consumedIntA = 0;

            Z.fuse(maybeTwoAsInt, saveIntA).accept(true);

            assertEquals(2, consumedIntA);
        }
    }

    @Test
    void dblToInt_to_intUnop() {
        assertEquals(4, Z.fuse(maybeTwoAsInt, addTwoToInt).applyAsInt(true));
    }

    @Test
    void dblToInt_to_intBiop() {
        assertEquals(5, Z.fuse(maybeTwoAsInt, addInts).apply(true).applyAsInt(3));
    }    
}
