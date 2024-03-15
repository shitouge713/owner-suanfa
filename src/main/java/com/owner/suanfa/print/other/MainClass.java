package com.owner.suanfa.print.other;

/**
 * 使用生产者消费者模式实现循环打印ABC
 */
public class MainClass {
    public static void main(String[] args) {
        CommonResource resource = new CommonResource();

        ThreadOne one = new ThreadOne(resource);
        Thread t1 = new Thread(one);

        ThreadTwo two = new ThreadTwo(resource);
        Thread t2 = new Thread(two);

        ThreadThree three = new ThreadThree(resource);
        Thread t3 = new Thread(three);

        t1.start();
        t2.start();
        t3.start();

    }
}
