package com.owner.suanfa.scene;

import com.owner.suanfa.util.FileIOUtils;

import java.io.BufferedWriter;
import java.util.Random;

public class NumberGenerator {
    private static Random r = new Random();

    public static void main(String[] args) {
        try {
            BufferedWriter writer = FileIOUtils.getWriter("src/main/resources/number.txt");
            char[] chars = {'1', '2', '3', '4', '5', '6', '7','8', '9', '0'};
            int m = chars.length;
            for (int i = 0; i < 1000; i++) {
                StringBuilder line = new StringBuilder();
                for (int j = 0; j < r.nextInt(11); j++) {
                    line.append(chars[r.nextInt(m)]);
                }
                if (line.length() == 0) continue;
                writer.write(line.toString());
                writer.newLine();
            }
            FileIOUtils.closeWriter(writer);
            System.out.println("文件生成完成。");
        } catch (Exception e) {
            System.out.println("写入文件时出现错误。");
            e.printStackTrace();
        }
    }
}
