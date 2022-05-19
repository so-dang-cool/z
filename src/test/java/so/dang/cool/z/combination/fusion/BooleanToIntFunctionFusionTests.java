package so.dang.cool.z.combination.fusion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static so.dang.cool.z.combination.TestFunctions.*;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import so.dang.cool.z.Z;
import so.dang.cool.z.annotation.Evil;

public class BooleanToIntFunctionFusionTests {

    @Test
    void dblToInt() {
        assertEquals(2, maybeTwoAsInt.applyAsInt(true));

        assertEquals(2, Z.fuse(maybeTwoAsInt).applyAsInt(true));
    }

    @Test
    void dblToInt_to_intFn() {
        Stream
            .of(
                Z.fuse(maybeTwoAsInt, intToString),
                Z.fuse(maybeTwoAsInt).fuse(intToString),
                Z.fuse(maybeTwoAsInt).fusing(intToString)
            )
            .forEach(
                fusion -> {
                    assertEquals("2", fusion.apply(true));
                }
            );
    }

    @Test
    void dblToInt_to_intToDbl() {
        Stream
            .of(
                Z.fuse(maybeTwoAsInt, intToDouble),
                Z.fuse(maybeTwoAsInt).fuse(intToDouble),
                Z.fuse(maybeTwoAsInt).fusing(intToDouble)
            )
            .forEach(
                fusion -> {
                    assertEquals(2.0, fusion.applyAsDouble(true));
                }
            );
    }

    @Test
    void dblToInt_to_intToLong() {
        Stream
            .of(
                Z.fuse(maybeTwoAsInt, intToLong),
                Z.fuse(maybeTwoAsInt).fuse(intToLong),
                Z.fuse(maybeTwoAsInt).fusing(intToLong)
            )
            .forEach(
                fusion -> {
                    assertEquals(2L, fusion.applyAsLong(true));
                }
            );
    }

    @Test
    void dblToInt_to_intPred() {
        Stream
            .of(
                Z.fuse(maybeTwoAsInt, isIntTwo),
                Z.fuse(maybeTwoAsInt).fuse(isIntTwo),
                Z.fuse(maybeTwoAsInt).fusing(isIntTwo)
            )
            .forEach(
                fusion -> {
                    assertTrue(fusion.test(true));
                }
            );
    }

    @Evil
    @Test
    void dblToInt_to_intCns() {
        synchronized (consumedIntA) {
            Stream
                .of(
                    Z.fuse(maybeTwoAsInt, saveIntA),
                    Z.fuse(maybeTwoAsInt).fuse(saveIntA),
                    Z.fuse(maybeTwoAsInt).fusing(saveIntA)
                )
                .forEach(
                    fusion -> {
                        consumedIntA = 0;

                        fusion.accept(true);

                        assertEquals(2, consumedIntA);
                    }
                );
        }
    }

    @Test
    void dblToInt_to_intUnop() {
        Stream
            .of(
                Z.fuse(maybeTwoAsInt, addTwoToInt),
                Z.fuse(maybeTwoAsInt).fuse(addTwoToInt),
                Z.fuse(maybeTwoAsInt).fusing(addTwoToInt)
            )
            .forEach(
                fusion -> {
                    assertEquals(4, fusion.applyAsInt(true));
                }
            );
    }

    @Test
    void dblToInt_to_intBiop() {
        Stream
            .of(
                Z.fuse(maybeTwoAsInt, addInts),
                Z.fuse(maybeTwoAsInt).fuse(addInts)
            )
            .forEach(
                fusion -> {
                    assertEquals(5, fusion.apply(true).applyAsInt(3));
                }
            );

        Stream
            .of(
                Z.fuse(maybeTwoAsInt).fuse(addInts, 3),
                Z.fuse(maybeTwoAsInt).fusing(addInts, 3)
            )
            .forEach(
                fusion -> {
                    assertEquals(5, fusion.applyAsInt(true));
                }
            );
    }
}
