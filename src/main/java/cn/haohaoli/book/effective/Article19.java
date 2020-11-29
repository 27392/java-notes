package cn.haohaoli.book.effective;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 19. 接口只用于定于类型
 *
 * @author LiWenHao
 */
@SuppressWarnings("unused")
public class Article19 {

    // 当类实现接口时，该接口作为一种类型(type),可以用来引用类的实例.因此,一个类实现了一个接口,因此表明客户端可以如何处理类的实例.为其他目的定义接口是不合适的

    public static void main(String[] args) {

        /*
            统一的将常量交由接口常量这样并不合适,单一接口有太多不同业务的值,不同语义的值.
            如果常量与现有有个接口紧密相关时,可以将其放在该类中.类似`Integer.MIN_VALUE`
            如果如果语义一致(类似状态这种)则可以使用枚举类
            否则就应该使用不可实例化的工具类存放常量
         */

        // 常量接口(不应该使用此种方式)
        String orderCacheKey = Constants.ORDER_CACHE_KEY;

        // 常量与类紧密相关,写在类中(推荐)
        String userDefaultAddress = UserService.USER_DEFAULT_ADDRESS;

        // 使用枚举(语义类似,例如状态值等.推荐)
        int value = OrderStatus.DELIVERY.getValue();

        // 使用不可实例化的工具类(在常量无法使用枚举的情况下使用该做法.推荐)
        String cacheKey = CacheKeyConstants.ORDER_CACHE_KEY;
    }


    @Deprecated
    interface Constants {
        // 订单待发货
        int ORDER_WAIT_DELIVERY = 1;

        // 订单以发货
        int ORDER_DELIVERY = 2;

        // 订单收货
        int ORDER_RECEIVING = 3;

        // 用户默认头像
        String USER_DEFAULT_AVATAR_IMG = "图片地址";

        // 用户默认地址
        String USER_DEFAULT_ADDRESS = "地址";

        // 商品缓存
        String PRODUCT_CACHE_KEY = "缓存key";

        // 用户
        String ORDER_CACHE_KEY = "缓存key";
    }

    /**
     *
     */
    @RequiredArgsConstructor
    @Getter
    enum OrderStatus {

        WAIT_DELIVERY(1, "待发货"),

        DELIVERY(2, "已发货"),

        RECEIVING(3, "收货");

        private final int    value;
        private final String desc;
    }

    static class UserService {

        public static final String USER_DEFAULT_AVATAR_IMG = "图片地址";
        public static final String USER_DEFAULT_ADDRESS    = "地址";

    }

    static class CacheKeyConstants {

        private CacheKeyConstants() {}

        // 商品缓存
        public static final String PRODUCT_CACHE_KEY = "缓存key";

        // 用户缓存
        public static final String ORDER_CACHE_KEY = "缓存key";
    }

}
