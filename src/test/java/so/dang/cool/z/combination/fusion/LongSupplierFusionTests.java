package so.dang.cool.z.combination.fusion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static so.dang.cool.z.combination.TestFunctions.*;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import so.dang.cool.z.Z;
import so.dang.cool.z.annotation.Evil;

public class LongSupplierFusionTests {

    @Test
    void longSup() {
        assertEquals(suppliedLong, getLong.getAsLong());
        assertEquals(suppliedLong, Z.with(getLong).getAsLong());
    }

    @Test
    void longSup_to_longFn() {
        assertEquals("3", Z.fuse(getLong, longToString).get());
        assertEquals("3", Z.with(getLong).fuse(longToString).get());
        assertEquals("3", Z.with(getLong).fusing(longToString).get());
    }

    @Test
    void longSup_to_longToDbl() {
        assertEquals(3.0, Z.fuse(getLong, longToDouble).getAsDouble());
        assertEquals(3.0, Z.with(getLong).fuse(longToDouble).getAsDouble());
        assertEquals(3.0, Z.with(getLong).fusing(longToDouble).getAsDouble());
    }

    @Test
    void longSup_to_longToInt() {
        assertEquals(3, Z.fuse(getLong, longToInt).getAsInt());
        assertEquals(3, Z.with(getLong).fuse(longToInt).getAsInt());
        assertEquals(3, Z.with(getLong).fusing(longToInt).getAsInt());
    }

    @Test
    void longSup_to_longPred() {
        assertTrue(Z.fuse(getLong, isLongThree).getAsBoolean());
        assertTrue(Z.with(getLong).fuse(isLongThree).getAsBoolean());
        assertTrue(Z.with(getLong).fusing(isLongThree).getAsBoolean());
    }

    @Evil
    @Test
    void longSup_to_longCns() {
        synchronized (consumedLongA) {
            consumedLongA = 0L;

            Z.fuse(getLong, saveLongA).run();
            Z.with(getLong).fuse(saveLongA).run();
            Z.with(getLong).fusing(saveLongA).run();

            assertEquals(suppliedLong, consumedLongA);
        }
    }

    @Test
    void longSup_to_longUnop() {
        assertEquals(6, Z.fuse(getLong, addThreeToLong).getAsLong());
        assertEquals(6, Z.with(getLong).fuse(addThreeToLong).getAsLong());
        assertEquals(6, Z.with(getLong).fusing(addThreeToLong).getAsLong());
    }

    @Test
    void longSup_to_longBiop() {
        Stream
            .of(
                Z.fuse(getLong, addLongs),
                Z.with(getLong).fuse(addLongs),
                Z.with(getLong).fusing(addLongs)
            )
            .forEach(
                fusion -> {
                    assertEquals(5, fusion.applyAsLong(2L));
                }
            );

        Stream
            .of(
                Z.with(getLong).fuse(addLongs, 2L),
                Z.with(getLong).fusing(addLongs, 2L)
            )
            .forEach(
                fusion -> {
                    assertEquals(5, fusion.getAsLong());
                }
            );
    }
}
