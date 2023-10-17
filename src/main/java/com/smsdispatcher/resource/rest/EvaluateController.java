package com.smsdispatcher.resource.rest;

import com.smsdispatcher.application.ApplicationEvaluatorService;
import com.smsdispatcher.application.EvaluateSMSContentCommand;
import com.smsdispatcher.domain.dispatcher.EvaluatedContent;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/evaluate-sms-content", produces = {MediaType.APPLICATION_JSON_VALUE, ApiVersion.V1_JSON})
@RequiredArgsConstructor
class EvaluateController {
    private final ApplicationEvaluatorService applicationEvaluatorService;

    @PostMapping
    public ResponseEntity<EvaluatedContent> evaluate(EvaluateSMSContentCommand command) {
        return ResponseEntity.ok(applicationEvaluatorService.handle(command));
    }
}
