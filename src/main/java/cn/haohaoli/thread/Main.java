package cn.haohaoli.thread;

public class Main {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());

        readFromDataBase();
        writeDataToFile();

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
