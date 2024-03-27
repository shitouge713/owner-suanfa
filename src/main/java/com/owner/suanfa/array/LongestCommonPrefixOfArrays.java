package com.owner.suanfa.array;

/**
 * 一个字符串数组，求字符串的最长公共前缀
 * 思路：
 * 它将数组中的第一个字符串作为初始的最长公共前缀，并与数组中的其他字符串逐一比较，
 * 找到两个字符串的公共前缀。这个过程会持续到遍历完数组或者公共前缀长度减少到0,
 * 最终， prefix 变量包含了数组中所有字符串的最长公共前缀
 */
public class LongestCommonPrefixOfArrays {
    public static void main(String[] args) {
        String[] strs = {"flower", "flow", "flight"};
        System.out.println("最长公共前缀是: " + longestCommonPrefix(strs));
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
