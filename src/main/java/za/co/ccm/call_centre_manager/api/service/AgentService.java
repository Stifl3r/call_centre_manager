package za.co.ccm.call_centre_manager.api.service;

import org.springframework.stereotype.Service;
import za.co.ccm.call_centre_manager.api.controller.model.AgentDto;
import za.co.ccm.call_centre_manager.api.controller.model.response.AgentResponse;
import za.co.ccm.call_centre_manager.api.exception.NotFoundException;
import za.co.ccm.call_centre_manager.api.repository.AgentRepository;

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

    public AgentDto getAgentById(Long id) throws NotFoundException {
        var result = agentRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Provided agent id does not exist"));
        return new AgentDto(result);
    }
}
