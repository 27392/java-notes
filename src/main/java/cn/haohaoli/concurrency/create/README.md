# 创建线程

## 官方文档解释

以下内容来自Thread类

> **有两种方法可以创建新的执行线程。** 

**一种是将一个类声明为Thread的子类。该子类应重写Thread类的run方法。然后可以分配并启动子类的实例**。例如，计算素数大于指定值的线程可以编写为：

```java
class PrimeThread extends Thread {
   long minPrime;
   PrimeThread(long minPrime) {
       this.minPrime = minPrime;
   }

   public void run() {
       // compute primes larger than minPrime
        . . .
   }
}
```

下面的代码将创建一个线程并使其开始运行：
```java
PrimeThread p = new PrimeThread(143);
p.start();
```

**创建线程的另一种方法是声明一个实现Runnable接口的类。然后，该类实现run方法。然后可以分配该类的实例，在创建Thread时将其作为参数传递并启动**。 其他样式的相同示例如下所示：

```java
class PrimeRun implements Runnable {
   long minPrime;
   PrimeRun(long minPrime) {
       this.minPrime = minPrime;
   }

   public void run() {
       // compute primes larger than minPrime
        . . .
   }
}
```

下面的代码将创建一个线程并使其开始运行：
```java
PrimeRun p = new PrimeRun(143);
new Thread(p).start();
```
 
## 结论

从文档上得出的结果是.**有两种方式可以创建线程**,这一点毋庸置疑

但准确的讲,**创建线程的只有一种那就是构造`Thread`类,这里说的两种是说: 实现线程执行单元的方式有两种**

它们本质上并没有什么区别.最终都是执行`run`方法.只是`run`方法的来源不同

