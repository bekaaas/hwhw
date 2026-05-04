public class BST<K extends Comparable<K>, V> {

    protected class Node {
        K key;
        V val;
        Node left, right;

        Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

    protected Node root;


    public void put(K key, V val) {
        if (root == null) {
            root = new Node(key, val);
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
    }


    public V get(K key) {
        Node current = root;

        while (current != null) {
            int cmp = key.compareTo(current.key);

            if (cmp < 0) current = current.left;
            else if (cmp > 0) current = current.right;
            else return current.val;
        }

        return null;
    }


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
        }
        else {
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
    }
}
