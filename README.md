# Z

Z is a Java library providing accessible, consistent function combinators.

## Z compared to `java.util.function`

Z

```java
ToIntFunction<String> asciiSum = Z.fuse(String::chars, IntStream::sum);
```

Plain Java

```java
// Capturing as a functional interface is necessary to expose composition methods.
Function<String, IntStream> chars = String::chars;
Function<IntStream, Integer> sum = IntStream::sum;

// Composition supports only Functions. E.g. it's not possible to optimize here as
// a composition over ToIntFunction<String>. Lambda indirection could fake it, but
// autoboxing/unboxing would still occur.
Function<String, Integer> asciiSum = sum.compose(chars);
```

## A more complex example

Z can perform sophisticated combinations that include static functions, instance
methods, selecting among (some kinds of) overloaded functions, and more.

```
Predicate<CharSequence> predicate = Z.with("https?://localhost(:\\d+)?(/\\S*)?")
    .fusingFn(Pattern::compile)
    .fusing(Pattern::matcher)
    .fuse(Matcher::matches);

predicate.test("https://localhost:443");
```

The above examples and more can be found in [Usage Examples](https://github.com/hiljusti/z/blob/HEAD/src/test/java/so/dang/cool/z/UsageExamples.java)

## Z is small

Z only focuses on function manipulation. Z brings in zero other dependencies.

If you're looking for something with more functional-programming features, like
persistent collections, tuples, and monads, [Vavr](https://www.vavr.io/) may be
worth checking out. Z is not related to the Vavr project.

## Details

- [Get Z from Maven Central](https://search.maven.org/artifact/so.dang.cool/z/)
- [Read Docs](https://www.javadoc.io/doc/so.dang.cool/z/latest/so/dang/cool/z/package-summary.html)

Have fun!
