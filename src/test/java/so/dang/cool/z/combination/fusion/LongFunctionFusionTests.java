package so.dang.cool.z.combination.fusion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static so.dang.cool.z.combination.TestFunctions.*;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import so.dang.cool.z.Z;
import so.dang.cool.z.annotation.Evil;

public class LongFunctionFusionTests {

    @Test
    void longFn() {
        assertEquals("1", longToString.apply(1L));
    }

    @Test
    void longFn_deep() {
        assertEquals("1", Z.fuse(longToString).apply(1L));
    }

    @Test
    void longFn_to_fn() {
        assertEquals(
            "1!",
            Z.fuse(longToString).fuse(addExclamationMark).apply(1L)
        );
    }

    @Test
    void longFn_to_bifn() {
        Stream
            .of(Z.fuse(longToString).fuse(concat))
            .forEach(
                fusion -> assertEquals("1.0", fusion.apply(1L).apply(".0"))
            );
    }

    @Test
    void longFn_to_toDblFn() {
        Stream
            .of(Z.fuse(longToString).fuse(stringToDouble))
            .forEach(fusion -> assertEquals(1L, fusion.applyAsDouble(1L)));
    }

    @Test
    void longFn_to_toDblBifn() {
        Stream
            .of(Z.fuse(longToString).fuse(addStringsAsDouble))
            .forEach(
                fusion -> assertEquals(3L, fusion.apply(1L).applyAsDouble("2"))
            );
    }

    @Test
    void longFn_to_toInt() {
        Stream
            .of(Z.fuse(longToString).fuse(stringToInt))
            .forEach(fusion -> assertEquals(1, fusion.applyAsInt(1L)));
    }

    @Test
    void longFn_to_toIntBifn() {
        Stream
            .of(Z.fuse(longToString).fuse(addStringsAsInt))
            .forEach(
                fusion -> assertEquals(3, fusion.apply(1L).applyAsInt("2"))
            );
    }

    @Test
    void longFn_to_toLongFn() {
        Stream
            .of(Z.fuse(longToString).fuse(stringToLong))
            .forEach(fusion -> assertEquals(1L, fusion.applyAsLong(1L)));
    }

    @Test
    void longFn_to_toLongBifn() {
        Stream
            .of(Z.fuse(longToString).fuse(addStringsAsLong))
            .forEach(
                fusion -> assertEquals(3L, fusion.apply(1L).applyAsLong("2"))
            );
    }

    @Test
    void longFn_to_pred() {
        assertFalse(Z.fuse(longToString).fuse("9999"::equals).test(1L));
        assertTrue(Z.fuse(longToString).fuse("1"::equals).test(1L));
    }

    @Test
    void longFn_to_bipred() {
        assertTrue(
            Z.fuse(longToString).fuse(doesStartWith).apply(1L).test("1")
        );
        assertFalse(
            Z.fuse(longToString).fuse(doesStartWith).apply(9999L).test("1")
        );
        assertTrue(
            Z.fuse(longToString).fuse(doesNotStartWith).apply(9999L).test("1")
        );
        assertFalse(
            Z.fuse(longToString).fuse(doesNotStartWith).apply(9999L).test("99")
        );
    }

    @Evil
    @Test
    void longFn_to_cns() {
        synchronized (consumedStringA) {
            Stream
                .of(Z.fuse(longToString).fuse(saveStringA))
                .forEach(
                    fusion -> {
                        consumedStringA = "";

                        fusion.accept(2L);

                        assertEquals("2", consumedStringA);
                    }
                );
        }
    }

    @Evil
    @Test
    void longFn_to_bicns() {
        synchronized (consumedStringB) {
            synchronized (consumedStringC) {
                Stream
                    .of(Z.fuse(longToString).fuse(saveStringsBandC))
                    .forEach(
                        fusion -> {
                            consumedStringB = "";
                            consumedStringC = "";

                            fusion.apply(3L).accept("two and a half");

                            assertEquals("3", consumedStringB);
                            assertEquals("two and a half", consumedStringC);
                        }
                    );
            }
        }
    }

    @Evil
    @Test
    void longFn_to_objDblCns() {
        synchronized (consumedStringD) {
            synchronized (consumedDoubleB) {
                Stream
                    .of(Z.fuse(longToString).fuse(saveStringDDoubleB))
                    .forEach(
                        fusion -> {
                            consumedStringD = "";
                            consumedDoubleB = 0.0;

                            fusion.apply(4L).accept(5.0);

                            assertEquals("4", consumedStringD);
                            assertEquals(5.0, consumedDoubleB);
                        }
                    );
            }
        }
    }

    @Evil
    @Test
    void longFn_to_objIntCns() {
        synchronized (consumedStringE) {
            synchronized (consumedIntB) {
                Stream
                    .of(Z.fuse(longToString).fuse(saveStringEIntB))
                    .forEach(
                        fusion -> {
                            consumedStringE = "";
                            consumedIntB = 0;

                            fusion.apply(6L).accept(6);

                            assertEquals("6", consumedStringE);
                            assertEquals(6, consumedIntB);
                        }
                    );
            }
        }
    }

    @Evil
    @Test
    void longFn_to_objLongCns() {
        synchronized (consumedStringF) {
            synchronized (consumedLongB) {
                Stream
                    .of(Z.fuse(longToString).fuse(saveStringFLongB))
                    .forEach(
                        fusion -> {
                            consumedStringF = "";
                            consumedLongB = 0L;

                            fusion.apply(8L).accept(8);

                            assertEquals("8", consumedStringF);
                            assertEquals(8, consumedLongB);
                        }
                    );
            }
        }
    }

    @Test
    void longFn_to_unop() {
        Stream
            .of(Z.fuse(longToString).fuse(addQuestionMark))
            .forEach(fusion -> assertEquals("10?", fusion.apply(10L)));
    }

    @Test
    void longFn_to_biop() {
        Stream
            .of(Z.fuse(longToString).fuse(relation))
            .forEach(
                fusion ->
                    assertEquals("same-ish", fusion.apply(11L).apply("11"))
            );
    }
}
