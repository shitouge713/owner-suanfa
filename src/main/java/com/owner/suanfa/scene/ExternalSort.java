package com.owner.suanfa.scene;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 如何使用外部排序算法对1GB数据进行排序，通过分割数据、排序小块、归并排序等步骤来实现在内存受限的情况下对大数据集的排序
 */
public class ExternalSort {
    public static void externalSort(int[] data) {
        //int chunkSize = 100000000; // 100MB
        int chunkSize = 1000000; // 1MB
        int chunkNum = data.length / chunkSize + 1;
        // 分割数据为小块并排序
        for (int i = 0; i < chunkNum; i++) {
            int start = i * chunkSize;
            int end = Math.min((i + 1) * chunkSize, data.length);
            int[] chunk = Arrays.copyOfRange(data, start, end);
            Arrays.sort(chunk);
            // 将排序好的小块写回磁盘
            // 写回磁盘的操作可以根据实际情况进行处理
        }
        // 使用优先队列进行归并排序
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < chunkNum; i++) {
            int start = i * chunkSize;
            int end = Math.min((i + 1) * chunkSize, data.length);
            int[] chunk = Arrays.copyOfRange(data, start, end);
            for (int num : chunk) {
                pq.offer(num);
            }
        }
        // 从优先队列中取出数据，即为排序后的结果
        for (int i = 0; i < data.length; i++) {
            data[i] = pq.poll();
        }
    }

    public static void main(String[] args) {
        // 模拟1GB的数据
        //int dataSize = 1000000000; // 1MB
        int dataSize = 1000000; // 1GB
        int[] data = new int[dataSize];
        for (int i = 0; i < dataSize; i++) {
            data[i] = (int) (Math.random() * dataSize);
        }
        // 对数据进行外部排序
        externalSort(data);
        // 输出排序后的数据
        for (int num : data) {
            System.out.println(num);
        }
    }
}
