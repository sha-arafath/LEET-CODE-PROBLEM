import java.util.concurrent.locks.ReentrantLock;

class DiningPhilosophers {

    private final ReentrantLock[] forks = new ReentrantLock[5];

    public DiningPhilosophers() {
        for (int i = 0; i < 5; i++) {
            forks[i] = new ReentrantLock();
        }
    }

    // call the given callables to execute actions
    public void wantsToEat(int philosopher,
                           Runnable pickLeftFork,
                           Runnable pickRightFork,
                           Runnable eat,
                           Runnable putLeftFork,
                           Runnable putRightFork) throws InterruptedException {

        int leftFork = philosopher;
        int rightFork = (philosopher + 4) % 5;

        // Pick lower-indexed fork first to prevent deadlocks
        int firstFork = Math.min(leftFork, rightFork);
        int secondFork = Math.max(leftFork, rightFork);

        forks[firstFork].lock();
        try {
            forks[secondFork].lock();
            try {
                // Execute actions in order
                if (firstFork == leftFork) {
                    pickLeftFork.run();
                    pickRightFork.run();
                } else {
                    pickRightFork.run();
                    pickLeftFork.run();
                }

                eat.run();

                if (firstFork == leftFork) {
                    putRightFork.run();
                    putLeftFork.run();
                } else {
                    putLeftFork.run();
                    putRightFork.run();
                }
            } finally {
                forks[secondFork].unlock();
            }
        } finally {
            forks[firstFork].unlock();
        }
    }
}
