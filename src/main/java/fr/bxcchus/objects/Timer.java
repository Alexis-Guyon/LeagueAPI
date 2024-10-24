package fr.bxcchus.objects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Timer {
    private long adjustedTimeLeftInPhase;
    private long internalNowInEpochMs;
    private boolean isInfinite;
    private String phase;
    private long totalTimeInPhase;
}
