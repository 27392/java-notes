package cn.haohaoli.book.headfirst.observer.version3.bulletinboard;

import cn.haohaoli.book.headfirst.observer.version2.DisplayElement;
import cn.haohaoli.book.headfirst.observer.version3.WeatherData;

import java.util.Observable;
import java.util.Observer;

/**
 * 气象统计
 * @author LiWenHao
 * @date 2019-03-10 18:10
 */
public class StatisticsDisplay extends DisplayElement implements Observer {

    private Observable observable;

    public StatisticsDisplay(Observable observable) {
        this.observable = observable;
        this.observable.addObserver(this);
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
