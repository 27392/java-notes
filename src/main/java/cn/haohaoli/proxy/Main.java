package cn.haohaoli.proxy;

import cn.haohaoli.proxy.annotation.Client;
import cn.haohaoli.proxy.annotation.RequestMapping;
import lombok.AllArgsConstructor;

import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

/**
 * @author LiWenHao
 * @date 2019/9/28 10:13
 */
public class Main {

    public static void main(String[] args) {

        ClientProxy   proxy                  = new ClientProxy(ServiceClient.class);
        ServiceClient instance               = (ServiceClient) proxy.instance();
        String        xxxxxxxxxxxxxxxxxxxxxx = instance.search("xxxxxxxxxxxxxxxxxxxxxx");
        System.out.println(xxxxxxxxxxxxxxxxxxxxxx);
    }

    @AllArgsConstructor
    static class ClientProxy implements InvocationHandler {

        private Class clazz;

        private Object instance() {
            return Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{clazz}, this);
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Client         client         = (Client) clazz.getAnnotation(Client.class);
            RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
            String         address          = client.value().concat(requestMapping.value()).concat("?ie=utf-8&wd=").concat(String.valueOf(args[0]));

            URL url = new URL(address);
            URLConnection connection = url.openConnection();

            InputStream inputStream = connection.getInputStream();

            StringBuilder sb = new StringBuilder();
            String        s;
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
                while ((s = bufferedReader.readLine()) != null) {
                    sb.append(s);
                }
            }
            return sb.toString();
        }
    }
}



