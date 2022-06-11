<img align="left" src="https://github.com/hiljusti/z/raw/core/img/logo.png">

**Fearless function combination in Java**

[![Maven Central](https://img.shields.io/maven-central/v/so.dang.cool/z.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22so.dang.cool%22%20AND%20a:%22z%22)
[![Javadoc](https://javadoc.io/badge2/so.dang.cool/z/javadoc.svg)](https://javadoc.io/doc/so.dang.cool/z)
![License](https://img.shields.io/github/license/hiljusti/z)

![Lines of code](https://img.shields.io/tokei/lines/github/hiljusti/z)
[![codecov](https://codecov.io/gh/hiljusti/z/branch/core/graph/badge.svg?token=CF80PDSXUB)](https://codecov.io/gh/hiljusti/z)
![GitHub repo size](https://img.shields.io/github/repo-size/hiljusti/z)
![jar size](https://img.shields.io/badge/jar_size-243_kB-blue)
![dependency count](https://img.shields.io/badge/dependencies-0-blue)

# Techniques

Unlock your functional programming potential with these combination techniques:

## Fusion

`Z.fuse(fn1, fn2)` Combine two functions.

```java
var internedTrim = Z.fuse(String::trim).fuse(String::intern);

assertEquals("hello", internedTrim.apply(" hello "));

// Protip: Interned strings can use == directly.
assertTrue("hello" == internedTrim.apply(" hello "));
```

## Fission

`Z.split(fn)` Split a multiargs function into a curried form.

```java
var concat = Z.split(String::concat);

assertEquals("hotpot", concat.apply("hot").apply("pot"));

// Protip: "Curried" functions can be partially applied.
var preSomething = concat.apply("pre");

assertEquals("prefix", preSomething.apply("fix"));
assertEquals("presume", preSomething.apply("sume"));

// Protip: Z also has a "flip" function to change order.
var fixedSomething = Z.flip(concat).apply("fix");

assertEquals("prefix", fixedSomething.apply("pre"));
assertEquals("suffix", fixedSomething.apply("suf"));
```

## Assimilation

`Z.assimilate[N](curriedFn)` Flatten a curried function into a multiargs function.

```java
var checkoutMessage = Z.assimilate2(
    (String item) ->
        (String name) -> String.format("Enjoy your %s, %s!", item, name)
);

assertEquals(
    "Enjoy your bike, Alice!",
    checkoutMessage.apply("bike", "Alice")
);
```

## Absorption

`Z.absorb(fn1, fn2)` Unnaturally combine two functions.

_This is an **evil** technique. It's provided as a tool to control evil problems, not to encourage evil code._

```java
var heroes = new ArrayList<>(List.of("joker"));

var emptiedHeroes = Z.fuse(heroes::clear).absorb(() -> heroes);

assertEquals(List.of(), emptiedHeroes.get());

heroes.add("twoface");
emptiedHeroes.get().add("batman");
assertEquals(List.of("batman"), heroes);
```

## Super Fusion

`Z.fuse(var or fn1).fuse(fn2).[...].fuse(fn[N])` Combine _N_ functions.

```java
var isLocalHost = Z.fuse("https?://localhost(:\\d+)?(/\\S*)?")
    .fuseFunction(Pattern::compile)
    .fuse(Pattern::matcher)
    .fuse(Matcher::matches);

assertTrue(isLocalHost.test("https://localhost:443"));
```

_Note: `fuseFunction` above is an explicit choice of a single-arg variant of
`Pattern::compile`. Similar `fuse[Etc]` exist for other functional interfaces._

# Z goals

1. Z only provides function combinators
1. Z will never need other dependencies
1. Z will never rely on reflection or code generation
1. Techniques with the potential to cause problems are annotated as `@Evil`;
    choose your path wisely...

## Comparisons and advantages

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

_More examples can be found in the [Usage Examples](https://github.com/hiljusti/z/blob/HEAD/src/test/java/so/dang/cool/z/UsageExamples.java) in the project's tests._
