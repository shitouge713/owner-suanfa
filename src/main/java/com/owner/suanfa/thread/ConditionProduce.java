package com.owner.suanfa.thread;

import java.util.concurrent.TimeUnit;

public class ConditionProduce implements Runnable {

    ProducerConsumerCommunication producerConsumerCommunication;

    public ConditionProduce(ProducerConsumerCommunication producerConsumerCommunication) {
        this.producerConsumerCommunication = producerConsumerCommunication;
    }

    @Override
    public void run() {
        while (true) {
            producerConsumerCommunication.lock.lock();
            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while (producerConsumerCommunication.integer >= 5) {
                try {
                    System.out.println("00---资源已满，需要等待...");
                    producerConsumerCommunication.produce.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //生产过程
            System.out.println("00---有线程消费，可以生产...");
            producerConsumerCommunication.integer++;
            producerConsumerCommunication.consumer.signalAll();
            producerConsumerCommunication.lock.unlock();
        }
    }
}
