## 通过行为参数化传递代码

在软件工程中,一个众所周知的问题就是,不管你做什么,用户的需求肯定会变

有个应用程序是帮助农民了解自己的库存的.这位农民可能想有一个查找库存中所有绿色苹果的功能

但到了第二天,他可能会告诉你：“其实我还想找出所有重量超过150克的苹果”

又过了两天,农民又跑回来补充道：“要是我可以找出所有既是绿色,重量也超过150克的苹果,那就太棒了”

你要如何应对这样不断变化的需求？(理想的状态下,应该把你的工作量降到最少.此外,类似的新功能实现起来还应该很简单,而且易于长期维护)

> 答案是: `行为参数化`

### 何为行为参数化?

**行为参数化是可以处理频繁边改的需求的一种软件开发模式**

它可以拿出一个代码块,把它准备好却不去执行它.

这个代码块以后可以被你的程序的其他部分调用,这意味着你可以推迟这块代码的执行.

> 例如,你可以将代码块作为参数传递给另一个方法,稍后再去执行它.这样,这个方法的行为就基于那块代码被参数化了
>
> 具体这样做的好处在下面的例子中会体现出来

### 应对不断变化的需求

#### 筛选绿苹果

第一个需求是`实现一个从列表中筛选绿苹果的功能`

```java
private static List<Apple> filterGreenApples(List<Apple> inventory) {
    ArrayList<Apple> result = new ArrayList<>();

    for (Apple apple : inventory) {
        if ("green".equals(apple.getColor())) {
            result.add(apple);
        }
    }
    return result;
}
```

当实现功能好之后,农民又改主意了,他还想要筛选红苹果

简单的解决办法就是复制这个方法,把名字改成`filterRedApples`,然后更改`if`条件来匹配红苹果.

然而，要是农民想要筛选多种颜色：浅绿色、暗红色、黄色等，这种方法就应付不了了

**一个良好的原则是在编写类似的代码之后,尝试将其抽象化**

#### 把颜色作为参数

在之前方法的基础上加上颜色参数就能灵活地适应变化

```java
private static List<Apple> filterApplesByColor(List<Apple> inventory, String color) {
    ArrayList<Apple> result = new ArrayList<>();

    for (Apple apple : inventory) {
        if (color.equals(apple.getColor())) {
            result.add(apple);
        }
    }
    return result;
}
```

此时这位农民又跑回来和你说：“要是能区分轻的苹果和重的苹果就太好了,重的苹果一般是重量大于150克

于是,直接复制了筛选颜色的方法修改判断条件将颜色换成重量,很快的就完成了

```java
private static List<Apple> filterApplesByWeight(List<Apple> inventory, int weight) {
    ArrayList<Apple> result = new ArrayList<>();

    for (Apple apple : inventory) {
        if (apple.getWeight() > weight) {
            result.add(apple);
        }
    }
    return result;
}
```

但是这样复制的话,两个方法中大部分的代码都是重复的.它打破了`DRY(Don’t Repeat Yourself，不要重复自己)`的软件工程原则

那么可以将颜色和重量结合为一个方法

#### 对你能想到的每个属性做筛选

目前有颜色和重量两个属性,结合为一个方法的话,可以使用一个标志来区分是跟进颜色筛选还是根据重量筛选

```java
private static List<Apple> filterApples(List<Apple> inventory, String color, int weight, boolean flag) {
    List<Apple> result = new ArrayList<>();

    for (Apple apple : inventory) {
        if ((flag && apple.getColor().equals(color))
                || (!flag && apple.getWeight() > weight)) {
            result.add(apple);
        }
    }
    return result;
}
```

功能是写完了,但是这样的代码看上去并不是很好,`if`中的条件判断很糟糕,`flag`的语义也不是那么明确

如果这位农民要求你对苹果的不同属性做筛选,比如大小、形状、产地等组合属性,做更复杂的查询,这种做法就不适用了

#### 根据抽象条件筛选

更好的做法是将变化的部分抽取出来,而变化的部分则是(对`Apple`的某些属性判断)

将对`Apple`属性判断的方法提取为一个接口,而具体的判断由子类实现

```java
public interface ApplePredicate{
    boolean test (Apple apple);
}
```

例如: `AppleGreenColorPredicate`只选出绿色的苹果,`AppleHeavyWeightPredicate`则是选出重量大于150克的苹果

```java
public class AppleGreenColorPredicate implements ApplePredicate {

    @Override
    public boolean test(Apple apple) {
        return "green".equals(apple.getColor());
    }
}
```

```java
public class AppleHeavyWeightPredicate implements ApplePredicate {

    @Override
    public boolean test(Apple apple) {
        return apple.getWeight() > 150;
    }
}
```

`ApplePredicate`UML图

![](https://blog-haohaoli.oss-cn-chengdu.aliyuncs.com/java8/%E6%88%AA%E5%B1%8F2020-07-19%20%E4%B8%8B%E5%8D%8811.23.40.png)


改造方法,传入`ApplePredicate`替换原有的变化点,直接调用`test`方法,判断逻辑由子类接管从而变得更加灵活

```java
public static List<Apple> filterApples(List<Apple> inventory, ApplePredicate predicate) {
    List<Apple> result = new ArrayList<>();

    for (Apple apple : inventory) {
        if (predicate.test(apple)) {
            result.add(apple);
        }
    }
    return result;
}
```

这段代码比我们第一次尝试的时候灵活多了,读起来、用起来也更容易

现在可以创建不同的`ApplePredicate`对象来完成更复杂的筛选,而不用去改变`filterApples`中的代码

如果农民让你找出所有重量超过150克的红苹果,你只需要创建一个类来实现ApplePredicate就行了

现在的代码现在足够灵活,可以应对任何涉及苹果属性的需求变更了

```java
public class AppleRedAndHeavyPredicate implements ApplePredicate {
    public boolean test(Apple apple) {
        return "red".equals(apple.getColor())
                && apple.getWeight() > 150;
    }
}
```

> `filterApples`方法的行为取决于你通过ApplePredicate对象传递的代码.
> 
> 换句话说,你把filterApples方法的行为参数化了！

这样做也不是没有任何的缺点

当要把新的行为传递给`filterApples`方法的时候,你不得不声明好几个实现`ApplePredicate`接口的类

然后实例化好几个只会提到一次的`ApplePredicate`对象,这真是很啰嗦，很费时间!

#### 使用匿名类

使用匿名类,它可以让你同时声明和实例化一个类.它可以帮助你进一步改善代码,让它变得更简洁

```java
List<Apple> redApples = filterApples(inventory, new ApplePredicate() {
        public boolean test(Apple a) {
            return "red".equals(a.getColor());
        }
    }
);
```

即使匿名类处理在某种程度上改善了为一个接口声明好几个实体类的啰嗦问题,但它仍不能令人满意

#### 使用 Lambda 表达式

上面的代码在Java 8里可以用`Lambda`表达式重写为下面的样子

```java
filterApples(inventory, (Apple apple) -> "red".equals(apple.getColor()))
```

#### 将 List 类型抽象化(使用泛型)

在使用`Lambda` 表达式后我们的代码变的更为简洁了,但是我们还可以更进一步

目前,`filterApples`方法还只适用于`Apple`.你还可以将`List`类型抽象化,从而超越你眼前要处理的问题

```java
public interface Predicate<T> {
    boolean test(T t);
}
```

```java
private static <T> List<T> filter(List<? extends T> list, Predicate<T> predicate) {
    List<T> result = new ArrayList<>();

    for (T e : list) {
        if (predicate.test(e)) {
            result.add(e);
        }
    }
    return result;
}
```

现在`filter`不仅仅只是能筛选`Apple`,还是筛选香蕉、桔子甚至是`String`、`Integer`的列表

```java
// 筛选能被2整除的数
filter(Arrays.asList(1, 2, 3, 4, 5), (Integer i) -> i % 2 == 0)
```

### 小结

1. 行为参数化,就是一个方法接受多个不同的行为作为参数,并在内部使用它们,完成不同行为的能力

2. 行为参数化可让代码更好地适应不断变化的要求,减轻未来的工作量

3. 传递代码,就是将新行为作为参数传递给方法.但在Java 8之前这实现起来很啰嗦.为接口声明许多只用一次的实体类而造成的啰嗦代码,在Java 8之前可以用匿名类来减少

4. Java API包含很多可以用不同行为进行参数化的方法，包括排序、线程和GUI处理