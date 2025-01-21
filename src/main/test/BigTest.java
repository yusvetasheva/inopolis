import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class BigTest {
    // ------------ Что выведет данный тест? -------------
    @Test
    @Ignore
    public void test4() {
        A a = new B();
        printInfo(a);
        printInfo(null);
    }

    private static void printInfo(A a) {
        a.foo();
    }

    private static class A {
        public static void foo() {
            System.out.println("class A");
        }
    }

    private static class B extends A {
        public static void foo() {
            System.out.println("class B extends A");
        }
    }


    // ------------ Что выведет данный тест? -------------
    @Test
    @Ignore
    public void test5() {
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            threads.add(new Thread(new ThreadImpl()));
        }
        threads.forEach(Thread::start);
        threads.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException ignored) {
            }
        });
    }

    private static class ThreadImpl implements Runnable {

        private static int counter = 0;

        @Override
        public synchronized void run() {
            int localValue = ++counter;
            try {
                Thread.sleep((long) (Math.random() * 20));
            } catch (InterruptedException ignored) {
            }
            System.out.println(localValue);
        }
    }

}
