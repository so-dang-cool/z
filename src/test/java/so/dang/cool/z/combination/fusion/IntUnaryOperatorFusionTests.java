package so.dang.cool.z.combination.fusion;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
            .of(
                Z.fuse(addTwoToInt, intToString),
                Z.fuse(addTwoToInt).fuse(intToString),
                Z.fuse(addTwoToInt).fusing(intToString)
            )
            .forEach(
                fusion -> {
                    assertEquals("3", fusion.apply(1));
                }
            );
    }

    @Test
    void intUnop_to_intToDbl() {
        Stream
            .of(
                Z.fuse(addTwoToInt, intToDouble),
                Z.fuse(addTwoToInt).fuse(intToDouble),
                Z.fuse(addTwoToInt).fusing(intToDouble)
            )
            .forEach(
                fusion -> {
                    assertEquals(3.0, fusion.applyAsDouble(1));
                }
            );
    }

    @Test
    void intUnop_to_intToLong() {
        Stream
            .of(
                Z.fuse(addTwoToInt, intToLong),
                Z.fuse(addTwoToInt).fuse(intToLong),
                Z.fuse(addTwoToInt).fusing(intToLong)
            )
            .forEach(
                fusion -> {
                    assertEquals(3L, fusion.applyAsLong(1));
                }
            );
    }

    @Test
    void intUnop_to_intPred() {
        Stream
            .of(
                Z.fuse(addTwoToInt, isIntTwo),
                Z.fuse(addTwoToInt).fuse(isIntTwo),
                Z.fuse(addTwoToInt).fusing(isIntTwo)
            )
            .forEach(
                fusion -> {
                    assertTrue(fusion.test(0));
                }
            );
    }

    @Evil
    @Test
    void intUnop_to_intCns() {
        synchronized (consumedIntA) {
            Stream
                .of(
                    Z.fuse(addTwoToInt, saveIntA),
                    Z.fuse(addTwoToInt).fuse(saveIntA),
                    Z.fuse(addTwoToInt).fusing(saveIntA)
                )
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
            .of(
                Z.fuse(addTwoToInt, addTwoToInt),
                Z.fuse(addTwoToInt).fuse(addTwoToInt),
                Z.fuse(addTwoToInt).fusing(addTwoToInt)
            )
            .forEach(
                fusion -> {
                    assertEquals(8, fusion.applyAsInt(4));
                }
            );
    }

    @Test
    void intUnop_to_intBiop() {
        Stream
            .of(
                Z.fuse(addTwoToInt, addInts),
                Z.fuse(addTwoToInt).fuse(addInts),
                Z.fuse(addTwoToInt).fusing(addInts)
            )
            .forEach(
                fusion -> {
                    assertEquals(3, fusion.apply(0).applyAsInt(1));
                }
            );

        Stream
            .of(
                Z.fuse(addTwoToInt).fuse(addInts, 1),
                Z.fuse(addTwoToInt).fusing(addInts, 1)
            )
            .forEach(
                fusion -> {
                    assertEquals(3, fusion.applyAsInt(0));
                }
            );
    }
}
