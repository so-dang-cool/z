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
        assertEquals("1!", Z.fuse(longToString, addExclamationMark).apply(1L));
    }

    @Test
    void longFn_to_fn_deep() {
        assertEquals(
            "1!",
            Z.fuse(longToString).fuse(addExclamationMark).apply(1L)
        );
    }

    @Test
    void longFn_to_fn_deeper() {
        assertEquals(
            "1!",
            Z.fuse(longToString).fusing(addExclamationMark).apply(1L)
        );
    }

    @Test
    void longFn_to_bifn() {
        Stream
            .of(
                Z.fuse(longToString, concat),
                Z.fuse(longToString).fuse(concat)
                //Z.with(longToString).fusing(concat)
            )
            .forEach(
                fusion -> assertEquals("1.0", fusion.apply(1L).apply(".0"))
            );

        Stream
            .of(
                Z.fuse(longToString).fuse(concat, ".0"),
                Z.fuse(longToString).fusing(concat, ".0")
            )
            .forEach(fusion -> assertEquals("1.0", fusion.apply(1L)));
    }

    @Test
    void longFn_to_toDblFn() {
        Stream
            .of(
                Z.fuse(longToString, stringToDouble),
                Z.fuse(longToString).fuse(stringToDouble),
                Z.fuse(longToString).fusing(stringToDouble)
            )
            .forEach(fusion -> assertEquals(1L, fusion.applyAsDouble(1L)));
    }

    @Test
    void longFn_to_toDblBifn() {
        Stream
            .of(
                Z.fuse(longToString, addStringsAsDouble),
                Z.fuse(longToString).fuse(addStringsAsDouble)
                //Z.with(longToString).fusing(addStringsAsDouble)
            )
            .forEach(
                fusion -> assertEquals(3L, fusion.apply(1L).applyAsDouble("2"))
            );

        Stream
            .of(
                Z.fuse(longToString).fuse(addStringsAsDouble, "2"),
                Z.fuse(longToString).fusing(addStringsAsDouble, "2")
            )
            .forEach(fusion -> assertEquals(3L, fusion.applyAsDouble(1L)));
    }

    @Test
    void longFn_to_toInt() {
        Stream
            .of(
                Z.fuse(longToString, stringToInt),
                Z.fuse(longToString).fuse(stringToInt),
                Z.fuse(longToString).fusing(stringToInt)
            )
            .forEach(fusion -> assertEquals(1, fusion.applyAsInt(1L)));
    }

    @Test
    void longFn_to_toIntBifn() {
        Stream
            .of(
                Z.fuse(longToString, addStringsAsInt),
                Z.fuse(longToString).fuse(addStringsAsInt)
                //Z.with(longToString).fusing(addStringsAsInt)
            )
            .forEach(
                fusion -> assertEquals(3, fusion.apply(1L).applyAsInt("2"))
            );

        Stream
            .of(
                Z.fuse(longToString).fuse(addStringsAsInt, "2"),
                Z.fuse(longToString).fusing(addStringsAsInt, "2")
            )
            .forEach(fusion -> assertEquals(3, fusion.applyAsInt(1L)));
    }

    @Test
    void longFn_to_toLongFn() {
        Stream
            .of(
                Z.fuse(longToString, stringToLong),
                Z.fuse(longToString).fuse(stringToLong),
                Z.fuse(longToString).fusing(stringToLong)
            )
            .forEach(fusion -> assertEquals(1L, fusion.applyAsLong(1L)));
    }

    @Test
    void longFn_to_toLongBifn() {
        Stream
            .of(
                Z.fuse(longToString, addStringsAsLong),
                Z.fuse(longToString).fuse(addStringsAsLong)
                //Z.with(longToString).fusing(addStringsAsLong)
            )
            .forEach(
                fusion -> assertEquals(3L, fusion.apply(1L).applyAsLong("2"))
            );

        Stream
            .of(
                Z.fuse(longToString).fuse(addStringsAsLong, "2"),
                Z.fuse(longToString).fusing(addStringsAsLong, "2")
            )
            .forEach(fusion -> assertEquals(3L, fusion.applyAsLong(1L)));
    }

    @Test
    void longFn_to_pred() {
        Stream
            .of(
                Z.fuse(longToString, isEmpty),
                Z.fuse(longToString).fuse(isEmpty),
                Z.fuse(longToString).fusing(isEmpty)
            )
            .forEach(fusion -> assertFalse(fusion.test(1L)));
    }

    @Test
    void longFn_to_bipred() {
        Stream
            .of(
                Z.fuse(longToString, startsWith),
                Z.fuse(longToString).fuse(startsWith)
                // Z.with(longToString).fusing(startsWith)
            )
            .forEach(fusion -> assertTrue(fusion.apply(1L).test("1")));

        Stream
            .of(
                Z.fuse(longToString).fuse(startsWith, "1"),
                Z.fuse(longToString).fusing(startsWith, "1")
            )
            .forEach(fusion -> assertTrue(fusion.test(1L)));
    }

    @Evil
    @Test
    void longFn_to_cns() {
        synchronized (consumedStringA) {
            Stream
                .of(
                    Z.fuse(longToString, saveStringA),
                    Z.fuse(longToString).fuse(saveStringA),
                    Z.fuse(longToString).fusing(saveStringA)
                )
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
                    .of(
                        Z.fuse(longToString, saveStringsBandC),
                        Z.fuse(longToString).fuse(saveStringsBandC)
                        //Z.with(longToString).fusing(saveStringsBandC)
                    )
                    .forEach(
                        fusion -> {
                            consumedStringB = "";
                            consumedStringC = "";

                            fusion.apply(3L).accept("two and a half");

                            assertEquals("3", consumedStringB);
                            assertEquals("two and a half", consumedStringC);
                        }
                    );

                Stream
                    .of(
                        Z
                            .fuse(longToString)
                            .fuse(saveStringsBandC, "two and a half"),
                        Z
                            .fuse(longToString)
                            .fusing(saveStringsBandC, "two and a half")
                    )
                    .forEach(
                        fusion -> {
                            consumedStringB = "";
                            consumedStringC = "";

                            fusion.accept(3L);

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
                    .of(
                        Z.fuse(longToString, saveStringDDoubleB),
                        Z.fuse(longToString).fuse(saveStringDDoubleB)
                        // Z.with(longToString).fusing(saveStringDDoubleB)
                    )
                    .forEach(
                        fusion -> {
                            consumedStringD = "";
                            consumedDoubleB = 0.0;

                            fusion.apply(4L).accept(5.0);

                            assertEquals("4", consumedStringD);
                            assertEquals(5.0, consumedDoubleB);
                        }
                    );

                Stream
                    .of(
                        Z.fuse(longToString).fuse(saveStringDDoubleB, 5.0),
                        Z.fuse(longToString).fusing(saveStringDDoubleB, 5.0)
                    )
                    .forEach(
                        fusion -> {
                            consumedStringD = "";
                            consumedDoubleB = 0.0;

                            fusion.accept(4L);

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
                    .of(
                        Z.fuse(longToString, saveStringEIntB),
                        Z.fuse(longToString).fuse(saveStringEIntB)
                        // Z.with(longToString).fusing(saveStringEIntB)
                    )
                    .forEach(
                        fusion -> {
                            consumedStringE = "";
                            consumedIntB = 0;

                            fusion.apply(6L).accept(6);

                            assertEquals("6", consumedStringE);
                            assertEquals(6, consumedIntB);
                        }
                    );

                Stream
                    .of(
                        Z.fuse(longToString).fuse(saveStringEIntB, 6),
                        Z.fuse(longToString).fusing(saveStringEIntB, 6)
                    )
                    .forEach(
                        fusion -> {
                            consumedStringE = "";
                            consumedIntB = 0;

                            fusion.accept(6L);

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
                    .of(
                        Z.fuse(longToString, saveStringFLongB),
                        Z.fuse(longToString).fuse(saveStringFLongB)
                        // Z.with(longToString).fusing(saveStringFLongB)
                    )
                    .forEach(
                        fusion -> {
                            consumedStringF = "";
                            consumedLongB = 0L;

                            fusion.apply(8L).accept(8);

                            assertEquals("8", consumedStringF);
                            assertEquals(8, consumedLongB);
                        }
                    );

                Stream
                    .of(
                        Z.fuse(longToString).fuse(saveStringFLongB, 8),
                        Z.fuse(longToString).fusing(saveStringFLongB, 8)
                    )
                    .forEach(
                        fusion -> {
                            consumedStringF = "";
                            consumedLongB = 0L;

                            fusion.accept(8L);

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
            .of(
                Z.fuse(longToString, addQuestionMark),
                Z.fuse(longToString).fuse(addQuestionMark),
                Z.fuse(longToString).fusing(addQuestionMark)
            )
            .forEach(fusion -> assertEquals("10?", fusion.apply(10L)));
    }

    @Test
    void longFn_to_biop() {
        Stream
            .of(
                Z.fuse(longToString, relation),
                Z.fuse(longToString).fuse(relation)
                // Z.with(longToString).fusing(relation)
            )
            .forEach(
                fusion ->
                    assertEquals("same-ish", fusion.apply(11L).apply("11"))
            );

        Stream
            .of(
                Z.fuse(longToString).fuse(relation, "11"),
                Z.fuse(longToString).fusing(relation, "11")
            )
            .forEach(fusion -> assertEquals("same-ish", fusion.apply(11L)));
    }
}
