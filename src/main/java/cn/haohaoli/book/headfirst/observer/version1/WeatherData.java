package cn.haohaoli.book.headfirst.observer.version1;

import cn.haohaoli.book.headfirst.observer.version1.bulletinboard.CurrentConditionsDisplay;
import cn.haohaoli.book.headfirst.observer.version1.bulletinboard.ForecastDisplay;
import cn.haohaoli.book.headfirst.observer.version1.bulletinboard.StatisticsDisplay;

/**
 * 天气数据
 * @author LiWenHao
 * @date 2019-03-10 17:51
 */
public class WeatherData {

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

    /**
     * 数据改变立即更新
     */
    public void measurementsChanged(){
        float temperature = getTemperature();
        float humidity = getHumidity();
        float pressure = getPressure();

        CurrentConditionsDisplay currentConditionsDisplay = new CurrentConditionsDisplay();
        StatisticsDisplay statisticsDisplay = new StatisticsDisplay();
        ForecastDisplay forecastDisplay = new ForecastDisplay();
        currentConditionsDisplay.update(temperature, humidity, pressure);
        statisticsDisplay.update(temperature, humidity, pressure);
        forecastDisplay.update(temperature, humidity, pressure);
    }
}
