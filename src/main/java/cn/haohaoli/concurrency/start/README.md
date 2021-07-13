# 启动线程

## 如何启动线程

> 在`Thread`类中介绍到使用`start`方法可以运行线程

### 使用start方法

```java
public class Test {

    public static void main(String[] args){
        Runnable runnable = () -> {
            // 打印当前运行的线程名称
            System.out.println(Thread.currentThread().getName());
        };
        
        // 自定义线程名称
        Thread thread = new Thread(runnable, "t1");
        
        // 调用start方法
        thread.start(); // t1
    }

}
```

运行实例会输出: `t1`,说明它是以新的线程运行,而不是使用主线程运行

### 使用run方法

既然`start`方法可以,那`run`方法行不行? 比较我们的业务逻辑不都写在`run`方法里的嘛?

下面我们来看,调用`run`方法是否会启动新的线程

```java
public class Test {

    public static void main(String[] args){
        Runnable runnable = () -> {
           // 打印当前运行的线程名称
           System.out.println(Thread.currentThread().getName());
        };
        Thread thread = new Thread(runnable, "t1");
        
        // 调用run方法
        thread.run(); // main
    }

}
```

可以看到,与我们调用`start`方法不一样,输出的结果是`main`主线程.并没有启动新的线程!

#### 为何不能启动

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

从源码可以看到,如果调用`run`方法,是直接调用`target`属性的也就是(Runnable)中的`run`方法.这就是个普通方法并没有看到有启动线程的处理,所以使用它并不会启动线程

## start方法解析

### 方法描述

> 使此线程开始执行;Java虚拟机调用这个线程的`run`方法
>
> 结果是两个线程并发运行: 当前线程(从`start`方法调用返回)和另一个线程(执行其`run`方法).
>
> 多次启动线程是不合法的.特别是,线程一旦完成执行,就不能重新启动.

### 源码分析

```java
public class Thread implements Runnable {

    // 省略其他方法...
    
    private native void start0();

    public synchronized void start() {
    
        // 检查线程状态.是否未启动,如果启动过就抛异常
        if (threadStatus != 0)
            throw new IllegalThreadStateException();

        group.add(this);

        boolean started = false;
        try {
            start0();   // 调用`start0`方法,`start0`是一个本地方法
            started = true;
        } finally {
            try {
                if (!started) {
                    group.threadStartFailed(this);
                }
            } catch (Throwable ignore) {
            }
        }
    }

}
```

`start`方法并会不会启动线程,而是在内部调用了本地方法`start0`.而这个`start0`方法会启动一个新的线程,然后运行其中的`run`方法

## 不可多次启动一个线程

在方法的开始就可以看到检查状态的代码,如果线程不是未启动状态会抛出异常

在描述中有这么一句:

> 多次启动线程是不合法的.特别是,线程一旦完成执行,就不能重新启动.

也就是说线程一旦启动后,状态就不会回到最初的状态.那就印证了一个线程只能启动一次

## 总结

1. 只有调用`start`方法才会启动新的线程,而调用`run`方法则只会像普通方法一样执行,不会启动新的线程

2. `start`方法可以理解为: 通知Java虚拟机调用`run`方法,而何时启动新的线程是由Java虚拟机决定的

3. 线程只能启动一次,也就是不可以重复的调用`start`方法,否则会抛出`IllegalThreadStateException`异常

4. 线程在启动后,状态就不会回到最初的状态

5. 执行`start`方法的顺序不代表线程启动的顺序(启动线程是由Java虚拟机调度)
