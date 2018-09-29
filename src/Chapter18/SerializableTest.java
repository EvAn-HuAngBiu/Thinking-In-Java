package Chapter18;

import java.io.*;
import java.util.Arrays;

/**
 * @author Evan
 * @date 2018/09/23 10:24
 */
public class SerializableTest {
    public static void main(String[] args) throws IOException {
        Teacher[] teachers = new Teacher[]{
                new Teacher("Ada", "10001"),
                new Teacher("Bob", "10002"),
                new Teacher("Evan", "10003")
        };
        Student student = new Student("Landy", teachers);
        student.setAge(18);
        System.out.println(student);

        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream("src/Chapter18/Resources3/student.object"))) {
            oos.writeObject(student);
        }

        Teacher.count++;
        Student fileStu = null;
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream("src/Chapter18/Resources3/student.object"))) {
            Object obj = ois.readObject();
            System.out.println(obj.getClass().getSimpleName());
            fileStu = (Student) obj;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(fileStu);

    }
}

class Student implements Serializable {
    private String name;
    private Teacher[] teachers;
    private transient int age;

    public Student(String name, Teacher[] teachers) {
        this.name = name;
        this.teachers = teachers;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Teacher[] getTeachers() {
        return teachers;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", teachers=" + (teachers == null ? null : Arrays.asList(teachers)) +
                ", age=" + age +
                '}';
    }
}

class Teacher implements Serializable {
    public static int count = 0;
    private String name;
    private String id;

    public Teacher(String name, String id) {
        count++;
        this.name = name;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", count='" + count + '\'' +
                '}';
    }
}
