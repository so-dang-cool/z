package so.dang.cool.z.combination;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static so.dang.cool.z.combination.TestFunctions.*;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import so.dang.cool.z.Z;
import so.dang.cool.z.annotation.Evil;

@Evil
public class AbsorptionTests {

    @Evil
    @Test
    void cns() {
        synchronized (consumedStringA) {
            Stream
                .of(saveStringA, Z.with(saveStringA).resolve())
                .forEach(
                    consumer -> {
                        consumedStringA = "";
                        consumer.accept("hello");
                        assertEquals("hello", consumedStringA);
                    }
                );
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
            Stream
                .of(saveIntA, Z.with(saveIntA).resolve())
                .forEach(
                    consumer -> {
                        consumedIntA = 0;
                        consumer.accept(1);
                        assertEquals(1, consumedIntA);
                    }
                );
        }
    }

    @Evil
    @Test
    void longCns() {
        synchronized (consumedLongA) {
            Stream
                .of(saveLongA, Z.with(saveLongA).resolve())
                .forEach(
                    consumer -> {
                        consumedLongA = 0L;
                        consumer.accept(1L);
                        assertEquals(1L, consumedLongA);
                    }
                );
        }
    }

    @Evil
    @Test
    void cns_to_fn() {
        synchronized (consumedStringA) {
            Stream
                .of(
                    Z.absorb(saveStringA, trim),
                    Z.with(saveStringA).absorb(trim),
                    Z.with(saveStringA).absorbing(trim).resolve()
                )
                .forEach(
                    fusion -> {
                        consumedStringA = "";

                        assertEquals(
                            "goodbye",
                            fusion.apply(" hello ").apply(" goodbye ")
                        );
                        assertEquals(" hello ", consumedStringA);
                    }
                );
        }
    }

    @Evil
    @Test
    void cns_to_sup() {
        synchronized (consumedStringA) {
            Stream
                .of(
                    Z.absorb(saveStringA, getString),
                    Z.with(saveStringA).absorb(getString),
                    Z.with(saveStringA).absorbing(getString).resolve()
                )
                .forEach(
                    fusion -> {
                        consumedStringA = "";

                        assertEquals(suppliedString, fusion.apply("hello"));
                        assertEquals("hello", consumedStringA);
                    }
                );
        }
    }

    @Evil
    @Test
    void cns_to_boolSup() {
        synchronized (consumedStringA) {
            Stream
                .of(
                    Z.absorb(saveStringA, getBooleanTrue),
                    Z.with(saveStringA).absorb(getBooleanTrue),
                    Z.with(saveStringA).absorbing(getBooleanTrue).resolve()
                )
                .forEach(
                    fusion -> {
                        consumedStringA = "";

                        assertEquals(true, fusion.test("salut"));
                        assertEquals("salut", consumedStringA);
                    }
                );
        }
    }

    @Evil
    @Test
    void cns_to_dblSup() {
        synchronized (consumedStringA) {
            Stream
                .of(
                    Z.absorb(saveStringA, getDouble),
                    Z.with(saveStringA).absorb(getDouble),
                    Z.with(saveStringA).absorbing(getDouble).resolve()
                )
                .forEach(
                    fusion -> {
                        consumedStringA = "";

                        assertEquals(
                            suppliedDouble,
                            fusion.applyAsDouble("hola")
                        );
                        assertEquals("hola", consumedStringA);
                    }
                );
        }
    }

    @Evil
    @Test
    void cns_to_intSup() {
        synchronized (consumedStringA) {
            Stream
                .of(
                    Z.absorb(saveStringA, getInt),
                    Z.with(saveStringA).absorb(getInt),
                    Z.with(saveStringA).absorbing(getInt).resolve()
                )
                .forEach(
                    fusion -> {
                        consumedStringA = "";

                        assertEquals(
                            suppliedInt,
                            fusion.applyAsInt("안녕하세요")
                        );
                        assertEquals("안녕하세요", consumedStringA);
                    }
                );
        }
    }

    @Evil
    @Test
    void cns_to_longSup() {
        synchronized (consumedStringA) {
            Stream
                .of(
                    Z.absorb(saveStringA, getLong),
                    Z.with(saveStringA).absorb(getLong),
                    Z.with(saveStringA).absorbing(getLong).resolve()
                )
                .forEach(
                    fusion -> {
                        consumedStringA = "";

                        assertEquals(
                            suppliedLong,
                            fusion.applyAsLong("kamusta")
                        );
                        assertEquals("kamusta", consumedStringA);
                    }
                );
        }
    }

    @Evil
    @Test
    void cns_to_op() {
        synchronized (consumedStringA) {
            synchronized (wasOperated) {
                Stream
                    .of(
                        Z.absorb(saveStringA, doOperation),
                        Z.with(saveStringA).absorb(doOperation),
                        Z.with(saveStringA).absorbing(doOperation).resolve()
                    )
                    .forEach(
                        fusion -> {
                            consumedStringA = "";
                            wasOperated = false;

                            fusion.accept("merhaba");

                            assertEquals("merhaba", consumedStringA);
                            assertTrue(wasOperated);
                        }
                    );
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

                /* deep */

                consumedStringB = "";
                consumedStringC = "";

                assertEquals(
                    true,
                    Z
                        .with(saveStringsBandC)
                        .absorb(getBooleanTrue)
                        .apply("hei")
                        .test("hej")
                );
                assertEquals("hei", consumedStringB);
                assertEquals("hej", consumedStringC);

                /* deeper */

                consumedStringB = "";
                consumedStringC = "";

                assertEquals(
                    true,
                    Z
                        .with(saveStringsBandC)
                        .absorbing(getBooleanTrue)
                        .resolve()
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

                /* deep */

                consumedStringB = "";
                consumedStringC = "";

                assertEquals(
                    suppliedDouble,
                    Z
                        .with(saveStringsBandC)
                        .absorb(getDouble)
                        .apply("buenas")
                        .applyAsDouble("dias")
                );
                assertEquals("buenas", consumedStringB);
                assertEquals("dias", consumedStringC);

                /* deeper */

                consumedStringB = "";
                consumedStringC = "";

                assertEquals(
                    suppliedDouble,
                    Z
                        .with(saveStringsBandC)
                        .absorbing(getDouble)
                        .resolve()
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
                        .absorb(saveStringsBandC, getInt)
                        .apply("안녕")
                        .applyAsInt("하세요")
                );
                assertEquals("안녕", consumedStringB);
                assertEquals("하세요", consumedStringC);

                /* deep */

                consumedStringB = "";
                consumedStringC = "";

                assertEquals(
                    suppliedInt,
                    Z
                        .with(saveStringsBandC)
                        .absorb(getInt)
                        .apply("안녕")
                        .applyAsInt("하세요")
                );
                assertEquals("안녕", consumedStringB);
                assertEquals("하세요", consumedStringC);

                /* deeper */

                consumedStringB = "";
                consumedStringC = "";

                assertEquals(
                    suppliedInt,
                    Z
                        .with(saveStringsBandC)
                        .absorbing(getInt)
                        .resolve()
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
                        .absorb(saveStringsBandC, getLong)
                        .apply("ça")
                        .applyAsLong("va")
                );
                assertEquals("ça", consumedStringB);
                assertEquals("va", consumedStringC);

                /* deep */

                consumedStringB = "";
                consumedStringC = "";

                assertEquals(
                    suppliedLong,
                    Z
                        .with(saveStringsBandC)
                        .absorb(getLong)
                        .apply("ça")
                        .applyAsLong("va")
                );
                assertEquals("ça", consumedStringB);
                assertEquals("va", consumedStringC);

                /* deeper */

                consumedStringB = "";
                consumedStringC = "";

                assertEquals(
                    suppliedLong,
                    Z
                        .with(saveStringsBandC)
                        .absorbing(getLong)
                        .resolve()
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
                        .absorb(saveStringsBandC, doOperation)
                        .apply("...")
                        .accept("!!!");

                    assertEquals("...", consumedStringB);
                    assertEquals("!!!", consumedStringC);
                    assertTrue(wasOperated);

                    /* deep */

                    consumedStringB = "";
                    consumedStringC = "";
                    wasOperated = false;

                    Z
                        .with(saveStringsBandC)
                        .absorb(doOperation)
                        .apply("...")
                        .accept("!!!");

                    assertEquals("...", consumedStringB);
                    assertEquals("!!!", consumedStringC);
                    assertTrue(wasOperated);

                    /* deeper */

                    consumedStringB = "";
                    consumedStringC = "";
                    wasOperated = false;

                    Z
                        .with(saveStringsBandC)
                        .absorbing(doOperation)
                        .resolve()
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
            Stream
                .of(
                    Z.absorb(saveBooleanA, getString),
                    Z.with(saveBooleanA).absorb(getString),
                    Z.with(saveBooleanA).absorbing(getString).resolve()
                )
                .forEach(
                    fusion -> {
                        consumedBooleanA = false;

                        assertEquals(suppliedString, fusion.apply(true));
                        assertEquals(true, consumedBooleanA);
                    }
                );
        }
    }

    @Evil
    @Test
    void boolCns_to_boolSup() {
        synchronized (consumedBooleanA) {
            Stream
                .of(
                    Z.absorb(saveBooleanA, getBooleanTrue),
                    Z.with(saveBooleanA).absorb(getBooleanTrue),
                    Z.with(saveBooleanA).absorbing(getBooleanTrue).resolve()
                )
                .forEach(
                    fusion -> {
                        consumedBooleanA = false;

                        assertEquals(true, fusion.test(true));
                        assertEquals(true, consumedBooleanA);
                    }
                );
        }
    }

    @Evil
    @Test
    void boolCns_to_dblSup() {
        synchronized (consumedBooleanA) {
            Stream
                .of(
                    Z.absorb(saveBooleanA, getDouble),
                    Z.with(saveBooleanA).absorb(getDouble),
                    Z.with(saveBooleanA).absorbing(getDouble).resolve()
                )
                .forEach(
                    fusion -> {
                        consumedBooleanA = false;

                        assertEquals(
                            suppliedDouble,
                            fusion.applyAsDouble(true)
                        );
                        assertEquals(true, consumedBooleanA);
                    }
                );
        }
    }

    @Evil
    @Test
    void boolCns_to_intSup() {
        synchronized (consumedBooleanA) {
            Stream
                .of(
                    Z.absorb(saveBooleanA, getInt),
                    Z.with(saveBooleanA).absorb(getInt),
                    Z.with(saveBooleanA).absorbing(getInt).resolve()
                )
                .forEach(
                    fusion -> {
                        consumedBooleanA = false;

                        assertEquals(suppliedInt, fusion.applyAsInt(true));
                        assertEquals(true, consumedBooleanA);
                    }
                );
        }
    }

    @Evil
    @Test
    void boolCns_to_longSup() {
        synchronized (consumedBooleanA) {
            Stream
                .of(
                    Z.absorb(saveBooleanA, getLong),
                    Z.with(saveBooleanA).absorb(getLong),
                    Z.with(saveBooleanA).absorbing(getLong).resolve()
                )
                .forEach(
                    fusion -> {
                        consumedBooleanA = false;

                        assertEquals(suppliedLong, fusion.applyAsLong(true));
                        assertEquals(true, consumedBooleanA);
                    }
                );
        }
    }

    @Evil
    @Test
    void boolCns_to_op() {
        synchronized (consumedBooleanA) {
            synchronized (wasOperated) {
                Stream
                    .of(
                        Z.absorb(saveBooleanA, doOperation),
                        Z.with(saveBooleanA).absorb(doOperation),
                        Z.with(saveBooleanA).absorbing(doOperation).resolve()
                    )
                    .forEach(
                        fusion -> {
                            consumedBooleanA = false;
                            wasOperated = false;

                            fusion.accept(true);

                            assertEquals(true, consumedBooleanA);
                            assertTrue(wasOperated);
                        }
                    );
            }
        }
    }

    @Evil
    @Test
    void dblCns_to_sup() {
        synchronized (consumedDoubleA) {
            Stream
                .of(
                    Z.absorb(saveDoubleA, getString),
                    Z.with(saveDoubleA).absorb(getString),
                    Z.with(saveDoubleA).absorbing(getString).resolve()
                )
                .forEach(
                    fusion -> {
                        consumedDoubleA = 0.0;

                        assertEquals(suppliedString, fusion.apply(1.0));
                        assertEquals(1.0, consumedDoubleA);
                    }
                );
        }
    }

    @Evil
    @Test
    void dblCns_to_boolSup() {
        synchronized (consumedDoubleA) {
            Stream
                .of(
                    Z.absorb(saveDoubleA, getBooleanTrue),
                    Z.with(saveDoubleA).absorb(getBooleanTrue),
                    Z.with(saveDoubleA).absorbing(getBooleanTrue).resolve()
                )
                .forEach(
                    fusion -> {
                        consumedDoubleA = 0.0;

                        assertEquals(true, fusion.test(1.0));
                        assertEquals(1.0, consumedDoubleA);
                    }
                );
        }
    }

    @Evil
    @Test
    void dblCns_to_dblSup() {
        synchronized (consumedDoubleA) {
            Stream
                .of(
                    Z.absorb(saveDoubleA, getDouble),
                    Z.with(saveDoubleA).absorb(getDouble),
                    Z.with(saveDoubleA).absorbing(getDouble).resolve()
                )
                .forEach(
                    fusion -> {
                        consumedDoubleA = 0.0;

                        assertEquals(suppliedDouble, fusion.applyAsDouble(1.0));
                        assertEquals(1.0, consumedDoubleA);
                    }
                );
        }
    }

    @Evil
    @Test
    void dblCns_to_intSup() {
        synchronized (consumedDoubleA) {
            Stream
                .of(
                    Z.absorb(saveDoubleA, getInt),
                    Z.with(saveDoubleA).absorb(getInt),
                    Z.with(saveDoubleA).absorbing(getInt).resolve()
                )
                .forEach(
                    fusion -> {
                        consumedDoubleA = 0.0;

                        assertEquals(suppliedInt, fusion.applyAsInt(1.0));
                        assertEquals(1.0, consumedDoubleA);
                    }
                );
        }
    }

    @Evil
    @Test
    void dblCns_to_longSup() {
        synchronized (consumedDoubleA) {
            Stream
                .of(
                    Z.absorb(saveDoubleA, getLong),
                    Z.with(saveDoubleA).absorb(getLong),
                    Z.with(saveDoubleA).absorbing(getLong).resolve()
                )
                .forEach(
                    fusion -> {
                        consumedDoubleA = 0.0;

                        assertEquals(suppliedLong, fusion.applyAsLong(1.0));
                        assertEquals(1.0, consumedDoubleA);
                    }
                );
        }
    }

    @Evil
    @Test
    void dblCns_to_op() {
        synchronized (consumedDoubleA) {
            synchronized (wasOperated) {
                Stream
                    .of(
                        Z.absorb(saveDoubleA, doOperation),
                        Z.with(saveDoubleA).absorb(doOperation),
                        Z.with(saveDoubleA).absorbing(doOperation).resolve()
                    )
                    .forEach(
                        fusion -> {
                            consumedDoubleA = 0.0;
                            wasOperated = false;

                            fusion.accept(1.0);

                            assertEquals(1.0, consumedDoubleA);
                            assertTrue(wasOperated);
                        }
                    );
            }
        }
    }

    @Evil
    @Test
    void objDblCns_to_sup() {
        synchronized (consumedStringD) {
            synchronized (consumedDoubleB) {
                Stream
                    .of(
                        Z.absorb(saveStringDDoubleB, getString),
                        Z.with(saveStringDDoubleB).absorb(getString)
                    )
                    .forEach(
                        fusion -> {
                            consumedStringD = "";
                            consumedDoubleB = 0.0;

                            assertEquals(
                                suppliedString,
                                fusion.apply("yo").apply(1.0)
                            );
                            assertEquals("yo", consumedStringD);
                            assertEquals(1.0, consumedDoubleB);
                        }
                    );
            }
        }
    }

    @Evil
    @Test
    void objDblCns_to_boolSup() {
        synchronized (consumedStringD) {
            synchronized (consumedDoubleB) {
                Stream
                    .of(
                        Z.absorb(saveStringDDoubleB, getBooleanTrue),
                        Z.with(saveStringDDoubleB).absorb(getBooleanTrue)
                    )
                    .forEach(
                        fusion -> {
                            consumedStringD = "";
                            consumedDoubleB = 0.0;

                            assertEquals(true, fusion.apply("yo").test(1.0));
                            assertEquals("yo", consumedStringD);
                            assertEquals(1.0, consumedDoubleB);
                        }
                    );
            }
        }
    }

    @Evil
    @Test
    void objDblCns_to_dblSup() {
        synchronized (consumedStringD) {
            synchronized (consumedDoubleB) {
                Stream
                    .of(
                        Z.absorb(saveStringDDoubleB, getDouble),
                        Z.with(saveStringDDoubleB).absorb(getDouble)
                    )
                    .forEach(
                        fusion -> {
                            consumedStringD = "";
                            consumedDoubleB = 0.0;

                            assertEquals(
                                suppliedDouble,
                                fusion.apply("yo").applyAsDouble(1.0)
                            );
                            assertEquals("yo", consumedStringD);
                            assertEquals(1.0, consumedDoubleB);
                        }
                    );
            }
        }
    }

    @Evil
    @Test
    void objDblCns_to_intSup() {
        synchronized (consumedStringD) {
            synchronized (consumedDoubleB) {
                Stream
                    .of(
                        Z.absorb(saveStringDDoubleB, getInt),
                        Z.with(saveStringDDoubleB).absorb(getInt)
                    )
                    .forEach(
                        fusion -> {
                            consumedStringD = "";
                            consumedDoubleB = 0.0;

                            assertEquals(
                                suppliedInt,
                                fusion.apply("yo").applyAsInt(1.0)
                            );
                            assertEquals("yo", consumedStringD);
                            assertEquals(1.0, consumedDoubleB);
                        }
                    );
            }
        }
    }

    @Evil
    @Test
    void objDblCns_to_longSup() {
        synchronized (consumedStringD) {
            synchronized (consumedDoubleB) {
                Stream
                    .of(
                        Z.absorb(saveStringDDoubleB, getLong),
                        Z.with(saveStringDDoubleB).absorb(getLong)
                    )
                    .forEach(
                        fusion -> {
                            consumedStringD = "";
                            consumedDoubleB = 0.0;

                            assertEquals(
                                suppliedLong,
                                fusion.apply("yo").applyAsLong(1.0)
                            );
                            assertEquals("yo", consumedStringD);
                            assertEquals(1.0, consumedDoubleB);
                        }
                    );
            }
        }
    }

    @Evil
    @Test
    void objDblCns_to_op() {
        synchronized (consumedStringD) {
            synchronized (consumedDoubleB) {
                synchronized (wasOperated) {
                    Stream
                        .of(
                            Z.absorb(saveStringDDoubleB, doOperation),
                            Z.with(saveStringDDoubleB).absorb(doOperation),
                            Z
                                .with(saveStringDDoubleB)
                                .absorbing(doOperation)
                                .resolve()
                        )
                        .forEach(
                            fusion -> {
                                consumedStringD = "";
                                consumedDoubleB = 0.0;
                                wasOperated = false;

                                fusion.apply("yo").accept(1.0);

                                assertEquals("yo", consumedStringD);
                                assertEquals(1.0, consumedDoubleB);
                                assertTrue(wasOperated);
                            }
                        );
                }
            }
        }
    }

    @Evil
    @Test
    void intCns_to_sup() {
        synchronized (consumedIntA) {
            Stream
                .of(
                    Z.absorb(saveIntA, getString),
                    Z.with(saveIntA).absorb(getString),
                    Z.with(saveIntA).absorbing(getString).resolve()
                )
                .forEach(
                    fusion -> {
                        consumedIntA = 0;

                        assertEquals(suppliedString, fusion.apply(1));
                        assertEquals(1, consumedIntA);
                    }
                );
        }
    }

    @Evil
    @Test
    void intCns_to_boolSup() {
        synchronized (consumedIntA) {
            Stream
                .of(
                    Z.absorb(saveIntA, getBooleanTrue),
                    Z.with(saveIntA).absorb(getBooleanTrue),
                    Z.with(saveIntA).absorbing(getBooleanTrue).resolve()
                )
                .forEach(
                    fusion -> {
                        consumedIntA = 0;

                        assertEquals(true, fusion.test(1));
                        assertEquals(1, consumedIntA);
                    }
                );
        }
    }

    @Evil
    @Test
    void intCns_to_dblSup() {
        synchronized (consumedIntA) {
            Stream
                .of(
                    Z.absorb(saveIntA, getDouble),
                    Z.with(saveIntA).absorb(getDouble),
                    Z.with(saveIntA).absorbing(getDouble).resolve()
                )
                .forEach(
                    fusion -> {
                        consumedIntA = 0;

                        assertEquals(suppliedDouble, fusion.applyAsDouble(1));
                        assertEquals(1, consumedIntA);
                    }
                );
        }
    }

    @Evil
    @Test
    void intCns_to_intSup() {
        synchronized (consumedIntA) {
            Stream
                .of(
                    Z.absorb(saveIntA, getInt),
                    Z.with(saveIntA).absorb(getInt),
                    Z.with(saveIntA).absorbing(getInt).resolve()
                )
                .forEach(
                    fusion -> {
                        consumedIntA = 0;

                        assertEquals(suppliedInt, fusion.applyAsInt(1));
                        assertEquals(1, consumedIntA);
                    }
                );
        }
    }

    @Evil
    @Test
    void intCns_to_longSup() {
        synchronized (consumedIntA) {
            Stream
                .of(
                    Z.absorb(saveIntA, getLong),
                    Z.with(saveIntA).absorb(getLong),
                    Z.with(saveIntA).absorbing(getLong).resolve()
                )
                .forEach(
                    fusion -> {
                        consumedIntA = 0;

                        assertEquals(suppliedLong, fusion.applyAsLong(1));
                        assertEquals(1, consumedIntA);
                    }
                );
        }
    }

    @Evil
    @Test
    void intCns_to_op() {
        synchronized (consumedIntA) {
            synchronized (wasOperated) {
                Stream
                    .of(
                        Z.absorb(saveIntA, doOperation),
                        Z.with(saveIntA).absorb(doOperation),
                        Z.with(saveIntA).absorbing(doOperation).resolve()
                    )
                    .forEach(
                        fusion -> {
                            consumedIntA = 0;
                            wasOperated = false;

                            fusion.accept(1);

                            assertEquals(1, consumedIntA);
                            assertTrue(wasOperated);
                        }
                    );
            }
        }
    }

    @Evil
    @Test
    void objIntCns_to_sup() {
        synchronized (consumedStringE) {
            synchronized (consumedIntB) {
                Stream
                    .of(
                        Z.absorb(saveStringEIntB, getString),
                        Z.with(saveStringEIntB).absorb(getString)
                    )
                    .forEach(
                        fusion -> {
                            consumedStringE = "";
                            consumedIntB = 0;

                            assertEquals(
                                suppliedString,
                                fusion.apply("yo").apply(1)
                            );
                            assertEquals("yo", consumedStringE);
                            assertEquals(1, consumedIntB);
                        }
                    );
            }
        }
    }

    @Evil
    @Test
    void objIntCns_to_boolSup() {
        synchronized (consumedStringE) {
            synchronized (consumedIntB) {
                Stream
                    .of(
                        Z.absorb(saveStringEIntB, getBooleanTrue),
                        Z.with(saveStringEIntB).absorb(getBooleanTrue)
                    )
                    .forEach(
                        fusion -> {
                            consumedStringE = "";
                            consumedIntB = 0;

                            assertEquals(true, fusion.apply("yo").test(1));
                            assertEquals("yo", consumedStringE);
                            assertEquals(1, consumedIntB);
                        }
                    );
            }
        }
    }

    @Evil
    @Test
    void objIntCns_to_dblSup() {
        synchronized (consumedStringE) {
            synchronized (consumedIntB) {
                Stream
                    .of(
                        Z.absorb(saveStringEIntB, getDouble),
                        Z.with(saveStringEIntB).absorb(getDouble)
                    )
                    .forEach(
                        fusion -> {
                            consumedStringE = "";
                            consumedIntB = 0;

                            assertEquals(
                                suppliedDouble,
                                fusion.apply("yo").applyAsDouble(1)
                            );
                            assertEquals("yo", consumedStringE);
                            assertEquals(1, consumedIntB);
                        }
                    );
            }
        }
    }

    @Evil
    @Test
    void objIntCns_to_intSup() {
        synchronized (consumedStringE) {
            synchronized (consumedIntB) {
                Stream
                    .of(
                        Z.absorb(saveStringEIntB, getInt),
                        Z.with(saveStringEIntB).absorb(getInt)
                    )
                    .forEach(
                        fusion -> {
                            consumedStringE = "";
                            consumedIntB = 0;

                            assertEquals(
                                suppliedInt,
                                fusion.apply("yo").applyAsInt(1)
                            );
                            assertEquals("yo", consumedStringE);
                            assertEquals(1, consumedIntB);
                        }
                    );
            }
        }
    }

    @Evil
    @Test
    void objIntCns_to_longSup() {
        synchronized (consumedStringE) {
            synchronized (consumedIntB) {
                Stream
                    .of(
                        Z.absorb(saveStringEIntB, getLong),
                        Z.with(saveStringEIntB).absorb(getLong)
                    )
                    .forEach(
                        fusion -> {
                            consumedStringE = "";
                            consumedIntB = 0;

                            assertEquals(
                                suppliedLong,
                                fusion.apply("yo").applyAsLong(1)
                            );
                            assertEquals("yo", consumedStringE);
                            assertEquals(1, consumedIntB);
                        }
                    );
            }
        }
    }

    @Evil
    @Test
    void objIntCns_to_op() {
        synchronized (consumedStringE) {
            synchronized (consumedIntB) {
                synchronized (wasOperated) {
                    Stream
                        .of(
                            Z.absorb(saveStringEIntB, doOperation),
                            Z.with(saveStringEIntB).absorb(doOperation),
                            Z
                                .with(saveStringEIntB)
                                .absorbing(doOperation)
                                .resolve()
                        )
                        .forEach(
                            fusion -> {
                                consumedStringE = "";
                                consumedIntB = 0;
                                wasOperated = false;

                                fusion.apply("yo").accept(1);

                                assertEquals("yo", consumedStringE);
                                assertEquals(1, consumedIntB);
                                assertTrue(wasOperated);
                            }
                        );
                }
            }
        }
    }

    @Evil
    @Test
    void longCns_to_sup() {
        synchronized (consumedLongA) {
            Stream
                .of(
                    Z.absorb(saveLongA, getString),
                    Z.with(saveLongA).absorb(getString),
                    Z.with(saveLongA).absorbing(getString).resolve()
                )
                .forEach(
                    fusion -> {
                        consumedLongA = 0L;

                        assertEquals(suppliedString, fusion.apply(1L));
                        assertEquals(1L, consumedLongA);
                    }
                );
        }
    }

    @Evil
    @Test
    void longCns_to_boolSup() {
        synchronized (consumedLongA) {
            Stream
                .of(
                    Z.absorb(saveLongA, getBooleanTrue),
                    Z.with(saveLongA).absorb(getBooleanTrue),
                    Z.with(saveLongA).absorbing(getBooleanTrue).resolve()
                )
                .forEach(
                    fusion -> {
                        consumedLongA = 0L;

                        assertEquals(true, fusion.test(1L));
                        assertEquals(1L, consumedLongA);
                    }
                );
        }
    }

    @Evil
    @Test
    void longCns_to_dblSup() {
        synchronized (consumedLongA) {
            Stream
                .of(
                    Z.absorb(saveLongA, getDouble),
                    Z.with(saveLongA).absorb(getDouble),
                    Z.with(saveLongA).absorbing(getDouble).resolve()
                )
                .forEach(
                    fusion -> {
                        consumedLongA = 0L;

                        assertEquals(suppliedDouble, fusion.applyAsDouble(1L));
                        assertEquals(1L, consumedLongA);
                    }
                );
        }
    }

    @Evil
    @Test
    void longCns_to_intSup() {
        synchronized (consumedLongA) {
            Stream
                .of(
                    Z.absorb(saveLongA, getInt),
                    Z.with(saveLongA).absorb(getInt),
                    Z.with(saveLongA).absorbing(getInt).resolve()
                )
                .forEach(
                    fusion -> {
                        consumedLongA = 0L;

                        assertEquals(suppliedInt, fusion.applyAsInt(1L));
                        assertEquals(1L, consumedLongA);
                    }
                );
        }
    }

    @Evil
    @Test
    void longCns_to_longSup() {
        synchronized (consumedLongA) {
            Stream
                .of(
                    Z.absorb(saveLongA, getLong),
                    Z.with(saveLongA).absorb(getLong),
                    Z.with(saveLongA).absorbing(getLong).resolve()
                )
                .forEach(
                    fusion -> {
                        consumedLongA = 0L;

                        assertEquals(suppliedLong, fusion.applyAsLong(1L));
                        assertEquals(1L, consumedLongA);
                    }
                );
        }
    }

    @Evil
    @Test
    void longCns_to_op() {
        synchronized (consumedLongA) {
            synchronized (wasOperated) {
                Stream
                    .of(
                        Z.absorb(saveLongA, doOperation),
                        Z.with(saveLongA).absorb(doOperation),
                        Z.with(saveLongA).absorbing(doOperation).resolve()
                    )
                    .forEach(
                        fusion -> {
                            consumedLongA = 0L;
                            wasOperated = false;

                            fusion.accept(1L);

                            assertEquals(1L, consumedLongA);
                            assertTrue(wasOperated);
                        }
                    );
            }
        }
    }

    @Evil
    @Test
    void objLongCns_to_sup() {
        synchronized (consumedStringF) {
            synchronized (consumedLongB) {
                Stream
                    .of(
                        Z.absorb(saveStringFLongB, getString),
                        Z.with(saveStringFLongB).absorb(getString)
                    )
                    .forEach(
                        fusion -> {
                            consumedStringF = "";
                            consumedLongB = 0L;

                            assertEquals(
                                suppliedString,
                                fusion.apply("yo").apply(1L)
                            );
                            assertEquals("yo", consumedStringF);
                            assertEquals(1L, consumedLongB);
                        }
                    );
            }
        }
    }

    @Evil
    @Test
    void objLongCns_to_boolSup() {
        synchronized (consumedStringF) {
            synchronized (consumedLongB) {
                Stream
                    .of(
                        Z.absorb(saveStringFLongB, getBooleanTrue),
                        Z.with(saveStringFLongB).absorb(getBooleanTrue)
                    )
                    .forEach(
                        fusion -> {
                            consumedStringF = "";
                            consumedLongB = 0L;

                            assertEquals(true, fusion.apply("yo").test(1L));
                            assertEquals("yo", consumedStringF);
                            assertEquals(1L, consumedLongB);
                        }
                    );
            }
        }
    }

    @Evil
    @Test
    void objLongCns_to_dblSup() {
        synchronized (consumedStringF) {
            synchronized (consumedLongB) {
                Stream
                    .of(
                        Z.absorb(saveStringFLongB, getDouble),
                        Z.with(saveStringFLongB).absorb(getDouble)
                    )
                    .forEach(
                        fusion -> {
                            consumedStringF = "";
                            consumedLongB = 0L;

                            assertEquals(
                                suppliedDouble,
                                fusion.apply("yo").applyAsDouble(1L)
                            );
                            assertEquals("yo", consumedStringF);
                            assertEquals(1L, consumedLongB);
                        }
                    );
            }
        }
    }

    @Evil
    @Test
    void objLongCns_to_intSup() {
        synchronized (consumedStringF) {
            synchronized (consumedLongB) {
                Stream
                    .of(
                        Z.absorb(saveStringFLongB, getInt),
                        Z.with(saveStringFLongB).absorb(getInt)
                    )
                    .forEach(
                        fusion -> {
                            consumedStringF = "";
                            consumedLongB = 0L;

                            assertEquals(
                                suppliedInt,
                                fusion.apply("yo").applyAsInt(1L)
                            );
                            assertEquals("yo", consumedStringF);
                            assertEquals(1L, consumedLongB);
                        }
                    );
            }
        }
    }

    @Evil
    @Test
    void objLongCns_to_longSup() {
        synchronized (consumedStringF) {
            synchronized (consumedLongB) {
                Stream
                    .of(
                        Z.absorb(saveStringFLongB, getLong),
                        Z.with(saveStringFLongB).absorb(getLong)
                    )
                    .forEach(
                        fusion -> {
                            consumedStringF = "";
                            consumedLongB = 0L;

                            assertEquals(
                                suppliedLong,
                                fusion.apply("yo").applyAsLong(1L)
                            );
                            assertEquals("yo", consumedStringF);
                            assertEquals(1L, consumedLongB);
                        }
                    );
            }
        }
    }

    @Evil
    @Test
    void objLongCns_to_op() {
        synchronized (consumedStringF) {
            synchronized (consumedLongB) {
                synchronized (wasOperated) {
                    Stream
                        .of(
                            Z.absorb(saveStringFLongB, doOperation),
                            Z.with(saveStringFLongB).absorb(doOperation),
                            Z
                                .with(saveStringFLongB)
                                .absorbing(doOperation)
                                .resolve()
                        )
                        .forEach(
                            fusion -> {
                                consumedStringF = "";
                                consumedLongB = 0L;
                                wasOperated = false;

                                fusion.apply("yo").accept(1L);

                                assertEquals("yo", consumedStringF);
                                assertEquals(1L, consumedLongB);
                                assertTrue(wasOperated);
                            }
                        );
                }
            }
        }
    }

    @Evil
    @Test
    void op_to_sup() {
        synchronized (wasOperated) {
            Stream
                .of(
                    Z.absorb(doOperation, getString),
                    Z.with(doOperation).absorb(getString),
                    Z.with(doOperation).absorbing(getString).resolve()
                )
                .forEach(
                    fusion -> {
                        wasOperated = false;

                        assertEquals(suppliedString, fusion.get());
                        assertTrue(wasOperated);
                    }
                );
        }
    }

    @Evil
    @Test
    void op_to_boolSup() {
        synchronized (wasOperated) {
            Stream
                .of(
                    Z.absorb(doOperation, getBooleanTrue),
                    Z.with(doOperation).absorb(getBooleanTrue),
                    Z.with(doOperation).absorbing(getBooleanTrue).resolve()
                )
                .forEach(
                    fusion -> {
                        wasOperated = false;

                        assertEquals(true, fusion.getAsBoolean());
                        assertTrue(wasOperated);
                    }
                );
        }
    }

    @Evil
    @Test
    void op_to_dblSup() {
        synchronized (wasOperated) {
            Stream
                .of(
                    Z.absorb(doOperation, getDouble),
                    Z.with(doOperation).absorb(getDouble),
                    Z.with(doOperation).absorbing(getDouble).resolve()
                )
                .forEach(
                    fusion -> {
                        wasOperated = false;

                        assertEquals(suppliedDouble, fusion.getAsDouble());
                        assertTrue(wasOperated);
                    }
                );
        }
    }

    @Evil
    @Test
    void op_to_intSup() {
        synchronized (wasOperated) {
            Stream
                .of(
                    Z.absorb(doOperation, getInt),
                    Z.with(doOperation).absorb(getInt),
                    Z.with(doOperation).absorbing(getInt).resolve()
                )
                .forEach(
                    fusion -> {
                        wasOperated = false;

                        assertEquals(suppliedInt, fusion.getAsInt());
                        assertTrue(wasOperated);
                    }
                );
        }
    }

    @Evil
    @Test
    void op_to_longSup() {
        synchronized (wasOperated) {
            Stream
                .of(
                    Z.absorb(doOperation, getLong),
                    Z.with(doOperation).absorb(getLong),
                    Z.with(doOperation).absorbing(getLong).resolve()
                )
                .forEach(
                    fusion -> {
                        wasOperated = false;

                        assertEquals(suppliedLong, fusion.getAsLong());
                        assertTrue(wasOperated);
                    }
                );
        }
    }

    @Evil
    @Test
    void op_to_op() {
        synchronized (wasOperated) {
            Stream
                .of(
                    Z.absorb(doOperation, doOperation),
                    Z.with(doOperation).absorb(doOperation),
                    Z.with(doOperation).absorbing(doOperation).resolve()
                )
                .forEach(
                    fusion -> {
                        wasOperated = false;

                        fusion.run();

                        assertTrue(wasOperated);
                    }
                );
        }
    }
}
