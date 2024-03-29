package com.owner.suanfa.array;

import java.util.*;

/**
 * 有⼀些词，需要将词中的字⺟出现位置不同但是出现次数相同的词分为⼀组
 * ⽐如eat，ccok,ate,cock会被划分成两组，分别是[eat,ate],[ccok,cock]
 * 思想：
 * 将词拆成char，排序后生成key
 * 相同key的放在同一个list中
 */
public class WordGrouping {
    public static void main(String[] args) {
        String[] words = {"eat", "ccok", "ate", "eta", "cock", "ccko"};
        Map<String, List<String>> groupedWords = groupWords(words);
        for (List<String> group : groupedWords.values()) {
            System.out.println(group);
        }
    }

    private static Map<String, List<String>> groupWords(String[] words) {
        Map<String, List<String>> map = new HashMap<>();
        for (String word : words) {
            // 将单词转换成字符数组，然后排序，最后再转换回字符串，作为键
            char[] chars = word.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            map.putIfAbsent(key, new ArrayList<>());
            map.get(key).add(word);
        }
        return map;
    }
}
