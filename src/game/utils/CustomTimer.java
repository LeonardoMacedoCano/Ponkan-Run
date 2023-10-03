package game.utils;

public class CustomTimer {
    private static final long NANOS_IN_MILLIS = 1_000_000L;
    private static final long NANOS_IN_SECOND = 1_000_000_000L;
    private static final int NUM_FPS_FOR_AVERAGE = 10;
    private final Animation game;
    private final long targetTime;
    private long timeBefore;
    private long sleepTime;
    private long jumps;
    private long delay;
    private long numDelays;
    private long excess;
    private long elapsedTime;
    private long previousStatisticTime;
    private long frameCounter;
    private long skippedFrames;
    private long totalSkippedFrames;
    private long statIntervalNanos;
    private long statisticsCounter;
    private double averageFPS;
    private double averageUPS;
    private final double[] FPSRecorder;
    private final double[] UPSRecorder;

    public CustomTimer(Animation game, int targetFPS) {
        this.game = game;
        this.targetTime = targetFPS * NANOS_IN_MILLIS;
        this.FPSRecorder = new double[NUM_FPS_FOR_AVERAGE];
        this.UPSRecorder = new double[NUM_FPS_FOR_AVERAGE];
        setSleepTime(this.targetTime);
        setPreviousStatisticTime(this.timeBefore = System.nanoTime());
    }

    public void normalize() {
        long currentTime = System.nanoTime();
        long elapsedNanos = currentTime - getTimeBefore();
        setSleepTime((getTargetTime() - elapsedNanos) - getDelay());

        if (getSleepTime() > 0) {
            try {
                Thread.sleep(getSleepTime() / NANOS_IN_MILLIS);
            } catch (InterruptedException e) {
                System.err.println("An error occurred while waiting in the 'normalize' method: " + e.getMessage());
                System.exit(1);
            }

            setDelay((System.nanoTime() - currentTime) - getSleepTime());
        } else {
            setExcess(getExcess() - getSleepTime());
            setDelay(0L);
            long maxDelays = 1;
            if (getNumDelays() < maxDelays) {
                Thread.yield();
            }
            setNumDelays(getNumDelays() + 1);
        }
        setTimeBefore(System.nanoTime());
    }


    public void skipFrames() {
        setJumps(0);

        int MAX_FRAME_JUMP = 25;
        while ((getExcess() > getTargetTime()) && (getJumps() < MAX_FRAME_JUMP)) {
            setExcess(getExcess() - getTargetTime());
            if (game != null) game.update();
            setJumps(getJumps() + 1);
        }
        setSkippedFrames(getSkippedFrames() + getJumps());
    }

    public void generateStatistics() {
        setFrameCounter(getFrameCounter() + 1);
        setStatIntervalNanos(getStatIntervalNanos() + getTargetTime());

        long MAX_INTERVAL_FOR_STATISTICS = 1000000000L;
        if (getStatIntervalNanos() >= MAX_INTERVAL_FOR_STATISTICS) {
            double currentFPS = 0.0;
            double currentUPS = 0.0;

            long currentTime = System.nanoTime();
            long timeBetweenStatistics = currentTime - getPreviousStatisticTime();

            setElapsedTime(getElapsedTime() + timeBetweenStatistics);
            setTotalSkippedFrames(getTotalSkippedFrames() + getSkippedFrames());
            setSkippedFrames(0);

            if (getElapsedTime() > 0) {
                currentFPS = (((double) getFrameCounter() / getElapsedTime()) * NANOS_IN_SECOND);
                currentUPS = (((double) (getFrameCounter() + getTotalSkippedFrames()) / getElapsedTime()) * NANOS_IN_SECOND);
            }

            FPSRecorder[(int) getStatisticsCounter() % NUM_FPS_FOR_AVERAGE] = currentFPS;
            UPSRecorder[(int) getStatisticsCounter() % NUM_FPS_FOR_AVERAGE] = currentUPS;

            setStatisticsCounter(getStatisticsCounter() + 1);

            if (getStatisticsCounter() > 0) {
                double totalFPS = 0.0;
                double totalUPS = 0.0;

                for (int i = 0; i < NUM_FPS_FOR_AVERAGE; i++) {
                    totalFPS += FPSRecorder[i];
                    totalUPS += UPSRecorder[i];
                }

                setAverageFPS(totalFPS / getStatisticsCounter());
                setAverageUPS(totalUPS / getStatisticsCounter());
            }

            setStatIntervalNanos(0L);
            setPreviousStatisticTime(currentTime);
        }
    }

    public long getTargetTime() {
        return targetTime;
    }

    public long getTimeBefore() {
        return timeBefore;
    }

    public void setTimeBefore(long timeBefore) {
        this.timeBefore = timeBefore;
    }

    public long getSleepTime() {
        return sleepTime;
    }

    public void setSleepTime(long sleepTime) {
        this.sleepTime = sleepTime;
    }

    public long getJumps() {
        return jumps;
    }

    public void setJumps(long jumps) {
        this.jumps = jumps;
    }

    public long getDelay() {
        return delay;
    }

    public void setDelay(long delay) {
        this.delay = delay;
    }

    public long getNumDelays() {
        return numDelays;
    }

    public void setNumDelays(long numDelays) {
        this.numDelays = numDelays;
    }

    public long getExcess() {
        return excess;
    }

    public void setExcess(long excess) {
        this.excess = excess;
    }

    public long getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(long elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public long getPreviousStatisticTime() {
        return previousStatisticTime;
    }

    public void setPreviousStatisticTime(long previousStatisticTime) {
        this.previousStatisticTime = previousStatisticTime;
    }

    public long getFrameCounter() {
        return frameCounter;
    }

    public void setFrameCounter(long frameCounter) {
        this.frameCounter = frameCounter;
    }

    public long getSkippedFrames() {
        return skippedFrames;
    }

    public void setSkippedFrames(long skippedFrames) {
        this.skippedFrames = skippedFrames;
    }

    public long getTotalSkippedFrames() {
        return totalSkippedFrames;
    }

    public void setTotalSkippedFrames(long totalSkippedFrames) {
        this.totalSkippedFrames = totalSkippedFrames;
    }

    public long getStatIntervalNanos() {
        return statIntervalNanos;
    }

    public void setStatIntervalNanos(long statIntervalNanos) {
        this.statIntervalNanos = statIntervalNanos;
    }

    public long getStatisticsCounter() {
        return statisticsCounter;
    }

    public void setStatisticsCounter(long statisticsCounter) {
        this.statisticsCounter = statisticsCounter;
    }

    public double getAverageFPS() {
        return averageFPS;
    }

    public void setAverageFPS(double averageFPS) {
        this.averageFPS = averageFPS;
    }

    public double getAverageUPS() {
        return averageUPS;
    }

    public void setAverageUPS(double averageUPS) {
        this.averageUPS = averageUPS;
    }

    public void reset() {
        setStatIntervalNanos(0L);
        setPreviousStatisticTime(0);
        setFrameCounter(0);
        setStatisticsCounter(0);
        setAverageFPS(0.0);
        setSkippedFrames(0L);
        setTotalSkippedFrames(0L);
        setAverageUPS(0.0);
        setElapsedTime(0L);
        setJumps(0);
        setDelay(0);
        setNumDelays(0);
        setExcess(0);

        for (int i = 0; i < FPSRecorder.length; ++i) {
            FPSRecorder[i] = 0;
            UPSRecorder[i] = 0;
        }

        setSleepTime(getTargetTime());
        setTimeBefore(System.nanoTime());
        setPreviousStatisticTime(getTimeBefore());
    }

}
