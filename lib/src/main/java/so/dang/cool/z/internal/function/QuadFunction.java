package so.dang.cool.z.internal.function;

import so.dang.cool.z.annotation.Evil;

@Evil
public interface QuadFunction<A, B, C, D, E> {
    public E apply(A a, B b, C c, D d);
}
