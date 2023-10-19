package com.smsdispatcher.resource.rest.membership;

import com.smsdispatcher.application.ApplicationEvaluatorService;
import com.smsdispatcher.application.ChangeMembershipCommand;
import com.smsdispatcher.resource.rest.ApiVersion;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/set-membership", produces = {MediaType.APPLICATION_JSON_VALUE, ApiVersion.V1_JSON})
@RequiredArgsConstructor
class MembershipController {
    private final ApplicationEvaluatorService applicationEvaluatorService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "500", content = @Content(schema = @Schema()))
    })
    @PatchMapping
    public ResponseEntity<Void> membership(@RequestBody ChangeMembershipCommand command) {
        applicationEvaluatorService.handle(command);
        return ResponseEntity.ok().build();
    }
}
