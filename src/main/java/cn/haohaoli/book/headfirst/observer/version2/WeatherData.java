package cn.haohaoli.book.headfirst.observer.version2;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * 天气数据
 * @author LiWenHao
 * @date 2019-03-10 17:51
 */
@Getter
public class WeatherData implements Subject {

    //温度
    private float temperature;
    //湿度
    private float humidity;
    //气压
    private float pressure;

    private List<Observer> observerList = new ArrayList<>();

    @Override
    public void registerObserver(Observer observer) {
        observerList.add(observer);
    }

    @Override
    public void removerObserver(Observer observer) {
        int i = observerList.indexOf(observer);
        observerList.remove(null);
        if (i > 0) {
            observerList.remove(i);
        }

    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observerList) {
            observer.update(temperature, humidity, pressure);
        }
    }

    public void setMeasurements(float temperature, float humidity, float pressure){
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        this.measurementsChanged();
    }

    public void measurementsChanged(){
        this.notifyObservers();
    }
}
