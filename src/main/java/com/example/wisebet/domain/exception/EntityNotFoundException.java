package com.example.wisebet.domain.exception;

import lombok.Getter;

import java.util.Map;

@Getter
public class EntityNotFoundException extends RuntimeException {

    private final Map<Class<?>, String> domainClassIdentifierMap;

    public EntityNotFoundException(String message, Map<Class<?>, String> domainClassIdentifierMap) {
        super(message);
        this.domainClassIdentifierMap = domainClassIdentifierMap;
    }

    @Override
    public String getMessage() {
        StringBuilder errorMessage = new StringBuilder(super.getMessage());
        if (domainClassIdentifierMap != null && !domainClassIdentifierMap.isEmpty()) {
            errorMessage.append(" Missing entities: ");
            domainClassIdentifierMap.forEach((entity, identifier) ->
                    errorMessage.append(entity.getSimpleName()).append(" with identifier ").append(identifier).append("; ")
            );
        }
        return errorMessage.toString();
    }
}
