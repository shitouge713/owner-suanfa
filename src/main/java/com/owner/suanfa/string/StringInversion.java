package com.owner.suanfa.string;

import java.util.ArrayList;
import java.util.List;

/**
 * 字符串的反转/数字的反转
 */
public class StringInversion {
    public static void main(String[] args) {
        method3("123213123123");
    }

    /**
     * 借助char实现字符串反转
     *
     * @param numStr
     */
    public static void method1(String numStr) {
        char[] arr = numStr.toCharArray();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            builder.append(arr[arr.length - 1 - i]);
        }
        System.out.println(builder.toString());
    }

    /**
     * 借助char实现字符串反转
     * 只需用半次for循环
     * @param numStr
     */
    public static void method2(String numStr) {
        char[] arr = numStr.toCharArray();
        char[] newArr = new char[arr.length];
        for (int i = 0; i < arr.length/2; i++) {
            newArr[i] = arr[arr.length-1-i];
            newArr[arr.length-1-i] = arr[i];
        }
        System.out.println(new String(newArr));
    }

    /**
     * 借助异或操作实现字符串的反转
     * 三次异或操作可以实现字符的位置互换
     *
     * @param numStr
     */
    public static void method3(String numStr) {
        char[] arr = numStr.toCharArray();
        int start = 0;
        int end = arr.length - 1;
        while (start < end) {
            arr[start] ^= arr[end];
            arr[end] ^= arr[start];
            arr[start] ^= arr[end];
            start++;
            end--;
        }
        System.out.println(arr);
    }
}
