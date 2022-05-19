package so.dang.cool.z.combination.fusion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static so.dang.cool.z.combination.TestFunctions.*;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import so.dang.cool.z.Z;
import so.dang.cool.z.annotation.Evil;

public class LongPredicateFusionTests {

    @Test
    void longPred() {
        assertTrue(isLongThree.test(3L));

        assertTrue(Z.fuse(isLongThree).test(3L));
    }

    @Test
    void longPred_to_boolFn() {
        Stream
            .of(
                Z.fuse(isLongThree, booleanToString),
                Z.fuse(isLongThree).fuse(booleanToString),
                Z.fuse(isLongThree).fusing(booleanToString)
            )
            .forEach(
                fusion -> {
                    assertEquals("true", fusion.apply(3L));
                }
            );
    }

    @Test
    void longPred_to_toDblFn() {
        Stream
            .of(
                Z.fuse(isLongThree, maybeOneAsDouble),
                Z.fuse(isLongThree).fuse(maybeOneAsDouble),
                Z.fuse(isLongThree).fusing(maybeOneAsDouble)
            )
            .forEach(
                fusion -> {
                    assertEquals(1.0, fusion.applyAsDouble(3L));
                }
            );
    }

    @Test
    void longPred_to_toIntFn() {
        Stream
            .of(
                Z.fuse(isLongThree, maybeTwoAsInt),
                Z.fuse(isLongThree).fuse(maybeTwoAsInt),
                Z.fuse(isLongThree).fusing(maybeTwoAsInt)
            )
            .forEach(
                fusion -> {
                    assertEquals(2, fusion.applyAsInt(3L));
                }
            );
    }

    @Test
    void longPred_to_toLongFn() {
        Stream
            .of(
                Z.fuse(isLongThree, maybeThreeAsLong),
                Z.fuse(isLongThree).fuse(maybeThreeAsLong),
                Z.fuse(isLongThree).fusing(maybeThreeAsLong)
            )
            .forEach(
                fusion -> {
                    assertEquals(3L, fusion.applyAsLong(3L));
                }
            );
    }

    @Test
    void longPred_to_boolPred() {
        Stream
            .of(
                Z.fuse(isLongThree, not),
                Z.fuse(isLongThree).fuse(not),
                Z.fuse(isLongThree).fusing(not)
            )
            .forEach(
                fusion -> {
                    assertFalse(fusion.test(3L));
                }
            );
    }

    @Evil
    @Test
    void longPred_to_cns() {
        synchronized (consumedBooleanA) {
            Stream
                .of(
                    Z.fuse(isLongThree, saveBooleanA),
                    Z.fuse(isLongThree).fuse(saveBooleanA),
                    Z.fuse(isLongThree).fusing(saveBooleanA)
                )
                .forEach(
                    fusion -> {
                        consumedBooleanA = false;

                        fusion.accept(3L);

                        assertTrue(consumedBooleanA);
                    }
                );
        }
    }
}
