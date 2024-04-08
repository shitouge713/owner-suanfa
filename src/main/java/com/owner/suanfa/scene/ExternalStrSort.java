package com.owner.suanfa.scene;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 分块处理 + 外排序 + 多路归并排序
 * 如何使用外部排序算法对1GB数据进行排序，通过分割数据、排序小块、归并排序等步骤来实现在内存受限的情况下对大数据集的排序
 * 整体流程为：读取数据 -> 分割小块 -> 排序小块 -> 归并排序 -> 输出有序文件
 * 1. **外部排序算法实现**：在 externalSort 方法中，首先从输入文件中读取数据，然后按照指定的块大小将数据分割成小块，对每个小块进行排序，
 * 并将排序好的小块写入临时文件中。最后，调用 mergeChunks 方法对所有小块进行归并排序。
 * 2. **拆分数据**： writeChunkToFile 方法将每个小块数据进行排序，并写入临时文件中。
 * 3. **归并排序**： mergeChunks 方法使用优先队列（PriorityQueue）实现多路归并排序。首先将所有小块的第一行数据读入优先队列，并根据大小排序。
 * 然后逐步从优先队列中取出最小的行，写入输出文件，并读取下一行替换原来的行，直到所有数据排序完成。最后删除临时文件
 * PriorityQueue：
 * Java中的PriorityQueue是一个优先级队列
 * 它会根据元素的自然顺序或者通过提供的Comparator来自动对元素进行排序。
 * 当您向PriorityQueue中添加元素时，它会根据元素的排序规则将元素放置在适当的位置，以确保队列中的元素始终保持有序状态。
 */
@Slf4j
public class ExternalStrSort {
    public static void main(String[] args) throws IOException {
        externalSort("src/main/resources/input.txt", "src/main/resources/output.txt", 1000); // 1000 lines per chunk
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
            return num1.compareTo(num2);
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
     * 使用了优先队列（PriorityQueue）来实现多路归并排序
     * 首先，我们将小文件加入到优先队列中，自定义排序规则
     * 然后，将所有块的第一行读入优先队列，优先队列会根据自定义规则排序
     * 然后，我们逐步从优先队列中取值，因为前面已保证排过序了，写入到输出文件，写入后读取下一行替换原来的行
     * 这样就实现了多路归并排序
     * 最后，我们删除临时文件
     *
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
                return cm1.compareTo(cm2);
            } catch (Exception e) {
                log.error("Error: {}", e);
                return 0; // 根据实际需求返回适当的值
            }
        });
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {
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
                    bw.write(line);
                    bw.newLine();
                    pq.add(br);
                } else {
                    br.close();
                }
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
