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
        assertEquals("2", Z.with(2).fuse(intToString).get());
    }

    @Test
    void int_to_intToDbl() {
        assertEquals(2.0, Z.with(2).fuse(intToDouble).getAsDouble());
    }

    @Test
    void int_to_intToLong() {
        assertEquals(2L, Z.with(2).fuse(intToLong).getAsLong());
    }

    @Test
    void int_to_intPred() {
        assertTrue(Z.with(2).fuse(isIntTwo).getAsBoolean());
    }

    @Evil
    @Test
    void int_to_intCns() {
        synchronized (consumedIntA) {
            consumedIntA = 0;

            Z.with(2).fuse(saveIntA).run();

            assertEquals(suppliedInt, consumedIntA);
        }
    }

    @Test
    void int_to_intUnop() {
        assertEquals(4, Z.with(2).fuse(addTwoToInt).getAsInt());
    }

    @Test
    void int_to_intBiop() {
        assertEquals(4, Z.with(2).fuse(addInts).applyAsInt(2));
    }
}
