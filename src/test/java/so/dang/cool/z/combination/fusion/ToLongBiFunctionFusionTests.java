package so.dang.cool.z.combination.fusion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
        assertEquals(3L, Z.fuse(addStringsAsLong).apply("1").applyAsLong("2"));
    }

    @Test
    void toLongBifn_to_longFn() {
        assertEquals(
            "3",
            Z.fuse(addStringsAsLong).fuse(longToString).apply("1").apply("2")
        );
    }

    @Test
    void toLongBifn_to_longToDbl() {
        Stream
            .of(Z.fuse(addStringsAsLong).fuse(longToDouble))
            .forEach(
                fusion -> {
                    assertEquals(3.0, fusion.apply("1").applyAsDouble("2"));
                }
            );
    }

    @Test
    void toLongBifn_to_longToInt() {
        Stream
            .of(Z.fuse(addStringsAsLong).fuse(longToInt))
            .forEach(
                fusion -> {
                    assertEquals(9, fusion.apply("4").applyAsInt("5"));
                }
            );
    }

    @Test
    void toLongBifn_to_longPred() {
        assertTrue(
            Z.fuse(addStringsAsLong).fuse(isLong(3L)).apply("-1").test("4")
        );
        assertFalse(
            Z.fuse(addStringsAsLong).fuse(isLong(9999L)).apply("-1").test("4")
        );
    }

    @Evil
    @Test
    void toLongBifn_to_longCns() {
        synchronized (consumedLongA) {
            Stream
                .of(Z.fuse(addStringsAsLong).fuse(saveLongA))
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
            .of(Z.fuse(addStringsAsLong).fuse(addThreeToLong))
            .forEach(
                fusion -> {
                    assertEquals(6L, fusion.apply("1").applyAsLong("2"));
                }
            );
    }

    @Test
    void toLongBifn_to_longBiop() {
        Stream
            .of(Z.fuse(addStringsAsLong).fuse(addLongs))
            .forEach(
                fusion -> {
                    assertEquals(
                        6L,
                        fusion.apply("1").apply("2").applyAsLong(3L)
                    );
                }
            );
    }
}
