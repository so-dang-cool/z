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
        assertEquals("1", Z.with(longToString).resolve().apply(1L));
    }

    @Test
    void longFn_to_fn() {
        assertEquals("1!", Z.fuse(longToString, addExclamationMark).apply(1L));
    }

    @Test
    void longFn_to_fn_deep() {
        assertEquals(
            "1!",
            Z.with(longToString).fuse(addExclamationMark).apply(1L)
        );
    }

    @Test
    void longFn_to_fn_deeper() {
        assertEquals(
            "1!",
            Z.with(longToString).fusing(addExclamationMark).resolve().apply(1L)
        );
    }

    @Test
    void longFn_to_bifn() {
        Stream
            .of(
                Z.fuse(longToString, concat),
                Z.with(longToString).fuse(concat)
                //Z.with(longToString).fusing(concat).resolve()
            )
            .forEach(
                fusion -> assertEquals("1.0", fusion.apply(1L).apply(".0"))
            );

        Stream
            .of(
                Z.with(longToString).fuse(concat, ".0"),
                Z.with(longToString).fusing(concat, ".0").resolve()
            )
            .forEach(fusion -> assertEquals("1.0", fusion.apply(1L)));
    }

    @Test
    void longFn_to_toDblFn() {
        Stream
            .of(
                Z.fuse(longToString, stringToDouble),
                Z.with(longToString).fuse(stringToDouble),
                Z.with(longToString).fusing(stringToDouble).resolve()
            )
            .forEach(fusion -> assertEquals(1L, fusion.applyAsDouble(1L)));
    }

    @Test
    void longFn_to_toDblBifn() {
        Stream
            .of(
                Z.fuse(longToString, addStringsAsDouble),
                Z.with(longToString).fuse(addStringsAsDouble)
                //Z.with(longToString).fusing(addStringsAsDouble).resolve()
            )
            .forEach(
                fusion -> assertEquals(3L, fusion.apply(1L).applyAsDouble("2"))
            );

        Stream
            .of(
                Z.with(longToString).fuse(addStringsAsDouble, "2"),
                Z.with(longToString).fusing(addStringsAsDouble, "2").resolve()
            )
            .forEach(fusion -> assertEquals(3L, fusion.applyAsDouble(1L)));
    }

    @Test
    void longFn_to_toInt() {
        Stream
            .of(
                Z.fuse(longToString, stringToInt),
                Z.with(longToString).fuse(stringToInt),
                Z.with(longToString).fusing(stringToInt).resolve()
            )
            .forEach(fusion -> assertEquals(1, fusion.applyAsInt(1L)));
    }

    @Test
    void longFn_to_toIntBifn() {
        Stream
            .of(
                Z.fuse(longToString, addStringsAsInt),
                Z.with(longToString).fuse(addStringsAsInt)
                //Z.with(longToString).fusing(addStringsAsInt).resolve()
            )
            .forEach(
                fusion -> assertEquals(3, fusion.apply(1L).applyAsInt("2"))
            );

        Stream
            .of(
                Z.with(longToString).fuse(addStringsAsInt, "2"),
                Z.with(longToString).fusing(addStringsAsInt, "2").resolve()
            )
            .forEach(fusion -> assertEquals(3, fusion.applyAsInt(1L)));
    }

    @Test
    void longFn_to_toLongFn() {
        Stream
            .of(
                Z.fuse(longToString, stringToLong),
                Z.with(longToString).fuse(stringToLong),
                Z.with(longToString).fusing(stringToLong).resolve()
            )
            .forEach(fusion -> assertEquals(1L, fusion.applyAsLong(1L)));
    }

    @Test
    void longFn_to_toLongBifn() {
        Stream
            .of(
                Z.fuse(longToString, addStringsAsLong),
                Z.with(longToString).fuse(addStringsAsLong)
                //Z.with(longToString).fusing(addStringsAsLong).resolve()
            )
            .forEach(
                fusion -> assertEquals(3L, fusion.apply(1L).applyAsLong("2"))
            );

        Stream
            .of(
                Z.with(longToString).fuse(addStringsAsLong, "2"),
                Z.with(longToString).fusing(addStringsAsLong, "2").resolve()
            )
            .forEach(fusion -> assertEquals(3L, fusion.applyAsLong(1L)));
    }

    @Test
    void longFn_to_pred() {
        Stream
            .of(
                Z.fuse(longToString, isEmpty),
                Z.with(longToString).fuse(isEmpty),
                Z.with(longToString).fusing(isEmpty).resolve()
            )
            .forEach(fusion -> assertFalse(fusion.test(1L)));
    }

    @Test
    void longFn_to_bipred() {
        Stream
            .of(
                Z.fuse(longToString, startsWith),
                Z.with(longToString).fuse(startsWith)
                // Z.with(longToString).fusing(startsWith).resolve()
            )
            .forEach(fusion -> assertTrue(fusion.apply(1L).test("1")));

        Stream
            .of(
                Z.with(longToString).fuse(startsWith, "1"),
                Z.with(longToString).fusing(startsWith, "1").resolve()
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
                    Z.with(longToString).fuse(saveStringA),
                    Z.with(longToString).fusing(saveStringA).resolve()
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
                        Z.with(longToString).fuse(saveStringsBandC)
                        //Z.with(longToString).fusing(saveStringsBandC).resolve()
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
                            .with(longToString)
                            .fuse(saveStringsBandC, "two and a half"),
                        Z
                            .with(longToString)
                            .fusing(saveStringsBandC, "two and a half")
                            .resolve()
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
                        Z.with(longToString).fuse(saveStringDDoubleB)
                        // Z.with(longToString).fusing(saveStringDDoubleB).resolve()
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
                        Z.with(longToString).fuse(saveStringDDoubleB, 5.0),
                        Z
                            .with(longToString)
                            .fusing(saveStringDDoubleB, 5.0)
                            .resolve()
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
                        Z.with(longToString).fuse(saveStringEIntB)
                        // Z.with(longToString).fusing(saveStringEIntB).resolve()
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
                        Z.with(longToString).fuse(saveStringEIntB, 6),
                        Z
                            .with(longToString)
                            .fusing(saveStringEIntB, 6)
                            .resolve()
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
                        Z.with(longToString).fuse(saveStringFLongB)
                        // Z.with(longToString).fusing(saveStringFLongB).resolve()
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
                        Z.with(longToString).fuse(saveStringFLongB, 8),
                        Z
                            .with(longToString)
                            .fusing(saveStringFLongB, 8)
                            .resolve()
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
                Z.with(longToString).fuse(addQuestionMark),
                Z.with(longToString).fusing(addQuestionMark).resolve()
            )
            .forEach(fusion -> assertEquals("10?", fusion.apply(10L)));
    }

    @Test
    void longFn_to_biop() {
        Stream
            .of(
                Z.fuse(longToString, relation),
                Z.with(longToString).fuse(relation)
                // Z.with(longToString).fusing(relation).resolve()
            )
            .forEach(
                fusion ->
                    assertEquals("same-ish", fusion.apply(11L).apply("11"))
            );

        Stream
            .of(
                Z.with(longToString).fuse(relation, "11"),
                Z.with(longToString).fusing(relation, "11").resolve()
            )
            .forEach(fusion -> assertEquals("same-ish", fusion.apply(11L)));
    }
}
