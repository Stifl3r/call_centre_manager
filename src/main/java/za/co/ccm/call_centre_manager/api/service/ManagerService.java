package za.co.ccm.call_centre_manager.api.service;

import org.springframework.stereotype.Service;
import za.co.ccm.call_centre_manager.api.controller.model.request.AgentToManagerEdit;
import za.co.ccm.call_centre_manager.api.exception.NotFoundException;
import za.co.ccm.call_centre_manager.api.repository.ManagerRepository;

@Service
public class ManagerService {

    private final ManagerRepository managerRepository;
    private final AgentService agentService;

    public ManagerService(ManagerRepository managerRepository,
                          AgentService agentService) {
        this.managerRepository = managerRepository;
        this.agentService = agentService;
    }

    public void assignAgentToManager(Long id, AgentToManagerEdit edit) throws NotFoundException {
        var manager = managerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Provided team id does not exist"));
        agentService.assignManagerToAgent(manager, edit);
    }
}
