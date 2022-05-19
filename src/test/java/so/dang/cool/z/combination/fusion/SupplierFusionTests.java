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
    }

    @Test
    void sup_to_bifn() {
        Stream
            .of(
                Z.fuse(getString, concat),
                Z.with(getString).fuse(concat)
            )
            .forEach(fusion -> assertEquals("Z!", fusion.apply("!")));

        assertEquals("Z!", Z.with(getString).fuse(concat, "!").get());
    }

    @Test
    void sup_to_toDblFn() {
        assertEquals(1.0, Z.fuse(() -> "1.0", stringToDouble).getAsDouble());
        assertEquals(
            1.0,
            Z.with(() -> "1.0").fuse(stringToDouble).getAsDouble()
        );
    }

    @Test
    void sup_to_toDblBifn() {
        Supplier<String> doubleOne = () -> "1.0";

        Stream
            .of(
                Z.fuse(doubleOne, addStringsAsDouble),
                Z.with(doubleOne).fuse(addStringsAsDouble)
            )
            .forEach(
                fusion -> {
                    assertEquals(2.5, fusion.applyAsDouble("1.5"));
                }
            );

            assertEquals(2.5, Z.with(doubleOne).fuse(addStringsAsDouble, "1.5").getAsDouble());
    }

    @Test
    void sup_to_toIntFn() {
        assertEquals(1, Z.fuse(() -> "1", stringToInt).getAsInt());
        assertEquals(1, Z.with(() -> "1").fuse(stringToInt).getAsInt());
    }

    @Test
    void sup_to_toIntBifn() {
        Supplier<String> intOne = () -> "1";

        Stream
            .of(
                Z.fuse(intOne, addStringsAsInt),
                Z.with(intOne).fuse(addStringsAsInt)
            )
            .forEach(
                fusion -> {
                    assertEquals(3, fusion.applyAsInt("2"));
                }
            );

            assertEquals(3, Z.with(intOne).fuse(addStringsAsInt, "2").getAsInt());
    }

    @Test
    void sup_to_toLongFn() {
        assertEquals(1L, Z.fuse(() -> "1", stringToLong).getAsLong());
        assertEquals(1L, Z.with(() -> "1").fuse(stringToLong).getAsLong());
    }

    @Test
    void sup_to_toLongBifn() {
        Supplier<String> intOne = () -> "1";

        Stream
            .of(
                Z.fuse(intOne, addStringsAsLong),
                Z.with(intOne).fuse(addStringsAsLong)
            )
            .forEach(
                fusion -> {
                    assertEquals(3L, fusion.applyAsLong("2"));
                }
            );

            assertEquals(3L, Z.with(intOne).fuse(addStringsAsLong, "2").getAsLong());
    }

    @Test
    void sup_to_pred() {
        assertFalse(Z.fuse(getString, isEmpty).getAsBoolean());
        assertFalse(Z.with(getString).fuse(isEmpty).getAsBoolean());
    }

    @Test
    void sup_to_bipred() {
        Stream
            .of(
                Z.fuse(getString, startsWith),
                Z.with(getString).fuse(startsWith)
            )
            .forEach(
                fusion -> {
                    assertTrue(fusion.test("Z"));
                }
            );

        assertTrue(Z.with(getString).fuse(startsWith, "Z").getAsBoolean());
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
                        Z.with(getString).fuse(saveStringsBandC)
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

                    consumedStringB = "";
                    consumedStringC = "";

                    Z.with(getString).fuse(saveStringsBandC, "z").run();

                    assertEquals(suppliedString, consumedStringB);
                    assertEquals("z", consumedStringC);
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
                        Z.with(getString).fuse(saveStringDDoubleB)
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

                    consumedStringD = "";
                    consumedDoubleB = 0.0;

                    Z.with(getString).fuse(saveStringDDoubleB, 5.0).run();

                    assertEquals(suppliedString, consumedStringD);
                    assertEquals(5.0, consumedDoubleB);
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
                        Z.with(getString).fuse(saveStringEIntB)
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

                consumedStringE = "";
                consumedIntB = 0;

                Z.with(getString).fuse(saveStringEIntB, 6).run();

                assertEquals(suppliedString, consumedStringE);
                assertEquals(6, consumedIntB);
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
                        Z.with(getString).fuse(saveStringFLongB)
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

                consumedStringF = "";
                consumedLongB = 0L;

                Z.with(getString).fuse(saveStringFLongB, 7L).run();

                assertEquals(suppliedString, consumedStringF);
                assertEquals(7L, consumedLongB);
            }
        }
    }

    @Test
    void sup_to_toUnop() {
        assertEquals("Z?", Z.fuse(getString, addQuestionMark).get());
        assertEquals("Z?", Z.with(getString).fuse(addQuestionMark).get());
    }

    @Test
    void sup_to_toBiop() {
        Stream
            .of(
                Z.fuse(getString, relation),
                Z.with(getString).fuse(relation)
            )
            .forEach(
                fusion -> {
                    assertEquals("same-ish", fusion.apply("z"));
                }
            );

        assertEquals("same-ish", Z.with(getString).fuse(relation, "z").get());
    }
}
