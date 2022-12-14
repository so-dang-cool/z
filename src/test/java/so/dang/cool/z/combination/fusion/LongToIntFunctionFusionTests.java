package so.dang.cool.z.combination.fusion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static so.dang.cool.z.combination.TestFunctions.*;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import so.dang.cool.z.Z;
import so.dang.cool.z.annotation.Evil;

public class LongToIntFunctionFusionTests {

    @Test
    void longToInt() {
        assertEquals(3, longToInt.applyAsInt(3L));

        assertEquals(3, Z.fuse(longToInt).applyAsInt(3L));
    }

    @Test
    void longToInt_to_intFn() {
        Stream
            .of(Z.fuse(longToInt).fuse(intToString))
            .forEach(
                fusion -> {
                    assertEquals("1", fusion.apply(1L));
                }
            );
    }

    @Test
    void longToInt_to_intToDbl() {
        Stream
            .of(Z.fuse(longToInt).fuse(intToDouble))
            .forEach(
                fusion -> {
                    assertEquals(1.0, fusion.applyAsDouble(1L));
                }
            );
    }

    @Test
    void longToInt_to_intToLong() {
        Stream
            .of(Z.fuse(longToInt).fuse(intToLong))
            .forEach(
                fusion -> {
                    assertEquals(1, fusion.applyAsLong(1L));
                }
            );
    }

    @Test
    void longToInt_to_intPred() {
        assertTrue(Z.fuse(longToInt).fuse(isInt(2)).test(2L));
        assertFalse(Z.fuse(longToInt).fuse(isInt(9999)).test(2L));
    }

    @Evil
    @Test
    void longToInt_to_intCns() {
        synchronized (consumedIntA) {
            Stream
                .of(Z.fuse(longToInt).fuse(saveIntA))
                .forEach(
                    fusion -> {
                        consumedIntA = 0;

                        fusion.accept(3L);

                        assertEquals(3, consumedIntA);
                    }
                );
        }
    }

    @Test
    void longToInt_to_intUnop() {
        Stream
            .of(Z.fuse(longToInt).fuse(addTwoToInt))
            .forEach(
                fusion -> {
                    assertEquals(3, fusion.applyAsInt(1L));
                }
            );
    }

    @Test
    void longToInt_to_intBiop() {
        Stream
            .of(Z.fuse(longToInt).fuse(addInts))
            .forEach(
                fusion -> {
                    assertEquals(4, fusion.apply(1L).applyAsInt(3));
                }
            );
    }
}
