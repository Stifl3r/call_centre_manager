package za.co.ccm.call_centre_manager.api.exception;

import lombok.Getter;

@Getter
public class NotFoundException extends GenericException {
    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, int errorCode) {
        super(message, errorCode);
    }
}
