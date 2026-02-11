import java.util.LinkedList;
import java.util.Queue;

class RecentCounter {

    private Queue<Integer> queue;

    public RecentCounter() {
        queue = new LinkedList<>();
    }

    public int ping(int t) {
        queue.offer(t);
        
        // Remove timestamps outside the 3000ms window
        while (queue.peek() < t - 3000) {
            queue.poll();
        }
        
        return queue.size();
    }
}
