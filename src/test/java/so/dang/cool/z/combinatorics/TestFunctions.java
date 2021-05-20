package so.dang.cool.z.combinatorics;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleFunction;
import java.util.function.DoublePredicate;
import java.util.function.DoubleSupplier;
import java.util.function.DoubleToIntFunction;
import java.util.function.DoubleToLongFunction;
import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.IntSupplier;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntToLongFunction;
import java.util.function.IntUnaryOperator;
import java.util.function.LongBinaryOperator;
import java.util.function.LongConsumer;
import java.util.function.LongFunction;
import java.util.function.LongPredicate;
import java.util.function.LongSupplier;
import java.util.function.LongToDoubleFunction;
import java.util.function.LongToIntFunction;
import java.util.function.LongUnaryOperator;
import java.util.function.ObjDoubleConsumer;
import java.util.function.ObjIntConsumer;
import java.util.function.ObjLongConsumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToDoubleBiFunction;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntBiFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongBiFunction;
import java.util.function.ToLongFunction;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

import so.dang.cool.z.internal.function.BooleanConsumer;
import so.dang.cool.z.internal.function.BooleanFunction;
import so.dang.cool.z.internal.function.BooleanUnaryOperator;
import so.dang.cool.z.internal.function.DecFunction;
import so.dang.cool.z.internal.function.DodecFunction;
import so.dang.cool.z.internal.function.NonFunction;
import so.dang.cool.z.internal.function.OctFunction;
import so.dang.cool.z.internal.function.Operator;
import so.dang.cool.z.internal.function.QuadFunction;
import so.dang.cool.z.internal.function.QuinFunction;
import so.dang.cool.z.internal.function.SeptFunction;
import so.dang.cool.z.internal.function.SexFunction;
import so.dang.cool.z.internal.function.TriFunction;
import so.dang.cool.z.internal.function.UndecFunction;

/**
 * Testing functions. For simplicity of combinations, every generic uses the
 * same type (String) -- primitives, of course, must remain primitives.
 */
public final class TestFunctions {
    static final String suppliedString = "Z";
    static final double suppliedDouble = 1.0;
    static final int suppliedInt = 2;
    static final long suppliedLong = 3L;

    static String consumedStringA = "";
    static String consumedStringB = "";
    static String consumedStringC = "";
    static String consumedStringD = "";
    static String consumedStringE = "";
    static String consumedStringF = "";
    static String consumedStringG = "";
    static Double consumedDoubleA = 0.0;
    static Double consumedDoubleB = 0.0;
    static Double consumedDoubleC = 0.0;
    static Integer consumedIntA = 0;
    static Integer consumedIntB = 0;
    static Integer consumedIntC = 0;
    static Long consumedLongA = 0L;
    static Long consumedLongB = 0L;
    static Long consumedLongC = 0L;
    static Boolean consumedBooleanA = false;
    static Boolean consumedBooleanB = false;
    static Boolean consumedBooleanC = false;
    static Boolean consumedBooleanD = false;
    static Boolean consumedBooleanE = false;
    static Boolean wasOperated = false;

    TestFunctions() {}

    static Function<String, String> toLower = String::toLowerCase;
    static Function<String, String> trim = String::trim;
    static Function<String, String> addExclamationMark = s -> s.concat("!");
    static BiFunction<String, String, String> concat = String::concat;
    static BiFunction<Boolean, String, String> maybeToUpper = (b, s) -> b ? s.toUpperCase() : s;
    static BooleanFunction<String> booleanToString = String::valueOf;
    static DoubleFunction<String> doubleToString = String::valueOf;
    static DoubleToIntFunction doubleToInt = d -> (int) d;
    static DoubleToLongFunction doubleToLong = d -> (long) d;
    static ToDoubleFunction<String> stringToDouble = Double::parseDouble;
    static ToDoubleFunction<Boolean> maybeOneAsDouble = b -> b ? 1.0 : 0.0;
    static ToDoubleBiFunction<String, String> addStringsAsDouble = (a, b) -> Double.parseDouble(a) + Double.parseDouble(b);
    static ToDoubleBiFunction<Boolean, String> maybeAddOneToStringAsDouble = (b, s) -> Double.parseDouble(s) + (b ? 1.0 : 0.0);
    static IntFunction<String> intToString = String::valueOf;
    static IntToDoubleFunction intToDouble = i -> (double) i;
    static IntToLongFunction intToLong = i -> (long) i;
    static ToIntFunction<String> stringToInt = Integer::parseInt;
    static ToIntFunction<Boolean> maybeTwoAsInt = b -> b ? 2 : 0;
    static ToIntBiFunction<String, String> addStringsAsInt = (a, b) -> Integer.parseInt(a) + Integer.parseInt(b);
    static ToIntBiFunction<Boolean, String> maybeAddTwoToStringAsInt = (b, s) -> Integer.parseInt(s) + (b ? 2 : 0);
    static LongFunction<String> longToString = String::valueOf;
    static LongToDoubleFunction longToDouble = n -> (double) n;
    static LongToIntFunction longToInt = n -> (int) n;
    static ToLongFunction<String> stringToLong = Long::parseLong;
    static ToLongFunction<Boolean> maybeThreeAsLong = b -> b ? 3L : 0;
    static ToLongBiFunction<String, String> addStringsAsLong = (a, b) -> Long.parseLong(a) + Long.parseLong(b);
    static ToLongBiFunction<Boolean, String> maybeAddThreeToStringAsLong = (b, s) -> Long.parseLong(s) + (b ? 3L : 0);
    static Predicate<String> isEmpty = String::isEmpty;
    static Predicate<Boolean> not = b -> !b;
    static BiPredicate<String, String> startsWith = String::startsWith;
    static BiPredicate<Boolean, String> maybeNotFromString = (b, s) -> { boolean b2 = Boolean.parseBoolean(s); return b ? !b2 : b2; };
    static DoublePredicate isDoubleOne = d -> Math.abs(d - 1.0) < Math.ulp(d);
    static IntPredicate isIntTwo = i -> i == 2;
    static LongPredicate isLongThree = n -> n == 3L;
    static Consumer<String> saveStringA = a -> consumedStringA = a;
    static BiConsumer<String, String> saveStringsBandC = (b, c) -> { consumedStringB = b; consumedStringC = c; };
    static BiConsumer<Boolean, String> saveBooleanBAndStringG = (b, g) -> { consumedBooleanB = b; consumedStringG = g; };
    static BooleanConsumer saveBooleanA = b -> consumedBooleanA = b;
    static DoubleConsumer saveDoubleA = a -> consumedDoubleA = a;
    static ObjDoubleConsumer<String> saveStringDDoubleB = (d, b) -> { consumedStringD = d; consumedDoubleB = b; };
    static ObjDoubleConsumer<Boolean> saveBooleanCDoubleC = (b, d) -> { consumedBooleanC = b; consumedDoubleC = d; };
    static IntConsumer saveIntA = a -> consumedIntA = a;
    static ObjIntConsumer<String> saveStringEIntB = (e, b) -> { consumedStringE = e; consumedIntB = b; };
    static ObjIntConsumer<Boolean> saveBooleanDIntC = (d, c) -> { consumedBooleanD = d; consumedIntC = c; };
    static LongConsumer saveLongA = a -> consumedLongA = a;
    static ObjLongConsumer<String> saveStringFLongB = (f, b) -> { consumedStringF = f; consumedLongB = b; };
    static ObjLongConsumer<Boolean> saveBooleanELongC = (e, c) -> { consumedBooleanE = e; consumedLongC = c; };
    static Supplier<String> getString = () -> suppliedString;
    static BooleanSupplier getBooleanTrue = () -> true;
    static DoubleSupplier getDouble = () -> suppliedDouble;
    static IntSupplier getInt = () -> suppliedInt;
    static LongSupplier getLong = () -> suppliedLong;
    static Operator doOperation = () -> wasOperated = true;
    static UnaryOperator<String> addQuestionMark = s -> s.concat("?");
    static BinaryOperator<String> relation = (a, b) -> a.equalsIgnoreCase(b) ? "same-ish" : "different";
    static BinaryOperator<Boolean> maybeNot = (a, b) -> a ? !b : b;
    static BooleanUnaryOperator booleanId = b -> b;
    static DoubleUnaryOperator addOneToDouble = d -> d + 1.0;
    static DoubleBinaryOperator addDoubles = (d1, d2) -> d1 + d2;
    static IntUnaryOperator addTwoToInt = i -> i + 2;
    static IntBinaryOperator addInts = (i1, i2) -> i1 + i2;
    static LongUnaryOperator addThreeToLong = n -> n + 3L;
    static LongBinaryOperator addLongs = (n1, n2) -> n1 + n2;
    static BiFunction<String, String, String>
        concat2 = (a, b) ->
            concatVariableStrings(a, b);
    static TriFunction<String, String, String, String>
        concat3 = (a, b, c) ->
            concatVariableStrings(a, b, c);
    static QuadFunction<String, String, String, String, String>
        concat4 = (a, b, c, d) ->
            concatVariableStrings(a, b, c, d);
    static QuinFunction<String, String, String, String, String, String>
        concat5 = (a, b, c, d, e) ->
            concatVariableStrings(a, b, c, d, e);
    static SexFunction<String, String, String, String, String, String, String>
        concat6 = (a, b, c, d, e, f) ->
            concatVariableStrings(a, b, c, d, e, f);
    static SeptFunction<String, String, String, String, String, String, String, String>
        concat7 = (a, b, c, d, e, f, g) ->
            concatVariableStrings(a, b, c, d, e, f, g);
    static OctFunction<String, String, String, String, String, String, String, String, String>
        concat8 = (a, b, c, d, e, f, g, h) ->
            concatVariableStrings(a, b, c, d, e, f, g, h);
    static NonFunction<String, String, String, String, String, String, String, String, String, String>
        concat9 = (a, b, c, d, e, f, g, h, i) ->
            concatVariableStrings(a, b, c, d, e, f, g, h, i);
    static DecFunction<String, String, String, String, String, String, String, String, String, String, String>
        concat10 = (a, b, c, d, e, f, g, h, i, j) ->
            concatVariableStrings(a, b, c, d, e, f, g, h, i, j);
    static UndecFunction<String, String, String, String, String, String, String, String, String, String, String, String>
        concat11 = (a, b, c, d, e, f, g, h, i, j, k) ->
            concatVariableStrings(a, b, c, d, e, f, g, h, i, j, k);
    static DodecFunction<String, String, String, String, String, String, String, String, String, String, String, String, String>
        concat12 = (a, b, c, d, e, f, g, h, i, j, k, l) ->
            concatVariableStrings(a, b, c, d, e, f, g, h, i, j, k, l);
    static Function<String,
            Function<String, String>>
        curriedConcat2 = a -> b ->
            concatVariableStrings(a, b);
    static Function<String,
            Function<String,
             Function<String, String>>>
        curriedConcat3 = a -> b -> c ->
            concatVariableStrings(a, b, c);
    static Function<String,
            Function<String,
             Function<String,
              Function<String, String>>>>
        curriedConcat4 = a -> b -> c -> d ->
            concatVariableStrings(a, b, c, d);
    static Function<String,
            Function<String,
             Function<String,
              Function<String,
               Function<String, String>>>>>
        curriedConcat5 = a -> b -> c -> d -> e ->
            concatVariableStrings(a, b, c, d, e);
    static Function<String,
            Function<String,
             Function<String,
              Function<String,
               Function<String,
                Function<String, String>>>>>>
        curriedConcat6 = a -> b -> c -> d -> e -> f ->
            concatVariableStrings(a, b, c, d, e, f);
    static Function<String,
            Function<String,
             Function<String,
              Function<String,
               Function<String,
                Function<String,
                 Function<String, String>>>>>>>
        curriedConcat7 = a -> b -> c -> d -> e -> f -> g ->
            concatVariableStrings(a, b, c, d, e, f, g);
    static Function<String,
            Function<String,
             Function<String,
              Function<String,
               Function<String,
                Function<String,
                 Function<String,
                  Function<String, String>>>>>>>>
        curriedConcat8 = a -> b -> c -> d -> e -> f -> g -> h ->
            concatVariableStrings(a, b, c, d, e, f, g, h);
    static Function<String,
            Function<String,
             Function<String,
              Function<String,
               Function<String,
                Function<String,
                 Function<String,
                  Function<String,
                   Function<String, String>>>>>>>>>
        curriedConcat9 = a -> b -> c -> d -> e -> f -> g -> h -> i ->
            concatVariableStrings(a, b, c, d, e, f, g, h, i);
    static Function<String,
            Function<String,
             Function<String,
              Function<String,
               Function<String,
                Function<String,
                 Function<String,
                  Function<String,
                   Function<String,
                    Function<String, String>>>>>>>>>>
        curriedConcat10 = a -> b -> c -> d -> e -> f -> g -> h -> i -> j ->
            concatVariableStrings(a, b, c, d, e, f, g, h, i, j);
    static Function<String,
            Function<String,
             Function<String,
              Function<String,
               Function<String,
                Function<String,
                 Function<String,
                  Function<String,
                   Function<String,
                    Function<String,
                     Function<String, String>>>>>>>>>>>
        curriedConcat11 = a -> b -> c -> d -> e -> f -> g -> h -> i -> j -> k ->
            concatVariableStrings(a, b, c, d, e, f, g, h, i, j, k);
    static Function<String,
            Function<String,
             Function<String,
              Function<String,
               Function<String,
                Function<String,
                 Function<String,
                  Function<String,
                   Function<String,
                    Function<String,
                     Function<String,
                      Function<String, String>>>>>>>>>>>>
        curriedConcat12 = a -> b -> c -> d -> e -> f -> g -> h -> i -> j -> k -> l ->
            concatVariableStrings(a, b, c, d, e, f, g, h, i, j, k, l);

    private static String concatVariableStrings(String ...strings) {
        return Stream.of(strings)
            .reduce(String::concat)
            .get();
    }
}
