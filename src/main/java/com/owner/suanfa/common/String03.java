package com.owner.suanfa.common;


/**
 * 判断一个数是不是回数
 * 方案1：从两边向中间扫
 * 1、如果存在不同的跳出
 * 2、当head的位置大于等于tail的位置时跳出
 * 3、满足条件，head++，tail--
 *
 * @return
 */
public class String03 {
    public static void main(String[] args) {
        String str = "123454321";
        boolean flag = false;
        int head = 0;
        int tail = str.length() - 1;
        if (str.length() < 1) {
            return;
        }
        while (head < tail) {
            if (str.charAt(head++) != str.charAt(tail--)) {
                flag = false;
                break;
            }
            flag = true;
        }
        System.out.println(flag);
    }


    public static boolean method(String str) {
        boolean flag = false;
        int head = 0;
        int tail = str.length() - 1;
        if (str.length() < 1) {
            return flag;
        }
        while (head < tail) {
            if (str.charAt(head++) != str.charAt(tail--)) {
                flag = false;
                break;
            }
            flag = true;
        }
        return flag;
    }
}
