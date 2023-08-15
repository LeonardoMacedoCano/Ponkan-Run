package game.utils;

public class CustomTimer {
    private Animation origin;
    private long statIntervalNanos = 0L;
    private long previousStatisticTime;
    private long frameCounter = 0;
    private double[] FPSRecorder;
    private long statisticsCounter = 0;
    private double averageFPS = 0.0;
    private long skippedFrames = 0L;
    private long totalSkippedFrames = 0L;
    private double[] UPSRecorder;
    private double averageUPS = 0.0;
    private long elapsedTime = 0L;
    private long time;
    private long timeBefore;
    private long sleepTime;
    private long jumps;
    private long delay = 0;
    private long numDelays = 0;
    private long excess = 0;
    private final int NUM_FPS_FOR_AVERAGE = 10;

    public CustomTimer(Animation origin, int time) {
        this.origin = origin;
        this.time = time * 1000000L;

        this.FPSRecorder = new double[this.NUM_FPS_FOR_AVERAGE];
        this.UPSRecorder = new double[this.NUM_FPS_FOR_AVERAGE];

        this.sleepTime = this.time;
        this.previousStatisticTime = this.timeBefore = System.nanoTime();
    }

    public void normalize() {
        long tempoAfter = System.nanoTime();
        long acquiredTime = tempoAfter - timeBefore;
        sleepTime = (time - acquiredTime) - delay;

        if(sleepTime > 0) {
            try{Thread.sleep(sleepTime / 1000000L);}
            catch(Exception e){ e.printStackTrace();}

            delay = (System.nanoTime() - tempoAfter) - sleepTime;
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
        while ((excess > this.time) && (jumps < MAX_FRAME_JUMP)) {
            excess -= this.time;
            if (this.origin != null) this.origin.update();
            jumps++;
        }
        skippedFrames += jumps;
    }

    public void generateStatistics() {
        ++frameCounter;
        statIntervalNanos += time;

        long MAX_INTERVAL_FOR_STATISTICS = 1000000000L;
        if (statIntervalNanos >= MAX_INTERVAL_FOR_STATISTICS) {
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
                currentFPS = (((double) frameCounter / elapsedTime) * 1000000000L);
                currentUPS = (((double) (frameCounter + totalSkippedFrames) / elapsedTime) * 1000000000L);
            }

            FPSRecorder[(int) statisticsCounter % NUM_FPS_FOR_AVERAGE] = currentFPS;
            UPSRecorder[(int) statisticsCounter++ % NUM_FPS_FOR_AVERAGE] = currentUPS;

            for (int i = 0; i < NUM_FPS_FOR_AVERAGE; i++) {
                totalFPS += FPSRecorder[i];
                totalUPS += UPSRecorder[i];
            }

            if (statisticsCounter < NUM_FPS_FOR_AVERAGE) {
                averageFPS = totalFPS / statisticsCounter;
                averageUPS = totalUPS / statisticsCounter;
            } else {
                averageFPS = totalFPS / NUM_FPS_FOR_AVERAGE;
                averageUPS = totalUPS / NUM_FPS_FOR_AVERAGE;
            }

            statIntervalNanos = 0L;
            previousStatisticTime = currentTime;
        }
    }

    public double getFps() { return this.averageFPS; }
    public double getUps() { return this.averageUPS; }

    public void restart() {
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

        for (int i = 0; i < this.FPSRecorder.length; ++i) {
            this.FPSRecorder[i] = 0;
            this.UPSRecorder[i] = 0;
        }

        this.sleepTime = this.time;
        this.previousStatisticTime = this.timeBefore = System.nanoTime();
    }
}
