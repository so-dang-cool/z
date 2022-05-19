package so.dang.cool.z.combination.fusion;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

        assertEquals(3, Z.with(addStringsAsInt).apply("1").applyAsInt("2"));
    }

    @Test
    void toIntBifn_to_intFn() {
        Stream
            .of(
                Z.fuse(addStringsAsInt, intToString),
                Z.with(addStringsAsInt).fuse(intToString),
                Z.with(addStringsAsInt).fusing(intToString)
            )
            .forEach(
                fusion -> {
                    assertEquals("3", fusion.apply("1").apply("2"));
                }
            );
    }

    @Test
    void toIntBifn_to_intToDbl() {
        Stream
            .of(
                Z.fuse(addStringsAsInt, intToDouble),
                Z.with(addStringsAsInt).fuse(intToDouble),
                Z.with(addStringsAsInt).fusing(intToDouble)
            )
            .forEach(
                fusion -> {
                    assertEquals(3.0, fusion.apply("1").applyAsDouble("2"));
                }
            );
    }

    @Test
    void toIntBifn_to_dblToLong() {
        Stream
            .of(
                Z.fuse(addStringsAsInt, intToLong),
                Z.with(addStringsAsInt).fuse(intToLong),
                Z.with(addStringsAsInt).fusing(intToLong)
            )
            .forEach(
                fusion -> {
                    assertEquals(9L, fusion.apply("4").applyAsLong("5"));
                }
            );
    }

    @Test
    void toIntBifn_to_intPred() {
        Stream
            .of(
                Z.fuse(addStringsAsInt, isIntTwo),
                Z.with(addStringsAsInt).fuse(isIntTwo),
                Z.with(addStringsAsInt).fusing(isIntTwo)
            )
            .forEach(
                fusion -> {
                    assertTrue(fusion.apply("-1").test("3"));
                }
            );
    }

    @Evil
    @Test
    void toIntBifn_to_intCns() {
        synchronized (consumedIntA) {
            Stream
                .of(
                    Z.fuse(addStringsAsInt, saveIntA),
                    Z.with(addStringsAsInt).fuse(saveIntA),
                    Z.with(addStringsAsInt).fusing(saveIntA)
                )
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
            .of(
                Z.fuse(addStringsAsInt, addTwoToInt),
                Z.with(addStringsAsInt).fuse(addTwoToInt),
                Z.with(addStringsAsInt).fusing(addTwoToInt)
            )
            .forEach(
                fusion -> {
                    assertEquals(6, fusion.apply("1").applyAsInt("3"));
                }
            );
    }

    @Test
    void toIntBifn_to_intBiop() {
        Stream
            .of(
                Z.fuse(addStringsAsInt, addInts),
                Z.with(addStringsAsInt).fuse(addInts)
            )
            .forEach(
                fusion -> {
                    assertEquals(6, fusion.apply("1").apply("2").applyAsInt(3));
                }
            );

        Stream
            .of(
                Z.with(addStringsAsInt).fuse(addInts, 3),
                Z.with(addStringsAsInt).fusing(addInts, 3)
            )
            .forEach(
                fusion -> {
                    assertEquals(6, fusion.apply("1").applyAsInt("2"));
                }
            );
    }
}
