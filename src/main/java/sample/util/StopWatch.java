package sample.util;

import java.time.Instant;

public class StopWatch {
    Instant start = Instant.now();
    Instant checked = Instant.now();
    
    public static StopWatch start() {
        return new StopWatch();
    }
    
    public long elapsed() {
        Instant lastChecked = checked;
        checked = Instant.now();
        return checked.toEpochMilli() - lastChecked.toEpochMilli();
    }
}
