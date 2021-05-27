package so.dang.cool.z.combination.fusion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static so.dang.cool.z.combination.TestFunctions.*;

import org.junit.jupiter.api.Test;

import so.dang.cool.z.Z;
import so.dang.cool.z.annotation.Evil;

public class DoubleFusionTests {
    @Test
    void double_deep() {
        assertEquals(1.0, Z.with(1.0).resolve().getAsDouble());
    }

    @Test
    void double_to_dblFn() {
        assertEquals("1.0", Z.fuse(1.0, doubleToString).get());
    }

    @Test
    void double_to_dblFn_deep() {
        assertEquals("1.0", Z.with(1.0).fuse(doubleToString).get());
    }

    @Test
    void double_to_dblFn_deeper() {
        assertEquals("1.0", Z.with(1.0).fusing(doubleToString).resolve().get());
    }

    @Test
    void double_to_dblToInt() {
        assertEquals(1, Z.fuse(1.0, doubleToInt).getAsInt());
    }

    @Test
    void double_to_dblToLong() {
        assertEquals(1L, Z.fuse(1.0, doubleToLong).getAsLong());
    }

    @Test
    void double_to_dblPred() {
        assertTrue(Z.fuse(1.0, isDoubleOne).getAsBoolean());
    }

    @Evil
    @Test
    void double_to_dblCns() {
        synchronized(consumedDoubleA) {
            consumedDoubleA = 0.0;

            Z.fuse(1.0, saveDoubleA).run();

            assertEquals(suppliedDouble, consumedDoubleA);
        }
    }

    @Test
    void double_to_dblUnop() {
        assertEquals(2.0, Z.fuse(1.0, addOneToDouble).getAsDouble());
    }

    @Test
    void double_to_dblBiop() {
        assertEquals(3.0, Z.fuse(1.0, addDoubles).applyAsDouble(2.0));
    }
}
