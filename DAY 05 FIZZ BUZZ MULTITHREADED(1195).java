class FizzBuzz {
    private int n;
    private int current = 1;

    // Semaphores for thread coordination
    private Semaphore semNum = new Semaphore(1);
    private Semaphore semFizz = new Semaphore(0);
    private Semaphore semBuzz = new Semaphore(0);
    private Semaphore semFizzBuzz = new Semaphore(0);

    public FizzBuzz(int n) {
        this.n = n;
    }

    // Helper method to transition to the next number and release the appropriate thread
    private void unlockNext() {
        current++;
        if (current > n) {
            // Wake everyone up so they can exit their while loops cleanly
            semNum.release();
            semFizz.release();
            semBuzz.release();
            semFizzBuzz.release();
            return;
        }
        
        if (current % 15 == 0) {
            semFizzBuzz.release();
        } else if (current % 3 == 0) {
            semFizz.release();
        } else if (current % 5 == 0) {
            semBuzz.release();
        } else {
            semNum.release();
        }
    }

    // Fizz thread runner
    public void fizz(Runnable printFizz) throws InterruptedException {
        while (current <= n) {
            semFizz.acquire();
            if (current > n) break;
            printFizz.run(); // prints "fizz"
            unlockNext();
        }
    }

    // Buzz thread runner
    public void buzz(Runnable printBuzz) throws InterruptedException {
        while (current <= n) {
            semBuzz.acquire();
            if (current > n) break;
            printBuzz.run(); // prints "buzz"
            unlockNext();
        }
    }

    // FizzBuzz thread runner
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        while (current <= n) {
            semFizzBuzz.acquire();
            if (current > n) break;
            printFizzBuzz.run(); // prints "fizzbuzz"
            unlockNext();
        }
    }

    // Number thread runner
    public void number(IntConsumer printNumber) throws InterruptedException {
        while (current <= n) {
            semNum.acquire();
            if (current > n) break;
            printNumber.accept(current); // prints the current integer
            unlockNext();
        }
    }
}
