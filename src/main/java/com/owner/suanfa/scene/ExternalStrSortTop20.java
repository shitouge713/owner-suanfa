package com.owner.suanfa.scene;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.*;

/**
 * 1GB的单词文件，怎么只用1M的内存找出出现次数TOP20的词
 * 分块 + 外部排序 + 优先队列 + 大顶堆算法
 */
@Slf4j
public class ExternalStrSortTop20 {

    public static void main(String[] args) throws IOException {
        externalSort("src/main/resources/words.txt", "src/main/resources/sort_words.txt", 1000); // 1000 lines per chunk
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
            String[] dc = line.split(" ");
            for (String str : dc) {
                chunk.add(str);
                //增加到一个文件大小时，创建文件存储，然后清空
                if (chunk.size() == chunkSize) {
                    chunks.add(writeChunkToFile(chunk, page++));
                    chunk.clear();
                }
            }
            line = reader.readLine();
        }
        //如果最后一个小块的大小不足  chunkSize ，仍然需要将这个小块写入临时文件中
        if (!chunk.isEmpty()) {
            chunks.add(writeChunkToFile(chunk, page));
        }
        reader.close();
        //归并排序
        mergeChunks(chunks, outputFilePath, chunkSize);
    }

    /**
     * @param chunk 小块数据
     * @return 小文件名称
     * @throws IOException
     */
    private static String writeChunkToFile(List<String> chunk, int page) throws IOException {
        /**
         * 注意，这里视情况判断是否需要排序
         * 这里的排序也很重要，保证一样的在一块，防止取20单词时，频率都为1造成的单词丢失
         */
        Collections.sort(chunk, String::compareTo);
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
    private static void mergeChunks(List<String> chunks, String outputFile, int chunkSize) {
        //需要保证小文件都是有序的，相同单词都是挨着的
        PriorityQueue<BufferedReader> pq = new PriorityQueue<>((o1, o2) -> {
            try {
                String line1 = o1.readLine();
                String line2 = o2.readLine();
                String cm1 = (line1 != null) ? line1 : "";
                String cm2 = (line2 != null) ? line2 : "";
                return cm1.compareTo(cm2);
            } catch (Exception e) {
                log.error("Error: {}", e);
                return 0; // 根据实际需求返回适当的值
            }
        });
        Map<String, Integer> wordCountMap = new HashMap<>();
        //注意取前20的优先队列，元素存放的是Map.Entry
        PriorityQueue<Map.Entry<String, Integer>> res = new PriorityQueue<>(Comparator.comparingInt(Map.Entry::getValue));
        try {
            // 初始化PriorityQueue
            for (String chunk : chunks) {
                BufferedReader br = new BufferedReader(new FileReader(chunk));
                pq.add(br);
            }
            // 多路归并排序，每次读取一行，放入到优先队列中，队列按map的value从小到大排序，超过20个剔除首个
            while (!pq.isEmpty()) {
                BufferedReader br = pq.poll();
                String line = br.readLine();
                if (line != null) {
                    wordCountMap.put(line, wordCountMap.getOrDefault(line, 0) + 1);
                    /*wordCountMap.put(line, wordCountMap.getOrDefault(line, 0) + 1);
                    //如果队列中已经存在了，删除后重新加入
                    if (res.contains(line)) {
                        res.remove(line);
                    }
                    res.offer(new AbstractMap.SimpleEntry<>(line, wordCountMap.get(line)));*/
                    // 维护出现次数最多的20个单词
                    if (res.size() > 20) {
                        res.poll();
                    }
                    pq.add(br);
                } else {
                    br.close();
                }
            }
        } catch (Exception e) {
            log.error("fatalError:{}", e);
        }
        // 输出出现次数最多的20个单词
        List<Map.Entry<String, Integer>> topWords = new ArrayList<>();
        while (!res.isEmpty()) {
            topWords.add(res.poll());
        }
        //排序处理
        Collections.reverse(topWords);
        System.out.println("Top 20 Elements::" + topWords);
        //写入到排序后的文件
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {
            // 初始化PriorityQueue
            for (Map.Entry<String, Integer> chunk : topWords) {
                bw.write(chunk.getKey() + " : " + chunk.getValue() + "次");
                bw.newLine();
            }
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
