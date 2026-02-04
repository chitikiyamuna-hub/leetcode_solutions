import java.util.*;

class Solution {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();
        int j = 0; // index for popped

        for (int x : pushed) {
            stack.push(x);

            // Pop as long as top matches popped[j]
            while (!stack.isEmpty() && stack.peek() == popped[j]) {
                stack.pop();
                j++;
            }
        }

        // If stack is empty, sequence is valid
        return stack.isEmpty();
    }
}
