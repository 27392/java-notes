package cn.haohaoli.book.headfirst.observer.version3.bulletinboard;

import cn.haohaoli.book.headfirst.observer.version3.DisplayElement;
import cn.haohaoli.book.headfirst.observer.version3.WeatherData;

import java.util.Observable;
import java.util.Observer;

/**
 * 目前状况
 * @author LiWenHao
 * @date 2019-03-10 18:09
 */
public class CurrentConditionsDisplay extends DisplayElement implements Observer {

    public CurrentConditionsDisplay(Observable observable) {
        //被观察者对象
        observable.addObserver(this);  //自动注册
    }

    public void display() {
        System.out.println("目前状况：");
        System.out.println("温度: " + temperature + ",湿度: " + humidity + ",气压: " + pressure);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof WeatherData) {
            WeatherData weatherData = (WeatherData) o;
            this.humidity = weatherData.getHumidity();
            this.temperature = weatherData.getTemperature();
            this.pressure = weatherData.getPressure();
            this.display();
        }
    }
}
