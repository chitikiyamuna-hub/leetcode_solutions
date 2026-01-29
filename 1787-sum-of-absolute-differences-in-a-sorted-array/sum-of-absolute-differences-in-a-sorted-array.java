class Solution {
    public int[] getSumAbsoluteDifferences(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        
        // Prefix sum of all elements
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }
        
        int leftSum = 0;
        
        for (int i = 0; i < n; i++) {
            int rightSum = totalSum - leftSum - nums[i];
            
            int leftContribution = nums[i] * i - leftSum;
            int rightContribution = rightSum - nums[i] * (n - i - 1);
            
            result[i] = leftContribution + rightContribution;
            
            leftSum += nums[i];
        }
        
        return result;
    }
}
