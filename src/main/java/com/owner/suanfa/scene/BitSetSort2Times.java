package com.owner.suanfa.scene;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * 使用位图统计数字出现次数超过2次的记录集合
 */
@Slf4j
public class BitSetSort2Times {

    public static void main(String[] args) throws IOException {
        externalSort("src/main/resources/number.txt"); // 1000 lines per chunk
    }

    /**
     * @param inputFilePath 输入文件
     * @throws IOException
     */
    public static void externalSort(String inputFilePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
        String line = reader.readLine();
        CustomBitSet bitSet = new CustomBitSet(Integer.MAX_VALUE / 2);
        while (line != null) {
            bitSet.set(Integer.parseInt(line));
            line = reader.readLine();
        }
        BufferedReader reader2 = new BufferedReader(new FileReader(inputFilePath));
        String line2 = reader2.readLine();
        while (line2 != null) {
            int i = Integer.parseInt(line2);
            if (bitSet.isGreaterThanTwo(i)) {
                log.info(i + "出现大于等于2次");
            }
            line2 = reader2.readLine();
        }

    }
}
