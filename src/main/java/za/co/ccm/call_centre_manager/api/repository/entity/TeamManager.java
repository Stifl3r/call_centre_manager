package za.co.ccm.call_centre_manager.api.repository.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Team_manager")
@Getter
@Setter
public class TeamManager {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "teamid", referencedColumnName = "teamid")
    @ManyToOne
    private Team team;

    @JoinColumn(name = "managerid", referencedColumnName = "managerid")
    @ManyToOne
    private Manager manager;
}
