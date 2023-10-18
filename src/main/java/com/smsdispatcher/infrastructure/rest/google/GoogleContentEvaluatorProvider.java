package com.smsdispatcher.infrastructure.rest.google;

import com.smsdispatcher.domain.dispatcher.ContentEvaluatorProvider;
import com.smsdispatcher.domain.dispatcher.EvaluationOfContent;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@RequiredArgsConstructor
class GoogleContentEvaluatorProvider implements ContentEvaluatorProvider {
    private final WebClient googleWebClient;
    private final String googleWebClientEndpoint;

    @Override
    public EvaluationOfContent performEvaluating(String url) {
        final EvaluateUrlCommand command = EvaluateUrlCommand.fromUrl(url);
        final EvaluatedUrl evaluatedUrl = perform(command);

        if (evaluatedUrl == null) {
            throw new GoogleProviderException();
        }
        return evaluatedUrl.isSafe()
                ? EvaluationOfContent.OK
                : EvaluationOfContent.THREAT;
    }

    private EvaluatedUrl perform(EvaluateUrlCommand command) {
        return googleWebClient
                .post()
                .uri(googleWebClientEndpoint)
                .body(BodyInserters.fromValue(command))
                .retrieve()
                .bodyToMono(EvaluatedUrl.class)
                .block();
    }
}
