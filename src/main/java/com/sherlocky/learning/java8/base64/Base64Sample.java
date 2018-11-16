package com.sherlocky.learning.java8.base64;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.UUID;
import java.util.stream.IntStream;

/**
 * 在Java 8中，Base64编码已经成为Java类库的标准，内置了 Base64 编码的编码器和解码器。
 *
 * Base64工具类提供了一套静态方法获取下面三种BASE64编解码器：
 *
 * - 1.基本：输出被映射到一组字符A-Za-z0-9+/，编码不添加任何行标，输出的解码仅支持A-Za-z0-9+/。
 * - 2.URL：输出映射到一组字符A-Za-z0-9+_，输出是URL和文件。
 * - 3.MIME：输出隐射到MIME友好格式。输出每行不超过76字符，并且使用'\r'并跟随'\n'作为分割。编码输出最后没有行分割
 *
 * @author: zhangcx
 * @date: 2018/11/16 10:32
 */
public class Base64Sample {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String defaultCharset = "utf-8";
        // 1.使用基本编码
        String srcString = "runasone&huojian";
        System.out.println("原始字符串: " + srcString);
        String base64encodedString = Base64.getEncoder().encodeToString(srcString.getBytes(defaultCharset));
        System.out.println("Base64 编码字符串 (基本) :" + base64encodedString);
        // 解码
        byte[] base64decodedBytes = Base64.getDecoder().decode(base64encodedString);
        System.out.println("解码得到原始字符串:  " + new String(base64decodedBytes, defaultCharset));

        // 2.MIME 编码
        StringBuilder s = new StringBuilder();
        // (0, 10) 下标不包含10; 相当于 for (int i=0; i<10; i++)
        IntStream.range(0, 10).forEach(i -> s.append(UUID.randomUUID().toString().replaceAll("-", "")).append("-"));
        System.out.println("原始字符串: " + s);
        byte[] mimieSrcBytes = s.toString().getBytes(defaultCharset);
        String mimeEncodedString = Base64.getMimeEncoder().encodeToString(mimieSrcBytes);
        System.out.println("Base64 编码字符串 (MIME) :" + mimeEncodedString);
        // 解码
        byte[] mimeDecoedBytes = Base64.getMimeDecoder().decode(mimeEncodedString);
        System.out.println("解码得到原始字符串:  " + new String(mimeDecoedBytes, defaultCharset));
    }
}
