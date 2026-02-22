/**
 * Definition for a binary tree node.
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    
    TreeNode(int x) {
        val = x;
    }
}

public class Solution {
    
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        
        while (root != null) {
            
            if (p.val < root.val && q.val < root.val) {
                root = root.left;   // Move left
            } 
            else if (p.val > root.val && q.val > root.val) {
                root = root.right;  // Move right
            } 
            else {
                return root;        // This is the split point (LCA)
            }
        }
        
        return null; // Not needed per constraints, but added for safety
    }
}