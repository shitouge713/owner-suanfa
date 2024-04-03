package com.owner.suanfa.scene;

import java.io.FileWriter;
import java.util.Random;

public class WordGenerator {
    public static void main(String[] args) {
        String[] words = {"apple", "banana", "cherry", "date", "elderberry", "fig", "grape", "honeydew", "kiwi", "lemon"};
        try {
            FileWriter writer = new FileWriter("src/main/resources/words.txt");
            Random random = new Random();
            for (int i = 0; i < 20; i++) {
                StringBuilder line = new StringBuilder();
                for (int j = 0; j < 10; j++) {
                    Integer sj = Math.abs(random.nextInt(1000));
                    line.append(words[random.nextInt(words.length)]+sj).append(" ");
                }
                writer.write(line.toString().trim() + "\n");
            }
            writer.close();
            System.out.println("文件生成完成。");
        } catch (Exception e) {
            System.out.println("写入文件时出现错误。");
            e.printStackTrace();
        }
    }
}
