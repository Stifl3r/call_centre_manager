package za.co.ccm.call_centre_manager.api.service;

import org.springframework.stereotype.Service;
import za.co.ccm.call_centre_manager.api.controller.model.TeamDto;
import za.co.ccm.call_centre_manager.api.controller.model.request.AgentToTeamEdit;
import za.co.ccm.call_centre_manager.api.controller.model.request.ManagerToTeamEdit;
import za.co.ccm.call_centre_manager.api.controller.model.request.TeamRequest;
import za.co.ccm.call_centre_manager.api.exception.InvalidFieldException;
import za.co.ccm.call_centre_manager.api.exception.NotFoundException;
import za.co.ccm.call_centre_manager.api.repository.ManagerRepository;
import za.co.ccm.call_centre_manager.api.repository.TeamManagerRepository;
import za.co.ccm.call_centre_manager.api.repository.TeamRepository;
import za.co.ccm.call_centre_manager.api.repository.entity.Team;
import za.co.ccm.call_centre_manager.api.repository.entity.TeamManager;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamService {

    private final TeamRepository teamRepository;
    private final AgentService agentService;
    private final TeamManagerRepository teamManagerRepository;
    private final ManagerRepository managerRepository;


    public TeamService(TeamRepository teamRepository,
                       AgentService agentService,
                       TeamManagerRepository teamManagerRepository,
                       ManagerRepository managerRepository) {
        this.teamRepository = teamRepository;
        this.agentService = agentService;
        this.teamManagerRepository = teamManagerRepository;
        this.managerRepository = managerRepository;
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

    public void assignAgentToTeam(Long id, AgentToTeamEdit edit) throws NotFoundException, InvalidFieldException {
        var team = teamRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Provided team id does not exist"));

        var agent = agentService.getAgentEntityById(edit.getAgentId());
        var teamManager = teamManagerRepository.findByManager(agent.getManager());

        if (teamManager.isEmpty()) {
            throw new InvalidFieldException("Agent has no manager assigned and cannot be assigned to a team");
        }

        if (teamManager.get().getTeam().getTeamId() != team.getTeamId()) {
            throw new InvalidFieldException("Agent cannot be assigned to a team that os not managed by their manager");
        }

        agentService.assignTeamToAgent(team, edit);
    }


    public void assignManagerToTeam(Long id, ManagerToTeamEdit edit) throws NotFoundException, InvalidFieldException {
        var team = teamRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Provided team id does not exist"));

        var teamManagers = teamManagerRepository.findAllByTeam(team);
        if (teamManagers.size() >= 2) {
            throw new InvalidFieldException("Team cannot have more than two(2) managers");
        }

        var manager = managerRepository.findById(edit.getId())
                .orElseThrow(() -> new NotFoundException("Provided manager id does not exist"));

        var managedTeams = teamManagerRepository.findAllByManager(manager);
        if (managedTeams.size() >= 2) {
            throw new InvalidFieldException("Manager cannot be assigned to more than two(2) teams");
        }

        var newTeamManager = new TeamManager();
        newTeamManager.setTeam(team);
        newTeamManager.setManager(manager);
        teamManagerRepository.save(newTeamManager);
    }
}
