package so.dang.cool.z;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

public class ZTests {
    @Test
    public void simple_regex_pipeline_example() {
        final String urlRegex = "https?://localhost(:\\d+)?(/\\S*)?";
        Pattern pattern = Pattern.compile(urlRegex);

        Predicate<CharSequence> validator1 = s -> pattern.matcher(s).matches();

        Predicate<CharSequence> validator2 = Z.fuse(pattern::matcher, Matcher::matches);

        Predicate<CharSequence> validator3 = Z.with(urlRegex)
            .fusingFn(Pattern::compile)
            .fusing(Pattern::matcher)
            .fuse(Matcher::matches);

        List<String> invalidCases = List.of("invalid", "http://invalid/localhost");
        List<String> validCases = List.of("http://localhost", "https://localhost:443/");

        Stream.of(validator1, validator2, validator3)
            .peek(v -> invalidCases.forEach(c -> assertFalse(() -> v.test(c))))
            .forEach(v -> validCases.forEach(c -> assertTrue(() -> v.test(c))));
    }
}
