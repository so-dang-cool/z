package so.dang.cool.z.combination;

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
import so.dang.cool.z.function.BooleanConsumer;
import so.dang.cool.z.function.BooleanFunction;
import so.dang.cool.z.function.BooleanPredicate;
import so.dang.cool.z.function.BooleanToDoubleFunction;
import so.dang.cool.z.function.BooleanToIntFunction;
import so.dang.cool.z.function.BooleanToLongFunction;
import so.dang.cool.z.function.DecFunction;
import so.dang.cool.z.function.DodecFunction;
import so.dang.cool.z.function.NonFunction;
import so.dang.cool.z.function.OctFunction;
import so.dang.cool.z.function.Operator;
import so.dang.cool.z.function.QuadFunction;
import so.dang.cool.z.function.QuinFunction;
import so.dang.cool.z.function.SeptFunction;
import so.dang.cool.z.function.SexFunction;
import so.dang.cool.z.function.TriFunction;
import so.dang.cool.z.function.UndecFunction;

/**
 * Testing functions. For simplicity of combinations, every generic uses the
 * same type (String) -- primitives, of course, must remain primitives.
 */
public final class TestFunctions {

    public static final String suppliedString = "Z";
    public static final double suppliedDouble = 1.0;
    public static final int suppliedInt = 2;
    public static final long suppliedLong = 3L;

    public static String consumedStringA = "";
    public static String consumedStringB = "";
    public static String consumedStringC = "";
    public static String consumedStringD = "";
    public static String consumedStringE = "";
    public static String consumedStringF = "";
    public static String consumedStringG = "";
    public static Double consumedDoubleA = 0.0;
    public static Double consumedDoubleB = 0.0;
    public static Double consumedDoubleC = 0.0;
    public static Integer consumedIntA = 0;
    public static Integer consumedIntB = 0;
    public static Integer consumedIntC = 0;
    public static Long consumedLongA = 0L;
    public static Long consumedLongB = 0L;
    public static Long consumedLongC = 0L;
    public static Boolean consumedBooleanA = false;
    public static Boolean consumedBooleanB = false;
    public static Boolean consumedBooleanC = false;
    public static Boolean consumedBooleanD = false;
    public static Boolean consumedBooleanE = false;
    public static Boolean consumedBooleanF = false;
    public static Boolean wasOperated = false;

    TestFunctions() {}

    public static Function<String, String> toLower = String::toLowerCase;
    public static Function<String, String> trim = String::trim;
    public static Function<String, String> addExclamationMark = s ->
        s.concat("!");
    public static BiFunction<String, String, String> concat = String::concat;
    public static BooleanFunction<String> booleanToString = String::valueOf;
    public static BooleanFunction<String> maybeOneAsString = b -> b ? "1" : "";
    public static BooleanToDoubleFunction maybeOneAsDouble = b ->
        b ? 1.0 : Double.NaN;
    public static BooleanToIntFunction maybeTwoAsInt = b -> b ? 2 : 0;
    public static BooleanToLongFunction maybeThreeAsLong = b -> b ? 3L : 0L;
    public static DoubleFunction<String> doubleToString = String::valueOf;
    public static DoubleFunction<String> doubleFloorToString = (double d) ->
        String.valueOf(d).split("\\.")[0];
    public static DoubleToIntFunction doubleToInt = d -> (int) d;
    public static DoubleToLongFunction doubleToLong = d -> (long) d;
    public static ToDoubleFunction<String> stringToDouble = Double::parseDouble;
    public static ToDoubleBiFunction<String, String> addStringsAsDouble = (a, b) ->
        Double.parseDouble(a) + Double.parseDouble(b);
    public static IntFunction<String> intToString = String::valueOf;
    public static IntToDoubleFunction intToDouble = i -> (double) i;
    public static IntToLongFunction intToLong = i -> (long) i;
    public static ToIntFunction<String> stringToInt = Integer::parseInt;
    public static ToIntBiFunction<String, String> addStringsAsInt = (a, b) ->
        Integer.parseInt(a) + Integer.parseInt(b);
    public static LongFunction<String> longToString = String::valueOf;
    public static LongToDoubleFunction longToDouble = n -> (double) n;
    public static LongToIntFunction longToInt = n -> (int) n;
    public static ToLongFunction<String> stringToLong = Long::parseLong;
    public static ToLongBiFunction<String, String> addStringsAsLong = (a, b) ->
        Long.parseLong(a) + Long.parseLong(b);
    public static Predicate<String> isEmpty = String::isEmpty;
    public static Predicate<String> isNotEmpty = Predicate.not(isEmpty);
    public static BiPredicate<String, String> doesStartWith =
        String::startsWith;
    public static BiPredicate<String, String> doesNotStartWith = (a, b) ->
        !doesStartWith.test(a, b);
    public static BooleanPredicate booleanId = b -> b;
    public static BooleanPredicate not = b -> !b;
    public static Consumer<String> saveStringA = a -> consumedStringA = a;
    public static BiConsumer<String, String> saveStringsBandC = (b, c) -> {
        consumedStringB = b;
        consumedStringC = c;
    };
    public static BooleanConsumer saveBooleanA = b -> consumedBooleanA = b;
    public static BooleanConsumer saveBooleanF = b -> consumedBooleanF = b;
    public static DoubleConsumer saveDoubleA = a -> consumedDoubleA = a;
    public static ObjDoubleConsumer<String> saveStringDDoubleB = (d, b) -> {
        consumedStringD = d;
        consumedDoubleB = b;
    };
    public static IntConsumer saveIntA = a -> consumedIntA = a;
    public static ObjIntConsumer<String> saveStringEIntB = (e, b) -> {
        consumedStringE = e;
        consumedIntB = b;
    };
    public static LongConsumer saveLongA = a -> consumedLongA = a;
    public static ObjLongConsumer<String> saveStringFLongB = (f, b) -> {
        consumedStringF = f;
        consumedLongB = b;
    };
    public static Supplier<String> getString = () -> suppliedString;
    public static DoubleSupplier getDouble = () -> suppliedDouble;
    public static IntSupplier getInt = () -> suppliedInt;
    public static LongSupplier getLong = () -> suppliedLong;
    public static Operator doOperation = () -> wasOperated = true;
    public static UnaryOperator<String> addQuestionMark = s -> s.concat("?");
    public static UnaryOperator<String> addTrailingZero = s -> s.concat("0");
    public static BinaryOperator<String> relation = (a, b) ->
        a.equalsIgnoreCase(b) ? "same-ish" : "different";
    public static BinaryOperator<String> concatAndAddTrailingZero = (a, b) ->
        a.concat(b).concat("0");
    public static DoubleUnaryOperator addOneToDouble = d -> d + 1.0;
    public static DoubleBinaryOperator addDoubles = (d1, d2) -> d1 + d2;
    public static IntUnaryOperator addTwoToInt = i -> i + 2;
    public static IntBinaryOperator addInts = (i1, i2) -> i1 + i2;
    public static LongUnaryOperator addThreeToLong = n -> n + 3L;
    public static LongBinaryOperator addLongs = (n1, n2) -> n1 + n2;

    public static BooleanSupplier getBoolean(boolean b) {
        return () -> b;
    }

    public static DoublePredicate isDouble(double d1) {
        return d2 -> {
            double greater = Math.max(d1, d2);
            double lesser = Math.min(d1, d2);
            return Math.abs(greater - lesser) < Math.ulp(lesser);
        };
    }

    public static IntPredicate isInt(int i1) {
        return i2 -> i1 == i2;
    }

    public static LongPredicate isLong(long n1) {
        return n2 -> n1 == n2;
    }

    // fmt:off

    public static BiFunction<String, String, String> concat2 =
        (a, b) -> concatVariableStrings(a, b);
    public static TriFunction<String, String, String, String> concat3 =
        (a, b, c) -> concatVariableStrings(a, b, c);
    public static QuadFunction<String, String, String, String, String> concat4 =
        (a, b, c, d) -> concatVariableStrings(a, b, c, d);
    public static QuinFunction<String, String, String, String, String, String> concat5 =
        (a, b, c, d, e) -> concatVariableStrings(a, b, c, d, e);
    public static SexFunction<String, String, String, String, String, String, String> concat6 =
        (a, b, c, d, e, f) -> concatVariableStrings(a, b, c, d, e, f);
    public static SeptFunction<String, String, String, String, String, String, String, String> concat7 =
        (a, b, c, d, e, f, g) -> concatVariableStrings(a, b, c, d, e, f, g);
    public static OctFunction<String, String, String, String, String, String, String, String, String> concat8 =
        (a, b, c, d, e, f, g, h) -> concatVariableStrings(a, b, c, d, e, f, g, h);
    public static NonFunction<String, String, String, String, String, String, String, String, String, String> concat9 =
        (a, b, c, d, e, f, g, h, i) -> concatVariableStrings(a, b, c, d, e, f, g, h, i);
    public static DecFunction<String, String, String, String, String, String, String, String, String, String, String> concat10 =
        (a, b, c, d, e, f, g, h, i, j) -> concatVariableStrings(a, b, c, d, e, f, g, h, i, j);
    public static UndecFunction<String, String, String, String, String, String, String, String, String, String, String, String> concat11 =
        (a, b, c, d, e, f, g, h, i, j, k) -> concatVariableStrings(a, b, c, d, e, f, g, h, i, j, k);
    public static DodecFunction<String, String, String, String, String, String, String, String, String, String, String, String, String> concat12 =
        (a, b, c, d, e, f, g, h, i, j, k, l) -> concatVariableStrings(a, b, c, d, e, f, g, h, i, j, k, l);
    public static Function<String, Function<String, String>> curriedConcat2 =
        a -> b -> concatVariableStrings(a, b);
    public static Function<String, Function<String, Function<String, String>>> curriedConcat3 =
        a -> b -> c -> concatVariableStrings(a, b, c);
    public static Function<String, Function<String, Function<String, Function<String, String>>>> curriedConcat4 =
        a -> b -> c -> d -> concatVariableStrings(a, b, c, d);
    public static Function<String, Function<String, Function<String, Function<String, Function<String, String>>>>> curriedConcat5 =
        a -> b -> c -> d -> e -> concatVariableStrings(a, b, c, d, e);
    public static Function<String, Function<String, Function<String, Function<String, Function<String, Function<String, String>>>>>> curriedConcat6 =
        a -> b -> c -> d -> e -> f -> concatVariableStrings(a, b, c, d, e, f);
    public static Function<String, Function<String, Function<String, Function<String, Function<String, Function<String, Function<String, String>>>>>>> curriedConcat7 =
        a -> b -> c -> d -> e -> f -> g -> concatVariableStrings(a, b, c, d, e, f, g);
    public static Function<String, Function<String, Function<String, Function<String, Function<String, Function<String, Function<String, Function<String, String>>>>>>>> curriedConcat8 =
        a -> b -> c -> d -> e -> f -> g -> h -> concatVariableStrings(a, b, c, d, e, f, g, h);
    public static Function<String, Function<String, Function<String, Function<String, Function<String, Function<String, Function<String, Function<String, Function<String, String>>>>>>>>> curriedConcat9 =
        a -> b -> c -> d -> e -> f -> g -> h -> i -> concatVariableStrings(a, b, c, d, e, f, g, h, i);
    public static Function<String, Function<String, Function<String, Function<String, Function<String, Function<String, Function<String, Function<String, Function<String, Function<String, String>>>>>>>>>> curriedConcat10 =
        a -> b -> c -> d -> e -> f -> g -> h -> i -> j -> concatVariableStrings(a, b, c, d, e, f, g, h, i, j);
    public static Function<String, Function<String, Function<String, Function<String, Function<String, Function<String, Function<String, Function<String, Function<String, Function<String, Function<String, String>>>>>>>>>>> curriedConcat11 =
        a -> b -> c -> d -> e -> f -> g -> h -> i -> j -> k -> concatVariableStrings(a, b, c, d, e, f, g, h, i, j, k);
    public static Function<String, Function<String, Function<String, Function<String, Function<String, Function<String, Function<String, Function<String, Function<String, Function<String, Function<String, Function<String, String>>>>>>>>>>>> curriedConcat12 =
        a -> b -> c -> d -> e -> f -> g -> h -> i -> j -> k -> l -> concatVariableStrings(a, b, c, d, e, f, g, h, i, j, k, l);

    // fmt:on

    private static String concatVariableStrings(String... strings) {
        return Stream.of(strings).reduce(String::concat).get();
    }
}
