package Chapter18;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * @author Evan
 * @date 2018/09/23 09:06
 */
public class IOTest3 {
    private static final int LENGTH = 0x1FFFF;

    public static void main(String[] args) throws IOException {
        MappedByteBuffer mapOut = new RandomAccessFile(
                "src/Chapter18/Resources3/text.dat", "rw").
                getChannel().map(FileChannel.MapMode.READ_WRITE, 0, LENGTH);
        for (int i = 0; i < LENGTH; i++) {
            mapOut.put((byte) 'x');
        }
        for (int i = LENGTH / 2; i < LENGTH / 2 + 6; i++) {
            System.out.print(((char)mapOut.get(i)) + "  ");
        }
        System.out.println("\n-----------------------------------------------");

        try (BufferedOutputStream bos = new BufferedOutputStream(new GZIPOutputStream(
                new FileOutputStream("src/Chapter18/Resources3/test.gz")))) {
            bos.write("Hello, this is a test GZIP file.\n你好，这是一个测试压缩文件。".getBytes());
        }

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new GZIPInputStream(
                new FileInputStream("src/Chapter18/Resources3/test.gz"))))) {
            String s;
            while ((s = br.readLine()) != null) {
                System.out.println(s);
            }
        }
    }
}
