package cn.haohaoli.book.headfirst.observer.version1;

import cn.haohaoli.book.headfirst.observer.version1.bulletinboard.CurrentConditionsDisplay;
import cn.haohaoli.book.headfirst.observer.version1.bulletinboard.ForecastDisplay;
import cn.haohaoli.book.headfirst.observer.version1.bulletinboard.StatisticsDisplay;
import lombok.Getter;

/**
 * 天气数据
 * @author LiWenHao
 * @date 2019-03-10 17:51
 */
@Getter
public class WeatherData {

    //温度
    private float temperature;
    //湿度
    private float humidity;
    //气压
    private float pressure;

    private final CurrentConditionsDisplay currentConditionsDisplay;
    private final StatisticsDisplay statisticsDisplay;
    private final ForecastDisplay forecastDisplay;

    public WeatherData(CurrentConditionsDisplay currentConditionsDisplay, StatisticsDisplay statisticsDisplay, ForecastDisplay forecastDisplay) {
        this.currentConditionsDisplay = currentConditionsDisplay;
        this.statisticsDisplay = statisticsDisplay;
        this.forecastDisplay = forecastDisplay;
    }

    /**
     * 数据改变立即更新
     */
    public void measurementsChanged(){

        currentConditionsDisplay.update(temperature, humidity, pressure);
        statisticsDisplay.update(temperature, humidity, pressure);
        forecastDisplay.update(temperature, humidity, pressure);
    }

    public void setMeasurements(float temperature, float humidity, float pressure){
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        // 调用数据变更方法
        this.measurementsChanged();
    }
}
