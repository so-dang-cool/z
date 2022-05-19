package so.dang.cool.z.combination.fusion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static so.dang.cool.z.combination.TestFunctions.*;

import java.util.function.Supplier;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import so.dang.cool.z.Z;
import so.dang.cool.z.annotation.Evil;

public class SupplierFusionTests {

    @Test
    void sup() {
        assertEquals(suppliedString, getString.get());
        assertEquals(suppliedString, Z.with(getString).get());
    }

    @Test
    void sup_to_fn() {
        assertEquals(
            suppliedString.toLowerCase(),
            Z.fuse(getString, toLower).get()
        );
        assertEquals(
            suppliedString.toLowerCase(),
            Z.with(getString).fuse(toLower).get()
        );
        assertEquals(
            suppliedString.toLowerCase(),
            Z.with(getString).fusing(toLower).get()
        );
    }

    @Test
    void sup_to_bifn() {
        Stream
            .of(
                Z.fuse(getString, concat),
                Z.with(getString).fuse(concat),
                Z.with(getString).fusing(concat)
            )
            .forEach(fusion -> assertEquals("Z!", fusion.apply("!")));

        Stream
            .of(
                Z.with(getString).fuse(concat, "!"),
                Z.with(getString).fusing(concat, "!")
            )
            .forEach(fusion -> assertEquals("Z!", fusion.get()));
    }

    @Test
    void sup_to_toDblFn() {
        assertEquals(1.0, Z.fuse(() -> "1.0", stringToDouble).getAsDouble());
        assertEquals(
            1.0,
            Z.with(() -> "1.0").fuse(stringToDouble).getAsDouble()
        );
        assertEquals(
            1.0,
            Z.with(() -> "1.0").fusing(stringToDouble).getAsDouble()
        );
    }

    @Test
    void sup_to_toDblBifn() {
        Supplier<String> doubleOne = () -> "1.0";

        Stream
            .of(
                Z.fuse(doubleOne, addStringsAsDouble),
                Z.with(doubleOne).fuse(addStringsAsDouble),
                Z.with(doubleOne).fusing(addStringsAsDouble)
            )
            .forEach(
                fusion -> {
                    assertEquals(2.5, fusion.applyAsDouble("1.5"));
                }
            );

        Stream
            .of(
                Z.with(doubleOne).fuse(addStringsAsDouble, "1.5"),
                Z.with(doubleOne).fusing(addStringsAsDouble, "1.5")
            )
            .forEach(
                fusion -> {
                    assertEquals(2.5, fusion.getAsDouble());
                }
            );
    }

    @Test
    void sup_to_toIntFn() {
        assertEquals(1, Z.fuse(() -> "1", stringToInt).getAsInt());
        assertEquals(1, Z.with(() -> "1").fuse(stringToInt).getAsInt());
        assertEquals(1, Z.with(() -> "1").fusing(stringToInt).getAsInt());
    }

    @Test
    void sup_to_toIntBifn() {
        Supplier<String> intOne = () -> "1";

        Stream
            .of(
                Z.fuse(intOne, addStringsAsInt),
                Z.with(intOne).fuse(addStringsAsInt),
                Z.with(intOne).fusing(addStringsAsInt)
            )
            .forEach(
                fusion -> {
                    assertEquals(3, fusion.applyAsInt("2"));
                }
            );

        Stream
            .of(
                Z.with(intOne).fuse(addStringsAsInt, "2"),
                Z.with(intOne).fusing(addStringsAsInt, "2")
            )
            .forEach(
                fusion -> {
                    assertEquals(3, fusion.getAsInt());
                }
            );
    }

    @Test
    void sup_to_toLongFn() {
        assertEquals(1L, Z.fuse(() -> "1", stringToLong).getAsLong());
        assertEquals(1L, Z.with(() -> "1").fuse(stringToLong).getAsLong());
        assertEquals(1L, Z.with(() -> "1").fusing(stringToLong).getAsLong());
    }

    @Test
    void sup_to_toLongBifn() {
        Supplier<String> intOne = () -> "1";

        Stream
            .of(
                Z.fuse(intOne, addStringsAsLong),
                Z.with(intOne).fuse(addStringsAsLong),
                Z.with(intOne).fusing(addStringsAsLong)
            )
            .forEach(
                fusion -> {
                    assertEquals(3L, fusion.applyAsLong("2"));
                }
            );

        Stream
            .of(
                Z.with(intOne).fuse(addStringsAsLong, "2"),
                Z.with(intOne).fusing(addStringsAsLong, "2")
            )
            .forEach(
                fusion -> {
                    assertEquals(3L, fusion.getAsLong());
                }
            );
    }

    @Test
    void sup_to_pred() {
        assertFalse(Z.fuse(getString, isEmpty).getAsBoolean());
        assertFalse(Z.with(getString).fuse(isEmpty).getAsBoolean());
        assertFalse(Z.with(getString).fusing(isEmpty).getAsBoolean());
    }

    @Test
    void sup_to_bipred() {
        Stream
            .of(
                Z.fuse(getString, startsWith),
                Z.with(getString).fuse(startsWith),
                Z.with(getString).fusing(startsWith)
            )
            .forEach(
                fusion -> {
                    assertTrue(fusion.test("Z"));
                }
            );

        Stream
            .of(
                Z.with(getString).fuse(startsWith, "Z"),
                Z.with(getString).fusing(startsWith, "Z")
            )
            .forEach(
                fusion -> {
                    assertTrue(fusion.getAsBoolean());
                }
            );
    }

    @Evil
    @Test
    void sup_to_cns() {
        synchronized (consumedStringA) {
            consumedStringA = "";
            Z.fuse(getString, saveStringA).run();
            assertEquals(suppliedString, consumedStringA);

            consumedStringA = "";
            Z.with(getString).fuse(saveStringA).run();
            assertEquals(suppliedString, consumedStringA);

            consumedStringA = "";
            Z.with(getString).fusing(saveStringA).run();
            assertEquals(suppliedString, consumedStringA);
        }
    }

    @Evil
    @Test
    void sup_to_bicns() {
        synchronized (consumedStringB) {
            synchronized (consumedStringC) {
                Stream
                    .of(
                        Z.fuse(getString, saveStringsBandC),
                        Z.with(getString).fuse(saveStringsBandC),
                        Z.with(getString).fusing(saveStringsBandC)
                    )
                    .forEach(
                        fusion -> {
                            consumedStringB = "";
                            consumedStringC = "";

                            fusion.accept("z");

                            assertEquals(suppliedString, consumedStringB);
                            assertEquals("z", consumedStringC);
                        }
                    );

                Stream
                    .of(
                        Z.with(getString).fuse(saveStringsBandC, "z"),
                        Z.with(getString).fusing(saveStringsBandC, "z")
                    )
                    .forEach(
                        fusion -> {
                            consumedStringB = "";
                            consumedStringC = "";

                            fusion.run();

                            assertEquals(suppliedString, consumedStringB);
                            assertEquals("z", consumedStringC);
                        }
                    );
            }
        }
    }

    @Evil
    @Test
    void sup_to_objDblCns() {
        synchronized (consumedStringD) {
            synchronized (consumedDoubleB) {
                Stream
                    .of(
                        Z.fuse(getString, saveStringDDoubleB),
                        Z.with(getString).fuse(saveStringDDoubleB),
                        Z.with(getString).fusing(saveStringDDoubleB)
                    )
                    .forEach(
                        fusion -> {
                            consumedStringD = "";
                            consumedDoubleB = 0.0;

                            fusion.accept(5.0);

                            assertEquals(suppliedString, consumedStringD);
                            assertEquals(5.0, consumedDoubleB);
                        }
                    );

                Stream
                    .of(
                        Z.with(getString).fuse(saveStringDDoubleB, 5.0),
                        Z.with(getString).fusing(saveStringDDoubleB, 5.0)
                    )
                    .forEach(
                        fusion -> {
                            consumedStringD = "";
                            consumedDoubleB = 0.0;

                            fusion.run();

                            assertEquals(suppliedString, consumedStringD);
                            assertEquals(5.0, consumedDoubleB);
                        }
                    );
            }
        }
    }

    @Evil
    @Test
    void sup_to_objIntCns() {
        synchronized (consumedStringE) {
            synchronized (consumedIntB) {
                Stream
                    .of(
                        Z.fuse(getString, saveStringEIntB),
                        Z.with(getString).fuse(saveStringEIntB),
                        Z.with(getString).fusing(saveStringEIntB)
                    )
                    .forEach(
                        fusion -> {
                            consumedStringE = "";
                            consumedIntB = 0;

                            fusion.accept(6);

                            assertEquals(suppliedString, consumedStringE);
                            assertEquals(6, consumedIntB);
                        }
                    );

                Stream
                    .of(
                        Z.with(getString).fuse(saveStringEIntB, 6),
                        Z.with(getString).fusing(saveStringEIntB, 6)
                    )
                    .forEach(
                        fusion -> {
                            consumedStringE = "";
                            consumedIntB = 0;

                            fusion.run();

                            assertEquals(suppliedString, consumedStringE);
                            assertEquals(6, consumedIntB);
                        }
                    );
            }
        }
    }

    @Evil
    @Test
    void sup_to_objLongFn() {
        synchronized (consumedStringF) {
            synchronized (consumedLongB) {
                Stream
                    .of(
                        Z.fuse(getString, saveStringFLongB),
                        Z.with(getString).fuse(saveStringFLongB),
                        Z.with(getString).fusing(saveStringFLongB)
                    )
                    .forEach(
                        fusion -> {
                            consumedStringF = "";
                            consumedLongB = 0L;

                            fusion.accept(7L);

                            assertEquals(suppliedString, consumedStringF);
                            assertEquals(7L, consumedLongB);
                        }
                    );

                Stream
                    .of(
                        Z.with(getString).fuse(saveStringFLongB, 7L),
                        Z.with(getString).fusing(saveStringFLongB, 7L)
                    )
                    .forEach(
                        fusion -> {
                            consumedStringF = "";
                            consumedLongB = 0L;

                            fusion.run();

                            assertEquals(suppliedString, consumedStringF);
                            assertEquals(7L, consumedLongB);
                        }
                    );
            }
        }
    }

    @Test
    void sup_to_toUnop() {
        assertEquals("Z?", Z.fuse(getString, addQuestionMark).get());
        assertEquals("Z?", Z.with(getString).fuse(addQuestionMark).get());
        assertEquals("Z?", Z.with(getString).fusing(addQuestionMark).get());
    }

    @Test
    void sup_to_toBiop() {
        Stream
            .of(
                Z.fuse(getString, relation),
                Z.with(getString).fuse(relation),
                Z.with(getString).fusing(relation)
            )
            .forEach(
                fusion -> {
                    assertEquals("same-ish", fusion.apply("z"));
                }
            );

        Stream
            .of(
                Z.with(getString).fuse(relation, "z"),
                Z.with(getString).fusing(relation, "z")
            )
            .forEach(
                fusion -> {
                    assertEquals("same-ish", fusion.get());
                }
            );
    }
}
