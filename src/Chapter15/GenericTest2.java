package Chapter15;

/**
 * @author Evan
 * @date 2018/09/14 19:29
 */
public class GenericTest2 {
    private Generic<Integer>[] genericArray;

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        ClassAsFactory<Employee> employeeFactory =
                new ClassAsFactory<>(Employee.class);
        Employee e2 = employeeFactory.getObj();
        System.out.println(Employee.id);

//        Not allowed to create a generic array
//        Generic<Integer>[] genericArray = new Generic<Integer>[5];
        GenericTest2 gt = new GenericTest2();
        gt.genericArray = (Generic<Integer>[]) new Generic[5];
        System.out.println(gt.genericArray.getClass().getSimpleName());
    }
}

class ClassAsFactory<T> {
    private T obj;

    public ClassAsFactory(Class<? extends T> clazz) {
        try {
            obj = clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public T getObj() {
        return obj;
    }
}

class Employee {
    public static int id;
    private int count = id++;
}

class Generic<T> {}
