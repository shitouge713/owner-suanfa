package com.owner.suanfa.string;

import java.util.HashMap;
import java.util.Map;

/**
 * 寻找字符串中不含有重复字符的最长字串
 * 方案1：时间复杂度O（n）
 * 滑动窗口
 * hashMap中存放滑动窗口tail，key为实际字符，value为字符在字符串中的位置
 * 滑动窗口起始都是0，左闭右开
 * tail每移动一个位置
 * 1、判断新的位置的元素在前面的字符串中是否存在
 * 2、如果存在，将滑动窗口head移动到重复字符左边的下一位，即map中key为字符的value值
 * 1.2、结果长度+1,比较和原长度的大小，取最大值
 * 1.3、将元素放入到map中,key为char，value为char在字符串中的位置
 *
 * @return
 */
public class WithoutRepeatingChar {
    public static void main(String[] args) {
        String str = "aaabbb";
        withoutRepeatingCharMethod(str);
    }

    public static void withoutRepeatingCharMethod(String str) {
        Map<Character, Integer> hash = new HashMap<>();
        int result = 0;
        int lastPosition = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (hash.containsKey(c)) {
                lastPosition = hash.get(c);
            }
            //判断result，需要放在外面
            result = Math.max(result, i - lastPosition);
            hash.put(c, i);
        }
        System.out.println(result);
    }
}
