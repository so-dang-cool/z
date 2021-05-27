package so.dang.cool.z.combination.fusion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static so.dang.cool.z.combination.TestFunctions.*;

import org.junit.jupiter.api.Test;

import so.dang.cool.z.Z;
import so.dang.cool.z.annotation.Evil;

public class ToIntBiFunctionFusionTests {
    @Test
    void toIntBifn() {
        assertEquals(3, addStringsAsInt.applyAsInt("1", "2"));
    }

    @Test
    void toIntBifn_deep() {
        assertEquals(3, Z.with(addStringsAsInt).resolve().apply("1").applyAsInt("2"));
    }

    @Test
    void toIntBifn_to_intFn() {
        assertEquals("3", Z.fuse(addStringsAsInt, intToString).apply("1").apply("2"));
    }

    @Test
    void toIntBifn_to_intFn_deep() {
        assertEquals("3", Z.with(addStringsAsInt).fuse(intToString).apply("1").apply("2"));
    }

    @Test
    void toIntBifn_to_intFn_deeper() {
        assertEquals("3", Z.with(addStringsAsInt).fusing(intToString).resolve().apply("1").apply("2"));
    }

    @Test
    void toIntBifn_to_intToDbl() {
        assertEquals(3.0, Z.fuse(addStringsAsInt, intToDouble).apply("1").applyAsDouble("2"));
    }

    @Test
    void toIntBifn_to_dblToLong() {
        assertEquals(9L, Z.fuse(addStringsAsInt, intToLong).apply("4").applyAsLong("5"));
    }

    @Test
    void toIntBifn_to_intPred() {
        assertTrue(Z.fuse(addStringsAsInt, isIntTwo).apply("-1").test("3"));
    }

    @Evil
    @Test
    void toIntBifn_to_intCns() {
        synchronized(consumedIntA) {
            consumedIntA = 0;

            Z.fuse(addStringsAsInt, saveIntA).apply("2").accept("3");

            assertEquals(5, consumedIntA);
        }
    }
    
    @Test
    void toIntBifn_to_intUnop() {
        assertEquals(6, Z.fuse(addStringsAsInt, addTwoToInt).apply("1").applyAsInt("3"));
    }

    @Test
    void toIntBifn_to_intBiop() {
        assertEquals(6, Z.fuse(addStringsAsInt, addInts).apply("1").apply("2").applyAsInt(3));
    }    
}
