class Solution {

    public int maxAreaOfIsland(int[][] grid) {

        int rows = grid.length;
        int cols = grid[0].length;
        int maxArea = 0;

        for (int i = 0; i < rows; i++) {

            for (int j = 0; j < cols; j++) {

                if (grid[i][j] == 1) {
                    maxArea = Math.max(maxArea, dfs(grid, i, j));
                }
            }
        }

        return maxArea;
    }

    private int dfs(int[][] grid, int row, int col) {

        // Boundary check
        if (row < 0 || col < 0 ||
            row >= grid.length || col >= grid[0].length ||
            grid[row][col] == 0) {

            return 0;
        }

        // Mark visited
        grid[row][col] = 0;

        int area = 1;

        // Explore 4 directions
        area += dfs(grid, row - 1, col); // up
        area += dfs(grid, row + 1, col); // down
        area += dfs(grid, row, col - 1); // left
        area += dfs(grid, row, col + 1); // right

        return area;
    }
}