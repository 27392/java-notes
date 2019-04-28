package cn.haohaoli.thread;


import java.io.File;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());

//        readFromDataBase();
//        writeDataToFile();
        Throwable throwable = new Throwable();
        throwable.printStackTrace();
        StackTraceElement[] stackTrace = throwable.getStackTrace();
        for (StackTraceElement stackTraceElement : stackTrace) {
            System.out.println(stackTraceElement);
        }
        String message = throwable.getMessage();


        int [][] yang = new int[7][];
        for (int i = 0; i <yang.length; i++) {
            yang[i] = new int[i + 1];
        }
        for (int i = 0; i < yang.length; i++) {
            for (int j = 0; j < yang[i].length; j++) {
                //第一个和最后一个是1
                yang[i][0] = 1;
                yang[i][i] = 1;
                //从第三个开始变化,第二层不能从第一个开始算,而且第上一层只能取到最后一位
                if(i > 1 && j > 0 && j < i) {
                    //上一层的数组
                    int i1 = yang[i - 1][j];
                    int i2 = yang[i - 1][j - 1];
                    yang[i][j] = i1 + i2;
                }
            }
        }
        System.out.println(Arrays.deepToString(yang));
        List<List<Integer>> generate = generate(15);
        for (List<Integer> integerList : generate) {
            for (Integer integer : integerList) {
                System.out.print(integer + " ");
            }
            System.out.println();
        }
        List<Integer> integerList = selfDividingNumbers(1, 22);

    }

    //最大重复数
    public int repeatedNTimes(int[] A) {

//        int number = 0;
//        int repeatCount = 0;
//
//        Map<Integer,Integer> map = new HashMap<>();
//        for (int i = 0; i < A.length; i++) {
//            if (i == 0) {
//                map.put(A[i], 0);
//                continue;
//            }
//            if (map.containsKey(A[i])) {
//                int count = map.get(A[i]) + 1;
//                int key = A[i];
//                map.put(A[i], count);
//                if (count > repeatCount) {
//                    number = key;
//                }
//            } else {
//                map.put(A[i], 0);
//            }
//        }
//        return number;

        Set<Integer> set = new HashSet<>();
        for (int i : A) {
            if (!set.add(i)) {
                return i;
            }
        }
        return 0;
    }

    public static List<Integer> selfDividingNumbers(int left, int right) {

        List<Integer> integerList = new ArrayList<>();
        /*for (int i = left; i <= right; i++) {
            if (i < 10) {
                integerList.add(i);
                continue;
            }
            char[] chars = String.valueOf(i).toCharArray();
            boolean b = true;
            for (char aChar : chars) {
                int is = aChar - 48;
                System.out.println(i + " , " + aChar);
                if (is == 0 || i % is != 0) {
                    b = false;
                }
            }
            if (b) {
                integerList.add(i);
            }
        }*/

        for (; left <= right; left ++) {

            boolean s = s(left);
            if (s) {
                integerList.add(left);
            }
        }

        return integerList;
    }

    public static boolean s (int i) {
        int old = i;
        while (old != 0){
            int i1 = i % 10;
            if (i1 == 0) {
                return true;
            }
            if (old % i1 != 0) {
                return false;
            }
            old /= 10;
        }
        return true;
    }

    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> yang = new ArrayList<>(numRows);
        if (numRows == 0) {
            return yang;
        }
        yang.add(Collections.singletonList(1));
        for (int i = 1; i < numRows; i++) {
            List<Integer> inner = new ArrayList<>(i);
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    inner.add(1);
                }
                //上一层
                if (i > 1 && j > 0 && j < i) {
                    Integer integer = yang.get(i - 1).get(j);
                    Integer integer1 = yang.get(i - 1).get(j - 1);
                    inner.add(integer + integer1);
                }
            }
            yang.add(inner);
        }
        return yang;
    }

    public static void readFromDataBase () {
        System.out.println("从数据库读取数据 开始");
        try {
            Thread.sleep(1000 * 3L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("从数据库读取数据 结束");
    }

    public static void writeDataToFile() {
        System.out.println("往本地写数据 开始");
        try {
            Thread.sleep(1000 * 3L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("往本地写数据 结束");
    }
}
