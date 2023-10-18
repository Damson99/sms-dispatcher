package com.smsdispatcher.domain.dispatcher;

public record EvaluatedContent(EvaluationOfContent evaluationOfContent) {
    public static final EvaluatedContent NONE = new EvaluatedContent(EvaluationOfContent.NONE);
    public static final EvaluatedContent OK = new EvaluatedContent(EvaluationOfContent.OK);
    public static final EvaluatedContent THREAT = new EvaluatedContent(EvaluationOfContent.THREAT);
}

enum EvaluationOfContent {
    OK,
    THREAT,
    NONE;

    public static boolean isThreat(EvaluationOfContent evaluationOfContent) {
        return evaluationOfContent == THREAT;
    }
}
