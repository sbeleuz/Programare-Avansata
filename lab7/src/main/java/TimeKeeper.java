public class TimeKeeper implements Runnable {
    private long startTime = System.currentTimeMillis();
    private long endTime;
    private int maxTime; // in seconds
    static boolean isTime = true;

    public TimeKeeper(int maxTime) {
        this.maxTime = maxTime;
    }

    public double getRunningTime() {
        return (double) (endTime - startTime) / 1000;
    }

    @Override
    public void run() {
        while (isTime) {
            endTime = System.currentTimeMillis();
            if (getRunningTime() > maxTime) {
                isTime = false;
            }
        }
    }
}
