package cn.haohaoli.lazy.componet;

import lombok.RequiredArgsConstructor;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author lwh
 */
@RequiredArgsConstructor
public class LazyMethodInterceptor implements MethodInterceptor {

    private final LoaderMap loaderMap;

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        String name = method.getName();
        if (name.startsWith("get")) {
            String property = name.substring(3);
            if (loaderMap.hasLoader(property)) {
                loaderMap.load(property);
            }
        } else if (name.startsWith("set")) {
            String property = name.substring(3);
            loaderMap.remove(property);
        }
        return proxy.invokeSuper(obj, args);
    }
}