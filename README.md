# Z

**Z is a Java library for exciting and fearless function combination.**

[![Maven Central](https://img.shields.io/maven-central/v/so.dang.cool/z.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22so.dang.cool%22%20AND%20a:%22z%22)
[![Javadoc](https://javadoc.io/badge2/so.dang.cool/z/javadoc.svg)](https://javadoc.io/doc/so.dang.cool/z)
![License](https://img.shields.io/github/license/hiljusti/z)

![dependency count](https://img.shields.io/badge/dependencies-0-brightgreen)
[![codecov](https://codecov.io/gh/hiljusti/z/branch/core/graph/badge.svg?token=CF80PDSXUB)](https://codecov.io/gh/hiljusti/z)
![Lines of code](https://img.shields.io/tokei/lines/github/hiljusti/z)
![GitHub repo size](https://img.shields.io/github/repo-size/hiljusti/z)

Unlock your functional programming potential with these combination techniques:

1. Fusion: `Z.fuse(fn1, fn2)` Combine two functions.
1. Fission - `Z.split(fn)` Split a multiargs function into a curried form.
1. Assimilation - `Z.assimilate2(curriedFn)` Flatten a curried function into a
    multiargs function.
1. Absorption - `Z.absorb(fn1, fn2)` Unnaturally combine two functions.
1. Super Fusion - `Z.with(fn1).fusing(fn2).[...].fuse(fnN)` Combine _N_
    functions.
    - **Experimental:** This feature is still in active development, but will
    be stable in future versions.

## Z is lean and principled

1. Z only provides function combinators
1. Z will never need other dependencies
1. Z will never rely on reflection or code generation
1. Techniques with the potential to cause problems are annotated as `@Evil`;
    choose your path wisely...

## Z gives succinct, precise function combination

Z fusion

```java
var asciiSum = Z.fuse(String::chars, IntStream::sum);

int sum = asciiSum.applyAsInt("abc");
```

Equivalent with `Function::compose`

```java
Function<IntStream, Integer> sumInts = IntStream::sum;
var asciiSum = sumInts.compose(String::chars);

// Captures as a Function<String, Integer> (autoboxing/unboxing occurs)
int sum = asciiSum.apply("abc");
```

Equivalent with a lambda

```java
// Inference (e.g. with var) is not possible
ToIntFunction<String> asciiSum = s -> s.chars().sum();

int sum = asciiSum.applyAsInt("abc");
```

Some advantages of Z here:

1. **Tacit yet explicit** - Z allows for [point-free](https://en.wikipedia.org/wiki/Tacit_programming)
    function combination. This means you state your logic as a fact, and don't
    worry as much about the exact syntax for instructions. (Of course, Z can
    accept lambdas!)
1. **Explicit ordering of actions** - Z lets you consistently define actions in
    the order they'll execute.
1. **"Just works" inference** - Z techniques are optimized for a wider variety
    of functional interfaces. It's not necessary to define (or cast) things to
    a `Function<A, B>` in order just to expose `Function::compose`.
1. **Idiomatic functions** - Z doesn't reimplement functional interfaces or
    conventions that already exist. A `Predicate` will have a `test` method, a
    `Consumer` will have an `accept` method, etc.

## Z can handle complex combinations

Z

```
var isLocalHost = Z.with("https?://localhost(:\\d+)?(/\\S*)?")
    .fusingFunction(Pattern::compile)
    .fusing(Pattern::matcher)
    .fuse(Matcher::matches);

isLocalHost.test("https://localhost:443");
```

Plain Java

```
Predicate<String> isLocalHost = (String s) ->
    Pattern.compile("https?://localhost(:\\d+)?(/\\S*)?")
        .matcher(s)
        .matches();

isLocalHost.test("https://localhost:443");
```

Z lets you perform sophisticated combinations with:

* Static functions
* Instance methods
* Selection among many kinds of overloaded functions
* Almost any function you can throw at it

The above examples and more can be found in [Usage Examples](https://github.com/hiljusti/z/blob/HEAD/src/test/java/so/dang/cool/z/UsageExamples.java)
