package za.co.ccm.call_centre_manager.api.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import za.co.ccm.call_centre_manager.api.controller.model.error.ApiError;
import za.co.ccm.call_centre_manager.api.controller.model.error.ApiErrorResponse;
import za.co.ccm.call_centre_manager.api.exception.GenericException;

import za.co.ccm.call_centre_manager.api.exception.NotFoundException;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static za.co.ccm.call_centre_manager.api.controller.model.error.ApiErrorType.VALIDATION_ERROR;

@ControllerAdvice(assignableTypes = {
        TeamController.class
})
public class GenericControllerAdvice {

    @ExceptionHandler({
            NotFoundException.class
    })
    @ResponseBody
    @ResponseStatus(NOT_FOUND)
    private ApiErrorResponse handleNotFoundError(Exception e) {
        Throwable cause = e.getCause();

        var ipe = (GenericException) e;
        if (hasUnderlyingCause(cause)) {
            return new ApiErrorResponse(new ApiError(VALIDATION_ERROR, ipe.getErrorCode(), cause.getCause().getMessage(), null));
        }
        return new ApiErrorResponse(new ApiError(VALIDATION_ERROR, ipe.getErrorCode(),  e.getMessage(), null));
    }

    private boolean hasUnderlyingCause(Throwable cause) {
        return cause != null && cause.getCause() != null;
    }
}
