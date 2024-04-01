package com.owner.suanfa.scene;

import java.io.*;
import java.util.*;

/**
 * 如何使用外部排序算法对1GB数据进行排序，通过分割数据、排序小块、归并排序等步骤来实现在内存受限的情况下对大数据集的排序
 */
public class ExternalSort {
    public static void main(String[] args) throws IOException {
        externalSort("src/main/resources/input.txt", "src/main/resources/output.txt", 100000); // 100000 lines per chunk
    }

    public static void externalSort(String inputFilePath, String outputFilePath, int chunkSize) throws IOException {
        List<String> chunk = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
        String line = reader.readLine();
        while (line != null) {
            chunk.add(line);
            if (chunk.size() == chunkSize) {
                Collections.sort(chunk);
                writeChunkToFile(chunk, outputFilePath);
                chunk.clear();
            }
            line = reader.readLine();
        }
        if (!chunk.isEmpty()) {
            Collections.sort(chunk);
            writeChunkToFile(chunk, outputFilePath);
        }
        reader.close();
        mergeChunks(outputFilePath, chunkSize);
    }

    private static void writeChunkToFile(List<String> chunk, String filePath) throws IOException {
        Collections.sort(chunk);
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));
        for (String line : chunk) {
            writer.write(line);
            writer.newLine();
        }
        writer.close();
    }

    private static void mergeChunks(String outputFilePath, int chunkSize) throws IOException {
        List<BufferedReader> readers = new ArrayList<>();
        for (int i = 0; i < chunkSize; i++) {
            File file = new File(outputFilePath + "_" + i);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileReader fileReader = new FileReader(file);
            readers.add(new BufferedReader(fileReader));
        }
        PriorityQueue<String> queue = new PriorityQueue<>();
        for (BufferedReader reader : readers) {
            String line = reader.readLine();
            if (line != null) {
                queue.add(line);
            }
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath));
        while (!queue.isEmpty()) {
            String line = queue.poll();
            writer.write(line);
            writer.newLine();
            int readerIndex = queue.size() % chunkSize;
            String nextLine = readers.get(readerIndex).readLine();
            if (nextLine != null) {
                queue.add(nextLine);
            }
        }
        writer.close();
        for (BufferedReader reader : readers) {
            reader.close();
        }
    }
}
