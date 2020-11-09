package za.co.ccm.call_centre_manager.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.ccm.call_centre_manager.api.repository.entity.Manager;

public interface ManagerRepository extends JpaRepository<Manager, Long> {
}
