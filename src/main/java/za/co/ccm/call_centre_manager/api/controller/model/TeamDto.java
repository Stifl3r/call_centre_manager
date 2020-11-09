package za.co.ccm.call_centre_manager.api.controller.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import za.co.ccm.call_centre_manager.api.repository.entity.Team;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
public class TeamDto {
    private Long id;
    private String name;
    private Set<ManagerDto> managers;

    public TeamDto(Team team) {
        this.id = team.getTeamId();
        this.name = team.getName();
    }

    public static TeamDto mapEntityToDto(Team team) {
        return new TeamDto(team.getTeamId(),
                team.getName(),
                team.getManagers().stream().map(manager -> new ManagerDto(manager.getFirstname(), manager.getLastname())).collect(Collectors.toSet()));
    }
}
