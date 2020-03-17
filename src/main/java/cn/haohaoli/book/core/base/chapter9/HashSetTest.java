package cn.haohaoli.book.core.base.chapter9;

import lombok.AllArgsConstructor;

import java.util.*;

/**
 * @author LiWenHao
 * @date 2019-05-26 17:26
 */
public class HashSetTest {

    public static void main(String[] args) {

        Set<User> set = new HashSet<>();

        // 未重写hashCode/equals(id)的对象
        set.add(new UserNoHashCodeAndEq("sw12", "李", 18));
        set.add(new UserNoHashCodeAndEq("sw12", "王", 21));

        // 重写hashCode/equals(id)的对象
        set.add(new UserHasHashCodeAndEq("r3s1", "陈", 20));
        set.add(new UserHasHashCodeAndEq("ayr4", "张", 19));
        set.add(new UserHasHashCodeAndEq("r3s1", "陈", 20));
        System.out.println(set);

        /* 结果
         *  没有重写hashCode/equals的对象则会出现重复的.而根据规则重写了的对象则不会出现重复,
         *  并且可以看到hashSet是无序的,元素的顺序并不是添加的顺序
         * [
         *  UserNoHashCodeAndEq[id='sw12', name='王', age=21],
         *  UserHasHashCodeAndEq[id='ayr4', name='张', age=19],
         *  UserHasHashCodeAndEq[id='r3s1', name='陈', age=20],
         *  UserNoHashCodeAndEq[id='sw12', name='李', age=18]
         * ]
         */

    }

    @AllArgsConstructor
    static abstract class User {

        // private String id; //- 子类是不能访问父类的私有属性 `5.1.10 - 访问修饰符`中有提及
        String  id;
        String  name;
        Integer age;

        @Override
        public String toString() {
            return new StringJoiner(", ", this.getClass().getSimpleName() + "[", "]")
                    .add("id='" + id + "'")
                    .add("name='" + name + "'")
                    .add("age=" + age)
                    .toString();
        }
    }

    /**
     * 没有重写equals/hashCode方法的类.
     * <p>
     * 这里将调用直接调用`Object`类中的方法,因为`User`类并没有重写方法.所以就会一直往上找
     * 而在`Object`类中equals方法是直接用"=="对两个对象进行对比
     * <p>
     * 这里显式的重写是为了更直观,没有重写会在调用的时候调用父类中的方法.这很基础~
     */
    static class UserNoHashCodeAndEq extends User {

        public UserNoHashCodeAndEq(String id, String name, Integer age) {
            super(id, name, age);
        }

        @Override
        public boolean equals(Object obj) {
            return super.equals(obj);
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }

    }

    /**
     * 有重写equals/hashCode方法的类. 仅使用id进行hash
     */
    static class UserHasHashCodeAndEq extends User {

        public UserHasHashCodeAndEq(String id, String name, Integer age) {
            super(id, name, age);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            User user = (User) o;
            return id.equals(user.id);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }
    }
}
