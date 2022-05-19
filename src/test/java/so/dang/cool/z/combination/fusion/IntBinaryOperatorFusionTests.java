package so.dang.cool.z.combination.fusion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static so.dang.cool.z.combination.TestFunctions.*;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import so.dang.cool.z.Z;
import so.dang.cool.z.annotation.Evil;

public class IntBinaryOperatorFusionTests {

    @Test
    void intBiop() {
        assertEquals(3, addInts.applyAsInt(1, 2));
        assertEquals(3, Z.fuse(addInts).apply(1).applyAsInt(2));
    }

    @Test
    void intBiop_to_intFn() {
        Stream
            .of(Z.fuse(addInts, intToString), Z.fuse(addInts).fuse(intToString))
            .forEach(
                fusion -> {
                    assertEquals("3", fusion.apply(1).apply(2));
                }
            );
    }

    @Test
    void intBiop_to_intToDbl() {
        Stream
            .of(Z.fuse(addInts, intToDouble), Z.fuse(addInts).fuse(intToDouble))
            .forEach(
                fusion -> {
                    assertEquals(3.0, fusion.apply(1).applyAsDouble(2));
                }
            );
    }

    @Test
    void intBiop_to_dblToLong() {
        Stream
            .of(Z.fuse(addInts, intToLong), Z.fuse(addInts).fuse(intToLong))
            .forEach(
                fusion -> {
                    assertEquals(9L, fusion.apply(4).applyAsLong(5));
                }
            );
    }

    @Test
    void intBiop_to_intPred() {
        Stream
            .of(Z.fuse(addInts, isIntTwo), Z.fuse(addInts).fuse(isIntTwo))
            .forEach(
                fusion -> {
                    assertTrue(fusion.apply(-1).test(3));
                }
            );
    }

    @Evil
    @Test
    void intBiop_to_intCns() {
        synchronized (consumedIntA) {
            Stream
                .of(Z.fuse(addInts, saveIntA), Z.fuse(addInts).fuse(saveIntA))
                .forEach(
                    fusion -> {
                        consumedIntA = 0;

                        fusion.apply(2).accept(3);

                        assertEquals(5, consumedIntA);
                    }
                );
        }
    }

    @Test
    void intBiop_to_intUnop() {
        Stream
            .of(
                Z.fuse(addInts, addTwoToInt),
                Z.fuse(addInts).fuse(addTwoToInt),
                Z.fuse(addInts).fusing(addTwoToInt)
            )
            .forEach(
                fusion -> {
                    assertEquals(6, fusion.apply(1).applyAsInt(3));
                }
            );
    }

    @Test
    void intBiop_to_intBiop() {
        Stream
            .of(Z.fuse(addInts, addInts), Z.fuse(addInts).fuse(addInts))
            .forEach(
                fusion -> {
                    assertEquals(6, fusion.apply(1).apply(2).applyAsInt(3));
                }
            );

        Stream
            .of(
                Z.fuse(addInts).fuse(addInts, 3),
                Z.fuse(addInts).fusing(addInts, 3)
            )
            .forEach(
                fusion -> {
                    assertEquals(6, fusion.apply(1).applyAsInt(2));
                }
            );
    }
}
