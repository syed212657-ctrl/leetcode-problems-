class Foo {
    // Initialize semaphores with 0 permits so they block initially
    private Semaphore sem1 = new Semaphore(0);
    private Semaphore sem2 = new Semaphore(0);

    public Foo() {
        // Constructor (optional, but good practice)
    }

    public void first(Runnable printFirst) throws InterruptedException {
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        // Release a permit to unblock the 'second' method
        sem1.release();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        // Wait until 'first' releases sem1
        sem1.acquire();
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        // Release a permit to unblock the 'third' method
        sem2.release();
    }

    public void third(Runnable printThird) throws InterruptedException {
        // Wait until 'second' releases sem2
        sem2.acquire();
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }
}

// Optional: Main class to test the implementation
public class Main {
    public static void main(String[] args) {
        Foo foo = new Foo();

        // Create the three Runnables
        Runnable printFirst = () -> System.out.print("first");
        Runnable printSecond = () -> System.out.print("second");
        Runnable printThird = () -> System.out.print("third");

        // Thread A calls first()
        Thread threadA = new Thread(() -> {
            try {
                foo.first(printFirst);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // Thread B calls second()
        Thread threadB = new Thread(() -> {
            try {
                foo.second(printSecond);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // Thread C calls third()
        Thread threadC = new Thread(() -> {
            try {
                foo.third(printThird);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // Start them in any arbitrary order to prove synchronization works
        threadC.start();
        threadB.start();
        threadA.start();
    }
}
