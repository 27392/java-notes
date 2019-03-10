package cn.haohaoli.book.headfirst.observer.version2.bulletinboard;

import cn.haohaoli.book.headfirst.observer.version2.DisplayElement;
import cn.haohaoli.book.headfirst.observer.version2.Observer;
import cn.haohaoli.book.headfirst.observer.version2.Subject;

/**
 * 气象统计
 * @author LiWenHao
 * @date 2019-03-10 18:10
 */
public class StatisticsDisplay implements Observer, DisplayElement {

    private float temperature;
    private float humidity;
    private float pressure;

    private Subject weatherDate;

    public StatisticsDisplay(Subject weatherDate) {
        this.weatherDate = weatherDate;
        this.weatherDate.registerObserver(this);
    }

    public void update(float temperature, float humidity, float pressure){
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        this.display();
    }

    public void display() {
        System.out.println("气象统计：");
        System.out.println("温度: " + temperature + ",湿度: " + humidity + ",气压: " + pressure);
    }
}
