package so.dang.cool.z.combination;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static so.dang.cool.z.combination.TestFunctions.*;

import org.junit.jupiter.api.Test;
import so.dang.cool.z.Z;
import so.dang.cool.z.annotation.Evil;

@Evil
public class AbsorptionTests {

    @Evil
    @Test
    void cns() {
        synchronized (consumedStringA) {
            consumedStringA = "";
            Z.fuse(saveStringA).accept("hello");
            assertEquals("hello", consumedStringA);
        }
    }

    @Evil
    @Test
    void boolCns() {
        synchronized (consumedBooleanA) {
            consumedBooleanA = false;
            saveBooleanA.accept(true);
            assertTrue(consumedBooleanA);

            consumedBooleanA = false;
            Z.fuse(saveBooleanA).accept(true);
            assertTrue(consumedBooleanA);
        }
    }

    @Evil
    @Test
    void dblCns() {
        synchronized (consumedDoubleA) {
            consumedDoubleA = 0.0;
            saveDoubleA.accept(1.5);
            assertEquals(1.5, consumedDoubleA);

            consumedDoubleA = 0.0;
            Z.fuse(saveDoubleA).accept(1.5);
            assertEquals(1.5, consumedDoubleA);
        }
    }

    @Evil
    @Test
    void objDblCns() {
        synchronized (consumedStringD) {
            synchronized (consumedDoubleB) {
                consumedStringD = "";
                consumedDoubleB = 0.0;
                saveStringDDoubleB.accept("heyo", 1.5);
                assertEquals("heyo", consumedStringD);
                assertEquals(1.5, consumedDoubleB);

                consumedStringD = "";
                consumedDoubleB = 0.0;
                Z.fuse(saveStringDDoubleB).apply("heyo").accept(1.5);
                assertEquals("heyo", consumedStringD);
                assertEquals(1.5, consumedDoubleB);
            }
        }
    }

    @Evil
    @Test
    void objIntCns() {
        synchronized (consumedStringE) {
            synchronized (consumedIntB) {
                consumedStringE = "";
                consumedIntB = 0;
                saveStringEIntB.accept("heyo", 2);
                assertEquals("heyo", consumedStringE);
                assertEquals(2, consumedIntB);

                consumedStringE = "";
                consumedIntB = 0;
                Z.fuse(saveStringEIntB).apply("heyo").accept(2);
                assertEquals("heyo", consumedStringE);
                assertEquals(2, consumedIntB);
            }
        }
    }

    @Evil
    @Test
    void objLongCns() {
        synchronized (consumedStringF) {
            synchronized (consumedLongB) {
                consumedStringF = "";
                consumedLongB = 0L;
                saveStringFLongB.accept("heyo", 2L);
                assertEquals("heyo", consumedStringF);
                assertEquals(2L, consumedLongB);

                consumedStringF = "";
                consumedLongB = 0L;
                Z.fuse(saveStringFLongB).apply("heyo").accept(2L);
                assertEquals("heyo", consumedStringF);
                assertEquals(2L, consumedLongB);
            }
        }
    }

    @Evil
    @Test
    void intCns() {
        synchronized (consumedIntA) {
            consumedIntA = 0;
            Z.fuse(saveIntA).accept(1);
            assertEquals(1, consumedIntA);
        }
    }

    @Evil
    @Test
    void longCns() {
        synchronized (consumedLongA) {
            consumedLongA = 0L;
            Z.fuse(saveLongA).accept(1L);
            assertEquals(1L, consumedLongA);
        }
    }

    @Evil
    @Test
    void cns_to_fn() {
        synchronized (consumedStringA) {
            consumedStringA = "";

            assertEquals(
                "goodbye",
                Z
                    .fuse(saveStringA)
                    .absorb(trim)
                    .apply(" hello ")
                    .apply(" goodbye ")
            );
            assertEquals(" hello ", consumedStringA);
        }
    }

    @Evil
    @Test
    void cns_to_sup() {
        synchronized (consumedStringA) {
            consumedStringA = "";

            assertEquals(
                suppliedString,
                Z.fuse(saveStringA).absorb(getString).apply("hello")
            );
            assertEquals("hello", consumedStringA);
        }
    }

    @Evil
    @Test
    void cns_to_boolSup() {
        synchronized (consumedStringA) {
            consumedStringA = "";

            assertEquals(
                true,
                Z.fuse(saveStringA).absorb(getBooleanTrue).test("salut")
            );
            assertEquals("salut", consumedStringA);
        }
    }

    @Evil
    @Test
    void cns_to_dblSup() {
        synchronized (consumedStringA) {
            consumedStringA = "";

            assertEquals(
                suppliedDouble,
                Z.fuse(saveStringA).absorb(getDouble).applyAsDouble("hola")
            );
            assertEquals("hola", consumedStringA);
        }
    }

    @Evil
    @Test
    void cns_to_intSup() {
        synchronized (consumedStringA) {
            consumedStringA = "";

            assertEquals(
                suppliedInt,
                Z.fuse(saveStringA).absorb(getInt).applyAsInt("안녕하세요")
            );
            assertEquals("안녕하세요", consumedStringA);
        }
    }

    @Evil
    @Test
    void cns_to_longSup() {
        synchronized (consumedStringA) {
            consumedStringA = "";

            assertEquals(
                suppliedLong,
                Z.fuse(saveStringA).absorb(getLong).applyAsLong("kamusta")
            );
            assertEquals("kamusta", consumedStringA);
        }
    }

    @Evil
    @Test
    void cns_to_op() {
        synchronized (consumedStringA) {
            synchronized (wasOperated) {
                consumedStringA = "";
                wasOperated = false;

                Z.fuse(saveStringA).absorb(doOperation).accept("merhaba");

                assertEquals("merhaba", consumedStringA);
                assertTrue(wasOperated);
            }
        }
    }

    @Evil
    @Test
    void bicns() {
        synchronized (consumedStringB) {
            synchronized (consumedStringC) {
                consumedStringB = "";
                consumedStringC = "";

                saveStringsBandC.accept("cześć", "喂");
                assertEquals("cześć", consumedStringB);
                assertEquals("喂", consumedStringC);
            }
        }
    }

    @Evil
    @Test
    void bicns_deep() {
        synchronized (consumedStringB) {
            synchronized (consumedStringC) {
                consumedStringB = "";
                consumedStringC = "";

                Z.fuse(saveStringsBandC).apply("cześć").accept("喂");
                assertEquals("cześć", consumedStringB);
                assertEquals("喂", consumedStringC);
            }
        }
    }

    @Evil
    @Test
    void bicns_to_sup() {
        synchronized (consumedStringB) {
            synchronized (consumedStringC) {
                consumedStringB = "";
                consumedStringC = "";

                assertEquals(
                    suppliedString,
                    Z
                        .fuse(saveStringsBandC)
                        .absorb(getString)
                        .apply("cześć")
                        .apply("喂")
                );
                assertEquals("cześć", consumedStringB);
                assertEquals("喂", consumedStringC);
            }
        }
    }

    @Evil
    @Test
    void bicns_to_sup_deep() {
        synchronized (consumedStringB) {
            synchronized (consumedStringC) {
                consumedStringB = "";
                consumedStringC = "";

                assertEquals(
                    suppliedString,
                    Z
                        .fuse(saveStringsBandC)
                        .absorb(getString)
                        .apply("cześć")
                        .apply("喂")
                );
                assertEquals("cześć", consumedStringB);
                assertEquals("喂", consumedStringC);
            }
        }
    }

    @Evil
    @Test
    void bicns_to_sup_deeper() {
        synchronized (consumedStringB) {
            synchronized (consumedStringC) {
                consumedStringB = "";
                consumedStringC = "";

                assertEquals(
                    suppliedString,
                    Z
                        .fuse(saveStringsBandC)
                        .absorb(getString)
                        .apply("cześć")
                        .apply("喂")
                );
                assertEquals("cześć", consumedStringB);
                assertEquals("喂", consumedStringC);
            }
        }
    }

    @Evil
    @Test
    void bicns_to_boolSup() {
        synchronized (consumedStringB) {
            synchronized (consumedStringC) {
                consumedStringB = "";
                consumedStringC = "";

                assertEquals(
                    true,
                    Z
                        .fuse(saveStringsBandC)
                        .absorb(getBooleanTrue)
                        .apply("hei")
                        .test("hej")
                );
                assertEquals("hei", consumedStringB);
                assertEquals("hej", consumedStringC);
            }
        }
    }

    @Evil
    @Test
    void bicns_to_dblSup() {
        synchronized (consumedStringB) {
            synchronized (consumedStringC) {
                consumedStringB = "";
                consumedStringC = "";

                assertEquals(
                    suppliedDouble,
                    Z
                        .fuse(saveStringsBandC)
                        .absorb(getDouble)
                        .apply("buenas")
                        .apply("dias")
                );
                assertEquals("buenas", consumedStringB);
                assertEquals("dias", consumedStringC);
            }
        }
    }

    @Evil
    @Test
    void bicns_to_intSup() {
        synchronized (consumedStringB) {
            synchronized (consumedStringC) {
                consumedStringB = "";
                consumedStringC = "";

                assertEquals(
                    suppliedInt,
                    Z
                        .fuse(saveStringsBandC)
                        .absorb(getInt)
                        .apply("안녕")
                        .apply("하세요")
                );
                assertEquals("안녕", consumedStringB);
                assertEquals("하세요", consumedStringC);
            }
        }
    }

    @Evil
    @Test
    void bicns_to_longSup() {
        synchronized (consumedStringB) {
            synchronized (consumedStringC) {
                consumedStringB = "";
                consumedStringC = "";

                assertEquals(
                    suppliedLong,
                    Z
                        .fuse(saveStringsBandC)
                        .absorb(getLong)
                        .apply("ça")
                        .apply("va")
                );
                assertEquals("ça", consumedStringB);
                assertEquals("va", consumedStringC);
            }
        }
    }

    @Evil
    @Test
    void bicns_to_op() {
        synchronized (consumedStringB) {
            synchronized (consumedStringC) {
                synchronized (wasOperated) {
                    consumedStringB = "";
                    consumedStringC = "";
                    wasOperated = false;

                    Z
                        .fuse(saveStringsBandC)
                        .absorb(doOperation)
                        .apply("...")
                        .accept("!!!");

                    assertEquals("...", consumedStringB);
                    assertEquals("!!!", consumedStringC);
                    assertTrue(wasOperated);
                }
            }
        }
    }

    @Evil
    @Test
    void boolCns_to_sup() {
        synchronized (consumedBooleanA) {
            consumedBooleanA = false;

            assertEquals(
                suppliedString,
                Z.fuse(saveBooleanA).absorb(getString).apply(true)
            );
            assertEquals(true, consumedBooleanA);
        }
    }

    @Evil
    @Test
    void boolCns_to_boolSup() {
        synchronized (consumedBooleanA) {
            consumedBooleanA = false;

            assertEquals(
                true,
                Z.fuse(saveBooleanA).absorb(getBooleanTrue).test(true)
            );
            assertEquals(true, consumedBooleanA);
        }
    }

    @Evil
    @Test
    void boolCns_to_dblSup() {
        synchronized (consumedBooleanA) {
            consumedBooleanA = false;

            assertEquals(
                suppliedDouble,
                Z.fuse(saveBooleanA).absorb(getDouble).applyAsDouble(true)
            );
            assertEquals(true, consumedBooleanA);
        }
    }

    @Evil
    @Test
    void boolCns_to_intSup() {
        synchronized (consumedBooleanA) {
            consumedBooleanA = false;

            assertEquals(
                suppliedInt,
                Z.fuse(saveBooleanA).absorb(getInt).applyAsInt(true)
            );
            assertEquals(true, consumedBooleanA);
        }
    }

    @Evil
    @Test
    void boolCns_to_longSup() {
        synchronized (consumedBooleanA) {
            consumedBooleanA = false;

            assertEquals(
                suppliedLong,
                Z.fuse(saveBooleanA).absorb(getLong).applyAsLong(true)
            );
            assertEquals(true, consumedBooleanA);
        }
    }

    @Evil
    @Test
    void boolCns_to_op() {
        synchronized (consumedBooleanA) {
            synchronized (wasOperated) {
                consumedBooleanA = false;
                wasOperated = false;

                Z.fuse(saveBooleanA).absorb(doOperation).accept(true);

                assertEquals(true, consumedBooleanA);
                assertTrue(wasOperated);
            }
        }
    }

    @Evil
    @Test
    void dblCns_to_sup() {
        synchronized (consumedDoubleA) {
            consumedDoubleA = 0.0;

            assertEquals(
                suppliedString,
                Z.fuse(saveDoubleA).absorb(getString).apply(1.0)
            );
            assertEquals(1.0, consumedDoubleA);
        }
    }

    @Evil
    @Test
    void dblCns_to_boolSup() {
        synchronized (consumedDoubleA) {
            consumedDoubleA = 0.0;

            assertEquals(
                true,
                Z.fuse(saveDoubleA).absorb(getBooleanTrue).test(1.0)
            );
            assertEquals(1.0, consumedDoubleA);
        }
    }

    @Evil
    @Test
    void dblCns_to_dblSup() {
        synchronized (consumedDoubleA) {
            consumedDoubleA = 0.0;

            assertEquals(
                suppliedDouble,
                Z.fuse(saveDoubleA).absorb(getDouble).applyAsDouble(1.0)
            );
            assertEquals(1.0, consumedDoubleA);
        }
    }

    @Evil
    @Test
    void dblCns_to_intSup() {
        synchronized (consumedDoubleA) {
            consumedDoubleA = 0.0;

            assertEquals(
                suppliedInt,
                Z.fuse(saveDoubleA).absorb(getInt).applyAsInt(1.0)
            );
            assertEquals(1.0, consumedDoubleA);
        }
    }

    @Evil
    @Test
    void dblCns_to_longSup() {
        synchronized (consumedDoubleA) {
            consumedDoubleA = 0.0;

            assertEquals(
                suppliedLong,
                Z.fuse(saveDoubleA).absorb(getLong).applyAsLong(1.0)
            );
            assertEquals(1.0, consumedDoubleA);
        }
    }

    @Evil
    @Test
    void dblCns_to_op() {
        synchronized (consumedDoubleA) {
            synchronized (wasOperated) {
                consumedDoubleA = 0.0;
                wasOperated = false;

                Z.fuse(saveDoubleA).absorb(doOperation).accept(1.0);

                assertEquals(1.0, consumedDoubleA);
                assertTrue(wasOperated);
            }
        }
    }

    @Evil
    @Test
    void objDblCns_to_sup() {
        synchronized (consumedStringD) {
            synchronized (consumedDoubleB) {
                consumedStringD = "";
                consumedDoubleB = 0.0;

                assertEquals(
                    suppliedString,
                    Z
                        .fuse(saveStringDDoubleB)
                        .absorb(getString)
                        .apply("yo")
                        .apply(1.0)
                );
                assertEquals("yo", consumedStringD);
                assertEquals(1.0, consumedDoubleB);
            }
        }
    }

    @Evil
    @Test
    void objDblCns_to_boolSup() {
        synchronized (consumedStringD) {
            synchronized (consumedDoubleB) {
                consumedStringD = "";
                consumedDoubleB = 0.0;

                assertEquals(
                    true,
                    Z
                        .fuse(saveStringDDoubleB)
                        .absorb(getBooleanTrue)
                        .apply("yo")
                        .test(1.0)
                );
                assertEquals("yo", consumedStringD);
                assertEquals(1.0, consumedDoubleB);
            }
        }
    }

    @Evil
    @Test
    void objDblCns_to_dblSup() {
        synchronized (consumedStringD) {
            synchronized (consumedDoubleB) {
                consumedStringD = "";
                consumedDoubleB = 0.0;

                assertEquals(
                    suppliedDouble,
                    Z
                        .fuse(saveStringDDoubleB)
                        .absorb(getDouble)
                        .apply("yo")
                        .applyAsDouble(1.0)
                );
                assertEquals("yo", consumedStringD);
                assertEquals(1.0, consumedDoubleB);
            }
        }
    }

    @Evil
    @Test
    void objDblCns_to_intSup() {
        synchronized (consumedStringD) {
            synchronized (consumedDoubleB) {
                consumedStringD = "";
                consumedDoubleB = 0.0;

                assertEquals(
                    suppliedInt,
                    Z
                        .fuse(saveStringDDoubleB)
                        .absorb(getInt)
                        .apply("yo")
                        .applyAsInt(1.0)
                );
                assertEquals("yo", consumedStringD);
                assertEquals(1.0, consumedDoubleB);
            }
        }
    }

    @Evil
    @Test
    void objDblCns_to_longSup() {
        synchronized (consumedStringD) {
            synchronized (consumedDoubleB) {
                consumedStringD = "";
                consumedDoubleB = 0.0;

                assertEquals(
                    suppliedLong,
                    Z
                        .fuse(saveStringDDoubleB)
                        .absorb(getLong)
                        .apply("yo")
                        .applyAsLong(1.0)
                );
                assertEquals("yo", consumedStringD);
                assertEquals(1.0, consumedDoubleB);
            }
        }
    }

    @Evil
    @Test
    void objDblCns_to_op() {
        synchronized (consumedStringD) {
            synchronized (consumedDoubleB) {
                synchronized (wasOperated) {
                    consumedStringD = "";
                    consumedDoubleB = 0.0;
                    wasOperated = false;

                    Z
                        .fuse(saveStringDDoubleB)
                        .absorb(doOperation)
                        .apply("yo")
                        .accept(1.0);

                    assertEquals("yo", consumedStringD);
                    assertEquals(1.0, consumedDoubleB);
                    assertTrue(wasOperated);
                }
            }
        }
    }

    @Evil
    @Test
    void intCns_to_sup() {
        synchronized (consumedIntA) {
            consumedIntA = 0;

            assertEquals(
                suppliedString,
                Z.fuse(saveIntA).absorb(getString).apply(1)
            );
            assertEquals(1, consumedIntA);
        }
    }

    @Evil
    @Test
    void intCns_to_boolSup() {
        synchronized (consumedIntA) {
            consumedIntA = 0;

            assertEquals(true, Z.fuse(saveIntA).absorb(getBooleanTrue).test(1));
            assertEquals(1, consumedIntA);
        }
    }

    @Evil
    @Test
    void intCns_to_dblSup() {
        synchronized (consumedIntA) {
            consumedIntA = 0;

            assertEquals(
                suppliedDouble,
                Z.fuse(saveIntA).absorb(getDouble).applyAsDouble(1)
            );
            assertEquals(1, consumedIntA);
        }
    }

    @Evil
    @Test
    void intCns_to_intSup() {
        synchronized (consumedIntA) {
            consumedIntA = 0;

            assertEquals(
                suppliedInt,
                Z.fuse(saveIntA).absorb(getInt).applyAsInt(1)
            );
            assertEquals(1, consumedIntA);
        }
    }

    @Evil
    @Test
    void intCns_to_longSup() {
        synchronized (consumedIntA) {
            consumedIntA = 0;

            assertEquals(
                suppliedLong,
                Z.fuse(saveIntA).absorb(getLong).applyAsLong(1)
            );
            assertEquals(1, consumedIntA);
        }
    }

    @Evil
    @Test
    void intCns_to_op() {
        synchronized (consumedIntA) {
            synchronized (wasOperated) {
                consumedIntA = 0;
                wasOperated = false;

                Z.fuse(saveIntA).absorb(doOperation).accept(1);

                assertEquals(1, consumedIntA);
                assertTrue(wasOperated);
            }
        }
    }

    @Evil
    @Test
    void objIntCns_to_sup() {
        synchronized (consumedStringE) {
            synchronized (consumedIntB) {
                consumedStringE = "";
                consumedIntB = 0;

                assertEquals(
                    suppliedString,
                    Z
                        .fuse(saveStringEIntB)
                        .absorb(getString)
                        .apply("yo")
                        .apply(1)
                );
                assertEquals("yo", consumedStringE);
                assertEquals(1, consumedIntB);
            }
        }
    }

    @Evil
    @Test
    void objIntCns_to_boolSup() {
        synchronized (consumedStringE) {
            synchronized (consumedIntB) {
                consumedStringE = "";
                consumedIntB = 0;

                assertEquals(
                    true,
                    Z
                        .fuse(saveStringEIntB)
                        .absorb(getBooleanTrue)
                        .apply("yo")
                        .test(1)
                );
                assertEquals("yo", consumedStringE);
                assertEquals(1, consumedIntB);
            }
        }
    }

    @Evil
    @Test
    void objIntCns_to_dblSup() {
        synchronized (consumedStringE) {
            synchronized (consumedIntB) {
                consumedStringE = "";
                consumedIntB = 0;

                assertEquals(
                    suppliedDouble,
                    Z
                        .fuse(saveStringEIntB)
                        .absorb(getDouble)
                        .apply("yo")
                        .applyAsDouble(1)
                );
                assertEquals("yo", consumedStringE);
                assertEquals(1, consumedIntB);
            }
        }
    }

    @Evil
    @Test
    void objIntCns_to_intSup() {
        synchronized (consumedStringE) {
            synchronized (consumedIntB) {
                consumedStringE = "";
                consumedIntB = 0;

                assertEquals(
                    suppliedInt,
                    Z
                        .fuse(saveStringEIntB)
                        .absorb(getInt)
                        .apply("yo")
                        .applyAsInt(1)
                );
                assertEquals("yo", consumedStringE);
                assertEquals(1, consumedIntB);
            }
        }
    }

    @Evil
    @Test
    void objIntCns_to_longSup() {
        synchronized (consumedStringE) {
            synchronized (consumedIntB) {
                consumedStringE = "";
                consumedIntB = 0;

                assertEquals(
                    suppliedLong,
                    Z
                        .fuse(saveStringEIntB)
                        .absorb(getLong)
                        .apply("yo")
                        .applyAsLong(1)
                );
                assertEquals("yo", consumedStringE);
                assertEquals(1, consumedIntB);
            }
        }
    }

    @Evil
    @Test
    void objIntCns_to_op() {
        synchronized (consumedStringE) {
            synchronized (consumedIntB) {
                synchronized (wasOperated) {
                    consumedStringE = "";
                    consumedIntB = 0;
                    wasOperated = false;

                    Z
                        .fuse(saveStringEIntB)
                        .absorb(doOperation)
                        .apply("yo")
                        .accept(1);

                    assertEquals("yo", consumedStringE);
                    assertEquals(1, consumedIntB);
                    assertTrue(wasOperated);
                }
            }
        }
    }

    @Evil
    @Test
    void longCns_to_sup() {
        synchronized (consumedLongA) {
            consumedLongA = 0L;

            assertEquals(
                suppliedString,
                Z.fuse(saveLongA).absorb(getString).apply(1L)
            );
            assertEquals(1L, consumedLongA);
        }
    }

    @Evil
    @Test
    void longCns_to_boolSup() {
        synchronized (consumedLongA) {
            consumedLongA = 0L;

            assertEquals(
                true,
                Z.fuse(saveLongA).absorb(getBooleanTrue).test(1L)
            );
            assertEquals(1L, consumedLongA);
        }
    }

    @Evil
    @Test
    void longCns_to_dblSup() {
        synchronized (consumedLongA) {
            consumedLongA = 0L;

            assertEquals(
                suppliedDouble,
                Z.fuse(saveLongA).absorb(getDouble).applyAsDouble(1L)
            );
            assertEquals(1L, consumedLongA);
        }
    }

    @Evil
    @Test
    void longCns_to_intSup() {
        synchronized (consumedLongA) {
            consumedLongA = 0L;

            assertEquals(
                suppliedInt,
                Z.fuse(saveLongA).absorb(getInt).applyAsInt(1L)
            );
            assertEquals(1L, consumedLongA);
        }
    }

    @Evil
    @Test
    void longCns_to_longSup() {
        synchronized (consumedLongA) {
            consumedLongA = 0L;

            assertEquals(
                suppliedLong,
                Z.fuse(saveLongA).absorb(getLong).applyAsLong(1L)
            );
            assertEquals(1L, consumedLongA);
        }
    }

    @Evil
    @Test
    void longCns_to_op() {
        synchronized (consumedLongA) {
            synchronized (wasOperated) {
                consumedLongA = 0L;
                wasOperated = false;

                Z.fuse(saveLongA).absorb(doOperation).accept(1L);

                assertEquals(1L, consumedLongA);
                assertTrue(wasOperated);
            }
        }
    }

    @Evil
    @Test
    void objLongCns_to_sup() {
        synchronized (consumedStringF) {
            synchronized (consumedLongB) {
                consumedStringF = "";
                consumedLongB = 0L;

                assertEquals(
                    suppliedString,
                    Z
                        .fuse(saveStringFLongB)
                        .absorb(getString)
                        .apply("yo")
                        .apply(1L)
                );
                assertEquals("yo", consumedStringF);
                assertEquals(1L, consumedLongB);
            }
        }
    }

    @Evil
    @Test
    void objLongCns_to_boolSup() {
        synchronized (consumedStringF) {
            synchronized (consumedLongB) {
                consumedStringF = "";
                consumedLongB = 0L;

                assertEquals(
                    true,
                    Z
                        .fuse(saveStringFLongB)
                        .absorb(getBooleanTrue)
                        .apply("yo")
                        .test(1L)
                );
                assertEquals("yo", consumedStringF);
                assertEquals(1L, consumedLongB);
            }
        }
    }

    @Evil
    @Test
    void objLongCns_to_dblSup() {
        synchronized (consumedStringF) {
            synchronized (consumedLongB) {
                consumedStringF = "";
                consumedLongB = 0L;

                assertEquals(
                    suppliedDouble,
                    Z
                        .fuse(saveStringFLongB)
                        .absorb(getDouble)
                        .apply("yo")
                        .applyAsDouble(1L)
                );
                assertEquals("yo", consumedStringF);
                assertEquals(1L, consumedLongB);
            }
        }
    }

    @Evil
    @Test
    void objLongCns_to_intSup() {
        synchronized (consumedStringF) {
            synchronized (consumedLongB) {
                consumedStringF = "";
                consumedLongB = 0L;

                assertEquals(
                    suppliedInt,
                    Z
                        .fuse(saveStringFLongB)
                        .absorb(getInt)
                        .apply("yo")
                        .applyAsInt(1L)
                );
                assertEquals("yo", consumedStringF);
                assertEquals(1L, consumedLongB);
            }
        }
    }

    @Evil
    @Test
    void objLongCns_to_longSup() {
        synchronized (consumedStringF) {
            synchronized (consumedLongB) {
                consumedStringF = "";
                consumedLongB = 0L;

                assertEquals(
                    suppliedLong,
                    Z
                        .fuse(saveStringFLongB)
                        .absorb(getLong)
                        .apply("yo")
                        .applyAsLong(1L)
                );
                assertEquals("yo", consumedStringF);
                assertEquals(1L, consumedLongB);
            }
        }
    }

    @Evil
    @Test
    void objLongCns_to_op() {
        synchronized (consumedStringF) {
            synchronized (consumedLongB) {
                synchronized (wasOperated) {
                    consumedStringF = "";
                    consumedLongB = 0L;
                    wasOperated = false;

                    Z
                        .fuse(saveStringFLongB)
                        .absorb(doOperation)
                        .apply("yo")
                        .accept(1L);

                    assertEquals("yo", consumedStringF);
                    assertEquals(1L, consumedLongB);
                    assertTrue(wasOperated);
                }
            }
        }
    }

    @Evil
    @Test
    void op_to_sup() {
        synchronized (wasOperated) {
            wasOperated = false;

            assertEquals(
                suppliedString,
                Z.fuse(doOperation).absorb(getString).get()
            );
            assertTrue(wasOperated);
        }
    }

    @Evil
    @Test
    void op_to_boolSup() {
        synchronized (wasOperated) {
            wasOperated = false;

            assertEquals(
                true,
                Z.fuse(doOperation).absorb(getBooleanTrue).getAsBoolean()
            );
            assertTrue(wasOperated);
        }
    }

    @Evil
    @Test
    void op_to_dblSup() {
        synchronized (wasOperated) {
            wasOperated = false;

            assertEquals(
                suppliedDouble,
                Z.fuse(doOperation).absorb(getDouble).getAsDouble()
            );
            assertTrue(wasOperated);
        }
    }

    @Evil
    @Test
    void op_to_intSup() {
        synchronized (wasOperated) {
            wasOperated = false;

            assertEquals(
                suppliedInt,
                Z.fuse(doOperation).absorb(getInt).getAsInt()
            );
            assertTrue(wasOperated);
        }
    }

    @Evil
    @Test
    void op_to_longSup() {
        synchronized (wasOperated) {
            wasOperated = false;

            assertEquals(
                suppliedLong,
                Z.fuse(doOperation).absorb(getLong).getAsLong()
            );
            assertTrue(wasOperated);
        }
    }

    @Evil
    @Test
    void op_to_op() {
        synchronized (wasOperated) {
            wasOperated = false;

            Z.fuse(doOperation).absorb(doOperation).run();

            assertTrue(wasOperated);
        }
    }
}
