package za.co.ccm.call_centre_manager.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;
import za.co.ccm.call_centre_manager.api.controller.model.request.AgentToManagerEdit;
import za.co.ccm.call_centre_manager.api.controller.model.request.ManagerRequest;
import za.co.ccm.call_centre_manager.api.controller.model.request.ManagerToTeamEdit;
import za.co.ccm.call_centre_manager.api.exception.InvalidFieldException;
import za.co.ccm.call_centre_manager.api.exception.NotFoundException;
import za.co.ccm.call_centre_manager.api.service.ManagerService;

import static java.net.HttpURLConnection.*;
import static java.net.HttpURLConnection.HTTP_NOT_FOUND;

@Api(tags = {"Managers"})
@RestController
@RequestMapping("api/managers")
@ApiResponses(value = {
        @ApiResponse(code = HTTP_OK, message = "OK"),
        @ApiResponse(code = HTTP_INTERNAL_ERROR, message = "Internal server error")
})
public class ManagerController {

    private final ManagerService managerService;

    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    @PutMapping("/{id}/agent")
    @ApiOperation(value = "Assign agent to manager")
    @ApiResponses(value = {
            @ApiResponse(code = HTTP_BAD_REQUEST, message = "Bad Request"),
            @ApiResponse(code = HTTP_NOT_FOUND, message = "Not Found")
    })
    public void assignManagerToTeam(@PathVariable Long id, @RequestBody AgentToManagerEdit edit) throws NotFoundException, InvalidFieldException {
        managerService.assignAgentToManager(id, edit);
    }

    @PostMapping()
    @ApiOperation(value = "Create new manager")
    @ApiResponses(value = {
            @ApiResponse(code = HTTP_BAD_REQUEST, message = "Bad Request")
    })
    public void createManager(ManagerRequest request) throws InvalidFieldException {
        managerService.createManager(request);
    }
}
