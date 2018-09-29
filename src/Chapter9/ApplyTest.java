package Chapter9;

/**
 * 代码完全解耦实例，使用了策略设计模式。
 * 将不同导出类的方法分别抽象为一个类，在处理函数中接受一个基类对象
 * 即构成了策略设计模式。策略模式会根据传入方法的实际对象类型调用其
 * 相应的函数代码，而不会一味的调用基类函数。
 *
 * @see <a href="http://www.runoob.com/design-pattern/strategy-pattern.html">
 *     Strategy design mode</a>
 * @author Evan
 * @date 2018/09/08 10:57
 */
public class ApplyTest {
    public static void process(Processor p, Object s) {
        System.out.println(p.name() + "\t" + p.process(s));
    }

    public static String s = "Hello, this is a message";

    public static void main(String[] args) {
        process(new Processor(), s);
        process(new Upcase(), s);
        process(new Downcase(), s);
    }
}

class Processor {
    public String name() {
        return getClass().getSimpleName();
    }

    Object process(Object input) {
        return input;
    }
}

class Upcase extends Processor {
    // 协变返回类型可以被Override
    @Override
    String process(Object input) {
        return ((String) input).toUpperCase();
    }
}

class Downcase extends Processor {
    @Override
    String process(Object input) {
        return ((String)input).toLowerCase();
    }
}
