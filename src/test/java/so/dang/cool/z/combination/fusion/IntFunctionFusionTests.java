package so.dang.cool.z.combination.fusion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static so.dang.cool.z.combination.TestFunctions.*;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import so.dang.cool.z.Z;
import so.dang.cool.z.annotation.Evil;

public class IntFunctionFusionTests {

    @Test
    void intFn() {
        assertEquals("1", intToString.apply(1));
    }

    @Test
    void intFn_deep() {
        assertEquals("1", Z.fuse(intToString).apply(1));
    }

    @Test
    void intFn_to_fn() {
        assertEquals("1!", Z.fuse(intToString, addExclamationMark).apply(1));
    }

    @Test
    void intFn_to_fn_deep() {
        assertEquals(
            "1!",
            Z.fuse(intToString).fuse(addExclamationMark).apply(1)
        );
    }

    @Test
    void intFn_to_fn_deeper() {
        assertEquals(
            "1!",
            Z.fuse(intToString).fusing(addExclamationMark).apply(1)
        );
    }

    @Test
    void intFn_to_bifn() {
        Stream
            .of(
                Z.fuse(intToString, concat),
                Z.fuse(intToString).fuse(concat)
                //Z.with(intToString).fusing(concat)
            )
            .forEach(
                fusion -> assertEquals("1.0", fusion.apply(1).apply(".0"))
            );

        Stream
            .of(
                Z.fuse(intToString).fuse(concat, ".0"),
                Z.fuse(intToString).fusing(concat, ".0")
            )
            .forEach(fusion -> assertEquals("1.0", fusion.apply(1)));
    }

    @Test
    void intFn_to_toDblFn() {
        Stream
            .of(
                Z.fuse(intToString, stringToDouble),
                Z.fuse(intToString).fuse(stringToDouble),
                Z.fuse(intToString).fusing(stringToDouble)
            )
            .forEach(fusion -> assertEquals(1, fusion.applyAsDouble(1)));
    }

    @Test
    void intFn_to_toDblBifn() {
        Stream
            .of(
                Z.fuse(intToString, addStringsAsDouble),
                Z.fuse(intToString).fuse(addStringsAsDouble)
                //Z.with(intToString).fusing(addStringsAsDouble)
            )
            .forEach(
                fusion -> assertEquals(3, fusion.apply(1).applyAsDouble("2"))
            );

        Stream
            .of(
                Z.fuse(intToString).fuse(addStringsAsDouble, "2"),
                Z.fuse(intToString).fusing(addStringsAsDouble, "2")
            )
            .forEach(fusion -> assertEquals(3, fusion.applyAsDouble(1)));
    }

    @Test
    void intFn_to_toIntFn() {
        Stream
            .of(
                Z.fuse(intToString, stringToInt),
                Z.fuse(intToString).fuse(stringToInt),
                Z.fuse(intToString).fusing(stringToInt)
            )
            .forEach(fusion -> assertEquals(1, fusion.applyAsInt(1)));
    }

    @Test
    void intFn_to_toIntBifn() {
        Stream
            .of(
                Z.fuse(intToString, addStringsAsInt),
                Z.fuse(intToString).fuse(addStringsAsInt)
                //Z.with(intToString).fusing(addStringsAsInt)
            )
            .forEach(
                fusion -> assertEquals(3, fusion.apply(1).applyAsInt("2"))
            );

        Stream
            .of(
                Z.fuse(intToString).fuse(addStringsAsInt, "2"),
                Z.fuse(intToString).fusing(addStringsAsInt, "2")
            )
            .forEach(fusion -> assertEquals(3, fusion.applyAsInt(1)));
    }

    @Test
    void intFn_to_toLong() {
        Stream
            .of(
                Z.fuse(intToString, stringToLong),
                Z.fuse(intToString).fuse(stringToLong),
                Z.fuse(intToString).fusing(stringToLong)
            )
            .forEach(fusion -> assertEquals(1L, fusion.applyAsLong(1)));
    }

    @Test
    void intFn_to_toLongBifn() {
        Stream
            .of(
                Z.fuse(intToString, addStringsAsLong),
                Z.fuse(intToString).fuse(addStringsAsLong)
                //Z.with(intToString).fusing(addStringsAsLong)
            )
            .forEach(
                fusion -> assertEquals(3L, fusion.apply(1).applyAsLong("2"))
            );

        Stream
            .of(
                Z.fuse(intToString).fuse(addStringsAsLong, "2"),
                Z.fuse(intToString).fusing(addStringsAsLong, "2")
            )
            .forEach(fusion -> assertEquals(3L, fusion.applyAsLong(1)));
    }

    @Test
    void intFn_to_pred() {
        Stream
            .of(
                Z.fuse(intToString, isEmpty),
                Z.fuse(intToString).fuse(isEmpty),
                Z.fuse(intToString).fusing(isEmpty)
            )
            .forEach(fusion -> assertFalse(fusion.test(1)));
    }

    @Test
    void intFn_to_bipred() {
        Stream
            .of(
                Z.fuse(intToString, startsWith),
                Z.fuse(intToString).fuse(startsWith)
                // Z.with(intToString).fusing(startsWith)
            )
            .forEach(fusion -> assertTrue(fusion.apply(1).test("1")));

        Stream
            .of(
                Z.fuse(intToString).fuse(startsWith, "1"),
                Z.fuse(intToString).fusing(startsWith, "1")
            )
            .forEach(fusion -> assertTrue(fusion.test(1)));
    }

    @Evil
    @Test
    void intFn_to_cns() {
        synchronized (consumedStringA) {
            Stream
                .of(
                    Z.fuse(intToString, saveStringA),
                    Z.fuse(intToString).fuse(saveStringA),
                    Z.fuse(intToString).fusing(saveStringA)
                )
                .forEach(
                    fusion -> {
                        consumedStringA = "";

                        fusion.accept(2);

                        assertEquals("2", consumedStringA);
                    }
                );
        }
    }

    @Evil
    @Test
    void intFn_to_bicns() {
        synchronized (consumedStringB) {
            synchronized (consumedStringC) {
                Stream
                    .of(
                        Z.fuse(intToString, saveStringsBandC),
                        Z.fuse(intToString).fuse(saveStringsBandC)
                        //Z.with(intToString).fusing(saveStringsBandC)
                    )
                    .forEach(
                        fusion -> {
                            consumedStringB = "";
                            consumedStringC = "";

                            fusion.apply(3).accept("two and a half");

                            assertEquals("3", consumedStringB);
                            assertEquals("two and a half", consumedStringC);
                        }
                    );

                Stream
                    .of(
                        Z
                            .fuse(intToString)
                            .fuse(saveStringsBandC, "two and a half"),
                        Z
                            .fuse(intToString)
                            .fusing(saveStringsBandC, "two and a half")
                    )
                    .forEach(
                        fusion -> {
                            consumedStringB = "";
                            consumedStringC = "";

                            fusion.accept(3);

                            assertEquals("3", consumedStringB);
                            assertEquals("two and a half", consumedStringC);
                        }
                    );
            }
        }
    }

    @Evil
    @Test
    void intFn_to_objDblCns() {
        synchronized (consumedStringD) {
            synchronized (consumedDoubleB) {
                Stream
                    .of(
                        Z.fuse(intToString, saveStringDDoubleB),
                        Z.fuse(intToString).fuse(saveStringDDoubleB)
                        // Z.with(intToString).fusing(saveStringDDoubleB)
                    )
                    .forEach(
                        fusion -> {
                            consumedStringD = "";
                            consumedDoubleB = 0.0;

                            fusion.apply(4).accept(5.0);

                            assertEquals("4", consumedStringD);
                            assertEquals(5.0, consumedDoubleB);
                        }
                    );

                Stream
                    .of(
                        Z.fuse(intToString).fuse(saveStringDDoubleB, 5.0),
                        Z.fuse(intToString).fusing(saveStringDDoubleB, 5.0)
                    )
                    .forEach(
                        fusion -> {
                            consumedStringD = "";
                            consumedDoubleB = 0.0;

                            fusion.accept(4);

                            assertEquals("4", consumedStringD);
                            assertEquals(5.0, consumedDoubleB);
                        }
                    );
            }
        }
    }

    @Evil
    @Test
    void intFn_to_objIntCns() {
        synchronized (consumedStringE) {
            synchronized (consumedIntB) {
                Stream
                    .of(
                        Z.fuse(intToString, saveStringEIntB),
                        Z.fuse(intToString).fuse(saveStringEIntB)
                        // Z.with(intToString).fusing(saveStringEIntB)
                    )
                    .forEach(
                        fusion -> {
                            consumedStringE = "";
                            consumedIntB = 0;

                            fusion.apply(8).accept(8);

                            assertEquals("8", consumedStringE);
                            assertEquals(8, consumedIntB);
                        }
                    );

                Stream
                    .of(
                        Z.fuse(intToString).fuse(saveStringEIntB, 8),
                        Z.fuse(intToString).fusing(saveStringEIntB, 8)
                    )
                    .forEach(
                        fusion -> {
                            consumedStringE = "";
                            consumedIntB = 0;

                            fusion.accept(8);

                            assertEquals("8", consumedStringE);
                            assertEquals(8, consumedIntB);
                        }
                    );
            }
        }
    }

    @Evil
    @Test
    void intFn_to_objLongCns() {
        synchronized (consumedStringF) {
            synchronized (consumedLongB) {
                Stream
                    .of(
                        Z.fuse(intToString, saveStringFLongB),
                        Z.fuse(intToString).fuse(saveStringFLongB)
                        // Z.with(intToString).fusing(saveStringFLongB)
                    )
                    .forEach(
                        fusion -> {
                            consumedStringF = "";
                            consumedLongB = 0L;

                            fusion.apply(6).accept(6L);

                            assertEquals("6", consumedStringF);
                            assertEquals(6L, consumedLongB);
                        }
                    );

                Stream
                    .of(
                        Z.fuse(intToString).fuse(saveStringFLongB, 6L),
                        Z.fuse(intToString).fusing(saveStringFLongB, 6L)
                    )
                    .forEach(
                        fusion -> {
                            consumedStringF = "";
                            consumedLongB = 0L;

                            fusion.accept(6);

                            assertEquals("6", consumedStringF);
                            assertEquals(6L, consumedLongB);
                        }
                    );
            }
        }
    }

    @Test
    void intFn_to_unop() {
        Stream
            .of(
                Z.fuse(intToString, addQuestionMark),
                Z.fuse(intToString).fuse(addQuestionMark),
                Z.fuse(intToString).fusing(addQuestionMark)
            )
            .forEach(fusion -> assertEquals("10?", fusion.apply(10)));
    }

    @Test
    void intFn_to_biop() {
        Stream
            .of(
                Z.fuse(intToString, relation),
                Z.fuse(intToString).fuse(relation)
                // Z.with(intToString).fusing(relation)
            )
            .forEach(
                fusion -> assertEquals("same-ish", fusion.apply(11).apply("11"))
            );

        Stream
            .of(
                Z.fuse(intToString).fuse(relation, "11"),
                Z.fuse(intToString).fusing(relation, "11")
            )
            .forEach(fusion -> assertEquals("same-ish", fusion.apply(11)));
    }
}
