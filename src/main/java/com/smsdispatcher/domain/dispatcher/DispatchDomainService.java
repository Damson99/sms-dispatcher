package com.smsdispatcher.domain.dispatcher;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
class DispatchDomainService extends DomainService {
    private final ContentEvaluatorProvider contentEvaluatorProvider;

    @Override
    public EvaluatedContent evaluateContentFor(String message, NetworkSubscriber networkSubscriber) {
        final List<String> urlsToEvaluate = getUrlsFromMessage(message);
        if (urlsToEvaluate.size() >= MAX_URLS_IN_MESSAGE) {
            log.warn("many urls detected {}", message);
            return EvaluatedContent.THREAT;
        }

        for (String url : urlsToEvaluate) {
            final EvaluationOfContent evaluationOfContent = contentEvaluatorProvider.performEvaluating(url);
            if (EvaluationOfContent.isThreat(evaluationOfContent)) {
                log.warn("threat detected {}", url);
                return EvaluatedContent.THREAT;
            }
        }
        return EvaluatedContent.OK;
    }
}
