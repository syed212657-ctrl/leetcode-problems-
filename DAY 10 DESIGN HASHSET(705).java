import java.util.LinkedList;

class MyHashSet {
    // A prime number size reduces collision cluster formation
    private static final int BASE = 769;
    private LinkedList<Integer>[] bucket;

    public MyHashSet() {
        bucket = new LinkedList[BASE];
        for (int i = 0; i < BASE; i++) {
            bucket[i] = new LinkedList<>();
        }
    }
    
    private int hash(int key) {
        return key % BASE;
    }

    public void add(int key) {
        int index = hash(key);
        if (!bucket[index].contains(key)) {
            bucket[index].add(key);
        }
    }

    public void remove(int key) {
        int index = hash(key);
        bucket[index].remove(Integer.valueOf(key)); // Remove object, not index
    }

    public boolean contains(int key) {
        int index = hash(key);
        return bucket[index].contains(key);
    }
}
