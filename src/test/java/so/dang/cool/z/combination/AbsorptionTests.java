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
                .of(saveStringA, Z.fuse(saveStringA))
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
            Stream
                .of(saveIntA, Z.fuse(saveIntA))
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
                .of(saveLongA, Z.fuse(saveLongA))
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
                    Z.fuse(saveStringA).absorb(trim),
                    Z.fuse(saveStringA).absorb(trim),
                    Z.fuse(saveStringA).absorbing(trim)
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
                    Z.fuse(saveStringA).absorb(getString),
                    Z.fuse(saveStringA).absorb(getString),
                    Z.fuse(saveStringA).absorbing(getString)
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
                    Z.fuse(saveStringA).absorb(getBooleanTrue),
                    Z.fuse(saveStringA).absorb(getBooleanTrue),
                    Z.fuse(saveStringA).absorbing(getBooleanTrue)
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
                    Z.fuse(saveStringA).absorb(getDouble),
                    Z.fuse(saveStringA).absorb(getDouble),
                    Z.fuse(saveStringA).absorbing(getDouble)
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
                    Z.fuse(saveStringA).absorb(getInt),
                    Z.fuse(saveStringA).absorb(getInt),
                    Z.fuse(saveStringA).absorbing(getInt)
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
                    Z.fuse(saveStringA).absorb(getLong),
                    Z.fuse(saveStringA).absorb(getLong),
                    Z.fuse(saveStringA).absorbing(getLong)
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
                        Z.fuse(saveStringA).absorb(doOperation),
                        Z.fuse(saveStringA).absorb(doOperation),
                        Z.fuse(saveStringA).absorbing(doOperation)
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
                        .absorbing(getString)
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

                /* deep */

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

                /* deeper */

                consumedStringB = "";
                consumedStringC = "";

                assertEquals(
                    true,
                    Z
                        .fuse(saveStringsBandC)
                        .absorbing(getBooleanTrue)
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
                        .fuse(saveStringsBandC)
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
                        .fuse(saveStringsBandC)
                        .absorbing(getDouble)
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
                        .fuse(saveStringsBandC)
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
                        .fuse(saveStringsBandC)
                        .absorbing(getInt)
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
                        .fuse(saveStringsBandC)
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
                        .fuse(saveStringsBandC)
                        .absorbing(getLong)
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

                    /* deep */

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

                    /* deeper */

                    consumedStringB = "";
                    consumedStringC = "";
                    wasOperated = false;

                    Z
                        .fuse(saveStringsBandC)
                        .absorbing(doOperation)
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
                    Z.fuse(saveBooleanA).absorb(getString),
                    Z.fuse(saveBooleanA).absorb(getString),
                    Z.fuse(saveBooleanA).absorbing(getString)
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
                    Z.fuse(saveBooleanA).absorb(getBooleanTrue),
                    Z.fuse(saveBooleanA).absorb(getBooleanTrue),
                    Z.fuse(saveBooleanA).absorbing(getBooleanTrue)
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
                    Z.fuse(saveBooleanA).absorb(getDouble),
                    Z.fuse(saveBooleanA).absorb(getDouble),
                    Z.fuse(saveBooleanA).absorbing(getDouble)
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
                    Z.fuse(saveBooleanA).absorb(getInt),
                    Z.fuse(saveBooleanA).absorb(getInt),
                    Z.fuse(saveBooleanA).absorbing(getInt)
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
                    Z.fuse(saveBooleanA).absorb(getLong),
                    Z.fuse(saveBooleanA).absorb(getLong),
                    Z.fuse(saveBooleanA).absorbing(getLong)
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
                        Z.fuse(saveBooleanA).absorb(doOperation),
                        Z.fuse(saveBooleanA).absorb(doOperation),
                        Z.fuse(saveBooleanA).absorbing(doOperation)
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
                    Z.fuse(saveDoubleA).absorb(getString),
                    Z.fuse(saveDoubleA).absorb(getString),
                    Z.fuse(saveDoubleA).absorbing(getString)
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
                    Z.fuse(saveDoubleA).absorb(getBooleanTrue),
                    Z.fuse(saveDoubleA).absorb(getBooleanTrue),
                    Z.fuse(saveDoubleA).absorbing(getBooleanTrue)
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
                    Z.fuse(saveDoubleA).absorb(getDouble),
                    Z.fuse(saveDoubleA).absorb(getDouble),
                    Z.fuse(saveDoubleA).absorbing(getDouble)
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
                    Z.fuse(saveDoubleA).absorb(getInt),
                    Z.fuse(saveDoubleA).absorb(getInt),
                    Z.fuse(saveDoubleA).absorbing(getInt)
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
                    Z.fuse(saveDoubleA).absorb(getLong),
                    Z.fuse(saveDoubleA).absorb(getLong),
                    Z.fuse(saveDoubleA).absorbing(getLong)
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
                        Z.fuse(saveDoubleA).absorb(doOperation),
                        Z.fuse(saveDoubleA).absorb(doOperation),
                        Z.fuse(saveDoubleA).absorbing(doOperation)
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
                        Z.fuse(saveStringDDoubleB).absorb(getString),
                        Z.fuse(saveStringDDoubleB).absorb(getString)
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
                        Z.fuse(saveStringDDoubleB).absorb(getBooleanTrue),
                        Z.fuse(saveStringDDoubleB).absorb(getBooleanTrue)
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
                        Z.fuse(saveStringDDoubleB).absorb(getDouble),
                        Z.fuse(saveStringDDoubleB).absorb(getDouble)
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
                        Z.fuse(saveStringDDoubleB).absorb(getInt),
                        Z.fuse(saveStringDDoubleB).absorb(getInt)
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
                        Z.fuse(saveStringDDoubleB).absorb(getLong),
                        Z.fuse(saveStringDDoubleB).absorb(getLong)
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
                            Z.fuse(saveStringDDoubleB).absorb(doOperation),
                            Z.fuse(saveStringDDoubleB).absorb(doOperation),
                            Z.fuse(saveStringDDoubleB).absorbing(doOperation)
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
                    Z.fuse(saveIntA).absorb(getString),
                    Z.fuse(saveIntA).absorb(getString),
                    Z.fuse(saveIntA).absorbing(getString)
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
                    Z.fuse(saveIntA).absorb(getBooleanTrue),
                    Z.fuse(saveIntA).absorb(getBooleanTrue),
                    Z.fuse(saveIntA).absorbing(getBooleanTrue)
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
                    Z.fuse(saveIntA).absorb(getDouble),
                    Z.fuse(saveIntA).absorb(getDouble),
                    Z.fuse(saveIntA).absorbing(getDouble)
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
                    Z.fuse(saveIntA).absorb(getInt),
                    Z.fuse(saveIntA).absorb(getInt),
                    Z.fuse(saveIntA).absorbing(getInt)
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
                    Z.fuse(saveIntA).absorb(getLong),
                    Z.fuse(saveIntA).absorb(getLong),
                    Z.fuse(saveIntA).absorbing(getLong)
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
                        Z.fuse(saveIntA).absorb(doOperation),
                        Z.fuse(saveIntA).absorb(doOperation),
                        Z.fuse(saveIntA).absorbing(doOperation)
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
                        Z.fuse(saveStringEIntB).absorb(getString),
                        Z.fuse(saveStringEIntB).absorb(getString)
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
                        Z.fuse(saveStringEIntB).absorb(getBooleanTrue),
                        Z.fuse(saveStringEIntB).absorb(getBooleanTrue)
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
                        Z.fuse(saveStringEIntB).absorb(getDouble),
                        Z.fuse(saveStringEIntB).absorb(getDouble)
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
                        Z.fuse(saveStringEIntB).absorb(getInt),
                        Z.fuse(saveStringEIntB).absorb(getInt)
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
                        Z.fuse(saveStringEIntB).absorb(getLong),
                        Z.fuse(saveStringEIntB).absorb(getLong)
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
                            Z.fuse(saveStringEIntB).absorb(doOperation),
                            Z.fuse(saveStringEIntB).absorb(doOperation),
                            Z.fuse(saveStringEIntB).absorbing(doOperation)
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
                    Z.fuse(saveLongA).absorb(getString),
                    Z.fuse(saveLongA).absorb(getString),
                    Z.fuse(saveLongA).absorbing(getString)
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
                    Z.fuse(saveLongA).absorb(getBooleanTrue),
                    Z.fuse(saveLongA).absorb(getBooleanTrue),
                    Z.fuse(saveLongA).absorbing(getBooleanTrue)
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
                    Z.fuse(saveLongA).absorb(getDouble),
                    Z.fuse(saveLongA).absorb(getDouble),
                    Z.fuse(saveLongA).absorbing(getDouble)
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
                    Z.fuse(saveLongA).absorb(getInt),
                    Z.fuse(saveLongA).absorb(getInt),
                    Z.fuse(saveLongA).absorbing(getInt)
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
                    Z.fuse(saveLongA).absorb(getLong),
                    Z.fuse(saveLongA).absorb(getLong),
                    Z.fuse(saveLongA).absorbing(getLong)
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
                        Z.fuse(saveLongA).absorb(doOperation),
                        Z.fuse(saveLongA).absorb(doOperation),
                        Z.fuse(saveLongA).absorbing(doOperation)
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
                        Z.fuse(saveStringFLongB).absorb(getString),
                        Z.fuse(saveStringFLongB).absorb(getString)
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
                        Z.fuse(saveStringFLongB).absorb(getBooleanTrue),
                        Z.fuse(saveStringFLongB).absorb(getBooleanTrue)
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
                        Z.fuse(saveStringFLongB).absorb(getDouble),
                        Z.fuse(saveStringFLongB).absorb(getDouble)
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
                        Z.fuse(saveStringFLongB).absorb(getInt),
                        Z.fuse(saveStringFLongB).absorb(getInt)
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
                        Z.fuse(saveStringFLongB).absorb(getLong),
                        Z.fuse(saveStringFLongB).absorb(getLong)
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
                            Z.fuse(saveStringFLongB).absorb(doOperation),
                            Z.fuse(saveStringFLongB).absorb(doOperation),
                            Z.fuse(saveStringFLongB).absorbing(doOperation)
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
                    Z.fuse(doOperation).absorb(getString),
                    Z.fuse(doOperation).absorb(getString),
                    Z.fuse(doOperation).absorbing(getString)
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
                    Z.fuse(doOperation).absorb(getBooleanTrue),
                    Z.fuse(doOperation).absorb(getBooleanTrue),
                    Z.fuse(doOperation).absorbing(getBooleanTrue)
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
                    Z.fuse(doOperation).absorb(getDouble),
                    Z.fuse(doOperation).absorb(getDouble),
                    Z.fuse(doOperation).absorbing(getDouble)
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
                    Z.fuse(doOperation).absorb(getInt),
                    Z.fuse(doOperation).absorb(getInt),
                    Z.fuse(doOperation).absorbing(getInt)
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
                    Z.fuse(doOperation).absorb(getLong),
                    Z.fuse(doOperation).absorb(getLong),
                    Z.fuse(doOperation).absorbing(getLong)
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
                    Z.fuse(doOperation).absorb(doOperation),
                    Z.fuse(doOperation).absorb(doOperation),
                    Z.fuse(doOperation).absorbing(doOperation)
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
