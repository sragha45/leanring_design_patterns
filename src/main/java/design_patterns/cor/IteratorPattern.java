package design_patterns.cor;

import java.util.Iterator;
import java.util.List;

public class IteratorPattern {
    public static void main(String[] args) {
        for(int x: new Doubler()) {
            System.out.println(x);
        }
    }
}

class Doubler implements Iterable<Integer> {

    List<Integer> data = List.of(1, 2, 3, 4, 5, 6);
    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            int pos = 0;
            @Override
            public boolean hasNext() {
                return pos != data.size();
            }

            @Override
            public Integer next() {
                return 2 * data.get(pos++);
            }
        };
    }
}
