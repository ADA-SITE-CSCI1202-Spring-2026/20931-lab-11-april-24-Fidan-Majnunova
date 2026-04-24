public class Main_task2 {
    public static void main(String[] args) {

        SharedResourse resource = new SharedResourse();

        Thread producer = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                resource.set(i);
                System.out.println("Produced: " + i);
            }
        });

        Thread consumer = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                int val = resource.get();
                System.out.println("Consumed: " + val);
            }
        });

        producer.start();
        consumer.start();
    }
}
