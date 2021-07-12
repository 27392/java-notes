# 创建线程

## 实现多线程的两种方式

通过查看`Thread`类的官方文档,可以得知`有两种方法可以创建新的执行线程`.继承Thread类与实现Runnable接口

### 继承Thread类

**将一个类声明为Thread的子类.该子类应重写Thread类的run方法。**

```java
public class ExtendThreadWay extends Thread {

    @Override
    public void run() {
        System.out.println("使用Thread方式实现线程");
    }

    public static void main(String[] args) {
        ExtendThreadWay extendThreadWay = new ExtendThreadWay();
        extendThreadWay.start();
    }
}
```

### 实现Runnable接口

**实现Runnable接口的类.然后,该类实现run方法.在创建Thread时将其作为参数传递并启动**

```java
public class ImplementRunnableWay implements Runnable {

    @Override
    public void run() {
        System.out.println("使用Runnable方式实现线程");
    }

    public static void main(String[] args) {
        Thread implementRunnableWay = new Thread(new ImplementRunnableWay());
        implementRunnableWay.start();
    }
}
```

## 两种方式比对

| 实现方式 | 拓展性 | 耦合度 | 开销 |
|---|---|---|---|
| 继承Thread类       | 受继承限制(Java只支持单继承)| 业务与线程创建运行等逻辑耦合              | 新建线程的开销(创建、执行、销毁等)是比较大的. |
| 实现Runnable接口   | 接口支持多实现             | 只存在业务逻辑.更符合面向对象的`单一职责`   | 使用Runnable则可以使用线程池等工具来避免此类的开销 |

### 本质上的对比

先来看下Thread类的`run`方法

```java
public class Thread implements Runnable {

    private Runnable target;

    // 省略其他方法...
        
    @Override
    public void run() {
        if (target != null) {
            target.run();
        }
    }
}
```

使用`实现Runnable接口`的方式,那么`Thread`类中的`run`方法就会调用我们传入的`Runnable`实例(target)的`run`方法

如果是`实现了Thread类`就需要重写`run`方法,那么`run`方法就调用子类的

> 这两种方式本质上并没有区别.都是执行`run`方法,只是`run`方法的来源不同

## 同时使用两种方式会怎么样? 

```java
public class BothWayCombine extends Thread {

    public BothWayCombine(Runnable target) {
        super(target);
    }

    @Override
    public void run() {
        System.out.println("thread");
    }

    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("runnable");
            }
        };

        Thread combine = new BothWayCombine(runnable);
        combine.start();
    }
}
```

> 由于子类重写了`run`方法那么这里输出的结果是: "thread"
>
> 不推荐这么写,这里只是举例!

## 错误的观点

在网上有许多的错误观点, 例如

1. 线程池创建线程也算一种新建线程的方式
    
    ```java
    /**
     * 线程池通过调用`ThreadFactory`实例的`newThread`方法得到`Thread`类实例
     * 在JDK,Executors类中默认实现的`ThreadFactory`就是通过传入`Runnable`实例实现的
     * 
     * 所以说在底层还是使用`Thread`类,并不算是一种新的方式
     */
    public class Executors {
    
        static class DefaultThreadFactory implements ThreadFactory {
    
            public Thread newThread(Runnable r) {
                Thread t = new Thread(group, r,
                                      namePrefix + threadNumber.getAndIncrement(),
                                      0);
                if (t.isDaemon())
                    t.setDaemon(false);
                if (t.getPriority() != Thread.NORM_PRIORITY)
                    t.setPriority(Thread.NORM_PRIORITY);
                return t;
            }
        }
    }
    ```
  
2. FutureTask + Callable
    
    - FutureTask 继承了 Runnable 接口本质上是一样的,并不算是一种新的方式
      
3. 定时器

    - 定时器也是一样,并不算是一种新的方式

4. 匿名类

    - 写法不同而已,也是一样的, 并不算是一种新的方式

5. lambda表达式

    - 语法糖,本质也是哪两种方法, 并不算是一种新的方式

6. Spring实现多线程
    
    - Spring将其包装后也是一样的, 并不算是一种新的方式
    
7. 等等

> 这么多种实现方式,在代码中千变万化,但是底层还是那两种方式(继承Thread类、实现了Thread类)
>
> 我们不能认为一个类它能实现线程我们就认为它是一种新的方式!

## 结论

从官方文档上得出的结果是: **有两种方式可以创建线程**.这一点毋庸置疑

但准确的讲:

> 创建线程的只有一种那就是构造`Thread`类
>
> 这里说的两种是说: 实现线程执行单元的方式有两种

