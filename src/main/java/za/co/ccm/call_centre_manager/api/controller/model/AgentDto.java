package za.co.ccm.call_centre_manager.api.controller.model;

import lombok.Getter;
import lombok.Setter;
import za.co.ccm.call_centre_manager.api.repository.entity.Agent;

@Getter
@Setter
public class AgentDto {
    private Long id;
    private String firstname;
    private String lastname;
    private String idNumber;
    private TeamDto team;
    private ManagerDto manager;

    public AgentDto(Agent agent) {
        this.id = agent.getAgentId();
        this.firstname = agent.getFirstname();
        this.lastname = agent.getLastname();
        this.idNumber = agent.getIdNumber();
//        this.team = new TeamDto(agent.getTeam());
    }
}
