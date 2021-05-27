package so.dang.cool.z.combination.fusion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static so.dang.cool.z.combination.TestFunctions.*;

import org.junit.jupiter.api.Test;

import so.dang.cool.z.Z;
import so.dang.cool.z.annotation.Evil;

public class ToDoubleFunctionFusionTests {
    @Test
    void toDblFn() {
        assertEquals(1.5, stringToDouble.applyAsDouble("1.5"));
    }

    @Test
    void toDblFn_deep() {
        assertEquals(1.5, Z.with(stringToDouble).resolve().applyAsDouble("1.5"));
    }

    @Test
    void toDblFn_to_dblFn() {
        assertEquals("4.5", Z.fuse(stringToDouble, doubleToString).apply("4.5"));
    }

    @Test
    void toDblFn_to_dblFn_deep() {
        assertEquals("4.5", Z.with(stringToDouble).fuse(doubleToString).apply("4.5"));
    }

    @Test
    void toDblFn_to_dblFn_deeper() {
        assertEquals("4.5", Z.with(stringToDouble).fusing(doubleToString).resolve().apply("4.5"));
    }

    @Test
    void toDblFn_to_dblToInt() {
        assertEquals(4, Z.fuse(stringToDouble, doubleToInt).applyAsInt("4.6"));
    }

    @Test
    void toDblFn_to_dblToLong() {
        assertEquals(4L, Z.fuse(stringToDouble, doubleToLong).applyAsLong("4.7"));
    }

    @Test
    void toDblFn_to_dblPred() {
        assertTrue(Z.fuse(stringToDouble, isDoubleOne).test("1.0"));
    }

    @Evil
    @Test
    void toDblFn_to_dblCns() {
        synchronized(consumedDoubleA) {
            consumedDoubleA = 0.0;

            Z.fuse(stringToDouble, saveDoubleA).accept("5.5");

            assertEquals(5.5, consumedDoubleA);
        }
    }

    @Test
    void toDblFn_to_dblUnop() {
        assertEquals(7.5, Z.fuse(stringToDouble, addOneToDouble).applyAsDouble("6.5"));
    }

    @Test
    void toDblFn_to_dblBiop() {
        assertEquals(3.0, Z.fuse(stringToDouble, addDoubles).apply("1.0").applyAsDouble(2.0));
    }    
}
