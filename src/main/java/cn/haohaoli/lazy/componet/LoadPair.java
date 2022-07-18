package cn.haohaoli.lazy.componet;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * @author lwh
 */
@Getter
@RequiredArgsConstructor
public class LoadPair {

    private final Object object;
    private final String property;

    private final Supplier<?> supplier;

    public void load() {
        try {
            Optional<Method> methodOpt = Arrays.stream(object.getClass().getDeclaredMethods())
                    .filter(r -> r.getName().startsWith("set"))
                    .filter(r -> Objects.equals(r.getName().substring(3).toUpperCase(), property.toUpperCase()))
                    .findAny();
            if (methodOpt.isPresent()) {
                Method method = methodOpt.get();
                method.invoke(object, supplier.get());
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
