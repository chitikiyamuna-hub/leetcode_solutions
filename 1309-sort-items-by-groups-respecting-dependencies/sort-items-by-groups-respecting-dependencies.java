import java.util.*;

class Solution {

    public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {

        // Assign unique group IDs to ungrouped items
        for (int i = 0; i < n; i++) {
            if (group[i] == -1) {
                group[i] = m++;
            }
        }

        // Item graph
        List<List<Integer>> itemGraph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            itemGraph.add(new ArrayList<>());
        }

        int[] itemIndegree = new int[n];

        // Group graph
        List<List<Integer>> groupGraph = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            groupGraph.add(new ArrayList<>());
        }

        int[] groupIndegree = new int[m];

        // Build graphs
        for (int curr = 0; curr < n; curr++) {
            for (int prev : beforeItems.get(curr)) {

                // Item dependency
                itemGraph.get(prev).add(curr);
                itemIndegree[curr]++;

                // Group dependency
                if (group[curr] != group[prev]) {
                    groupGraph.get(group[prev]).add(group[curr]);
                    groupIndegree[group[curr]]++;
                }
            }
        }

        // Topological sort for items and groups
        List<Integer> itemOrder = topoSort(itemGraph, itemIndegree, n);
        List<Integer> groupOrder = topoSort(groupGraph, groupIndegree, m);

        // Cycle detected
        if (itemOrder.isEmpty() || groupOrder.isEmpty()) {
            return new int[0];
        }

        // Map items to groups
        Map<Integer, List<Integer>> groupToItems = new HashMap<>();

        for (int item : itemOrder) {
            groupToItems
                .computeIfAbsent(group[item], k -> new ArrayList<>())
                .add(item);
        }

        // Build final answer
        List<Integer> result = new ArrayList<>();

        for (int grp : groupOrder) {
            result.addAll(groupToItems.getOrDefault(grp, new ArrayList<>()));
        }

        return result.stream().mapToInt(i -> i).toArray();
    }

    private List<Integer> topoSort(List<List<Integer>> graph,
                                   int[] indegree,
                                   int size) {

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < size; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        List<Integer> order = new ArrayList<>();

        while (!queue.isEmpty()) {
            int node = queue.poll();
            order.add(node);

            for (int neighbor : graph.get(node)) {
                indegree[neighbor]--;

                if (indegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        return order.size() == size ? order : new ArrayList<>();
    }
}