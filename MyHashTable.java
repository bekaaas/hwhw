import java.util.LinkedList;

public class MyHashTable<K, V> {

    private static class HashNode<K, V> {
        K key;
        V value;

        HashNode(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private LinkedList<HashNode<K, V>>[] chainArray;
    private int M;
    private int size;

    public MyHashTable() {
        this(11);
    }

    @SuppressWarnings("unchecked")
    public MyHashTable(int M) {
        this.M = M;
        chainArray = new LinkedList[M];
        for (int i = 0; i < M; i++) {
            chainArray[i] = new LinkedList<>();
        }
        size = 0;
    }

    private int hash(K key) {
        return Math.abs(key.hashCode()) % M;
    }

    public void put(K key, V value) {
        int index = hash(key);
        LinkedList<HashNode<K, V>> bucket = chainArray[index];

        for (HashNode<K, V> node : bucket) {
            if (node.key.equals(key)) {
                node.value = value;
                return;
            }
        }

        bucket.add(new HashNode<>(key, value));
        size++;
    }

    public V get(K key) {
        int index = hash(key);
        LinkedList<HashNode<K, V>> bucket = chainArray[index];

        for (HashNode<K, V> node : bucket) {
            if (node.key.equals(key)) {
                return node.value;
            }
        }

        return null;
    }

    public V remove(K key) {
        int index = hash(key);
        LinkedList<HashNode<K, V>> bucket = chainArray[index];

        for (HashNode<K, V> node : bucket) {
            if (node.key.equals(key)) {
                bucket.remove(node);
                size--;
                return node.value;
            }
        }

        return null;
    }

    public boolean contains(V value) {
        for (LinkedList<HashNode<K, V>> bucket : chainArray) {
            for (HashNode<K, V> node : bucket) {
                if (node.value.equals(value)) {
                    return true;
                }
            }
        }
        return false;
    }

    public K getKey(V value) {
        for (LinkedList<HashNode<K, V>> bucket : chainArray) {
            for (HashNode<K, V> node : bucket) {
                if (node.value.equals(value)) {
                    return node.key;
                }
            }
        }
        return null;
    }

    public int size() {
        return size;
    }

    public void printBucketSizes() {
        for (int i = 0; i < M; i++) {
            System.out.println("Bucket " + i + ": " + chainArray[i].size());
        }
    }
}
