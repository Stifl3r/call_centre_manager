package za.co.ccm.call_centre_manager.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;
import za.co.ccm.call_centre_manager.api.controller.model.TeamDto;
import za.co.ccm.call_centre_manager.api.controller.model.request.AgentToTeamEdit;
import za.co.ccm.call_centre_manager.api.controller.model.request.ManagerToTeamEdit;
import za.co.ccm.call_centre_manager.api.controller.model.request.TeamRequest;
import za.co.ccm.call_centre_manager.api.exception.InvalidFieldException;
import za.co.ccm.call_centre_manager.api.exception.NotFoundException;
import za.co.ccm.call_centre_manager.api.service.TeamService;

import java.util.List;

import static java.net.HttpURLConnection.*;

@Api(tags = {"Teams"})
@RestController
@RequestMapping("api/teams")
@ApiResponses(value = {
        @ApiResponse(code = HTTP_OK, message = "OK"),
        @ApiResponse(code = HTTP_INTERNAL_ERROR, message = "Internal server error")
})
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping()
    @ApiOperation(value = "Get list of teams")
    public List<TeamDto> getTeams()  {
        return teamService.getAllTeams();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get team by id")
    @ApiResponses(value = {
            @ApiResponse(code = HTTP_NOT_FOUND, message = "Not Found")
    })
    public TeamDto getConsultationById(@PathVariable Long id) throws NotFoundException {
        return teamService.getTeamById(id);
    }

    @PostMapping()
    @ApiOperation(value = "Create a team")
    @ApiResponses(value = {
            @ApiResponse(code = HTTP_BAD_REQUEST, message = "Bad Request")
    })
    public void createAgent(@RequestBody TeamRequest request) throws InvalidFieldException {
        teamService.createATeam(request);
    }

    @PutMapping("/{id}/agent")
    @ApiOperation(value = "Assign agent to team")
    @ApiResponses(value = {
            @ApiResponse(code = HTTP_BAD_REQUEST, message = "Bad Request"),
            @ApiResponse(code = HTTP_NOT_FOUND, message = "Not Found")
    })
    public void assignAgentToTeam(@PathVariable Long id, @RequestBody AgentToTeamEdit edit) throws NotFoundException, InvalidFieldException {
        teamService.assignAgentToTeam(id, edit);
    }

    @PutMapping("/{id}/manager")
    @ApiOperation(value = "Assign manager to team")
    @ApiResponses(value = {
            @ApiResponse(code = HTTP_BAD_REQUEST, message = "Bad Request"),
            @ApiResponse(code = HTTP_NOT_FOUND, message = "Not Found")
    })
    public void assignManagerToTeam(@PathVariable Long id, @RequestBody ManagerToTeamEdit edit) throws NotFoundException, InvalidFieldException {
        teamService.assignManagerToTeam(id, edit);
    }
}
