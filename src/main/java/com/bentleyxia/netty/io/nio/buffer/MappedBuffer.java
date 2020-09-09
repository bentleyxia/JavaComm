package com.bentleyxia.netty.io.nio.buffer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

public class MappedBuffer {
    static private final int start = 0;
    static private final int end = 1024;

    public static void main(String[] args) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("src/main/resources/log.txt","rw");
        FileChannel channel = randomAccessFile.getChannel();

        // buffer attach filesystem
        MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_WRITE, start, end);

        map.put(0, (byte)97);
        map.put(1023, (byte)122);

        channel.close();

        Charset charset = Charset.forName("utf-8");
        System.out.println(charset.decode(map));
    }
}
