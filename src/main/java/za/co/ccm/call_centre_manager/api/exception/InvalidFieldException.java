package za.co.ccm.call_centre_manager.api.exception;

import lombok.Getter;

@Getter
public class InvalidFieldException extends GenericException {

    public InvalidFieldException(String message) {
        super(message);
    }

    public InvalidFieldException(String message, int errorCode) {
        super(message, errorCode);
    }
}
