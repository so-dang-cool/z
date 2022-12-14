package so.dang.cool.z.combination.fusion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static so.dang.cool.z.combination.TestFunctions.*;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import so.dang.cool.z.Z;
import so.dang.cool.z.annotation.Evil;

public class IntUnaryOperatorFusionTests {

    @Test
    void intUnop() {
        assertEquals(3, addTwoToInt.applyAsInt(1));

        assertEquals(3, Z.fuse(addTwoToInt).applyAsInt(1));
    }

    @Test
    void intUnop_to_intFn() {
        Stream
            .of(Z.fuse(addTwoToInt).fuse(intToString))
            .forEach(
                fusion -> {
                    assertEquals("3", fusion.apply(1));
                }
            );
    }

    @Test
    void intUnop_to_intToDbl() {
        Stream
            .of(Z.fuse(addTwoToInt).fuse(intToDouble))
            .forEach(
                fusion -> {
                    assertEquals(3.0, fusion.applyAsDouble(1));
                }
            );
    }

    @Test
    void intUnop_to_intToLong() {
        Stream
            .of(Z.fuse(addTwoToInt).fuse(intToLong))
            .forEach(
                fusion -> {
                    assertEquals(3L, fusion.applyAsLong(1));
                }
            );
    }

    @Test
    void intUnop_to_intPred() {
        assertTrue(Z.fuse(addTwoToInt).fuse(isInt(2)).test(0));
        assertFalse(Z.fuse(addTwoToInt).fuse(isInt(9999)).test(0));
    }

    @Evil
    @Test
    void intUnop_to_intCns() {
        synchronized (consumedIntA) {
            Stream
                .of(Z.fuse(addTwoToInt).fuse(saveIntA))
                .forEach(
                    fusion -> {
                        consumedIntA = 0;

                        fusion.accept(3);

                        assertEquals(5, consumedIntA);
                    }
                );
        }
    }

    @Test
    void intUnop_to_intUnop() {
        Stream
            .of(Z.fuse(addTwoToInt).fuse(addTwoToInt))
            .forEach(
                fusion -> {
                    assertEquals(8, fusion.applyAsInt(4));
                }
            );
    }

    @Test
    void intUnop_to_intBiop() {
        Stream
            .of(Z.fuse(addTwoToInt).fuse(addInts))
            .forEach(
                fusion -> {
                    assertEquals(3, fusion.apply(0).applyAsInt(1));
                }
            );
    }
}
