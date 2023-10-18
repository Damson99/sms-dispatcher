package com.smsdispatcher.domain.dispatcher;

public enum EvaluationOfContent {
    OK,
    THREAT,
    NONE;

    public static boolean isThreat(EvaluationOfContent evaluationOfContent) {
        return evaluationOfContent == THREAT;
    }
}
