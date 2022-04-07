import java.time.Instant;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.Test;

public class ScheduleTest {
    ScheduledExecutorService executorService = Executors.newScheduledThreadPool(10);

    private final AtomicInteger count = new AtomicInteger();

    private final AtomicBoolean cancel = new AtomicBoolean(false);

    @Test
    public void testScheduleExecutorService() throws InterruptedException {
        runFixed(3);
        // TimeUnit.SECONDS.sleep(2);
    }

    public void runWithTimer(int times) {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            private final AtomicInteger acount = new AtomicInteger();

            @Override
            public void run() {
                if (!cancel.get()) {
                    System.out.println("execute……" + Instant.now());
                    int i = acount.incrementAndGet();
                    System.out.println("i = " + i);
                    if (acount.get() == times) {
                        System.out.println("canceled……");
                        timer.cancel();
                    }
                } else {
                    timer.cancel();
                    System.out.println("be canceled……");
                }
            }
        }, 0, 3000);
    }

    public void runFixed(int times) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(times);
        ScheduledFuture<?> future = executorService.scheduleAtFixedRate(() -> {
            if (cancel.get()) {
                System.out.println("interrupt……");
                Thread.currentThread().interrupt();
            }
            System.out.println("execute……" + Instant.now());
            countDownLatch.countDown();
            int i = count.incrementAndGet();
            System.out.println("i = " + i);
        }, 0, 3, TimeUnit.SECONDS);

        countDownLatch.await();
        future.cancel(true);

        // if(count.get() > times || cancel.get()) {
        // System.out.println("canceled……");
        // // 传入false参数只能取消还没开始的任务，若任务已经开始了，
        // // 就由其运行下去。所以对于已经开始的任务，如果想要停止的话，需要给cancel方法的参数设置为true。
        // future.cancel(true);
        // }
    }

    public static void main(String[] args) throws InterruptedException {
        ScheduleTest scheduleTest = new ScheduleTest();
        // scheduleTest.runFixed(3);
        scheduleTest.runWithTimer(3);
        TimeUnit.SECONDS.sleep(4);
        System.out.println("ready cancel……");
        scheduleTest.cancel.set(true);
        System.out.println("finished……");
    }

}
