package za.co.ccm.call_centre_manager.api.service;

import org.springframework.stereotype.Service;
import za.co.ccm.call_centre_manager.api.controller.model.AgentDto;
import za.co.ccm.call_centre_manager.api.controller.model.TeamDto;
import za.co.ccm.call_centre_manager.api.controller.model.request.AgentTeamEdit;
import za.co.ccm.call_centre_manager.api.controller.model.request.TeamRequest;
import za.co.ccm.call_centre_manager.api.exception.InvalidFieldException;
import za.co.ccm.call_centre_manager.api.exception.NotFoundException;
import za.co.ccm.call_centre_manager.api.repository.TeamRepository;
import za.co.ccm.call_centre_manager.api.repository.entity.Team;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamService {

    private final TeamRepository teamRepository;
    private final AgentService agentService;

    public TeamService(TeamRepository teamRepository,
                       AgentService agentService) {
        this.teamRepository = teamRepository;
        this.agentService = agentService;
    }

    public List<TeamDto> getAllTeams() {
        var teams = teamRepository.findAll();
        return teams.stream().map(TeamDto::new).collect(Collectors.toList());
    }

    public TeamDto getTeamById(Long id) throws NotFoundException {
        var result = teamRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Provided team id does not exist"));
        return new TeamDto(result);
    }

    public void createATeam(TeamRequest request) throws InvalidFieldException {
        if (request.getName() == null || request.getName().isBlank()) {
            throw new InvalidFieldException("Team name cannot be null or blank");
        }

        var team = new Team();
        team.setName(request.getName());
        teamRepository.save(team);
    }

    public void assignAgentToTeam(Long id, AgentTeamEdit edit) throws NotFoundException {
        var team = teamRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Provided team id does not exist"));

        agentService.assignTeamToAgent(team, edit);
    }
}
