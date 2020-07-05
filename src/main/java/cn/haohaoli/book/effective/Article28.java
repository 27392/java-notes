package cn.haohaoli.book.effective;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 28.利用有限制通配符来提升API的灵活性
 *
 * @author LiWenHao
 */
public class Article28 {

    public static void main(String[] args) {

        // TODO PECS 表示 producer-extends, consumer-super

        // 换句话说,如果参数化类型表示一个`T`生产者,就使用`<? extends T>`; 如果它表示一个`T`消费者,就是用`<? super T>`

        /*
            `pushAll2`的参数`src`参数产生`E`实例供`Stack`使用,因此`src`相应的类型为`Iterable<? extends E>`
            `popAll2`的参数`dst`参数通过`Stack`消费`E`实例,因此`dst`相应的类型为`Collection<? super E>`
         */

        // pushAll2 方法; <? extends E> 读
        producer();

        // popAll2 方法; <? super E>  写
        consumer();

    }

    private static void producer() {

        Stack<String> stringStack = new Stack<>();
        List<String>  strings     = new ArrayList<>();

        // 如果`strings`的元素类型与`stringStack`的完全匹配,那就没有问题
        stringStack.pushAll(strings);

        Stack<Number> numberStack = new Stack<>();
        List<Integer> integers    = new ArrayList<>();

        // 不管`Number`和`Integer`是什么关系,在泛型中它们都没有任何关系,所以这里是错误的
        // numberStack.pushAll(integers);    // 错误

        // 而应将其修改为"E 的某个子类型的Iterable接口"
        numberStack.pushAll2(integers);

    }

    private static void consumer() {

        Stack<String> stringStack = new Stack<>();
        List<String>  strings     = new ArrayList<>();

        // 同理`stringStack1`的元素类型与`strings2`的完全匹配,那就没有问题
        stringStack.popAll(strings);

        Stack<Number> numberStack = new Stack<>();
        List<Object>  objects     = new ArrayList<>();

        // 这里也一样,在泛型中,Number与Object并没有什么关系,所里这里也是错误的
        // numberStack1.popAll(objects);     // 错误

        // 而应将其修改为"E 的某种超类的集合"
        numberStack.popAll2(objects);

    }

    private static class Stack<E> {

        public void push(E e) {
        }

        public E pop() {
            return null;
        }

        public boolean isEmpty() {
            return false;
        }

        public void pushAll(Iterable<E> src) {
            src.forEach(this::push);
        }

        /*
            将集合中的元素添加到栈中
                这里是(读)操作,在`popAll2`方法中解释过如果使用`<? super E>`的话是不可以读取数据的
                所以这里我们使用`<? extends E>`
         */
        public void pushAll2(Iterable<? extends E> src) {
            src.forEach(this::push);
        }

        public void popAll(Collection<E> dst) {
            while (!isEmpty()) {
                dst.add(pop());
            }
        }

        /*
            将栈中的元素添加到指定的集合中
                这里的操作很明显是(写)的操作,如果我们使用`Collection<? super E>`的话,它是不可以写入元素的
                    例如`E`是`Employee`,编译器只知道需要某个`Integer`的子类型,但是不知道具体需要什么类型,毕竟`?`不能用来匹配
                    但是我们使用从中读取内容的话就不存在这个问题;将元素赋值给一个 E 的引用是完全没有问题的.(就好比子类赋值给父类,这并不会出现什么问题)
                而这里我们使用的`Collection<? super E>`,它限制为`E`的所有的超类型
                    例如`E`是`Manager`,那么可以向它传递,`Employee`,Object`等类,而不能传递`Executive`类
                    如果调用读取读取方法只能将它赋值给一个`Object`
             所以在写入的时候需要使用`<? super E>`,而不是使用`<? extend E>`
         */
        public void popAll2(Collection<? super E> collection) {
            while (!isEmpty()) {
                collection.add(pop());
            }
        }
    }

    static class Employee {
    }

    static class Manager extends Employee {
    }

    static class Executive extends Manager {
    }

}
