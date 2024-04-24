package com.owner.suanfa.array;

/**
 * 一个字符串数组，求字符串的最长公共前缀
 * 思路：
 * 它将数组中的第一个字符串作为初始的最长公共前缀，并与数组中的其他字符串逐一比较，
 * 如果满足要求，肯定可以在其他字符串中通过indexOf能够获取到且值为0
 * 如果不满足，将初始公共前缀减少一位，再次判断
 * while循环执行这个过程（条件是在其他字符串中通过indexOf能够匹配到公共字符串）
 * 当公共字符串为“”时，退出循环
 * 一次for循环，一次while循环
 */
public class LongestCommonPrefixOfArrays {
    public static void main(String[] args) {
        System.out.println("igflht".indexOf("flower"));
        System.out.println("igflhtflower".indexOf("flower"));
        String[] strs = {"flower", "flow", "flighflowt"};
        System.out.println("最长公共前缀是: " + longestCommonPrefix2(strs));
    }

    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            int length = Math.min(prefix.length(), strs[i].length());
            int index = 0;
            while (index < length && prefix.charAt(index) == strs[i].charAt(index)) {
                index++;
            }
            prefix = prefix.substring(0, index);
            if (prefix.isEmpty()) {
                break;
            }
        }
        return prefix;
    }

    public static String longestCommonPrefix2(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            /**
             * !=0，表示该字符串不是以公共字符串为前缀，需要对公共字符串-1位操作，可以寻找最长公共字符串前缀
             * ==-1，表示该字符串不包含公共字符串，需要对公共字符串-1位操作，可以寻找最长公共字符串
             */
            //while (strs[i].indexOf(prefix) == -1) {
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) {
                    return "";
                }
            }
        }
        return prefix;
    }

}
