package cn.haohaoli.book.headfirst.observer.version2.bulletinboard;

import cn.haohaoli.book.headfirst.observer.version2.DisplayElement;
import cn.haohaoli.book.headfirst.observer.version2.Observer;

/**
 * 目前状况
 * @author LiWenHao
 * @date 2019-03-10 18:09
 */
public class CurrentConditionsDisplay extends DisplayElement implements Observer {

    @Override
    public void update(float temperature, float humidity, float pressure){
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        this.display();
    }

    @Override
    public void display() {
        System.out.println("目前状况：");
        System.out.println("温度: " + this.temperature + ",湿度: " + this.humidity + ",气压: " + this.pressure);
    }
}
