package za.co.ccm.call_centre_manager.api.controller.model.response;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import za.co.ccm.call_centre_manager.api.repository.entity.Agent;

@Getter
@Setter

public class AgentPage {
    private String firstName;
    private String lastname;
    private String manager;
    private String team;

    public AgentPage(@NonNull Agent agent) {
        this.firstName = agent.getFirstname();
        this.lastname = agent.getLastname();
        this.manager = agent.getManager() != null ?
                agent.getManager().getFirstname() + " " +
                agent.getManager().getLastname() : null;
        this.team = agent.getTeam() != null ?
                agent.getTeam().getName() : null;
    }
}
