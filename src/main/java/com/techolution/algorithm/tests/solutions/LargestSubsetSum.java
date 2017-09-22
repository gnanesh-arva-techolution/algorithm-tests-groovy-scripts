package com.techolution.algorithm.tests.solutions;

/**
 * @author Gnanesh Arva
 * @since 20 Sep 2017 at 17:11
 */
public class LargestSubsetSum {

    static long[] maxSubsetSum(int[] k) {
        int length = k.length;
        long[] maxSum = new long[length];
        long sum = 0;
        for (int i = 0; i < length; i++) {
            for (int j = 1; j <= k[i] / 2; j++) {
                if (k[i] % j == 0) {
                    sum = sum + j;
                }
            }
            maxSum[i] = sum + k[i];
            sum = 0;
        }
        return maxSum;
    }

}
