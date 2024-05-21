import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;

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
    private final ReadWriteLock[] locks;

    public HashTable(int capacity) {
        this.capacity = capacity;
        this.table = new ArrayList<>(capacity);
        this.locks = new ReentrantReadWriteLock[capacity];
        for (int i = 0; i < capacity; i++) {
            table.add(new LinkedList<>());
            locks[i] = new ReentrantReadWriteLock();
        }
    }

    private int getBucketIndex(Key key) {
        return key.getHashIndex() % capacity;
    }

    @Override
    public void put(Key key, Value value) {
        int index = getBucketIndex(key);
        Lock writeLock = locks[index].writeLock();
        writeLock.lock();
        try {
            LinkedList<MyEntry<Key, Value>> bucket = table.get(index);
            for (MyEntry<Key, Value> entry : bucket) {
                if (entry.getKey().equals(key)) {
                    entry.setValue(value);
                    return;
                }
            }
            bucket.add(new MyEntry<>(key, value));
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public Value get(Key key) {
        int index = getBucketIndex(key);
        Lock readLock = locks[index].readLock();
        readLock.lock();
        try {
            LinkedList<MyEntry<Key, Value>> bucket = table.get(index);
            for (MyEntry<Key, Value> entry : bucket) {
                if (entry.getKey().equals(key)) {
                    return entry.getValue();
                }
            }
            return null; // vagy opcionálisan dobhatnánk kivételt, ha a kulcs nincs meg
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public void remove(Key key) {
        int index = getBucketIndex(key);
        Lock writeLock = locks[index].writeLock();
        writeLock.lock();
        try {
            LinkedList<MyEntry<Key, Value>> bucket = table.get(index);
            bucket.removeIf(entry -> entry.getKey().equals(key));
        } finally {
            writeLock.unlock();
        }
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
        return id;
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

// Tesztelés
public class Main {
    public static void main(String[] args) {
        HashTable<ExampleKey, String> hashTable = new HashTable<>(5);

        Runnable fillTask1 = () -> {
            for (int i = 0; i < 5; i++) {
                hashTable.put(new ExampleKey(i), "Value" + i);
            }
        };

        Runnable fillTask2 = () -> {
            for (int i = 5; i < 10; i++) {
                hashTable.put(new ExampleKey(i), "Value" + i);
            }
        };

        Runnable removeTask = () -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("Removing key " + i + ": " + hashTable.get(new ExampleKey(i)));
                hashTable.remove(new ExampleKey(i));
            }
        };

        Thread thread1 = new Thread(fillTask1);
        Thread thread2 = new Thread(fillTask2);
        Thread thread3 = new Thread(removeTask);

        thread1.start();
        thread2.start();
        thread3.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
