package cn.haohaoli.book.headfirst.observer.version1;

import cn.haohaoli.book.headfirst.observer.version1.bulletinboard.CurrentConditionsDisplay;
import cn.haohaoli.book.headfirst.observer.version1.bulletinboard.ForecastDisplay;
import cn.haohaoli.book.headfirst.observer.version1.bulletinboard.StatisticsDisplay;

/**
 * @author LiWenHao
 * @date 2019-03-13 20:34
 */
public class Test {

    public static void main(String[] args) {

        //创建布告板
        CurrentConditionsDisplay currentConditionsDisplay = new CurrentConditionsDisplay();
        ForecastDisplay forecastDisplay = new ForecastDisplay();
        StatisticsDisplay statisticsDisplay = new StatisticsDisplay();

        WeatherData weatherData = new WeatherData(currentConditionsDisplay, statisticsDisplay, forecastDisplay);

        //更新天气数据
        weatherData.setMeasurements(80,65,30.4f);
        System.out.println("==================================================");
        weatherData.setMeasurements(82,70,28.9f);
        System.out.println("==================================================");
        weatherData.setMeasurements(78,90,28.9f);
    }
}
