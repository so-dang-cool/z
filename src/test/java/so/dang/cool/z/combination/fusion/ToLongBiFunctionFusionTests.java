package so.dang.cool.z.combination.fusion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static so.dang.cool.z.combination.TestFunctions.*;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import so.dang.cool.z.Z;
import so.dang.cool.z.annotation.Evil;

public class ToLongBiFunctionFusionTests {

    @Test
    void toLongBifn() {
        assertEquals(3L, addStringsAsLong.applyAsLong("1", "2"));
    }

    @Test
    void toLongBifn_deep() {
        assertEquals(3L, Z.with(addStringsAsLong).apply("1").applyAsLong("2"));
    }

    @Test
    void toLongBifn_to_longFn() {
        assertEquals(
            "3",
            Z.fuse(addStringsAsLong, longToString).apply("1").apply("2")
        );
    }

    @Test
    void toLongBifn_to_longFn_deep() {
        assertEquals(
            "3",
            Z.with(addStringsAsLong).fuse(longToString).apply("1").apply("2")
        );
    }

    @Test
    void toLongBifn_to_longFn_deeper() {
        assertEquals(
            "3",
            Z.with(addStringsAsLong).fusing(longToString).apply("1").apply("2")
        );
    }

    @Test
    void toLongBifn_to_longToDbl() {
        Stream
            .of(
                Z.fuse(addStringsAsLong, longToDouble),
                Z.with(addStringsAsLong).fuse(longToDouble),
                Z.with(addStringsAsLong).fusing(longToDouble)
            )
            .forEach(
                fusion -> {
                    assertEquals(3.0, fusion.apply("1").applyAsDouble("2"));
                }
            );
    }

    @Test
    void toLongBifn_to_longToInt() {
        Stream
            .of(
                Z.fuse(addStringsAsLong, longToInt),
                Z.with(addStringsAsLong).fuse(longToInt),
                Z.with(addStringsAsLong).fusing(longToInt)
            )
            .forEach(
                fusion -> {
                    assertEquals(9, fusion.apply("4").applyAsInt("5"));
                }
            );
    }

    @Test
    void toLongBifn_to_longPred() {
        Stream
            .of(
                Z.fuse(addStringsAsLong, isLongThree),
                Z.with(addStringsAsLong).fuse(isLongThree),
                Z.with(addStringsAsLong).fusing(isLongThree)
            )
            .forEach(
                fusion -> {
                    assertTrue(fusion.apply("-1").test("4"));
                }
            );
    }

    @Evil
    @Test
    void toLongBifn_to_longCns() {
        synchronized (consumedLongA) {
            Stream
                .of(
                    Z.fuse(addStringsAsLong, saveLongA),
                    Z.with(addStringsAsLong).fuse(saveLongA),
                    Z.with(addStringsAsLong).fusing(saveLongA)
                )
                .forEach(
                    fusion -> {
                        consumedLongA = 0L;

                        fusion.apply("2").accept("3");

                        assertEquals(5L, consumedLongA);
                    }
                );
        }
    }

    @Test
    void toLongBifn_to_longUnop() {
        Stream
            .of(
                Z.fuse(addStringsAsLong, addThreeToLong),
                Z.with(addStringsAsLong).fuse(addThreeToLong),
                Z.with(addStringsAsLong).fusing(addThreeToLong)
            )
            .forEach(
                fusion -> {
                    assertEquals(6L, fusion.apply("1").applyAsLong("2"));
                }
            );
    }

    @Test
    void toLongBifn_to_longBiop() {
        Stream
            .of(
                Z.fuse(addStringsAsLong, addLongs),
                Z.with(addStringsAsLong).fuse(addLongs)
            )
            .forEach(
                fusion -> {
                    assertEquals(
                        6L,
                        fusion.apply("1").apply("2").applyAsLong(3L)
                    );
                }
            );

        Stream
            .of(
                Z.with(addStringsAsLong).fuse(addLongs, 3L),
                Z.with(addStringsAsLong).fusing(addLongs, 3L)
            )
            .forEach(
                fusion -> {
                    assertEquals(6L, fusion.apply("1").applyAsLong("2"));
                }
            );
    }
}
