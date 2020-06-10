package mm.chenme.lib.commutillibdemo;

import org.junit.Test;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/**
 * Descriptions：
 * StartVersion：
 * <p>
 * Author：ChenME
 * Date：2020/6/10
 * Email：ibelieve1210@163.com
 */
public class JavaTest {
    @Test
    public void ktTest() {
        Kt kt = new Kt(
                new Function0<Unit>() {
                    @Override
                    public Unit invoke() {
                        System.out.println("我在 Java 中实现参数 1 的回调！");
                        return null;
                    }
                },
                new Function1<String, Unit>() {
                    @Override
                    public Unit invoke(String s) {
                        System.out.println("我在 Java 中实现参数 2 的回调！回调参数是：" + s);
                        return null;
                    }
                },
                new Function0<Boolean>() {
                    @Override
                    public Boolean invoke() {
                        System.out.println("我在 Java 中实现参数 3 的回调！");
                        return false;
                    }
                },
                new Function1<String, Boolean>() {
                    @Override
                    public Boolean invoke(String s) {
                        System.out.println("我在 Java 中实现参数 4 的回调！回调参数是：" + s);
                        return true;
                    }
                },
                new Function2<String, Integer, Boolean>() {
                    @Override
                    public Boolean invoke(String s, Integer integer) {
                        System.out.println("我在 Java 中实现参数 2 的回调！回调参数是：" + s + " 以及：" + integer);
                        return true;
                    }
                });

        kt.funcNoParam(new Function0<Unit>() {
            @Override
            public Unit invoke() {
                System.out.println("我在 Java 中调用 funcNoParam() 函数！");
                return null;
            }
        });
    }
}
