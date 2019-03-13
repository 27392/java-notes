package cn.haohaoli.book.headfirst.observer.version1.bulletinboard;

/**
 * 目前状况
 * @author LiWenHao
 * @date 2019-03-10 18:09
 */
public class CurrentConditionsDisplay {

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
        System.out.println("目前状况：");
        System.out.println("温度: " + temperature + ",湿度: " + humidity + ",气压: " + pressure);
    }
}
