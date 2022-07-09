package cn.haohaoli.book.headfirst.observer.version2;

import cn.haohaoli.book.headfirst.observer.version2.bulletinboard.CurrentConditionsDisplay;
import cn.haohaoli.book.headfirst.observer.version2.bulletinboard.ForecastDisplay;
import cn.haohaoli.book.headfirst.observer.version2.bulletinboard.StatisticsDisplay;

/**
 * @author LiWenHao
 * @date 2019-03-10 18:56
 */
public class Test {

    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();

        // 创建布告板
        CurrentConditionsDisplay currentConditionsDisplay = new CurrentConditionsDisplay();
        ForecastDisplay forecastDisplay = new ForecastDisplay();
        StatisticsDisplay statisticsDisplay = new StatisticsDisplay();

        // 注册为观察者
        weatherData.registerObserver(currentConditionsDisplay);
        weatherData.registerObserver(forecastDisplay);
        weatherData.registerObserver(statisticsDisplay);

        // 更新天气数据
        weatherData.setMeasurements(80,65,30.4f);
        System.out.println("==================================================");

        // 删除观察者
        weatherData.removerObserver(statisticsDisplay);
        weatherData.setMeasurements(82,70,28.9f);

        // 删除观察者
        weatherData.removerObserver(forecastDisplay);
        System.out.println("==================================================");
        weatherData.setMeasurements(78,90,28.9f);

        // 注册观察者
        weatherData.registerObserver(statisticsDisplay);
        System.out.println("==================================================");
        weatherData.setMeasurements(78,90,28.9f);
    }
}
