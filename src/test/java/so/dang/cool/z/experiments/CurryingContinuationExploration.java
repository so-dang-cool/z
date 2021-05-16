package so.dang.cool.z.experiments;

import org.junit.jupiter.api.Test;

public class CurryingContinuationExploration {
    @Test
    public void string_builder_example() {
        // TODO: Make this work. P.S. is this evil?

        // BiFunction<String, String, String> append = (String base, String suffix) -> base.concat(suffix);

        // var fourPartStringBuilder = Z.with(String.class)
        //     .fusing(append)
        //     .fusing(append)
        //     .fusing(append)
        //     .fuse(append);

        // assertEquals("hello world!", fourPartStringBuilder.accept("hello").apply(" ").apply("world").apply("!"));
    }
}
