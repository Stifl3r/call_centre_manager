package za.co.ccm.call_centre_manager.api.controller.model.request;

import lombok.Getter;
import lombok.Setter;
import za.co.ccm.call_centre_manager.api.repository.entity.Agent;

@Getter
@Setter
public class AgentRequest {
    private String firstname;
    private String lastname;
    private String idNumber;
}
