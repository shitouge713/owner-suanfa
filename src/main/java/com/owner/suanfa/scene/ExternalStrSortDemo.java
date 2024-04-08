package com.owner.suanfa.scene;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * 大顶堆、小顶堆算法示例
 * 演示优先队列PriorityQueue用法
 */
public class ExternalStrSortDemo {

    public static void main(String[] args) {
        // 创建大顶堆
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        // 创建小顶堆
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        // 向大顶堆和小顶堆中插入元素
        int[] nums = {10, 30, 20, 50, 40};
        for (int num : nums) {
            maxHeap.add(num);
            minHeap.add(num);
        }
        // 打印大顶堆中的元素
        System.out.println("Elements in max heap:");
        while (!maxHeap.isEmpty()) {
            System.out.println(maxHeap.poll());
        }
        System.out.println();
        // 打印小顶堆中的元素
        System.out.println("Elements in min heap:");
        while (!minHeap.isEmpty()) {
            System.out.println(minHeap.poll());
        }
    }
}
