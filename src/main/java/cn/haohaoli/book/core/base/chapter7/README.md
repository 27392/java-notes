# Java 核心技术 卷I - 第七章

## 7.1 - 处理错误

假设在一个Java程序运行期间出现了一个错误:

这个错误可能时由于文件包含了错误的信息,或者网络连接出现问题造成的,也有可能因为使用无效的数值下标,或者试图使用一个没有被赋值的对象引用而造成的

用户期望在出现错误时,程序能够采用一些理智的行为.如果由于出现错误而使得某些操作没有完成,程序应该:

 - 返回到一种安全状态,并能够让用户执行一些其他的命令
 - 或者允许用户保存所有操作的结果,并以妥善的方式终止程序

要做到这些并不是一件很容易的事情

其原因是**检测(或引发)错误条件的代码通常离那些能够让数据恢复到安全状态,或者能够保存用户的操作结果,并正常地退出程序的代码很远**.

**异常处理的任务就是将控制权从错误产生的地方转移给能够处理这种情况的错误处理器**

> 在Java中,如果某个方法不能够采用正常的途径完整的完成它的任务,就可以通过另外一个路径退出方法
> 
> **在这种情况下,方法并不返回任何值,而是抛出`throw`一个封装了错误信息的对象.需要注意的是,这个方法将会立刻退出,并不返回任何值**
>
> **此外,调用这个方法的代码也将无法继续执行,取而代之的是,异常处理机制开始搜索能够处理这种异常状况的异常处理器**

### 7.1.1 - 异常分类

**在Java中,异常对象都是派生域`Throwable`类的一个实例**

Java异常层次简化结构如下:
    
             Throwable
      ┌──────────┴───────────┐
    Error                 Exception
                    ┌─────────┴──────────┐
                IOException     RuntimeException

需要注意的是,**所有的异常都是有`Throwable`继承而来**,但是下一层立即分解为两个分支: `Error`和`Exception`

**`Error`类层次结构描述了程序运行时系统的内部错误和资源耗尽错误.程序不应该抛出这种类型的对象**
 
 - 如果出现这种的内部错误,除了通告给用户,并尽力的使程序安全的终止之外,再也无能为力了.但是这种情况一般很少出现

而我们更需要关注`Exception`层次结构.这个层次结构又有两个分支: 一个分支派生于`RuntimeException`,并一个分支包含其他异常

 - 划分两个分支的规则是: **由程序错误导致的异常属于`RuntimeException`**;**而程序本身没有问题,但由于像I/O错误这种问题导致的异常属于其他异常** 

派生于`RuntimeException`的异常包含下面几种情况:

 - **错误的类型转换**
 - **数组访问越界**
 - **访问`null`指针**

不是派生于`RuntimeException`的异常包含:

 - **试图在文件尾部后面读取数据**
 - **试图打开一个不存在的文件**
 - **试图根据给定的字符串查找`Class`对象,而这个字符串表示的类不存在**

**如果出现`RuntimeException`异常,那么就一定是你的问题**,这是一条相当有道理的规则.

> Java将派生于`Error`类或`RuntimeException`类的所有异常称为非受检查异常,所有其他的异常称为受查异常

### 7.1.2 - 声明受查异常

如果遇到了无法处理的情况,Java的方法可以抛出一个异常

这个道理很简单: **一个方法不仅需要告诉编译器将要返回什么值,还要告诉编译器有可能发生什么错误**

例如,一段读取文件的代码知道有可能读取的文件不存在,获取内容为空

 - 因此,试图处理文件信息的代码就需要通知编译器可能会抛出`IOException`类的异常

方法应该在其首部声明所有可能抛出的异常.这样可以从首部方法出这个方法可能抛出哪类受查异常.例如:

```java
public static Class<?> forName(String className) throws ClassNotFoundException 
```

> 这个声明表示这个方法将根据给定的`String`参数产生一个`Class`对象,但也有可能抛出一个`ClassNotFoundException`异常
>
> 如果发生了这种糟糕的情况,构造器将不会初始化一个新的`Class`对象,而是抛出一个`ClassNotFoundException`类对象
>
> 如果这个方法方法真的抛出了这样一个异常对象,运行时系统就会开始搜索异常处理器,以便知道如何处理`ClassNotFoundException`

**在自己编写方法时,不必将所有可能抛出的异常都进行声明**

至于什么时候需要在方法中用`throws`子句声明异常,什么异常必须使用`throws`子句声明,需要记住在遇到下面4种情况时应该抛出异常:

 1. **调用一个抛出受查异常的方法**,例如: FileInputStream构造器
 
 2. **程序运行过程中发现错误**,并且利用`throw`语句抛出一个受查异常
 
 3. **程序出现错误**,例如: `a[-1] =0`会抛出一个`ArrayIndexOutBoundsException`这样的非受查异常
 
 4. **Java虚拟机和运行时库出现的内部错误**

如果出现前两种情况之一,则必须告诉调用方有可能抛出的异常.为什么要这么做?
  
  - 因为任何一个抛出异常的方法都有可能是一个死亡陷阱,如果没有处理器捕获这个异常,当前执行的线程就会结束
 
**对于那些可能被他人使用的方法,应该根据异常规范在方法的首部声明这个方法可能抛出的异常**

```java
public static Class<?> forName(String className) throws ClassNotFoundException
```

如果一个方法有可能抛出多个受查异常类型,那么就必须在方法的首部列出所有的异常类,每个异常类之间用逗号隔开.例如:

```java
public T newInstance() throws InstantiationException, IllegalAccessException
```

但是,不需要声明Java的内部错误,就是从`Error`继承的错误.任何程序代码都具有抛出那些异常的潜能,而我们对其没有任何控制能力
 
同样,也不应该声明从`RuntimeException`继承的那些非受检查的异常,因为这些运行时错误完全在我们的控制之下.

```java
// NullPointerException 继承于 RuntimeException
public void print() throws NullPointerException
```

**如果特别关注数组下标引发的错误,就一个将更多的时间花费在修正程序中的错误上,而不是说明这些错误发生的可能性上**

> 总之,一个方法必须声明所有可能抛出的受查异常,而非受查异常要么不可控制(Error),要么就应该避免发生(RuntimeException)
>
> 如果方法没有声明所有可能发生的受查异常,编译器将会发出错误信息

### 7.1.3 - 抛出异常

`print()`方法用于打印一个字符.然而我们希望参数不为空,如果为空我们认为这是一种不正常的情况,希望抛出(`throw`)一个异常

但是首先要决定应该抛出什么类型的异常,将上述异常归结为`RuntimeException`是一种很好的选择

```java
/**
 * @param
 * @throws NullPointerException
 */
public void print (String str) {
    if (null == str) {
        throw new NullPointerException();
    }
    System.out.print(str);
}
```

> 可以将方法中抛出的(非检查)异常在注释`@throws`中标明,提高可读性

对于一个已经存在的异常我们只需要,找到合适的异常类然后创建该类的对象将其抛出

**一旦方法抛出了异常,这个方法就不可能返回到调用者.也就是说,不必为返回的默认值或错误代码担忧**

### 7.1.4 - 创建自定义异常

在程序中,可能会遇见任何标准异常类都没有能够充分描述清楚的问题,在这种情况下,创建自己的异常类就是一件顺理成章的事情了

我们只需要定义一个派生于`Exception`或是派生于`Exception`子类的类

例如,定义一个派生于`RuntimeException`的类

```java
public class PrintException extends RuntimeException {
    
    public PrintException(){
    }

    public PrintException(String message) {
        super(message);
    }
}
```

**习惯上,定义的类应该包含两个构造器,一个是默认构造器;另一个是带有详细描述信息的构造器**

 - 超类`Throwable`的`toString`方法会打印这些详细信息,这在调试中非常有用

现在就可以抛出自己定义的异常类型了

```java
/**
 * @param
 * @throws PrintException
 */
public void print (String str) {
    if (null == str) {
        throw new PrintException();
    }
    System.out.print(str);
}
```

## 7.2 - 捕获异常

我们已经知道了如果抛出一个异常.这个过程十分的简单.只要将其抛出就不用理了,但是有些代码必须捕获异常

如果某个异常发生的时候没有在任何地方进行捕获,那程序就会终止执行,并在控制台上打印异常信息,其中包含异常的类型和堆栈内容

想要捕获一个异常,必须设置`try/catch`语句块.例如:

```java
try {
    // 代码
} catch (Exception e){
    // 处理语句
}
```

如果在`try`语句块中任何代码抛出了一个在`catch`子句中的异常类,那么:

1. 程序将跳过`try`语句块的其余代码

2. 程序将执行`catch`子句中的代码

**如果在`try`语句块中的代码没有抛出任何异常,那么程序将跳过`catch`子句**

**如果方法中的任何代码抛出了一个在`catch`子句中没有声明的异常类型,那么这个方法就会立即退出**

### 7.2.2 捕获多个异常

在一个`try`语句块中可以捕获多个异常类型,并对不同类型的异常做出不同的处理.可以按照下列方法为每个异常类型使用一个独立的`catch`子句

```java
try {
    // 代码
} catch (FileNotFoundException e) {
    // 处理语句
} catch (IoException e) {
    // 处理语句
}
```

> 异常对象可能包含于异常本身有关的信息,要获取对象的更多信息,可以使用`e.getMessage()`

在JDK7中,同一个`catch`子句中可以捕获多个异常类型

```java
try {
    // 代码
} catch (FileNotFoundException | NullPointerException e ) {
    // 处理语句
}
```

### 7.2.3 再次抛出异常与异常链

**在`catch`子句中可以在抛出一个异常,这样做的目的是改变异常的类型**

假如我们在开发一个邮件子系统,那么,用于表示子系统故障的异常类型可能会有多种解释;例如:

```java
try {
    // 发送邮件
} catch (AuthenticationFailedException ex) {
    throw new MailAuthenticationException("Authentication failed");
} 
```
> 这里的`MailAuthenticationException`用带有异常信息文本的构造器来构造
>
> 不过有可以有一种更好的处理方式,并且将原始的异常设置为新异常的"原因"

```java
try {
    // 发送邮件
} catch (AuthenticationFailedException ex) {
    Throwable se = new AuthenticationFailedException("Authentication failed");
    se.initCause(e);
    throw se;
}
```

> 在Throwable中有包含异常信息和原因的构造器 `Throwable(String message, Throwable cause)`,
>
> 或只有原因的构造器`Throwable(Throwable cause)`
>
> 只需要在我们自定义的异常中定义相同的构造器调用父类构造器

```java
try {
    // 发送邮件
} catch (AuthenticationFailedException ex) {
    throw new AuthenticationFailedException("Authentication failed", ex);
}
```

当捕获到异常时,就可以使用下面的语句重新获得原始的异常:

```java
Throwable e = ex.getCause();
```

**强烈建议使用这种包装技术.这样可以让用户抛出子系统中的高级异常,而不会丢失原始异常的细节**

**如果在一个方法中发生了一个受查异常,而不允许抛出它,那么包装技术就十分有用. 我们可以捕获这个异常,并将它包装秤一个运行时异常**

有时你可能只想记录异常,在将它重新抛出,而不做任何的改变:

```java
try {
    // 发送邮件
} catch (AuthenticationFailedException ex) {
    // 记录日志
    throw ex;
}
```

> 参考**`spring-boot-starter-mail`**包中的邮件代码和异常体系

### 7.2.4 finally子句

当代码抛出一个异常时,就会终止方法中剩余代码的处理,并退出这个方法的执行.

如果方法获得了一些本地资源,并且只有这个方法自己知道,有如果这些资源在退出方法之前必须被回收,那么就会产生资源回收问题

一种解决方案是捕获并重新抛出所有的异常.但是这种解决方法不是很理想,这是因为需要在两个地方清除所分配的资源.

一个在正常的代码中,而另一个在异常的代码中

```java
BufferedReader br = null;
try {
    br = new BufferedReader(new FileReader(file));
    String s;
    while (null != (s = br.readLine())) {
        System.out.println(s);
    }
    // 正常的代码中清除所分配的资源
    br.close();
} catch (IOException e) {
    // 异常的代码中清除所分配的资源
    br.close();
    e.printStackTrace();
}
```

而在Java中有一种更好的解决方法,就是`finally`子句

**不管是否有异常被捕获,`finally`子句中的代码都被执行:**

````java
BufferedReader br = null;
try {
    // 1
    br = new BufferedReader(new FileReader(file));
    String s;
    while (null != (s = br.readLine())) {
        System.out.println(s);
    }
    // 2
} catch (IOException e) {
    // 3
    e.printStackTrace();
    // 4
} finally {
    // 5
    br.close();
}
// 6
````
+ 在上面的代码中,有下列三种情况会执行`finally`子句
 
  - **代码没有抛出异常.**
     
     在这种情况下,程序首先执行`try`语句块中的全部代码,然后执行`finally`子句中的代码.
     
     随后,继续执行`try`语句块之后的第一条语句,也就是说,执行标注的`1,2,5,6`处
  
  - **抛出一个在`catch`子句中捕获的异常.在上面的实例中就是`IOException`异常**
  
     在这种情况下,程序将执行`try`语句块中的所有代码,知道发生异常为止.
     
     此时,将跳过`try`语句块中剩余代码,转去执行与改异常匹配的`catch`子句中的代码,最后执行`finally`子句中的代码
     
       + 如果`catch`子句没有抛出异常,程序将执行`try`语句块之后的第一条语句. 执行标注`1,3,4,5,6`处的语句
       + 如果`cathc`子句抛出异常,异常将被抛会这个方法的调用者,执行标注`1,3,5`处的语句
     
  - **代码抛出一个异常,但这个异常不是由`catch`子句捕获的.**
  
     在这种情况下,程序将执行`try`语句块中的所有语句,知道有异常被抛出为止.
     
     此时,将跳过`try`语句块中剩余代码,然后执行`finally`子句中的语句,并将异常抛给这个方法的调用者,执行标注`1.5`处的语句

> **`try`语句可以只有`finally`子句,而没有`catch`子句**
>
> **无论在`try`语句块中是否遇到异常,`finally`子句中`br.close()`语句都会被执行**
>
> **如果真的遇到一个异常,这个异常将会被重新抛出,并且必须由另一个`catch`子句捕获**

```java
BufferedReader br = null;
try {
    br = new BufferedReader(new FileReader(file));
    String s;
    while (null != (s = br.readLine())) {
        System.out.println(s);
    }
} finally {
    br.close();
}
```

> **强烈建议解耦合`try/catch`和`try/finally`语句块.这样可以提高代码的清晰度**
> 
> **内层的`try`语句块只有一个职责,就是确保关闭输入流.**
>
> **外层的`try`语句块也只有一个职责,就是确保报告出现的错误**
>
> **这种设计方式不仅清楚,而且还具有一个功能,就是将会报告`finally`子句中出现的错误**
>
```java
BufferedReader br = null;
try {
    try {
        br = new BufferedReader(new FileReader(file));
        String s;
        while (null != (s = br.readLine())) {
            System.out.println(s);
        }
    } finally {
        if (Objects.nonNull(br)) {
            br.close();
        }
    }
} catch (IOException e) {
    e.printStackTrace();
}
```

#### finally子句包含return语句

当`finally`子句中包含`return`语句时,将会出现一个意想不到的结果.

**假设利用`return`语句从`try`语句块中退出.在方法返回前,`finally`子句的内容将被执行.**

**如果`finally`子句中也有一个`return`语句,这个返回值将会不改原始的返回值**

```java
public static int finallyReturnExample (){
    try{
        return 1 / 1;
    } finally {
        System.out.println("finally！");
        return -1;
    }
}
```
**在方法执行时,`try`语句块的计算结果为"0",并执行`return`语句.**

**然而,在方法真正返回前,还要执行`finally`子句.`finally`子句将使用方法返回"-1",这个返回值覆盖了原始返回值"0"**

#### try语句块与finally语句块同时发生异常

现在假设在`try`语句块中的代码抛出了一些非`IOException`的异常,这些异常只有这个方法的调用者才能够给予处理

执行`finally`语句块,并调用`close`方法.而`close`方法本身也有可能抛出`IOException`异常.

当出现这种情况时,原始的异常将会丢失,转而抛出`close`方法的异常.

这会有问题,因为第一个异常很可能更有意思.如果要做适当的处理,重新抛出原来的异常,代码会变的极其繁琐.如下:

```java
public static void finallyThrowExample(File file) throws Exception {
    BufferedReader br = null;
    Exception      ex = null;
    try {
        br = new BufferedReader(new FileReader(file));
        print(br);
    } catch (IOException e) {
        ex = e;
        throw ex;
    } finally {
        try {
            if (Objects.nonNull(br)) {
                br.close();
            }
        } catch (Exception e) {
            // 当有异常时不在抛出
            if (null == ex) {
                throw e;
            }
        }
    }
}
```

### 7.2.5 带资源的try语句

在往常对资源进行操作通常像以下一样,我们在操作完或发送异常时手动释放资源

```java
BufferedReader br = null;
try {
    br = new BufferedReader(new FileReader(file));
    String s;
    while (null != (s = br.readLine())) {
        System.out.println(s);
    }
} catch (IOException e) {
    e.printStackTrace();
} finally {
    try {
        if (Objects.nonNull(is)) {
            is.close();
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}
```

假设资源是一个实现了`AutoCloseable`接口的类,在JDK7之后为这种代码模式提供了一个很有用的快捷方式.

> `AutoCloseable`接口只有一个方法
>
> ```java
> void close() throws Exception;
> ```

```java
try (BufferedReader br = new BufferedReader(new FileReader(file));) {
    String s;
    while (null != (s = br.readLine())) {
        System.out.println(s);
    }
} catch (IOException e) {
    e.printStackTrace();
}
```

> **在正常退出`try`块,或者存在一个异常时,都会调用`br.close`方法,就好像使用了`finally`块一样**

**还可以指定多个资源,多个表达式后面加(`;`),最后一个可以不加**

```java
try (InputStream inputStream = resource.openConnection().getInputStream();
     OutputStream outputStream = new FileOutputStream(file)) {
    byte[] buff = new byte[1024];
    while (inputStream.read(buff, 0, buff.length) != -1) {
        outputStream.write(buff, 0, buff.length);
        outputStream.flush();
    }
} catch (IOException e) {
    e.printStackTrace();
}
```

**无论这个块如果退出,`inputStream`和`outputStream`都会关闭**.如果你用常规的手动编程,就需要两个嵌套的`try/finally`语句.如下:

```java
InputStream  inputStream  = null;
OutputStream outputStream = null;
Exception    ex           = null;
try {
    inputStream  = resource.openConnection().getInputStream();
    outputStream = new FileOutputStream(file);
    byte[] buff = new byte[1024];
    while (inputStream.read(buff, 0, buff.length) != -1) {
        outputStream.write(buff, 0, buff.length);
        outputStream.flush();
    }
} catch (IOException e) {
    ex = e;
    throw ex;
} finally {
    try {
        if (null != inputStream) {
            inputStream.close();
        }
    } catch (Exception e) {
        if (null == ex) {
            ex = e;
            throw e;
        }
    }
    if (null != outputStream) {
        try {
            outputStream.close();
        } catch (Exception e) {
            if (null == ex) {
                throw e;
            }
        }
    }
}
```

> 这种方式显然很麻烦.所以需要关闭资源,就要尽可能使用带资源的`try`语句

#### 验证AutoCloseable接口

定义一个类实现`AutoCloseable`接口

```java
public class Close implements AutoCloseable {

    @Override
    public void close() {
        System.out.println("执行了close");
    }
}
```

使用`try-whit-resource`方法看是否会调用`close`方法

```java
public static void main(String[] args) {
    try (Close close = new Close()) {
        System.out.println(close.getClass().getSimpleName());
    } catch (Exception e) {
        e.printStackTrace();
    }
}
```
结果很显然依次输出了`Close`和`执行了close`

**所以得出结果,实现了`AutoCloseable`接口的类使用`try-whit-resource`方法时肯定会调用`close`方法**

> 此外`InputStream,OutputStream`都实现了`Closeable`接口.
> 
> `Closeable`接口它是`AutoCloseable`接口的子类,也包含一个`close`方法.不过,这个方法声明为抛出一个`IOException`

### 7.2.6 分析堆栈轨迹元素

**堆栈轨迹是一个方法调用过程的列表,它包含了程序执行过程中方法调用的特定位置**

  + `printStackTrace`方法
  
    可以使用`Throwable`类的`printStackTrace`方法访问堆栈轨迹的文本描述信息

    ```java
    Throwable    throwable = new Throwable();
    StringWriter out       = new StringWriter();
    throwable.printStackTrace(new PrintWriter(out));
    System.out.println(out.toString());
    ```
  + `printStackTrace`方法
  
    还可以使用`getStackTrace`方法,它同样是`Throwable`类的方法,它会得到StackTraceElement对象的一个数组
   
    `StackTraceElement`类含有能够获得文件名和当前执行的代码行号的方法,同时,还含有能够获得类名和方法名的方法.
   
    ```java
    Throwable           t        = new Throwable();
    StackTraceElement[] elements = t.getStackTrace();
    for (StackTraceElement stackTraceElement : elements) {
        System.out.println(stackTraceElement);
    }
    ```

## 7.3 - 使用异常机制的技巧

1. **异常处理不能代替简单的测试**
    
    试着一个空栈进行上百万次退栈操作,在退栈之前,首先查看栈是否为空,又或者直接捕获异常
    
    ```java
    Stack stack = new Stack();
    // 简单的测试
    for (int i = 0; i < 1000000; i++) {
        if (!stack.empty()) {
            stack.pop();
        }
    }
    // 直接捕获异常
    for (int i = 0; i < 1000000; i++) {
        try {
            stack.pop();
        } catch (EmptyStackException e) {
        }
    }
    ```
 
    通过测试可以的看出到与简单的测试相比捕获异常所花费的时间大大超过的了前者
    
    **因为异常处理会消耗大量的时间,所以我们只在异常情况下使用异常!**
    
2. **不要过分地细化异常**

    **不要将每一条语句分别装在一个独立的`try`语句块中.应该使用多个`catch`去捕获一个`try`块中的异常**
    
3. **利用异常层次结构**

    **不要只抛出`RuntimeException`.应该寻找更加适当的子类或者创建自己的异常,毕竟异常也是对象**

    **也不要只捕获`Throwable`或者`Exception`异常,选择使用哪种正确类型异常对程序的可读性很有必要**

4. **不要压制异常**

    **如果捕获异常,就应该在`catch`块中对他们进行处理**
 
5. **在检查错误时,'苛刻'要比放任更好**

    在用无效参数调用一个方法时,返回一个虚拟的数值,还是抛出一个异常?
    
    例如,当栈空时,`Stack.pop`是返回一个`null`,那么后面的代码可能会抛出`NullPointerException`异常
    
    **所以在出错的地方抛出`EmptyStackException`异常要在后面抛出`NullPointerException`异常更好**

6. **不要羞于传递异常**
    
    如果调用了一个抛出异常的方法,这些方法就会本能的捕获可能产生的异常
    
    其实,**传递异常要比捕获这些异常更好.如果过早的捕获,就无法传递给高层次的方法**
    
    那是不是一直抛到最上层的调用者.然后最上层也把它抛出去?当然不是!**要找到最恰当的时机去捕获,去做一些处理**
    
> 5,6 条可以归纳为'早抛出,晚捕获',更早的抛出能直接定位异常,也能更好的传递
> 
> [参考博客](https://dombro96.github.io/2017/12/11/Java%E4%BC%98%E9%9B%85%E7%9A%84%E5%A4%84%E7%90%86%E5%BC%82%E5%B8%B8/#3)

## 7.4 - 断言

## 7.5 - 日志 (JUL)

**在没有日志API之前**

调试代码的时候我们通常会插入一些`System.out.print()`方法来帮助我们观察程序运行的操作过程

当然,一旦发现问题的根源就要将这些语句从代码中删除.

如果接下来又出现了这个问题,就需要在插入几个`System.out.print()`语句

日志API就是为了解决这个问题而设计的.

**有日志API之后**

下面先讨论这些API的优化

- **可以很容易的取消全部日志记录,或者仅仅取消某个级别的日志,而且打开和关闭这个操作也很容易**

- **可以很简单的禁止日志记录的输出,因此将这些日志代码留在程序中的开销很小**

- **日志记录可以被定向到不同的处理器,用于在控制台中显示,用于存储在文件中等**

- **日志记录器和处理器都可以记录进行过滤.过滤器可以根据过滤实现器定制的标准丢弃那些无用的记录项**

- **日志记录器可以采用不用的方式格式化,例如,纯文本或XML**

- **应用程序可以使用多个日志记录器,他们使用类似包名的这种具有层次结构的名字,例如`cn.haohaoli.book.core.base.chapter7.log`**

- **在默认情况下,日志系统的配置有配置文件控制,如果需要的话,应用程序可以替换这个配置**

### 7.5.1 基本日志

那么想要生成简单的日志,可以使用全局日志记录器(`gloabl logger`)并调用其`info`方法

```java
Logger logger = Logger.getGlobal();
logger.info("基本的日志记录");
```

在默认的情况下,在控制台将显示以下内容

```text
十二月 16, 2019 10:49:49 上午 cn.haohaoli.book.core.base.chapter7.log.LogTest simpleLogRecordExample
信息: 基本的日志记录
```

### 7.5.2 高级日志

**在一个专业的应用程序中,不要将所有的日志都记录到全局日志记录器中,而是可以定义日志记录器**

> 日志记录器并不是只有全局的,是可以自定义日志记录器的

可以调用`getLogger`方法来创建或获取记录器

> 这里是创建或获取记录器(如果已经使用给定的名称创建了日志记录器,则返回该日志记录器.否则将创建一个新的日志记录器)

```java
private final static Logger LOGGER = Logger.getLogger("cn.haohaoli.book.core.base.chapter7.log");
```

> 未被任何变量引用的体质记录器可能会被垃圾回收.为了防止这种发生,要想上面的例子中一样,用一个静态变量储存日志变量的一个引用

与包名类似,日志记录器名称也具有层次结构.但是事实上.与包名相比,日志记录器的层次性更强.

对包来说,一个包的名字与其父包的名字之间没有语义关系,但是日志记录器的父与子将共享某些属性.

 - 例如,如果对`cn.haohaoli.book.core.base.chapter7.log`日志记录器设置了日志级别,他的子记录器也会继承这个级别

通常,有以下7个日志记录器级别(在`java.util.logging.Level`中有定义):

- SEVERE(最高)
- WARNING
- INFO
- CONFIG
- FINE
- FINER
- FINEST(最低)

在默认情况下,只记录前三个级别(`SEVERE, WARNING, INFO`).但是也可以设置其他级别

```java
LOGGER.setLevel(Level.FINE);
```

现在,`FINE`和更高级别的记录都可以记录下来

> 另外还可以设置为`Level.ALL`来开启所有记录的日志,或者使用`Level.OFF`关闭所有级别的记录

对于所有的级别有下面几种记录方法

```java
LOGGER.warning("warning");
LOGGER.fine("fine");

// 也可以log方法指定级别
LOGGER.log(Level.FINE, "fine");
```

> **默认的日志配置只记录了INFO或者更高级别的所有记录**
> 
> **因此应该使用`CONFIG`,`FINE`,`FINER`和`FINEST`级别那些有助于诊断,但一般没有什么用的调试信息**
>
> **应该使用低级别的等级来记录调试信息,高等级可以用来记录一些显式的信息,包括异常信息、警告信息等**

**注意:默认的日志处理器不会处理低于`INFO`级别的信息,如果要记录需要修改日志处理器配置**

#### 7.5.2.1 执行流和异常日志记录

##### 执行流记录

`entering`和`exiting`用来跟踪执行流的方法,例如

```java
public static String enteringAndExitingLogExample (String str) {
    LOGGER.entering(LogTest.class.getName(), "enteringAndExitingLogExample", str);
    String concat = str.concat(" - ").concat(str);
    LOGGER.exiting(LogTest.class.getName(), "enteringAndExitingLogExample", concat);
    return concat;
}
```

**调用方法后将输出以等级为`FINER`级别和以字符串`ENTRY`和`RETURN`开始的日志记录**

##### 异常记录

记录日志的常见用途是记录那些不可预料的异常,可以使用下面两个方法

```java
void throwing(String className, String methodName, Throwable t);
void log(Level l, String message, Throwable t);
```

实例

```java
public static void exceptionLogExample (String className) {
    try {
        Class.forName(className);
    } catch (ClassNotFoundException e) {
        // 记录异常有以下两种方式
        LOGGER.throwing(LogTest.class.getName(), "exceptionLogExample", e);
        // LOGGER.log(Level.WARNING, "错误", e);
    }
}
```

**调用`throwing`可以记录一条`FINER`级别和以字符串`THROW`开始的日志记录**

### 7.5.3 日志管理器配置

#### 加载配置文件

可以通过编辑配置文件来修改日志系统的各种属性

**在默认的情况下,配置文件存在于`jre/lib/logging/properties`**

如果想要使用另一个配置文件,就要将`java.util.logging.config.file`特性设置为配置文件的存储位置,并用下列命令启动应用程序

```shell script
java -Djava.util.logging.config.file=configFile mainClass
```

**日志管理器在虚拟机启动过程中初始化,在`main`方法执行之前完成**.如果`main`中调用

```java
System.setProperty("java.util.logging.config.file", "配置路径");
```

也会调用`LogManager.readConfiguration()`来重新初始化日志管理器

在书中还提到用`java.util.logging.LogManager`和`java.util.logging.config.class`处理.后面在了解用法

#### 配置文件

想要修改默认的日志记录级别,就要编辑配置文件,并修改以下命令行

```properties
.level=INFO
```

也可以通过添加以下内容来指定自己的日志记录级别

```properties
cn.haohaoli.book.core.base.level=FINE
```

> 也就是说,在日志记录器名后加`.level`

日志记录并不将消息发送到控制台上,这是处理器的任务.另外处理器也是有级别的

如果想要在控制台上看到`FINE`级别的消息,就需要进行下列设置

```properties
java.util.logging.ConsoleHandler.level=FINE
```

### 7.5.4 本地化

书中说的不是清楚,以后开发中遇到再补充

### 7.5.5 处理器

#### 控制台处理器

**在默认的情况下,日志记录器将记录发送到`ConsoleHandler`中并由它输出到`System.err`流中**

**特别的是,日志记录器(就是`logger`)还会将记录发送到父处理器中,而最终的处理器(命名为`""`),有一个`ConsoleHandler`**

**与日志记录器一样,处理器也有日志记录级别.对于一个要被记录的日志记录,它的日志记录级别必须高于日志记录器和处理的阈值**

日志管理器配置文件设置的默认控制台处理器的日志记录级别为

```properties
java.util.logging.ConsoleHandler.level=INFO
```

想要记录`FINE`级别的日志,就必须修改配置文件中的默认日志记录级别和处理器级别.这个在上面说过了

另外,还可以绕过配置文件,安装自己的处理器

```java
Logger LOGGER = Logger.getLogger("cn.haohaoli.book.core.base.chapter7.log.LogHandlerTest");
LOGGER.setLevel(Level.FINE);
LOGGER.setUseParentHandlers(false);     //是否使用父处理器
Handler consoleHandler = new ConsoleHandler();
consoleHandler.setLevel(Level.FINE);
LOGGER.addHandler(consoleHandler);
LOGGER.fine("fine");
```

在默认情况下,日志记录器(就是`logger`)将记录发送到自己的处理器和父处理器

我们自己的日志记录器是原始日志记录器(命名为`""`)的子类,而原始日志记录器将会把所有等于或等于`INFO`级别的记录发送到控制台

意思就是这样一来,我们现在就有两个`ConsoleHandler`处理器了,父类一个,然后我们自己有定义了一个

这样就会出现一条记录我们就会看到两遍,所以我们一个将`useParentHandlers`属性设置为`false`

#### 文件处理器和套接字处理器

想要日志记录发送到其他的地方,就要添加其他的处理器.

日志API为此提供了两个很有用的处理器,一个是`FileHandler`另一个是`SocketHandler`

`SocketHandler`将记录发送到特定的主机和端口,而更有用的是`FileHandler`它可以将记录收集到文件中

```java
Logger LOGGER       = Logger.getLogger("cn.haohaoli.book.core.base.chapter7.log.LogHandlerTest");
Handler fileHandler = new FileHandler();
LOGGER.addHandler(fileHandler);
```

这些记录被记录到用户主目录下,记录被格式化为`XML`

```xml
<record>
  <date>2019-12-17T11:40:11</date>
  <millis>1576554011719</millis>
  <sequence>0</sequence>
  <logger>cn.haohaoli.book.core.base.chapter7.log.LogHandlerTest</logger>
  <level>FINE</level>
  <class>cn.haohaoli.book.core.base.chapter7.log.LogHandlerTest</class>
  <method>main</method>
  <thread>1</thread>
  <message>fine</message>
</record>
```
可以通过设置日志管理器配置文件中的不同参数,或者利用其它构造器来修改文件处理器的默认行为

|               配置属性                |                   描述               |                 默认值           |
|                 ----                  |                   ----              |                 ----            |
|java.util.logging.FileHandler.level    |               处理器级别             |             Level.ALL           |
|java.util.logging.FileHandler.append   | 控制处理器应该追加到一个已经存在的文件尾部；还是应该为每个运行的程序打开一个新文件|false|
|java.util.logging.FileHandler.limit    | 在打开另一个文件之前允许写入一个文件的近似最大字节数( 0 表示无限制)|在 FileHandler 类中为 0 (表示无限制;在默认的日志管理器配置文件中为 50000|
|java.util.logging.FileHandler.pattern  | 日志文件名的模式,具体可以参考下面的表   |            %h/java%u.log         |
|java.util.logging.FileHandler.count    | 在循环序列中的日志记录数              |             1 (不循环）           |
|java.util.logging.FileHandler.filter   | 使用的过滤器类                       |           没有使用过滤器          |
|java.util.logging.FileHandler.encoding | 使用的字符编码                       |           平台的编码              |
|java.util.logging.FileHandler.formatter| 记录格式器                          | java.util.logging.XMLFormatter   |

日志记录文件模式变量

|    变量   |                   描述                                      |  
|   ----   |                   ----                                       |
|    %h    |  系统属性 `user.home` 的值                                    |
|    %t    |  系统临时目录                                                 |
|    %u    |  用于解决冲突的唯一编号                                        |
|    %g    |  为循环日志记录生成的数值.(当使用循环功能且模式不包括%g, 使用后缀%g)|
|    %%    |  % 宇符                                                       |

**如果有多个应用程序(或者用一个应用程序的多个副本)使用同一个日志文件,就应该开启`append`标识**

**另外,应该在文件名模式中使用`%u`,以便每个应用程序创建日志的唯一副本**

#### 自定义处理器

> 可以通过拓展`Handler`或者`StreamHandler`类自定义处理器

### 7.5.6 过滤器

### 7.5.6 格式化器

 

## 7.6 - 调试技巧
