package za.co.ccm.call_centre_manager.api.repository.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Team")
@Getter
@Setter
public class Team {

    @Id
    @Column(name = "teamid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teamId;
    private String name;
}
