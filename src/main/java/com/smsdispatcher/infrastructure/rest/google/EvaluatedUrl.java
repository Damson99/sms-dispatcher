package com.smsdispatcher.infrastructure.rest.google;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public record EvaluatedUrl(List<Score> scores) {

    public record Score(String threatType, String confidenceLevel) {}

    boolean isSafe() {
        return scores.stream()
                .filter(score -> SafeConfidenceLevelEnum.SAFE_THREAT_TYPES.contains(score.confidenceLevel))
                .findFirst()
                .isEmpty();
    }

    enum SafeConfidenceLevelEnum {
        SAFE,
        LOW;

        static final Set<String> SAFE_THREAT_TYPES = Arrays.stream(SafeConfidenceLevelEnum.values())
                .map(SafeConfidenceLevelEnum::name)
                .collect(Collectors.toSet());
    }
}