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

        assertEquals(2, Z.with(maybeTwoAsInt).resolve().applyAsInt(true));
    }

    @Test
    void dblToInt_to_intFn() {
        Stream
            .of(
                Z.fuse(maybeTwoAsInt, intToString),
                Z.with(maybeTwoAsInt).fuse(intToString),
                Z.with(maybeTwoAsInt).fusing(intToString).resolve()
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
                Z.with(maybeTwoAsInt).fuse(intToDouble),
                Z.with(maybeTwoAsInt).fusing(intToDouble).resolve()
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
                Z.with(maybeTwoAsInt).fuse(intToLong),
                Z.with(maybeTwoAsInt).fusing(intToLong).resolve()
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
                Z.with(maybeTwoAsInt).fuse(isIntTwo),
                Z.with(maybeTwoAsInt).fusing(isIntTwo).resolve()
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
                    Z.with(maybeTwoAsInt).fuse(saveIntA),
                    Z.with(maybeTwoAsInt).fusing(saveIntA).resolve()
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
                Z.with(maybeTwoAsInt).fuse(addTwoToInt),
                Z.with(maybeTwoAsInt).fusing(addTwoToInt).resolve()
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
                Z.with(maybeTwoAsInt).fuse(addInts)
            )
            .forEach(
                fusion -> {
                    assertEquals(5, fusion.apply(true).applyAsInt(3));
                }
            );

        Stream
            .of(
                Z.with(maybeTwoAsInt).fuse(addInts, 3),
                Z.with(maybeTwoAsInt).fusing(addInts, 3).resolve()
            )
            .forEach(
                fusion -> {
                    assertEquals(5, fusion.applyAsInt(true));
                }
            );
    }
}
