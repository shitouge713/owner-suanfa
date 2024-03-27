package com.owner.suanfa.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerCommunication {
    /**
     * 要被锁的对象
     */
    Lock lock = new ReentrantLock();
    /**
     * 消费队列
     */
    Condition consumer = lock.newCondition();
    /**
     * 生产队列
     */
    Condition produce = lock.newCondition();
    /**
     * 共享资源
     */
    static Integer integer = 0;

    public static void main(String[] args) {
        ProducerConsumerCommunication resources = new ProducerConsumerCommunication();
        Thread t1 = new Thread(new ConditionProduce(resources));
        Thread t2 = new Thread(new ConditionConsumer(resources));
        //Thread t3 = new Thread(new ConditionConsumer01(resources));
        //Thread t4 = new Thread(new ConditionConsumer02(resources));
        //Thread t5 = new Thread(new ConditionProduce01(resources));
        t1.start();
        t2.start();
        //t3.start();
        //t4.start();
        //t5.start();
    }
}
