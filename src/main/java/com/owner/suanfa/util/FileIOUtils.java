package com.owner.suanfa.util;

import java.io.*;

/**
 * @作者 : sxl
 */
public class FileIOUtils {
    // 拿到指定文件的输入流，并包装成文件 BufferedReader
    public static BufferedReader getReader(String name) {
        try {
            FileInputStream inputStream = new FileInputStream(name);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            return br;
        } catch (IOException e) {
            throw new RuntimeException("IOException", e);
        }
    }

    // 拿到指定文件的输出流，并包装成文件 BufferedWriter
    public static BufferedWriter getWriter(String name) {
        try {
            FileOutputStream outputStream = new FileOutputStream(name);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(outputStream));
            return bw;
        } catch (IOException e) {
            throw new RuntimeException("IOException", e);
        }
    }

    public static void closeReader(Reader reader) {
        try {
            if (reader != null) {
                reader.close();
            }
        } catch (IOException e) {
            throw new RuntimeException("IOException", e);
        }
    }

    public static void closeWriter(Writer writer) {
        try {
            if (writer != null) {
                writer.close();
            }
        } catch (IOException e) {
            throw new RuntimeException("IOException", e);
        }
    }
}
