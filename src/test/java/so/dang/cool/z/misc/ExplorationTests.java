package so.dang.cool.z.misc;

import java.util.List;

import org.junit.jupiter.api.Test;

public class ExplorationTests {
    @Test
    public void cons() {
        var stack = Zack.of("Hello!").push(17).push(List.of('a', 'b', 'c'));

        // The test is mostly that typing works here without casting:
        List<Character> abcs = stack.top();
        Integer seventeen = stack.prev().top();
        String greeting = stack.prev().prev().top();

        System.out.println(abcs);
        System.out.println(seventeen);
        System.out.println(greeting);
    }
}

interface Zack<ST extends Zack<?, ?>, VAL> {
    VAL top();
    ST prev();

    default <NEXT> Zack<Zack<ST, VAL>, NEXT> push(NEXT next) {
        return new OneNode<>(this, next);
    }

    public static <VAL> Zack<VoidNode, VAL> of(VAL next) {
        return new OneNode<>(new VoidNode(), next);
    }

    class VoidNode implements Zack<VoidNode, Void> {
        @Override
        public Void top() {
            throw new RuntimeException("Zack overflow!");
        }

        @Override
        public VoidNode prev() {
            throw new RuntimeException("Zack overflow!");
        }
    }

    class OneNode<ST extends Zack<?, ?>, VAL> implements Zack<ST, VAL> {
        private ST prev;
        private VAL v;

        OneNode(ST prev, VAL v) {
            this.prev = prev;
            this.v = v;
        }

        @Override
        public VAL top() {
            return v;
        }

        @Override
        public ST prev() {
            return prev;
        }
    }
}
