package game.utils;

public class CustomTimer {
    private static final long NANOS_IN_MILLIS = 1_000_000L;
    private static final long NANOS_IN_SECOND = 1_000_000_000L;
    private static final int MAX_FPS_SAMPLES = 10;
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
        this.FPSRecorder = new double[MAX_FPS_SAMPLES];
        this.UPSRecorder = new double[MAX_FPS_SAMPLES];
        this.sleepTime = this.targetTime;
        this.previousStatisticTime = this.timeBefore = System.nanoTime();
    }

    public void normalize() {
        long currentTime = System.nanoTime();
        long elapsedNanos = currentTime - timeBefore;
        sleepTime = (targetTime - elapsedNanos) - delay;

        if (sleepTime > 0) {
            try {
                Thread.sleep(sleepTime / NANOS_IN_MILLIS);
            } catch (InterruptedException e) {
                System.err.println("An error occurred while waiting in the 'normalize' method: " + e.getMessage());
                System.exit(1);
            }
            delay = (System.nanoTime() - currentTime) - sleepTime;
        } else {
            excess -= sleepTime;
            delay = 0L;
            long maxDelays = 1;

            if (++numDelays >= maxDelays) {
                Thread.yield();
                numDelays = 0;
            }
        }
        timeBefore = System.nanoTime();
    }

    public void skipFrames() {
        jumps = 0;
        int MAX_FRAME_JUMP = 25;

        while ((excess > targetTime) && (jumps < MAX_FRAME_JUMP)) {
            excess -= targetTime;
            if (game != null) game.update();
            jumps++;
        }
        skippedFrames += jumps;
    }

    public void generateStatistics() {
        ++frameCounter;
        statIntervalNanos += targetTime;

        if (statIntervalNanos >= NANOS_IN_SECOND) {
            double currentFPS = 0.0;
            double currentUPS = 0.0;
            double totalFPS = 0.0;
            double totalUPS = 0.0;
            long currentTime = System.nanoTime();
            long timeBetweenStatistics = currentTime - previousStatisticTime;
            elapsedTime += timeBetweenStatistics;
            totalSkippedFrames += skippedFrames;
            skippedFrames = 0;

            if (elapsedTime > 0) {
                currentFPS = (((double) frameCounter / elapsedTime) * NANOS_IN_SECOND);
                currentUPS = (((double) (frameCounter + totalSkippedFrames) / elapsedTime) * NANOS_IN_SECOND);
            }

            FPSRecorder[(int) statisticsCounter % MAX_FPS_SAMPLES] = currentFPS;
            UPSRecorder[(int) statisticsCounter++ % MAX_FPS_SAMPLES] = currentUPS;

            for (int i = 0; i < MAX_FPS_SAMPLES; i++) {
                totalFPS += FPSRecorder[i];
                totalUPS += UPSRecorder[i];
            }

            if (statisticsCounter < MAX_FPS_SAMPLES) {
                averageFPS = totalFPS / statisticsCounter;
                averageUPS = totalUPS / statisticsCounter;
            } else {
                averageFPS = totalFPS / MAX_FPS_SAMPLES;
                averageUPS = totalUPS / MAX_FPS_SAMPLES;
            }

            statIntervalNanos = 0L;
            previousStatisticTime = currentTime;
        }
    }

    public double getAverageFPS() {
        return averageFPS;
    }

    public double getAverageUPS() {
        return averageUPS;
    }

    public void reset() {
        statIntervalNanos = 0L;
        previousStatisticTime = 0;
        frameCounter = 0;
        statisticsCounter = 0;
        averageFPS = 0.0;
        skippedFrames = 0L;
        totalSkippedFrames = 0L;
        averageUPS = 0.0;
        elapsedTime = 0L;
        jumps = 0;
        delay = 0;
        numDelays = 0;
        excess = 0;

        for (int i = 0; i < FPSRecorder.length; ++i) {
            FPSRecorder[i] = 0;
            UPSRecorder[i] = 0;
        }

        sleepTime = targetTime;
        previousStatisticTime = timeBefore = System.nanoTime();
    }
}
