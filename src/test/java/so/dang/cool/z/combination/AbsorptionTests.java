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
            saveStringA.accept("hello");
            assertEquals("hello", consumedStringA);

            consumedStringA = "";
            Z.with(saveStringA).resolve().accept("hello");
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
            Z.with(saveBooleanA).resolve().accept(true);
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
            Z.with(saveDoubleA).resolve().accept(1.5);
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
                Z.with(saveStringDDoubleB).resolve().apply("heyo").accept(1.5);
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
                Z.with(saveStringEIntB).resolve().apply("heyo").accept(2);
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
                Z.with(saveStringFLongB).resolve().apply("heyo").accept(2L);
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
            saveIntA.accept(1);
            assertEquals(1, consumedIntA);

            consumedIntA = 0;
            Z.with(saveIntA).resolve().accept(1);
            assertEquals(1, consumedIntA);
        }
    }

    @Evil
    @Test
    void longCns() {
        synchronized (consumedLongA) {
            consumedLongA = 0L;
            saveLongA.accept(1L);
            assertEquals(1L, consumedLongA);

            consumedLongA = 0L;
            Z.with(saveLongA).resolve().accept(1L);
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
                Z.absorb(saveStringA, trim).apply(" hello ").apply(" goodbye ")
            );
            assertEquals(" hello ", consumedStringA);
        }
    }

    @Evil
    @Test
    void cns_to_fn_deep() {
        synchronized (consumedStringA) {
            consumedStringA = "";

            assertEquals(
                "goodbye",
                Z
                    .with(saveStringA)
                    .absorb(trim)
                    .apply(" hello ")
                    .apply(" goodbye ")
            );
            assertEquals(" hello ", consumedStringA);
        }
    }

    @Evil
    @Test
    void cns_to_fn_deeper() {
        synchronized (consumedStringA) {
            consumedStringA = "";

            assertEquals(
                "goodbye",
                Z
                    .with(saveStringA)
                    .absorbing(trim)
                    .resolve()
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
                Z.absorb(saveStringA, getString).apply("hello")
            );
            assertEquals("hello", consumedStringA);
        }
    }

    @Evil
    @Test
    void cns_to_sup_deep() {
        synchronized (consumedStringA) {
            consumedStringA = "";

            assertEquals(
                suppliedString,
                Z.with(saveStringA).absorb(getString).apply("hello")
            );
            assertEquals("hello", consumedStringA);
        }
    }

    @Evil
    @Test
    void cns_to_sup_deeper() {
        synchronized (consumedStringA) {
            consumedStringA = "";

            assertEquals(
                suppliedString,
                Z
                    .with(saveStringA)
                    .absorbing(getString)
                    .resolve()
                    .apply("hello")
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
                Z.absorb(saveStringA, getBooleanTrue).test("salut")
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
                Z.absorb(saveStringA, getDouble).applyAsDouble("hola")
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
                Z.absorb(saveStringA, getInt).applyAsInt("안녕하세요")
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
                Z.absorb(saveStringA, getLong).applyAsLong("kamusta")
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

                Z.absorb(saveStringA, doOperation).accept("merhaba");

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

                Z.with(saveStringsBandC).resolve().apply("cześć").accept("喂");
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
                        .absorb(saveStringsBandC, getString)
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
                        .with(saveStringsBandC)
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
                        .with(saveStringsBandC)
                        .absorbing(getString)
                        .resolve()
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
                        .absorb(saveStringsBandC, getBooleanTrue)
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
                        .absorb(saveStringsBandC, getDouble)
                        .apply("buenas")
                        .applyAsDouble("dias")
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
                        .absorb(saveStringsBandC, getInt)
                        .apply("안녕")
                        .applyAsInt("하세요")
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
                        .absorb(saveStringsBandC, getLong)
                        .apply("ça")
                        .applyAsLong("va")
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
                        .absorb(saveStringsBandC, doOperation)
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
    void dblCns_to_sup() {
        synchronized (consumedDoubleA) {
            consumedDoubleA = 0.0;

            assertEquals(
                suppliedString,
                Z.absorb(saveDoubleA, getString).apply(1.0)
            );
            assertEquals(1.0, consumedDoubleA);
        }
    }

    @Evil
    @Test
    void dblCns_to_boolSup() {
        synchronized (consumedDoubleA) {
            consumedDoubleA = 0.0;

            assertEquals(true, Z.absorb(saveDoubleA, getBooleanTrue).test(1.0));
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
                Z.absorb(saveDoubleA, getDouble).applyAsDouble(1.0)
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
                Z.absorb(saveDoubleA, getInt).applyAsInt(1.0)
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
                Z.absorb(saveDoubleA, getLong).applyAsLong(1.0)
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

                Z.absorb(saveDoubleA, doOperation).accept(1.0);

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
                        .absorb(saveStringDDoubleB, getString)
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
                        .absorb(saveStringDDoubleB, getBooleanTrue)
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
                        .absorb(saveStringDDoubleB, getDouble)
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
                        .absorb(saveStringDDoubleB, getInt)
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
                        .absorb(saveStringDDoubleB, getLong)
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
                        .absorb(saveStringDDoubleB, doOperation)
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
                Z.absorb(saveIntA, getString).apply(1)
            );
            assertEquals(1, consumedIntA);
        }
    }

    @Evil
    @Test
    void intCns_to_boolSup() {
        synchronized (consumedIntA) {
            consumedIntA = 0;

            assertEquals(true, Z.absorb(saveIntA, getBooleanTrue).test(1));
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
                Z.absorb(saveIntA, getDouble).applyAsDouble(1)
            );
            assertEquals(1, consumedIntA);
        }
    }

    @Evil
    @Test
    void intCns_to_intSup() {
        synchronized (consumedIntA) {
            consumedIntA = 0;

            assertEquals(suppliedInt, Z.absorb(saveIntA, getInt).applyAsInt(1));
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
                Z.absorb(saveIntA, getLong).applyAsLong(1)
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

                Z.absorb(saveIntA, doOperation).accept(1);

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
                    Z.absorb(saveStringEIntB, getString).apply("yo").apply(1)
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
                        .absorb(saveStringEIntB, getBooleanTrue)
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
                        .absorb(saveStringEIntB, getDouble)
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
                    Z.absorb(saveStringEIntB, getInt).apply("yo").applyAsInt(1)
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
                        .absorb(saveStringEIntB, getLong)
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
                        .absorb(saveStringEIntB, doOperation)
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
                Z.absorb(saveLongA, getString).apply(1L)
            );
            assertEquals(1L, consumedLongA);
        }
    }

    @Evil
    @Test
    void longCns_to_boolSup() {
        synchronized (consumedLongA) {
            consumedLongA = 0L;

            assertEquals(true, Z.absorb(saveLongA, getBooleanTrue).test(1L));
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
                Z.absorb(saveLongA, getDouble).applyAsDouble(1L)
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
                Z.absorb(saveLongA, getInt).applyAsInt(1L)
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
                Z.absorb(saveLongA, getLong).applyAsLong(1L)
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

                Z.absorb(saveLongA, doOperation).accept(1L);

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
                    Z.absorb(saveStringFLongB, getString).apply("yo").apply(1L)
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
                        .absorb(saveStringFLongB, getBooleanTrue)
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
                        .absorb(saveStringFLongB, getDouble)
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
                        .absorb(saveStringFLongB, getInt)
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
                        .absorb(saveStringFLongB, getLong)
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
                        .absorb(saveStringFLongB, doOperation)
                        .apply("yo")
                        .accept(1L);

                    assertEquals("yo", consumedStringF);
                    assertEquals(1L, consumedLongB);
                    assertTrue(wasOperated);
                }
            }
        }
    }

    // TODO: <Operator>

    @Evil
    @Test
    void op_to_sup() {
        synchronized (wasOperated) {
            wasOperated = false;

            assertEquals(
                suppliedString,
                Z.absorb(doOperation, getString).get()
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
                Z.absorb(doOperation, getBooleanTrue).getAsBoolean()
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
                Z.absorb(doOperation, getDouble).getAsDouble()
            );
            assertTrue(wasOperated);
        }
    }

    @Evil
    @Test
    void op_to_intSup() {
        synchronized (wasOperated) {
            wasOperated = false;

            assertEquals(suppliedInt, Z.absorb(doOperation, getInt).getAsInt());
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
                Z.absorb(doOperation, getLong).getAsLong()
            );
            assertTrue(wasOperated);
        }
    }

    @Evil
    @Test
    void op_to_op() {
        synchronized (wasOperated) {
            wasOperated = false;

            Z.absorb(doOperation, doOperation).run();

            assertTrue(wasOperated);
        }
    }
}
