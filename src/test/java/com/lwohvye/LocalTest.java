package com.lwohvye;

import org.junit.jupiter.api.Test;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class LocalTest {

    @FunctionalInterface
    public interface MessageBuilder {
        String buildMessage();
    }

    private static void log(int level, MessageBuilder builder) {
        if (level == 1) {
            System.out.println(builder.buildMessage());// 实际上利用内部类 延迟的原理,代码不相关 无需进入到启动代理执行
        }
    }

    @Test
    public void test1() {
        String msgA = "Hello";
        String msgB = "World";
        String msgC = "Java";
        // 里面是接口的实现
        log(2, () -> {
            System.out.println("lambda 是否执行了");
            return msgA + msgB + msgC;
        });
    }

    // https://www.lwohvye.com/2020/10/28/java-8%e5%87%bd%e6%95%b0%e5%bc%8f%e6%8e%a5%e5%8f%a3function%e3%80%81consumer%e3%80%81predicate%e3%80%81supplier/

    // region Supplier

    private static String test_Supplier(Supplier<String> suply) {
        return suply.get(); //供应者接口
    }

    @Test
    public void doTestSupplier() {
        // 产生的数据作为 sout 作为输出
        System.out.println(test_Supplier(() -> "产生数据"));

        System.out.println(String.valueOf(new Supplier<String>() {
            @Override
            public String get() {
                return "产生数据";
            }
        }));
    }
    // endregion

    // region Consumer
    private static void generateX(Consumer<String> consumer) {
        consumer.accept("hello consumer");
    }

    @Test
    public void doTestConsumerAcp() {
        generateX(s -> System.out.println(s));
    }
    // endregion

    // region Predicate

    private static void method_test(Predicate<String> predicate, String str) {
        boolean b = predicate.test(str);
        System.out.println(b);
    }

    @Test
    public void doTestPredicate() {
        method_test(s -> s.contains("a"), "Java 8");
    }
    // endregion

    // region Function
    private static void numberToString(Function<Number, String> function, Number num) {
        String apply = function.apply(num);
        System.out.println("转换结果:" + apply);
    }

    @Test
    public void doTestFunction() {
        numberToString((s) -> String.valueOf(s), 12L);
    }
    // endregion
}
