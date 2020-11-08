package za.co.ccm.call_centre_manager.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.ccm.call_centre_manager.api.controller.model.TeamDto;
import za.co.ccm.call_centre_manager.api.controller.model.response.AgentResponse;
import za.co.ccm.call_centre_manager.api.service.AgentService;

import java.util.List;

import static java.net.HttpURLConnection.HTTP_INTERNAL_ERROR;
import static java.net.HttpURLConnection.HTTP_OK;

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

}
