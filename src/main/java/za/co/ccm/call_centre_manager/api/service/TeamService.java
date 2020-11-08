package za.co.ccm.call_centre_manager.api.service;

import org.springframework.stereotype.Service;
import za.co.ccm.call_centre_manager.api.controller.model.TeamDto;
import za.co.ccm.call_centre_manager.api.repository.TeamRepository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamService {

    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public List<TeamDto> getAllTeams() {
        var teams = teamRepository.findAll();
        return teams.stream().map(TeamDto::new).collect(Collectors.toList());
    }
}
