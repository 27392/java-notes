package cn.haohaoli.book.headfirst.observer.version2.bulletinboard;

import cn.haohaoli.book.headfirst.observer.version2.DisplayElement;
import cn.haohaoli.book.headfirst.observer.version2.Observer;
import cn.haohaoli.book.headfirst.observer.version2.Subject;
import cn.haohaoli.book.headfirst.observer.version2.WeatherData;

/**
 * 目前状况
 * @author LiWenHao
 * @date 2019-03-10 18:09
 */
public class CurrentConditionsDisplay implements Observer, DisplayElement {

    private float temperature;
    private float humidity;
    private float pressure;

    private Subject weatherDate;

    public CurrentConditionsDisplay(Subject weatherDate) {
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
        System.out.println("目前状况：");
        System.out.println("温度: " + temperature + ",湿度: " + humidity + ",气压: " + pressure);
    }
}
