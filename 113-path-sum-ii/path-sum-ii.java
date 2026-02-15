import java.util.*;

class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> currentPath = new ArrayList<>();
        
        dfs(root, targetSum, currentPath, result);
        return result;
    }
    
    private void dfs(TreeNode node, int remainingSum, 
                     List<Integer> currentPath, 
                     List<List<Integer>> result) {
        
        if (node == null) return;
        
        // Add current node to path
        currentPath.add(node.val);
        
        // Check if it's a leaf and sum matches
        if (node.left == null && node.right == null 
            && remainingSum == node.val) {
            
            result.add(new ArrayList<>(currentPath)); // Copy path
        } else {
            // Continue searching
            dfs(node.left, remainingSum - node.val, currentPath, result);
            dfs(node.right, remainingSum - node.val, currentPath, result);
        }
        
        // Backtrack (remove current node)
        currentPath.remove(currentPath.size() - 1);
    }
}
