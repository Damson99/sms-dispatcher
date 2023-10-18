package com.smsdispatcher.infrastructure.rest.google;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class EvaluateUrlCommand {
    private final String uri;
    private final Set<GoogleThreatTypesEnum> threatTypes;
    private final boolean allowScan;

    public static EvaluateUrlCommand fromUrl(String uri) {
        return new EvaluateUrlCommand(uri, Set.of(GoogleThreatTypesEnum.values()), true);
    }
}
