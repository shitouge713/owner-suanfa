package com.owner.suanfa.scene;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

@Slf4j
public class ExternalNumberSortTop20 {


    public static void main(String[] args) throws IOException {
        externalSort("src/main/resources/number.txt",
                "src/main/resources/tempSort",
                "src/main/resources/tempSort_pc.txt",
                "src/main/resources/numberSort.txt",
                2); // 1000 lines per chunk
    }

    /**
     * @param inputFilePath 输入文件
     * @param tempSortPath  临时排序后的文件
     * @param tempPcPath    临时统计次数文件
     * @param chunkSize     块大小
     * @throws IOException
     */
    public static void externalSort(String inputFilePath, String tempSortPath, String tempPcPath, String outputPath, int chunkSize) throws IOException {
        //文件集合
        BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
        String line = reader.readLine();
        while (line != null) {
            //大文件拆成多个小文件
            writeChunkToFile(chunkSize, line, tempSortPath);
            line = reader.readLine();
        }
        reader.close();
        //小文件求频次，tempPcPath中保存的是 单词:次数
        for (int i = 0; i < chunkSize ; i++) {
            String filePath = tempSortPath + "_" + i + ".txt";
            countNumberTimes(filePath, tempPcPath);
        }
        //归并排序
        mergeChunks(tempPcPath, outputPath);
        // 删除临时文件
        for (int i = 0; i < chunkSize; i++) {
            File file = new File(tempSortPath + "_" + i + ".txt");
            file.delete();
        }
        File file = new File(tempPcPath);
        file.delete();
    }


    /**
     * 将总文件拆分成多个小文件,每个小文件需排序后写入到外部文件
     * 之所以求余，是为了使相同的数字保存在同一个文件中
     *
     * @param context 小块数据
     * @return 小文件名称
     * @throws IOException
     */
    private static void writeChunkToFile(Integer chunkSize, String context, String tempSortFile) throws IOException {
        //注意，这里要按Integer排序
        Integer size = Math.abs(context.hashCode() % chunkSize);
        String filePath = tempSortFile + "_" + size + ".txt";
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));
        writer.write(context);
        writer.newLine();
        writer.close();
    }

    /**
     * 单词频次写入新的文件
     */
    private static void countNumberTimes(String tempSortFile, String tempPcFile) {
        try {
            // 初始化PriorityQueue
            PriorityQueue<BufferedReader> pq = new PriorityQueue<>();
            BufferedReader br = new BufferedReader(new FileReader(tempSortFile));
            pq.add(br);
            // 统计每个文件中单词的频次
            Map<String, Integer> wordCount = new HashMap<>();
            String line = br.readLine();
            while (line != null) {
                wordCount.put(line, wordCount.getOrDefault(line, 0) + 1);
                line = br.readLine();
            }
            br.close();
            for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
                //写入到排序后的文件
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(tempPcFile, true))) {
                    bw.write(entry.getKey() + ":" + entry.getValue());
                    bw.newLine();
                } catch (Exception e) {
                    log.error("fatalError:{}", e);
                }
            }
        } catch (Exception e) {
            log.error("fatalError:{}", e);
        }
    }

    /**
     * @param tempPcFile
     * @param outputPath
     */
    private static void mergeChunks(String tempPcFile, String outputPath) {
        //注意，这里要按Integer排序
        PriorityQueue<BufferedReader> pq = new PriorityQueue<>(20, (o1, o2) -> {
            try {
                String line1 = o1.readLine();
                String line2 = o2.readLine();
                String[] arr1 = (line1 != null) ? line1.split(":") : null;
                String[] arr2 = (line2 != null) ? line2.split(":") : null;
                Integer cm1 = (arr1 != null) ? Integer.parseInt(arr1[1]) : -1;
                Integer cm2 = (arr2 != null) ? Integer.parseInt(arr1[1]) : -1;
                return cm2.compareTo(cm1);
            } catch (Exception e) {
                log.error("Error: {}", e);
                return 0; // 根据实际需求返回适当的值
            }
        });
        //写入到排序后的文件
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputPath))) {
            // 初始化PriorityQueue
            BufferedReader br = new BufferedReader(new FileReader(tempPcFile));
            pq.add(br);
            // 多路归并排序
            String line = br.readLine();
            while (!StringUtils.isEmpty(line)) {
                bw.write(line);
                bw.newLine();
                line = br.readLine();
            }
            br.close();
        } catch (Exception e) {
            log.error("fatalError:{}", e);
        }
    }
}
