import java.util.concurrent.locks.Lock;

public class Foo {
    final Object lock = new Object();
    int status = 1;

    public Foo() {

    }

    public void first(Runnable printFirst) throws InterruptedException {
        synchronized (lock) {
            while (status != 1) {
                lock.wait();
            }
            status = 2;
            printFirst.run();
            lock.notifyAll();
        }
    }

    public void second(Runnable printSecond) throws InterruptedException {
        synchronized (lock) {
            while (status != 2) {
                lock.wait();
            }
            status = 3;
            printSecond.run();
            lock.notifyAll();
        }
    }

    public void third(Runnable printThird) throws InterruptedException {
        synchronized (lock) {
            while (status != 3) {
                lock.wait();
            }
            status = 1;
            printThird.run();
            lock.notifyAll();
        }
    }
}
