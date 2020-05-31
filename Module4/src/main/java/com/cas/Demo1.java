package com.cas;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @author LiXingyu
 * @date 2020-05-22 2:23 下午
 */
public class Demo1 {
    long perSize;
    RandomAccessFile readFile;
    File file;

    public Demo1(File file) {
        this.file = file;
        try {
            this.readFile = new RandomAccessFile(file, "r");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        this.perSize = file.length() / 3;
    }

    /**
     *
     */
    void readPoemFile() {
        try {
            read(0, perSize, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void read(long start, long end, int round) throws IOException {

        if (start >= file.length()) {
            return;
        }
        if (end >= file.length()) {
            byte[] buffers = new byte[(int) (file.length() - start)];
            readFile.seek(start);
            readFile.read(buffers);
            System.out.println("round:" + round + "-" + new String(buffers, "GB18030"));
        } else {
            readFile.seek(end);
            while (readFile.read() != "\n".getBytes()[0]) {
                end++;
                readFile.seek(end);
            }

            byte[] buffers = new byte[(int) (end - start)];
            readFile.seek(start);
            readFile.read(buffers);
            System.out.println("round:" + round + "-" + new String(buffers, "GB18030"));
            read(end + 1, end + 1 + perSize, ++round);
        }
    }

    public static void main(String[] args) throws IOException {
        File file = new File("/Users/lixingyu/Desktop/spring.txt");
        Demo1 demo1 = new Demo1(file);
        demo1.readPoemFile();

    }

}
