package mm.chenme.lib.commutillibdemo;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

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
//                new Function1<String, Boolean>() {
//                    @Override
//                    public Boolean invoke(String s) {
//                        System.out.println("我在 Java 中实现参数 4 的回调！回调参数是：" + s);
//                        return true;
//                    }
//                },
                s -> {
                    System.out.println("我在 Java 中实现参数 4 的回调！回调参数是：" + s);
                    return true;
                },
//                new Function2<String, Integer, Boolean>() {
//                    @Override
//                    public Boolean invoke(String s, Integer integer) {
//                        System.out.println("我在 Java 中实现参数 2 的回调！回调参数是：" + s + " 以及：" + integer);
//                        return true;
//                    }
//                },
                (s, integer) -> {
                    System.out.println("我在 Java 中实现参数 2 的回调！回调参数是：" + s + " 以及：" + integer);
                    return true;
                });

        kt.funcNoParam(new Function0<Unit>() {
            @Override
            public Unit invoke() {
                System.out.println("我在 Java 中调用 funcNoParam() 函数！");
                return null;
            }
        });
    }

    @Test
    public void mapTest(){
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(1, 333);
        map.put(2, 1234);
        map.put(3, 4321);
        map.put(4,5555);
        map.put(5,5555);
        Integer[] keys = map.keySet().toArray(new Integer[0]);
        Random random = new Random();
//        Integer randomKey = keys[random.nextInt(keys.length)];
        System.out.println(keys[random.nextInt(keys.length)]);
        System.out.println(keys[random.nextInt(keys.length)]);
        System.out.println(keys[random.nextInt(keys.length)]);
        System.out.println(keys[random.nextInt(keys.length)]);

    }

    @Test
    public void formatTest(){
        System.out.println(formatTimestamp2Hms(367800000L));
        System.out.println(formatTimestamp2Hms(3678000L));
        System.out.println(formatTimestamp2Hms(3478000L));
        System.out.println(formatTimestamp2Hms(61000L));
        System.out.println(formatTimestamp2Hms(6000L));
        System.out.println(formatTimestamp2Hms(0L));
    }

    public static String formatTimestamp2Hms(Long timestamp) {
        if (null == timestamp || timestamp <= 0) {
            return "00:00:00";
        }
        String result = "";
        timestamp /= 1000;
        int hour = (int) (timestamp / 3600);
        if (hour <= 0) {
            result += "00:";
        } else if (hour < 10) {
            result += "0" + hour + ":";
        } else {
            result += hour + ":";
        }

        timestamp %= 3600;
        int min = (int) (timestamp / 60);
        if (min <= 0) {
            result += "00:";
        } else if (min < 10) {
            result += "0" + min + ":";
        } else {
            result += min + ":";
        }

        timestamp %= 60;
        if (timestamp < 10) {
            result += "0" + timestamp;
        } else {
            result += String.valueOf(timestamp);
        }
        return result;
    }

    @Test
   public void mathRound(){
        int a = 89;
        int b = 71;
        System.out.println(Math.round((float)b/(float)a*100));
    }
}
