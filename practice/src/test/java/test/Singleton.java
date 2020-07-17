package test;

/**
 * @author yeyulin
 * @description:
 * @date 2020/7/6 9:53
 **/
public class Singleton {
    private static Singleton instance; // 不加volatile

    private volatile boolean flag = false; // 一个flag来标识初始化是否完成

    private Singleton() {
        try {
            Thread.sleep(1000);
            flag = true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // 给客户端调用的，如果初始化未完成，应该返回false，如果完成，返回true
    public boolean isFlag() {
        return flag;
    }

    // 双重锁检查实现单例模式
    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

}
