package cn.haohaoli.book.headfirst.observer.version2;

/**
 * 观察者接口
 * @author LiWenHao
 * @date 2019-03-10 18:41
 */
public interface Observer {

    /**
     * 更新
     * @param temperature 温度
     * @param humidity 湿度
     * @param pressure 气压
     */
    void update(float temperature, float humidity, float pressure);
}
