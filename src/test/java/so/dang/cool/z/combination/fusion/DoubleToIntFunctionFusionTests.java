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

        assertEquals(1, Z.with(doubleToInt).applyAsInt(1.0));
    }

    @Test
    void dblToInt_to_intFn() {
        Stream
            .of(
                Z.fuse(doubleToInt, intToString),
                Z.with(doubleToInt).fuse(intToString),
                Z.with(doubleToInt).fusing(intToString)
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
                Z.with(doubleToInt).fuse(intToDouble),
                Z.with(doubleToInt).fusing(intToDouble)
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
                Z.with(doubleToInt).fuse(intToLong),
                Z.with(doubleToInt).fusing(intToLong)
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
                Z.with(doubleToInt).fuse(isIntTwo),
                Z.with(doubleToInt).fusing(isIntTwo)
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
                    Z.with(doubleToInt).fuse(saveIntA),
                    Z.with(doubleToInt).fusing(saveIntA)
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
                Z.with(doubleToInt).fuse(addTwoToInt),
                Z.with(doubleToInt).fusing(addTwoToInt)
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
            .of(Z.fuse(doubleToInt, addInts), Z.with(doubleToInt).fuse(addInts))
            .forEach(
                fusion -> {
                    assertEquals(5, fusion.apply(2.4).applyAsInt(3));
                }
            );

        Stream
            .of(
                Z.with(doubleToInt).fuse(addInts, 3),
                Z.with(doubleToInt).fusing(addInts, 3)
            )
            .forEach(
                fusion -> {
                    assertEquals(5, fusion.applyAsInt(2.4));
                }
            );
    }
}
