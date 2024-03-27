package com.owner.suanfa.thread;

import java.util.concurrent.TimeUnit;

public class ConditionConsumer01 implements  Runnable{
    ProducerConsumerCommunication producerConsumerCommunication;
    public ConditionConsumer01(ProducerConsumerCommunication producerConsumerCommunication){
        this.producerConsumerCommunication = producerConsumerCommunication;
    }
    @Override
    public void run(){
        while(true){
            producerConsumerCommunication.lock.lock();
            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while(producerConsumerCommunication.integer<=0){
                try {
                    System.out.println("00---没有资源，需要等待...");
                    producerConsumerCommunication.consumer.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            producerConsumerCommunication.integer--;
            System.out.println("00---获取资源，执行消费..."+ producerConsumerCommunication.integer);
            producerConsumerCommunication.produce.signalAll();
            producerConsumerCommunication.lock.unlock();
        }
    }
}