package mm.chenme.lib.commutillibdemo.complex_demo.interview.design_patterns.singleton.java;

/**
 * Descriptions：
 * StartVersion：
 * <p>
 * Author：ChenME
 * Date：2020/8/19
 * Email：ibelieve1210@163.com
 */

public class SingletonInstance {

    private SingletonInstance() {
    }

    public SingletonInstance getInstance() {
        return SingletonHolder.instance;
    }

    private static class SingletonHolder {
        private static final SingletonInstance instance = new SingletonInstance();
    }
}

//
///**
// * 枚举方式
// */
//public enum SingletonInstance {
//    instance;
//
//    public void doSomething() {
//    }
//}
