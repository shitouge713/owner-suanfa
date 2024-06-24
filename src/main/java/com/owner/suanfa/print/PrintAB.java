package com.owner.suanfa.print;

/**
 * synchronized + wait/notify实现线程间通信
 * 1、循环打印AB
 * 2、循环打印10以内的奇数偶数
 */
public class PrintAB {
    static boolean t1Running = true;
    static boolean t2Running = false;
    //private Integer num = 10;

    public synchronized void printA() {
        while (!t1Running) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //System.out.print(Thread.currentThread().getName()+":"+num-- +"\n");
        System.out.print(Thread.currentThread().getName() + ":A" + "\n");
        t1Running = false;
        t2Running = true;
        this.notify();
    }

    public synchronized void printB() {
        while (!t2Running) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //System.out.print(Thread.currentThread().getName() + ":" + num-- + "\n");
        System.out.print(Thread.currentThread().getName() + ":B" + "\n");
        t1Running = true;
        t2Running = false;
        this.notify();
    }

    public static void main(String[] args) {
        PrintAB ab = new PrintAB();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <5 ; i++) {
                    ab.printA();
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <5 ; i++) {
                    ab.printB();
                }
            }
        });
        thread1.start();
        thread2.start();
    }
}
