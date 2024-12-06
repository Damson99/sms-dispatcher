package com.smsdispatcher.domain.dispatcher;

public interface ContentEvaluatorProvider {

    EvaluationOfContent performEvaluating(String url);
}
