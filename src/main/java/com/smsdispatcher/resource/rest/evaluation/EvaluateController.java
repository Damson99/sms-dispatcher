package com.smsdispatcher.resource.rest.evaluation;

import com.smsdispatcher.application.ApplicationEvaluatorService;
import com.smsdispatcher.application.EvaluateSMSContentCommand;
import com.smsdispatcher.domain.dispatcher.EvaluatedContent;
import com.smsdispatcher.resource.rest.ApiVersion;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/evaluate-sms-content", produces = {MediaType.APPLICATION_JSON_VALUE, ApiVersion.V1_JSON})
@RequiredArgsConstructor
class EvaluateController {
    private final ApplicationEvaluatorService applicationEvaluatorService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "500", content = @Content(schema = @Schema()))
    })
    @PostMapping
    public ResponseEntity<EvaluatedContent> evaluate(@RequestBody EvaluateSMSContentCommand command) {
        return ResponseEntity.ok(applicationEvaluatorService.handle(command));
    }
}
