package za.co.ccm.call_centre_manager.api.controller.model;

import lombok.Getter;
import lombok.Setter;
import za.co.ccm.call_centre_manager.api.repository.entity.Team;

@Getter
@Setter
public class TeamDto {
    private Long id;
    private String name;

    public TeamDto(Team team) {
        this.id = team.getTeamId();
        this.name = team.getName();
    }
}
