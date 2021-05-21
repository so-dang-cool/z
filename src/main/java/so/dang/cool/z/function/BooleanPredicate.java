package so.dang.cool.z.function;

public interface BooleanPredicate {
    public static final BooleanPredicate ID = b -> b;
    public static final BooleanPredicate NOT = b -> !b;

    boolean test(boolean b);
}
