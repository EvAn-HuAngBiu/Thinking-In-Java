package Chapter14;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * @author Evan
 * @date 2018/09/13 10:11
 */
public class ProxyTest {
    public static void main(String[] args) {
        RealObject real = new RealObject();
        // 参数说明：被代理的接口的类加载器，被代理的类列表，代理处理器
        Interface proxy = (Interface) Proxy.newProxyInstance(Interface.class.getClassLoader(),
                new Class[] {Interface.class}, new DynamicProxyHandler(real));

        proxy.dosomething();
        proxy.dosomethingelse("Say hi");
    }
}

interface Interface {
    void dosomething();

    void dosomethingelse(String arg);
}

class RealObject implements Interface {

    @Override
    public void dosomething() {
        System.out.println("Do something");
    }

    @Override
    public void dosomethingelse(String arg) {
        System.out.println("Do something else : " + arg);
    }
}

class DynamicProxyHandler implements InvocationHandler {
    private Object proxied;

    public DynamicProxyHandler(Object proxied) {
        this.proxied = proxied;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("***** Proxy Called *****\n" + proxy.getClass() + ", method: " +
                method + ", args: " + Arrays.toString(args));

        return method.invoke(proxied, args);
    }
}