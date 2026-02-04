import java.util.*;

class Solution {
    public String minRemoveToMakeValid(String s) {
        Stack<Integer> stack = new Stack<>();
        Set<Integer> remove = new HashSet<>();

        // First pass: find invalid parentheses
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch == '(') {
                stack.push(i);
            } else if (ch == ')') {
                if (!stack.isEmpty()) {
                    stack.pop();
                } else {
                    remove.add(i); // invalid ')'
                }
            }
        }

        // Remaining '(' in stack are invalid
        while (!stack.isEmpty()) {
            remove.add(stack.pop());
        }

        // Build result
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (!remove.contains(i)) {
                sb.append(s.charAt(i));
            }
        }

        return sb.toString();
    }
}
