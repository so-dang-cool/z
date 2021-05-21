package so.dang.cool.z.function;

import so.dang.cool.z.annotation.Evil;

@Evil
public interface QuinFunction<A, B, C, D, E, F> {
    public F apply(A a, B b, C c, D d, E e);
}
