package za.co.ccm.call_centre_manager.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import za.co.ccm.call_centre_manager.api.controller.model.AgentDto;
import za.co.ccm.call_centre_manager.api.controller.model.TeamDto;
import za.co.ccm.call_centre_manager.api.controller.model.request.AgentFilter;
import za.co.ccm.call_centre_manager.api.controller.model.request.AgentRequest;
import za.co.ccm.call_centre_manager.api.controller.model.response.AgentPage;
import za.co.ccm.call_centre_manager.api.controller.model.response.AgentResponse;
import za.co.ccm.call_centre_manager.api.exception.InvalidFieldException;
import za.co.ccm.call_centre_manager.api.exception.NotFoundException;
import za.co.ccm.call_centre_manager.api.service.AgentService;

import java.util.List;

import static java.net.HttpURLConnection.*;
import static org.springframework.data.domain.PageRequest.of;

@Api(tags = {"Agents"})
@RestController
@RequestMapping("api/agents")
@ApiResponses(value = {
        @ApiResponse(code = HTTP_OK, message = "OK"),
        @ApiResponse(code = HTTP_INTERNAL_ERROR, message = "Internal server error")
})
public class AgentController {

    private final AgentService agentService;

    public AgentController(AgentService agentService) {
        this.agentService = agentService;
    }

    @GetMapping()
    @ApiOperation(value = "Get list of agents")
    public List<AgentResponse> getAllAgents()  {
        return agentService.getAllAgents();
    }

    @GetMapping("/paginated")
    @ApiOperation(value = "Get paginated list of agents")
    public Page<AgentPage> getPaginatedAgents(AgentFilter filter)  {
        var pageRequest = of(filter.getPageIndex(), filter.getPageSize());
        return agentService.getPaginatedAgents(pageRequest);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get agent by id")
    @ApiResponses(value = {
            @ApiResponse(code = HTTP_NOT_FOUND, message = "Not Found")
    })
    public AgentDto getConsultationById(@PathVariable Long id) throws NotFoundException {
        return agentService.getAgentById(id);
    }


    @PostMapping()
    @ApiOperation(value = "Create an agent")
    @ApiResponses(value = {
            @ApiResponse(code = HTTP_BAD_REQUEST, message = "Bad Request")
    })
    public void createAgent(@RequestBody AgentRequest request) throws InvalidFieldException {
        agentService.createAnAgent(request);
    }
}
