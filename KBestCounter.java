import java.util.*;

public class KBestCounter<T extends Comparable<? super T>> {   
    private int k;
    private PriorityQueue<T> heap;
    
    //constructor
    public KBestCounter(int k) {
        heap = new PriorityQueue<T>(k);
        this.k = k;
    }
    
    public void count(T x) {
        if (heap.size() < x) {
            heap.offer(x);
        }
        
        else {
            if (heap.peek().compareTo(x) < 0) {
                heap.poll();
                heap.offer(x);
            }
        }
    }
    
    public List<T> kbest() {
        ArrayList<T> list = new ArrayList<>();
        PriorityQueue<T> newHeap = new PriorityQueue<T>(heap);
        int i = 0;

        while (heap.isEmpty() == false) {
            T next = heap.remove();
            list.add(i++, next);
        }

        heap = newHeap;
        
        return heap;
    } 
}