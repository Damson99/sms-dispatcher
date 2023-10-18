package com.smsdispatcher.domain.dispatcher;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
class DispatchDomainService extends DomainService {
    private final ContentEvaluatorProvider contentEvaluatorProvider;

    DispatchDomainService(ContentEvaluatorProvider contentEvaluatorProvider, UrlDetector urlDetector) {
        super(urlDetector);
        this.contentEvaluatorProvider = contentEvaluatorProvider;
    }

    @Override
    public EvaluatedContent evaluateContentFor(String message, NetworkSubscriber networkSubscriber) {
        if (networkSubscriber.isNotMember()) {
            return EvaluatedContent.NOT_MEMBER;
        }

        final List<String> urlsToEvaluate = getUrlsFromMessage(message);
        final int urlsOccurrences = urlsToEvaluate.size();
        if (urlsOccurrences == 0) {
            return EvaluatedContent.OK;
        } else if (urlsOccurrences >= MAX_URLS_IN_MESSAGE) {
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
