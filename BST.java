import java.util.LinkedList;
import java.util.Queue;

public class BST<K extends Comparable<K>, V> {
    private Node root;
    private int size;

    private class Node {
        private K key;
        private V val;
        private Node left, right;

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

    public void put(K key, V val) {
        Node newNode = new Node(key, val);
        if (root == null) {
            root = newNode;
            size++;
            return;
        }

        Node current = root;
        Node parent = null;

        while (current != null) {
            parent = current;
            if (key.compareTo(current.key) < 0) {
                current = current.left;
            } else if (key.compareTo(current.key) > 0) {
                current = current.right;
            } else {
                current.val = val;
                return;
            }
        }

        if (key.compareTo(parent.key) < 0) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
        size++;
    }

    public V get(K key) {
        Node current = root;

        while (current != null) {
            if (key.compareTo(current.key) < 0) {
                current = current.left;
            } else if (key.compareTo(current.key) > 0) {
                current = current.right;
            } else {
                return current.val;
            }
        }

        return null;
    }

    public void delete(K key) {
        Node parent = null;
        Node current = root;

        while (current != null && !current.key.equals(key)) {
            parent = current;
            if (key.compareTo(current.key) < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        if (current == null) return;

        if (current.left == null || current.right == null) {
            Node newCurr;

            if (current.left == null) {
                newCurr = current.right;
            } else {
                newCurr = current.left;
            }

            if (parent == null) {
                root = newCurr;
            } else if (current == parent.left) {
                parent.left = newCurr;
            } else {
                parent.right = newCurr;
            }
        }
          
        else {
            Node p = null;
            Node temp;

            temp = current.right;
            while (temp.left != null) {
                p = temp;
                temp = temp.left;
            }

            if (p != null) {
                p.left = temp.right;
            } else {
                current.right = temp.right;
            }

            current.key = temp.key;
            current.val = temp.val;
        }

        size--;
    }

    public Iterable<Node> iterator() {
        Queue<Node> queue = new LinkedList<>();
        Stack<Node> stack = new Stack<>();
        Node current = root;

        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            current = stack.pop();
            queue.offer(current);
            current = current.right;
        }

        return queue;
    }

    public int size() {
        return size;
    }

    public class Stack<T> {
        private LinkedList<T> list = new LinkedList<>();

        public void push(T value) {
            list.addFirst(value);
        }

        public T pop() {
            return list.removeFirst();
        }

        public boolean isEmpty() {
            return list.isEmpty();
        }
    }
}
