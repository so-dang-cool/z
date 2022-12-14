package so.dang.cool.z.combination.fusion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
        assertEquals(suppliedLong, Z.fuse(getLong).getAsLong());
    }

    @Test
    void longSup_to_longFn() {
        assertEquals("3", Z.fuse(getLong).fuse(longToString).get());
    }

    @Test
    void longSup_to_longToDbl() {
        assertEquals(3.0, Z.fuse(getLong).fuse(longToDouble).getAsDouble());
    }

    @Test
    void longSup_to_longToInt() {
        assertEquals(3, Z.fuse(getLong).fuse(longToInt).getAsInt());
    }

    @Test
    void longSup_to_longPred() {
        assertTrue(Z.fuse(getLong).fuse(isLong(3L)).getAsBoolean());
        assertFalse(Z.fuse(getLong).fuse(isLong(9999L)).getAsBoolean());
    }

    @Evil
    @Test
    void longSup_to_longCns() {
        synchronized (consumedLongA) {
            consumedLongA = 0L;
            Z.fuse(getLong).fuse(saveLongA).run();
            assertEquals(suppliedLong, consumedLongA);
        }
    }

    @Test
    void longSup_to_longUnop() {
        assertEquals(6, Z.fuse(getLong).fuse(addThreeToLong).getAsLong());
    }

    @Test
    void longSup_to_longBiop() {
        Stream
            .of(Z.fuse(getLong).fuse(addLongs))
            .forEach(
                fusion -> {
                    assertEquals(5, fusion.applyAsLong(2L));
                }
            );
    }
}
