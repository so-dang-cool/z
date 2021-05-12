package so.dang.cool.z.continuation;

import org.junit.jupiter.api.Test;

import so.dang.cool.z.Z;
import so.dang.cool.z.continuation.FromFn;

public class FromFnTest {
    @Test
    public void can_it_work() {
        FromFn<String, FromFn<Integer, Integer>> lengther = Z.fuse((Integer i) -> i + 2, (Integer i) -> i.toString());
    }
}
