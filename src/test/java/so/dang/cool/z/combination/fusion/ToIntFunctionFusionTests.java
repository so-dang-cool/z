package so.dang.cool.z.combination.fusion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static so.dang.cool.z.combination.TestFunctions.*;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import so.dang.cool.z.Z;
import so.dang.cool.z.annotation.Evil;

public class ToIntFunctionFusionTests {

    @Test
    void toIntFn() {
        assertEquals(1, stringToInt.applyAsInt("1"));

        assertEquals(1, Z.with(stringToInt).resolve().applyAsInt("1"));
    }

    @Test
    void toIntFn_to_intFn() {
        Stream
            .of(
                Z.fuse(stringToInt, intToString),
                Z.with(stringToInt).fuse(intToString),
                Z.with(stringToInt).fusing(intToString).resolve()
            )
            .forEach(
                fusion -> {
                    assertEquals("1", fusion.apply("1"));
                }
            );
    }

    @Test
    void toIntFn_to_intToDbl() {
        Stream
            .of(
                Z.fuse(stringToInt, intToDouble),
                Z.with(stringToInt).fuse(intToDouble),
                Z.with(stringToInt).fusing(intToDouble).resolve()
            )
            .forEach(
                fusion -> {
                    assertEquals(1.0, fusion.applyAsDouble("1"));
                }
            );
    }

    @Test
    void toIntFn_to_intToLong() {
        Stream
            .of(
                Z.fuse(stringToInt, intToLong),
                Z.with(stringToInt).fuse(intToLong),
                Z.with(stringToInt).fusing(intToLong).resolve()
            )
            .forEach(
                fusion -> {
                    assertEquals(1L, fusion.applyAsLong("1"));
                }
            );
    }

    @Test
    void toIntFn_to_intPred() {
        Stream
            .of(
                Z.fuse(stringToInt, isIntTwo),
                Z.with(stringToInt).fuse(isIntTwo),
                Z.with(stringToInt).fusing(isIntTwo).resolve()
            )
            .forEach(
                fusion -> {
                    assertTrue(fusion.test("2"));
                }
            );
    }

    @Evil
    @Test
    void toIntFn_to_intCns() {
        synchronized (consumedIntA) {
            Stream
                .of(
                    Z.fuse(stringToInt, saveIntA),
                    Z.with(stringToInt).fuse(saveIntA),
                    Z.with(stringToInt).fusing(saveIntA).resolve()
                )
                .forEach(
                    fusion -> {
                        consumedIntA = 0;

                        fusion.accept("5");

                        assertEquals(5, consumedIntA);
                    }
                );
        }
    }

    @Test
    void toIntFn_to_intUnop() {
        Stream
            .of(
                Z.fuse(stringToInt, addTwoToInt),
                Z.with(stringToInt).fuse(addTwoToInt),
                Z.with(stringToInt).fusing(addTwoToInt).resolve()
            )
            .forEach(
                fusion -> {
                    assertEquals(8, fusion.applyAsInt("6"));
                }
            );
    }

    @Test
    void toIntFn_to_intBiop() {
        Stream
            .of(Z.fuse(stringToInt, addInts), Z.with(stringToInt).fuse(addInts))
            .forEach(
                fusion -> {
                    assertEquals(3, fusion.apply("1").applyAsInt(2));
                }
            );

        Stream
            .of(
                Z.with(stringToInt).fuse(addInts, 2),
                Z.with(stringToInt).fusing(addInts, 2).resolve()
            )
            .forEach(
                fusion -> {
                    assertEquals(3, fusion.applyAsInt("1"));
                }
            );
    }
}
