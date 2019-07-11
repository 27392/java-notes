package cn.haohaoli.leet;

/**
 * 1108. IP 地址无效化
 * https://leetcode-cn.com/problems/defanging-an-ip-address/
 * @author LiWenHao
 * @date 2019/7/11 14:10
 */
public class Leet1108 {

    public static String defangIPaddr(String address) {
        return address.replace(".", "[.]");
    }

    public static void main(String[] args) {
        String ipv4 = "255.255.255.255";
        System.out.println(defangIPaddr(ipv4));
    }
}
