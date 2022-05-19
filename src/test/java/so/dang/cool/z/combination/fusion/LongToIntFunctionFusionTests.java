package so.dang.cool.z.combination.fusion;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
            .of(
                Z.fuse(longToInt, intToString),
                Z.fuse(longToInt).fuse(intToString),
                Z.fuse(longToInt).fusing(intToString)
            )
            .forEach(
                fusion -> {
                    assertEquals("1", fusion.apply(1L));
                }
            );
    }

    @Test
    void longToInt_to_intToDbl() {
        Stream
            .of(
                Z.fuse(longToInt, intToDouble),
                Z.fuse(longToInt).fuse(intToDouble),
                Z.fuse(longToInt).fusing(intToDouble)
            )
            .forEach(
                fusion -> {
                    assertEquals(1.0, fusion.applyAsDouble(1L));
                }
            );
    }

    @Test
    void longToInt_to_intToLong() {
        Stream
            .of(
                Z.fuse(longToInt, intToLong),
                Z.fuse(longToInt).fuse(intToLong),
                Z.fuse(longToInt).fusing(intToLong)
            )
            .forEach(
                fusion -> {
                    assertEquals(1, fusion.applyAsLong(1L));
                }
            );
    }

    @Test
    void longToInt_to_intPred() {
        Stream
            .of(
                Z.fuse(longToInt, isIntTwo),
                Z.fuse(longToInt).fuse(isIntTwo),
                Z.fuse(longToInt).fusing(isIntTwo)
            )
            .forEach(
                fusion -> {
                    assertTrue(fusion.test(2L));
                }
            );
    }

    @Evil
    @Test
    void longToInt_to_intCns() {
        synchronized (consumedIntA) {
            Stream
                .of(
                    Z.fuse(longToInt, saveIntA),
                    Z.fuse(longToInt).fuse(saveIntA),
                    Z.fuse(longToInt).fusing(saveIntA)
                )
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
            .of(
                Z.fuse(longToInt, addTwoToInt),
                Z.fuse(longToInt).fuse(addTwoToInt),
                Z.fuse(longToInt).fusing(addTwoToInt)
            )
            .forEach(
                fusion -> {
                    assertEquals(3, fusion.applyAsInt(1L));
                }
            );
    }

    @Test
    void longToInt_to_intBiop() {
        Stream
            .of(Z.fuse(longToInt, addInts), Z.fuse(longToInt).fuse(addInts))
            .forEach(
                fusion -> {
                    assertEquals(4, fusion.apply(1L).applyAsInt(3));
                }
            );

        Stream
            .of(
                Z.fuse(longToInt).fuse(addInts, 3),
                Z.fuse(longToInt).fusing(addInts, 3)
            )
            .forEach(
                fusion -> {
                    assertEquals(4, fusion.applyAsInt(1L));
                }
            );
    }
}
