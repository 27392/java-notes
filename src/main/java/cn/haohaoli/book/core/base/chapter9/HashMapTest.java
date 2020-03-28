package cn.haohaoli.book.core.base.chapter9;

import lombok.*;

import java.util.*;

/**
 * 资料参考 ： https://www.cnblogs.com/CarpenterLee/p/6507161.html
 * @author LiWenHao
 */
public class HashMapTest {

    public static void main(String[] args) {

        Map<String, Employee> staff = new HashMap<>(4);

        System.out.println(staff);

        System.out.println("========修改操作=========");
        System.out.println("put           " + staff.put("1001", Employee.of("张二")));
        System.out.println("put           " + staff.put("1002", Employee.of("李四")));
        System.out.println("put           " + staff.put("1001", Employee.of("张三")));
        System.out.println("remove        " + staff.remove("1002"));
        System.out.println(staff);

        System.out.println("========查询操作=========");
        System.out.println("size          " + staff.size());
        System.out.println("isEmpty       " + staff.isEmpty());
        System.out.println("containsKey   " + staff.containsKey("1001"));
        System.out.println("containsValue " + staff.containsValue(Employee.of("张三")));
        System.out.println("get           " + staff.get("1001"));
        System.out.println("get           " + staff.get("1002"));

        System.out.println("========批量操作=========");
        Map<String,Employee> singleMap = new HashMap<String,Employee>(2) {{
            put("1002", Employee.of("陈四"));
        }};
        staff.putAll(singleMap);
        System.out.println("putAll        " + staff);
        staff.clear();
        System.out.println("clear         " + staff);

        System.out.println("=======默认方法=======");

//        putIfAbsent(initMap());
//        getOrDefault(initMap());
//        merge(initMap());
//        remove(initMap());
//        compute(initMap());
//        computeIfAbsent(initMap());
//        computeIfPresent(initMap());
//        replace(initMap());
//        replaceAll(initMap());
        forEach(initMap());
    }


    private static void remove(HashMap<String, Employee> map) {
        System.out.println("=======remove=======");
        System.out.println(map);
        /*
        if (map.containsKey(key) && Objects.equals(map.get(key), value)) {
           map.remove(key);
           return true;
        } else
          return false;
        }
        */
        // key、value都相等才删除
        System.out.println(map.remove("1001", Employee.of("张三")));
        // 否则不做任何操作
        System.out.println(map.remove("1002", Employee.of("张三")));
        System.out.println(map);
    }

    private static void putIfAbsent(HashMap<String, Employee> map) {
        System.out.println("=======putIfAbsent=======");
        System.out.println(map);
        /*
        V v = map.get(key);
        if (v == null)
          v = map.put(key, value);
        return v;
        */
        // key存在不作任何操作.直接返回
        System.out.println(map.putIfAbsent("1001", Employee.of("赵")));
        // key不存在.添加映射
        System.out.println(map.putIfAbsent("1009", Employee.of("陈七")));
        System.out.println(map);
    }

    private static void merge(HashMap<String, Employee> map) {
        System.out.println("=======merge=======");
        System.out.println(map);
        /*
        方法签名: merge(K key, V value, BiFunction<? super V,? super V,? extends V> remappingFunction)

        V oldValue = map.get(key);
        V newValue = (oldValue == null) ? value : remappingFunction.apply(oldValue, value);
        if (newValue == null)
          map.remove(key);
        else
          map.put(key, newValue);
        }
        */
        // `oldValue == null && newValue != null` -- key不存在、新的value值不为null.添加映射
        System.out.println(map.merge("1009", Employee.of("陈七"), (oldValue, newValue) -> newValue));
        // `oldValue != null && newValue == null` -- key存在、新的value值为null.删除key
        System.out.println(map.merge("1001", Employee.of("陈七"), (oldValue, newValue) -> null));
        // `oldValue != null && newValue != null` -- key存在、新的value值不为null.更新映射
        System.out.println(map.merge("1002", Employee.of("四"), (oldValue, newValue) -> newValue));
        System.out.println(map);
    }

    private static void getOrDefault(HashMap<String, Employee> map) {
        System.out.println("=======getOrDefault=======");
        System.out.println(map.getOrDefault("1001", Employee.of("默认")));
        System.out.println(map.getOrDefault("1009", Employee.of("默认")));
    }

    private static void forEach(HashMap<String, Employee> map) {
        System.out.println("=======forEach=======");
        /*
        for (Map.Entry<K, V> entry : map.entrySet())
          action.accept(entry.getKey(), entry.getValue());
        }
        */
        map.forEach((k, v) -> System.out.printf("key = %s , value = %s\n", k, v));
    }

    private static void replace(HashMap<String, Employee> map) {
        System.out.println("=======replace=======");
        System.out.println(map);

        /*
        if (map.containsKey(key)) {
           return map.put(key, value);
        } else
          return null;
        }
        */
        // key存在替换value值
        System.out.println(map.replace("1001", Employee.of("张")));
        // key不存在不作任何操作
        System.out.println(map.replace("1009", Employee.of("张")));

        /*
        方法签名: boolean replace(K key, V oldValue, V newValue)

        if (map.containsKey(key) && Objects.equals(map.get(key), value)) {
          map.put(key, newValue);
          return true;
        } else
          return false;
        }
        */
        // key、value值都相等.替换value值
        System.out.println(map.replace("1002", Employee.of("李四"), Employee.of("李")));
        // key、value值不相等.不作任何操作
        System.out.println(map.replace("1003", Employee.of("李四"), Employee.of("李")));

        System.out.println(map);
    }

    private static void replaceAll(HashMap<String, Employee> map) {
        System.out.println("=======replaceAll=======");
        System.out.println(map);
        /*
        方法签名: replaceAll(BiFunction<? super K, ? super V, ? extends V> function)
        for (Map.Entry<K, V> entry : map.entrySet())
          entry.setValue(function.apply(entry.getKey(), entry.getValue()));
        }
        */
        // 对所有的value进行操作
        map.replaceAll((k, v) -> Employee.of(v.getName().substring(1)));
        System.out.println(map);
    }

    private static void compute(HashMap<String,Employee> map) {
        System.out.println("=======compute=======");
        System.out.println(map);

        /*
        方法签名: `compute(K key, BiFunction<? super K,? super V,? extends V> remappingFunction)`

        V oldValue = map.get(key);
        V newValue = remappingFunction.apply(key, oldValue);
        if (oldValue != null ) {
            if (newValue != null)
               map.put(key, newValue);
            else
               map.remove(key);
        } else {
            if (newValue != null)
               map.put(key, newValue);
            else
               return null;
        }
        */
        // `oldValue != null && newValue == null` -- key存在、新的value值为null.删除映射
        System.out.println(map.compute("1001", (k, v) -> Objects.isNull(v) ? Employee.of("三") : null));
        // `oldValue != null && newValue != null` -- key存在、新的value值不为null.添加映射
        System.out.println(map.compute("1002", (k, v) -> Objects.isNull(v) ? null : Employee.of("四")));
        // `oldValue == null && newValue != null` -- key不存在、新的value值不为null.添加映射
        System.out.println(map.compute("1001", (k, v) -> Employee.of("张三")));
        // `oldValue == null && newValue == null` -- key不存在、新的value为null.不作任何操作
        System.out.println(map.compute("1009", (k, v) -> null));
        System.out.println(map);
    }

    private static void computeIfAbsent(HashMap<String,Employee> map) {
        System.out.println("=======computeIfAbsent=======");
        System.out.println(map);

        /*
        方法签名: V computeIfAbsent(K key, Function<? super K,? extends V> mappingFunction)

        if (map.get(key) == null) {
           V newValue = mappingFunction.apply(key);
           if (newValue != null)
              map.put(key, newValue);
        }
        */
        // `map.get(key) == null && newValue != null` -- key不存在、新的value值不为null.添加映射
        System.out.println(map.computeIfAbsent("1005", (k) -> Employee.of("王七")));
        // `map.get(key) == null && newValue == null` -- key不存在、新的value值为null.不做任何操作
        System.out.println(map.computeIfAbsent("1006", (k) -> null));
        // `map.get(key) != null`                     -- key存在.不做任何操作
        System.out.println(map.computeIfAbsent("1001", (k) -> Employee.of("李")));
        System.out.println(map);
    }

    private static void computeIfPresent(HashMap<String,Employee> map) {
        System.out.println("=======computeIfPresent=======");
        System.out.println(map);

        /*
        方法签名: V computeIfPresent(K key, BiFunction<? super K,? super V,? extends V> remappingFunction)

        if (map.get(key) != null) {
          V oldValue = map.get(key);
          V newValue = remappingFunction.apply(key, oldValue);
          if (newValue != null)
              map.put(key, newValue);
          else
              map.remove(key);
        }
        */
        // `map.get(key) != null && newValue != null` -- key存在、新的value值不为null.更新value值
        System.out.println(map.computeIfPresent("1002", (k,v) -> Employee.of("四")));
        // `map.get(key) != null && newValue == null` -- key存在、新的value值为null.删除映射
        System.out.println(map.computeIfPresent("1001", (k,v) -> null));
        // `map.get(key) == null`                     -- key不存在.不做任何操作
        System.out.println(map.computeIfPresent("1006", (k,v) -> Employee.of("王")));
        System.out.println(map);
    }

    private static HashMap<String,Employee> initMap(){
        HashMap<String, Employee> staff = new HashMap<>();
        staff.put("1001", Employee.of("张三"));
        staff.put("1002", Employee.of("李四"));
        staff.put("1003", Employee.of("王五"));
        staff.put("1004", Employee.of("齐六"));
        return staff;
    }

    @Getter
    @Setter
    @AllArgsConstructor(staticName = "of")
    private static class Employee {

        private String name;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Employee employee = (Employee) o;
            return Objects.equals(name, employee.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", Employee.class.getSimpleName() + "[", "]")
                    .add("name='" + name + "'")
                    .toString();
        }
    }

}
