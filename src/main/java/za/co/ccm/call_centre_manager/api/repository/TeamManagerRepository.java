package za.co.ccm.call_centre_manager.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.ccm.call_centre_manager.api.repository.entity.Manager;
import za.co.ccm.call_centre_manager.api.repository.entity.Team;
import za.co.ccm.call_centre_manager.api.repository.entity.TeamManager;

import java.util.List;
import java.util.Optional;

public interface TeamManagerRepository extends JpaRepository<TeamManager, Long> {

    List<TeamManager> findAllByTeam(Team team);

    List<TeamManager> findAllByManager(Manager manager);

    Optional<TeamManager> findByManager(Manager manager);
}
