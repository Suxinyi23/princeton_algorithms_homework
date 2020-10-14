import edu.princeton.cs.algs4.StdRandom;
import java.util.Arrays;
import java.util.Iterator;


public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] array;
    private int size;

    // construct an empty randomized queue
    public RandomizedQueue() {
        this.size = 0;
        this.array = (Item[]) new Object[4];
    }

    private RandomizedQueue(Item[] array, int size) {
        this.size = size;
        this.array = Arrays.copyOf(array,array.length);
    }
    // is the randomized queue empty?
    public boolean isEmpty() {
        return this.size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return this.size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (this.size == this.array.length) {
            this.resize(this.array.length,  2 * this.size);
        }
        this.array[size] = item;
        this.size++;
    }

    private void resize(int oldCapacity, int capacity) {
        capacity = Math.max(capacity, 4);
        if(oldCapacity == capacity) return;
        Item[] newArray = (Item[]) new Object[capacity];
        for (int i = 0; i < this.size; i++) {
            newArray[i] = this.array[i];
        }
        this.array = newArray;
    }

    // remove and return a random item
    public Item dequeue() {
        if (this.size == 0) throw new java.util.NoSuchElementException();
        int target = StdRandom.uniform(0, this.size);
        Item ret = this.array[target];
        this.array[target] = null;
        Item temp = this.array[target];
        this.array[target] = this.array[size - 1];
        this.array[size - 1] = temp;
        size--;
        if (this.size <= 0.25 * this.array.length)
            this.resize(this.array.length, (int) (0.5 * this.array.length));
        return ret;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (this.size == 0) throw new java.util.NoSuchElementException();
        int target = StdRandom.uniform(0, this.size);
        return this.array[target];
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        RandomizedQueue<Item> rq = new RandomizedQueue<>(array, size);

        @Override
        public boolean hasNext() {
            return rq.size > 0;
        }

        @Override
        public Item next() {
            if(!this.hasNext())throw new java.util.NoSuchElementException();
            return rq.dequeue();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> rq = new RandomizedQueue<>();
        rq.enqueue(2);
        rq.enqueue(3);
        rq.enqueue(5);
        for (Integer i : rq) {
            System.out.print(i + " ");
        }
        System.out.println();
        rq.dequeue();
        for (Integer i : rq) {
            System.out.print(i + " ");
        }
        System.out.println();
        rq.dequeue();
        for (Integer i : rq) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

}