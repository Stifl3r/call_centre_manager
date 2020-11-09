package za.co.ccm.call_centre_manager.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import za.co.ccm.call_centre_manager.api.repository.entity.Team;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {

    @Query(value = "select t.* " +
            "  from team t" +
            " where t.teamid not in (select tm.teamid " +
            "                          from ccm.team_manager tm) " +
            "   and t.teamid not in (select a.teamid " +
            "                          from ccm.agent a) ", nativeQuery = true)
    List<Team> getTeamsWithoutAgentsAndManagers();

    @Query("select distinct t " +
            " from Team t " +
            " left outer join t.managers tm ")
    List<Team> getTeamsWithManagers();
}
