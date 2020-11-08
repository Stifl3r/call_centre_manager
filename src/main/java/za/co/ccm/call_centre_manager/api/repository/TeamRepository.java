package za.co.ccm.call_centre_manager.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.ccm.call_centre_manager.api.repository.entity.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
