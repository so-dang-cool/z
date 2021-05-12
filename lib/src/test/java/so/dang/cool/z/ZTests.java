package so.dang.cool.z;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

public class ZTests {
    @Test
    public void simple_regex_pipeline_example() {
        final String urlRegex = "https?://localhost(:\\d+)?(/\\S*)?";
        Pattern pattern = Pattern.compile(urlRegex);

        Predicate<CharSequence> predicate = Z.fuse(pattern::matcher, Matcher::matches);

        assertFalse(predicate.test("invalid"));
        assertTrue(predicate.test("https://localhost:443"));
    }

    @Test
    public void complex_regex_pipeline_example() {
        Predicate<CharSequence> predicate = Z.with("https?://localhost(:\\d+)?(/\\S*)?")
            .fusingFn(Pattern::compile)
            .fusing(Pattern::matcher)
            .fuse(Matcher::matches);

        assertFalse(predicate.test("invalid"));
        assertTrue(predicate.test("https://localhost:443"));
    }
}
