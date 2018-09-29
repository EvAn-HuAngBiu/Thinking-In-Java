package Chapter18;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Evan
 * @date 2018/09/22 20:15
 */
public class IOTest2 {
    public static void main(String[] args) throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        try (FileChannel fileChannel = new FileInputStream("src/Chapter18/Resources2/text.txt").getChannel()) {
            List<Byte> byteList = new ArrayList<>();
            while (fileChannel.read(byteBuffer) != -1) {
                byteBuffer.flip();
                while (byteBuffer.hasRemaining()) {
                    byteList.add(byteBuffer.get());
                }
                byteBuffer.clear();
            }

            byte[] bytes = new byte[byteList.size()];
            for (int i = 0; i < bytes.length; i++) {
                bytes[i] = byteList.get(i);
            }
            System.out.println(new String(bytes, StandardCharsets.UTF_8));
        }


        System.out.println("------------------------------------------");
        // Write and read by CharBuffer
        String text = "This is an example text.\n这是一个实例文本。";
        try (FileChannel fc = new FileOutputStream("src/Chapter18/Resources2/text2.txt").getChannel()) {
            // Must be UTF-16BE, otherwise default CharBuffer will not recognize
            // fc.write(ByteBuffer.wrap(text.getBytes(StandardCharsets.UTF_16BE)));
            fc.write(ByteBuffer.wrap(text.getBytes()));
        }

        try (FileChannel fc = new FileInputStream("src/Chapter18/Resources2/text2.txt").getChannel()) {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            fc.read(buffer);
            buffer.flip();
            // System.out.println(buffer.asCharBuffer());
            // If not UTF-16BE
            System.out.println(Charset.forName("UTF-8").decode(buffer));
        }

        System.out.println("------------------------------------------");
        ByteBuffer basicTypeBuffer = ByteBuffer.allocate(1024);
        basicTypeBuffer.asCharBuffer().put("Bonjour");
        basicTypeBuffer.rewind();
        char c;
        System.out.print("Char = ");
        while ((c = basicTypeBuffer.getChar()) != 0) {
            System.out.print(c);
        }
        System.out.println();

        basicTypeBuffer.rewind();
        basicTypeBuffer.asShortBuffer().put((short) 64);
        System.out.println("Short = " + basicTypeBuffer.getShort());

        basicTypeBuffer.rewind();
        basicTypeBuffer.asIntBuffer().put(99471142);
        System.out.println("Int = " + basicTypeBuffer.getInt());

        basicTypeBuffer.rewind();
        basicTypeBuffer.asDoubleBuffer().put(99471142);
        System.out.println("Double = " + basicTypeBuffer.getDouble());

        System.out.println("------------------------------------------");
        ByteBuffer viewBuffer = ByteBuffer.allocate(1024);
        IntBuffer viewIntBuffer = viewBuffer.asIntBuffer();
        viewIntBuffer.put(new int[]{12, 26, 37, 48, 56, 71, 82});
        System.out.println(viewIntBuffer.get(2));
        viewIntBuffer.put(2, 3700);
        viewIntBuffer.flip();
        while (viewIntBuffer.hasRemaining()) {
            System.out.print(viewIntBuffer.get() + "  ");
        }
        System.out.println();

        ByteBuffer orderedBuffer = ByteBuffer.allocate(12);
        CharBuffer orderedCharBuffer = orderedBuffer.asCharBuffer();
        orderedCharBuffer.put("abcdef");
        orderedCharBuffer.flip();
        System.out.print("Default order: ");
        while (orderedCharBuffer.hasRemaining()) {
            System.out.print(orderedCharBuffer.get());
        }
        System.out.println(" Order array: " + Arrays.toString(orderedBuffer.array()));
        orderedCharBuffer.clear();

        // Change order type
        orderedBuffer.order(ByteOrder.LITTLE_ENDIAN);
        orderedCharBuffer = orderedBuffer.asCharBuffer();
        orderedCharBuffer.put("abcdef");
        orderedCharBuffer.flip();
        System.out.print("Use LITTLE_ENDIAN: ");
        while (orderedCharBuffer.hasRemaining()) {
            System.out.print(orderedCharBuffer.get());
        }
        System.out.println(" Order array: " + Arrays.toString(orderedBuffer.array()));
    }
}
