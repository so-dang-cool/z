package so.dang.cool.z.combination.fusion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static so.dang.cool.z.combination.TestFunctions.*;

import org.junit.jupiter.api.Test;
import so.dang.cool.z.Z;
import so.dang.cool.z.annotation.Evil;

public class SupplierFusionTests {

    @Test
    void sup() {
        assertEquals(suppliedString, getString.get());
        assertEquals(suppliedString, Z.with(getString).resolve().get());
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
            Z.with(getString).fusing(toLower).resolve().get()
        );
    }

    @Test
    void sup_to_bifn() {
        assertEquals("Z!", Z.fuse(getString, concat).apply("!"));
        assertEquals("Z!", Z.with(getString).fuse(concat).apply("!"));
        assertEquals(
            "Z!",
            Z.with(getString).fusing(concat).resolve().apply("!")
        );
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
            Z.with(() -> "1.0").fusing(stringToDouble).resolve().getAsDouble()
        );
    }

    @Test
    void sup_to_toDblBifn() {
        assertEquals(
            2.0,
            Z.fuse(() -> "1.0", addStringsAsDouble).applyAsDouble("1.0")
        );
        assertEquals(
            2.0,
            Z.with(() -> "1.0").fuse(addStringsAsDouble).applyAsDouble("1.0")
        );
        assertEquals(
            2.0,
            Z
                .with(() -> "1.0")
                .fusing(addStringsAsDouble)
                .resolve()
                .applyAsDouble("1.0")
        );
    }

    @Test
    void sup_to_toIntFn() {
        assertEquals(1, Z.fuse(() -> "1", stringToInt).getAsInt());
        assertEquals(1, Z.with(() -> "1").fuse(stringToInt).getAsInt());
        assertEquals(
            1,
            Z.with(() -> "1").fusing(stringToInt).resolve().getAsInt()
        );
    }

    @Test
    void sup_to_toIntBifn() {
        assertEquals(2, Z.fuse(() -> "1", addStringsAsInt).applyAsInt("1"));
        assertEquals(
            2,
            Z.with(() -> "1").fuse(addStringsAsInt).applyAsInt("1")
        );
        assertEquals(
            2,
            Z.with(() -> "1").fusing(addStringsAsInt).resolve().applyAsInt("1")
        );
    }

    @Test
    void sup_to_toLongFn() {
        assertEquals(1L, Z.fuse(() -> "1", stringToLong).getAsLong());
        assertEquals(1L, Z.with(() -> "1").fuse(stringToLong).getAsLong());
        assertEquals(
            1L,
            Z.with(() -> "1").fusing(stringToLong).resolve().getAsLong()
        );
    }

    @Test
    void sup_to_toLongBifn() {
        assertEquals(2L, Z.fuse(() -> "1", addStringsAsLong).applyAsLong("1"));
        assertEquals(
            2L,
            Z.with(() -> "1").fuse(addStringsAsLong).applyAsLong("1")
        );
        assertEquals(
            2L,
            Z
                .with(() -> "1")
                .fusing(addStringsAsLong)
                .resolve()
                .applyAsLong("1")
        );
    }

    @Test
    void sup_to_pred() {
        assertFalse(Z.fuse(getString, isEmpty).getAsBoolean());
        assertFalse(Z.with(getString).fuse(isEmpty).getAsBoolean());
        assertFalse(Z.with(getString).fusing(isEmpty).resolve().getAsBoolean());
    }

    @Test
    void sup_to_bipred() {
        assertTrue(Z.fuse(getString, startsWith).test("Z"));
        assertTrue(Z.with(getString).fuse(startsWith).test("Z"));
        assertTrue(Z.with(getString).fusing(startsWith).resolve().test("Z"));
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
            Z.with(getString).fusing(saveStringA).resolve().run();
            assertEquals(suppliedString, consumedStringA);
        }
    }

    @Evil
    @Test
    void sup_to_bicns() {
        synchronized (consumedStringB) {
            synchronized (consumedStringC) {
                consumedStringB = "";
                consumedStringC = "";
                Z.fuse(getString, saveStringsBandC).accept("z");
                assertEquals(suppliedString, consumedStringB);
                assertEquals("z", consumedStringC);

                consumedStringB = "";
                consumedStringC = "";
                Z.with(getString).fuse(saveStringsBandC).accept("z");
                assertEquals(suppliedString, consumedStringB);
                assertEquals("z", consumedStringC);

                consumedStringB = "";
                consumedStringC = "";
                Z
                    .with(getString)
                    .fusing(saveStringsBandC)
                    .resolve()
                    .accept("z");
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
                consumedStringD = "";
                consumedDoubleB = 0.0;
                Z.fuse(getString, saveStringDDoubleB).accept(5.0);
                assertEquals(suppliedString, consumedStringD);
                assertEquals(5.0, consumedDoubleB);

                consumedStringD = "";
                consumedDoubleB = 0.0;
                Z.with(getString).fuse(saveStringDDoubleB).accept(5.0);
                assertEquals(suppliedString, consumedStringD);
                assertEquals(5.0, consumedDoubleB);

                consumedStringD = "";
                consumedDoubleB = 0.0;
                Z
                    .with(getString)
                    .fusing(saveStringDDoubleB)
                    .resolve()
                    .accept(5.0);
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
                consumedStringE = "";
                consumedIntB = 0;
                Z.fuse(getString, saveStringEIntB).accept(6);
                assertEquals(suppliedString, consumedStringE);
                assertEquals(6, consumedIntB);

                consumedStringE = "";
                consumedIntB = 0;
                Z.with(getString).fuse(saveStringEIntB).accept(6);
                assertEquals(suppliedString, consumedStringE);
                assertEquals(6, consumedIntB);

                consumedStringE = "";
                consumedIntB = 0;
                Z.with(getString).fusing(saveStringEIntB).resolve().accept(6);
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
                consumedStringF = "";
                consumedLongB = 0L;
                Z.fuse(getString, saveStringFLongB).accept(7L);
                assertEquals(suppliedString, consumedStringF);
                assertEquals(7L, consumedLongB);

                consumedStringF = "";
                consumedLongB = 0L;
                Z.with(getString).fuse(saveStringFLongB).accept(7L);
                assertEquals(suppliedString, consumedStringF);
                assertEquals(7L, consumedLongB);

                consumedStringF = "";
                consumedLongB = 0L;
                Z.with(getString).fusing(saveStringFLongB).resolve().accept(7L);
                assertEquals(suppliedString, consumedStringF);
                assertEquals(7L, consumedLongB);
            }
        }
    }

    @Test
    void sup_to_toUnop() {
        assertEquals("Z?", Z.fuse(getString, addQuestionMark).get());
        assertEquals("Z?", Z.with(getString).fuse(addQuestionMark).get());
        assertEquals(
            "Z?",
            Z.with(getString).fusing(addQuestionMark).resolve().get()
        );
    }

    @Test
    void sup_to_toBiop() {
        assertEquals("same-ish", Z.fuse(getString, relation).apply("z"));
        assertEquals("same-ish", Z.with(getString).fuse(relation).apply("z"));
        assertEquals(
            "same-ish",
            Z.with(getString).fusing(relation).resolve().apply("z")
        );
    }
}
