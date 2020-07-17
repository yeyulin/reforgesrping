package test;

/**
 * @author yeyulin
 * @description:
 * @date 2020/7/6 9:53
 **/
public class SingletonDemo {
    private final static int THREAD_NUMBER = 100000;

    private static class MyThread implements Runnable {


        public void run() {
            Singleton singleton = Singleton.getInstance();
            if (!singleton.isFlag()) {
                System.out.println("I am false!!!");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < THREAD_NUMBER; i++) {
            new Thread(new MyThread()).start();
        }

    }

}
