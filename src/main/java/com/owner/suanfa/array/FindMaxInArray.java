package com.owner.suanfa.array;

import java.util.HashMap;
import java.util.Map;

/**
 * 一个数组，里面全是数字, 从左致右的特征是:从小到大，然后从大到小，数组中可能会出现连续重复的数字
 * 请写一段代码<最快>找出数组中最大的数值(最大值可能为1到多个)，输出最大数字和个数
 * 比如数组 [1,3,3,3,4,5,6,7,8,8,8,8,8,9,10,10,11,11,12,23,33,33,10,2,2,2,2,2,2,1,1,1]
 * 疑问：
 * 如果可以重复，二分查找到中间重复数字后，无法判断应该向左还是向右
 * 如果不重复，二分查找确实快
 */
public class FindMaxInArray {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 11, 11, 4, 4, 4, 4, 4, 3, 2, 1};
        int[] arr1 = new int[]{1, 11, 11, 11, 4, 4, 4, 4, 4, 3, 2, 1};
        int[] arr2 = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 1};
        findByStart(arr);
        findByStart(arr1);
        findPeakElement(arr2);
    }

    /**
     * 方式一：
     * 从左到右依次判断
     *
     * @param arr
     */
    public static void findByStart(int[] arr) {
        int start = 0;
        int end = arr.length - 1;
        boolean rightFlag = false;
        boolean leftFlag = false;
        int large = -1;
        Map<Integer, Integer> hash = new HashMap<>();
        while (start < end) {
            if (arr[start] < arr[end]) {
                if (arr[end] > large) {
                    large = arr[end];
                } else {
                    rightFlag = true;
                }
            } else if (arr[start] > arr[end]) {
                if (arr[start] > large) {
                    large = arr[start];
                } else {
                    leftFlag = true;
                }
            }
            if (rightFlag && leftFlag) {
                break;
            }
            add(hash, arr[start]);
            add(hash, arr[end]);
            start++;
            end--;
        }
        System.out.println("最大值：" + large + ",次数：" + hash.get(large));
    }

    public static void add(Map<Integer, Integer> hash, Integer num) {
        if (null == hash.get(num)) {
            hash.put(num, 1);
        } else {
            hash.put(num, hash.get(num) + 1);
        }
    }

    /**
     * 方式二
     *
     * @param nums
     */
    public static void findPeakElement(int[] nums) {
        int left = 0, right = nums.length - 1;
        // 二分查找最大值，当left=right时，跳出循环
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < nums[mid + 1]) {
                // 如果mid元素小于mid+1元素，则最大值一定在右侧
                left = mid + 1;
            } else if (nums[mid] > nums[mid + 1]) {
                // 如果mid元素大于mid+1元素，最大值一定在左侧
                right = mid;
            }
        }
        // left就是最大值的位置，下面计算这个最大值出现的次数
        int max = nums[left];
        int count = 1; // 最大值至少出现一次
        // 向左扫描
        int i = left - 1;
        while (i >= 0 && nums[i] == max) {
            count++;
            i--;
        }
        // 向右扫描
        i = left + 1;
        while (i < nums.length && nums[i] == max) {
            count++;
            i++;
        }
        System.out.println("最大值: " + max + ", 出现次数: " + count);
    }
}
