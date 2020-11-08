package za.co.ccm.call_centre_manager.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.ccm.call_centre_manager.api.controller.model.TeamDto;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("api/teams")
public class TeamController {

    @GetMapping()
    public List<TeamDto> getTeams()  {
        return Collections.EMPTY_LIST;
    }
}
