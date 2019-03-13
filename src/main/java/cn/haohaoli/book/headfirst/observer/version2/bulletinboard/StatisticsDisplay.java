package cn.haohaoli.book.headfirst.observer.version2.bulletinboard;

import cn.haohaoli.book.headfirst.observer.version2.DisplayElement;
import cn.haohaoli.book.headfirst.observer.version2.Observer;

/**
 * 气象统计
 * @author LiWenHao
 * @date 2019-03-10 18:10
 */
public class StatisticsDisplay extends DisplayElement implements Observer {

    @Override
    public void update(float temperature, float humidity, float pressure){
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        this.display();
    }

    @Override
    public void display() {
        System.out.println("气象统计：");
        System.out.println("温度: " + this.temperature + ",湿度: " + this.humidity + ",气压: " + this.pressure);
    }
}
