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

        assertEquals(1, Z.fuse(stringToInt).applyAsInt("1"));
    }

    @Test
    void toIntFn_to_intFn() {
        Stream
            .of(Z.fuse(stringToInt).fuse(intToString))
            .forEach(
                fusion -> {
                    assertEquals("1", fusion.apply("1"));
                }
            );
    }

    @Test
    void toIntFn_to_intToDbl() {
        Stream
            .of(Z.fuse(stringToInt).fuse(intToDouble))
            .forEach(
                fusion -> {
                    assertEquals(1.0, fusion.applyAsDouble("1"));
                }
            );
    }

    @Test
    void toIntFn_to_intToLong() {
        Stream
            .of(Z.fuse(stringToInt).fuse(intToLong))
            .forEach(
                fusion -> {
                    assertEquals(1L, fusion.applyAsLong("1"));
                }
            );
    }

    @Test
    void toIntFn_to_intPred() {
        Stream
            .of(Z.fuse(stringToInt).fuse(isIntTwo))
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
                .of(Z.fuse(stringToInt).fuse(saveIntA))
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
            .of(Z.fuse(stringToInt).fuse(addTwoToInt))
            .forEach(
                fusion -> {
                    assertEquals(8, fusion.applyAsInt("6"));
                }
            );
    }

    @Test
    void toIntFn_to_intBiop() {
        Stream
            .of(Z.fuse(stringToInt).fuse(addInts))
            .forEach(
                fusion -> {
                    assertEquals(3, fusion.apply("1").applyAsInt(2));
                }
            );
    }
}
