package so.dang.cool.z.combination.fusion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static so.dang.cool.z.combination.TestFunctions.*;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import so.dang.cool.z.Z;
import so.dang.cool.z.annotation.Evil;

public class IntSupplierFusionTests {

    @Test
    void intSup() {
        assertEquals(suppliedInt, getInt.getAsInt());
        assertEquals(suppliedInt, Z.with(getInt).resolve().getAsInt());
    }

    @Test
    void intSup_to_intFn() {
        assertEquals("2", Z.fuse(getInt, intToString).get());
        assertEquals("2", Z.with(getInt).fuse(intToString).get());
        assertEquals("2", Z.with(getInt).fusing(intToString).resolve().get());
    }

    @Test
    void intSup_to_intToDbl() {
        assertEquals(2.0, Z.fuse(getInt, intToDouble).getAsDouble());
        assertEquals(2.0, Z.with(getInt).fuse(intToDouble).getAsDouble());
        assertEquals(
            2.0,
            Z.with(getInt).fusing(intToDouble).resolve().getAsDouble()
        );
    }

    @Test
    void intSup_to_intToLong() {
        assertEquals(2L, Z.fuse(getInt, intToLong).getAsLong());
        assertEquals(2L, Z.with(getInt).fuse(intToLong).getAsLong());
        assertEquals(
            2L,
            Z.with(getInt).fusing(intToLong).resolve().getAsLong()
        );
    }

    @Test
    void intSup_to_intPred() {
        assertTrue(Z.fuse(getInt, isIntTwo).getAsBoolean());
        assertTrue(Z.with(getInt).fuse(isIntTwo).getAsBoolean());
        assertTrue(Z.with(getInt).fusing(isIntTwo).resolve().getAsBoolean());
    }

    @Evil
    @Test
    void intSup_to_intCns() {
        synchronized (consumedIntA) {
            consumedIntA = 0;

            Z.fuse(getInt, saveIntA).run();
            Z.with(getInt).fuse(saveIntA).run();
            Z.with(getInt).fusing(saveIntA).resolve().run();

            assertEquals(suppliedInt, consumedIntA);
        }
    }

    @Test
    void intSup_to_intUnop() {
        assertEquals(4, Z.fuse(getInt, addTwoToInt).getAsInt());
        assertEquals(4, Z.with(getInt).fuse(addTwoToInt).getAsInt());
        assertEquals(
            4,
            Z.with(getInt).fusing(addTwoToInt).resolve().getAsInt()
        );
    }

    @Test
    void intSup_to_intBiop() {
        Stream
            .of(
                Z.fuse(getInt, addInts),
                Z.with(getInt).fuse(addInts),
                Z.with(getInt).fusing(addInts).resolve()
            )
            .forEach(
                fusion -> {
                    assertEquals(4, fusion.applyAsInt(2));
                }
            );

        Stream
            .of(
                Z.with(getInt).fuse(addInts, 2),
                Z.with(getInt).fusing(addInts, 2).resolve()
            )
            .forEach(
                fusion -> {
                    assertEquals(4, fusion.getAsInt());
                }
            );
    }
}
