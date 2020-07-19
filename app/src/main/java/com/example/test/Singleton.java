package com.example.test;

public class Singleton {
    /**
     * 懒汉模式
     */
    private static Singleton instance1;
//    private Singleton() {}
    public synchronized static Singleton getInstance() {
        if (instance1 == null) {
            instance1 = new Singleton();
        }
        return instance1;
    }

    /**
     * 饿汉模式
     */
    private static Singleton instance2 = new Singleton();
//    private Singleton() {}
    private static Singleton getInstance1() {
        return instance2;
    }

    /**
     * 静态内部类模式
     */
    private static class SingleTonHolder {
        private static final Singleton INSTANCE = new Singleton();
    }
    private Singleton() {}
    private static Singleton getInstance2() {
        return SingleTonHolder.INSTANCE;
    }
}
