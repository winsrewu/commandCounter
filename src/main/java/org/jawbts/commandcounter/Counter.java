package org.jawbts.commandcounter;

public class Counter {
    private static Counter INSTANCE = new Counter();

    private long count = 0;

    public static Counter getInstance() {
        return INSTANCE;
    }

    public long tick() {
        long k = count;
        count = 0;
        return k;
    }

    public void increase(long amount) {
        count += amount;
    }
}
