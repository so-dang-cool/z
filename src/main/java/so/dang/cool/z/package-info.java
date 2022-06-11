/**
 * The Z fusion library.
 *
 * <h1>Fusion</h1>
 *
 * Fusion combines two functional interfaces into one. It is the heart and soul of
 * Z techniques.
 * <br><br>
 * Standard usage is {@code Z.fuse(initial).fuse(next)} where both {@code initial} and
 * {@code next} are functional interfaces.
 * <br><br>
 * The technique used is like the {@code compose} and {@code andThen} functions
 * available from Java's {@code java.util.function} package, but generalizes to
 * support arbitrary combinations.
 * <br><br>
 * Fusion supports the combination of any result-providing function with any
 * argument-accepting function.
 * <br><br>
 * For example, you can combine a {@code Function<A, B>} with a {@code Function<B, C>}
 * to get a {@code Function<A, C>}.
 * <br><br>
 * <pre>
 * {@code
 * // === Plain Java ===
 *
 * // Capturing as a functional interface is necessary to expose composition methods.
 * Function<String, IntStream> chars = String::chars;
 * Function<IntStream, Integer> sum = IntStream::sum;
 *
 * // Composition supports only Functions. E.g. it's not possible to optimize here as
 * // a composition over ToIntFunction<String>. Lambda indirection could fake it, but
 * // autoboxing/unboxing would still occur.
 * Function<String, Integer> asciiSum = sum.compose(chars);
 *
 * // === Z ===
 *
 * // Z handles composition a little more succinctly.
 * ToIntFunction<String> asciiSum = Z.fuse(String::chars).fuse(IntStream::sum);
 * }
 * </pre>
 * <br><br>
 * Usage with overloaded methods may still require more specification or lambdas.
 *
 * <h1>More complex combinations</h1>
 *
 * If you want to fuse more than two functional interfaces, you may be interested in
 * SuperFusion combinators.
 * <br><br>
 * If you want to make more evil combinations, (E.g. combining a {@code Consumer<A>}
 * and a {@code Supplier<B>} into a {@code Function<A, B>}) you may be interested in
 * Absorption combinators or evil SuperFusion combinators.
 *
 * <h1>Super Fusion</h1>
 *
 * Super Fusion allows the combination of any number of functional interfaces.
 *
 * TODO: Explain.
 *
 * <h1>Fission</h1>
 *
 * Fission dissolves functional interfaces into a curried form.
 *
 * TODO: Explain.
 *
 * <h1>Assimilation (Evil)</h1>
 *
 * Assimilation combines curried functions into many-argument functional interfaces.
 *
 * TODO: Explain
 *
 * <h1>Absorption (Evil)</h1>
 *
 * Absorption forcefully combines functional interfaces that share no direct relation.
 *
 * TODO: Explain
 */
package so.dang.cool.z;
