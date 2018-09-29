package Chapter15;

/**
 * @author Evan
 * @date 2018/09/16 09:16
 */
public class GenericTest4 {
    static void rawArgs(Holder holder, Object arg) {
        holder.setValue(arg);
        Object obj = holder.getValue();
        System.out.println("Value = " + obj + ", Type = " + obj.getClass().getSimpleName());
    }

    static void unboundedArg(Holder<?> holder, Object arg) {
//        Not allowed to do this
//        holder.setValue(arg);
        Object obj = holder.getValue();
        System.out.println("Value = " + obj + ", Type = " + obj.getClass().getSimpleName());
    }

    static <T> void exactArg(Holder<T> holder, T arg) {
        holder.setValue(arg);
        T obj = holder.getValue();
        System.out.println("Value = " + obj + ", Type = " + obj.getClass().getSimpleName());
    }

    static <T> void exactArg2(Holder<T> holder) {
        T obj = holder.getValue();
        System.out.println("Value = " + obj + ", Type = " + obj.getClass().getSimpleName());
    }

    static <T> void withSubType(Holder<? extends T> holder, T arg) {
//        Not allowed to do this
//        holder.setValue(arg);
        T obj = holder.getValue();
        System.out.println("Value = " + obj + ", Type = " + obj.getClass().getSimpleName());
    }

    static <T> void withSuperType(Holder<? super T> holder, T arg) {
        holder.setValue(arg);
//        Not allwed to do this
//        T obj = holder.getValue();
        Object obj = holder.getValue();
        System.out.println("Value = " + obj + ", Type = " + obj.getClass().getSimpleName());
    }

    public static void main(String[] args) {
        Holder raw = new Holder();
        Holder<Long> exact = new Holder<>();
        Holder<?> unbounded = new Holder<Long>();
        Holder<? extends Long> bounded = new Holder<Long>();
        Long lng = 1l;

        System.out.println("Call rawArg Method: ");
        rawArgs(raw, lng);
        rawArgs(exact, lng);
        rawArgs(unbounded, lng);
        rawArgs(bounded, lng);
        System.out.println("----------------------------");

        System.out.println("Call unboundedArg Method: ");
        unboundedArg(raw, lng);
        unboundedArg(exact, lng);
        unboundedArg(unbounded, lng);
        unboundedArg(bounded, lng);
        System.out.println("----------------------------");


        System.out.println("Call exactArg Method: ");
        exactArg(raw, lng);
        exactArg(exact, lng);
//        These two method call is illegal as the type argument T is unknown
//        The compiler cannot apply Long to an unknown type
//        exactArg(unbounded, lng);
//        exactArg(bounded, lng);
        System.out.println("----------------------------");

        System.out.println("Call exactArg2 Method: ");
        exactArg2(raw);
        exactArg2(exact);
        exactArg2(unbounded);
        exactArg2(bounded);
        System.out.println("----------------------------");

        System.out.println("Call withSubType Method: ");
        withSubType(raw, lng);
        withSubType(exact, lng);
        withSubType(unbounded, lng);
        withSubType(bounded, lng);
        System.out.println("----------------------------");

        System.out.println("Call withSuperType Method: ");
        withSuperType(raw, lng);
        withSuperType(exact, lng);
//        withSuperType(unbounded, lng);
//        withSuperType(bounded, lng);
        System.out.println("----------------------------");

    }
}


class Holder<T> {
    private T value;

    public Holder() {

    }

    public Holder(T val) {
        value = val;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
