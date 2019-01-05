package cn.haohaoli.book.core.chapter5.interfaces;

public interface interfaces {

    void get ();

    static void gets (){
        System.out.println("默认");
    }

    default void getd () {

    }
}
