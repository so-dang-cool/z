/**
 * The Z fusion library.
 *
 * <h1>Fusion</h1>
 *
 * Fusion combines two functional interfaces into one. It is the heart and soul of Z techniques.
 *
 * Access through `Z.fuse(initial, next)` where both `initial` and `next` are
 * functional interfaces.
 *
 * The technique used is like the `compose` and `andThen` static functions
 * available from Java's `java.util.function` package, but generalizes to
 * support arbitrary combinations.
 *
 * Fusion supports the combination of any result-providing function with any
 * argument-accepting function.
 *
 * For example, you can combine a Function<A, B> with a Function<B, C> to get a
 * Function<A, C>.
 *
 * <pre>
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
 * ToIntFunction<String> asciiSum = Z.fuse(String::chars, IntStream::sum);
 * </pre>
 *
 * Usage with overloaded methods may still require more specification or lambdas.
 *
 * <h1>More complex combinations</h1>
 *
 * If you want to fuse more than two functional interfaces, you may be interested in
 * SuperFusion continuations.
 *
 * If you want to make more evil combinations, (E.g. Consumer<A> + Supplier<B>)
 * you may be interested in Absorption combinations or evil SuperFusion continuations.
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
