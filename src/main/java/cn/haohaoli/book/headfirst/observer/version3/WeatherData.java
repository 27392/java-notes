package cn.haohaoli.book.headfirst.observer.version3;

import java.util.Observable;

/**
 * 天气数据
 * @author LiWenHao
 * @date 2019-03-10 17:51
 */
public class WeatherData extends Observable {

    //温度
    private float temperature;
    //湿度
    private float humidity;
    //气压
    private float pressure;

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }

    public void measurementsChanged(){
        setChanged();
        notifyObservers();
    }

    public void setMeasurements(float temperature, float humidity, float pressure){
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        this.notifyObservers();
        this.measurementsChanged();
    }
}
