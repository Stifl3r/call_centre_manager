package za.co.ccm.call_centre_manager.api.controller.model.error;

import lombok.Getter;

@Getter
public enum ApiErrorType {
    API_ERROR("apiError"),
    NOT_FOUND_ERROR("notFoundError"),
    BACKEND_ERROR("backendError"),
    VALIDATION_ERROR("validationError");

    private final String code;

    ApiErrorType(String code) {
        this.code = code;
    }
}
