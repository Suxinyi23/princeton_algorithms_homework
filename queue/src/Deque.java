import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    private Node<Item> first;
    private Node<Item> last;
    private int size;

    private class Node<Item> {
        Item item;
        Node<Item> next;
        Node<Item> pre;

        Node(Item item) {
            this.item = item;
            this.next = null;
            this.pre = null;
        }
    }

    // construct an empty deque
    public Deque() {
        this.first = this.last = null;
        this.size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return this.size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return this.size;
    }


    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        this.size++;
        if (this.first != null) {
            Node<Item> node = new Node<>(item);
            node.next = this.first;
            this.first.pre = node;
            this.first = node;
        } else {
            Node<Item> node = new Node<Item>(item);
            this.first = this.last = node;
        }
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        this.size++;
        if (this.last != null) {
            Node<Item> node = new Node<>(item);
            node.pre = this.last;
            this.last.next = node;
            this.last = node;
        } else {
            Node<Item> node = new Node<>(item);
            this.first = this.last = node;
        }
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (this.first == null)
            throw new java.util.NoSuchElementException();
        size--;
        if (this.first.next == null) {
            Node<Item> node = first;
            this.first = this.last = null;
            return node.item;
        }
        Node<Item> node = first;
        this.first = this.first.next;
        this.first.pre = null;
        return node.item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (this.first == null)
            throw new java.util.NoSuchElementException();
        size--;
        if (this.first.next == null) {
            Node<Item> node = first;
            this.first = this.last = null;
            return node.item;
        }
        Node<Item> node = last;
        this.last = this.last.pre;
        this.last.next = null;
        return node.item;
    }

    private class DequeIterator implements Iterator<Item> {
        Node<Item> current = first;

        @Override
        public boolean hasNext() {
            return this.current != null;
        }

        @Override
        public Item next() {
            if (this.current == null) {
                throw new java.util.NoSuchElementException();
            }
            Item item = this.current.item;
            this.current = this.current.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> dq = new Deque<Integer>();
        dq.addLast(3);
        for (Integer i : dq) {
            System.out.print(i + " ");
        }
        System.out.println();
        dq.addFirst(2);
        for (Integer i : dq) {
            System.out.print(i + " ");
        }
        System.out.println();
        dq.addLast(5);
        for (Integer i : dq) {
            System.out.print(i + " ");
        }
        System.out.println();
        dq.removeFirst();
        dq.removeLast();
        for (Integer i : dq) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

}