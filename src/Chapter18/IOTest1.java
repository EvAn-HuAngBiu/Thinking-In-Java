package Chapter18;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Evan
 * @date 2018/09/21 18:59
 */
public class IOTest1 {
    public static void main(String[] args) throws IOException {
        try (PrintStream ps = new PrintStream(new FileOutputStream("src/Chapter18/Resource1/text.txt"))) {
            ps.println("Hello, World");
            ps.println("你好，世界");
            ps.println(5);
            ps.println(true);
        }

        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader("src/Chapter18/Resource1/text.txt"))) {
            String s;
            while ((s = br.readLine()) != null) {
                sb.append(s).append("\n");
            }
            System.out.println(sb.toString());
        }

        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("src/Chapter18/Resource1/nums.txt"))) {
            dos.writeInt(123);
            dos.writeDouble(3.1415926);
            dos.writeChar('A');
            dos.writeBoolean(true);
            dos.flush();
        }

        // Unnecessary to use Try-With-Resource as the close method do nothing
        System.out.println("----------------------------------------------------");
        DataInputStream dis = new DataInputStream(new FileInputStream("src/Chapter18/Resource1/nums.txt"));
        System.out.println("Read Int: " + dis.readInt());
        System.out.println("Read Double: " + dis.readDouble());
        System.out.println("Read Char: " + dis.readChar());
        System.out.println("Read Boolean: " + dis.readBoolean());

        System.out.println("----------------------------------------------------");
        try (BufferedReader br2 = new BufferedReader(new StringReader(sb.toString()))) {
            PrintStream ps2 = System.out;
            String s1;
            int lineCount = 0;
            while ((s1 = br2.readLine()) != null) {
                System.out.println(++lineCount + ": " + s1);
            }
        }

        try (PrintWriter pw = new PrintWriter("src/Chapter18/Resource1/text2.txt", StandardCharsets.UTF_8)) {
            String str1 = "哈哈哈";
            // Transform string to GBK
            byte[] str1Byte = str1.getBytes("GBK");
            // Write to file by UTF-8 charset
            pw.println(new String(str1Byte, StandardCharsets.UTF_8));
            pw.flush();

            // Read from file using UTF-8 charset
            Scanner in = new Scanner(Paths.get("src/Chapter18/Resource1/text2.txt"), "UTF-8");
            String readInStr = in.next();
            System.out.println(readInStr);
        }

        try (BufferedReader bf = new BufferedReader(new InputStreamReader(System.in))) {
            // System.out.println("Got input: " + bf.readLine());
        }

        try {
            Process process = new ProcessBuilder("cmd /c java --version".split(" ")).start();
            Scanner in = new Scanner(process.getInputStream());
            while (in.hasNextLine()) {
                System.out.println(in.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
