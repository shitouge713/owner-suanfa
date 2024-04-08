package com.owner.suanfa.scene;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.*;

/**
 * 使用bitset对数字排序
 */
@Slf4j
public class BitSetSort {


    public static void main(String[] args) throws IOException {
        externalSort("src/main/resources/number.txt", "src/main/resources/numberSort.txt", 1000); // 1000 lines per chunk
    }

    /**
     * @param inputFilePath  输入文件
     * @param outputFilePath 输出文件
     * @param chunkSize      块大小
     * @throws IOException
     */
    public static void externalSort(String inputFilePath, String outputFilePath, int chunkSize) throws IOException {
        //每个文件内容
        List<String> chunk = new ArrayList<>();
        //文件集合
        List<String> chunks = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
        String line = reader.readLine();
        int page = 1;
        while (line != null) {
            chunk.add(line);
            //增加到一个文件大小时，创建文件存储，然后清空
            if (chunk.size() == chunkSize) {
                chunks.add(writeChunkToFile(chunk, page++));
                chunk.clear();
            }
            line = reader.readLine();
        }
        //如果最后一个小块的大小不足  chunkSize ，仍然需要将这个小块写入临时文件中
        if (!chunk.isEmpty()) {
            chunks.add(writeChunkToFile(chunk, page));
        }
        reader.close();
        //归并排序
        mergeChunks(chunks, outputFilePath);
    }

    /**
     * 将总文件拆分成多个小文件,每个小文件需排序后写入到外部文件
     *
     * @param chunk 小块数据
     * @return 小文件名称
     * @throws IOException
     */
    private static String writeChunkToFile(List<String> chunk, int page) throws IOException {
        //注意，这里要按Integer排序
        Collections.sort(chunk, (o1, o2) -> {
            Integer num1 = Integer.parseInt(o1);
            Integer num2 = Integer.parseInt(o2);
            return num2.compareTo(num1);
        });
        String filePath = "chunk" + "_" + page + ".txt";
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));
        for (String line : chunk) {
            writer.write(line);
            writer.newLine();
        }
        writer.close();
        return filePath;
    }

    /**
     * @param chunks
     * @param outputFile
     */
    private static void mergeChunks(List<String> chunks, String outputFile) {
        //注意，这里要按Integer排序
        PriorityQueue<BufferedReader> pq = new PriorityQueue<>((o1, o2) -> {
            try {
                String line1 = o1.readLine();
                String line2 = o2.readLine();
                Integer cm1 = (line1 != null) ? Integer.parseInt(line1) : -1;
                Integer cm2 = (line2 != null) ? Integer.parseInt(line2) : -1;
                return cm2.compareTo(cm1);
            } catch (Exception e) {
                log.error("Error: {}", e);
                return 0; // 根据实际需求返回适当的值
            }
        });
        BitSet bitSet = new BitSet();
        try {
            // 初始化PriorityQueue
            for (String chunk : chunks) {
                BufferedReader br = new BufferedReader(new FileReader(chunk));
                pq.add(br);
            }
            // 多路归并排序
            while (!pq.isEmpty()) {
                BufferedReader br = pq.poll();
                String line = br.readLine();
                if (line != null) {
                    Integer num = Integer.parseInt(line);
                    if (!bitSet.get(num)) {
                        //表示将第num位置置为1
                        bitSet.set(num);
                    }
                    pq.add(br);
                } else {
                    br.close();
                }
            }
        } catch (Exception e) {
            log.error("fatalError:{}", e);
        }
        //写入到排序后的文件
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {
            //从大到小输出
            int index = bitSet.length();
            while ((index = bitSet.previousSetBit(index - 1)) != -1) {
                bw.write(String.valueOf(index));
                bw.newLine();
            }
            //从小到大输出
            /*for (int i = 0; i < bitSet.size(); i++) {
                if (bitSet.get(i)) {
                    bw.write(String.valueOf(i));
                    bw.newLine();
                }
            }*/
        } catch (Exception e) {
            log.error("fatalError:{}", e);
        }
        // 删除临时文件
        for (String chunk : chunks) {
            File file = new File(chunk);
            file.delete();
        }
    }
}
