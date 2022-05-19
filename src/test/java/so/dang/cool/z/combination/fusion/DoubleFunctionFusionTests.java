package so.dang.cool.z.combination.fusion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static so.dang.cool.z.combination.TestFunctions.*;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import so.dang.cool.z.Z;
import so.dang.cool.z.annotation.Evil;

public class DoubleFunctionFusionTests {

    @Test
    void dblFn() {
        assertEquals("1.0", doubleToString.apply(1.0));
    }

    @Test
    void dblFn_deep() {
        assertEquals("1.0", Z.with(doubleToString).apply(1.0));
    }

    @Test
    void dblFn_to_fn() {
        assertEquals(
            "1.0!",
            Z.fuse(doubleToString, addExclamationMark).apply(1.0)
        );
    }

    @Test
    void dblFn_to_fn_deep() {
        assertEquals(
            "1.0!",
            Z.with(doubleToString).fuse(addExclamationMark).apply(1.0)
        );
    }

    @Test
    void dblFn_to_fn_deeper() {
        assertEquals(
            "1.0!",
            Z.with(doubleToString).fusing(addExclamationMark).apply(1.0)
        );
    }

    @Test
    void dblFn_to_bifn() {
        Stream
            .of(
                Z.fuse(doubleToString, concat),
                Z.with(doubleToString).fuse(concat)
                //Z.with(doubleToString).fusing(concat)
            )
            .forEach(
                fusion -> assertEquals("1.0.0", fusion.apply(1.0).apply(".0"))
            );

        Stream
            .of(
                Z.with(doubleToString).fuse(concat, ".0"),
                Z.with(doubleToString).fusing(concat, ".0")
            )
            .forEach(fusion -> assertEquals("1.0.0", fusion.apply(1.0)));
    }

    @Test
    void dblFn_to_toDblFn() {
        Stream
            .of(
                Z.fuse(doubleToString, stringToDouble),
                Z.with(doubleToString).fuse(stringToDouble),
                Z.with(doubleToString).fusing(stringToDouble)
            )
            .forEach(fusion -> assertEquals(1.0, fusion.applyAsDouble(1.0)));
    }

    @Test
    void dblFn_to_toDblBifn() {
        Stream
            .of(
                Z.fuse(doubleToString, addStringsAsDouble),
                Z.with(doubleToString).fuse(addStringsAsDouble)
                //Z.with(doubleToString).fusing(addStringsAsDouble)
            )
            .forEach(
                fusion ->
                    assertEquals(3.0, fusion.apply(1.0).applyAsDouble("2.0"))
            );

        Stream
            .of(
                Z.with(doubleToString).fuse(addStringsAsDouble, "2.0"),
                Z.with(doubleToString).fusing(addStringsAsDouble, "2.0")
            )
            .forEach(fusion -> assertEquals(3.0, fusion.applyAsDouble(1.0)));
    }

    @Test
    void dblFn_to_toInt() {
        Stream
            .of(
                Z.fuse(doubleFloorToString, stringToInt),
                Z.with(doubleFloorToString).fuse(stringToInt),
                Z.with(doubleFloorToString).fusing(stringToInt)
            )
            .forEach(fusion -> assertEquals(1, fusion.applyAsInt(1.0)));
    }

    @Test
    void dblFn_to_toIntBifn() {
        Stream
            .of(
                Z.fuse(doubleFloorToString, addStringsAsInt),
                Z.with(doubleFloorToString).fuse(addStringsAsInt)
                //Z.with(doubleFloorToString).fusing(addStringsAsInt)
            )
            .forEach(
                fusion -> assertEquals(3, fusion.apply(1.0).applyAsInt("2"))
            );

        Stream
            .of(
                Z.with(doubleFloorToString).fuse(addStringsAsInt, "2"),
                Z.with(doubleFloorToString).fusing(addStringsAsInt, "2")
            )
            .forEach(fusion -> assertEquals(3, fusion.applyAsInt(1.0)));
    }

    @Test
    void dblFn_to_toLongFn() {
        Stream
            .of(
                Z.fuse(doubleFloorToString, stringToLong),
                Z.with(doubleFloorToString).fuse(stringToLong),
                Z.with(doubleFloorToString).fusing(stringToLong)
            )
            .forEach(fusion -> assertEquals(1L, fusion.applyAsLong(1.0)));
    }

    @Test
    void dblFn_to_toLongBifn() {
        Stream
            .of(
                Z.fuse(doubleFloorToString, addStringsAsLong),
                Z.with(doubleFloorToString).fuse(addStringsAsLong)
                //Z.with(doubleFloorToString).fusing(addStringsAsLong)
            )
            .forEach(
                fusion -> assertEquals(3L, fusion.apply(1.0).applyAsLong("2"))
            );

        Stream
            .of(
                Z.with(doubleFloorToString).fuse(addStringsAsLong, "2"),
                Z.with(doubleFloorToString).fusing(addStringsAsLong, "2")
            )
            .forEach(fusion -> assertEquals(3L, fusion.applyAsLong(1.0)));
    }

    @Test
    void dblFn_to_pred() {
        Stream
            .of(
                Z.fuse(doubleToString, isEmpty),
                Z.with(doubleToString).fuse(isEmpty),
                Z.with(doubleToString).fusing(isEmpty)
            )
            .forEach(fusion -> assertFalse(fusion.test(1.0)));
    }

    @Test
    void dblFn_to_bipred() {
        Stream
            .of(
                Z.fuse(doubleToString, startsWith),
                Z.with(doubleToString).fuse(startsWith)
                // Z.with(doubleToString).fusing(startsWith)
            )
            .forEach(fusion -> assertTrue(fusion.apply(1.0).test("1")));

        Stream
            .of(
                Z.with(doubleToString).fuse(startsWith, "1"),
                Z.with(doubleToString).fusing(startsWith, "1")
            )
            .forEach(fusion -> assertTrue(fusion.test(1.0)));
    }

    @Evil
    @Test
    void dblFn_to_cns() {
        synchronized (consumedStringA) {
            Stream
                .of(
                    Z.fuse(doubleToString, saveStringA),
                    Z.with(doubleToString).fuse(saveStringA),
                    Z.with(doubleToString).fusing(saveStringA)
                )
                .forEach(
                    fusion -> {
                        consumedStringA = "";

                        fusion.accept(1.5);

                        assertEquals("1.5", consumedStringA);
                    }
                );
        }
    }

    @Evil
    @Test
    void dblFn_to_bicns() {
        synchronized (consumedStringB) {
            synchronized (consumedStringC) {
                Stream
                    .of(
                        Z.fuse(doubleToString, saveStringsBandC),
                        Z.with(doubleToString).fuse(saveStringsBandC)
                        //Z.with(doubleToString).fusing(saveStringsBandC)
                    )
                    .forEach(
                        fusion -> {
                            consumedStringB = "";
                            consumedStringC = "";

                            fusion.apply(2.5).accept("two and a half");

                            assertEquals("2.5", consumedStringB);
                            assertEquals("two and a half", consumedStringC);
                        }
                    );

                Stream
                    .of(
                        Z
                            .with(doubleToString)
                            .fuse(saveStringsBandC, "two and a half"),
                        Z
                            .with(doubleToString)
                            .fusing(saveStringsBandC, "two and a half")
                    )
                    .forEach(
                        fusion -> {
                            consumedStringB = "";
                            consumedStringC = "";

                            fusion.accept(2.5);

                            assertEquals("2.5", consumedStringB);
                            assertEquals("two and a half", consumedStringC);
                        }
                    );
            }
        }
    }

    @Evil
    @Test
    void dblFn_to_objDblCns() {
        synchronized (consumedStringD) {
            synchronized (consumedDoubleB) {
                Stream
                    .of(
                        Z.fuse(doubleToString, saveStringDDoubleB),
                        Z.with(doubleToString).fuse(saveStringDDoubleB)
                        // Z.with(doubleToString).fusing(saveStringDDoubleB)
                    )
                    .forEach(
                        fusion -> {
                            consumedStringD = "";
                            consumedDoubleB = 0.0;

                            fusion.apply(3.5).accept(4.5);

                            assertEquals("3.5", consumedStringD);
                            assertEquals(4.5, consumedDoubleB);
                        }
                    );

                Stream
                    .of(
                        Z.with(doubleToString).fuse(saveStringDDoubleB, 4.5),
                        Z.with(doubleToString).fusing(saveStringDDoubleB, 4.5)
                    )
                    .forEach(
                        fusion -> {
                            consumedStringD = "";
                            consumedDoubleB = 0.0;

                            fusion.accept(3.5);

                            assertEquals("3.5", consumedStringD);
                            assertEquals(4.5, consumedDoubleB);
                        }
                    );
            }
        }
    }

    @Evil
    @Test
    void dblFn_to_objIntCns() {
        synchronized (consumedStringE) {
            synchronized (consumedIntB) {
                Stream
                    .of(
                        Z.fuse(doubleToString, saveStringEIntB),
                        Z.with(doubleToString).fuse(saveStringEIntB)
                        // Z.with(doubleToString).fusing(saveStringEIntB)
                    )
                    .forEach(
                        fusion -> {
                            consumedStringE = "";
                            consumedIntB = 0;

                            fusion.apply(5.5).accept(6);

                            assertEquals("5.5", consumedStringE);
                            assertEquals(6, consumedIntB);
                        }
                    );

                Stream
                    .of(
                        Z.with(doubleToString).fuse(saveStringEIntB, 6),
                        Z.with(doubleToString).fusing(saveStringEIntB, 6)
                    )
                    .forEach(
                        fusion -> {
                            consumedStringE = "";
                            consumedIntB = 0;

                            fusion.accept(5.5);

                            assertEquals("5.5", consumedStringE);
                            assertEquals(6, consumedIntB);
                        }
                    );
            }
        }
    }

    @Evil
    @Test
    void dblFn_to_objLongCns() {
        synchronized (consumedStringF) {
            synchronized (consumedLongB) {
                Stream
                    .of(
                        Z.fuse(doubleToString, saveStringFLongB),
                        Z.with(doubleToString).fuse(saveStringFLongB)
                        // Z.with(doubleToString).fusing(saveStringFLongB)
                    )
                    .forEach(
                        fusion -> {
                            consumedStringF = "";
                            consumedLongB = 0L;

                            fusion.apply(7.5).accept(8);

                            assertEquals("7.5", consumedStringF);
                            assertEquals(8, consumedLongB);
                        }
                    );

                Stream
                    .of(
                        Z.with(doubleToString).fuse(saveStringFLongB, 8),
                        Z.with(doubleToString).fusing(saveStringFLongB, 8)
                    )
                    .forEach(
                        fusion -> {
                            consumedStringF = "";
                            consumedLongB = 0L;

                            fusion.accept(7.5);

                            assertEquals("7.5", consumedStringF);
                            assertEquals(8, consumedLongB);
                        }
                    );
            }
        }
    }

    @Test
    void dblFn_to_unop() {
        Stream
            .of(
                Z.fuse(doubleToString, addQuestionMark),
                Z.with(doubleToString).fuse(addQuestionMark),
                Z.with(doubleToString).fusing(addQuestionMark)
            )
            .forEach(fusion -> assertEquals("9.5?", fusion.apply(9.5)));
    }

    @Test
    void dblFn_to_biop() {
        Stream
            .of(
                Z.fuse(doubleToString, relation),
                Z.with(doubleToString).fuse(relation)
                // Z.with(doubleToString).fusing(relation)
            )
            .forEach(
                fusion ->
                    assertEquals("same-ish", fusion.apply(10.5).apply("10.5"))
            );

        Stream
            .of(
                Z.with(doubleToString).fuse(relation, "10.5"),
                Z.with(doubleToString).fusing(relation, "10.5")
            )
            .forEach(fusion -> assertEquals("same-ish", fusion.apply(10.5)));
    }
}
