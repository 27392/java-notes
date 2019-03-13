package cn.haohaoli.book.headfirst.observer.version1.bulletinboard;

/**
 * 气象统计
 * @author LiWenHao
 * @date 2019-03-10 18:10
 */
public class StatisticsDisplay {

    private float temperature;
    private float humidity;
    private float pressure;

    public void update(float temperature, float humidity, float pressure){
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        display();
    }

    private void display() {
        System.out.println("气象统计：");
        System.out.println("温度: " + temperature + ",湿度: " + humidity + ",气压: " + pressure);
    }
}
