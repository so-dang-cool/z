package so.dang.cool.z.combination.fusion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static so.dang.cool.z.combination.TestFunctions.*;

import org.junit.jupiter.api.Test;
import so.dang.cool.z.Z;
import so.dang.cool.z.annotation.Evil;

public class IntFusionTests {

    @Test
    void int_deep() {
        assertEquals(2, Z.with(2).getAsInt());
    }

    @Test
    void int_to_intFn() {
        assertEquals("2", Z.fuse(2, intToString).get());
    }

    @Test
    void int_to_intFn_deep() {
        assertEquals("2", Z.with(2).fuse(intToString).get());
    }

    @Test
    void int_to_intToDbl() {
        assertEquals(2.0, Z.fuse(2, intToDouble).getAsDouble());
    }

    @Test
    void int_to_intToLong() {
        assertEquals(2L, Z.fuse(2, intToLong).getAsLong());
    }

    @Test
    void int_to_intPred() {
        assertTrue(Z.fuse(2, isIntTwo).getAsBoolean());
    }

    @Evil
    @Test
    void int_to_intCns() {
        synchronized (consumedIntA) {
            consumedIntA = 0;

            Z.fuse(2, saveIntA).run();

            assertEquals(suppliedInt, consumedIntA);
        }
    }

    @Test
    void int_to_intUnop() {
        assertEquals(4, Z.fuse(2, addTwoToInt).getAsInt());
    }

    @Test
    void int_to_intBiop() {
        assertEquals(4, Z.fuse(2, addInts).applyAsInt(2));
    }
}
