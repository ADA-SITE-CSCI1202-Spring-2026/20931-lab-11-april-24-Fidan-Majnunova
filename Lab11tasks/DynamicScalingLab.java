public class DynamicScalingLab {

    static class MathTask implements Runnable 
    {
        private final int taskId;

        public MathTask(int taskId) {
            this.taskId = taskId;
        }
        @Override
        public void run() {
            long sum = 0;

            for (int i = 0; i < 10_000_000; i++) {
                sum += (long) i * i * i + i * taskId;
            }
            System.out.println("Task " + taskId + " finished. Sum: " + sum);
        }
    }
    public static void main(String[] args) {

        int coreCount = Runtime.getRuntime().availableProcessors();
        System.out.println("Detected Core Count: " + coreCount);
        long startSingle = System.currentTimeMillis();
        Thread singleThread = new Thread(new MathTask(1));
        singleThread.start();

        try {
            singleThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long endSingle = System.currentTimeMillis();
        System.out.println("Time (1 thread): " + (endSingle - startSingle) + " ms");
        Thread[] threads = new Thread[coreCount];
        long startMulti = System.currentTimeMillis();

        for (int i = 0; i < coreCount; i++) 
        {
            threads[i] = new Thread(new MathTask(i));
            threads[i].start();
        }
        for (int i = 0; i < coreCount; i++) 
        {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        long endMulti = System.currentTimeMillis();
        System.out.println("Time (" + coreCount + " threads): " + (endMulti - startMulti) + " ms");
    }
}