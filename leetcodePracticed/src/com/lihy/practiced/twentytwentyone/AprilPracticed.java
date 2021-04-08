package com.lihy.practiced.twentytwentyone;

import java.util.*;

/**
 * 四月每日一题
 *
 * @author hongyanli
 * @since 2021/4/1 11:10
 */
public class AprilPracticed {

    public static void main(String[] args) {
        System.out.println(clumsy(10));;
    }


    /**
     * 08号问题
     *
     * @param nums 入参数组
     * @return 结果
     */
    public int findMin(int[] nums) {
        return Arrays.stream(nums).min().getAsInt();
    }


    /**
     * 在被旋转过的数组中找到目标值
     *
     * @param nums nums
     * @param target 目标值
     * @return 结果
     */
    public boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0){
            return false;
        }
        return Arrays.stream(nums).allMatch(n -> n == target);
    }


    /**
     * 7 号做的问题 但是不是每日一题
     *
     * @param x 数
     * @param n 名字
     * @return 结果
     */
    public double myPow(double x, int n) {
        long N = n;
        return N >= 0 ? quickMul(x, N) : 1.0 / quickMul(x, -N);
    }

    public double quickMul(double x, long N) {
        if (N == 0) {
            return 1.0;
        }
        double y = quickMul(x, N / 2);
        return N % 2 == 0 ? y * y : y * y * x;
    }

    /**
     * 6号问题，删除数组中重复的值
     *
     * @param nums 数组
     * @return 结果
     */
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0){
            return 0;
        }

        int k = 0;
        int count = 1;
        for (int i = 1; i < nums.length; i++){
            if (nums[i] == nums[i - 1] ){
                count++;
            }
            else {
                count = 1;
            }
            if (count <= 2){
                nums[++k] = nums[i];
            }

        }

        return k + 1;
    }

    /**
     * 5号问题，合并有序数组
     *
     * @param nums1 数组1
     * @param m 数组1元素容量
     * @param nums2 数组2
     * @param n 数组2 元素容量
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p = m + n - 1;
        int p1 = m-1;
        int p2 = n-1;

        while ( p1 >= 0 && p2 >= 0) {
            nums1[p--] = nums1[p1] > nums2[p2] ? nums1[p1--] : nums2[p2--];
        }

        if ( n > 0){
            System.arraycopy(nums2,0,nums1,0,p2 + 1);
        }
    }


    /**
     * 4号问题，森林里有多少兔子
     *
     * @param answers 回答数组
     * @return 结果
     */
    public int numRabbits(int[] answers) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int y : answers) {
            count.put(y, count.getOrDefault(y, 0) + 1);
        }
        int ans = 0;
        for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
            int y = entry.getKey(), x = entry.getValue();
            ans += (x + y) / (y + 1) * (y + 1);
        }
        return ans;

    }

    /**
     * 03
     *
     * @param text1 串1
     * @param text2 串2
     * @return 结果
     */
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            char c1 = text1.charAt(i - 1);
            for (int j = 1; j <= n; j++) {
                char c2 = text2.charAt(j - 1);
                if (c1 == c2) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }

    /**
     * 02
     *
     * @param height 数组
     * @return 结果
     */
    public int trap(int[] height) {
        int n = height.length;
        if (n == 0) {
            return 0;
        }

        int[] leftMax = new int[n];
        leftMax[0] = height[0];
        for (int i = 1; i < n; ++i) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }

        int[] rightMax = new int[n];
        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; --i) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }

        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        return ans;
    }

    /**
     * 01 问题， 笨阶乘
     *
     * @param N 入参
     * @return 结果
     */
    public static  int clumsy(int N) {
        Deque<Integer> stack = new LinkedList<>();
        stack.push(N--);


        int i = 0;
        while (N > 0){
           if (i % 4 == 0){
               stack.push(stack.pop() * N);
           }else if (i % 4 == 1){
               stack.push(stack.pop() / N);
           }else if (i % 4 == 2){
               stack.push(N);
           }else {
               stack.push(-N);
           }
           N--;
           i++;
        }

        int result = 0;
        while (!stack.isEmpty()){
            result += stack.pop();
        }
        return result;
    }
}
