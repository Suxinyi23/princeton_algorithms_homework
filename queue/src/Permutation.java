import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

public class Permutation {
    public static void main(String[] args) {
        RandomizedQueue<String> rq = new RandomizedQueue<>();
        int k = Integer.parseInt(args[0]);
        String[] strs = StdIn.readAllStrings();
        StdRandom.shuffle(strs);
        for (int i=0; i<k; i++) {
            rq.enqueue(strs[i]);
        }
        for (int i=0; i<k; i++) {
            System.out.println(rq.dequeue());
        }
    }
}