package so.dang.cool.z.misc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import so.dang.cool.z.Z;

public class MiscellaneousTests {
    @Test
    void id_test() {
        assertEquals("1", Z.id(String.class).apply("1"));
    }
}
