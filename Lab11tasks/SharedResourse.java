public class SharedResourse {
    private int value;
    private boolean bChanged = false;

    public synchronized int get() {
        while (!bChanged) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        bChanged = false;
        notify();
        return value;
    }

    public synchronized void set(int value) {
        this.value = value;
        bChanged = true;
        notify();
    }
}

