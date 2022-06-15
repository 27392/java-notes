package cn.haohaoli.book.headfirst.proxy;

/**
 * 静态代理
 *
 * @author lwh
 */
public class StaticProxyTest {

    public static void main(String[] args) {
        UserService proxy = new UserServiceProxy(new UserServiceImpl());
        boolean     b     = proxy.deleteById("123");
    }

    interface UserService {

        boolean deleteById(String id);
    }

    static class UserServiceProxy implements UserService {

        private final UserService delegate;

        UserServiceProxy(UserService delegate) {
            this.delegate = delegate;
        }

        @Override
        public boolean deleteById(String id) {
            this.before();
            boolean bol = delegate.deleteById(id);
            this.after();
            return bol;
        }

        private void before() {
            System.out.println("校验权限...");
        }

        private void after() {
            System.out.println("记录日志...");
        }
    }

    static class UserServiceImpl implements UserService {

        @Override
        public boolean deleteById(String id) {
            System.out.println("deleteById: " + id);
            return false;
        }
    }
}
