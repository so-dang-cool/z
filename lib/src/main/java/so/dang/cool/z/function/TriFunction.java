package so.dang.cool.z.function;

import so.dang.cool.z.annotation.Evil;

@Evil
public interface TriFunction<A, B, C, D> {
    public D apply(A a, B b, C c);
}
