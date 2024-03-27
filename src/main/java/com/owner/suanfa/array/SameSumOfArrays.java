package com.owner.suanfa.array;

import java.util.*;


public class SameSumOfArrays {
    public static void main(String[] args) {
        int[] arrays = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        findSum(arrays,14);
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
     * 求得数组中两两相加等于某个数的全部记录
     */
    public static void sameSumRecords(int[] arrays, int target) {
        //List<Object> list = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arrays.length; i++) {
            int a = target - arrays[i];
            if (map.containsKey(a)) {
                System.out.println(Arrays.toString(new int[]{i, map.get(a)}));
            } else {
                map.put(arrays[i], i);
            }
        }
    }

}
