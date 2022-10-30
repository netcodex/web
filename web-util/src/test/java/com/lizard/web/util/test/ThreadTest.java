package com.lizard.web.util.test;

import java.util.concurrent.TimeUnit;

/**
 * @author X
 * @version 1.0
 * @since 2022-06-22 20:44
 **/
public class ThreadTest {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new DaemonThread());
//        thread.setDaemon(true);
        thread.setUncaughtExceptionHandler((t, e) -> System.out.println(e.getMessage()));
        thread.start();
        TimeUnit.MILLISECONDS.sleep(100);
    }

    static class DaemonThread implements Runnable {
        @Override
        public void run() {
            System.out.println("Daemon thread start....");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("exit by interrupt exception");
            } finally {
                System.out.println("finally block");
            }
        }
    }

}
