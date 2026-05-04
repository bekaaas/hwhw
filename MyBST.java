import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class MyBST<K extends Comparable<K>, V> extends BST<K, V>
        implements Iterable<MyBST.Entry<K, V>> {

    private int size;

    // Entry для key + value
    public static class Entry<K, V> {
        private K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() { return key; }
        public V getValue() { return value; }
    }

    public int size() {
        return size;
    }

    @Override
    public void put(K key, V val) {
        if (root == null) {
            root = new Node(key, val);
            size++;
            return;
        }

        Node current = root;
        Node parent = null;

        while (current != null) {
            parent = current;

            int cmp = key.compareTo(current.key);

            if (cmp < 0) current = current.left;
            else if (cmp > 0) current = current.right;
            else {
                current.val = val;
                return;
            }
        }

        if (key.compareTo(parent.key) < 0)
            parent.left = new Node(key, val);
        else
            parent.right = new Node(key, val);

        size++;
    }

    @Override
    public void delete(K key) {
        Node current = root;
        Node parent = null;

        while (current != null && !current.key.equals(key)) {
            parent = current;

            if (key.compareTo(current.key) < 0)
                current = current.left;
            else
                current = current.right;
        }

        if (current == null) return;

        if (current.left == null || current.right == null) {
            Node newChild = (current.left != null) ? current.left : current.right;

            if (parent == null) root = newChild;
            else if (parent.left == current) parent.left = newChild;
            else parent.right = newChild;
        } else {
            Node successorParent = current;
            Node successor = current.right;

            while (successor.left != null) {
                successorParent = successor;
                successor = successor.left;
            }

            current.key = successor.key;
            current.val = successor.val;

            if (successorParent.left == successor)
                successorParent.left = successor.right;
            else
                successorParent.right = successor.right;
        }

        size--;
    }

    // ITERATOR (in-order)
    @Override
    public Iterator<Entry<K, V>> iterator() {
        List<Entry<K, V>> list = new ArrayList<>();
        Stack<Node> stack = new Stack<>();

        Node current = root;

        while (current != null || !stack.isEmpty()) {

            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            current = stack.pop();

            list.add(new Entry<>(current.key, current.val));

            current = current.right;
        }

        return list.iterator();
    }
}
