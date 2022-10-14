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

interface Zack<PREV extends Zack<?, ?>, CURR> {
    CURR top();
    PREV prev();

    default <NEXT> Zack<Zack<PREV, CURR>, NEXT> push(NEXT next) {
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

    class OneNode<PREV extends Zack<?, ?>, CURR> implements Zack<PREV, CURR> {
        private PREV prev;
        private CURR v;

        OneNode(PREV prev, CURR v) {
            this.prev = prev;
            this.v = v;
        }

        @Override
        public CURR top() {
            return v;
        }

        @Override
        public PREV prev() {
            return prev;
        }
    }
}
