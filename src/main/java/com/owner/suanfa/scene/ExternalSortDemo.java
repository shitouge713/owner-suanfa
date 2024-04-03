package com.owner.suanfa.scene;

import java.util.PriorityQueue;
import java.util.Random;

/**
 * 演示优先队列PriorityQueue用法
 */
public class ExternalSortDemo {

    public static void main(String[] args) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Integer::compareTo);
        System.out.println("插入的数据");
        //随机添加两位数
        for (int i = 0; i < 10; i++) {
            Integer num = new Random().nextInt(90) + 10;
            System.out.print(num + ",");
            pq.offer(num);
        }
        System.out.println("\n输出后的数据");
        while (true) {
            Integer result = pq.poll();
            if (result == null) break;
            System.out.print(result + ",");
        }
    }
}
