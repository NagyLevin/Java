import java.util.ArrayList;
import java.util.LinkedList;

// Hashable interfész
interface Hashable {
    int getHashIndex();
}

// MyEntry osztály a kulcs-érték párok tárolására
class MyEntry<Key, Value> {
    Key key;
    Value value;

    public MyEntry(Key key, Value value) {
        this.key = key;
        this.value = value;
    }

    public Key getKey() {
        return key;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }
}

// MyMap interfész
interface MyMap<Key extends Hashable, Value> {
    void put(Key key, Value value);
    Value get(Key key);
    void remove(Key key);
}

// HashTable osztály, ami megvalósítja a MyMap interfészt
class HashTable<Key extends Hashable, Value> implements MyMap<Key, Value> {
    private final ArrayList<LinkedList<MyEntry<Key, Value>>> table;
    private final int capacity;

    public HashTable(int capacity) {
        this.capacity = capacity;
        this.table = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; i++) {
            table.add(new LinkedList<>());
        }
    }

    private int getBucketIndex(Key key) {
        return key.getHashIndex() % capacity;
    }

    @Override
    public void put(Key key, Value value) {
        int index = getBucketIndex(key);
        LinkedList<MyEntry<Key, Value>> bucket = table.get(index);

        for (MyEntry<Key, Value> entry : bucket) {
            if (entry.getKey().equals(key)) {
                entry.setValue(value);
                return;
            }
        }

        bucket.add(new MyEntry<>(key, value));
    }

    @Override
    public Value get(Key key) {
        int index = getBucketIndex(key);
        LinkedList<MyEntry<Key, Value>> bucket = table.get(index);

        for (MyEntry<Key, Value> entry : bucket) {
            if (entry.getKey().equals(key)) {
                return entry.getValue();
            }
        }
        return null; // vagy opcionálisan dobhatnánk kivételt, ha a kulcs nincs meg
    }

    @Override
    public void remove(Key key) {
        int index = getBucketIndex(key);
        LinkedList<MyEntry<Key, Value>> bucket = table.get(index);

        bucket.removeIf(entry -> entry.getKey().equals(key));
    }
}

// Példa Hashable implementáció
class ExampleKey implements Hashable {
    private final int id;

    public ExampleKey(int id) {
        this.id = id;
    }

    @Override
    public int getHashIndex() {
        return Integer.hashCode(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ExampleKey that = (ExampleKey) obj;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}

// Használat példa
public class Main {
    public static void main(String[] args) {
        HashTable<ExampleKey, String> hashTable = new HashTable<>(10);

        ExampleKey key1 = new ExampleKey(1);
        ExampleKey key2 = new ExampleKey(2);

        hashTable.put(key1, "Value1");
        hashTable.put(key2, "Value2");

        System.out.println(hashTable.get(key1)); // Kimenet: Value1
        System.out.println(hashTable.get(key2)); // Kimenet: Value2

        hashTable.remove(key1);
        System.out.println(hashTable.get(key1)); // Kimenet: null
    }
}
