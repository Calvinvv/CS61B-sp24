import org.eclipse.jetty.server.HttpInput;

import java.security.PrivateKey;
import java.util.List;
import java.util.ArrayList; // import the ArrayList class


public class LinkedListDeque61B<T> implements Deque61B<T> {

    class Node {
        public Node prev;
        public T item;
        public Node next;
        Node(Node prev, T item, Node next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }
    }
    private int size = 0;
    private Node sentinel;

    public LinkedListDeque61B() {
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }


    @Override
    public void addFirst(T x) {
        sentinel.next = new Node(sentinel, x, sentinel.next);
        if (size == 0) { //保障addfirst时Sentinel可以指向last元素
            sentinel.prev = sentinel.next;
            sentinel.next.next = sentinel;
        }
        size++;
    }

    @Override
    public void addLast(T x) {
        sentinel.prev.next = new Node(sentinel.prev, x, sentinel);
        sentinel.prev = sentinel.prev.next;
        size++;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        Node now = sentinel.next;
        while (now.item != null) {
            returnList.add(now.item);
            now = now.next;
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size--;
        return sentinel.next.item;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        size--;
        return sentinel.prev.item;
    }

    @Override
    public T get(int index) {
        if (index > size - 1 || index < 0) {
            return null;
        }
        Node now = sentinel.next;
        for (int i = 0; i < index; i++) {
            now = now.next;
        }
        return now.item;

    }

    @Override
    public T getRecursive(int index) {
        return null;
    }
}
