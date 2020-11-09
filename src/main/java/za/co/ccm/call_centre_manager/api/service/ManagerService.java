package za.co.ccm.call_centre_manager.api.service;

import org.springframework.stereotype.Service;
import za.co.ccm.call_centre_manager.api.controller.model.request.AgentToManagerEdit;
import za.co.ccm.call_centre_manager.api.controller.model.request.ManagerRequest;
import za.co.ccm.call_centre_manager.api.exception.InvalidFieldException;
import za.co.ccm.call_centre_manager.api.exception.NotFoundException;
import za.co.ccm.call_centre_manager.api.repository.ManagerRepository;
import za.co.ccm.call_centre_manager.api.repository.TeamManagerRepository;
import za.co.ccm.call_centre_manager.api.repository.entity.Manager;

@Service
public class ManagerService {

    private final ManagerRepository managerRepository;
    private final AgentService agentService;
    private final TeamManagerRepository teamManagerRepository;

    public ManagerService(ManagerRepository managerRepository,
                          AgentService agentService,
                          TeamManagerRepository teamManagerRepository) {
        this.managerRepository = managerRepository;
        this.agentService = agentService;
        this.teamManagerRepository = teamManagerRepository;
    }

    public void assignAgentToManager(Long id, AgentToManagerEdit edit) throws NotFoundException {
        var manager = managerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Provided manager id does not exist"));
        agentService.assignManagerToAgent(manager, edit);
    }

    public void createManager(ManagerRequest request) throws InvalidFieldException {
        if (request.getFirstname() == null || request.getFirstname().isBlank()) {
            throw new InvalidFieldException("First name is a required field");
        }

        if (request.getLastname() == null || request.getLastname().isBlank()) {
            throw new InvalidFieldException("Last name is a required field");
        }

        if (request.getIdNumber() == null || request.getIdNumber().isBlank()) {
            throw new InvalidFieldException("Id Number is a required field");
        }

        if (request.getIdNumber().length() != 13) {
            throw new InvalidFieldException("ID number must have 13 characters");
        }

        var manager = new Manager();
        manager.setFirstname(request.getFirstname());
        manager.setLastname(request.getLastname());
        manager.setIdNumber(request.getIdNumber());

        managerRepository.save(manager);
    }

    public void deleteManager(Long id) throws NotFoundException, InvalidFieldException {
        var manager = managerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Provided manager id does not exist"));

        var managerAgents = agentService.getAgentsForManager(manager);
        if (managerAgents.size() > 0) {
            throw new InvalidFieldException("Cannot delete a manager with agents assigned");
        }

        var managedTeams = teamManagerRepository.findAllByManager(manager);
        if (managedTeams.size() > 0) {
            throw new InvalidFieldException("Cannot delete a manager that is assigned to a team")
        }

        managerRepository.delete(manager);
    }
}
