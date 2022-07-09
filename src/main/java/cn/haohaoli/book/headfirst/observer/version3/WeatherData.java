package cn.haohaoli.book.headfirst.observer.version3;

import lombok.Getter;

import java.util.Observable;

/**
 * 天气数据
 * @author LiWenHao
 * @date 2019-03-10 17:51
 */
@Getter
public class WeatherData extends Observable {

    // 温度
    private float temperature;
    // 湿度
    private float humidity;
    // 气压
    private float pressure;

    public void measurementsChanged(){
        super.setChanged();
        super.notifyObservers();
    }

    public void setMeasurements(float temperature, float humidity, float pressure){
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        this.measurementsChanged();
    }
}
