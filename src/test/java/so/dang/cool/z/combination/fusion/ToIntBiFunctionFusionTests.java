package so.dang.cool.z.combination.fusion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static so.dang.cool.z.combination.TestFunctions.*;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import so.dang.cool.z.Z;
import so.dang.cool.z.annotation.Evil;

public class ToIntBiFunctionFusionTests {

    @Test
    void toIntBifn() {
        assertEquals(3, addStringsAsInt.applyAsInt("1", "2"));

        assertEquals(3, Z.fuse(addStringsAsInt).apply("1").applyAsInt("2"));
    }

    @Test
    void toIntBifn_to_intFn() {
        Stream
            .of(Z.fuse(addStringsAsInt).fuse(intToString))
            .forEach(
                fusion -> {
                    assertEquals("3", fusion.apply("1").apply("2"));
                }
            );
    }

    @Test
    void toIntBifn_to_intToDbl() {
        Stream
            .of(Z.fuse(addStringsAsInt).fuse(intToDouble))
            .forEach(
                fusion -> {
                    assertEquals(3.0, fusion.apply("1").applyAsDouble("2"));
                }
            );
    }

    @Test
    void toIntBifn_to_dblToLong() {
        Stream
            .of(Z.fuse(addStringsAsInt).fuse(intToLong))
            .forEach(
                fusion -> {
                    assertEquals(9L, fusion.apply("4").applyAsLong("5"));
                }
            );
    }

    @Test
    void toIntBifn_to_intPred() {
        assertTrue(
            Z.fuse(addStringsAsInt).fuse(isInt(2)).apply("-1").test("3")
        );
        assertFalse(
            Z.fuse(addStringsAsInt).fuse(isInt(9999)).apply("-1").test("3")
        );
    }

    @Evil
    @Test
    void toIntBifn_to_intCns() {
        synchronized (consumedIntA) {
            Stream
                .of(Z.fuse(addStringsAsInt).fuse(saveIntA))
                .forEach(
                    fusion -> {
                        consumedIntA = 0;

                        fusion.apply("2").accept("3");

                        assertEquals(5, consumedIntA);
                    }
                );
        }
    }

    @Test
    void toIntBifn_to_intUnop() {
        Stream
            .of(Z.fuse(addStringsAsInt).fuse(addTwoToInt))
            .forEach(
                fusion -> {
                    assertEquals(6, fusion.apply("1").applyAsInt("3"));
                }
            );
    }

    @Test
    void toIntBifn_to_intBiop() {
        Stream
            .of(Z.fuse(addStringsAsInt).fuse(addInts))
            .forEach(
                fusion -> {
                    assertEquals(6, fusion.apply("1").apply("2").applyAsInt(3));
                }
            );
    }
}
