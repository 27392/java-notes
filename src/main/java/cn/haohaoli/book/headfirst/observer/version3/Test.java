package cn.haohaoli.book.headfirst.observer.version3;

import cn.haohaoli.book.headfirst.observer.version3.bulletinboard.CurrentConditionsDisplay;
import cn.haohaoli.book.headfirst.observer.version3.bulletinboard.ForecastDisplay;
import cn.haohaoli.book.headfirst.observer.version3.bulletinboard.StatisticsDisplay;

/**
 * @author LiWenHao
 * @date 2019-03-10 22:59
 */
public class Test {

    public static void main(String[] args) {

        WeatherData observable = new WeatherData();

        // 创建布告板
        CurrentConditionsDisplay currentConditionsDisplay = new CurrentConditionsDisplay(observable);
        ForecastDisplay          forecastDisplay          = new ForecastDisplay(observable);
        StatisticsDisplay        statisticsDisplay        = new StatisticsDisplay(observable);

        // 更新天气数据
        observable.setMeasurements(80, 65, 30.4f);
        System.out.println("==================================================");

        // 删除观察者
        observable.deleteObserver(statisticsDisplay);
        observable.deleteObserver(forecastDisplay);
        observable.deleteObserver(currentConditionsDisplay);
        observable.setMeasurements(82, 70, 28.9f);
        System.out.println("==================================================");

        // 增加观察者
        observable.addObserver(currentConditionsDisplay);
        observable.setMeasurements(78, 90, 28.9f);
    }
}
