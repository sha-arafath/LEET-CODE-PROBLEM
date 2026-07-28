import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

class FizzBuzz {
    private int n;
    private final Semaphore fizzSeq = new Semaphore(0);
    private final Semaphore buzzSeq = new Semaphore(0);
    private final Semaphore fizzbuzzSeq = new Semaphore(0);
    private final Semaphore numSeq = new Semaphore(1);

    public FizzBuzz(int n) {
        this.n = n;
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        for (int i = 3; i <= n; i += 3) {
            if (i % 5 != 0) {
                fizzSeq.acquire();
                printFizz.run();
                numSeq.release();
            }
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        for (int i = 5; i <= n; i += 5) {
            if (i % 3 != 0) {
                buzzSeq.acquire();
                printBuzz.run();
                numSeq.release();
            }
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        for (int i = 15; i <= n; i += 15) {
            fizzbuzzSeq.acquire();
            printFizzBuzz.run();
            numSeq.release();
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            numSeq.acquire();
            if (i % 15 == 0) {
                fizzbuzzSeq.release();
            } else if (i % 3 == 0) {
                fizzSeq.release();
            } else if (i % 5 == 0) {
                buzzSeq.release();
            } else {
                printNumber.accept(i);
                numSeq.release();
            }
        }
    }
}
