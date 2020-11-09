package za.co.ccm.call_centre_manager.api.controller.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ManagerRequest {
    private String firstname;
    private String lastname;
    private String idNumber;
}
