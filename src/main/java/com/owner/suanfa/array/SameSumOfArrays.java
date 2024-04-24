package com.owner.suanfa.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 前提：数组中没有重复元素
 * 数组int[] sums中计算两个数之和=sum，求得这些组合
 */
public class SameSumOfArrays {
    public static void main(String[] args) {
        int[] arrays = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        sameSumRecords(arrays, 14);
    }

    /**
     * 数组中计算两个数之和=sum，求得这些组合
     * 两种方式
     * 1、两次for循环
     * 2、一次for循环，借助map匹配
     *
     * @param nums
     * @param sum
     * @return
     */
    public static boolean findSum(int[] nums, int sum) {
        boolean flag = false;
        for (int i = 0; i < nums.length; i++) {
            int m = nums[i];
            int n = sum - m;
            for (int j = i; j < nums.length - i; j++) {
                if (nums[j] == n) {
                    flag = true;
                    System.out.println(nums[i] + "," + nums[j]);
                }
            }
        }
        return flag;
    }

    /**
     * 只用半次循环能不能搞定？
     * 不能，满足条件的两个数字，如果都在数组的左侧或者右侧，通过半次循环拿不到
     * 求得数组中两两相加等于某个数的全部记录
     */
    public static void sameSumRecords(int[] arrays, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arrays.length; i++) {
            int start = arrays[i];
            int startDiff = target - start;
            if (map.containsKey(startDiff)) {
                //展示元素位置
                System.out.println(Arrays.toString(new int[]{i, map.get(startDiff)}));
                //展示元素值
                //System.out.println(start + "," + startDiff);
            } else {
                map.put(arrays[i], i);
            }
        }
    }

}
