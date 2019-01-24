package cn.haohaoli.book.core.chapter5.reflection;

import cn.haohaoli.book.core.chapter5.inheritance.Employee;
import cn.haohaoli.book.core.chapter5.inheritance.Executive;

import java.lang.reflect.*;
import java.util.Scanner;

/**
 * 反射测试类
 * @author LiWenHao
 * @date 2019-01-24 11:42
 */
public class ReflectionTest  {

    /**
     * TODO 反射
     *  在程序运行期间，Java 运行时系统始终为所有的对象维护一个被称为运行时的类型标识。
     *  这个信息跟踪着每个对象所属的类。 虚拟机利用运行时类型信息选择相应的方法执行。
     */
    public static void main(String[] args) throws Exception {

        /**
         * TODO Class Api (部分)
         *  • static Cl ass forName(String className)
         *      返回描述类名为 className 的 Class 对象。
         *  • Object newInstance()
         *      返回这个类的一个新实例
         *  • Field[] getFields()
         *  • Filed[] getDeclaredFields()
         *      getFields           方法将返回一个包含 Field 对象的数组， 这些对象记录了这个类或其超类的公有域
         *      getDeclaredFields   方法也将返回包含 Field 对象的数组，
         *      这些对象记录了这个类的全部域。如果类中没有域， 或者 Class 对象描述的是基本类型或数组类型， 这些方法将返回一个长度为 0 的数组
         *  • Field getField(String name)
         *  • Field getDeclaredField(String name)
         *      getField            返回一个 Field对象，它反映此表示的类或接口的指定公共成员字段 类对象。
         *      getDeclaredField    返回一个 Field对象，它反映此表示的类或接口的指定已声明字段 类对象。
         *  • Method[] getMethods()
         *  • Method[] getDeclareMethods()
         *      返回包含 Method 对象的数组：
         *          getMethods          将返回所有的公有方法， 包括从超类继承来的公有方法；
         *          getDeclaredMethods  返回这个类或接口的全部方法， 但不包括由超类继承了的方法。
         *  • Constructor[] getConstructors()
         *  • Constructor[] getDeclaredConstructors()
         *      返回包含 Constructor 对象的数组， 其中包含了 Class 对象所描述的类的所有公有构造器（getConstructors) 或所有构造器（getDeclaredConstructors)
         */
        Class clazz = Employee.class;
        Class classes = Executive.class;
        try {
            /**
             * TODO 实例化对象 new 和 newInstance
             *  newInstance     方法它生成对象只能调用无参的构造函数
             *  new             关键字生成对象没有这个限制。
             *  资料：http://www.cnblogs.com/wanqieddy/p/3654501.html
             *  注意：
             *    返回这个类的一个新实例
             *     类必须保证有无参构造器 构造器参考第四章Employee类中有笔记
             *     如果类没有无参构造使用newInstance方法 会抛出异常： InstantiationException (实例化异常)
             *     很明显Employee 我们自己定义了构造方法 所以默认的构造方法不存在
             */
//            Employee employee = (Employee) clazz.newInstance();           //error
            //有无参构造器
            Executive executive = (Executive) classes.newInstance();        //ok
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        //域
        Field[] fields = clazz.getFields();
        Field[] declaredFields = clazz.getDeclaredFields();
        //方法
        Method[] methods = clazz.getMethods();
        Method[] declaredMethods = clazz.getDeclaredMethods();
        //构造器
        Constructor[] constructors = clazz.getConstructors();
        Constructor[] declaredConstructors = clazz.getDeclaredConstructors();
        /**
         * TODO getConstructor 方法
         *  入参为可变参数所以在不传任何参数的情况下调用无参构造
         *  在传入参数的时候,会找寻和参数类型相同的构造器
         *  调用getConstructor方法我们并没有传入参数 也就是调用无参构造
         *  clazz 对象是Employee 此类中并没有无参构造
         *  抛出异常 NoSuchMethodException (没有这样的方法异常)
         *
         */
//        Constructor constructor = clazz.getConstructor(); //error
        Constructor constructor = classes.getConstructor(); //ok
        Object o = constructor.newInstance();

        /**
         *  调用有参数构造的方法
         *  使用getConstructor方法传入指定构造器的入参类型获取构造器
         *  然后在通过newInstance方法可实例化对象
         */
        Constructor constructor1 = clazz.getConstructor(String.class, double.class, int.class, int.class, int.class);
        Employee obj = (Employee) constructor1.newInstance("反射调用有参数构造", 5000d, 2018, 1, 24);
        System.out.println("name = " + obj.getName() + " ,Salary = " + obj.getSalary() + " ,HireDay = " + obj.getHireDay());

        /**
         * TODO Constructor 类 Api (部分)
         *  • Class getDeclaringClass()
         *      返冋一个用于描述类中定义的构造器、 方法或域的 Class 对象。
         *  • Class[] getExceptionTypes () ( 在 Constructor 和 Method 类 中）
         *      返回一个用于描述方法抛出的异常类型的 Class 对象数组。
         *  • int getModifiers()
         *      返回一个用于描述构造器、 方法或域的修饰符的整型数值。使用 Modifier 类中的这个
         *      方法可以分析这个返回值。
         *  • String getName()
         *      返冋一个用于描述构造器、 方法或域名的字符串。
         *  • Class[] getParameterTypes () ( 在 Constructor 和 Method 类 中）
         *      返回一个用于描述参数类型的 Class 对象数组。
         *  • Class getReturnType() ( 在 Method 类 中）
         *      返回一个用于描述返回类型的 Class 对象
         */

        /**
         * TODO Modifier 类 Api (部分)
         *  •static String toString(int modifiers)
         *   返回对应 modifiers 中位设置的修饰符的字符串表表示。
         *  • static boolean isAbstract(int modifiers )
         *  • static boolean isFinal (int modifiers )
         *  • static boolean isInterface(int modifiers )
         *  • static boolean isNative(int modifiers )
         *  • static boolean isPrivate(int modifiers )
         *  • static boolean isProtected(int modifiers )
         *  • static boolean isPublic(int modifiers )
         *  • static boolean isStatic(int modifiers )
         *  • static boolean isStrict(int modifiers )
         *  • static boolean isSynchronized(int modifiers )
         *  • static boolean isVolatile(int modifiers )
         *      这些方法将检测方法名中对应的修饰符在 modifiers 值中的位
         */

        /**
         * TODO AccessibleObject 类 Api (部分)
         *  • void setAccessible(boolean flag)
         *      为反射对象设置可访问标志。flag 为 true 表明屏蔽 Java 语言的访问检查，使得对象的
         *      私有属性也可以被査询和设置。
         *  • boolean isAccessible()
         *      返回反射对象的可访问标志的值。
         *  • static void setAccessible(AccessibleObject[] array,boolean flag)
         *      是一种设置对象数组可访问标志的快捷方法。
         */

        /**
         * TODO Field 类 Api (部分)
         *  • Object get(Object obj)
         *      返回 obj 对象中用 Field 对象表示的域值。
         *  • void set(Object obj ,Object newValue )
         *      用一个新值设置 Obj 对象中 Field 对象表示的域。
         */
        Employee employee = new Employee("xx", 50000, 198, 1, 1);
        Class aClass1 = employee.getClass();
        Field salary = aClass1.getDeclaredField("salary");
        salary.setAccessible(true);
        salary.set(employee, 999999.0);
        double aDouble = salary.getDouble(employee);
        System.out.println(aDouble);

        String name;
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入类名查看信息 例如 'java.lang.String' :");
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
            //获取参数类型
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
