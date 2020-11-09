package za.co.ccm.call_centre_manager.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import za.co.ccm.call_centre_manager.api.repository.entity.Agent;

public interface AgentRepository extends JpaRepository<Agent, Long> {

    @Query(" select a " +
            "  from Agent a")
    Page<Agent> findByFilter(Pageable pageable);
}
