package com.owner.suanfa.string;

/**
 * 一个整数，它加上 100 后是一个完全平方数，加上 168 又是一个完全平方数，请问该数是多少？
 */
public class SquareNumbers {

    public static void main(String[] args) {
        squareNumbers();
    }

    public static void squareNumbers() {
        for (int i = 1; i < 10000000; i++) {
            if (Math.sqrt(i + 100) % 1 == 0) {
                if (Math.sqrt(i + 168) % 1 == 0) {
                    System.out.println(i);
                }
            }
        }
    }
}
