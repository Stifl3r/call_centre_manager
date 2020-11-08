package za.co.ccm.call_centre_manager.api.repository.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "manager")
@Getter
@Setter
public class Manager {
    @Id
    @Column(name = "managerid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long managerId;
    private String firstname;
    private String lastname;
    private String idNumber;
}
