package cbr.object;

import java.util.ArrayList;
import java.util.List;

public class Accumulator<T> {
    private final List<T> list = new ArrayList<>();

    public void add(T object) {
        list.add(object);
    }

    public List<T> getResult() {
        return list;
    }
}
