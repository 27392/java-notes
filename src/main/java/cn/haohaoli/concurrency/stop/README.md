# 停止线程

一般来说,线程在运行完毕就会结束,无须手动停止.但在有些情况下就需要手动去停止线程. 比如: 服务需要快速的关闭或者运行超时,运行出错等

想要安全快速且可靠的停止并不容易.Java没有提供一种可以安全正确停止线程的机制. 而是提供了一种**协同机制**. 也就说:

如果需要手动停止一个线程,我们最多只能做到去**通知**对方让它停止,无法做到强制停止.而是否停止何时停止是由被通知线程决定

## 通常情况下线程的停止

> 在通常情况下线程只有**两种**情况会停止

1. `run`方法中的代码运行完毕

2. 发生未捕获的异常

## 正确的手动停止线程

那么想正确即安全的停止一个线程就需要两个步骤:

1. **通知(中断)被停止线程**
   
2. **被停止线程配合**

> 如果要停止一个线程,我们最多只能做到去通知对方让它停止(中断)
>
> 而被停止(中断)的线程本身拥有决定权,不但能决定何时来响应停止(中断),何时去停止.同时还有最高决定权就是停不停止
>
> 如果我想停止(中断)一个线程,而那个线程不想被停止(中断),那么我们对此无能为力


### 相关方法描述

|  方法 | 作用 | 描述
|---|---|---|
| void interrupt()       | 中断线程 | 将线程的中断状态设置为`true` |
| **static** boolean interrupted()   | 判断当前线程(即正在执行命令的线程)是否被中断|该方法是静态方法,调用后会将当前线程的中断状态重置为`false`|
| boolean isInterrupted()   | 判断线程是否被中断           | 与`interrupted`不同的是,它不会改变线程中断状态  |

### 未在线程休眠时中断

```java
public class WithOutSleepWay {

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(() -> {
            int num = 0;
            int limit = Integer.MAX_VALUE / 2;
            while (num <= limit) {
                if (num % 100000 == 0) {
                    System.out.println(num);
                }
                num++;
            }
            System.out.println("任务完成");
        }, "t1");

        thread.start();

        // 主线程休眠50毫秒
        Thread.sleep(50);

        // 中断线程
        thread.interrupt();
    }
}
```

运行实例后发现`t1`线程根本就没有任何反应,甚至和我不调用`interrupt`方法的结果是一样的

因为虽然对`t1`线程发出了中断的通知,但是在`t1`线程中并没有处理中断的逻辑.因此,即使`t1`线程的中断状态为`true`,也不会有任何作用

在调用方使用`Thread.interrupted()`,`Thread.currentThread().isInterrupted()`获取中断状态

```java
public class WithOutSleepWay {

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(() -> {
            int num = 0;
            int limit = Integer.MAX_VALUE / 2;

            // Thread.interrupted() 获取当前线程是否被中断,同时会清除中断状态
            // Thread.currentThread().isInterrupted() 获取当前线程是否被中断

            // while (!Thread.interrupted() && num <= limit) {
            while (!Thread.currentThread().isInterrupted() && num <= limit) {
                if (num % 100000 == 0) {
                    System.out.println(num);
                }
                num++;
            }
            System.out.println("任务完成, 线程中断状态: " + Thread.currentThread().isInterrupted());
        });

        thread.start();

        // 主线程休眠50毫秒
        Thread.sleep(50);

        // 中断线程
        thread.interrupt();
    }
}
```

### 在线程休眠时中断

如果线程在休眠过程,就无法检测中断状态. 所以`sleep`方法在中断时会抛出`InterruptedException`异常

`InterruptedException`是检查异常,编译器会要求必须捕`try catch`或者`throws`

#### 正常的休眠

```java
public class WithSleepWay {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            long start = System.currentTimeMillis();

            int num = 0;
            int limit = Integer.MAX_VALUE / 2;

            while (!Thread.currentThread().isInterrupted() && num <= limit) {
                if (num % 100000 == 0) {
                    System.out.println(num);
                }
                num++;
            }

            try {
                // 当前线程休眠1秒(1000毫秒)
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("任务完成, 时间: " + (System.currentTimeMillis() - start ) + "毫秒");
        });

        thread.start();

        // 主线程休眠500毫秒
        Thread.sleep(500);

        // 中断线程
        thread.interrupt();
    }
}
```

#### 循环休眠

```java
public class WithSleepWay {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            long start = System.currentTimeMillis();
            int num = 0;

            while (!Thread.currentThread().isInterrupted() && num <= 1000) {
                if (num % 100 == 0) {
                    System.out.println(num);
                }
                num++;

                try {
                    // 当前休眠10毫秒
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println(Thread.currentThread().isInterrupted());
                }
            }
            System.out.println("任务完成, 时间: " + (System.currentTimeMillis() - start ) + "毫秒");
        });

        thread.start();

        // 主线程休眠3秒
        Thread.sleep(3000);

        // 中断线程
        thread.interrupt();
    }
}
```

运行实例可以看到在三秒后抛出`InterruptedException`异常,但线程并未停止.并且在`while`条件也是判断了中断状态.按道理说是会停止的呀!

是因为`sleep`函数会重置中断状态.所以就会导致`while`循环中的条件不生效

解决办法: 将`try catch`语句放置在`while`循环外,一旦发生异常即可停止

```java
public class WithSleepWay {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            long start = System.currentTimeMillis();
            int num = 0;

            try {
                // !Thread.currentThread().isInterrupted() 不需要检查,如果已经被中断`sleep`方法会抛出异常
                // while (!Thread.currentThread().isInterrupted() && num <= 1000)
                while (num <= 1000) {
                    if (num % 100 == 0) {
                        System.out.println(num);
                    }
                    num++;
                    
                    // 当前休眠10毫秒
                    Thread.sleep(10);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("任务完成, 时间: " + (System.currentTimeMillis() - start ) + "毫秒");
        });

        thread.start();

        // 主线程休眠3秒
        Thread.sleep(3000);

        // 中断线程
        thread.interrupt();
    }
}
```

**如果在每次循环都调用`sleep`方法(或者其他的可中断方法).`isInterrupted`检查即没有必要也没有用处**

**如果在中断状态为`true`时调用`sleep`方法,它不会休眠.相反,它将清除中断状态并抛出`InterruptedException`异常**

**因此,如果要在循环中调用`sleep`方法,正确的做法是:捕获`InterruptedException`异常**

## 异常处理的两种最佳实践

**请勿压制中断异常**(`InterruptedException`),**如果选择在底层代码压制,那么高层代码在调用时将无法检测**;可以选择:

1. **在`catch`子句中调用`Thread.currentThread().interrupt()`来重新设置中断状态.调用者可以使用`isInterrupted`方法检测**

2. **更好的选择是,用`throws InterruptedException`标记你的方法,不采用`try`语句块捕获异常.调用者可以捕获这一异常**

## 总结

`sleep`方法会清除中断状态