package exercise;

import java.util.ArrayList;
import java.util.List;

class SafetyList {
    // BEGIN
    private List<Integer> list = new ArrayList<>();

    public synchronized void add(int number) {
        this.list.add(number);
    }

    public int get(int idx) {
        return this.list.get(idx).intValue();
    }

    public int getSize() {
        return this.list.size();
    }

    public List<Integer> getList() {
        return new ArrayList<>(this.list);
    }
    // END
}
