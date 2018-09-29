package Chapter20;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;


/**
 * @author Evan
 * @date 2018/09/24 20:34
 */
public class AnnotationTest1 {

    private String str = "Hello";

    @UseCase(id = 133, description = "Test execute")
    private void testExecute() {

    }

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface UseCase {
        int id();

        String description() default "No description";
    }

    public static void main(String[] args) {
        Class<AnnotationTest1> clazz = AnnotationTest1.class;
        try {
            Method method = clazz.getDeclaredMethod("testExecute");
            UseCase annotation = method.getDeclaredAnnotation(UseCase.class);
            System.out.println(annotation.id() + ", " + annotation.description());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}



