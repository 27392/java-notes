package cn.haohaoli.book.core.chapter5.reflection;

import cn.haohaoli.book.core.chapter5.extend.Employee;

import java.lang.reflect.*;
import java.util.Scanner;

public class ReflectionTest  {

    public static void main(String[] args) throws Exception {
        Class clazz = Employee.class;
        try {
            //返回这个类的一个新实例
            Employee employee = (Employee) clazz.newInstance();
            System.out.println(employee);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        //域
        Field[] fields = clazz.getFields();
        Field[] declaredFields = clazz.getDeclaredFields();
        //方法
        Method[] methods = clazz.getMethods();
        Method[] declaredMethods = clazz.getDeclaredMethods();
        //构造器
        Constructor constructor = clazz.getConstructor();
        Object o = constructor.newInstance();
        Constructor[] constructors = clazz.getConstructors();
        Constructor[] declaredConstructors = clazz.getDeclaredConstructors();

        Employee employee1 = new Employee("xx", 50000, 198, 1, 1);
        Class<?> aClass1 = employee1.getClass();
        Field name1 = aClass1.getDeclaredField("salary");
        name1.setAccessible(true);
        name1.set(employee1, 999999.0);
        double aDouble = name1.getDouble(employee1);
        System.out.println(aDouble);

        String name;
        Scanner scanner = new Scanner(System.in);
        name = scanner.next();
        try {
            Class<?> aClass = Class.forName(name);
            Class<?> superclass = aClass.getSuperclass();
            String s = Modifier.toString(aClass.getModifiers());
            System.out.print(s + " " + aClass.getName());
            if (superclass != null && superclass != Object.class) {
                System.out.print(" extends " + superclass.getName());
            }
            System.out.println(" {");
            printConstructors(aClass);
            System.out.println();
            printFields(aClass);
            System.out.println();
            printMethods(aClass);
            System.out.println("}");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void printConstructors(Class clazz) {
        Constructor[] constructors = clazz.getConstructors();
        for (Constructor c : constructors) {
            System.out.print("     ");
            String name = c.getName();
            System.out.print(Modifier.toString(c.getModifiers()) + " " + name);
            Class[] t = c.getParameterTypes();
            System.out.print(" (");
            for (Class aClass : t) {
                System.out.print(aClass.getName() + " ");
            }
            System.out.println(");");
        }
    }

    public static void printFields(Class clazz) {
        Field[] fields = clazz.getFields();
        for (Field field : fields) {
            System.out.print("     ");
            String name = field.getName();
            String s = Modifier.toString(field.getModifiers());
            System.out.println(s + " " + name);
        }
    }
    public static void printMethods(Class clazz) {
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            System.out.print("     ");
            String name = method.getName();
            String s = Modifier.toString(method.getModifiers());
            System.out.print(s + " " + name);
            Class<?>[] parameterTypes = method.getParameterTypes();
            System.out.print(" (");
            for (Class<?> parameterType : parameterTypes) {
                System.out.print(parameterType.getName() + " ");
            }
            System.out.println(");");
        }
    }

}
