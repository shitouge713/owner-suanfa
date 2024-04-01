package com.owner.suanfa.scene;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * 往文件中写入1GB的数据整数
 */
public class WriteIntegersToFile {
    public static void main(String[] args) {
        String filePath = "src/main/resources/input.txt";
        long fileSize = 1024 * 1024 * 10; // 1MB
        int bufferSize = 1024 ; // 1MB buffer

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            Random random = new Random();
            long bytesWritten = 0;
            while (bytesWritten < fileSize) {
                int number = random.nextInt();
                writer.write(Integer.toString(number));
                writer.newLine();
                bytesWritten += 1; // Integer takes 4 bytes
                if (bytesWritten % bufferSize == 0) {
                    writer.flush();
                }
            }
            writer.close();
            System.out.println("File created with 1GB of random integers.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
