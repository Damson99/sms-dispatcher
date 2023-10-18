package com.smsdispatcher.resource.rest.membership;

import com.smsdispatcher.application.ApplicationEvaluatorService;
import com.smsdispatcher.application.ChangeMembershipCommand;
import com.smsdispatcher.resource.rest.ApiVersion;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/set-membership", produces = {MediaType.APPLICATION_JSON_VALUE, ApiVersion.V1_JSON})
@RequiredArgsConstructor
class MembershipController {
    private final ApplicationEvaluatorService applicationEvaluatorService;

    @PostMapping
    public ResponseEntity<Void> membership(@RequestBody ChangeMembershipCommand command) {
        applicationEvaluatorService.handle(command);
        return ResponseEntity.ok().build();
    }
}
