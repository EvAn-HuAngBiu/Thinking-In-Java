package Chapter18;

import java.io.*;

/**
 * @author Evan
 * @date 2018/09/23 10:34
 */
public class ExternalizableTest {
    public static void main(String[] args) throws IOException {
        Blip1 b1 = new Blip1("Blip", 1234, "Normal Comment");
        System.out.println(b1);
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream("src/Chapter18/Resources3/ext.object"))) {
            oos.writeObject(b1);
        }

        Blip1 b2 = null;
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream("src/Chapter18/Resources3/ext.object"))) {
            b2 = (Blip1) ois.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(b2);
    }
}

class Blip1 implements Externalizable {
    private String name;
    private int id;
    private String comment;

    // Constructor must be public as Externalizable read from file will call it
    // before call readObject method!
    public Blip1() {
        name = "default name";
        id = -1;
        comment = "default comment";
    }

    public Blip1(String name, int id, String comment) {
        this.name = name;
        this.id = id;
        this.comment = comment;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(name);
        out.writeInt(id);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.name = (String) in.readObject();
        id = in.readInt();
    }

    @Override
    public String toString() {
        return "Blip1{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", comment='" + comment + '\'' +
                '}';
    }
}
