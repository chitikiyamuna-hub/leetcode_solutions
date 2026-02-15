import java.util.*;

class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        postorder(root, result);
        return result;
    }
    
    private void postorder(TreeNode node, List<Integer> result) {
        if (node == null) {
            return;
        }
        
        postorder(node.left, result);   // Visit left subtree
        postorder(node.right, result);  // Visit right subtree
        result.add(node.val);           // Visit root
    }
}
