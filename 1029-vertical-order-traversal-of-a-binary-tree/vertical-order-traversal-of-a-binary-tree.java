import java.util.*;

class Solution {
    
    class NodeInfo {
        int row;
        int val;
        
        NodeInfo(int row, int val) {
            this.row = row;
            this.val = val;
        }
    }
    
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        // column -> list of NodeInfo
        TreeMap<Integer, List<NodeInfo>> map = new TreeMap<>();
        
        dfs(root, 0, 0, map);
        
        List<List<Integer>> result = new ArrayList<>();
        
        for (List<NodeInfo> list : map.values()) {
            // Sort by row first, then by value
            Collections.sort(list, (a, b) -> {
                if (a.row == b.row)
                    return a.val - b.val;
                return a.row - b.row;
            });
            
            List<Integer> column = new ArrayList<>();
            for (NodeInfo node : list) {
                column.add(node.val);
            }
            result.add(column);
        }
        
        return result;
    }
    
    private void dfs(TreeNode node, int row, int col,
                     TreeMap<Integer, List<NodeInfo>> map) {
        
        if (node == null) return;
        
        map.putIfAbsent(col, new ArrayList<>());
        map.get(col).add(new NodeInfo(row, node.val));
        
        dfs(node.left, row + 1, col - 1, map);
        dfs(node.right, row + 1, col + 1, map);
    }
}
