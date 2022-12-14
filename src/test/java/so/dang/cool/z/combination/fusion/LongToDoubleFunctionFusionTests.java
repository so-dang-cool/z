package so.dang.cool.z.combination.fusion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static so.dang.cool.z.combination.TestFunctions.*;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import so.dang.cool.z.Z;
import so.dang.cool.z.annotation.Evil;

public class LongToDoubleFunctionFusionTests {

    @Test
    void longToDbl() {
        assertEquals(3.0, longToDouble.applyAsDouble(3L));

        assertEquals(3.0, Z.fuse(longToDouble).applyAsDouble(3L));
    }

    @Test
    void longToDbl_to_dblFn() {
        Stream
            .of(Z.fuse(longToDouble).fuse(doubleToString))
            .forEach(
                fusion -> {
                    assertEquals("1.0", fusion.apply(1L));
                }
            );
    }

    @Test
    void longToDbl_to_dblToInt() {
        Stream
            .of(Z.fuse(longToDouble).fuse(doubleToInt))
            .forEach(
                fusion -> {
                    assertEquals(2, fusion.applyAsInt(2L));
                }
            );
    }

    @Test
    void longToDbl_to_dblToLong() {
        Stream
            .of(Z.fuse(longToDouble).fuse(doubleToLong))
            .forEach(
                fusion -> {
                    assertEquals(3L, fusion.applyAsLong(3L));
                }
            );
    }

    @Test
    void longToDbl_to_dblPred() {
        assertTrue(Z.fuse(longToDouble).fuse(isDouble(1.0)).test(1L));
        assertFalse(Z.fuse(longToDouble).fuse(isDouble(9999.0)).test(1L));
    }

    @Evil
    @Test
    void longToDbl_to_dblCns() {
        synchronized (consumedDoubleA) {
            Stream
                .of(Z.fuse(longToDouble).fuse(saveDoubleA))
                .forEach(
                    fusion -> {
                        consumedDoubleA = 0.0;

                        fusion.accept(1L);

                        assertEquals(1.0, consumedDoubleA);
                    }
                );
        }
    }

    @Test
    void longToDbl_to_dblUnop() {
        Stream
            .of(Z.fuse(longToDouble).fuse(addOneToDouble))
            .forEach(
                fusion -> {
                    assertEquals(2.0, fusion.applyAsDouble(1L));
                }
            );
    }

    @Test
    void longToDbl_to_dblBiop() {
        Stream
            .of(Z.fuse(longToDouble).fuse(addDoubles))
            .forEach(
                fusion -> {
                    assertEquals(1.5, fusion.apply(1L).applyAsDouble(0.5));
                }
            );
    }
}
