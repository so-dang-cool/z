# Contributing

Pull requests welcomed!

Please run any nontrivial contribution ideas by the project maintainer, [hiljusti](mailto:hiljusti@pm.me), before sending a PR.

## Evil

Some code may need to be evil, and if so, should be marked with `@Evil`.

This is an example of a neutral combination:

```
Function<A, B> + Function <B, C> = Function<A, C>
```

Assuming both functions are pure, it has a clear flow and nothing is hidden.

This, on the other hand, is an example of an _**evil**_ combination:

```
Consumer<A> + Supplier<B> = Function<A, B>
```

There is no direct relation between the void return of the Consumer and the no-args invocation of the Supplier. This can usually be understood as a code smell. Something is being done in that Consumer (a network call, something in a thread, a mutating action) that may now be hidden from the end caller.

Evil functions or collections of functions can be a sign of either

1. Evil code is being written, or
1. Code is interacting with something evil

## Ordering

Because alphabetic ordering may be difficult to grok, the project follows the
following ordering. Classes may be omitted when there is no benefit, or a
different class also matches, and there's no benefit to specificity.

### Function family

1. Function
1. BiFunction
1. DoubleFunction
1. DoubleToIntFunction
1. DoubleToLongFunction
1. ToDoubleFunction
1. ToDoubleBiFunction
1. IntFunction
1. IntToDoubleFunction
1. IntToLongFunction
1. ToIntFunction
1. ToIntBiFunction
1. LongFunction
1. LongToDoubleFunction
1. LongToIntFunction
1. ToLongFunction
1. ToLongBiFunction

### Predicate family

1. Predicate
1. BiPredicate
1. DoublePredicate
1. IntPredicate
1. LongPredicate

# Consumer family

1. Consumer
1. BiConsumer
1. DoubleConsumer
1. ObjDoubleConsumer
1. IntConsumer
1. ObjIntConsumer
1. LongConsumer
1. ObjLongConsumer

# Supplier Family

1. Supplier
1. BooleanSupplier
1. DoubleSupplier
1. IntSupplier
1. LongSupplier

# Operator Family

1. Operator (Z only)
1. UnaryOperator
1. BinaryOperator
1. DoubleUnaryOperator
1. DoubleBinaryOperator
1. IntUnaryOperator
1. IntBinaryOperator
1. LongUnaryOperator
1. LongBinaryOperator
