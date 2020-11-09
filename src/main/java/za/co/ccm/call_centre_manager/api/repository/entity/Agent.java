package za.co.ccm.call_centre_manager.api.repository.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Agent")
@Getter
@Setter
public class Agent {

    @Id
    @Column(name = "agentid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long agentId;
    private String firstname;
    private String lastname;
    private String idNumber;
//    @OneToOne(mappedBy = "Team") TODO Use correct mapping
    @ManyToOne
    @JoinColumn(name = "teamId", referencedColumnName = "teamId")
    private Team team;

    @ManyToOne
    @JoinColumn(name = "managerId", referencedColumnName = "managerId")
    private Manager manager;

}
