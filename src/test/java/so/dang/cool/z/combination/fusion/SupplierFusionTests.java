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
        assertEquals(suppliedString, Z.fuse(getString).get());
    }

    @Test
    void sup_to_fn() {
        assertEquals(
            suppliedString.toLowerCase(),
            Z.fuse(getString).fuse(toLower).get()
        );
        assertEquals(
            suppliedString.toLowerCase(),
            Z.fuse(getString).fuse(toLower).get()
        );
    }

    @Test
    void sup_to_bifn() {
        assertEquals("Z!", Z.fuse(getString).fuse(concat).apply("!"));
    }

    @Test
    void sup_to_toDblFn() {
        assertEquals(
            1.0,
            Z.fuse(() -> "1.0").fuse(stringToDouble).getAsDouble()
        );
    }

    @Test
    void sup_to_toDblBifn() {
        Supplier<String> doubleOne = () -> "1.0";

        Stream
            .of(Z.fuse(doubleOne).fuse(addStringsAsDouble))
            .forEach(
                fusion -> {
                    assertEquals(2.5, fusion.applyAsDouble("1.5"));
                }
            );
    }

    @Test
    void sup_to_toIntFn() {
        assertEquals(1, Z.fuse(() -> "1").fuse(stringToInt).getAsInt());
    }

    @Test
    void sup_to_toIntBifn() {
        Supplier<String> intOne = () -> "1";

        Stream
            .of(Z.fuse(intOne).fuse(addStringsAsInt))
            .forEach(
                fusion -> {
                    assertEquals(3, fusion.applyAsInt("2"));
                }
            );
    }

    @Test
    void sup_to_toLongFn() {
        assertEquals(1L, Z.fuse(() -> "1").fuse(stringToLong).getAsLong());
    }

    @Test
    void sup_to_toLongBifn() {
        Supplier<String> intOne = () -> "1";

        Stream
            .of(Z.fuse(intOne).fuse(addStringsAsLong))
            .forEach(
                fusion -> {
                    assertEquals(3L, fusion.applyAsLong("2"));
                }
            );
    }

    @Test
    void sup_to_pred() {
        assertFalse(Z.fuse(getString).fuse(isEmpty).getAsBoolean());
    }

    @Test
    void sup_to_bipred() {
        Stream
            .of(Z.fuse(getString).fuse(startsWith))
            .forEach(
                fusion -> {
                    assertTrue(fusion.test("Z"));
                }
            );
    }

    @Evil
    @Test
    void sup_to_cns() {
        synchronized (consumedStringA) {
            consumedStringA = "";
            Z.fuse(getString).fuse(saveStringA).run();
            assertEquals(suppliedString, consumedStringA);
        }
    }

    @Evil
    @Test
    void sup_to_bicns() {
        synchronized (consumedStringB) {
            synchronized (consumedStringC) {
                Stream
                    .of(Z.fuse(getString).fuse(saveStringsBandC))
                    .forEach(
                        fusion -> {
                            consumedStringB = "";
                            consumedStringC = "";

                            fusion.accept("z");

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
                    .of(Z.fuse(getString).fuse(saveStringDDoubleB))
                    .forEach(
                        fusion -> {
                            consumedStringD = "";
                            consumedDoubleB = 0.0;

                            fusion.accept(5.0);

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
                    .of(Z.fuse(getString).fuse(saveStringEIntB))
                    .forEach(
                        fusion -> {
                            consumedStringE = "";
                            consumedIntB = 0;

                            fusion.accept(6);

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
                    .of(Z.fuse(getString).fuse(saveStringFLongB))
                    .forEach(
                        fusion -> {
                            consumedStringF = "";
                            consumedLongB = 0L;

                            fusion.accept(7L);

                            assertEquals(suppliedString, consumedStringF);
                            assertEquals(7L, consumedLongB);
                        }
                    );
            }
        }
    }

    @Test
    void sup_to_toUnop() {
        assertEquals("Z?", Z.fuse(getString).fuse(addQuestionMark).get());
    }

    @Test
    void sup_to_toBiop() {
        Stream
            .of(Z.fuse(getString).fuse(relation))
            .forEach(
                fusion -> {
                    assertEquals("same-ish", fusion.apply("z"));
                }
            );
    }
}
