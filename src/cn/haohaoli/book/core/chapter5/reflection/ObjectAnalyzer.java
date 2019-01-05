package cn.haohaoli.book.core.chapter5.reflection;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

public class ObjectAnalyzer {

    private ArrayList<Object> list = new ArrayList<>();

    public String toString (Object o) {
        if (o == null) {
            return "null";
        }
        if (list.contains(o)) {
            return "...";
        }
        list.add(o);
        Class<?> cl = o.getClass();
        if (cl == String.class) {
            return (String) o;
        }
        if (cl.isArray()) {
            String s = cl.getComponentType() + "[]{";
            for (int i = 0; i < Array.getLength(o); i++) {
                if (i > 0) {
                    s += ',';
                    Object o1 = Array.get(o, i);
                    if (cl.getComponentType().isPrimitive()) {
                        s += o1;
                    } else {
                        s += toString(o1);
                    }
                }
            }
            return s + "}";
        }
        String s = cl.getName();
        do {
            s += "[";
            Field[] fields = cl.getDeclaredFields();
            AccessibleObject.setAccessible(fields, true);
            for (Field field : fields) {
                if (!Modifier.isStatic(field.getModifiers())) {
                    if (!s.endsWith("[")) {
                        s += ",";
                    }
                    s += field.getName() + "=";
                    try {
                        Class<?> type = field.getType();
                        Object o1 = field.get(o);
                        if (type.isPrimitive()) {
                            s += o1;
                        } else {
                            s += toString(o1);
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
            s += "]";
            cl = cl.getSuperclass();
        } while (cl != null);
        return s;
    }
}
