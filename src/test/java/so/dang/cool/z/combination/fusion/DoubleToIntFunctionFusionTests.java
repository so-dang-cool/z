package so.dang.cool.z.combination.fusion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static so.dang.cool.z.combination.TestFunctions.*;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import so.dang.cool.z.Z;
import so.dang.cool.z.annotation.Evil;

public class DoubleToIntFunctionFusionTests {

    @Test
    void dblToInt() {
        assertEquals(1, doubleToInt.applyAsInt(1.0));

        assertEquals(1, Z.fuse(doubleToInt).applyAsInt(1.0));
    }

    @Test
    void dblToInt_to_intFn() {
        Stream
            .of(
                Z.fuse(doubleToInt, intToString),
                Z.fuse(doubleToInt).fuse(intToString),
                Z.fuse(doubleToInt).fusing(intToString)
            )
            .forEach(
                fusion -> {
                    assertEquals("1", fusion.apply(1.1));
                }
            );
    }

    @Test
    void dblToInt_to_intToDbl() {
        Stream
            .of(
                Z.fuse(doubleToInt, intToDouble),
                Z.fuse(doubleToInt).fuse(intToDouble),
                Z.fuse(doubleToInt).fusing(intToDouble)
            )
            .forEach(
                fusion -> {
                    assertEquals(1.0, fusion.applyAsDouble(1.2));
                }
            );
    }

    @Test
    void dblToInt_to_intToLong() {
        Stream
            .of(
                Z.fuse(doubleToInt, intToLong),
                Z.fuse(doubleToInt).fuse(intToLong),
                Z.fuse(doubleToInt).fusing(intToLong)
            )
            .forEach(
                fusion -> {
                    assertEquals(1L, fusion.applyAsLong(1.3));
                }
            );
    }

    @Test
    void dblToInt_to_intPred() {
        Stream
            .of(
                Z.fuse(doubleToInt, isIntTwo),
                Z.fuse(doubleToInt).fuse(isIntTwo),
                Z.fuse(doubleToInt).fusing(isIntTwo)
            )
            .forEach(
                fusion -> {
                    assertTrue(fusion.test(2.1));
                }
            );
    }

    @Evil
    @Test
    void dblToInt_to_intCns() {
        synchronized (consumedIntA) {
            Stream
                .of(
                    Z.fuse(doubleToInt, saveIntA),
                    Z.fuse(doubleToInt).fuse(saveIntA),
                    Z.fuse(doubleToInt).fusing(saveIntA)
                )
                .forEach(
                    fusion -> {
                        consumedIntA = 0;

                        fusion.accept(2.2);

                        assertEquals(2, consumedIntA);
                    }
                );
        }
    }

    @Test
    void dblToInt_to_intUnop() {
        Stream
            .of(
                Z.fuse(doubleToInt, addTwoToInt),
                Z.fuse(doubleToInt).fuse(addTwoToInt),
                Z.fuse(doubleToInt).fusing(addTwoToInt)
            )
            .forEach(
                fusion -> {
                    assertEquals(4, fusion.applyAsInt(2.3));
                }
            );
    }

    @Test
    void dblToInt_to_intBiop() {
        Stream
            .of(Z.fuse(doubleToInt, addInts), Z.fuse(doubleToInt).fuse(addInts))
            .forEach(
                fusion -> {
                    assertEquals(5, fusion.apply(2.4).applyAsInt(3));
                }
            );

        Stream
            .of(
                Z.fuse(doubleToInt).fuse(addInts, 3),
                Z.fuse(doubleToInt).fusing(addInts, 3)
            )
            .forEach(
                fusion -> {
                    assertEquals(5, fusion.applyAsInt(2.4));
                }
            );
    }
}
