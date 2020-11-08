package za.co.ccm.call_centre_manager.api.controller.model.response;

import lombok.Getter;
import lombok.Setter;
import za.co.ccm.call_centre_manager.api.repository.entity.Agent;

@Getter
@Setter
public class AgentResponse {private Long id;
    private String firstname;
    private String lastname;
    private String idNumber;

    public AgentResponse(Agent agent) {
        this.id = agent.getAgentId();
        this.firstname = agent.getFirstname();
        this.lastname = agent.getLastname();
        this.idNumber = agent.getIdNumber();
    }
}
