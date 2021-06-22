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

        assertEquals(3, Z.with(addTwoToInt).resolve().applyAsInt(1));
    }

    @Test
    void intUnop_to_intFn() {
        Stream
            .of(
                Z.fuse(addTwoToInt, intToString),
                Z.with(addTwoToInt).fuse(intToString),
                Z.with(addTwoToInt).fusing(intToString).resolve()
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
                Z.with(addTwoToInt).fuse(intToDouble),
                Z.with(addTwoToInt).fusing(intToDouble).resolve()
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
                Z.with(addTwoToInt).fuse(intToLong),
                Z.with(addTwoToInt).fusing(intToLong).resolve()
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
                Z.with(addTwoToInt).fuse(isIntTwo),
                Z.with(addTwoToInt).fusing(isIntTwo).resolve()
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
                    Z.with(addTwoToInt).fuse(saveIntA),
                    Z.with(addTwoToInt).fusing(saveIntA).resolve()
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
                Z.with(addTwoToInt).fuse(addTwoToInt),
                Z.with(addTwoToInt).fusing(addTwoToInt).resolve()
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
                Z.with(addTwoToInt).fuse(addInts),
                Z.with(addTwoToInt).fusing(addInts).resolve()
            )
            .forEach(
                fusion -> {
                    assertEquals(3, fusion.apply(0).applyAsInt(1));
                }
            );

        Stream
            .of(
                Z.with(addTwoToInt).fuse(addInts, 1),
                Z.with(addTwoToInt).fusing(addInts, 1).resolve()
            )
            .forEach(
                fusion -> {
                    assertEquals(3, fusion.applyAsInt(0));
                }
            );
    }
}
