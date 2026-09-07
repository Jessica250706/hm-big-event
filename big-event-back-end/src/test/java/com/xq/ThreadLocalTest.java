package com.xq;

import org.junit.jupiter.api.Test;

public class ThreadLocalTest {

    @Test
    public void testThreadLocalSetAndGet() {
        // 提供一个 ThreadLocal 对象
        ThreadLocal tl = new ThreadLocal();
        // 开启两个线程
        new Thread(() -> {
            tl.set("Yuki");
            System.out.println(Thread.currentThread().getName() + "：" + tl.get());
            System.out.println(Thread.currentThread().getName() + "：" + tl.get());
            System.out.println(Thread.currentThread().getName() + "：" + tl.get());
        }, "绿色").start();
        new Thread(() -> {
            tl.set("Momo");
            System.out.println(Thread.currentThread().getName() + "：" + tl.get());
            System.out.println(Thread.currentThread().getName() + "：" + tl.get());
            System.out.println(Thread.currentThread().getName() + "：" + tl.get());
        }, "粉色").start();
    }
}
