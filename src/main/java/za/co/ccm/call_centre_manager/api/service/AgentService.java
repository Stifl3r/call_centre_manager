package za.co.ccm.call_centre_manager.api.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import za.co.ccm.call_centre_manager.api.controller.model.AgentDto;
import za.co.ccm.call_centre_manager.api.controller.model.request.AgentRequest;
import za.co.ccm.call_centre_manager.api.controller.model.request.AgentToManagerEdit;
import za.co.ccm.call_centre_manager.api.controller.model.request.AgentToTeamEdit;
import za.co.ccm.call_centre_manager.api.controller.model.response.AgentPage;
import za.co.ccm.call_centre_manager.api.controller.model.response.AgentResponse;
import za.co.ccm.call_centre_manager.api.exception.InvalidFieldException;
import za.co.ccm.call_centre_manager.api.exception.NotFoundException;
import za.co.ccm.call_centre_manager.api.repository.AgentRepository;
import za.co.ccm.call_centre_manager.api.repository.entity.Agent;
import za.co.ccm.call_centre_manager.api.repository.entity.Manager;
import za.co.ccm.call_centre_manager.api.repository.entity.Team;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AgentService {

    private final AgentRepository agentRepository;

    public AgentService(AgentRepository agentRepository) {
        this.agentRepository = agentRepository;
    }
    public List<AgentResponse> getAllAgents() {
        var agents = agentRepository.findAll();
        return agents.stream().map(AgentResponse::new).collect(Collectors.toList());
    }

    public Agent getAgentEntityById(Long id) throws NotFoundException {
        var result = agentRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Provided agent id does not exist"));
        return result;
    }

    public AgentDto getAgentById(Long id) throws NotFoundException {
        return new AgentDto(getAgentEntityById(id));
    }

    public void createAnAgent(AgentRequest request) throws InvalidFieldException {
        if (request.getFirstname() == null || request.getFirstname().isBlank()) {
            throw new InvalidFieldException("First name is a required field");
        }

        if (request.getLastname() == null || request.getLastname().isBlank()) {
            throw new InvalidFieldException("Last name is a required field");
        }

        if (request.getIdNumber() == null || request.getIdNumber().isBlank()) {
            throw new InvalidFieldException("Id number is a required field");
        }
        var agent = new Agent();
        agent.setFirstname(request.getFirstname());
        agent.setLastname(request.getLastname());
        agent.setIdNumber(request.getIdNumber());
        agentRepository.save(agent);
    }

    public void assignTeamToAgent(Team team, AgentToTeamEdit edit) throws NotFoundException {
        var agent = getAgentEntityById(edit.getAgentId());

        agent.setTeam(team);
        agentRepository.save(agent);
    }

    public void assignManagerToAgent(Manager manager, AgentToManagerEdit edit) throws NotFoundException {
        var agent = getAgentEntityById(edit.getAgentId());

        agent.setManager(manager);
        agentRepository.save(agent);
    }

    public Page<AgentPage> getPaginatedAgents(PageRequest pageRequest) {
        var agents = agentRepository.findByFilter(pageRequest);

        return agents.map(AgentPage::new);
    }

    public void deleteAgent(Long id) throws NotFoundException {
        var agent = getAgentEntityById(id);
        agentRepository.delete(agent);
    }

    public List<Agent> getAgentsForManager(Manager manager) {
        return agentRepository.findAllByManager(manager);
    }
}
