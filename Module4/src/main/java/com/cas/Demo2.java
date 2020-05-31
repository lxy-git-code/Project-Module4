package com.cas;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author LiXingyu
 * @date 2020-05-23 3:55 下午
 */
public class Demo2 {
    public static void main(String[] args) throws IOException {
          //123
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byteArrayOutputStream.write("123456".getBytes());
        byte[] bytes = byteArrayOutputStream.toByteArray();
        System.out.println(new String(bytes));

    }
}
