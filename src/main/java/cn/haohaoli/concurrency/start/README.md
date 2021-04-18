# 启动线程

## start方法

### 官方文档

**使线程开始执行: Java虚拟机调用这个线程的`run`方法**

**结果是两个线程并发运行: 当前线程(从对start方法的调用返回)和另一个线程(执行其run方法)。**

**多次启动线程是不合法的。特别是, 线程一旦完成执行，就不能重新启动。**

---

从文档上可以得出结论:

> `start`方法其实是去通知JVM虚拟机,告诉它,此线程已经准备就绪,等待调用线程对象的`run`方法

这个过程其实就是让系统安排一个时间来调用`Thread`中的`run`方法,也就是使线程得到运行,具有异步执行的效果

还有一个重点是:

> 执行`start`方法的顺序不代表线程启动的顺序

### 源码

```java
public synchronized void start() {

    // 状态值默认为零对应状态“NEW”,表示线程“尚未启动”
    // 如果线程状态不是“NEW”(尚未启动)抛出异常
    if (threadStatus != 0)
        throw new IllegalThreadStateException();

    // 加入线程组
    group.add(this);
    
    boolean started = false;
    try {
        // start0是native方法
        start0();
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
```

### 不可多次启动一个线程

在文档中有这么一句:

> 多次启动线程是不合法的。特别是, 线程一旦完成执行，就不能重新启动。

先看句尾`线程一旦完成执行，就不能重新启动`,这里也可以理解为**线程的状态永远不可能回到`NEW`状态**

在源码中可以看到调用`start`方法在调用`start0`方法前会去检查线程的状态

## run方法

在看过`start`方法后,可以得知,`run`方法是由新的线程来调用的.

注意: **切勿手动调用Thread类或者是Runnable类的`run`方法.调用`run`方法不会开启新的线程,它只会在当前线程中串行执行**

### 源码

```java
public void run() {
    if (target != null) {
        target.run();
    }
}
```

## 总结

1. 只有调用`start`方法才会启动新的线程,而调用`run`方法则只会像普通方法一样执行,不会启动新的线程
   
2. 不可以重复的调用`start`方法,重复调用会抛出`IllegalThreadStateException`异常

3. 执行`start`方法的顺序不代表线程启动的顺序
