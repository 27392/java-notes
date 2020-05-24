package cn.haohaoli.book.core.base.chapter5.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

/**
 * @author LiWenHao
 */
public class FieldTest {

    public static void main(String[] args) throws NoSuchFieldException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        System.out.println("===========getFields===========");
        getFields(Example.class);

        System.out.println("===========getValueAndModifyValue===========");
        getValueAndModifyValue();
    }

    /**
     * 获得字段
     *
     * @param clazz
     */
    public static void getFields(Class<?> clazz) {
        Field[] fields         = clazz.getFields();
        Field[] declaredFields = clazz.getDeclaredFields();

        // 获得所有的公有字段(包含继承字段)
        System.out.println("getFields(): ");
        for (Field field : fields) {
            System.out.println(Modifier.toString(field.getModifiers()) + " " + field.getName());
        }

        // 获得该类的所有字段.这包括公共字段、受保护字段、默认(包)访问和私有字段(不包括继承字段)
        System.out.println("\ngetDeclaredFields(): ");
        for (Field declaredField : declaredFields) {
            System.out.println(Modifier.toString(declaredField.getModifiers()) + " " + declaredField.getName());
        }
    }

    /**
     * 获取属性值和修改属性值
     *
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws InstantiationException
     */
    public static void getValueAndModifyValue() throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {

        Example example = Example.class.getConstructor(Integer.class, Integer.class, Integer.class).newInstance(1, 1, 18);

        // 获取字段
        Field age  = Example.class.getField("age");
        Field sex   = Example.class.getDeclaredField("sex");

        // 获取字段的值
        System.out.println("age: " + age.get(example));

        sex.setAccessible(true);
        System.out.println("sex: " + sex.get(example));

        // 设置字段的值
        sex.set(example, 2);
        System.out.println("set sex after : " + sex.get(example));

    }

    static class Example extends AbstractExample {

        private Integer id;

        private final Integer sex;

        public Example(Integer id, Integer sex, Integer age) {
            this.id = id;
            this.sex = sex;
            this.age = age;
        }
    }

    static abstract class AbstractExample {

        private String name;

        public Integer age;
    }
}
