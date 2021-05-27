package so.dang.cool.z.function;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static so.dang.cool.z.combination.TestFunctions.*;

import org.junit.jupiter.api.Test;
import so.dang.cool.z.annotation.Evil;

public class BooleanConsumerTests {

    @Evil
    @Test
    public void andThen() {
        synchronized (consumedBooleanA) {
            synchronized (consumedBooleanF) {
                consumedBooleanA = false;
                consumedBooleanF = false;

                saveBooleanA.andThen(saveBooleanF).accept(true);

                assertTrue(consumedBooleanA);
                assertTrue(consumedBooleanF);
            }
        }
    }

    @Evil
    @Test
    public void andThen_false() {
        synchronized (consumedBooleanA) {
            synchronized (consumedBooleanF) {
                consumedBooleanA = true;
                consumedBooleanF = true;

                saveBooleanA.andThen(saveBooleanF).accept(false);

                assertFalse(consumedBooleanA);
                assertFalse(consumedBooleanF);
            }
        }
    }
}
