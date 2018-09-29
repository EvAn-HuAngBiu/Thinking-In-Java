package Chapter9;

/**
 * 代码完全解耦实例，使用了适配器设计模式
 * 适配器提供了一个中介，在无法修改类库源码时，可以通过适配器
 * ，适配器接受你所拥有的接口，返回一个方法需要的参数类型，在适配器中进行
 * 类型的适配即可。
 *
 * 示例中，{@link WaveForm}及其子类具有与{@link Processor}类一致的方法，但是
 * {@link WaveForm}类不是{@link Processor}的一个子类，而将适配器类{@link FilterAdapter}
 * 定义为{@link Processor}的子类后，适配器就可以处理{@link WaveForm}
 * 并正确的调用{@link Apply#process(IProcessor, Object)}
 * 方法
 *
 * @see <a href="http://www.runoob.com/design-pattern/adapter-pattern.html">
 *     Adapter design mode</a>
 *
 * @author Evan
 * @date 2018/09/08 17:51
 */
public class WaveformTest {
    public static void main(String[] args) {
        WaveForm w = new WaveForm();
        Apply.process(new FilterAdapter(new LowPass(1.0)), w);
        Apply.process(new FilterAdapter(new HighPass(2.0)), w);
    }
}

interface IProcessor {
    String name();

    Object process(Object input);
}

class Apply {
    public static void process(IProcessor p, Object s) {
        System.out.println(p.name() + "\t" + p.process(s));
    }
}

class WaveForm {
    private static long counter;
    private final long id = counter++;

    @Override
    public String toString() {
        return "Waveform: " + id;
    }
}

class Filter {
    public String name() {
        return getClass().getSimpleName();
    }

    public WaveForm process(WaveForm input) {
        return input;
    }
}

class LowPass extends Filter {
    double cutoff;

    public LowPass(double cutoff) {
        this.cutoff = cutoff;
    }

    public WaveForm process(WaveForm input) {
        return input;
    }
}

class HighPass extends Filter {
    double cutoff;

    public HighPass(double cutoff) {
        this.cutoff = cutoff;
    }

    public WaveForm process(WaveForm input) {
        return input;
    }
}

class FilterAdapter implements IProcessor {
    Filter filter;
    public FilterAdapter(Filter filter) {
        this.filter = filter;
    }

    @Override
    public String name() {
        return filter.name();
    }

    @Override
    public WaveForm process(Object input) {
        return filter.process((WaveForm)input);
    }
}