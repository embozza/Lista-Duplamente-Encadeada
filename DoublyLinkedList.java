import java.util.Objects;

public class DoublyLinkedList<T> {

    private Node<T> base;
    private Node<T> top;
    private int size;

    private static class Node<T> {
        T value;
        Node<T> next;
        Node<T> previous;

        Node(T value) {
            this.value = value;
        }
    }

    public DoublyLinkedList() {
        base = null;
        top = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return false;
    }

    public int getSize() {
        return size;
    }

    public void add(T value) {
        Node<T> newNode = new Node<>(value);
        if (isEmpty()) {
            base = newNode;
            top = newNode;
        } else {
            top.next = newNode;
            newNode.previous = top;
            top = newNode;
        }
        size++;
    }

    public T remove(int pos) {
        Objects.checkIndex(pos, size);
        Node<T> currentNode = getNode(pos);

        if (currentNode == base) {
            base = base.next;
            if (base != null) {
                base.previous = null;
            }
        } else if (currentNode == top) {
            top = top.previous;
            top.next = null;
        } else {
            currentNode.previous.next = currentNode.next;
            currentNode.next.previous = currentNode.previous;
        }

        size--;
        return currentNode.value;
    }

    public T remove(Node<T> node) {
        Node<T> currentNode = base;
        while (currentNode != null) {
            if (currentNode.equals(node)) {
                if (currentNode == base) {
                    base = base.next;
                    if (base != null) {
                        base.previous = null;
                    }
                } else if (currentNode == top) {
                    top = top.previous;
                    top.next = null;
                } else {
                    currentNode.previous.next = currentNode.next;
                    currentNode.next.previous = currentNode.previous;
                }

                size--;
                return currentNode.value;
            }
            currentNode = currentNode.next;
        }
        return null; 
    }

    public Node<T> getNode(int pos) {
        Objects.checkIndex(pos, size); 
        Node<T> currentNode = base;
        for (int i = 0; i < pos; i++) {
            currentNode = currentNode.next;
        }
        return currentNode;
    }

    public T get(int pos) {
        return getNode(pos).value;
    }

    public void set(int pos, T value) {
        Node<T> node = getNode(pos);
        node.value = value;
    }
}
