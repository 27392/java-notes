package cn.haohaoli.lazy.componet;

import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * @author lwh
 */
@RequiredArgsConstructor
public class LoaderMap {

    private final Map<String, LoadPair> loaderMap = new HashMap<>();

    public void add(String property, Object o, Supplier<?> supplier) {
        String key = property.toUpperCase();
        loaderMap.put(key, new LoadPair(o, property, supplier));
    }

    public boolean hasLoader(String property) {
        return loaderMap.containsKey(property.toUpperCase());
    }

    public void load(String property) {
        LoadPair pair = loaderMap.remove(property.toUpperCase());
        if (pair != null) {
            pair.load();
        }
    }

    public void remove(String property) {
        loaderMap.remove(property);
    }
}